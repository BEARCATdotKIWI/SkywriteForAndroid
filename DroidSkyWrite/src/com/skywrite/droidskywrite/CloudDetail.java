package com.skywrite.droidskywrite;

import java.util.ArrayList;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class CloudDetail extends Activity {
	public static String cloudId;
	public static int cumulus;
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		ArrayList<String> po = intent.getStringArrayListExtra("po");
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setBackgroundColor(Color.BLACK);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		TextView tv0 = new TextView(this);
		tv0.setTextSize(20);
	    tv0.setText("	      ");
		
		TextView tv1 = new TextView(this);
		tv1.setTextSize(20);
		tv1.setTextColor(Color.WHITE);
	    tv1.setText("	Author: " + po.get(0) + " ");
	    
	    
	    TextView tv2 = new TextView(this);
		tv2.setTextSize(20);
		tv2.setTextColor(Color.WHITE);
	    tv2.setText("	Content: " + po.get(1) + " ");
	    
		
	    TextView tv3 = new TextView(this);
		tv3.setTextSize(20);
		tv3.setTextColor(Color.WHITE);
	    tv3.setText("	Cumulus: " + po.get(2) + " ");
	    
	    Button cumulate = new Button(this);
	    cumulate.setText("Add Cumulus");
	    cumulate.setTextColor(Color.WHITE);
	    cumulate.setOnClickListener(new OnClickListener() {
	    	public void onClick(View view){
	    		cumulate(view);
	    	}
	    });
	    
	    linearLayout.addView(tv0);
		linearLayout.addView(tv1);
		linearLayout.addView(tv2);
		linearLayout.addView(tv3);
		linearLayout.addView(cumulate);
		
		
		cumulus = Integer.parseInt(po.get(2));
		cloudId = po.get(3);
		System.out.println(cloudId);
		
		setContentView(linearLayout);
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
		getMenuInflater().inflate(R.menu.sky_write_cloud, menu);
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
	
	public void cumulate(View view){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("clouds");
		 
		// Retrieve the object by id
		query.getInBackground(cloudId, new GetCallback<ParseObject>() {
		  public void done(ParseObject cloud, ParseException e) {
		    if (e == null) {
		      // Now let's update it with some new data. In this case, only cumulus
		      // will get sent to the Parse Cloud. Nothing else has changed.
		      System.out.println(cumulus);
		      System.out.println(cumulus+1);
		    	cloud.put("cumulus", cumulus+1);
		      cloud.saveInBackground();
		    } else {
		    	e.printStackTrace();
		    }
		  }
		});
		
		Intent intent = new Intent(this, Skywrite.class);
		startActivity(intent);
	}

}
