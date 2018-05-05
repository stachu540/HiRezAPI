package hirezapi.rest;

import hirezapi.Platform;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class RestClient {
    private final RestTemplate restClient = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    private final Set<ClientHttpRequestInterceptor> restInterceptors = new LinkedHashSet<>();

    public RestClient(Platform platform) {
        this.restClient.setUriTemplateHandler(new DefaultUriBuilderFactory(platform.getBaseUrl()));
        addRestInterceptor(new RestLogger());
    }

    public void addRestInterceptor(ClientHttpRequestInterceptor interceptor) {
        restInterceptors.add(interceptor);
    }

    public RestTemplate getRestClient() {
        this.restClient.setErrorHandler(new RestErrorHandler());
        this.restClient.setInterceptors(new ArrayList<>(restInterceptors));

        return this.restClient;
    }
}
