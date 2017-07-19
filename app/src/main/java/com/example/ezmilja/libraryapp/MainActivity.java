package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_booklist;
    private String screenSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels / (displayMetrics.densityDpi / 160);
        int width = displayMetrics.widthPixels / (displayMetrics.densityDpi / 160);

        if (height < 470 || width < 320) {
            setContentView(R.layout.small_activity_main);
            screenSize = "small";
        }
        else if (height < 640 || width < 480){
            setContentView(R.layout.medium_activity_main);
            screenSize = "medium";
        }
        else if (height < 960 || width < 720){
            setContentView(R.layout.large_activity_main);
            screenSize = "large";
        }
        else {
            setContentView(R.layout.xlarge_activity_main);
            screenSize = "xlarge";
        }

        createButton();

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");
        TextView textView1 = (TextView) findViewById(R.id.TextView1);
        textView1.setTypeface(myTypeFace1);


    }

    private void createButton(){
        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");
        btn_booklist = (Button) findViewById(R.id.btn_booklist);
        btn_booklist.setText(screenSize);
        btn_booklist.setTypeface(myTypeFace1);
        btn_booklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ContentsActivity.class);
                intent.putExtra("screenSize", screenSize);
                finish();
                startActivity(intent);
            }
        });

    }

}

