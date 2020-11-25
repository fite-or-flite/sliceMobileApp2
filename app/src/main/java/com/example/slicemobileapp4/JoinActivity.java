package com.example.slicemobileapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class JoinActivity extends AppCompatActivity {

    EditText joinEmail, joinPassword, joinFirstName, joinLastName, joinPhone, joinRetypePassword;
    Button joinButton;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        setUpButtons();
    }

    private void setUpButtons() {
        joinEmail = findViewById(R.id.join_email_text);
        joinPassword = findViewById(R.id.join_password_text);
        joinFirstName = findViewById(R.id.join_first_name_text);
        joinLastName = findViewById(R.id.join_last_name_text);
        joinPhone = findViewById(R.id.join_phone_text);
        joinButton = findViewById(R.id.join_button);
        joinRetypePassword = findViewById(R.id.join_retype_password_text);
        
        loadingBar = new ProgressDialog(this);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        String email = joinEmail.getText().toString();
        String password = joinPassword.getText().toString();
        String phone = joinPhone.getText().toString();
        String firstName = joinFirstName.getText().toString();
        String lastName = joinLastName.getText().toString();
        String retypePassword = joinRetypePassword.getText().toString();

        //check passwords match
        if (!(password.equals(retypePassword))) {
            Toast.makeText(JoinActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        }
        //check for empty fields
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(JoinActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(JoinActivity.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(JoinActivity.this, "Please enter your first name.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(JoinActivity.this, "Please enter your last name.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(JoinActivity.this, "Please enter your phone number.", Toast.LENGTH_SHORT).show();
        } else {
            //store data in fb
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validateEmail(email, firstName, lastName, phone, password);

        }

    }

    private void validateEmail(final String email, final String firstName, final String lastName, final String phone, final String password) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("Email", email);
                    userDataMap.put("FirstName", firstName);
                    userDataMap.put("LastName", lastName);
                    userDataMap.put("Phone", phone);
                    userDataMap.put("Password", password);

                    databaseReference.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(JoinActivity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(JoinActivity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(JoinActivity.this, "This phone number is already registered.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}