package fr.stjodijon.beeconnected.beeconnected;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import fr.stjodijon.beeconnected.beeconnected.views.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doTransaction(new MainFragment());


    }


    private void injectData() {
        LinearLayout layout = findViewById(R.id.data_layout);
        

    }
    private void doTransaction(final Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity, fragment);
        fragmentTransaction.commit();
    }

}
