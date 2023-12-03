package com.example.fragrancestore;

import static com.example.fragrancestore.Utils.getFilterString;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static java.net.HttpURLConnection.HTTP_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HomeActivity extends AppCompatActivity {
    Button search, update, delete;
    Spinner brandsDropdown, concentrationDropdown, quantityDropdown;
    EditText name;
    static final String fragranceAvailable = "The fragrance you are looking for is available.";
    static final String fragranceNotAvailable = "Unfortunately, the fragrance you are looking for is not available.";
    static final String userDeletedSuccessfully = "The user was deleted successfully.";
    static final String emailString = "email";
    static final String valueString = "value";
    static final String systemIdString = "systemId";
    static final String odataEtagString = "@odata.etag";
    static final String nameString = "name";
    static final String brandString = "brand";
    static final String priceString = "price";
    static final String concentrationString = "concentration";
    static final String quantityString = "quantity";
    static final String pricePerAmountString = "pricePerAmount";
    static final String baseNotesString = "baseNotes";
    static final String midNotesString = "midNotes";
    static final String topNotesString = "topNotes";
    static final String bitmapImage = "bitmapImage";
    static final String imageString = "image@odata.mediaReadLink";
    static final String nameEmpty = "The name of the fragrance should not be empty";

    public enum Brands{
        ACQUA_DI_PARMA ("Acqua di Parma"),
        ARMANI ("Armani"),
        CHANEL ("Chanel"),
        CLIVE_CHRISTIAN ("Clive Christian"),
        DIOR ("Dior"),
        HUGO_BOSS ("Hugo Boss"),
        LALIQUE ("Lalique"),
        MAISON_FRANCIS_KURKJIDAN ("Maison Francis Kurkjidan"),
        PRADA ("Prada"),
        TOM_FORD ("Tom Ford"),
        VERSACE("Versace"),
        XERJOFF ("Xerjoff");

        private final String name;

        Brands(String s) {
            name = s;
        }

        @NonNull
        public String toString() {
            return this.name;
        }
    }

    public enum Concentration{
        EAU_FRAICHE ("Eau Fraiche"),
        EAU_DE_COLOGNE ("Eau de Cologne"),
        EAU_DE_TOILETTE ("Eau de Toilette"),
        EAU_DE_PARFUM ("Eau de Parfum"),
        PARFUM ("Parfum");

        private final String name;

        Concentration(String s) {
            name = s;
        }

        @NonNull
        public String toString() {
            return this.name;
        }
    }

    public enum Quantity{
        THIRTY_ML ("30 ml"),
        FIFTY_ML ("50 ml"),
        SIXTY_ML ("60 ml"),
        SEVENTYFIVE_ML ("75 ml"),
        ONE_HUNDRED_ML ("100 ml"),
        ONE_HUNDRED_TWENTYFIVE_ML ("125 ml"),
        ONE_HUNDRED_FIFTY_ML ("150 ml"),
        ONE_HUNDRED_EIGHTY_ML ("180 ml"),
        TWO_HUNDRED_ML ("200 ml"),
        TWO_HUNDRED_FIFTY_ML ("250 ml");

        private final String name;

        Quantity(String s) { name = s; }

        @NonNull
        public String toString() {
            return this.name;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        brandsDropdown = findViewById(R.id.brandSpinner);
        concentrationDropdown = findViewById(R.id.concentrationSpinner);
        quantityDropdown = findViewById(R.id.quantitySpinner);
        name = findViewById(R.id.nameEdit);
        search = findViewById(R.id.searchButton);
        update = findViewById(R.id.updateButton);
        delete = findViewById(R.id.deleteButton);
        Bundle bundleReceived = getIntent().getExtras();

        brandsDropdown.setAdapter(new ArrayAdapter<>(this, R.layout.color_spinner_layout, Brands.values()));
        concentrationDropdown.setAdapter(new ArrayAdapter<>(this, R.layout.color_spinner_layout, Concentration.values()));
        quantityDropdown.setAdapter(new ArrayAdapter<>(this, R.layout.color_spinner_layout, Quantity.values()));

        search.setOnClickListener(view -> {
            final String baseUrl = "http://10.146.1.100:3048/BC210/api/blaga/store/v1.0/fragrances";
            WebServices webServices = new WebServices();
            Pair<Integer, String> responseCodeBody;
            String fragranceName = name.getText().toString();
            String fragranceBrand = brandsDropdown.getSelectedItem().toString();
            String fragranceConcentration = concentrationDropdown.getSelectedItem().toString();
            String fragranceQuantity = quantityDropdown.getSelectedItem().toString();

            if (!checkUserInputs(fragranceName))
                return;

            String filter = getFilterStringSearch(fragranceName, fragranceBrand, fragranceConcentration, fragranceQuantity);

            responseCodeBody = webServices.sendGetRequest(baseUrl, filter);
            String message = getMessage(responseCodeBody);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            if (message.equals(fragranceAvailable)) {
                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                Bundle bundleToSend = getFragranceBundle(responseCodeBody.second, bundleReceived.getString(emailString));

                intent.putExtras(bundleToSend);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(view -> {
            final String baseUrl = "http://10.146.1.100:3048/BC210/api/blaga/store/v1.0/users";
            WebServices webServices = new WebServices();
            Pair<Integer, String> responseCodeBodyGet;
            Pair<Integer, String> responseCodeBodyDelete;

            responseCodeBodyGet = webServices.sendGetRequest(baseUrl, getFilterString(bundleReceived.getString(emailString)));
            if (responseCodeBodyGet.first == HTTP_OK) {
                responseCodeBodyDelete = webServices.sendDeleteRequest(baseUrl +
                                "(" + getJSONValue(responseCodeBodyGet.second, systemIdString) + ")",
                                getJSONValue(responseCodeBodyGet.second, odataEtagString));

                String message = getMessage(responseCodeBodyDelete);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                if (message.equals(userDeletedSuccessfully)) {
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

        });

        update.setOnClickListener(view -> {
            final String baseUrl = "http://10.146.1.100:3048/BC210/api/blaga/store/v1.0/users";
            Intent intent = new Intent(HomeActivity.this, UserPageActivity.class);
            Pair<Integer, String> responseCodeBodyGet;
            WebServices webServices = new WebServices();

            responseCodeBodyGet = webServices.sendGetRequest(baseUrl, getFilterString(bundleReceived.getString(emailString)));
            if (responseCodeBodyGet.first == HTTP_OK) {
                Bundle bundleToSend = new Bundle();
                bundleToSend.putString(emailString, bundleReceived.getString(emailString));
                bundleToSend.putString(systemIdString, getJSONValue(responseCodeBodyGet.second, systemIdString));
                bundleToSend.putString(odataEtagString, getJSONValue(responseCodeBodyGet.second, odataEtagString));

                intent.putExtras(bundleToSend);
                startActivity(intent);
            }
        });
    }

    private boolean checkUserInputs(String fragranceName) {
        if (TextUtils.isEmpty(fragranceName)){
            Toast.makeText(this, nameEmpty, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getFilterStringSearch(String fragranceName, String fragranceBrand, String fragranceConcentration, String fragranceQuantity) {
        String filter = "?$filter=(";

        filter += "name eq " + "'" + fragranceName + "'" + " and ";
        filter += "brand eq " + "'" + fragranceBrand + "'" + " and ";
        filter += "concentration eq " + "'" + fragranceConcentration + "'" + " and ";
        filter += "quantity eq " + "'" + fragranceQuantity + "'" + ")";

        return filter;
    }

    private String getMessage(Pair<Integer, String> responseCodeBody) {
        switch (responseCodeBody.first) {
            case HTTP_NO_CONTENT:
                return userDeletedSuccessfully;
            case HTTP_OK:
                if (!isEmptyResponse(responseCodeBody.second))
                    return fragranceAvailable;
            default:
                return fragranceNotAvailable;
        }
    }

    private Bundle getFragranceBundle(String responseBody, String email) {
        Bundle bundle = new Bundle();
        WebServices webServices = new WebServices();
        Pair<Integer, InputStream> responseCodeBody;

        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray jsonValues = jsonResponse.getJSONArray(valueString);
            JSONObject jsonFragrance = jsonValues.getJSONObject(0);
            bundle.putString(emailString, email);
            bundle.putString(nameString, jsonFragrance.get(nameString).toString());
            bundle.putString(brandString, jsonFragrance.get(brandString).toString());
            bundle.putString(priceString, jsonFragrance.get(priceString).toString());
            bundle.putString(concentrationString, jsonFragrance.get(concentrationString).toString());
            bundle.putString(quantityString, jsonFragrance.get(quantityString).toString());
            bundle.putString(pricePerAmountString, jsonFragrance.get(pricePerAmountString).toString());
            bundle.putString(baseNotesString, jsonFragrance.get(baseNotesString).toString());
            bundle.putString(midNotesString, jsonFragrance.get(midNotesString).toString());
            bundle.putString(topNotesString, jsonFragrance.get(topNotesString).toString());

            String baseUrl = jsonFragrance.get(imageString).toString();
            responseCodeBody = webServices.sendGetRequestPicture(baseUrl, "");
            if (responseCodeBody.first == HTTP_OK) {
                Bitmap bitmap = BitmapFactory.decodeStream(responseCodeBody.second);
                bundle.putString(bitmapImage, encodeImage(bitmap, Bitmap.CompressFormat.JPEG, 90));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bundle;
    }

    public static String encodeImage(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    private String getJSONValue(String responseBody, String value) {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray jsonValues = jsonResponse.getJSONArray(valueString);
            JSONObject jsonUser = jsonValues.getJSONObject(0);

            return jsonUser.get(value).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    private boolean isEmptyResponse(String responseBody) {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray jsonValues = jsonResponse.getJSONArray(valueString);

            if (jsonValues.length() == 0)
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
