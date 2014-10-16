package com.example.mh4db;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {

	private DBHelper dbHelper = null;
	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle drawerListener;
	private DrawerAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("MH4 Database");

		//create our database Helper
		dbHelper = new DBHelper(this);
		//we call the create right after initializing the helper, just in case
		//they have never run the app before
		dbHelper.createDatabase();

		drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
		//mainmenu = getResources().getStringArray(R.array.mainmenu);
		listView = (ListView) findViewById(R.id.drawerList);
		myAdapter = new DrawerAdapter(this);
		//listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mainmenu));
		listView.setAdapter(myAdapter);
		listView.setOnItemClickListener(this);

		drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_navigation_drawer, R.string.drawer_open,
				R.string.drawer_close){

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);

			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}	

		};
		drawerLayout.setDrawerListener(drawerListener);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}



	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(drawerListener.onOptionsItemSelected(item))
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch(position) {
		case 0: {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		}
		case 1: {
			Intent intent = new Intent(this, MonsterActivity.class);
			startActivity(intent);
			break;
		}
		case 2: {
			Intent intent = new Intent(this, ItemActivity.class);
			startActivity(intent);
			break;
		}
		case 3: {
			Intent intent = new Intent(this, MapActivity.class);
			startActivity(intent);
			break;
		}
		default: {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		}
		}
		drawerLayout.closeDrawers();
		finish();
	}

}
