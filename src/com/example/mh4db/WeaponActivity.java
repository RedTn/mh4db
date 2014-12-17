package com.example.mh4db;	

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class WeaponActivity extends ActionBarActivity implements OnItemClickListener {
	DBAdapter myDb;
	ArrayList<WeaponTypeset> weaponArry = new ArrayList<WeaponTypeset>();
	WeaponsetAdapter adapter;
	public final static String ID_EXTRA="com.example.mh4db._ID";
	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle drawerListener;
	private DrawerAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weapon);
		setTitle("Weapons");
		
		fillDrawer();
		
		openDB();
		
		List<WeaponTypeset> weaponSets = myDb.getAllWeaponTypes();
		for(WeaponTypeset ws : weaponSets) {
			weaponArry.add(ws);
		}
		
		adapter = new WeaponsetAdapter(this, R.layout.weapon_type_layout,
				weaponArry);

		GridView dataList = (GridView) findViewById(R.id.gridView1);
		dataList.setAdapter(adapter);
		
		dataList.setOnItemClickListener(onGridClick);

		}
		catch (Exception e) {
			Log.e("ERROR", "ERROR IN CODE: " + e.toString());

			e.printStackTrace();
		}
	}
	
	private AdapterView.OnItemClickListener onGridClick=new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, 
				View view, int position,
				long id)
		{	
			Log.i("listen", "position: " + Integer.toString(position) + " id: " + Long.toString(id));
			WeaponTypeset weaponSet = weaponArry.get((int)id);
			Intent i = new Intent(WeaponActivity.this, WeaponTypeActivity.class);
			i.putExtra(ID_EXTRA, weaponSet.get_classid());
			startActivity(i);

		}
	};
	
	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}

	private void closeDB() {
		myDb.close();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();

		closeDB();
	}

	private void fillDrawer() {
		drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);

		listView = (ListView) findViewById(R.id.drawerList);
		myAdapter = new DrawerAdapter(this);
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
		MenuItems m = MenuItems.get(position);
		switch(m) {
		case HOME: {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		}
		case MONSTERS: {
			Intent intent = new Intent(this, MonsterActivity.class);
			startActivity(intent);
			break;
		}
		case ITEMS: {
			Intent intent = new Intent(this, ItemActivity.class);
			startActivity(intent);
			break;
		}
		case MAPS: {
			Intent intent = new Intent(this, MapActivity.class);
			startActivity(intent);
			break;
		}
		case WEAPONS: {
			Intent intent = new Intent(this, WeaponActivity.class);
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
