package hirez.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.reactivex.Single;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j(topic = "hirez.RestClient")
public class RestClient {
    static final String DEFAULT_USER_AGENT = new StringBuilder(GitProperties.get(GitProperties.APPLICATION_NAME))
            .append(" v").append(GitProperties.get(GitProperties.APPLICATION_VERSION))
            .append(" [Rev. ").append(GitProperties.get(GitProperties.GIT_COMMIT_ID_ABBREV)).append("]")
            .toString();
    private final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .registerModule(new JavaTimeModule());
    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(log::debug)
                    .setLevel(HttpLoggingInterceptor.Level.BASIC)).build();
    @Getter
    private final Configuration configuration;

    public <T> Single<T> get(Class<T> type, String url) {
        Request request = new Request.Builder().get().addHeader("User-Agent", configuration.getUserAgent())
                .url(url).build();

        return Single.<T>create(sink -> httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sink.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sink.onSuccess(buildResponse(response, type));
            }
        })).doOnSuccess(r -> log.debug("[onSuccess()] \"" + url + "\""))
                .doOnError(e -> log.error("[onError()] \"" + url + "\"", e))
                .doOnSubscribe(d -> log.debug("[onSubscribe()] \"" + url + "\""))
                .doOnDispose(() -> log.debug("[onDisposed()] \"" + url + "\""))
                .doOnTerminate(() -> log.trace("[onTerminate()] \"" + url + "\""));
    }

    @SuppressWarnings("unchecked")
    private <T> T buildResponse(Response response, Class<T> type) throws IOException {
        if (response.body() == null) return null;
        String body = response.body().string();
        if (String.class.equals(type)) {
            return (T) body;
        } else {
            return mapper.readValue(body, type);
        }
    }
}
