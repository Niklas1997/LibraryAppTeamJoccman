package com.example.ezmilja.libraryapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardList extends AppCompatActivity {

    private RequestDbHelper requestDbHelper;
    private Cursor cursor;
    private Button buttonRequest;
    private ListView listView;
    private List<RequestBook> originalList;
    private Button btn_more;
    private SearchView searchView;
    private LeaderboardList.CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_list);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        requestDbHelper = new RequestDbHelper(LeaderboardList.this);
        cursor = requestDbHelper.getAllData();

        originalList = new ArrayList<RequestBook>();

        while (cursor.moveToNext()) {
            String t_name = cursor.getString(1);
            String t_author = cursor.getString(2);
            String t_email = cursor.getString(3);
            String t_vote = cursor.getString(4);
            String id = cursor.getString(0);
            originalList.add( new RequestBook( t_name, t_author, t_email, Integer.parseInt(t_vote), Integer.parseInt(id)));
        }

        createButton();
        makeListView();

        searchView = (SearchView) findViewById(R.id.jeffdasearchbar);
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
                RequestBook b1 = (RequestBook) o;
                RequestBook b2 = (RequestBook) t1;
                return b1.compareTo(b2);
            }
        });
    }
    private void makeListView(){

        listView = (ListView) findViewById(R.id.leaderbd_list);

        customAdapter = new LeaderboardList.CustomAdapter(LeaderboardList.this, originalList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                Toast.makeText(LeaderboardList.this, "Goodbye Dave! Hello Steve!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createButton(){

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");
        buttonRequest = (Button) findViewById(R.id.buttonRequest);
        buttonRequest.setTypeface(myTypeFace1);

        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequestDialog();
            }
        });
    }

    private void makeRequestDialog(){
        final Dialog dialog = new Dialog(LeaderboardList.this);
        dialog.setContentView(R.layout.request);
        dialog.show();

        Typeface myTypeFace1 = Typeface.createFromAsset(getAssets(),"yourfont.ttf");

        Button btn_submitRequest = (Button) dialog.findViewById(R.id.btn_submitrequest);
        Button btn_back = (Button)dialog.findViewById(R.id.btn_back);
        btn_submitRequest.setTypeface(myTypeFace1);
        btn_back.setTypeface(myTypeFace1);

        final EditText edt_name = dialog.findViewById(R.id.name);
        final EditText edt_author = dialog.findViewById(R.id.reason);
        final EditText edt_email = dialog.findViewById(R.id.email);

        btn_submitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_name = edt_name.getText().toString();
                String temp_author = edt_author.getText().toString();
                String temp_email = edt_email.getText().toString();
                if (requestCheck(temp_name, temp_author, temp_email)) {
                    RequestBook temp = new RequestBook(temp_name, temp_author, temp_email, 0, requestDbHelper.getAllData().getCount() + 1);
                    originalList.add(temp);
                    requestDbHelper.insertData(temp.getBookName(), temp.getAuthor(), temp.getEmail(), temp.getVote() + "");
                    makeListView();

                    dialog.dismiss();
                }
                else {
                    Toast.makeText(LeaderboardList.this, "Error: Please input correctly", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private boolean requestCheck(String name, String author, String email){
        if (name.length() == 0 || author.length() == 0){
            return false;
        }
        if (email.toLowerCase().endsWith("@ericsson.com")){
            return true;
        }
        return false;
    }

    class CustomAdapter extends BaseAdapter implements Filterable {

        BookFilter bookFilter;
        Context context;
        List <RequestBook> showList;

        public CustomAdapter(Context context,List <RequestBook> items){
            this.context = context;
            this.showList = items;
            sortlist(showList);
        }

        private class ViewHolder{
            TextView bookName;
            TextView bookVote;
            ImageView image;
            boolean upVote;
            Button btn_more;
        }
        @Override
        public int getCount() {
            return showList.size();
        }

        @Override
        public Object getItem(int position) {
            return showList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return showList.get(position).hashCode();
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {


            View vi = view;
            final ViewHolder holder ;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final RequestBook myBook = showList.get(position);

            if(view==null){

                vi = inflater.inflate(R.layout.leaderboard_layout,null);
                holder = new ViewHolder();
                holder.bookName= (TextView) vi.findViewById(R.id.tbx_bookName);
                holder.bookVote = (TextView) vi.findViewById(R.id.tbx_voteCount);
                holder.image = (ImageView) vi.findViewById(R.id.ibnt_vote);
                holder.image.setTag(position);
                holder.btn_more = (Button) vi.findViewById(R.id.btn_more);
                vi.setTag(holder);
            }
            else{
                holder = (ViewHolder) vi.getTag();
            }

            holder.bookName.setText(myBook.getBookName());
            holder.bookVote.setText(myBook.getVote()+ "");

            if(myBook.getisUpVoted()){
                holder.image.setImageResource(R.drawable.steve);
            }
            else{
                holder.image.setImageResource(R.drawable.dave);
            }



            holder.btn_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(LeaderboardList.this)
                            .setTitle(myBook.getBookName())
                            .setMessage("Author: " + "\n" + myBook.getAuthor() + "\n" + "\n" + "Originally requested by: "
                                    + "\n" + myBook.getEmail()).setNeutralButton("Close", null).show();
                }
            });



            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myBook.getisUpVoted()) {
                        myBook.setisUpVoted(false);
                        holder.image.setImageResource(R.drawable.dave);
                        myBook.addVote(-1);
                        holder.bookVote.setText(myBook.getVote()+ "");
                        requestDbHelper.updateData(myBook.getId() + "", myBook.getBookName(), myBook.getAuthor(),myBook.getEmail(), myBook.getVote() + "");
                    }
                    else {
                        myBook.setisUpVoted(true);
                        holder.image.setImageResource(R.drawable.steve);
                        myBook.addVote(1);
                        holder.bookVote.setText( myBook.getVote() + "");
                        requestDbHelper.updateData(myBook.getId() + "", myBook.getBookName(), myBook.getAuthor(),myBook.getEmail(), myBook.getVote() + "");
                    }

                }
            });

            return vi;
        }


        @Override
        public Filter getFilter() {
            if (bookFilter == null)
                bookFilter = new BookFilter();
            return bookFilter;
        }

        class BookFilter extends Filter{

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // We implement here the filter logic
                if (constraint == null || constraint.length() < 1) {
                    // No filter implemented we return all the list
                    showList = originalList;
                    results.values = showList;
                    results.count = showList.size();
                }
                else {
                    // We perform filtering operation
                    List<RequestBook> nBookList = new ArrayList<RequestBook>();

                    for (RequestBook b : originalList) {
                        if (b.getBookName().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
                            nBookList.add(b);
                        }
                        else if (b.getAuthor().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
                            nBookList.add(b);

                        }
                    }
                    showList = nBookList;
                    results.values = nBookList;
                    results.count = nBookList.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                // Now we have to inform the adapter about the new list filtered
                if(results.count ==0) {
                    notifyDataSetInvalidated();
                }
                else
                {
                    showList = (List<RequestBook>) results.values;
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



