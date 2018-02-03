package fr.stjodijon.beeconnected.beeconnected.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Utilisateur on 03/02/2018.
 */

public class JsonTestData {

    public static JSONObject FAKE_DATA ;

    static {
        try {
            JSONObject data = new JSONObject().put("temp", 20).
                    put("lum", 500).put("weight", 51).put("humidity", "15").put("id", 1).put("timestamp", 254514);
            FAKE_DATA = new JSONObject().put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
