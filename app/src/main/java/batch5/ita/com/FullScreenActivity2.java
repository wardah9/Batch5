package batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

public class FullScreenActivity2 extends AppCompatActivity {

    private Locale myLocale;
    private ListView language_list;

    String[] languages = {
            "Arabic",
            "English"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_full_screen2);

        language_list = (ListView) findViewById(R.id.language_list);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages);
        language_list.setAdapter(adapter);

        language_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (i == 0) {
                    changeLang("ar");
                    startActivity(new Intent(FullScreenActivity2.this, HelloScreenActivity.class));
                } else {
                    changeLang("en");
                    startActivity(new Intent(FullScreenActivity2.this, HelloScreenActivity.class));
                }
            }
        });
    }

    public void changeLang(String lang) {
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration configuration = new android.content.res.Configuration();
        configuration.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }
}
