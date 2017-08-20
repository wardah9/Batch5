package batch5.ita.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SharedPrefrenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefrence);

        SharedPreferences MyPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = MyPrefs.edit();
        editor.putString("username","wardah");
        editor.putInt("age", 23);
        editor.putBoolean("isPresent",true);
        editor.commit();

    }

    public void OnClickViewPref(View view) {
        startActivity(new Intent(SharedPrefrenceActivity.this,SharedPrefrenceRetrieveActivity.class));
    }
}
