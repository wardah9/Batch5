package project.batch5.ita.com;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import batch5.ita.com.R;
import project.batch5.ita.com.model.FireBaseProducts;

public class visibilitySample extends AppCompatActivity {

    private EditText nameFb;
    private TextView nameFbText;
    private EditText genderFb;
    private TextView genderFbText;
    private EditText traking_no_fb;
    private TextView rollnoFbText;
    private ImageView imageview_firebasesample;



    private FireBaseProducts product;

    ArrayList<FireBaseProducts> FbProductsList;


    private int SELECT_FROM_GALLARY = 2;
    private int SELECT_FROM_CAMERA = 1;
    private Uri filePath;

    public String fb_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visibility_sample);

        imageview_firebasesample = (ImageView) findViewById(R.id.imageview_firebasesample);
        nameFb = (EditText) findViewById(R.id.name_fb);
        nameFbText = (TextView) findViewById(R.id.name_fb_text);
        genderFb = (EditText) findViewById(R.id.gender_fb);
        genderFbText = (TextView) findViewById(R.id.gender_fb_text);
        traking_no_fb = (EditText) findViewById(R.id.rollno_fb);
        rollnoFbText = (TextView) findViewById(R.id.rollno_fb_text);

        viewItemFromFirebase();


        imageview_firebasesample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(visibilitySample.this);
                dialog.setContentView(R.layout.custom_dialog_ex2);
                Button camera_button = (Button) dialog.findViewById(R.id.cc);
                Button gallery_button = (Button) dialog.findViewById(R.id.gg);

                camera_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ContextCompat.checkSelfPermission(visibilitySample.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(visibilitySample.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
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
        });

    }

     int x=0;
    int y=0;
    private void viewItemFromFirebase() {

        DatabaseReference pmyRef;
        FirebaseDatabase pdatabase;

        pdatabase = FirebaseDatabase.getInstance();
        pmyRef = pdatabase.getReference("Project_products");


        pmyRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if(x==1){

                }else{
                    x=1;
                    Log.v("firbase", "i am here from biewitem "+x);

                    FbProductsList = new ArrayList<FireBaseProducts>();
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                        Log.v("firbase", "key is 0: " + postSnapShot.getKey()
                                + " value  is " + postSnapShot.getValue());

                        FireBaseProducts fireBaseProducts = postSnapShot.getValue(FireBaseProducts.class);

                        //just to know the retriveing value
                        assert fireBaseProducts != null;
                        fireBaseProducts.getProductFbaseId();
                        fireBaseProducts.getProductFbNAme();
                        fireBaseProducts.getProductFbPrice();
                        fireBaseProducts.getProductFbRollno();
                        fireBaseProducts.getProductFbImg();

                        FbProductsList.add(fireBaseProducts);


                    }
                    nameFb.setText(FbProductsList.get(0).getProductFbNAme());
                    fb_id = FbProductsList.get(0).getProductFbaseId();
                    genderFb.setText(FbProductsList.get(0).getProductFbPrice());
                    traking_no_fb.setText(FbProductsList.get(0).getProductFbRollno());

                    Picasso.with(visibilitySample.this).load(FbProductsList.get(0).getProductFbImg()).into(imageview_firebasesample);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        filePath = data.getData();

        if (requestCode == SELECT_FROM_CAMERA && resultCode == RESULT_OK) {

            Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
            imageview_firebasesample.setImageBitmap(cameraBitmap);
        } else if (requestCode == SELECT_FROM_GALLARY && resultCode == RESULT_OK) {

            try {
                InputStream input = getContentResolver().openInputStream(data == null ? null : data.getData());
                final Bitmap bitmap = BitmapFactory.decodeStream(input, null, new BitmapFactory.Options());

                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                imageview_firebasesample.setImageDrawable(drawable);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Error occurred ! ,  " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    public void OnUpdate1Clicked(View view) {


        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference mStorageRef = storage.getReferenceFromUrl("gs://batch5-9e3b4.appspot.com");
        final FirebaseDatabase pdatabase = FirebaseDatabase.getInstance();
        final DatabaseReference pmyRef = pdatabase.getReference("Project_products");
        pmyRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                if(y==1){

                }else {
                    y = 1;
                    Log.v("firbase", "i am here from btnupdate "+y);
                    FbProductsList = new ArrayList<FireBaseProducts>();
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                        Log.v("firbase", "key is 1: " + postSnapShot.getKey()
                                + " value  is " + postSnapShot.getValue());

                        FireBaseProducts fireBaseProducts = postSnapShot.getValue(FireBaseProducts.class);

                        FbProductsList.add(fireBaseProducts);


                    }

//                product = new FireBaseProducts();
//                product.setProductFbNAme(nameFb.getText().toString());
//                product.setProductFbPrice(genderFb.getText().toString());
//                product.setProductFbaseId(fb_id);
//
//                product.setProductFbImg();
//                product.setProductFbRollno(traking_no_fb.getText().toString());
//
//                fb_id = FbProductsList.get(0).getProductFbaseId();
//                pmyRef.child(fb_id).setValue(product);
//
//              fb_id = FbProductsList.get(0).getProductFbaseId();
                    int i = 0;

                    StorageReference riversRef = mStorageRef.child(i + ".jpg");
                    riversRef.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                                    product = new FireBaseProducts();
                                    product.setProductFbNAme(nameFb.getText().toString());
                                    product.setProductFbPrice(genderFb.getText().toString());
                                    product.setProductFbaseId(fb_id);
                                    assert downloadUrl != null;
                                    product.setProductFbImg(downloadUrl.toString());
                                    product.setProductFbRollno(traking_no_fb.getText().toString());

                                    pmyRef.child(fb_id).setValue(product);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads

                                    Log.d("firebase", exception.toString());

                                }
                            });
                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });


    }

    public void OnOkClicked(View view) {

        nameFb.setVisibility(View.GONE);
        genderFb.setVisibility(View.GONE);
        traking_no_fb.setVisibility(View.GONE);
        nameFbText.setVisibility(View.VISIBLE);
        genderFbText.setVisibility(View.VISIBLE);
        rollnoFbText.setVisibility(View.VISIBLE);

        DatabaseReference pmyRef;
        FirebaseDatabase pdatabase;

        pdatabase = FirebaseDatabase.getInstance();
        pmyRef = pdatabase.getReference("Project_products");

        pmyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FbProductsList = new ArrayList<FireBaseProducts>();
                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                    Log.v("firbase", "key is 2: " + postSnapShot.getKey()
                            + " value  is " + postSnapShot.getValue());

                    FireBaseProducts fireBaseProducts = postSnapShot.getValue(FireBaseProducts.class);
                    //just to know the retriveing value
                    assert fireBaseProducts != null;
                    fireBaseProducts.getProductFbNAme();
                    fireBaseProducts.getProductFbPrice();
                    fireBaseProducts.getProductFbRollno();
                    fireBaseProducts.getProductFbImg();

                    FbProductsList.add(fireBaseProducts);
                }

                nameFbText.setText(FbProductsList.get(0).getProductFbNAme());
                fb_id = FbProductsList.get(0).getProductFbaseId();
                genderFbText.setText(FbProductsList.get(0).getProductFbPrice());
                rollnoFbText.setText(FbProductsList.get(0).getProductFbRollno());

                Picasso.with(visibilitySample.this).load(FbProductsList.get(0).getProductFbImg()).into(imageview_firebasesample);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });

    }

    public void OnviewClicked(View view) {

        startActivity(new Intent(visibilitySample.this, FirebaseProductsListActivity.class));
    }
}
