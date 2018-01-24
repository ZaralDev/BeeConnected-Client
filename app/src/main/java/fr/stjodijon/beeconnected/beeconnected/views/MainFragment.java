package fr.stjodijon.beeconnected.beeconnected.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.stjodijon.beeconnected.beeconnected.R;

/**
 * Created by Utilisateur on 24/01/2018.
 */

public class MainFragment extends Fragment {

    private final int layout = R.layout.main_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(layout, container, false);

        Spinner spinner = v.findViewById(R.id.spinner);
        initSpinner(spinner);
        return v;
    }


    private void initSpinner(Spinner spinner) {

        ArrayList<String> list = new ArrayList<>();
        list.add("Ruche 1");
        list.add("Ruche 2");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
