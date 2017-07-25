package com.example.ezmilja.libraryapp;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonToBookConverter {
    public static List<Book> convertJSON(JSONArray jsonBooks) throws JSONException {
        // Create a new list of Book (to fill)
        System.out.print("Started converJSON");
        List<Book> books = new ArrayList<>();


        // Go through all the books in the JSONArray
        for (int i=0; i < jsonBooks.size(); i++) {
            // Get the Individual JSON book
            JSONObject jsonBook =  (JSONObject) jsonBooks.get(i);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&convertJSON" + jsonBook);
            Book book;
            try {
                String imageId = jsonBook.get("imageId").toString(); // Get the image Id
                String isbn = jsonBook.get("isbn").toString(); // Get the isbn
                String bookName = jsonBook.get("bookName").toString(); // Get the Book Name
                String author = jsonBook.get("author").toString(); // Get the author
                String description = jsonBook.get("description").toString(); // Get the description
                String page = jsonBook.get("page").toString(); // Get the Total Pages of the book
                String publisher = jsonBook.get("publisher").toString(); // Get the Publisher Name and the Published Date




                book = new Book(imageId, isbn, bookName,author, description, page, publisher,0,0,0,0); // Create a new Book
                books.add(book);

                System.out.println(book.toString());
            }catch (Exception e){
                Log.e("JSON Processing", "Error for book: " + jsonBook.toString());
            }
        }
        return books;
    }


    }

