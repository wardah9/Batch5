package Batch5.ita.com;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by batch3 on 7/27/17.
 */

public class Add_Remove extends Activity {

    EditText a;
    EditText b;
    Button Addnum;
    int Resultadd;
    int Resultmult;
    TextView tv;
    LinearLayout screen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addremove);

        a = (EditText) findViewById(R.id.a_EditText);
        b = (EditText) findViewById(R.id.b_EditText);
        tv = new TextView(this);

        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        screen = (LinearLayout) findViewById(R.id.Resultis);

    }

    public void onAddClicked(View view) {

        String mynum1 = a.getText().toString();
        String mynum2 = b.getText().toString();

        Resultadd = Integer.parseInt(mynum1) + Integer.parseInt(mynum2);
        tv.setText("Result is " + Integer.toString(Resultadd));

        screen.addView(tv);

    }
}
