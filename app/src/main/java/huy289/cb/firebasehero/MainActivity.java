package huy289.cb.firebasehero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView tvUserName, tvProviderID, tvEmail, tvPhone, tvTenantID, tvUID;
    ImageView ivAvt;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        getViews();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(final FirebaseUser currentUser) {
        if(currentUser != null) {
            Toast.makeText(this, "Welcome to Hero App my friend", Toast.LENGTH_SHORT).show();
            tvUserName.setText("DisplayName: " + currentUser.getDisplayName());
            tvProviderID.setText("ProviderId: " + currentUser.getProviderId());
            tvEmail.setText("Email: " + currentUser.getEmail());
            tvPhone.setText("Phone: " + currentUser.getPhoneNumber());
//            tvTenantID.setText("Uri: " + currentUser.getPhotoUrl().toString());
            tvUID.setText("UID: " + currentUser.getUid());
//            ivAvt.setImageURI(currentUser.getPhotoUrl());
//            Logout button onclick -> logout user -> StartActivity
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    Toast.makeText(MainActivity.this, "Logged Out!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "No user", Toast.LENGTH_SHORT).show();
        }


    }

    public void getViews() {
        tvUserName = findViewById(R.id.tvUsername);
        tvProviderID = findViewById(R.id.tvProviderID);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvTenantID = findViewById(R.id.tvTenantID);
        tvUID = findViewById(R.id.tvUID);
        ivAvt = findViewById(R.id.ivAvt);
        logout = findViewById(R.id.logout);
    }
}