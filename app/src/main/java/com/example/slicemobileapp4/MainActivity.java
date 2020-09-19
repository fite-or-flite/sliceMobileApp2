package com.example.slicemobileapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slicemobileapp4.Prevalent.Prevalent;
import com.example.slicemobileapp4.models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button mainJoinButton, mainLoginButton;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();
        loadingBar = new ProgressDialog(this);

        //check if rememberMe previously clicked
//        Paper.init(this);
//
//        String userEmail = "";
//        String userPassword = "";
//          //trying to make sure not null
//        if (Paper.book().read(Prevalent.userEmailKey) != null) {
//            userEmail = Paper.book().read(Prevalent.userEmailKey);
//        }
//        if (Paper.book().read(Prevalent.userPasswordKey) != null) {
//            userPassword = Paper.book().read(Prevalent.userPasswordKey);
//        }
//        if (!userEmail.equals("") && !userPassword.equals("")) {
//            if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
//
//                allowAccess(userEmail, userPassword);
//
//                if (TextUtils.isEmpty(userEmail)) {
//                    Toast.makeText(this, "Please enter your email.", Toast.LENGTH_SHORT).show();
//                } else if (TextUtils.isEmpty(userPassword)) {
//                    Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show();
//                } else {
//                    loadingBar.setTitle("Welcome back.");
//                    loadingBar.setCanceledOnTouchOutside(false);
//                    loadingBar.show();
//                }
//            }
//        }
    }

    private void allowAccess(final String userEmailKey, final String userPasswordKey) {

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(userEmailKey).exists()) {
                    UserModel userData = dataSnapshot.child("Users").child(userEmailKey).getValue(UserModel.class);
                    if (userData.getEmail().equals(userEmailKey)) {
                        if (userData.getPassword().equals(userPasswordKey)) {
                            Toast.makeText(MainActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User email does not exist. Please register.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setupButtons() {

        mainJoinButton = findViewById(R.id.main_join_button);
        mainLoginButton = findViewById(R.id.main_login_button);

        mainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mainJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}