package project.batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import batch5.ita.com.R;
import project.batch5.ita.com.model.PUsers;

public class FirebaseUSerProfileActivity extends AppCompatActivity {

    private TextView userFName;
    private TextView userShowFPhone;
    private TextView userShowFGender;
    private TextView userShowFEmail;

    private DatabaseReference myRef;
    private FirebaseDatabase database;
  //  ArrayList<PUsers> usersArrayList;

    PUsers pUsers;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_user_profile);

        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");

        //Toast.makeText(this, "id is "+id, Toast.LENGTH_SHORT).show();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Project_users");

        userFName = (TextView) findViewById(R.id.user_f_name);
        userShowFPhone = (TextView) findViewById(R.id.user_show_f_phone);
        userShowFGender = (TextView) findViewById(R.id.user_show_f_gender);
        userShowFEmail = (TextView) findViewById(R.id.user_show_f_email);


        //good example to avoid late response..

        myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                    Log.v("firbase", "key is : " + postSnapShot.getKey()
                            + " value  is " + postSnapShot.getValue());

                    PUsers fireBaseProducts = dataSnapshot.getValue(PUsers.class);
                    //just to know the retriveing value
                    assert fireBaseProducts != null;

                        userFName.setText(fireBaseProducts.getPuserName());
                        userShowFPhone.setText("Phone no : "+fireBaseProducts.getPuserPhone());
                        userShowFGender.setText("Gender : "+fireBaseProducts.getPuserGender());
                        userShowFEmail.setText("Email : "+fireBaseProducts.getPuserEmail());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read data.", error.toException());
            }
        });


        // long way ..

       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                    Log.v("firbase", "key is : " + postSnapShot.getKey()
                            + " value  is " + postSnapShot.getValue());

                    PUsers fireBaseProducts = postSnapShot.getValue(PUsers.class);
                    //just to know the retriveing value
                    assert fireBaseProducts != null;

                    if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(fireBaseProducts.getpFirebaseUserId())) {
                        userFName.setText(fireBaseProducts.getPuserName());
                        userShowFPhone.setText("Phone no : "+fireBaseProducts.getPuserPhone());
                        userShowFGender.setText("Gender : "+fireBaseProducts.getPuserGender());
                        userShowFEmail.setText("Email : "+fireBaseProducts.getPuserEmail());

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });*/
    }



    public void OnOKClickedd(View view) {
        startActivity(new Intent(FirebaseUSerProfileActivity.this, FirebaseProductsListActivity.class));
    }
}
