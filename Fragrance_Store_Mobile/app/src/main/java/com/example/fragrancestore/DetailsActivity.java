package com.example.fragrancestore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    static final String nameString = "name";
    static final String emailString = "email";
    static final String brandString = "brand";
    static final String priceString = "price";
    static final String concentrationString = "concentration";
    static final String quantityString = "quantity";
    static final String pricePerAmountString = "pricePerAmount";
    static final String baseNotesString = "baseNotes";
    static final String midNotesString = "midNotes";
    static final String topNotesString = "topNotes";
    static final String bitmapImage = "bitmapImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nameDetails = findViewById(R.id.name_det);
        TextView brandDetails = findViewById(R.id.brand_det);
        TextView priceDetails = findViewById(R.id.price_det);
        TextView pricePerAmountDetails = findViewById(R.id.pricePerAmount_det);
        TextView concentrationDetails = findViewById(R.id.concentration_det);
        TextView quantityDetails = findViewById(R.id.quantity_det);
        TextView baseNotesDetails = findViewById(R.id.baseNotes_det);
        TextView midNotesDetails = findViewById(R.id.midNotes_det);
        TextView topNotesDetails = findViewById(R.id.topNotes_det);
        ImageView imageDetails = findViewById(R.id.image_det);
        Button search = findViewById(R.id.searchButton);

        Bundle bundle = getIntent().getExtras();

        String emailBundle = bundle.getString(emailString);
        String nameBundle = bundle.getString(nameString);
        String brandBundle = bundle.getString(brandString);
        String priceBundle = bundle.getString(priceString);
        String pricePerAmountBundle = bundle.getString(pricePerAmountString);
        String concentrationBundle = bundle.getString(concentrationString);
        String quantityBundle = bundle.getString(quantityString);
        String baseNotesBundle = bundle.getString(baseNotesString);
        String midNotesBundle = bundle.getString(midNotesString);
        String topNotesBundle = bundle.getString(topNotesString);

        String imageBundle = bundle.getString(bitmapImage);
        imageDetails.setImageBitmap(decodeImage(imageBundle));
        nameDetails.setText(nameBundle);
        brandDetails.setText(brandBundle);
        priceDetails.setText(priceBundle);
        pricePerAmountDetails.setText(pricePerAmountBundle);
        concentrationDetails.setText(concentrationBundle);
        quantityDetails.setText(quantityBundle);
        baseNotesDetails.setText(baseNotesBundle);
        midNotesDetails.setText(midNotesBundle);
        topNotesDetails.setText(topNotesBundle);

        search.setOnClickListener(view -> {
            Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
            Bundle bundleToSend = new Bundle();
            bundleToSend.putString(emailString, emailBundle);
            intent.putExtras(bundleToSend);
            startActivity(intent);
        });
    }

    public static Bitmap decodeImage(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
