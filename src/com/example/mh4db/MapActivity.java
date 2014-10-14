package com.example.mh4db;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MapActivity extends ActionBarActivity implements OnItemClickListener {
	DBAdapter myDb;
	public final static String ID_EXTRA="com.example.mh4db._ID";
	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle drawerListener;
	private DrawerAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		myDb = new DBAdapter(this);
		myDb.open();
		
		fillDrawer();

		Mapset mapset = myDb.getMapset(1);
		byte[] byteArray = mapset.get_image();
		Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

		ImageView map = (ImageView) findViewById(R.id.image_map);
		map.setImageBitmap(bm);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
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
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
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
