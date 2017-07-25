package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import static com.example.ezmilja.libraryapp.R.id.imageView10;

public class BookInfoActivity extends AppCompatActivity {

    private TextView textView, txt_rating, pageTxt, isbnTxt;
    private Button btn_check;
    private AutoCompleteTextView descriptionTxt, txt_details;
    private Typeface myTypeFace1;
    private Book book;
    private String id;
    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();



        final String id = intent.getStringExtra("id");
        final Book book = BookCache.getBook(Integer.parseInt(id.trim()));
        TextView isbnTxt = (TextView) findViewById(R.id.isbnTxt);
        isbnTxt.setText(book.getIsbn());


        myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");

        createTextViews(book);
        createButtons(book);
    }


    private void createTextViews(Book book) {

        descriptionTxt = (AutoCompleteTextView) findViewById(R.id.descriptionTxt);
        descriptionTxt.setText("Description : " + book.getDescription());
        descriptionTxt.setFocusable(false);

        txt_details = (AutoCompleteTextView) findViewById(R.id.txt_details);
        txt_details.setText("Title : " + book.getBookName() + "\n" + "\n" + "Author : " + book.getAuthor() + "\n"
                + "\n" + "Publisher : " + book.getPublisher() + "\n" + "\n" + "Number of Copies Available : " + book.getNumberOfCopys());
        txt_details.setFocusable(false);

        txt_rating = (TextView) findViewById(R.id.txt_Rating);
        txt_rating.setText("User Rating : " + (Math.round(((book.getRating() / book.getNum_rating()) * 10)) / 10.0) + "/5");

        pageTxt = (TextView) findViewById(R.id.pageTxt);
        pageTxt.setText("Page: "+book.getPage());

        isbnTxt = (TextView) findViewById(R.id.isbnTxt);
        isbnTxt.setText("ISBN: "+ book.getIsbn());


        ImageView imageView = (ImageView) findViewById(imageView10);
        try {
            final String imageUrl = book.getImageId();
            Bitmap bitmap = MemoryCache.IMAGE_MEMORY_CACHE.get(imageUrl);
            if (bitmap == null) {
                ImageLoaderRest imageLoaderRest = new ImageLoaderRest();
                imageLoaderRest.execute(new String[]{book.getImageId()});
                bitmap = imageLoaderRest.get();
                MemoryCache.IMAGE_MEMORY_CACHE.put(imageUrl, bitmap);
            }
            imageView.setImageBitmap(bitmap);
        } catch (final Exception e) {
            e.printStackTrace();
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    private void createButtons(final Book book){

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
                startActivity(intent);
                finish();
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
