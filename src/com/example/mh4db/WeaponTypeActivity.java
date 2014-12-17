package com.example.mh4db;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class WeaponTypeActivity extends ActionBarActivity {
	public final static String ID_EXTRA="com.example.mh4db._ID";
	int passedVal;
	DBAdapter myDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weapon_type);

		passedVal = getIntent().getIntExtra(WeaponActivity.ID_EXTRA, -1);

		myDb = new DBAdapter(this);
		myDb.open();

		String name = myDb.getWeaponTypeNameById(passedVal);
		if(name != null) {
			setTitle(name);
		}

	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.descript, menu);	//TODO: change
		return true;
	}
	 */

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

}
