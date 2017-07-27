package com.example.ezmilja.libraryapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by emirmun on 27/07/2017.
 */

public class RequestBookStatusTask extends AsyncTask<Object, Object, String> {
    private ProgressDialog pDialog;
    private Context context;
    private static final String TAG = BookStatusTask.class.getName();

    public RequestBookStatusTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Putting Data ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

    }

    @Override
    protected String doInBackground(Object... params) {
        try{
            Book book = (Book) params[0];
            String u = "http://159.107.165.157:8000/RequestBook" + "/" + book.getId() + "/";
            URL url = new URL(u); // here is your URL path

            JSONObject jsonObject = BookToJSONConverter.convertBookToJSON(book);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            Log.d(TAG, "Sending to server: " + jsonObject.toString());
            writer.write(jsonObject.toString());

            writer.flush();
            writer.close();
            os.close();

            String msg = conn.getResponseMessage();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            }
            else {
                Log.e(TAG, "Error response from server: "+responseCode);
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            Log.e(TAG, "Error message from server: " + e.getMessage());
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {
        pDialog.dismiss();
        Log.d(TAG, "Response from server: " + result);
        // refresh any list
    }
}



