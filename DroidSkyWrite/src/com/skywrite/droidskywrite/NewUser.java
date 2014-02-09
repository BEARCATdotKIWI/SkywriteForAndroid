package com.skywrite.droidskywrite;

import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class NewUser extends Activity {
	public final static String EXTRA_MESSAGE = "com.skywrite.droidskywrite.MESSAGE";
	public static Boolean UserFound = true;
	public static Boolean CreationFailed = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Parse.initialize(this, "umfw2ue85hMZT76ylCTwxjxQIlWxo7Su4Hn03lm4", "FQHwMNyvEWgs2u2Er9DEVEvgbfDxgrzFKTzBqn3b");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void validateAndCreateUser(View view){
		EditText usernameText = (EditText) findViewById(R.id.edit_username);
    	EditText passText = (EditText) findViewById(R.id.edit_pass);
    	EditText passConf = (EditText) findViewById(R.id.confirm_pass);
    	
    	if ( !( passText.getText().toString().equals(passConf.getText().toString()) ) ) {
    		Intent intent = new Intent(this, NewUserFailure.class);
    		intent.putExtra(EXTRA_MESSAGE, "Passwords did not match.");
    		startActivity(intent);
    	} else if (queryForUser(usernameText)){
    		Intent intent = new Intent(this, NewUserFailure.class);
    		intent.putExtra(EXTRA_MESSAGE, "User already exists.");
    		startActivity(intent);
    	} else {
    		//Create user and go to main page
    		ParseUser user = new ParseUser();
    		user.setUsername(usernameText.getText().toString());
    		user.setPassword(passText.getText().toString());
    		 
    		user.signUpInBackground(new SignUpCallback() {
    		  public void done(ParseException e) {
    		    if (e == null) {
    		      // Hooray! Let them use the app now.
    		      NewUser.CreationFailed = false;
    		    } else {
    		      // Sign up didn't succeed. Look at the ParseException
    		      // to figure out what went wrong
    		    }
    		  }
    		});
    		// End user registration
    		if (!CreationFailed){ 
    			Intent intent = new Intent(this, MainPage.class);
    			String message = "Welcome, " + usernameText.getText().toString() + "!";
    			intent.putExtra(EXTRA_MESSAGE, message);
    			startActivity(intent);
    		} else {
    			Intent intent = new Intent(this, NewUserFailure.class);
        		intent.putExtra(EXTRA_MESSAGE, "Something went wrong while creating your account.");
        		startActivity(intent);
    		}
    	}
	}

	private boolean queryForUser(EditText usernameText) {
		// TODO Auto-generated method stub
		ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
		query.whereEqualTo("username", usernameText.getText().toString());
		query.findInBackground(new FindCallback<ParseObject>() {
			  public void done(List<ParseObject> users, ParseException e) {
			    if ((users == null) || (users.size() == 0) || (users.get(0) == null) || (users.get(0).get("username") == null)) {
			    	NewUser.UserFound = false;
			    	System.out.println("We can make a user!!!");
			    } else {
			    	NewUser.UserFound = true;
			    	System.out.println("Something is awry.");
			    	e.printStackTrace();
			    }
			  }
			});
		
		return  !UserFound;
	}

}
