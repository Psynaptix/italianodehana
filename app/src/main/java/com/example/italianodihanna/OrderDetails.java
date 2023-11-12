package com.example.italianodihanna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class OrderDetails extends AppCompatActivity {

    TextView listView, priceView;
    String list_choice;
    double price_peso;

    Button homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        homebtn = findViewById(R.id.back);

        listView = (TextView) findViewById(R.id.listView);
        priceView = (TextView) findViewById(R.id.priceView);


        Bundle bundle = getIntent().getExtras();
        list_choice = bundle.getString("choices");
        price_peso = bundle.getDouble("price");

        listView.setText(list_choice);
        priceView.setText("Total: " + price_peso);

        homebtn.setOnClickListener(v -> {
            Intent logIn = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logIn);
            finish();
            Toast.makeText(OrderDetails.this, "Log out Successfully",
                    Toast.LENGTH_SHORT).show();
        });
    }
}