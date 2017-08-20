package project.batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Batch5.ita.com.R;
import project.batch5.ita.com.model.FireBaseProducts;

public class FirebaseAdminScreenActivity extends AppCompatActivity {

    private EditText Product_Name;
    private EditText Product_Price;
    private EditText Product_Trakingno;

    private DatabaseReference pmyRef;
    private FirebaseDatabase pdatabase;
    private FireBaseProducts product;

//    private int SELECT_FROM_GALLARY = 2;
//    private int SELECT_FROM_CAMERA = 1;
    private ImageView imageview_firebase;
//
//    Uri filePath;Ipho

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_admin_screen);

        Product_Name = (EditText) findViewById(R.id.Pro_name);
        Product_Price = (EditText) findViewById(R.id.Pro_Price);
        Product_Trakingno = (EditText) findViewById(R.id.Pro_rollno);

        imageview_firebase = (ImageView) findViewById(R.id.imageview_firebase);

        pdatabase = FirebaseDatabase.getInstance();
        pmyRef = pdatabase.getReference("Project_products");

    }

    public void OnAdminInsertDataClicked(View view) {

        String dkey = pmyRef.push().getKey();
        product = new FireBaseProducts();
        product.setProductFbNAme(Product_Name.getText().toString());
        product.setProductFbPrice(Product_Price.getText().toString());
        product.setProductFbaseId(dkey);
        product.setProductFbImg("http://shfcs.org/en/wp-content/uploads/2015/11/MedRes_Product-presentation-2.jpg");
        product.setProductFbRollno(Product_Trakingno.getText().toString());

        pmyRef.child(dkey).setValue(product);

        startActivity(new Intent(FirebaseAdminScreenActivity.this, FriebaseLoginActivity.class));
    }
}
