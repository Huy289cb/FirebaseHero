package huy289.cb.firebasehero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText email;
    private Button reset;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        mAuth = FirebaseAuth.getInstance();
        getViews();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString();
                mAuth.sendPasswordResetEmail(sEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ForgetPasswordActivity.this, "Please check your email to change your password!!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ForgetPasswordActivity.this, "Email invalid!!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }

    private void getViews() {
        email = findViewById(R.id.email);
        reset = findViewById(R.id.reset);
    }
}