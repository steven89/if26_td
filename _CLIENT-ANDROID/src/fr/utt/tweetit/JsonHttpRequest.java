package fr.utt.tweetit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JsonHttpRequest extends AsyncTask<String, Void, String> {
	
	private JsonHttpCallback httpCallback;
	
	public JsonHttpRequest (JsonHttpCallback callback){
		this.setCallback(callback);
	}
	
	public void setCallback(JsonHttpCallback callback){
		this.httpCallback = callback;
	}
	
	protected String doInBackground(String... args) {
	    HttpGet httpRequest = new HttpGet(args[0]);
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpResponse httpResponse = null;
	    try{
	        httpResponse = httpClient.execute(httpRequest);
	        return this.readResponse(httpResponse.getEntity().getContent());
	    } catch (Exception e){
	        return "ERROR";
	    }
	}

	protected void onPostExecute(String result) {
        JSONObject httpResponse = JSONParse(result);
        this.httpCallback.call(httpResponse);
    }
	
	private JSONObject JSONParse(String jsonString){
		JSONObject jsonResponse = new JSONObject();
		try {
			jsonResponse = new JSONObject(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	private String readResponse(InputStream stream){
		String lineRead = "";
		StringBuilder stringResponse = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		try {
			while((lineRead = reader.readLine())!=null){
				stringResponse.append(lineRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringResponse.toString();
	}
	
}
