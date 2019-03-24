package com.example.mdlab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    public static DemoDataProvider Ddp;
    private TextView StatusText;
    private EditText LoginText;
    private EditText PswdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ddp = new DemoDataProvider();
        StatusText = findViewById(R.id.statusText);
        LoginText = findViewById(R.id.loginField);
        PswdText = findViewById(R.id.pswdField);
        Button loginButton = findViewById(R.id.loginBtn);
        Button signUpButton = findViewById(R.id.signUpBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate(LoginText.getText().toString(), PswdText.getText().toString());
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    private void Validate(String login, String pswd) {
        for (Person person: Ddp.getUsersList()) {
            if ((person.getLogin().equals(login) || person.getEmail().equals(login))
                && person.getPassword().equals(pswd)) {
                StatusText.setText("Login OK");
            } else {
                StatusText.setText("Login failed");
            }
        }
    }

    private void SignUp() {
        Intent signUpActivity = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(signUpActivity);
    }
}
