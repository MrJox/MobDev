package com.example.lab3.Activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab3.ApplicationController;
import com.example.lab3.Models.CredentialsModel;
import com.example.lab3.Models.ResponseModel;
import com.example.lab3.R;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final TextView statusView = findViewById(R.id.signup_statusView);
        final EditText loginText = findViewById(R.id.signup_loginTxt);
        final EditText pswdText = findViewById(R.id.signup_pswdTxt);
        final EditText pswd2Text = findViewById(R.id.signup_pswd2Txt);
        final Button signupBtn = findViewById(R.id.signup_signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginText.getText().toString();
                String pswd = pswdText.getText().toString();
                String pswd2 = pswd2Text.getText().toString();

                if (!login.isEmpty() && !pswd.isEmpty() && !pswd2.isEmpty()) {
                    if (pswd.equals(pswd2)) {
                        CredentialsModel credentials = new CredentialsModel(login, pswd);
                        ResponseModel response = ApplicationController.getInstance().getDataProvider().signup(credentials);
                        statusView.setText(response.getStatus());
                        if (response.getCode() == 200)
                            finish();
                    } else {
                        statusView.setText(getString(R.string.pswdMismatch));
                    }
                } else {
                    statusView.setText(R.string.fieldsEmpty);
                }
            }
        });
    }
}
