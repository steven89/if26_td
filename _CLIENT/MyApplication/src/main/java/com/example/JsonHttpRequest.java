package com.example;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by if26 on 08/10/13.
 */
public class JsonHttpRequest extends AsyncTask<String, Void, String> {

    public String doInBackground(String... arg){


        HttpGet logRequest = new HttpGet(arg[0]);
        HttpClient logClient = new DefaultHttpClient();
        HttpResponse logRep = null;
        try{
            logRep = logClient.execute(logRequest);
            return logRep.toString();

        } catch (Exception e){
            return "ERROR";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Create here your JSONObject...
        resultat(result); // And then use the json object inside this method
    }

    public String resultat(String param){
        return param;
    }
}
