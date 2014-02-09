package com.skywrite.droidskywrite;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.skywrite.droidskywrite.MESSAGE";
	public static Boolean loginSuccess = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Parse.initialize(this, "umfw2ue85hMZT76ylCTwxjxQIlWxo7Su4Hn03lm4", "FQHwMNyvEWgs2u2Er9DEVEvgbfDxgrzFKTzBqn3b");
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void doLogin(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, Skywrite.class);
    	
    	EditText usernameText = (EditText) findViewById(R.id.edit_username);
    	EditText passText = (EditText) findViewById(R.id.edit_pass);
    	
    	ParseUser.logInInBackground(usernameText.getText().toString(), passText.getText().toString(), new LogInCallback() {
    		  public void done(ParseUser user, ParseException e) {
    		    if (user != null) {
    		      // SUCCESS
    		    	MainActivity.loginSuccess = true;
    		    } else {
    		      // login failed
    		    	MainActivity.loginSuccess = false;
    		    }
    		  }
    		});
    	
    	if (loginSuccess) {
        	startActivity(intent);
    	}
    }
    
    public void doNewUser(View view) {
    	Intent intent = new Intent(this, NewUser.class);
    	startActivity(intent);
    }
    
}
