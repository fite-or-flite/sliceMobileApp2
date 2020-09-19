package com.example.slicemobileapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slicemobileapp4.Prevalent.Prevalent;
import com.example.slicemobileapp4.models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText loginEmailText, loginPasswordText;
    ProgressDialog loadingBar;
    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  Paper.init(this);

        setUpButtons();

    }

    private void setUpButtons() {
        loginButton = findViewById(R.id.login_button);
        loginEmailText = findViewById(R.id.login_email_text);
        loginPasswordText = findViewById(R.id.login_password_text);
        loadingBar = new ProgressDialog(this);
        rememberMe = findViewById(R.id.remember_me_checkbox);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
    }

    private void LoginUser() {
        String email = loginEmailText.getText().toString();
        String password = loginPasswordText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Logging in...");
            loadingBar.setMessage("Please wait.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(email, password);
        }

    }

    private void AllowAccessToAccount(final String email, final String password) {
//
//        if (rememberMe.isChecked()) {
//            Paper.book().write(Prevalent.userEmailKey, email);
//            Paper.book().write(Prevalent.userPasswordKey, password);
//        }
        // else destroy()?

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users");

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(email).exists()) {
                    UserModel userData = dataSnapshot.child(email).getValue(UserModel.class);
                    if (userData.getEmail().equals(email)) {
                        if (userData.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User email does not exist. Please register.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}