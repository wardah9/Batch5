package Batch5.ita.com;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DownloadImageActivity extends AppCompatActivity {

    private ImageView picasso_imageView;
    String URL = "http://www.twitrcovers.com/wp-content/uploads/2013/01/Cute-Cat-Paw-l.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        picasso_imageView = (ImageView) findViewById(R.id.picasso_image);
    }

    public void OnDownloadClicked(View view) {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        Picasso.with(this).load(URL).into(picasso_imageView, new Callback() {
            @Override
            public void onSuccess() {

                dialog.dismiss();
            }

            @Override
            public void onError() {

            }
        });

    }
}
