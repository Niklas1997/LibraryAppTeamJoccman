package com.example.ezmilja.libraryapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardList extends AppCompatActivity {

    private final RequestCache books = RequestCache.CACHE;
    private Button buttonRequest;
    private ListView listView;
    private List<BookRequest> originalList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_list);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        originalList = new ArrayList<BookRequest>();
        for (int i = 0; i < books.getNumberOfBookRequests(); i++){
            originalList.add(books.getBookRequest(i));
        }

        createButton();
        makeListView();



    }

    private void makeListView(){

        listView = (ListView) findViewById(R.id.leaderbd_list);

        LeaderboardList.CustomAdapter customAdapter = new LeaderboardList.CustomAdapter(LeaderboardList.this, originalList);
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
//        Button btn_back = (Button)dialog.findViewById(R.id.btn_back);
        btn_submitRequest.setTypeface(myTypeFace1);
//        btn_back.setTypeface(myTypeFace1);

        btn_submitRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
    }

    class CustomAdapter extends BaseAdapter {

        Context context;
        List <BookRequest> showList;

        public CustomAdapter(Context context,List <BookRequest> items){
            this.context = context;
            this.showList = items;
        }

        private class ViewHolder{
            TextView bookName;
            TextView bookVote;
            ImageView image;
            boolean upVote;
        }
        @Override
        public int getCount() {
            return books.getNumberOfBookRequests();
        }

        @Override
        public Object getItem(int position) {
            return books.getBookRequest(position);
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

            SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            final BookRequest myBook = showList.get(position);

            if(view==null){

                vi = inflater.inflate(R.layout.leaderboard_layout,null);
                holder = new ViewHolder();
                holder.bookName= (TextView) vi.findViewById(R.id.tbx_bookName);
                holder.bookVote = (TextView) vi.findViewById(R.id.tbx_voteCount);
                holder.image = (ImageView) vi.findViewById(R.id.ibnt_vote);
                holder.image.setTag(position);

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


            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myBook.getisUpVoted()) {
                        myBook.setisUpVoted(false);
                        holder.image.setImageResource(R.drawable.dave);
                        myBook.addVote(-1);
                        holder.bookVote.setText(myBook.getVote()+ "");
                    }
                    else {
                        myBook.setisUpVoted(true);
                        holder.image.setImageResource(R.drawable.steve);
                        myBook.addVote(1);
                        holder.bookVote.setText( myBook.getVote() + "");
                    }

                }
            });

            return vi;
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



