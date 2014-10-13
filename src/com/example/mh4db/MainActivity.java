package com.example.mh4db;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {

	private DBHelper dbHelper = null;
	private DrawerLayout drawerLayout;
	private ListView listView;
	//private String[] mainmenu;
	private ActionBarDrawerToggle drawerListener;
	private MyAdapter myAdapter;

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
		myAdapter = new MyAdapter(this);
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

	public void onMonsterClick(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, MonsterActivity.class);
		startActivity(intent);
	}
	public void onItemClick(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, ItemActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		 */
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
			Intent intent = new Intent(this, MonsterActivity.class);
			startActivity(intent);
			break;
		}
		case 1: {
			Intent intent = new Intent(this, ItemActivity.class);
			startActivity(intent);
			break;
		}
		default: {
			Intent intent = new Intent(this, MonsterActivity.class);
			startActivity(intent);
			break;
		}
		}
		drawerLayout.closeDrawers();
	}

	class MyAdapter extends BaseAdapter {
		private Context context;
		String[] mainmenu;
		int[] images = {R.drawable.ic_monster, R.drawable.ic_item};
		public MyAdapter(Context context) {
			this.context = context;
			mainmenu=context.getResources().getStringArray(R.array.mainmenu);
		}
		@Override
		public int getCount() {

			return mainmenu.length;
		}

		@Override
		public Object getItem(int position) {

			return mainmenu[position];
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = null;
			if(convertView==null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row=inflater.inflate(R.layout.custom_row, parent, false);
			}
			else {
				row = convertView;
			}
			TextView title = (TextView) row.findViewById(R.id.text_drawer);
			ImageView image = (ImageView) row.findViewById(R.id.image_drawer);
			title.setText(mainmenu[position]);
			image.setImageResource(images[position]);

			return row;
		}

	}
}
