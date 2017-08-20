package batch5.ita.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        Button alert_button = (Button) findViewById(R.id.alert_button);
        Button progress_button = (Button) findViewById(R.id.progress_button);
        Button custom_button = (Button) findViewById(R.id.custom_button);


        alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayDialog();
            }
        });

        progress_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DisplayProgressDialog();

            }
        });


        custom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(AlertActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                Button Proceed_button = (Button) dialog.findViewById(R.id.proceed);
                Proceed_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


    }

    private void DisplayProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(AlertActivity.this);
        dialog.setMessage("Loading ...");
        dialog.show();

        Timer RunSplash = new Timer();

        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        };

        RunSplash.schedule(ShowSplash, 5000);
    }

    private void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertActivity.this);
        builder.setTitle("Hello world!");
        builder.setMessage("Loading..");
        builder.setIcon(R.drawable.air_transport);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertActivity.this, "OK clicked ..", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertActivity.this, "Cancel clicked ..", Toast.LENGTH_SHORT).show();
            }
        });


        builder.show();
    }


}
