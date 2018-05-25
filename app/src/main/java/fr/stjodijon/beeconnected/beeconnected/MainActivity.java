package fr.stjodijon.beeconnected.beeconnected;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


import java.util.List;

import fr.stjodijon.beeconnected.beeconnected.views.GraphFragment;
import fr.stjodijon.beeconnected.beeconnected.views.MainFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Starting app");
        /*
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty("http.proxyHost","10.29.0.252");
        systemProperties.setProperty("http.proxyPort","9009");*/
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        doTransaction(new MainFragment(this));
    }

    @Override
    public void onBackPressed() {

        if (getVisibleFragment() instanceof MainFragment) {
            super.onBackPressed();
        } else {
            doTransaction(new MainFragment(this));
        }

    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                MainFragment fragment = new MainFragment(this);
                this.doTransaction(fragment);

        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void doTransaction(final Fragment fragment) {

        android.support.v4.app.FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity, fragment);
        fragmentTransaction.commit();
    }

}
