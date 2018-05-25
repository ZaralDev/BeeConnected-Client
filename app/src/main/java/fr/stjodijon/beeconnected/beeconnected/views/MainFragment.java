package fr.stjodijon.beeconnected.beeconnected.views;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import fr.stjodijon.beeconnected.beeconnected.DataHandler;
import fr.stjodijon.beeconnected.beeconnected.DataObject;
import fr.stjodijon.beeconnected.beeconnected.MainActivity;
import fr.stjodijon.beeconnected.beeconnected.R;
import fr.stjodijon.beeconnected.beeconnected.adapter.BeeSpinnerAdapter;
import fr.stjodijon.beeconnected.beeconnected.utils.HttpUtils;

/**
 * Created by Utilisateur on 24/01/2018.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {

    private final int layout = R.layout.main_layout;
    private MainActivity main;

    private TextView tempData, lumData, weightData, humidityData;
    private TextView tempStats, lumStats, weightStats, humidityStats;

    private ImageView tempInc, lumInc, weightInc, humidityInc;


    public MainFragment(MainActivity main) {
        this.main = main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(layout, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (main.getSupportActionBar() != null){
            main.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            main.getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

        final Spinner spinner = v.findViewById(R.id.spinner);
        tempData = v.findViewById(R.id.data_temp);
        lumData = v.findViewById(R.id.data_lum);
        weightData = v.findViewById(R.id.data_weight);
        humidityData = v.findViewById(R.id.data_humidity);

        tempStats = v.findViewById(R.id.stats_temp);
        lumStats = v.findViewById(R.id.stats_lum);
        weightStats = v.findViewById(R.id.stats_weight);
        humidityStats = v.findViewById(R.id.stats_humidity);

        tempInc = v.findViewById(R.id.cross_temp);
        lumInc = v.findViewById(R.id.cross_lum);
        weightInc = v.findViewById(R.id.cross_weight);
        humidityInc = v.findViewById(R.id.cross_humidity);
        initSpinner(spinner);
        v.findViewById(R.id.temp_layout).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                GraphFragment fragment = new GraphFragment(main, "temp", spinner.getSelectedItemPosition()+1);
                main.doTransaction(fragment);
            }
        });
        v.findViewById(R.id.lum_layout).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                GraphFragment fragment = new GraphFragment(main, "lum", spinner.getSelectedItemPosition()+1);
                main.doTransaction(fragment);
            }
        });
        v.findViewById(R.id.weight_layout).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                GraphFragment fragment = new GraphFragment(main, "weight", spinner.getSelectedItemPosition()+1);
                main.doTransaction(fragment);
            }
        });
        v.findViewById(R.id.humidity_layout).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                GraphFragment fragment = new GraphFragment(main, "hum", spinner.getSelectedItemPosition()+1);
                main.doTransaction(fragment);
            }
        });
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final int hiveId = i+1;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String r = HttpUtils.sendGet("http://149.91.88.38:3925/api/data?id=" + hiveId + "&limit=2");
                            main.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {

                                        JSONObject obj = new JSONObject(r);
                                        DataHandler handler = new DataHandler(obj);
                                        DataObject latestData = handler.getLatestData();
                                        tempData.setText(latestData.getTemp() + "°C");
                                        lumData.setText(latestData.getLuminosity() + "Lux");
                                        humidityData.setText(latestData.getHumidity() + "%");
                                        weightData.setText(latestData.getWeight() + "Kg");
                                        
                                        DataObject nearest = handler.getBefore(latestData);
                                        if (nearest != null) {
                                            int tempPercent = latestData.getPercentDiffTemp(nearest);
                                            boolean incTemp = tempPercent >= 0;
                                            tempStats.setText(tempPercent + "%");
                                            tempInc.setImageResource(incTemp ? R.drawable.ic_diagonal_arrow:  R.drawable.ic_diagonal_arrow_down );

                                            int lumPercent = latestData.getPercentDiffLum(nearest);
                                            boolean incLum = lumPercent >= 0;
                                            lumStats.setText(lumPercent + "%");
                                            lumInc.setImageResource(incLum ? R.drawable.ic_diagonal_arrow:  R.drawable.ic_diagonal_arrow_down );

                                            int humidityPercent = latestData.getPercentDiffHumidity(nearest);
                                            boolean incHum = humidityPercent >= 0;
                                            humidityStats.setText(humidityPercent + "%");
                                            humidityInc.setImageResource(incHum ? R.drawable.ic_diagonal_arrow:  R.drawable.ic_diagonal_arrow_down );

                                            int weightPercent = latestData.getPercentDiffWeight(nearest);
                                            boolean incWeight = weightPercent >= 0;
                                            weightStats.setText(weightPercent + "%");
                                            weightInc.setImageResource(incWeight ? R.drawable.ic_diagonal_arrow:  R.drawable.ic_diagonal_arrow_down );
                                        } else {
                                            //Default values
                                            tempStats.setText( "-");
                                            lumStats.setText("-");
                                            humidityStats.setText("-");
                                            weightStats.setText("-");

                                            tempInc.setImageResource( R.drawable.ic_diagonal_arrow);
                                            lumInc.setImageResource( R.drawable.ic_diagonal_arrow );
                                            humidityInc.setImageResource( R.drawable.ic_diagonal_arrow);
                                            weightInc.setImageResource(R.drawable.ic_diagonal_arrow);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                //String r = HttpUtils.sendGet("http://google.fr");
                            //System.out.println(r);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

}
