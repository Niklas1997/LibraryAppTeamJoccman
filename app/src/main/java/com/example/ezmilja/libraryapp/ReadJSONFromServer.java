package com.example.ezmilja.libraryapp;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by emirmun on 21/07/2017.
 */

public class ReadJSONFromServer {
    public static JSONArray getJSON(String url) {
        HttpURLConnection con = null;
        try {
            URL u = new URL(url);
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
            return new JSONArray(String.valueOf(sb));
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

}

