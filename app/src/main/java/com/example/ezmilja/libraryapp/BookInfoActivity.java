package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookInfoActivity extends AppCompatActivity {


    private Button btn_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);


        createButton();
        //readBookInfo();
    }

    private void readBookInfo(){
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("name");
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
    }
}
