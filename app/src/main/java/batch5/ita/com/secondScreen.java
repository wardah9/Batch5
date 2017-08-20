package batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class secondScreen extends AppCompatActivity {

    String androidNames[] = {
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow",
            "Nougat",
            "Oreo"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondscreen);


        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("studebnName");
        int number = bundle.getInt("serialNum");
        boolean isPresent = bundle.getBoolean("isPresent");

        Log.v("secondScreen", "student Name is " + name + " student serial number is " + number + " student is present " + isPresent);


        ListView listView = (ListView) findViewById(R.id.week4_listView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, androidNames);
        listView.setAdapter(adapter);


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.secondScreen_id);


        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view3 = inflater.inflate(R.layout.week4_header, null);
        relativeLayout.addView(view3);


        View view4 = inflater.inflate(R.layout.week4_footer, null);
        relativeLayout.addView(view4);
    }
}
