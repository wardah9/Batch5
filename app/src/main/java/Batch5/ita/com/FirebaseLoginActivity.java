package Batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText password;
    private EditText email;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText update_Password;
    private EditText confirm_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);

        mAuth = FirebaseAuth.getInstance();
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);


        update_Password = (EditText) findViewById(R.id.update_Password);
        confirm_Password = (EditText) findViewById(R.id.confirm_Password);

    }

    public void OnAddUserFirebase(View view) {

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("firebaseLogin", "createUserWithEmail:onComplete:" + task.isSuccessful());


                        if (!task.isSuccessful()) {
                            Toast.makeText(FirebaseLoginActivity.this, "register failed", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(FirebaseLoginActivity.this, "register successful", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void OnLoginUserFirebase(View view) {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("firebaseLogin", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("firebaseLogin", "signInWithEmail:failed", task.getException());
                            Toast.makeText(FirebaseLoginActivity.this, "Login failed",
                                    Toast.LENGTH_SHORT).show();
                        } else

                            Toast.makeText(FirebaseLoginActivity.this, "Login success ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FirebaseLoginActivity.this,FirebaseDatabaseActivity.class));

                    }
                });
    }

    public void OnUpdatePasswordFriebase(View view) {
        if (update_Password.getText().length() > 0 && confirm_Password.getText().length() > 0 && update_Password.getText().toString().equals(confirm_Password.getText().toString())) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {

                user.updatePassword(update_Password.getText().toString());
                Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "user is null", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "check password again", Toast.LENGTH_SHORT).show();
    }

    public void OnForgetPasswordClicked(View view) {

        mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FirebaseLoginActivity.this, "Password send to ur email", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(FirebaseLoginActivity.this, "Password not send you have to check email again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OnUSerInfoFriebase(View view) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String email = user.getEmail();
            String uid = user.getUid();


            TextView userInfo = (TextView) findViewById(R.id.userInfo);

            userInfo.setText("userEmail is : " + email + " \n and UID is : " + uid);
        }

    }

    public void OnUSerStatusFriebase(View view) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(FirebaseLoginActivity.this, "signed_in", Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(FirebaseLoginActivity.this, "signed_out", Toast.LENGTH_SHORT).show();
    }

    public void OnUserSign_outFriebase(View view) {

        mAuth.signOut();

    }
}
