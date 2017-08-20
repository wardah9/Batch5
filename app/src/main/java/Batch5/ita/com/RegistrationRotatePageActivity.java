package Batch5.ita.com;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegistrationRotatePageActivity extends AppCompatActivity {

    private EditText name_rotate;
    private EditText password_rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_rotate_page);

        name_rotate = (EditText) findViewById(R.id.name_rotate);
        password_rotate = (EditText) findViewById(R.id.password_rotate);

        if (savedInstanceState != null){
            onRestoreInstanceState(savedInstanceState);

            savedInstanceState.getString("username");
            savedInstanceState.getInt("number");
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString("username",name_rotate.getText().toString());
        outState.putInt("number", Integer.parseInt(password_rotate.getText().toString()));
    }
}
