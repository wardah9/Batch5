package Batch5.ita.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ApplicationSharedActivity extends AppCompatActivity {

    private SharedPreferences UserPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_shared);


        Timer RunSplash = new Timer();

        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                finish();

                UserPrefs = getSharedPreferences("UserPrefs2", MODE_PRIVATE);
                boolean isaccepted = UserPrefs.getBoolean("isaccepted", false);

                if (isaccepted) {
                    Intent myIntent = new Intent(ApplicationSharedActivity.this, WelcomeScreenSharedActivity.class);
                    startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(ApplicationSharedActivity.this, TermsAndConditionsActivity.class);
                    startActivity(myIntent);
                }
            }
        };

        RunSplash.schedule(ShowSplash, 5000);
    }
}
