package batch5.ita.com;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SharedPrefrenceRetrieveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefrence_retrieve);


        SharedPreferences MyPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String namePref = MyPrefs.getString("username","userName does not exist !");
        int agePref = MyPrefs.getInt("age",22);
        boolean presentPref = MyPrefs.getBoolean("isPresent",true);

        Toast.makeText(SharedPrefrenceRetrieveActivity.this, "my name is : " + namePref+" your age is "+agePref+" with status present "+presentPref, Toast.LENGTH_SHORT).show();

    }
}
