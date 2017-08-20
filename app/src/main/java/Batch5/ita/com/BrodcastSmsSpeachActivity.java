package Batch5.ita.com;

import android.support.v7.app.AppCompatActivity;

public class BrodcastSmsSpeachActivity extends AppCompatActivity {
//    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;
//    private TextToSpeech tts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_brodcast_sms_speach);
//
//        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//
//                tts.setLanguage(Locale.ENGLISH);
//            }
//        });
//    }
//
//    public void getPermissionToReadSMS() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
//            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)) {
//                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
//            }
//            requestPermissions(new String[]{Manifest.permission.READ_SMS},
//                    READ_SMS_PERMISSIONS_REQUEST);
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
//        // Make sure it's our original READ_CONTACTS request
//        if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
//                refreshSmsInbox();
//            } else {
//                Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }
//
//
//    public void refreshSmsInbox() {
//        ContentResolver contentResolver = getContentResolver();
//        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
//        int indexBody = smsInboxCursor.getColumnIndex("body");
//        int indexAddress = smsInboxCursor.getColumnIndex("address");
//        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
//        // arrayAdapter.clear();
//        do {
//            String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +
//                    "\n" + smsInboxCursor.getString(indexBody) + "\n";
//            //  arrayAdapter.add(str);
//            tts.speak(str,TextToSpeech.QUEUE_FLUSH,null);
//        } while (smsInboxCursor.moveToNext());
//
//
//    }
}
