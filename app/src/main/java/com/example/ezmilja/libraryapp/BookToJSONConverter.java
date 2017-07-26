package com.example.ezmilja.libraryapp;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by emirmun on 20/07/2017.
 */

public class BookToJSONConverter {

    private static final String TAG = BookToJSONConverter.class.getName();

    public final JSONObject convertBookToJSON(Book book) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("id", book.getId());
        JSONObject imageId = jsonObject.put("imageId", book.getImageId());
        jsonObject.put("isbn", book.getIsbn());
        jsonObject.put("bookName", book.getBookName());
        jsonObject.put("author", book.getAuthor());
        jsonObject.put("description", book.getDescription());
        jsonObject.put("page", book.getPage());
        jsonObject.put("publisher", book.getPublisher());

        Log.d(TAG, "Converted book: " + book.getBookName() + " to JSON: " + jsonObject.toString());

        return jsonObject;
    }

    public JSONArray convertBookToJSON(List<Book> book) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for(Book b : book){
            JSONObject jsonBook = convertBookToJSON(b);
            jsonArray.put(jsonBook);
        }

        return jsonArray;
    }
}
