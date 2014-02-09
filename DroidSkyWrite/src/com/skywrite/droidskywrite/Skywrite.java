package com.skywrite.droidskywrite;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class Skywrite extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skywrite);

		ListView listView1 = (ListView) findViewById(R.id.listView1);
		
		ParseQueryAdapter<ParseObject> adapter1 = new ParseQueryAdapter<ParseObject>(this, "clouds");
		adapter1.setTextKey("text");
		
		listView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				System.out.println("You pressed an item.");
				Intent intent = new Intent(parent.getContext(), CloudDetail.class);
				ListView listView = (ListView) parent;
				ParseObject po = (ParseObject) listView.getItemAtPosition(position);
				ArrayList<String> ar = new ArrayList<String>();
				ar.add(po.getString("user"));
				ar.add(po.getString("text"));
				Integer cum = po.getInt("cumulus");
				ar.add(cum.toString());
				ar.add(po.getObjectId());
				intent.putStringArrayListExtra("po", ar);
				startActivity(intent);
			}
		});
		
		

		listView1.setAdapter(adapter1);
		
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
		getMenuInflater().inflate(R.menu.skywrite, menu);
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
	
	public void makeCloud(View view) {
		Intent intent = new Intent(this, CloudMaker.class);
		startActivity(intent);
	}
	
	public void refreshPage(View view) {
		Intent intent = new Intent(this, Skywrite.class);
		startActivity(intent);
	}
	
}
