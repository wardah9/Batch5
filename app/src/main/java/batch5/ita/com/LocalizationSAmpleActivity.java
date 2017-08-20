package batch5.ita.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class LocalizationSAmpleActivity extends AppCompatActivity {

    private Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization_sample);
    }


    public void changeLang(String lang){
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration configuration = new  android.content.res.Configuration();
        configuration.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
    }
}
