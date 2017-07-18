package pl.stachu540.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class HttpClient {
    private final String base_url;
    private final int timeout = 10; // default timeout is 10 seconds
    private final OkHttpClient client;

    public HttpClient(String base_url) {
        this.base_url = base_url;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build();
    }

    protected String request(String endpoint, String... args) {
        try {
            String url = (base_url.endsWith("/")) ? base_url : base_url + "/";
            url += (endpoint.startsWith("/")) ? endpoint.substring(1) : endpoint;
            if (!url.endsWith("/") && Arrays.asList(args).toArray().length > 0) {
                url += "/";
            }
            url += (Arrays.asList(args).toArray().length > 0) ?
                    String.join("/", Arrays.asList(args)) :
                    "";

            Request request = new Request.Builder()
                    .url(url)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.52 Safari/537.36")
                    .build();

            Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
