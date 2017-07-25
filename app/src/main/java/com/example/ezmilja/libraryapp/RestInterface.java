package com.example.ezmilja.libraryapp;

import android.os.AsyncTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by emirmun on 24/07/2017.
 */

public class RestInterface extends AsyncTask<String, Void, JSONArray> {

    @Override
    protected JSONArray doInBackground(String... url) {
        HttpURLConnection con = null;
        try {
            URL u = new URL(url[0]);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            // Parse raw String to JSON and return
            JSONParser jsonParser = new JSONParser();
            JSONObject book = (JSONObject) jsonParser.parse(sb.toString());

            JSONArray jsonArray = (JSONArray)book.get("results");
            return jsonArray;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }


    public static void main(String[]args){
        HttpURLConnection con = null;
        try {
            URL u = new URL("http://127.0.0.1:8000/Book");
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            // Parse raw String to JSON and return
            JSONParser jsonParser = new JSONParser();
            JSONObject book = (JSONObject) jsonParser.parse(sb.toString());

            JSONArray jsonArray = (JSONArray)book.get("results");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}


