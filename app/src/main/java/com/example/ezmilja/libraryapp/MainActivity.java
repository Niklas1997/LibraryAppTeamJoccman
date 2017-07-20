package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                Timer startTimer = new Timer();
                startTimer.schedule(new TimerTask() {

                            @Override
                            public void run() {

                                Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                                startActivity(intent);
                            }


                }, 3000);
    }
}


