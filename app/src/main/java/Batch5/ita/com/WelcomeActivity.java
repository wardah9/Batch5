package Batch5.ita.com;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by batch3 on 7/25/17.
 */

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_login);

        Log.v("WelcomeActivity","onCreate method");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("WelcomeActivity","onStart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("WelcomeActivity","onResume method");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.v("WelcomeActivity","onPause method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("WelcomeActivity","onStop method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("WelcomeActivity","onDestroy method");
    }
}
