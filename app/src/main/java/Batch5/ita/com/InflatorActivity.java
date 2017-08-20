package Batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class InflatorActivity extends AppCompatActivity {

    Button add, remove;
    //TextView tv;
    LinearLayout rlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflator);

        LayoutInflater inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.header, null);

        rlayout = (LinearLayout) findViewById(R.id.rl);
    }

    public void onAdditionClicked(View view) {
        LayoutInflater inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.header, null);
        rlayout.addView(v);
    }

    public void onRemoveClicked(View V) {

        View myview = findViewById(R.id.ll);
        ViewGroup parent = (ViewGroup) myview.getParent();

        parent.removeView(myview);

        //rl.removeView();
    }
}
