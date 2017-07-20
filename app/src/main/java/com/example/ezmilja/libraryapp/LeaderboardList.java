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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardList extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private Button buttonRequest;
    private ListView listView;
    private List<RequestBook> originalList;
    private Button btn_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_list);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        databaseHelper = new DatabaseHelper(LeaderboardList.this);
        cursor = databaseHelper.getAllData();

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
        Button btn_back = (Button)dialog.findViewById(R.id.btn_back);
        btn_submitRequest.setTypeface(myTypeFace1);
        btn_back.setTypeface(myTypeFace1);

        final EditText edt_name = dialog.findViewById(R.id.name);
        final EditText edt_author = dialog.findViewById(R.id.reason);
        final EditText edt_email = dialog.findViewById(R.id.email);

        btn_submitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBook temp = new RequestBook(edt_name.getText().toString(),
                        edt_author.getText().toString(), edt_email.getText().toString(), 0, databaseHelper.getAllData().getCount() + 1);
                originalList.add(temp);
                databaseHelper.insertData(temp.getBookName(), temp.getAuthor(), temp.getEmail(), temp.getVote() + "");
                makeListView();

                dialog.dismiss();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

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
                        databaseHelper.updateData(myBook.getId() + "", myBook.getBookName(), myBook.getAuthor(),myBook.getEmail(), myBook.getVote() + "");
                    }
                    else {
                        myBook.setisUpVoted(true);
                        holder.image.setImageResource(R.drawable.steve);
                        myBook.addVote(1);
                        holder.bookVote.setText( myBook.getVote() + "");
                        databaseHelper.updateData(myBook.getId() + "", myBook.getBookName(), myBook.getAuthor(),myBook.getEmail(), myBook.getVote() + "");
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



