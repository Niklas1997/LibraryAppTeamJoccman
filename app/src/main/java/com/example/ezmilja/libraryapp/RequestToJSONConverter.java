package com.example.ezmilja.libraryapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ezobrcl on 27/07/2017.
 */

class RequestToJSONConverter {
    public static JSONObject convertRequestToJSON(RequestBook request) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("bookName", request.getBookName());
        jsonObject.put("author", request.getAuthor());
        jsonObject.put("email", request.getEmail());
        jsonObject.put("vote", request.getVote());
        jsonObject.put("isUpVoted", request.getisUpVoted());


        System.out.println("Converted book: " + request.getBookName() + " to JSON: " + jsonObject.toString());

        return jsonObject;
    }
}
