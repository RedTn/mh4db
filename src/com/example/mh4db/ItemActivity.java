package com.example.mh4db;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class ItemActivity extends Activity {
	
	DBAdapter myDb;
	ArrayList<Itemset> itemArry = new ArrayList<Itemset>();
	//ItemsetAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		Log.i("ActivityCheck", "in onCreate (ItemActivity)");

		setTitle("Items");
		
		myDb = new DBAdapter(this);
		myDb.open();
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("ActivityCheck", "in onResume (ItemActivity)");
	}
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("ActivityCheck", "in onPause (ItemActivity)");
	}
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("ActivityCheck", "in onStop (ItemActivity)");
	}
}
