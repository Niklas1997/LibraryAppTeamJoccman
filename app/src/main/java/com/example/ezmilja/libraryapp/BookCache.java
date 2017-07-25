package com.example.ezmilja.libraryapp;

import android.content.Context;

import org.json.simple.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emirmun on 13/07/2017.
 */

public class BookCache {

    public static final Map<Integer, Book> books = new HashMap<>();

    public BookCache(Context context) throws IOException, JSONException {

        RestInterface restInterface = new RestInterface();
        restInterface.execute("http://159.107.166.213:8000/Book/");
        try {
            JSONArray jsonArray = restInterface.get();
            List<Book> booksJson = JsonToBookConverter.convertJSON(jsonArray);
            for (int i=0; i < booksJson.size(); i++) {
                books.put(i, booksJson.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Book getBook(final int id){
        return books.get(id);
    }

    public int getNumberOfBooks(){
        return books.size();
    }
}
