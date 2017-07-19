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
        bookRequest.put(0, new BookRequest("bookName","James is cool",42));
        bookRequest.put(1, new BookRequest("bookName","James is cool",420));
        bookRequest.put(2, new BookRequest("bookName","James is cool",420));
        bookRequest.put(3, new BookRequest("bookName","James is cool",420));
        bookRequest.put(4, new BookRequest("bookName","James is cool",420));
        bookRequest.put(5, new BookRequest("bookName","James is cool",420));
        bookRequest.put(6, new BookRequest("bookName","James is cool",420));
        bookRequest.put(7, new BookRequest("bookName","James is cool",420));

        bookRequest.put(8, new BookRequest("bookName","James is cool",420));
        bookRequest.put(9, new BookRequest("bookName","James is cool",420));
        bookRequest.put(10, new BookRequest("bookName","James is cool",420));
        bookRequest.put(11, new BookRequest("bookName","James is cool",420));
        bookRequest.put(12, new BookRequest("bookName","James is cool",420));
        bookRequest.put(13, new BookRequest("bookName","James is cool",420));
        bookRequest.put(14, new BookRequest("bookName","James is cool",420));
        bookRequest.put(15, new BookRequest("bookName","James is cool",420));
    }

    public BookRequest getBookRequest(final int id){
        return bookRequest.get(id);
    }

    public int getNumberOfBookRequests(){
        return bookRequest.size();
    }
}
