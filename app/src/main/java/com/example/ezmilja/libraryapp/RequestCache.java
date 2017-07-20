package com.example.ezmilja.libraryapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emirmun on 13/07/2017.
 */

public class RequestCache {

    public static final RequestCache CACHE = new RequestCache();

    private final Map<Integer, BookRequest> bookRequest = new HashMap<>();

    private RequestCache(){
        bookRequest.put(0, new BookRequest("The Hobbit","Tolkien",0));

    }

    public BookRequest getBookRequest(final int id){
        return bookRequest.get(id);
    }

    public int getNumberOfBookRequests(){
        return bookRequest.size();
    }
}
