package com.example.italianodihanna;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declare variables
    ImageView logoutButton;
    ViewPager2 viewPager2;
    Button pizzaButton, halohaloButton, chickenButton, sisigButton, grahamButton;
    Button cakeButton;
    String choices = "";
    Double price = 0.00;

    Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //list of init variables

        //buttons in menu
        pizzaButton = findViewById(R.id.btnpizza);
        cakeButton = findViewById(R.id.btncake);
        halohaloButton =  findViewById(R.id.btnhalohalo);
        chickenButton =  findViewById(R.id.btnmanok);
        sisigButton = findViewById(R.id.btnsisig);
        grahamButton =  findViewById(R.id.btngraham);
        placeOrder = findViewById(R.id.placeOrder);


        //initialize variables
        logoutButton = findViewById(R.id.btnlogout);
        viewPager2 = findViewById(R.id.viewPager);

        //banner pictures
        List<SlideItem> sliderItem = new ArrayList<>();
        sliderItem.add(new SlideItem(R.drawable.banner1));
        sliderItem.add(new SlideItem(R.drawable.banner2));
        sliderItem.add(new SlideItem(R.drawable.banner3));
        sliderItem.add(new SlideItem(R.drawable.banner5));
        sliderItem.add(new SlideItem(R.drawable.banner6));
        viewPager2.setAdapter(new SlideAdapter(sliderItem, viewPager2));

        //conditions
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent logIn = new Intent(getApplicationContext(), Login.class);
            startActivity(logIn);
            finish();
            Toast.makeText(MainActivity.this, "Log out Successfully",
                    Toast.LENGTH_SHORT).show();
        });

        placeOrder.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), OrderDetails.class);
            Bundle bundle = new Bundle();
            bundle.putString("choices", choices);
            bundle.putDouble("price", price);
            Toast.makeText(MainActivity.this, "Place Order Succesfully",
                    Toast.LENGTH_SHORT).show();
            i.putExtras(bundle);
            startActivity(i);
            finish();
        });


    }

    public void add_to_list(View view) {
        if (view == findViewById(R.id.btnpizza)) {
            choices = choices + "Pizza             ₱349" + "\n";
            price = price + 349;
            Toast.makeText(MainActivity.this, "Pizza has been added to your order successfully",
                    Toast.LENGTH_SHORT).show();
        } else if (view == findViewById(R.id.btncake)) {
            choices = choices + "Cake              ₱299" + "\n";
            price = price + 299;
            Toast.makeText(MainActivity.this, "Cake has been added to your order successfully",
                    Toast.LENGTH_SHORT).show();
        } else if (view == findViewById(R.id.btnhalohalo)) {
            choices = choices + "Halo Halo      ₱79" + "\n";
            price = price + 79;
            Toast.makeText(MainActivity.this, "Halo Halo has been added to your order successfully",
                    Toast.LENGTH_SHORT).show();
        }else if (view == findViewById(R.id.btnmanok)) {
            choices = choices + "Chicken         ₱299" + "\n";
            price = price + 229;
            Toast.makeText(MainActivity.this, "Chicken has been added to your order successfully",
                    Toast.LENGTH_SHORT).show();
        }else if (view == findViewById(R.id.btnsisig)) {
            choices = choices + "Sisig              ₱99" + "\n";
            price = price + 99;
            Toast.makeText(MainActivity.this, "Sisig has been added to your order successfully",
                    Toast.LENGTH_SHORT).show();
        }else if (view == findViewById(R.id.btngraham)) {
            choices = choices + "Graham         ₱99" + "\n";
            price = price + 99;
            Toast.makeText(MainActivity.this, "Graham has been added to your order successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }

}