package com.example.fragrancestore;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    EditText email, password;
    TextView signUp;
    static final String baseUrl = "http://10.146.1.100:3048/BC210/api/blaga/store/v1.0/checks";
    static final String successfullyLogged = "Successfully logged in";
    static final String emailEmpty = "Email should not be empty";
    static final String passwordEmpty = "Password should not be empty";
    static final String passwordLength = "The password length should be greater than 6 letter";
    static final String emailString = "email";
    static final String passwordString = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.signIn_btn);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));

        signIn.setOnClickListener(view -> {
            WebServices webServices = new WebServices();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            if (!checkUserInputs(userEmail, userPassword))
                return;

            JSONObject jsonObject = createJsonObject(userEmail, userPassword);

            String message = webServices.sendPostRequest(baseUrl, jsonObject, WebServices.authentication.LOGIN);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            if (message.equals(successfullyLogged)) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(emailString, userEmail);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private boolean checkUserInputs(String email, String password) {
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, emailEmpty, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, passwordEmpty, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.length() < 6){
            Toast.makeText(this, passwordLength, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private JSONObject createJsonObject(String email, String password) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(emailString, email);
            jsonObject.put(passwordString, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}