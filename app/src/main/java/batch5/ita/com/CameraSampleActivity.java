package batch5.ita.com;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CameraSampleActivity extends AppCompatActivity {

    private ImageView img;
    private Context context;
    private int SELECT_FROM_GALLARY = 2;
    private int SELECT_FROM_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_sample);


        img = (ImageView) findViewById(R.id.image);


    }

    public void onCameraLaunched(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            Intent cameraIntent = new Intent();
            cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, SELECT_FROM_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_FROM_CAMERA && resultCode == RESULT_OK) {

            Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(cameraBitmap);
        } else if (requestCode == SELECT_FROM_GALLARY && resultCode == RESULT_OK) {

            try {
                InputStream input = getContentResolver().openInputStream(data == null ? null : data.getData());
                final Bitmap bitmap = BitmapFactory.decodeStream(input, null, new BitmapFactory.Options());

                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                img.setImageDrawable(drawable);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Error occurred ! ,  " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    public void onGalleryLaunched(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FROM_GALLARY);

    }
}
