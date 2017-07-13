package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import static com.example.ezmilja.libraryapp.R.array.Author;

public class ListActivity extends AppCompatActivity {

    private Button btn_back;
    private ListView listView;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createListView();

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

    private void createListView(){



        final String[] bookNames = getResources().getStringArray(R.array.bookName);
        final String[] authors = getResources().getStringArray(R.array.Author);
        listView = (ListView) findViewById(R.id.listView);

        searchView = (SearchView) findViewById(R.id.searchView);

        String[] temp = new String[bookNames.length];
        for (int i = 0; i < bookNames.length; i++){
            temp[i] = bookNames[i] + " /n/split/a/ " + authors[i];
        }

        final CustomList listAdapter = new CustomList(ListActivity.this, temp, bookNames, authors);

        listView.setAdapter(listAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String bookName = bookNames[i];
                String author = authors[i];


                Intent intent = new Intent(ListActivity.this, BookInfoActivity.class);
                intent.putExtra("bookName", bookName);
                intent.putExtra("author", author);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listAdapter.getFilter().filter(s);
                return false;
            }


        });
    }
}
