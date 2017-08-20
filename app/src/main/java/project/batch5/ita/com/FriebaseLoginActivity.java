package project.batch5.ita.com;

import android.content.Intent;
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

import Batch5.ita.com.R;

public class FriebaseLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText emailEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friebase_login);

        mAuth = FirebaseAuth.getInstance();
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);

    }

    public void OnLoginUser(View view) {
        mAuth.signInWithEmailAndPassword(emailEdit.getText().toString(), passwordEdit.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("firebaseLogin", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("firebaseLogin", "signInWithEmail:failed", task.getException());
                            Toast.makeText(FriebaseLoginActivity.this, "Login failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FriebaseLoginActivity.this, "Login success ", Toast.LENGTH_SHORT).show();
                            getUID();

                        }
                    }
                });
    }
    private void getUID() {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userID = user.getUid();
            startActivity(new Intent(FriebaseLoginActivity.this, FirebaseUSerProfileActivity.class).putExtra("user_id",userID));
        } else {
            // User is signed out
            Log.d("firebase", "onAuthStateChanged:signed_out");
        }
    }
    public void OnRegisterUSer(View view) {

        startActivity(new Intent(FriebaseLoginActivity.this , FirebaseUSerRegisterActivity.class));
    }

    public void OnForgetPassword(View view) {
        startActivity(new Intent(FriebaseLoginActivity.this , FirebaseForgetPasswordActivity.class));
    }
}
