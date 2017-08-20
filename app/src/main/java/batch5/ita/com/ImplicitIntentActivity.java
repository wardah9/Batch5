package batch5.ita.com;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImplicitIntentActivity extends AppCompatActivity {

    private EditText editText_Url;
    private EditText editText_phoneNo;
    private EditText editText_keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);


        editText_keyword = (EditText) findViewById(R.id.edt_keyword);
        EditText editText_location = (EditText) findViewById(R.id.edt_location);
        editText_phoneNo = (EditText) findViewById(R.id.edt_phoneNo);
        editText_Url = (EditText) findViewById(R.id.edt_url);

        Button button_call = (Button) findViewById(R.id.call_btn);
        Button button_map = (Button) findViewById(R.id.map_btn);
        Button button_lounch = (Button) findViewById(R.id.launch_btn);
        Button button_search = (Button) findViewById(R.id.search_btn);
        Button button_viewContent = (Button) findViewById(R.id.viewContact_btn);

    }

    public void onLaunchClicked(View view) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText_Url.getText().toString()));
        startActivity(browserIntent);
    }

    public void onCallClicked(View view) {

        Intent callIntent = new Intent();
        // ACTION_CALL / ACTION_DIAL

        callIntent.setAction(Intent.ACTION_CALL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);

        } else {
            try {
                callIntent.setData(Uri.parse("tel:" + Integer.parseInt(editText_phoneNo.getText().toString())));
                startActivity(callIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onSearchClicked(View view) {

        Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        searchIntent.putExtra(SearchManager.QUERY, editText_keyword.getText().toString());
        startActivity(searchIntent);
    }

    public void onMapClicked(View view) {
    }

    public void onViewContentClicked(View view) {

        Intent ContactsIntent = new Intent();
        ContactsIntent.setAction(Intent.ACTION_VIEW);
        ContactsIntent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivity(ContactsIntent);
    }

    public void onSendSMSClicked(View view) {

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("sms:98768676"));
        smsIntent.putExtra("sms_body", "Test... ");
        startActivity(smsIntent);


    }

    public void onSendEmailClicked(View view) {

        Intent emailIntent = new Intent();
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"wardah9658@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "email body");
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "choose email client !"));

    }
}
