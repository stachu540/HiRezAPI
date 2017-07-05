package pl.stachu540;


import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class API {
    private final String source;
    private final Map<String, String> headers = new HashMap<String, String>();
    private static final int timeout = 2 * 1000;

    public enum requestType {
        GET, POST, PUT, DELETE
    }

    public API(String url) {
        this.headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.52 Safari/537.36");
        this.source = url;
    }

    public void setHeader(String name, String value) {
        if (!name.equals("User-Agent")) {
            headers.put(name, value);
        }
    }

    public void deleteHeader(String name) {
        if (!name.equals("User-Agent")) {
            headers.remove(name);
        }
    }

    public JSONObject getData(requestType req, String path) {
        return getData(req, path, "");
    }

    @SuppressWarnings("UseSpecificCatch")
    public JSONObject getData(requestType req, String path, String post) {
        JSONObject jsonData = new JSONObject("{}");
        InputStream is = null;
        String content = "";
        String src = (source.endsWith("/")) ? source.substring(0, (source.length() - 1)) : source;
        path = path.startsWith("/") ? path.substring(1) : path;
        String url_path = src + "/" + path;
        try {
            URL url = new URL(url_path);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            headers.forEach(c::addRequestProperty);

            c.setRequestMethod(req.name());
            c.setConnectTimeout(timeout);

            if (!post.isEmpty()) c.setDoOutput(true);
            c.connect();
            if (!post.isEmpty()) {
                try (OutputStream o = c.getOutputStream()){
                    o.write(post.getBytes(Charset.forName("UTF-8")));
                }
            }

            is = c.getInputStream();
            content = IOUtils.toString(is, c.getContentEncoding());

            fillJSONObject(jsonData, true, req.name(), post, url_path, c.getResponseCode(), "", "", content);
        } catch (Exception e) {
            Throwable rootCause = e;
            while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
                rootCause = rootCause.getCause();
            }

            fillJSONObject(jsonData, false, req.name(), post, url_path, 0, e.getClass().getSimpleName(), e.getMessage(), content);
            System.err.println(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    fillJSONObject(jsonData, false, req.name(), post, url_path, 0, e.getClass().getName(), e.getMessage(), content);
                    System.err.println(e.getMessage());
                }
            }
        }

        return jsonData;
    }

    private void fillJSONObject(JSONObject jsonObject, boolean success, String type, String post, String url, int responseCode, String exception, String exceptionMessage, String jsonContent) {
        jsonObject.put("_success", success);
        jsonObject.put("_type", type);
        jsonObject.put("_post", post);
        jsonObject.put("_url", url);
        jsonObject.put("_http", responseCode);
        jsonObject.put("_exception", exception);
        jsonObject.put("_exceptionMessage", exceptionMessage);
        jsonObject.put("_content", jsonContent);
    }
}
