package Batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sample);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_settings:
                Toast.makeText(MenuSampleActivity.this,
                        "Settings is Selected", Toast.LENGTH_LONG)
                        .show();
                return true;

            case R.id.menu_save:
                Toast.makeText(MenuSampleActivity.this,
                        "Save is Selected", Toast.LENGTH_LONG)
                        .show();
                return true;

            case R.id.menu_search:
                Toast.makeText(MenuSampleActivity.this,
                        "Search is Selected", Toast.LENGTH_LONG)
                        .show();
                return true;

            case R.id.menu_delete:
                Toast.makeText(MenuSampleActivity.this,
                        "Delete is Selected", Toast.LENGTH_LONG)
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
