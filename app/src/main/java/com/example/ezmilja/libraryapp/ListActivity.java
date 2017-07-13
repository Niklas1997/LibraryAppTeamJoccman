package com.example.ezmilja.libraryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {



    int[] IMAGES = {R.drawable.foresight, R.drawable.apracticalapproach, R.drawable.ado, R.drawable.advancecobra};

    String[] BOOKNAME = {"2020 ForeSight", "A Practical Approach", "ADO.NET","Advanced COBRA programming with C++ " };

    String[] DESCRPTION = {"Gary Gruver, Mike Young, Pat Fulgham", "Hugh Courtney","Bill Hamilton","Michi Henning and Steve Vinoski"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        ListView listView = (ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);



    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
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

            imageView.setImageResource(IMAGES[i]);
            textView_bookname.setText(BOOKNAME[i]);
            textView_description.setText(DESCRPTION[i]);

            return view;
        }
    }
}