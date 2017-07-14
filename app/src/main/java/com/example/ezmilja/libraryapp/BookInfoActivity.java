package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookInfoActivity extends AppCompatActivity {
    private Button btn_back;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
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
