package com.example.fragrancestore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class UserPageActivity extends AppCompatActivity {

    EditText name, surname, address;
    Button save;
    static final String detailsUpdated = "The details were updated successfully.";
    static final String baseUrl = "http://10.146.1.100:3048/BC210/api/blaga/store/v1.0/users";
    static final String nameString = "name";
    static final String surnameString = "surname";
    static final String addressString = "address";
    static final String systemIdString = "systemId";
    static final String odataEtagString = "@odata.etag";
    static final String emailString = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        name = findViewById(R.id.name_up);
        surname = findViewById(R.id.surname_up);
        address = findViewById(R.id.address_up);
        save = findViewById(R.id.save_btn);

        save.setOnClickListener(view -> {
            WebServices webServices = new WebServices();
            String userName = name.getText().toString();
            String userSurname = surname.getText().toString();
            String userAddress = address.getText().toString();
            Bundle bundleReceived = getIntent().getExtras();
            String systemId = bundleReceived.getString(systemIdString);
            String odataEtag = bundleReceived.getString(odataEtagString);

            JSONObject jsonObject = createJsonObject(userName, userSurname, userAddress);

            String message = webServices.sendPutRequest(baseUrl + "(" + systemId + ")", jsonObject, odataEtag);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            if (message.equals(detailsUpdated)) {
                Intent intent = new Intent(UserPageActivity.this, HomeActivity.class);

                Bundle bundleToSend = new Bundle();
                bundleToSend.putString(emailString, bundleReceived.getString(emailString));

                intent.putExtras(bundleReceived);
                startActivity(intent);
            }
        });
    }

    private JSONObject createJsonObject(String name, String surname, String address) {
        JSONObject jsonObject = new JSONObject();

        try {
            if (!name.isEmpty())
                jsonObject.put(nameString, name);
            if (!surname.isEmpty())
                jsonObject.put(surnameString, surname);
            if (!address.isEmpty())
                jsonObject.put(addressString, address);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
