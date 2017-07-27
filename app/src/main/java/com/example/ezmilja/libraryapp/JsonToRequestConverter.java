package com.example.ezmilja.libraryapp;

import android.util.Log;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ezmilja on 27/07/2017.
 */

class JsonToRequestConverter {

    public static List<RequestBook> convertJSON(JSONArray jsonRequests) throws JSONException {
        // Create a new list of Book (to fill)
        System.out.print("Started converJSON");
        List<RequestBook> requestBooks = new ArrayList<>();


        // Go through all the books in the JSONArray
        for (int i = 0; i < jsonRequests.size(); i++) {
            // Get the Individual JSON book
            JSONObject jsonRequest = (JSONObject) jsonRequests.get(i);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&convertJSON" + jsonRequest);
            RequestBook request;
            try {



                String bookName = jsonRequest.get("bookName").toString(); // Get the image Id
                String author = jsonRequest.get("author").toString(); // Get the author
                long vote = (Long) jsonRequest.get("vote"); // get the vote no
                String email = jsonRequest.get("email").toString(); // Get the Total Pages of the book





                request = new RequestBook( bookName, author, email, vote); // Create a new Book
                requestBooks.add(request);

                System.out.println(requestBooks.toString());
            } catch (Exception e) {
                Log.e("JSON Processing", "Error for book: " + jsonRequest.toString(), e);
            }
        }
        return requestBooks;
    }
}
