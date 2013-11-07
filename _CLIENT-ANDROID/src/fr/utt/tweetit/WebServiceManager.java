package fr.utt.tweetit;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class WebServiceManager {
	
	private static ArrayList<String> datas;
	
	public static void initSimpleData(){
		datas = new ArrayList<String>();
		datas.add("I love tweeting");
		datas.add("but it's still quite simple");
		datas.add("I just made a new Android app !");
	}
	
	public static ArrayList<String> getSimpleData(){
		return datas;
	}
	
	public static ArrayList<JSONObject> getFollowersData(){
		ArrayList<JSONObject>data = new ArrayList<JSONObject>();
		try {
			data.add(new JSONObject("{'last_name':'Calderan', 'first_name':'Julien', 'email':'julien@test.fr'}"));
			data.add(new JSONObject("{'last_name':'Salaun', 'first_name':'Steven', 'email':'steven@test.fr'}"));
			data.add(new JSONObject("{'last_name':'Perez', 'first_name':'Karina', 'email':'test2@test.fr'}"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static ArrayList<JSONObject> getFolloweesData(){
		ArrayList<JSONObject>data = new ArrayList<JSONObject>();
		try {
			data.add(new JSONObject("{'last_name':'Jacquemet', 'first_name':'Thibault', 'email':'thibault@test.fr'}"));
			data.add(new JSONObject("{'last_name':'Larue', 'first_name':'Kevin', 'email':'kevin@test.fr'}"));
			data.add(new JSONObject("{'last_name':'Philippe', 'first_name':'Pierre', 'email':'pierre@test.fr'}"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static void postNewMessage(String msg){
		datas.add(msg);
		Log.i("steven", datas.toString());
	}
}