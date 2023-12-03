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

public class RegistrationActivity extends AppCompatActivity {

    Button signUp;
    EditText name, surname, address, email, password;
    TextView signIn;
    static final String baseUrl = "http://10.146.1.100:3048/BC210/api/blaga/store/v1.0/users";
    static final String successfullyRegistered = "Successfully registered";
    static final String emptyName = "Name should not be empty";
    static final String emptySurname = "Surname should not be empty";
    static final String emptyAddress = "Address should not be empty";
    static final String emptyEmail = "Email should not be empty";
    static final String emptyPassword = "Password should not be empty";
    static final String passwordLength = "The password length should be greater than 6 letter";
    static final String emailString = "email";
    static final String nameString = "name";
    static final String surnameString = "surname";
    static final String addressString = "address";
    static final String dummyString = "dummyString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signUp = findViewById(R.id.signUp_btn);
        email = findViewById(R.id.email_reg);
        name = findViewById(R.id.name_reg);
        surname = findViewById(R.id.surname_reg);
        address = findViewById(R.id.address_reg);
        password = findViewById(R.id.password_reg);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(view -> startActivity(new Intent(RegistrationActivity.this, LoginActivity.class)));

        signUp.setOnClickListener(view -> {
            WebServices webServices = new WebServices();
            String userEmail = email.getText().toString();
            String userName = name.getText().toString();
            String userSurname = surname.getText().toString();
            String userAddress = address.getText().toString();
            String userPassword = password.getText().toString();

            if (!checkUserInputs(userEmail, userName, userSurname, userAddress, userPassword))
                return;

            JSONObject jsonObject = createJsonObject(userEmail, userName, userSurname, userAddress, userPassword);

            String message = webServices.sendPostRequest(baseUrl, jsonObject, WebServices.authentication.REGISTER);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            if (message.equals(successfullyRegistered)) {
                Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(emailString, userEmail);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private boolean checkUserInputs(String email, String name, String surname, String address, String password) {
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, emptyName, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(surname)){
            Toast.makeText(this, emptySurname, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(address)){
            Toast.makeText(this, emptyAddress, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, emptyEmail, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, emptyPassword, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.length() < 6){
            Toast.makeText(this, passwordLength, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private JSONObject createJsonObject(String email, String name, String surname, String address, String password) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(emailString, email);
            jsonObject.put(nameString, name);
            jsonObject.put(surnameString, surname);
            jsonObject.put(addressString, address);
            jsonObject.put(dummyString, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}