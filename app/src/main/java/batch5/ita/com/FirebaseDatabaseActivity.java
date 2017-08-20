package batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseDatabaseActivity extends AppCompatActivity {

    private EditText stdId;
    private EditText stdName;
    private EditText stdEmail;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Student student;

    public ArrayList<Student> StudentList;
    private EditText delete_item_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);

        stdId = (EditText) findViewById(R.id.std_id);
        stdName = (EditText) findViewById(R.id.std_name);
        stdEmail = (EditText) findViewById(R.id.std_email);
        delete_item_position = (EditText) findViewById(R.id.delete_item_position);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("StudentsList");


    }

    public void OnclickWriteIntoDatabse(View view) {

        student = new Student();


        //   myRef.setValue("Hello, World!");     // set value only

        // myRef.child("1").setValue("hellow ?");    //set key with value

        //  myRef.child("1").setValue(student);       // set key with list of values in class

    }

    public void OnclickREADFromDatabse(View view) {
        // Read from the database
        StudentList = new ArrayList<Student>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                    Log.v("firbase", "key is : " + postSnapShot.getKey()
                            + " value  is " + postSnapShot.getValue());


                    Student student = postSnapShot.getValue(Student.class);

                    student.getStudentName();    //just to know the retriveing value
                    student.getStudentEmail();     //just to know the retriveing value


                    StudentList.add(student);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });
    }

    public void InsertFirbaseDatabase(View view) {

        String key = myRef.push().getKey();

        student = new Student(stdName.getText().toString(),stdId.getText().toString(),stdEmail.getText().toString(),key);

        myRef.child(key).setValue(student);

    }

    public void DeleteFirbaseDatabase(View view) {

        Student student = StudentList.get(Integer.parseInt(delete_item_position.getText().toString()));
        myRef.child(student.getStudentFirebaseId()).removeValue();

    }
}
