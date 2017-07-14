package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class RequestActivity extends AppCompatActivity {

    private Button button;
    private TextView name;
    private TextView reason;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        createButton();

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");



        name = (TextView) findViewById(R.id.name);
        name.setTypeface(myTypeFace1);


        reason = (TextView) findViewById(R.id.reason);
        reason.setTypeface(myTypeFace1);

        email = (TextView) findViewById(R.id.email);
        email.setTypeface(myTypeFace1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setEnabled(false);

                sendEmail();

                Toast.makeText(RequestActivity.this, "Book requested!",
                        Toast.LENGTH_LONG).show();

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                button.setEnabled(true);

                                finish();


                            }
                        });
                    }
                }, 1600);
            }
        });

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
}



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void createButton(){

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");
        button = (Button) findViewById(R.id.button);
        button.setTypeface(myTypeFace1);}


    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"aoife.broderick@ericsson.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String message = email.getText().toString() + '\n' + name.getText().toString() + '\n' + reason.getText().toString();
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Book Request");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Sending mail"));
            finish();
            Log.i("Email sent", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(RequestActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }





}





