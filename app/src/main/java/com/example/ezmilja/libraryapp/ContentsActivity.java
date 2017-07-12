package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentsActivity extends AppCompatActivity {

    private Button btn_list;
    private Button btn31list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        createButton();

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");
        btn31list = (Button) findViewById(R.id.btn31list);
        btn31list.setTypeface(myTypeFace1);
    }

    private void createButton(){
        btn_list = (Button) findViewById(R.id.btn31list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentsActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
}}
