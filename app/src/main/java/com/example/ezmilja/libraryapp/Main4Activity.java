package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {


    private Button btn_back;
    private Button btn_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        createButton();
    }

    private void createButton() {
        btn_back = (Button) findViewById(R.id.btn_back);
       // btn_list = (Button) findViewById(R.id.btn_list);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
       // btn_list.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   Intent intent = new Intent(Main4Activity.this, Main2Activity.class);
               // startActivity(intent);
          //  }
        //});
    }
}
