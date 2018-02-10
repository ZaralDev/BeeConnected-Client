package fr.stjodijon.beeconnected.beeconnected;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zaral on 10/02/2018.
 */

public class DataObject {

    private final JSONObject obj;

    private final int id;
    private int temp, lum, weight, humidity;
    private long timestamp;
    public DataObject(JSONObject obj) throws JSONException {
        this.obj = obj;
        System.out.println(obj.toString());
        this.id = obj.getInt("id");
        this.temp = obj.getInt("temp");
        this.weight = obj.getInt("weight");
        this.lum = obj.getInt("lum");
        this.humidity = obj.getInt("humidity");
        this.timestamp = obj.getLong("timestamp");
    }

    public int getId() {
        return id;
    }

    public int getTemp() {
        return temp;
    }

    public int getLuminosity() {
        return lum;
    }

    public int getWeight() {
        return weight;
    }

    public int getHumidity() {
        return humidity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getPercentDiffTemp(DataObject obj) {
        return new Float(((getTemp()-obj.getTemp())/(float)obj.getTemp())*100).intValue();
    }


    public int getPercentDiffLum(DataObject obj) {
        return new Float(((getLuminosity()-obj.getLuminosity())/(float)obj.getLuminosity())*100).intValue();
    }


    public int getPercentDiffWeight(DataObject obj) {
        return new Float(((getWeight()-obj.getWeight())/(float)obj.getWeight())*100).intValue();
    }


    public int getPercentDiffHumidity(DataObject obj) {
        return new Float(((getHumidity()-obj.getHumidity())/(float)obj.getHumidity())*100).intValue();
    }
}

