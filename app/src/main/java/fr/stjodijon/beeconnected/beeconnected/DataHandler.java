package fr.stjodijon.beeconnected.beeconnected;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.stjodijon.beeconnected.beeconnected.utils.JsonData;

/**
 * Created by Zaral on 10/02/2018.
 */

public class DataHandler {

    private ArrayList<DataObject> dataList = new ArrayList<>();

    private final JSONObject mainObj;

    public DataHandler(JSONObject mainObj) throws JSONException {
        this.mainObj = mainObj;

        JSONArray data = mainObj.getJSONArray("data");
        if (data.length() == 0) {
            dataList.add(new DataObject(JsonData.EMPTY_DATA));
        }
        for (int i = 0; i < data.length(); i++) {
            JSONObject d = data.getJSONObject(i);
            dataList.add(new DataObject(d));
        }
    }

    public DataObject getLatestData() {
        DataObject latest = dataList.get(0);
        if (dataList.size() >= 1) {
            for (int i = 1; i < dataList.size(); i++) {
                DataObject temp = dataList.get(i);
                if (temp.getTimestamp() > latest.getTimestamp()) {
                    latest = temp;
                }
            }
        }
        return latest;
    }

    public DataObject getBefore(DataObject obj) {
        DataObject nearest = null;

        for (int i = 0; i < dataList.size(); i++) {
            DataObject temp = dataList.get(i);
            if (obj.getTimestamp() != temp.getTimestamp() ) {
                if (nearest == null) nearest = temp;
                if (Math.abs(obj.getTimestamp()-nearest.getTimestamp()) >  Math.abs(obj.getTimestamp()-temp.getTimestamp()))   {
                    nearest = temp;
                }
            }
        }
        return nearest;
    }
}
