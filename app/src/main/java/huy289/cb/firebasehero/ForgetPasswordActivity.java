package huy289.cb.firebasehero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText email;
    private Button reset;
    private TextView tvNoti;

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
                                    tvNoti.setTextColor(Color.parseColor("#27ae60"));
                                    tvNoti.setText("Please check your email to change password!!!");
                                } else {
                                    tvNoti.setTextColor(Color.parseColor("#e74c3c"));
                                    tvNoti.setText("Email invalid!!");
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
        tvNoti = findViewById(R.id.tvNoti);
    }
}