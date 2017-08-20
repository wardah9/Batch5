package batch5.ita.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class WelcomeScreenSharedActivity extends AppCompatActivity {

    private EditText password_edi;
    private EditText username;

    boolean check = false;

    private SharedPreferences UserPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen_shared);

        UserPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        username = (EditText) findViewById(R.id.username_editText);
        password_edi = (EditText) findViewById(R.id.password_editText);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.remeber_ch);

//        if (UserPrefs.getString("username","wawoo") != null && UserPrefs.getString("password","123") != null){
//            startActivity(new Intent(WelcomeScreenSharedActivity.this, HomeScreenSharedActivity.class));
//            finish();
//        }


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    check = true;
                else
                    check = false;
            }
        });


    }

    public void OnLoginClicked(View view) {

        if (check == true){
            SharedPreferences.Editor editor = UserPrefs.edit();
            editor.putString("username", username.getText().toString());
            editor.putString("password", password_edi.getText().toString());
            editor.commit();

            startActivity(new Intent(WelcomeScreenSharedActivity.this, TermsAndConditionsActivity.class));
        }else

            startActivity(new Intent(WelcomeScreenSharedActivity.this, TermsAndConditionsActivity.class));
    }
}
