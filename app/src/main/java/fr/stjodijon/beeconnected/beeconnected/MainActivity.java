package fr.stjodijon.beeconnected.beeconnected;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Properties;

import fr.stjodijon.beeconnected.beeconnected.views.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Starting app");
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty("http.proxyHost","10.29.0.252");
        systemProperties.setProperty("http.proxyPort","9009");
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        doTransaction(new MainFragment(this));
    }


    private void injectData() {


    }

    private void doTransaction(final Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity, fragment);
        fragmentTransaction.commit();
    }

}
