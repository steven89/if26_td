package fr.utt.tweetit;

import org.json.JSONObject;

public interface JsonHttpCallback {
	public Object call(JSONObject jsonResponse);
}
