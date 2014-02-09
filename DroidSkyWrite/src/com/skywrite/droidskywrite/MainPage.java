package com.skywrite.droidskywrite;

import com.parse.Parse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class MainPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Parse.initialize(this, "umfw2ue85hMZT76ylCTwxjxQIlWxo7Su4Hn03lm4", "FQHwMNyvEWgs2u2Er9DEVEvgbfDxgrzFKTzBqn3b");
		super.onCreate(savedInstanceState);
		
	    // Get the message from the intent
	   // Intent intent = getIntent();
	    //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    //TextView textView = new TextView(this);
	    //textView.setTextSize(40);
	    //textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(R.layout.activity_main_page);
	    
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
		getMenuInflater().inflate(R.menu.main_page, menu);
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
	
	public void proceedToSkywrite(View view){
		Intent intent = new Intent(this, Skywrite.class);
		startActivity(intent);
	}

}
