package batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class TermsAndConditionsActivity extends AppCompatActivity {

    int success = 0;
    private Button button_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditons);

        CheckBox acceptCheckBox = (CheckBox) findViewById(R.id.accept_ch);
        button_ok = (Button) findViewById(R.id.ok_btn);
        Button button_cancell = (Button) findViewById(R.id.cancel_btn);

        button_ok.setEnabled(false);

        acceptCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    button_ok.setEnabled(true);
                }
                else
                    button_ok.setEnabled(false);
            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TermsAndConditionsActivity.this,HomeScreenSharedActivity.class));
            }
        });

        button_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
