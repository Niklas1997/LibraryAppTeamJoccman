package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookInfoActivity extends AppCompatActivity {
    private Button btn_back;
    private TextView textView, txt_rating;
    private Button btn_check;
    private String screenSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        screenSize = bundle.getString("screenSize");

        switch(screenSize) {
            case "xlarge":
                setContentView(R.layout.xlarge_activity_book_info);
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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();

        final String id = intent.getStringExtra("id");
        final Book book = BookCache.CACHE.getBook(Integer.parseInt(id.trim()));


        TextView isbnTxt = (TextView) findViewById(R.id.isbnTxt);
        isbnTxt.setText(book.getIsbn());

        TextView descriptionTxt = (TextView) findViewById(R.id.descriptionTxt);
        descriptionTxt.setText(book.getDescription());


        TextView publishedTxt = (TextView) findViewById(R.id.publishedTxt);
        publishedTxt.setText(book.getPublisher());


        TextView pageTxt = (TextView) findViewById(R.id.pageTxt);
        pageTxt.setText(book.getPage());

        TextView textView = (TextView) findViewById(R.id.bookname);
        textView.setText(book.getBookName());

        ImageView imageView = (ImageView) findViewById(R.id.imageView10);
        imageView.setImageResource(book.getImageId());

        TextView authorTxt = (TextView) findViewById(R.id.authorTxt);
        authorTxt.setText(book.getAuthor());

        TextView txt_rating = (TextView) findViewById(R.id.txt_Rating);
                txt_rating.setText("User Rating : " + book.getRating() + "/5");

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");
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
                intent.putExtra("screenSize", screenSize);
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
