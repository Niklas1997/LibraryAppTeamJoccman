package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookInfoActivity extends AppCompatActivity {


    private Button btn_back;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);


        createButton();
        readBookInfo();
    }

    private void readBookInfo(){
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("bookName");
        message = message + " : " + bundle.getString("author");
        textView = (TextView) findViewById(R.id.textView3);
        textView.setText(message);
    }

    private void createButton() {
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
