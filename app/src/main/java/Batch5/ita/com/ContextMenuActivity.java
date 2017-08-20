package Batch5.ita.com;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ContextMenuActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        Button button_context_menu = (Button) findViewById(R.id.context_menu1);
        LinearLayout layout_context = (LinearLayout) findViewById(R.id.layout_context);
        registerForContextMenu(layout_context);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(android.R.drawable.ic_menu_gallery);
        menu.setHeaderTitle("Select the action ..");
        menu.add(0, v.getId(), 0, "Call");
        menu.add(0, v.getId(), 0, "SMS");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Call") {
            Intent callIntent = new Intent();

            callIntent.setAction(Intent.ACTION_CALL);

            if (ActivityCompat.checkSelfPermission(ContextMenuActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ContextMenuActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 0);
            } else {
                try {
                    callIntent.setData(Uri.parse("tel:998746553"));
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContextMenuActivity.this, "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (item.getTitle() == "SMS") {

            //send sms in background
//            SmsManager sms = SmsManager.getDefault();
//            sms.sendTextMessage("5556", null, "Hello my friends twice!", null, null);

            sendSMS();
        }
        return super.onContextItemSelected(item);
    }

    public void sendSMS() {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("sms:98768676"));
        smsIntent.putExtra("sms_body", "Test... ");
        startActivity(smsIntent);


    }

}
