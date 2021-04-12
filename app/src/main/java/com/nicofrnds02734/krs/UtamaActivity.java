package com.nicofrnds02734.krs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UtamaActivity extends AppCompatActivity {

    CardView cardentry, cardkhs, cardcetak, cardkeluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        cardentry = findViewById(R.id.cardentry);
        cardkhs = findViewById(R.id.cardkhs);
        cardcetak = findViewById(R.id.cardcetak);
        cardkeluar = findViewById(R.id.cardkeluar);

        cardkhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UtamaActivity.this, KartuActivity.class));
            }
        });
        cardentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UtamaActivity.this, InputActivity.class));
            }
        });
    }
}