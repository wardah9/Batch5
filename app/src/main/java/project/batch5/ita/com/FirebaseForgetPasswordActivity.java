package project.batch5.ita.com;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import batch5.ita.com.R;

public class FirebaseForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText forget_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_forget_password);

        forget_email = (EditText) findViewById(R.id.forget_email);
    }

    public void OnUserForgetPassword(View view) {
        mAuth.sendPasswordResetEmail(forget_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FirebaseForgetPasswordActivity.this, "Password send to ur email", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(FirebaseForgetPasswordActivity.this, "Password not send you have to check email again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
