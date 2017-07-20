package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookInfoActivity extends AppCompatActivity {
    private Button btn_back;
    private TextView textView, txt_rating;
    private Button btn_check;
    private AutoCompleteTextView descriptionTxt, txt_details;
    private Typeface myTypeFace1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();

        final String id = intent.getStringExtra("id");
        final Book book = BookCache.CACHE.getBook(Integer.parseInt(id.trim()));

        myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");

        createTextViews(book);
        createButtons(book);
    }

    private void createTextViews(Book book){
        descriptionTxt = (AutoCompleteTextView)findViewById(R.id.descriptionTxt);
        descriptionTxt.setText(book.getDescription());

        txt_details = (AutoCompleteTextView)findViewById(R.id.txt_details);
        txt_details.setText( "Title : " + book.getBookName() + "\n" + "\n" + "Author : " + book.getAuthor() + "\n"
                + "\n"+ "Publisher : " + book.getPublisher() + "\n" + "\n" + "Number of Copies Available");

        txt_rating = (TextView) findViewById(R.id.txt_Rating);
        txt_rating.setText("User Rating : " + book.getRating() + "/5");
    }

    private void createButtons(final Book book){
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setTypeface(myTypeFace1);


        btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setTypeface(myTypeFace1);

        btn_check = (Button) findViewById(R.id. btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookInfoActivity.this, CheckoutActivity.class);
                intent.putExtra("isbn", book.getIsbn());
                startActivity(intent);
            }}
        );
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

}
