package com.example.ezmilja.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.ezmilja.libraryapp.R.array.Author;

public class ListActivity extends AppCompatActivity {

    private Button btn_back;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        createListView();
        createButton();
    }



    private void createButton(){
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void createListView(){

        final String[] bookNames = getResources().getStringArray(R.array.bookName);
        final String[] authors = getResources().getStringArray(R.array.Author);
        listView = (ListView) findViewById(R.id.listView);

        CustomList listAdapter = new CustomList(ListActivity.this, bookNames, authors);

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
    }
}
