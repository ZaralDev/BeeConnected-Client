package fr.stjodijon.beeconnected.beeconnected.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Utilisateur on 03/02/2018.
 */

public class JsonData {

    public static JSONObject FAKE_DATA, EMPTY_DATA ;

    static {
        try {
            JSONObject data = new JSONObject().put("temp", 20).
                    put("lum", 500).put("weight", 51).put("humidity", "15").put("id", 1).put("timestamp", 254514);
            FAKE_DATA = data;
            JSONObject data_2 = new JSONObject().put("temp", 0).
                    put("lum", 0).put("weight", 0).put("humidity", "0").put("id", 0).put("timestamp", 0);
            EMPTY_DATA = data_2;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
