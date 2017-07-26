package com.example.ezmilja.libraryapp;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by emirmun on 20/07/2017.
 */

public class BookToJSONConverter {

    private static final String TAG = BookToJSONConverter.class.getName();

    public static final JSONObject convertBookToJSON(Book book) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", book.getId());

        jsonObject.put("isbn", book.getIsbn());
        jsonObject.put("bookName", book.getBookName());
        jsonObject.put("author", book.getAuthor());
        jsonObject.put("description", book.getDescription());
        jsonObject.put("page", book.getPage());
        jsonObject.put("publisher", book.getPublisher());
        jsonObject.put("rating", book.getRating());
        jsonObject.put("num_rating", book.getNum_rating());
        jsonObject.put("isRated", book.getIsRated());
        jsonObject.put("numberOfCopys", book.getNumberOfCopys());
        jsonObject.put("MAX_COPYS", book.getMAX_COPYS());


        System.out.println("Converted book: " + book.getBookName() + " to JSON: " + jsonObject.toString());

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
