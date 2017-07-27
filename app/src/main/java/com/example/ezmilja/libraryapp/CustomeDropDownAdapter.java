package com.example.ezmilja.libraryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elundni on 27/07/2017.
 */

public class CustomeDropDownAdapter extends BaseAdapter implements Filterable{
    private List<Book> originalList;
    private List<Book> shownList;
    private BookFilter bookFilter;
    public final AppCompatActivity context;

    public CustomeDropDownAdapter(AppCompatActivity context, List<Book> listOfBooks) {
        originalList = listOfBooks;
        shownList = listOfBooks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return shownList.size();
    }

    @Override
    public Object getItem(int i) {
        return shownList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = context.getLayoutInflater().inflate(R.layout.customlayout, null);

        ImageView imageView = view.findViewById(R.id.imageView2);
        TextView textView_isbn = view.findViewById(R.id.tbx_bookName);


        if (shownList.size() > i) {
            final Book book = shownList.get(i);
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
            textView_isbn.setText(book.getBookName());

        } else {
            textView_isbn.setText("Error");
        }

        return view;
    }


    @Override
    public Filter getFilter() {
        if (bookFilter == null)
            bookFilter = new BookFilter();
        return bookFilter;
    }

    class BookFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() < 1) {
                // No filter implemented we return all the list
                shownList = originalList;
                results.values = shownList;
                results.count = shownList.size();
            } else {
                // We perform filtering operation
                List<Book> nBookList = new ArrayList<Book>();

                for (Book b : originalList) {
                    if (b.getIsbn().contains(constraint.toString().toUpperCase())) {
                        nBookList.add(b);
                    }
                }
                shownList = nBookList;
                results.values = nBookList;
                results.count = nBookList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0) {
                notifyDataSetInvalidated();
            } else {
                shownList = (List<Book>) results.values;
                notifyDataSetChanged();
            }

        }
    }
}
