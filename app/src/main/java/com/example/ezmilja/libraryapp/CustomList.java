package com.example.ezmilja.libraryapp;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] book_name;
    private final String[] author;
    private final String[] books;
    //private final Integer[] imageId;
    public CustomList(Activity context, String[] books,
                      String[] book_name, String[] author ) {
        super(context, R.layout.list_single, books);
        this.books = books;
        this.context = context;
        this.book_name = book_name;
        this.author = author;
       // this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtBookName = (TextView) rowView.findViewById(R.id.txt_bookname);
        TextView txtAuthor = (TextView) rowView.findViewById(R.id.txt_author);

        String[] temp = super.getItem(position).split(" /n/split/a/ ");
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_book);

        imageView.setImageResource(R.drawable.titleimage);
        txtBookName.setText(temp[0]);
        txtAuthor.setText(temp[1]);

        //imageView.setImageResource(imageId[position]);

        return rowView;
    }
}