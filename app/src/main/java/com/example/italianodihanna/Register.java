package com.example.italianodihanna;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Register extends AppCompatActivity {

    //create variables
    TextInputEditText editTextEmail, editTextPassword;
    Button registerButton;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView regisTextView;

    //check if the user is log in it will open the main activity screen or the resto screen
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent openResto = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(openResto);
            finish();
        }
    }



    //main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize
        mAuth = FirebaseAuth.getInstance();
        editTextEmail= findViewById(R.id.email);
        editTextPassword= findViewById(R.id.password);
        registerButton = findViewById(R.id.btnregister);
        progressBar = findViewById(R.id.progressBar);
        regisTextView = findViewById(R.id.returnToLogin);

        //when text view or return to login is click
        regisTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLogin = new Intent(getApplicationContext(), Login.class);
                startActivity(openLogin);
                finish();

            }
        });

        //when button is click
        registerButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());


            //show message for email
            if(TextUtils.isEmpty((email))){
                Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                return;
            }

            //show message for password
            if(TextUtils.isEmpty((password))){
                Toast.makeText(Register.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                return;
            }

            //condition for registering an account
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Intent openResto = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(openResto);
                            finish();
                            Toast.makeText(Register.this, "Account created successfully",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }
}