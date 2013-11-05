package fr.utt.tweetit;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText email;
	private EditText passwd;
	private TextView token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // fields
        email = (EditText) findViewById(R.id.text_email);
        passwd = (EditText) findViewById(R.id.text_pass);
        email.setText("test1@test.fr");
        passwd.setText("test");
        Button button = (Button) findViewById(R.id.btn_login);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // check login
            	JsonHttpRequest request = new JsonHttpRequest(new JsonHttpCallback() {
					@Override
					public Object call(JSONObject jsonResponse) {
						
						try {
							Intent loadAccueil = new Intent(getApplicationContext(), TweetItActivity.class);
			                loadAccueil.putExtra("token", jsonResponse.getString("token"));
			                startActivity(loadAccueil);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
						return null;
					}
				});      	
            	request.execute("http://train.sandbox.eutech-ssii.com/messenger/login.php?email="+email.getText().toString()+"&password="+passwd.getText().toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
