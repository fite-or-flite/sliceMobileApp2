package com.example.slicemobileapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        Paper.init(this);

        String userPhone = "";
        String userPassword = "";
        userPhone = Paper.book().read(Prevalent.userPhoneKey);
        userPassword = Paper.book().read(Prevalent.userPasswordKey);

        //make sure not null
        if (userPhone != null && userPassword != null) { // crashes without this line
            if(!userPhone.equals("") && !userPassword.equals("")) {
                if (!TextUtils.isEmpty(userPhone) && !TextUtils.isEmpty(userPassword)) {

                    allowAccess(userPhone, userPassword);

                    if (TextUtils.isEmpty(userPhone)) {
                        Toast.makeText(this, "Please enter your phone number.", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(userPassword)) {
                        Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                    } else {
                        loadingBar.setTitle("Welcome back.");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();
                    }
                }
            }
        }
    }

    private void allowAccess(final String userPhoneKey, final String userPasswordKey) {

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(userPhoneKey).exists()) {
                    UserModel userData = dataSnapshot.child("Users").child(userPhoneKey).getValue(UserModel.class);
                    if (userData.getPhone().equals(userPhoneKey)) {
                        if (userData.getPassword().equals(userPasswordKey)) {
                            Toast.makeText(MainActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.currentUser = userData;
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User account does not exist. Please register.", Toast.LENGTH_SHORT).show();
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