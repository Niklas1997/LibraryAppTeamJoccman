package com.example.ezmilja.libraryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {



    private  final List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PopUpBookInfo popUp = new PopUpBookInfo(books.get(i));
                popUp.createPopUp(ListActivity.this, BookInfoActivity.class, "");
            }
        });

        books.add(new Book("2020 ForeSight", R.drawable.foresight, "Gary Gruver, Mike Young, Pat Fulgham"));
        books.add(new Book("A Practical Approach", R.drawable.apracticalapproach, "Hugh Courtney"));
        books.add(new Book("ADO.NET", R.drawable.ado, "Bill Hamilton"));
        books.add(new Book("Advanced COBRA programming with C++ ", R.drawable.advancecobra, "Michi Henning and Steve Vinoski"));
        books.add(new Book("Agile Retrospectives", R.drawable.agileretrospectives,"Esther Derby, Diana Larsen"));
        books.add(new Book("Agile Testing",R.drawable.agiletesting, "Lisa Crispin"));
        books.add(new Book("Ant In Action",R.drawable.antinaction, "Erik Hatcher, Steve Loughran"));
        books.add(new Book("The Art of Readable Code", R.drawable.artofreadablecode, "Dustin Boswell, Trevor Foucher"));
        books.add(new Book("Bounce", R.drawable.bounce, "Matthew Syed"));
        books.add(new Book("Business Process Management is a Team sport", R.drawable.businessprocess,"Andrew Spanyi"));






    }

    public List<Book> search(final String search){
        final List<Book> result = new ArrayList<>();
        for(final Book book: books){

            if(book.getName().contains(search)){
                result.add(book);
            }
        }

        return result;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return books.size();
        }

        @Override
        public Object getItem(int i) {
            return books.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView2);
            TextView textView_bookname = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_description = (TextView)view.findViewById(R.id.textView_description);

            final Book book = books.get(i);
            imageView.setImageResource(book.getImageId());
            textView_bookname.setText(book.getName());
            textView_description.setText(book.getDescription());

            return view;
        }
    }
}