package com.emsi.rate_star;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emsi.rate_star.adapter.CardAdapter;
import com.emsi.rate_star.services.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper MyDb;
    ArrayList<String> card_id,card_name,card_image,card_rate;
    CardAdapter cardAdapter;
    RatingBar rate;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.Liste_Fifa);
        id = findViewById(R.id.id);


        add_button = findViewById(R.id.add_b);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, Add_Card.class);
                startActivity(intent);
            }
        });

        MyDb = new MyDatabaseHelper(MainActivity.this);
        card_id = new ArrayList<>();
        card_name = new ArrayList<>();
        card_image = new ArrayList<>();
        card_rate = new ArrayList<>();

        StoreData();

        rate =findViewById(R.id.ratingBar);
        if (rate != null){


            rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    MyDatabaseHelper MyDb = new MyDatabaseHelper(MainActivity.this);
                    MyDb.updateRate(id.getText().toString().trim(),rate.getRating());
                }
            });

        }

        cardAdapter = new CardAdapter(MainActivity.this,card_id,card_name,card_image,card_rate);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void StoreData(){
        Cursor cursor = MyDb.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                card_id.add(cursor.getString(0));
                card_name.add(cursor.getString(1));
                card_image.add(cursor.getString(2));
                card_rate.add(cursor.getString(3));
            }
        }
    }


}