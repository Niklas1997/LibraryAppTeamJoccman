package com.example.ezmilja.libraryapp;

import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestCache {

    public static final RequestCache CACHE = new RequestCache();

    private final Map<Integer, RequestBook> bookRequest = new HashMap<>();

    public List<RequestBook> getRequestJson() {
        return requestJson;
    }

    private List<RequestBook> requestJson;

    public RequestCache(){
        RestInterface restInterface = new RestInterface();
        restInterface.execute("http://159.107.165.84:8000/RequestBook/");
        try {
            JSONArray jsonArray = restInterface.get();
            requestJson = JsonToRequestConverter.convertJSON(jsonArray);
            for (int i=0; i < requestJson.size(); i++) {
                bookRequest.put(i, requestJson.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public RequestBook getBookRequest(final int id){
        return bookRequest.get(id);
    }

    public int getNumberOfBookRequests(){
        return bookRequest.size();
    }
}
