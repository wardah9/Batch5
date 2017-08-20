package Batch5.ita.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomAdapterActivity extends AppCompatActivity {

    String namez[] = {
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

    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);

        listView2 = (ListView) findViewById(R.id.listView_CustomAdapter);
        listView2.setAdapter(new customAdapterList());

    }

    public class customAdapterList extends BaseAdapter {

        @Override
        public int getCount() {
            return namez.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View customview = inflater.inflate(R.layout.customlist,null);

            TextView textView_custom = (TextView) customview.findViewById(R.id.custom_text);
            textView_custom.setText(namez[position]);

            return customview;
        }
    }
}
