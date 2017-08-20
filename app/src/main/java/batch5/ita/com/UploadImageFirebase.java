package batch5.ita.com;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UploadImageFirebase extends AppCompatActivity {

    private ImageView imageview_firebase;
    private int SELECT_FROM_GALLARY = 2;
    private int SELECT_FROM_CAMERA = 1;

    private FirebaseStorage storage;
    private StorageReference mStorageRef;


    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image_firebase);


        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://batch5-9e3b4.appspot.com");


        imageview_firebase = (ImageView) findViewById(R.id.imageview_firebase);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        filePath = data.getData();

        if (requestCode == SELECT_FROM_CAMERA && resultCode == RESULT_OK) {

            Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
            imageview_firebase.setImageBitmap(cameraBitmap);
        } else if (requestCode == SELECT_FROM_GALLARY && resultCode == RESULT_OK) {

            try {
                InputStream input = getContentResolver().openInputStream(data == null ? null : data.getData());
                final Bitmap bitmap = BitmapFactory.decodeStream(input, null, new BitmapFactory.Options());

                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                imageview_firebase.setImageDrawable(drawable);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Error occurred ! ,  " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    public void OnPickImageClicked(View view) {

        final Dialog dialog = new Dialog(UploadImageFirebase.this);
        dialog.setContentView(R.layout.custom_dialog_ex2);
        Button camera_button = (Button) dialog.findViewById(R.id.cc);
        Button gallery_button = (Button) dialog.findViewById(R.id.gg);

        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(UploadImageFirebase.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UploadImageFirebase.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    Intent cameraIntent = new Intent();
                    cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, SELECT_FROM_CAMERA);
                }

                dialog.dismiss();
            }
        });
        gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_FROM_GALLARY);

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void OnUploadImageClicked(View view) {

        // StorageReference riversRef = mStorageRef.child("");
        StorageReference riversRef = mStorageRef.child("cat.jpg");

        riversRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(UploadImageFirebase.this, "upload successful", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads

                        Log.d("firebase", exception.toString());
                        Toast.makeText(UploadImageFirebase.this, "upload failed " + exception, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void OnDownloadImageClicked(View view) throws IOException {

        StorageReference riversRef = mStorageRef.child("android.jpg");

        final File localFile = File.createTempFile("images", "jpg");
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        imageview_firebase.setImageBitmap(bitmap);
                        Toast.makeText(UploadImageFirebase.this, "image downloaded", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
                Toast.makeText(UploadImageFirebase.this, "image download fail", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
