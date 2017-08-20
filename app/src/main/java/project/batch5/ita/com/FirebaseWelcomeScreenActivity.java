package project.batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import batch5.ita.com.R;

public class FirebaseWelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firbase_welcome_screen);
    }

    public void OnAdminClicked(View view) {
        startActivity(new Intent(this , FirebaseAdminScreenActivity.class));
    }

    public void OnUsersClicked(View view) {
        startActivity(new Intent(this , FriebaseLoginActivity.class));
    }
}
