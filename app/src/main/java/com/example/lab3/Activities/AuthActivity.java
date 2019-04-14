package com.example.lab3.Activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.lab3.ApplicationController;
import com.example.lab3.Models.CredentialsModel;
import com.example.lab3.Models.ResponseModel;
import com.example.lab3.R;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        final TextView statusView = findViewById(R.id.auth_statusView);
        final EditText loginText = findViewById(R.id.auth_loginTxt);
        final EditText pswdText = findViewById(R.id.auth_pswdTxt);
        final Button loginBtn = findViewById(R.id.auth_loginBtn);
        final Button signupBtn = findViewById(R.id.auth_signupBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginText.getText().toString();
                String pswd = pswdText.getText().toString();

                if (!login.isEmpty() && !pswd.isEmpty()) {
                    CredentialsModel credentials = new CredentialsModel(login, pswd);
                    ResponseModel response = ApplicationController.getInstance().getDataProvider().authorize(credentials);
                    statusView.setText(response.getStatus());
                } else {
                    statusView.setText(getString(R.string.fieldsEmpty));
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(AuthActivity.this, SignupActivity.class);
                startActivity(signupActivity);
            }
        });
    }
}
