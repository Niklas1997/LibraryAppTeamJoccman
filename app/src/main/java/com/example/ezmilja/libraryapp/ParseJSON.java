package com.example.ezmilja.libraryapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


/**
 * Created by emirmun on 20/07/2017.
 */



public class ParseJSON {


    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\emirmun\\Documents\\GitHub\\LibraryAppTeamJoccman\\app\\src\\main\\res\\raw\\books.json"));

        for (int i = 0; i < a.length(); i++) {
            try {


                JSONObject o = new JSONObject();
                JSONObject book = (JSONObject) o;

                int imageId = (int) book.get("imageId");
                System.out.println(imageId);

                String isbn = (String) book.get("isbn");
                System.out.println(isbn);

                String bookName = (String) book.get("bookName");
                System.out.println(bookName);

                String author = (String) book.get("author");
                System.out.println(author);

                String description = (String) book.get("description");
                System.out.println(description);

                String page = (String) book.get("page");
                System.out.println(page);

                String publisher = (String) book.get("publisher");
                System.out.println(publisher);

                int rating = (int) book.get("rating");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }}
