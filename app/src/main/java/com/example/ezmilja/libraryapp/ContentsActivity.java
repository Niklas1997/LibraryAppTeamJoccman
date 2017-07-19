package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class ContentsActivity extends AppCompatActivity {

    private Button btn_list;
    private Button btn31list;
    private Button btn_rqst;
    private Button btn_check;
    private String screenSize;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        screenSize = bundle.getString("screenSize");

        switch(screenSize) {
            case "xlarge":
                setContentView(R.layout.xlarge_activity_contents);
                break;
            case "medium":
                setContentView(R.layout.medium_activity_contents);
                break;
            case "large":
                setContentView(R.layout.large_activity_contents);
                break;
            default:
                setContentView(R.layout.small_activity_contents);
        }


        createButton();
    }

    private void createButton(){

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");
        btn31list = (Button) findViewById(R.id.btn31list);
        btn31list.setTypeface(myTypeFace1);


        btn_rqst = (Button) findViewById(R.id.btn_rqst);
        btn_rqst.setTypeface(myTypeFace1);


        btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setTypeface(myTypeFace1);


        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setTypeface(myTypeFace1);

        btn_list = (Button) findViewById(R.id.btn31list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentsActivity.this, ListActivity.class);
                intent.putExtra("screenSize", screenSize);
                startActivity(intent);
            }
        });
        btn_rqst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentsActivity.this, LeaderboardList.class);
                intent.putExtra("screenSize", screenSize);
                startActivity(intent);
            }

        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentsActivity.this, CheckoutActivity.class);
                intent.putExtra("screenSize", screenSize);
                startActivity(intent);
            }
        });
    };
}