package project.batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import batch5.ita.com.R;
import project.batch5.ita.com.model.PUsers;

public class FirebaseUSerRegisterActivity extends AppCompatActivity {

    private EditText puserName;
    private EditText puserPhone;
    private EditText puserEmail;
    private EditText puserpass;
    private Spinner userGender;

    String[] items = {
            "Male",
            "Female"
    };

    private DatabaseReference myRef;
    private FirebaseAuth uAuth;
    private FirebaseDatabase database;
    private PUsers PUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_user_register);

        uAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Project_users");

        puserName = (EditText) findViewById(R.id.user_name);
        puserPhone = (EditText) findViewById(R.id.user_phone);
        puserEmail = (EditText) findViewById(R.id.user_email);
        puserpass = (EditText) findViewById(R.id.user_pass);
        userGender = (Spinner) findViewById(R.id.user_gender);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        userGender.setAdapter(adapter);

    }

    public void OnFirebaseRegisterUser(View view) {

        uAuth.createUserWithEmailAndPassword(puserEmail.getText().toString(), puserpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("firebaseLogin", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            Toast.makeText(FirebaseUSerRegisterActivity.this, "register successful", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(FirebaseUSerRegisterActivity.this, "register failed ", Toast.LENGTH_SHORT).show();
                        getUID();
                    }
                });

    }

    private void getUID() {

        String pkey = FirebaseAuth.getInstance().getCurrentUser().getUid();

        PUsers = new PUsers(puserName.getText().toString(), puserPhone.getText().toString(), puserEmail.getText().toString(), puserpass.getText().toString(), userGender.getSelectedItem().toString(), pkey);

        myRef.child(pkey).setValue(PUsers);

        startActivity(new Intent(this, FriebaseLoginActivity.class));

    }


}
