package batch5.ita.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rregister2FireBaseActivity extends AppCompatActivity {

    private EditText name_ed1;
    private EditText email_ed2;
    private EditText password_ed3;
    private EditText gender_ed4;
    private EditText phone_ed5;

    private FirebaseAuth uAuth;

    private DatabaseReference MyUsers;
    private FirebaseDatabase Users_database;
    private Users users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rregister2_fire_base);

        uAuth = FirebaseAuth.getInstance();
        Users_database = FirebaseDatabase.getInstance();

        name_ed1 = (EditText) findViewById(R.id.ed1);
        email_ed2 = (EditText) findViewById(R.id.ed2);
        password_ed3 = (EditText) findViewById(R.id.ed3);
        gender_ed4 = (EditText) findViewById(R.id.ed4);
        phone_ed5 = (EditText) findViewById(R.id.ed5);

    }

    public void OnSignupAth2Firebase(View view) {

        uAuth.createUserWithEmailAndPassword(email_ed2.getText().toString(), password_ed3.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("firebaseLogin", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            Toast.makeText(Rregister2FireBaseActivity.this, "register successful", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(Rregister2FireBaseActivity.this, "register failed ", Toast.LENGTH_SHORT).show();
                        getUID();
                    }
                });
    }

    private void getUID() {

        FirebaseUser user = uAuth.getCurrentUser();
        if (user != null) {
            String userID = user.getUid();

            MyUsers = Users_database.getReference("PUsers");
            users = new Users(name_ed1.getText().toString(), gender_ed4.getText().toString(), phone_ed5.getText().toString());
            MyUsers.child(userID).setValue(users);

        } else {
            // User is signed out
            Log.d("firebase", "onAuthStateChanged:signed_out");
        }
    }
}
