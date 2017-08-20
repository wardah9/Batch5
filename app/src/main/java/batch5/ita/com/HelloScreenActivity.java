package batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HelloScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);
    }

    public void OnNextPageClicked(View view) {
        startActivity(new Intent(HelloScreenActivity.this, RegistrationRotatePageActivity.class));
    }
}
