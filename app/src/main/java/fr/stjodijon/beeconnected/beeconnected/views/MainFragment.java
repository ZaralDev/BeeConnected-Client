package fr.stjodijon.beeconnected.beeconnected.views;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import fr.stjodijon.beeconnected.beeconnected.MainActivity;
import fr.stjodijon.beeconnected.beeconnected.R;
import fr.stjodijon.beeconnected.beeconnected.adapter.BeeSpinnerAdapter;
import fr.stjodijon.beeconnected.beeconnected.utils.HttpUtils;
import fr.stjodijon.beeconnected.beeconnected.utils.JsonTestData;

/**
 * Created by Utilisateur on 24/01/2018.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {

    private final int layout = R.layout.main_layout;
    private MainActivity main;

    private TextView tempData, lumData, weightData, humidityData;
    public MainFragment(MainActivity main) {
        this.main = main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(layout, container, false);

        Spinner spinner = v.findViewById(R.id.spinner);
        tempData = v.findViewById(R.id.data_temp);
        lumData = v.findViewById(R.id.data_lum);
        weightData = v.findViewById(R.id.data_weight);
        humidityData = v.findViewById(R.id.data_humidity);

        initSpinner(spinner);
        return v;
    }

    private void initSpinner(Spinner spinner) {

        ArrayList<String> list = new ArrayList<>();
        list.add("Ruche 1");
        list.add("Ruche 2");
        BeeSpinnerAdapter adapter = new BeeSpinnerAdapter(this.getContext(),
                android.R.layout.simple_spinner_item, list);

        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        final int[] d = {0};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Test" + i + 1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(11, 1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            d[0]++;
                            JSONObject obj = JsonTestData.FAKE_DATA;
                            try {
                                JSONObject data = obj.getJSONObject("data");
                                tempData.setText((d[0] +data.getInt("temp")) + "°C");
                                lumData.setText((d[0] +data.getInt("lum") )+ "Lux");
                                humidityData.setText((d[0] +data.getInt("humidity")) + "%");
                                weightData.setText((d[0] +data.getInt("weight")) + "Kg");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        //final String r = HttpUtils.sendGet("http://149.91.88.38:3925/api/data?id=1&limit=3");
                            //String r = HttpUtils.sendGet("http://google.fr");
                            //System.out.println(r);


                    }
                }).start();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void getInfo() {

    }

}
