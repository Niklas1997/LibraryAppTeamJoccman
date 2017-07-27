package com.example.ezmilja.libraryapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import JeffScanner.IntentIntegrator;
import JeffScanner.IntentResult;

import static com.example.ezmilja.libraryapp.R.id.imageView10;

public class CheckoutActivity extends AppCompatActivity {

    private  BookCache books;


    private ImageButton btn_info;
    private static RadioButton radioButton;
    private static Button button;
    private static RadioGroup radioGroup;
    private boolean on;
    TextView txt_name, txt_author;
    private AutoCompleteTextView acTextView;
    private ImageView image_book;
    private String[] isbn_array;

    private Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        acTextView = (AutoCompleteTextView) findViewById(R.id.dropDownTextView);
        txt_name= (TextView)findViewById(R.id.txt_name);
        txt_name.setFocusable(false);
        txt_author =(TextView)findViewById(R.id.txt_author);
        txt_author.setFocusable(false);

        image_book = (ImageView) findViewById(R.id.imgv_bookimg);

        try {
            books = new BookCache(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createButton();
        dropDownList();
        autoFill();
        createScan();
    }

    private void createScan(){
        scanBtn = (Button)findViewById(R.id.scan_button);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.scan_button){
                    IntentIntegrator scanIntegrator = new IntentIntegrator(CheckoutActivity.this);
                    scanIntegrator.initiateScan();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();


            acTextView.setText(scanContent);
            Book book = getBookFromISBN(scanContent);
            if (book != null) {
                acTextView.setText(scanContent);
                txt_name.setVisibility(View.VISIBLE);
                txt_name.setText(book.getBookName());
                txt_author.setText(book.getAuthor());
                txt_author.setVisibility(View.VISIBLE);
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
                    image_book.setImageBitmap(bitmap);
                    image_book.setVisibility(View.VISIBLE);
                } catch (final Exception e) {
                    e.printStackTrace();
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
            }

        }


        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void dropDownList(){
        int numBooks = books.getBooksJson().size();
        isbn_array = new String[numBooks];
        for (int i = 0; i < numBooks; i++){
            isbn_array[i] = books.getBooksJson().get(i).getIsbn();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, isbn_array);
        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter);
        acTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = getBookFromISBN((String) adapterView.getItemAtPosition(i));
                txt_name.setVisibility(View.VISIBLE);
                txt_name.setText(book.getBookName());
                txt_author.setText(book.getAuthor());
                txt_author.setVisibility(View.VISIBLE);
                String mDrawableName = book.getImageId();

                final String imageUrl = book.getImageId();
                Bitmap bitmap = MemoryCache.IMAGE_MEMORY_CACHE.get(imageUrl);
                if (bitmap == null) {
                    ImageLoaderRest imageLoaderRest = new ImageLoaderRest();
                    imageLoaderRest.execute(new String[]{book.getImageId()});
                    try {
                        bitmap = imageLoaderRest.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    MemoryCache.IMAGE_MEMORY_CACHE.put(imageUrl, bitmap);
                }
                image_book.setImageBitmap(bitmap);
                image_book.setVisibility(View.VISIBLE);
            }
        });
    }

    private Book getBookFromISBN(String isbn){
        for (int i = 0; i < isbn_array.length; i++){
            if (isbn_array[i].equals(isbn)) {
                return books.getBooksJson().get(i);
            }
        }
        return null;
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

                            Book tempBook = getBookFromISBN(acTextView.getText().toString());

                            if (radioButton.getText().equals("Check IN")) {
                                if (tempBook.getNumberOfCopys() < tempBook.getMAX_COPYS()) {
                                    Toast.makeText(CheckoutActivity.this, "Book Checked IN", Toast.LENGTH_SHORT).show();
                                    tempBook.addToNumberOfCopys(1);
                                   BookStatusTask task = new BookStatusTask(v.getContext());
                                   System.out.println(task.execute(tempBook));

                                    makeRatingDialog();
                                }
                                else {
                                    Toast.makeText(CheckoutActivity.this, "All copies are in library", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if (radioButton.getText().equals("Check OUT")){

                                if (tempBook == null){
                                    Toast.makeText(CheckoutActivity.this, "Book not found", Toast.LENGTH_SHORT).show();
                                }
                                else if (tempBook.getNumberOfCopys() < 1){
                                    Toast.makeText(CheckoutActivity.this, "No copies left", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(CheckoutActivity.this, "Book Checked OUT", Toast.LENGTH_SHORT).show();
                                    tempBook.addToNumberOfCopys(-1);
                                    BookStatusTask task = new BookStatusTask(v.getContext());
                                          System.out.println(task.execute(tempBook));

                                    finish();
                                }
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
            txt_name = (TextView)findViewById(R.id.txt_name);
            Bundle bundle = getIntent().getExtras();
            String message = bundle.getString("isbn");
            Book temp = getBookFromISBN(message);
            acTextView.setFocusable(false);
            acTextView.setOnClickListener(null);

            acTextView.setBackgroundColor(Color.rgb(214,216,216));

            radioButton = (RadioButton) findViewById(R.id.rbtn_out);
            radioButton.setChecked(true);

            acTextView.setText(message);
            txt_name.setText(temp.getBookName());
            txt_author.setText(temp.getAuthor());

            String mDrawableName = temp.getImageId();
            final String imageUrl = temp.getImageId();
            Bitmap bitmap = MemoryCache.IMAGE_MEMORY_CACHE.get(imageUrl);
            if (bitmap == null) {
                ImageLoaderRest imageLoaderRest = new ImageLoaderRest();
                imageLoaderRest.execute(new String[]{temp.getImageId()});
                try {
                    bitmap = imageLoaderRest.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                MemoryCache.IMAGE_MEMORY_CACHE.put(imageUrl, bitmap);
            }
            image_book.setImageBitmap(bitmap);
            image_book.setVisibility(View.VISIBLE);
            txt_name.setVisibility(View.VISIBLE);
            txt_author.setVisibility(View.VISIBLE);
            scanBtn.setVisibility(View.INVISIBLE);
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

        final Book selectedBook = getBookFromISBN(acTextView.getText().toString());

        if (selectedBook == null){
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
        bookName.setText(selectedBook.getBookName());

        TextView author = (TextView) dialog.findViewById(R.id.tbx_author);
        author.setText(selectedBook.getAuthor());

        ImageView dialogBookImg = (ImageView) dialog.findViewById(R.id.img_bookcover);
        String mDrawableName = selectedBook.getImageId();
        final String imageUrl = selectedBook.getImageId();
        Bitmap bitmap = MemoryCache.IMAGE_MEMORY_CACHE.get(imageUrl);
        if (bitmap == null) {
            ImageLoaderRest imageLoaderRest = new ImageLoaderRest();
            imageLoaderRest.execute(new String[]{selectedBook.getImageId()});
            try {
                bitmap = imageLoaderRest.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            MemoryCache.IMAGE_MEMORY_CACHE.put(imageUrl, bitmap);
        }
        dialogBookImg.setImageBitmap(bitmap);

        Button close = (Button) dialog.findViewById(R.id.close);
        close.setTypeface(myTypeFace1);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        Button submit_button = dialog.findViewById(R.id.submit_button);
        submit_button.setTypeface(myTypeFace1);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selectedBook.getIsRated()) {
                    Toast.makeText(CheckoutActivity.this, "Rating submitted", Toast.LENGTH_SHORT).show();
                    RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                    selectedBook.addRating(ratingBar.getRating());
                    BookStatusTask task = new BookStatusTask(view.getContext());
                      System.out.println(task.execute(selectedBook));

                    finish();

                }
                else {
                    Toast.makeText(CheckoutActivity.this, "You have already rated this book", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
