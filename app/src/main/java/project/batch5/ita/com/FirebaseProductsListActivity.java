package project.batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Batch5.ita.com.R;
import project.batch5.ita.com.model.FireBaseProducts;

public class FirebaseProductsListActivity extends AppCompatActivity {

    private ListView FierbaseProductsList;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    ArrayList<FireBaseProducts> FbProductsList;

    private FirebaseStorage storage;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_products_list);

        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://batch5-9e3b4.appspot.com");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Project_products");

        FierbaseProductsList = (ListView) findViewById(R.id.Fierbase_ProductsList);

        getimages();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FbProductsList = new ArrayList<FireBaseProducts>();
                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                    Log.v("firbase", "key is : " + postSnapShot.getKey()
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
                FierbaseProductsList.setAdapter(new CustomAdapter(FbProductsList));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });


    }

    private void getimages() {

    }

    class CustomAdapter extends BaseAdapter {

        ArrayList<FireBaseProducts> fbProductsList;

        public CustomAdapter(ArrayList<FireBaseProducts> fbProductsList) {
            this.fbProductsList = fbProductsList;
        }

        @Override
        public int getCount() {
            return fbProductsList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view2 = inflater.inflate(R.layout.student_interface, null);

            TextView name = (TextView) view2.findViewById(R.id.student_name1);
            TextView price = (TextView) view2.findViewById(R.id.student_id1);
            TextView trakingno = (TextView) view2.findViewById(R.id.student_GPA1);
            ImageView pImage = (ImageView) view2.findViewById(R.id.student_profile_image);

            FireBaseProducts FbProducts = fbProductsList.get(i);

            name.setText(FbProducts.getProductFbNAme());
            price.setText(FbProducts.getProductFbPrice());
            trakingno.setText(FbProducts.getProductFbRollno());

            Picasso.with(FirebaseProductsListActivity.this).load(FbProducts.getProductFbImg()).into(pImage);

            return view2;
        }
    }
}
