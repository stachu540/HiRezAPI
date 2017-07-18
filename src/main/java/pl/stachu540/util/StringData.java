package pl.stachu540.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class StringData {

    private final String string;

    /**
     * String Data
     * @param string string
     */
    public StringData(String string) {
        this.string = string;
    }

    /**
     * Getting data to {@link String}
     * @return {@link String}
     */
    @Override
    public String toString() {
        return string;
    }

    /**
     * Getting data to {@link JSONArray}
     * @return {@link JSONArray}
     */
    public JSONArray toJsonArray() {
        return new JSONArray(string);
    }

    /**
     * Getting data to {@link JSONObject}
     * @return {@link JSONObject}
     */
    public JSONObject toJsonObject() {
        return new JSONObject(string);
    }

// TODO: supporting on next release.
//    /**
//     * Getting data to XML {@link Document}
//     * @return {@link Document}
//     */
//    public Document toXmlData() {
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder;
//
//            builder = factory.newDocumentBuilder();
//            return builder.parse(new InputSource(new StringReader(string)));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
}
