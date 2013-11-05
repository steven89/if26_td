package fr.utt.tweetit;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class TweetItActivity extends Activity {
	
	Bundle extras;
	TextView token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweet_it_acticity);
		
		extras = getIntent().getExtras();
		TextView lab_token = (TextView) findViewById(R.id.lab_token);
		lab_token.setText(extras.get("token").toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweet_it, menu);
		return true;
	}

}
