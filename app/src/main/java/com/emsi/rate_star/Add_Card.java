package com.emsi.rate_star;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.emsi.rate_star.services.MyDatabaseHelper;

public class Add_Card extends AppCompatActivity {

    EditText name,image,rate;
    Button add;
    ImageButton close;
    RatingBar rate_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_activity);

        name = findViewById(R.id.name);
        image = findViewById(R.id.Image);
        rate = findViewById(R.id.Rate);
        add = findViewById(R.id.add_butt);
        close = findViewById(R.id.close);
        rate_b = findViewById(R.id.rateb);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Add_Card.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper MyDb = new MyDatabaseHelper(Add_Card.this);
                MyDb.addCard(name.getText().toString().trim(),image.getText().toString().trim(),rate_b.getRating());
                //,rate.getText().toString().trim()
            }
        });
    }
}