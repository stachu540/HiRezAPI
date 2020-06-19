package hirez.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import hirez.api.object.*;
import hirez.api.object.interfaces.ReturnedMessage;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.Getter;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Endpoint {
    static final String DEFAULT_USER_AGENT = new StringBuilder(GitProperties.get(GitProperties.APPLICATION_NAME))
            .append(" v").append(GitProperties.get(GitProperties.APPLICATION_VERSION))
            .append(" [Rev. ").append(GitProperties.get(GitProperties.GIT_COMMIT_ID_ABBREV)).append("]")
            .toString();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Getter
    private final Configuration configuration;
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;

    protected Endpoint(Configuration configuration) {
        this.configuration = configuration;

        OkHttpClient.Builder httpBulder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(LOGGER::debug)
                        .setLevel(HttpLoggingInterceptor.Level.BASIC));
        ObjectMapper mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .findAndRegisterModules();
        configuration.getHttpClient().accept(httpBulder);
        configuration.getMapper().accept(mapper);

        this.httpClient = httpBulder.build();
        this.mapper = mapper;
    }

    final <T> Single<T> get(Class<T> type, String url) {
        return Single.<T>create(sink ->
                httpClient.newCall(new Request.Builder().get()
                        .header("User-Agent", configuration.getUserAgent())
                        .url(url).build()).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        sink.onError(e);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        sink.onSuccess(buildResponse(response, type));
                    }
                }))
                .doOnEvent((success, error) -> {
                    if (Objects.nonNull(success)) {
                        LOGGER.debug("[SUCCESS] " + url + " -> " + success);
                    }
                    if (Objects.nonNull(error)) {
                        LOGGER.error("[ERROR] " + url, error);
                    }
                })
                .doOnSubscribe(d -> LOGGER.debug("[SUBSCRIBE] \"" + url + "\""))
                .doOnDispose(() -> LOGGER.debug("[DISPOSE] \"" + url + "\""))
                .doOnTerminate(() -> LOGGER.warn("[TERMINATE] \"" + url + "\""));
    }

    protected final <T> Single<T> call(Class<T> type, String method, String... argv) {
        return get(type, configuration.createUrl(method, argv)).flatMap(r -> Single.create(sink -> {
            ReturnedMessage rm = null;
            if (r instanceof ReturnedMessage[] && ((ReturnedMessage[]) r).length > 0) {
                rm = ((ReturnedMessage[]) r)[0];
            }
            if (r instanceof ReturnedMessage) {
                rm = (ReturnedMessage) r;
            }

            if (rm != null) {
                if (rm.getReturnedMessage() != null) {
                    if (method.equalsIgnoreCase("createsession")) {
                        if (rm.getReturnedMessage().equals("Approved")) {
                            sink.onSuccess(r);
                        } else {
                            sink.onError(new HiRezException(rm));
                        }
                    } else {
                        sink.onError(new HiRezException(rm));
                    }
                } else {
                    sink.onSuccess(r);
                }
            } else {
                sink.onSuccess(r);
            }
        }));
    }

    private <T> T buildResponse(Response response, Class<T> type) throws IOException {
        return mapper.readValue(Objects.requireNonNull(response.body()).charStream(), type);
    }

    protected final <T> Single<T> testAndCall(Class<T> type, String method, String... argv) {
        if (!configuration.getSessionStorage().isPresent()) {
            return createSession().flatMap(c -> testAndCall(type, method, argv));
        } else {
            return testSession().flatMap(t -> {
                if (!t.isSuccessful()) {
                    return createSession().flatMap(c -> testAndCall(type, method, argv));
                } else {
                    return call(type, method, argv);
                }
            });
        }
    }

    public final Single<CreateSession> createSession() {
        return call(CreateSession.class, "createsession").doOnSuccess(r -> {
            if (r.getReturnedMessage().equalsIgnoreCase("Approved")) {
                configuration.getSessionStorage().set(r);
            }
        });
    }

    public final Single<TestSession> testSession() {
        return call(String.class, "testsession")
                .map(TestSession::new).flatMap(response ->
                        Single.<TestSession>create(sink -> {
                            if (response.isSuccessful()) {
                                sink.onSuccess(response);
                            } else {
                                sink.onError(new HiRezException(response.getRawMessage()));
                            }
                        })
                ).onErrorResumeNext(e -> {
                    if (e instanceof HiRezException && e.getMessage().contains("Invalid session id.")) {
                        return createSession().flatMap($ -> testSession());
                    } else return Single.error(e);
                });
    }

    public final Single<Ping> ping() {
        return call(String.class, "ping")
                .map(Ping::new);
    }

    public Single<DataUsage> getDataUsed() {
        return testAndCall(DataUsage[].class, "getdataused")
                .map(d -> d[0]);
    }

    public Flowable<HiRezServer> getHiRezServerStatus() {
        return testAndCall(HiRezServer[].class, "gethirezserverstatus")
                .flattenAsFlowable(Arrays::asList);
    }

    public final Single<PatchInfo> getPatchInfo() {
        return testAndCall(PatchInfo.class, "getpatchinfo");
    }

    public final StatusPage getStatusPage() {
        return new StatusPage(this);
    }
}
