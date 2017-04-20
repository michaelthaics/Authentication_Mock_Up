package com.example.stars.locationauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GpsModule gps = new GpsModule(this);
        Double longitude = gps.getLongitude();
        Double latitude = gps.getLatitude();

        Bundle bundle = getIntent().getExtras();
        String recipient = bundle.getString("recipient");
        String password = bundle.getString("password");
        Double amount = bundle.getDouble("amount");

        Log.d("Recipient:", recipient);
        Log.d("Password:", password);
        Log.d("amount:", amount.toString());
        Log.d("Coordinate Longitude", longitude.toString());
        Log.d("Coordinate Latitude", latitude.toString());

        setContentView(R.layout.activity_confirmation);

        EditText second_pass = (EditText)findViewById(R.id.second_password);
        second_pass.setVisibility(View.GONE);
        Button button = (Button)findViewById(R.id.confirm_button);
        button.setVisibility(View.INVISIBLE);

        TextView text = (TextView)findViewById(R.id.recipient);
        text.setText("Recipient: " + recipient);
        text = (TextView)findViewById(R.id.amount);
        text.setText("Amount sent: " + amount.toString());
        text = (TextView)findViewById(R.id.longitude);
        text.setText("Your Longitude: " + longitude.toString());
        text = (TextView)findViewById(R.id.latitude);
        text.setText("Your Latitude: " + latitude.toString());

        Double authLong = -80.2229;
        Double authLat = 43.5286;

        text = (TextView)findViewById(R.id.status);

        if (authLong - longitude > 0.4 || authLong - longitude < -0.4 || authLat - latitude > 0.4 || authLat - latitude < -0.4){
            Double longDiff = authLong-longitude;
            Log.d("long is: ", longDiff.toString());
            Double latDiff = authLat - latitude;
            Log.d("lat is: ", latDiff.toString());

            text.setText("You seem to be outside the primary authentication zone. Please enter your secondary password.");
            second_pass.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);

        }
        else{
            text.setText("Your transfer has been successfully sent.");
        }

    }

    public void confirmPassword(View v){
        TextView text;
        EditText second_pass = (EditText)findViewById(R.id.second_password);
        String secondary_password = second_pass.getText().toString();

        if (secondary_password.matches("obimbo123")) {
            text = (TextView)findViewById(R.id.status2);
            text.setText("Your transfer has been successfully sent.");
        }
        else if(secondary_password.matches("gettingmugged")){
            text = (TextView)findViewById(R.id.status2);
            text.setText("Your transfer has been successfully sent.");
        }
        else{
            second_pass.setError("WRONG PASSWORD, PLEASE TRY AGAIN");
        }
    }
}
