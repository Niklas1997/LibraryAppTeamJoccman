package com.example.ezmilja.libraryapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by emirmun on 25/07/2017.
 */

public class ImageLoaderRest extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... url) {
        HttpURLConnection con = null;
        try {
            URL u = new URL(url[0]);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            return BitmapFactory.decodeStream(con.getInputStream());
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
