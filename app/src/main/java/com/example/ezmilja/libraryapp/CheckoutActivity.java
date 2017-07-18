package com.example.ezmilja.libraryapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static com.example.ezmilja.libraryapp.R.id.btn31list;
import static com.example.ezmilja.libraryapp.R.id.close;
import static com.example.ezmilja.libraryapp.R.id.name;

public class CheckoutActivity extends AppCompatActivity {

    private ImageButton btn_info;
    private static RadioButton radioButton;
    private static Button button;
    private static RadioGroup radioGroup;
    private boolean on;
    TextView textView1;
    private AutoCompleteTextView acTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        acTextView = (AutoCompleteTextView) findViewById(R.id.dropDownTextView);
        textView1 = (TextView)findViewById(R.id.name);
        textView1.setFocusable(false);

        createButton();
        dropDownList();
    }

    private void dropDownList(){
        String[] temp = getResources().getStringArray(R.array.ISBN);
        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, temp);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(3);
        //Set the adapter
        acTextView.setAdapter(adapter);
    }


    private void createButton() {
        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(), "yourfont.ttf");
        button = (Button) findViewById(R.id.button);
        button.setTypeface(myTypeFace1);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        on = false;

        btn_info = (ImageButton) findViewById(R.id.imageButton3);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               makeInfoDialog();
            }
        });
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (on) {
                            int selected_id = radioGroup.getCheckedRadioButtonId();
                            radioButton = (RadioButton) findViewById(selected_id);

                            if (radioButton.getText().equals("Check IN")) {
                                makeRatingDialog();
                            }
                            else {
                                Toast.makeText(CheckoutActivity.this, "Book Checked OUT", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                on =true;
            }
        });

        autoFill();
    }

    private void autoFill(){
        try{
            textView1 = (TextView)findViewById(R.id.name);
            Bundle bundle = getIntent().getExtras();
            String message = bundle.getString("isbn");
            String message1 = bundle.getString("book");
            acTextView.setFocusable(false);
            textView1.setFocusable(false);
            acTextView.setOnClickListener(null);
            textView1.setOnClickListener(null);

            if (message.contains("ISBN:")) {
                message = message.substring(5);
            }

            acTextView.setBackgroundColor(Color.rgb(214,216,216));
            textView1.setBackgroundColor(Color.rgb(214,216,216));

            radioButton = (RadioButton) findViewById(R.id.rbtn_out);
            radioButton.setChecked(true);

            acTextView.setText(message);
            textView1.setText(message1);
        }
        catch (Exception e){}
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

    private void makeInfoDialog(){
        final Dialog dialog = new Dialog(CheckoutActivity.this);
        dialog.setContentView(R.layout.icon);
        dialog.setTitle("Please use the ISBN-13 on back of Book");
        dialog.show();

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");

        TextView title = (TextView) dialog.findViewById(R.id.title);
        title.setTypeface(myTypeFace1);

        Button close = (Button) dialog.findViewById(R.id.close);

        close.setTypeface(myTypeFace1);

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void makeRatingDialog(){

        String[] book = textView1.getText().toString().split("\n");

        if (book.length < 2){
            Toast.makeText(CheckoutActivity.this, "No book found", Toast.LENGTH_SHORT).show();
            return;
        }

        final Dialog dialog = new Dialog(CheckoutActivity.this);
        dialog.setContentView(R.layout.rating_dialog);
        dialog.show();

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");


        TextView title = (TextView) dialog.findViewById(R.id.tbx_title);
        title.setTypeface(myTypeFace1);

        TextView bookName = (TextView) dialog.findViewById(R.id.tbx_bookname);
        bookName.setText(book[0]);

        TextView author = (TextView) dialog.findViewById(R.id.tbx_author);
        author.setText(book[1]);

        Button close = (Button) dialog.findViewById(R.id.close);

        close.setTypeface(myTypeFace1);


        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}
