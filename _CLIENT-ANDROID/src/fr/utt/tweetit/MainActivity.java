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
	private TextView connexion_status;
	private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // fields
        this.email = (EditText) findViewById(R.id.text_email);
        this.passwd = (EditText) findViewById(R.id.text_pass);
        this.connexion_status = (TextView) findViewById(R.id.lab_connexion_status);
        this.connexion_status.setText("");
        this.email.setText("test1@test.fr");
        this.passwd.setText("test");
        this.btn_login = (Button) findViewById(R.id.btn_login);

        this.btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // check login
            	connexion_status.setText("Connexion...");
            	JsonHttpRequest request = new JsonHttpRequest(new JsonHttpCallback() {
					@Override
					public Object call(JSONObject jsonResponse) {
						try {
							if(!jsonResponse.getBoolean("error")){
								Intent loadAccueil = new Intent(getApplicationContext(), TweetItActivity.class);
				                loadAccueil.putExtra("token", jsonResponse.getString("token"));
				                startActivity(loadAccueil);
				                connexion_status.setText("");
							}
							else{
								connexion_status.setText("Invalid login/password");
							}
						} catch (JSONException e) {
							connexion_status.setText("Network error");
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
