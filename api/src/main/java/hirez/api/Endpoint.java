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
                })).doOnSuccess(r -> LOGGER.debug("[onSuccess()] \"" + url + "\""))
                .doOnError(e -> LOGGER.error("[onError()] \"" + url + "\"", e))
                .doOnSubscribe(d -> LOGGER.debug("[onSubscribe()] \"" + url + "\""))
                .doOnDispose(() -> LOGGER.debug("[onDisposed()] \"" + url + "\""))
                .doOnTerminate(() -> LOGGER.trace("[onTerminate()] \"" + url + "\""));
    }

    protected final  <T> Single<T> call(Class<T> type, String method, String... argv) {
        return get(type, configuration.createUrl(method, argv));
    }

    private <T> T buildResponse(Response response, Class<T> type) throws IOException {
        String body = Objects.requireNonNull(response.body()).string();
        if (type.isInstance(body)) { // Safety reflection if instance is String
            return type.cast(body); // Safety cast avoid use @SuppressWarnings annotation
        } else {
            return mapper.readValue(body, type);
        }
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
            }).flatMap(r -> Single.create(sink -> {
                if (type.isAssignableFrom(ReturnedMessage.class)) {
                    ReturnedMessage rm = (type.isArray()) ? ((ReturnedMessage[]) r)[0] : (ReturnedMessage) r;
                    if (rm.getReturnedMessage() != null) {
                        sink.onError(new HiRezException(rm));
                    } else {
                        sink.onSuccess(r);
                    }
                } else {
                    sink.onSuccess(r);
                }
            }));
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
                .map(TestSession::new);
    }

    public final Single<Ping> ping() {
        return call(String.class, "ping")
                .map(Ping::new);
    }

    public final Single<DataUsage> getDataUsed() {
        return testAndCall(DataUsage[].class, "getdataused")
                .map(d -> d[0]);
    }

    public final Flowable<HiRezServer> getHiRezServerStatus() {
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
