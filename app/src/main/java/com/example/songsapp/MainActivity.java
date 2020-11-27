package com.example.songsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView judikay = (ImageView) findViewById(R.id.judikay);
        judikay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent judikayIntent = new Intent(MainActivity.this, JudikayActivity.class);
                startActivity(judikayIntent);
            }
        });
        ImageView mercy_chinwo = (ImageView) findViewById(R.id.mercy_chinwo);
        mercy_chinwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mercy_chinwoIntent = new Intent(MainActivity.this, Mercy_chinwoActivity.class);
                startActivity(mercy_chinwoIntent);
            }
        });
        ImageView frank_edward = (ImageView) findViewById(R.id.frank_edward);
        frank_edward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent frank_edwardIntent = new Intent(MainActivity.this, Frank_edwardActivity.class);
                startActivity(frank_edwardIntent);
            }
        });
        ImageView travis_green = (ImageView) findViewById(R.id.travis_green);
        travis_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent travis_greenIntent = new Intent(MainActivity.this, Travis_greenActivity.class);
                startActivity(travis_greenIntent);
            }
        });
    }
}