package Batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class AutoCompTextViewActivity extends AppCompatActivity {

    String names[]={
            "wardah",
            "maysoora",
            "noora",
            "sara",
            "android SDK",
            "android books",
            "android firefox",
            "welcome to android",
            "wardah",
            "maysoora",
            "noora",
            "sara",
            "wardah",
            "maysoora",
            "noora",
            "sara",
            "android SDK",
            "android books",
            "android firefox",
            "welcome to android"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_activity);

//        AutoCompleteTextView autoCompTextView = (AutoCompleteTextView) findViewById(R.id.atcv);


//        autoCompTextView.setAdapter(adapter);
//        autoCompTextView.setThreshold(4);


//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        spinner.setAdapter(adapter);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(AutoCompTextViewActivity.this, "position is "+position, Toast.LENGTH_SHORT).show();
                Toast.makeText(AutoCompTextViewActivity.this, "position is "+position +" name is "+names[position], Toast.LENGTH_SHORT).show();

            }
        });


        GridView gridView = (GridView) findViewById(R.id.gv);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(AutoCompTextViewActivity.this, "position is "+position, Toast.LENGTH_SHORT).show();
                 Toast.makeText(AutoCompTextViewActivity.this, "position is "+position +" name is "+names[position], Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(AutoCompTextViewActivity.this, "position is "+position, Toast.LENGTH_SHORT).show();
                 Toast.makeText(AutoCompTextViewActivity.this, "position is "+position +" name is "+names[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
