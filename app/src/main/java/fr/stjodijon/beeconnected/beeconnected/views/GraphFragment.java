package fr.stjodijon.beeconnected.beeconnected.views;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.stjodijon.beeconnected.beeconnected.DataHandler;
import fr.stjodijon.beeconnected.beeconnected.DataObject;
import fr.stjodijon.beeconnected.beeconnected.MainActivity;
import fr.stjodijon.beeconnected.beeconnected.R;
import fr.stjodijon.beeconnected.beeconnected.adapter.DateFormater;
import fr.stjodijon.beeconnected.beeconnected.utils.HttpUtils;


/**
 * Created by Zaral on 11/02/2018.
 */

@SuppressLint("ValidFragment")
public class GraphFragment extends Fragment {

    private final int layout = R.layout.graph_layout;
    private MainActivity main;
    private String graphType;
    private int id;

    public GraphFragment(MainActivity main, String graphType, int id) {
        this.main = main;
        this.graphType = graphType;
        this.id = id;
        System.out.println(id);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(layout, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (main.getSupportActionBar() != null){
            main.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            main.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    final LineChart chart = (LineChart) v.findViewById(R.id.chart);

                    final List<Entry> entries = new ArrayList<>();
                    final String r = HttpUtils.sendGet("http://149.91.88.38:3925/api/data?id=" + id + "&limit=0"); //0 to get all datas
                    JSONObject obj = new JSONObject(r);
                    DataHandler handler = new DataHandler(obj);
                    ArrayList<DataObject> datas = handler.getDataList();

                    for (DataObject d : datas) {
                        switch (graphType) {
                            case "temp":
                                entries.add(new Entry(d.getTimestamp(), d.getTemp()));
                                break;
                            case "lum":
                                entries.add(new Entry(d.getTimestamp(), d.getLuminosity()));
                                break;
                            case "weight":
                                entries.add(new Entry(d.getTimestamp(), d.getWeight()));
                                break;
                            case "hum":
                                entries.add(new Entry(d.getTimestamp(), d.getHumidity()));
                                break;
                        }
                    }

                    Collections.sort(entries, new EntryXComparator());

                    main.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            LineDataSet dataSet = new LineDataSet(entries, graphType);
                            LineData lineData = new LineData(dataSet);
                            XAxis xAxis = chart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setTextSize(10f);
                            xAxis.setDrawAxisLine(true);
                            xAxis.setDrawGridLines(false);
                            xAxis.setValueFormatter(new DateFormater());

                            YAxis rightAxis = chart.getAxisRight();

                            rightAxis.setEnabled(false);
                            //dataSet.setHighlightEnabled(false);
                            dataSet.setColor(getResources().getColor(R.color.colorPrimary));
                            chart.getLegend().setEnabled(false);
                            Description desc = new Description();
                            desc.setText("");

                            chart.setDescription(desc);
                            chart.setData(lineData);
                            chart.invalidate();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, 500);


        return v;
    }



}
