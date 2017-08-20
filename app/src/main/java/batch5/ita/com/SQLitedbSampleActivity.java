package batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SQLitedbSampleActivity extends AppCompatActivity {

    private EditText name;
    private EditText phone;

    DatabaseOpenHelperHandler db;
    private ListView dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlitedb_sample);
        name = (EditText) findViewById(R.id.name_db);
        phone = (EditText) findViewById(R.id.phone_dp);

        dbList = (ListView) findViewById(R.id.list_db);

        db = new DatabaseOpenHelperHandler(this);
    }

    public void OnInsertDataClicked(View view) {

        db.addContact(name.getText().toString(), phone.getText().toString());
    }

    public void OnViewDataClicked(View view) {

        ArrayList<SQLiteModule> arrayList = db.getData();
        //SQLiteModule module = new SQLiteModule();

        for (int i = 0; i < arrayList.size(); i++) {
            // String rowData = arrayList.get(i);
            Log.v("data", "data: "
                    + arrayList.get(i).getDbId() + " "
                    + arrayList.get(i).getDbName() + " "
                    + arrayList.get(i).getDbPhone());

//            Toast.makeText(this,
//                    arrayList.get(i).getDbId() + "\n"
//                            + arrayList.get(i).getDbName() + "\n"
//                            + arrayList.get(i).getDbPhone() + "\n", Toast.LENGTH_SHORT).show();

        }

       dbList.setAdapter(new CustomDBadapter(arrayList));

    }

    private class CustomDBadapter extends BaseAdapter {

        ArrayList<SQLiteModule> arrayList;

        public CustomDBadapter(ArrayList<SQLiteModule> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
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
            View view = inflater.inflate(R.layout.db_item_list, null);

            TextView contact_id = (TextView) view.findViewById(R.id.contact_id);
            TextView contact_name = (TextView) view.findViewById(R.id.contact_name);
            TextView contact_phone = (TextView) view.findViewById(R.id.contact_phone);

            SQLiteModule module = arrayList.get(position);

            contact_id.setText(module.getDbId());
            contact_name.setText(module.getDbName());
            contact_phone.setText(module.getDbPhone());

            return view;
        }
    }
}