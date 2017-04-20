package com.example.stars.locationauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendTransfer(View v){
        String recipient = "";
        String password = "";
        Double amount = 0.00;
        Boolean auth = false;

        EditText text = (EditText)findViewById(R.id.recipient_text);

        if (TextUtils.isEmpty(text.getText().toString())){
            text.setError("Cannot be empty");
        }
        else{
            recipient = text.getText().toString();
        }

        text = (EditText)findViewById(R.id.password);
        password = text.getText().toString();


        text = (EditText)findViewById(R.id.transfer_amount);
        if (TextUtils.isEmpty(text.getText().toString())){
            text.setError("Cannot be empty");
        }
        else{
            amount = Double.parseDouble(text.getText().toString());
        }

        Log.d("SET AS", password);
        if (!(password.matches("charlie123"))) {
            text = (EditText)findViewById(R.id.password);
            text.setError("WRONG PASSWORD, PLEASE TRY AGAIN");
            Log.d("SET AS", password);
        }
        else {
            auth = true;
        }

        if (amount > 0 && password != "" && recipient != "" && auth == true){
            Intent passCredentials = new Intent(this, ConfirmationActivity.class);
            passCredentials.putExtra("recipient",recipient);
            passCredentials.putExtra("password", password);
            passCredentials.putExtra("amount", amount);

            startActivity(passCredentials);
            setContentView(R.layout.activity_confirmation);
        }
        else if (amount <= 0){
            text = (EditText)findViewById(R.id.transfer_amount);
            text.setError("Amount has to be greater than 0");
        }


    }
}
