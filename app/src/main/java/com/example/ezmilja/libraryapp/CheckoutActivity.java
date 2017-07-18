package com.example.ezmilja.libraryapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    private final BookCache books = BookCache.CACHE;
    private ImageButton btn_info;
    private static RadioButton radioButton;
    private static Button button;
    private static RadioGroup radioGroup;
    private boolean on;
    TextView textView1;
    private AutoCompleteTextView acTextView;
    private ImageView image_book;
    private String[] isbn_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        acTextView = (AutoCompleteTextView) findViewById(R.id.dropDownTextView);
        textView1 = (TextView)findViewById(R.id.name);
        textView1.setFocusable(false);

        image_book = (ImageView) findViewById(R.id.imgv_bookimg);


        createButton();
        dropDownList();
        autoFill();
    }

    private void dropDownList(){
        int numBooks = books.getNumberOfBooks();
        isbn_array = new String[numBooks];
        for (int i = 0; i < numBooks; i++){
            isbn_array[i] = books.getBook(i).getIsbn().substring(5);
        }
        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, isbn_array);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);

        acTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = getBookFromISBN((String) adapterView.getItemAtPosition(i));
                textView1.setVisibility(View.VISIBLE);
                textView1.setText(book.getBookName() + "\n" + book.getAuthor());

                image_book.setImageResource(book.getImageId());
                image_book.setVisibility(View.VISIBLE);
            }
        });
    }

    private Book getBookFromISBN(String isbn){
        for (int i = 0; i < isbn_array.length; i++){
            if (isbn_array[i].equals(isbn)) {
                return books.getBook(i);
            }
        }
        return books.getBook(0);
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

    }

    private void autoFill(){
        try{
            textView1 = (TextView)findViewById(R.id.name);
            Bundle bundle = getIntent().getExtras();
            String message = bundle.getString("isbn");
            if (message.contains("ISBN:")) {
                message = message.substring(5);
            }
            Book temp = getBookFromISBN(message);
            String message1 = bundle.getString("book");
            acTextView.setFocusable(false);
            acTextView.setOnClickListener(null);

            acTextView.setBackgroundColor(Color.rgb(214,216,216));

            radioButton = (RadioButton) findViewById(R.id.rbtn_out);
            radioButton.setChecked(true);

            acTextView.setText(message);
            textView1.setText(message1);
            image_book.setImageResource(temp.getImageId());

            image_book.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
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
