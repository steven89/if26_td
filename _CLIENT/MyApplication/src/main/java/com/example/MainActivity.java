package com.example;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;

public class MainActivity extends Activity {

    private EditText name;
    private EditText passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.txt_name);
        passwd = (EditText) findViewById(R.id.txt_passwd);
        Button button = (Button) findViewById(R.id.btn_login);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent loadAccueil = new Intent(getApplicationContext(), AccueilAct.class);
                loadAccueil.putExtra("name", name.getText());
                loadAccueil.putExtra("passwd", passwd.getText());
                startActivity(loadAccueil);
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
