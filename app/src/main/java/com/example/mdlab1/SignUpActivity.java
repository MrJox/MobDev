package com.example.mdlab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class SignUpActivity extends AppCompatActivity {
    private EditText login;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login = findViewById(R.id.loginField);
        firstName = findViewById(R.id.firstField);
        lastName = findViewById(R.id.lastField);
        email = findViewById(R.id.emailField);
        pswd = findViewById(R.id.pswdField);
        Button signUp = findViewById(R.id.signupBtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp(new Person(  firstName.getText().toString(),
                                    lastName.getText().toString(),
                                    email.getText().toString(),
                                    login.getText().toString(),
                                    pswd.getText().toString()));
            }
        });
    }

    private void SignUp(Person newUser) {
        MainActivity.Ddp.getUsersList().add(newUser);
        finish();
    }
}
