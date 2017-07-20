package com.example.ezmilja.libraryapp;

import java.util.HashMap;
import java.util.Map;


public class RequestCache {

    public static final RequestCache CACHE = new RequestCache();

    private final Map<Integer, RequestBook> bookRequest = new HashMap<>();

    private RequestCache(){
        //bookRequest.put(0, new RequestBook("The Hobbit","Tolkien","james.milton@ericsson.com",0));

    }

    public RequestBook getBookRequest(final int id){
        return bookRequest.get(id);
    }

    public int getNumberOfBookRequests(){
        return bookRequest.size();
    }
}
