package com.example.ezmilja.libraryapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private BookCache books;
    private List<Book> originalList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        try {
            books = new BookCache(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        originalList = new ArrayList<Book>();
        for (int i = 0; i < books.getNumberOfBooks(); i++) {
            originalList.add(books.getBook(i));
            System.out.println("******************" + books.getBook(i));
        }

        searchView = (SearchView) findViewById(R.id.searchbar);
        ListView listView = (ListView)findViewById(R.id.listView);

        final CustomAdapter customAdapter = new CustomAdapter(originalList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book temp = (Book) adapterView.getItemAtPosition(i);
                int id = 0;
                for (int j = 0; j < books.getNumberOfBooks(); j++) {
                    if (books.getBook(j).equals(temp)) {
                        id = j;
                        break;
                    }
                }
                PopUpBookInfo popUp = new PopUpBookInfo(id, temp);
                popUp.createPopUp(ListActivity.this, BookInfoActivity.class, "");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                customAdapter.getFilter().filter(s);
                return false;
            }
        });

    }

        private void sortlist(List list){
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o, Object t1) {
                Book b1 = (Book) o;
                Book b2 = (Book) t1;
                return b1.getBookName().compareTo(b2.getBookName());
            }
        });
    }

    class CustomAdapter extends BaseAdapter implements Filterable{

        List<Book> shownList;
        BookFilter bookFilter;

        public CustomAdapter(List<Book> listOfBooks) {
            shownList = listOfBooks;
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
            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageView imageView = view.findViewById(R.id.imageView2);
            TextView textView_bookName = view.findViewById(R.id.tbx_bookName);
            TextView textView_author = view.findViewById(R.id.textView_author);

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
                textView_bookName.setText(book.getBookName());
                textView_author.setText(book.getAuthor());
            } else {
                textView_bookName.setText("Error");
                textView_author.setText("Error");
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
                        if (b.getBookName().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
                            nBookList.add(b);
                        } else if (b.getAuthor().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
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
