package com.example;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.HttpRetryException;

public class AccueilAct extends Activity {
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        extras = getIntent().getExtras();
        TextView lab_token = (TextView) findViewById(R.id.lab_token);
        JsonHttpRequest req = new JsonHttpRequest();
        //String reponse = req.execute("http://localhost:8888/if26/index.php?log="+extras.get("name").toString()+"&pass="+extras.get("passwd").toString());
        //"http://localhost:8888/if26/index.php?log="+arg[0]+"&pass="+arg[1]
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }
    
}
