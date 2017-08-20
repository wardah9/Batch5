package Batch5.ita.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {
    private EditText username, password;
    private TextView tv;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_practice);
        //giv value by id
        username = (EditText) findViewById(R.id.UserName);
        password = (EditText) findViewById(R.id.Password);

        //crate object onclick
        Button login_btn = (Button) findViewById(R.id.login_btn);

        //creat object for button2
        Button SignUp1 = (Button) findViewById(R.id.SignUp1);
        tv  = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //read data from Text view
        login_btn.getText().toString();
        //method on click
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.equal to show what user written in text view
                if(username.getText().toString().equals("ita") && password.getText().toString().equals("ita@12345")){

                    //to show massege
                    Toast.makeText(Batch5.ita.com.WelcomeScreen.this, "Successful Login ",1000).show();

                }
                else{
                    Toast.makeText(Batch5.ita.com.WelcomeScreen.this, " un Successful Login ",1000).show();
                    tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    //show massege in Text View
                    tv.setText("login is un Succsessfully");
                    ll = (LinearLayout) findViewById(R.id.login_practice);
                    ll.addView(tv);
                }

            }

        });
    }
}
