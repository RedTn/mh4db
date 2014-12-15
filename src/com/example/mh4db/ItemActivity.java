package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ItemActivity extends ActionBarActivity implements OnItemClickListener {

	DBAdapter myDb;
	ArrayList<Itemset> itemArry = new ArrayList<Itemset>();
	ItemsetAdapter adapter;
	public final static String ID_EXTRA="com.example.mh4db._ID";
	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle drawerListener;
	private DrawerAdapter myAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		Log.i("ActivityCheck", "in onCreate (ItemActivity)");

		setTitle("Items");

		myDb = new DBAdapter(this);
		myDb.open();
		
		fillDrawer();

		List<Itemset> itemsets = myDb.getAllItemsets();
		for(Itemset is : itemsets) {
			itemArry.add(is);
		}

		adapter = new ItemsetAdapter(this, R.layout.item_layout,
				itemArry);

		ListView dataList = (ListView) findViewById(R.id.itemList);
		dataList.setAdapter(adapter);

		dataList.setOnItemClickListener(onItemListClick);

	}

	private AdapterView.OnItemClickListener onItemListClick=new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, 
				View view, int position,
				long id)
		{	
			Itemset itemset = itemArry.get((int)id);
			Intent i = new Intent(ItemActivity.this, ItemDetailActivity.class);
			i.putExtra(ID_EXTRA, itemset.get_id());
			startActivity(i);

		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.monster_activity_actions, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String arg0) {

				return true;
			}

			@Override
			public boolean onQueryTextChange(String arg0) {
				itemArry.clear();

				List<Itemset> itemsets = myDb.getItemsetsSearch(arg0);
				for (Itemset is : itemsets) {
					itemArry.add(is);
				}
				adapter = new ItemsetAdapter(ItemActivity.this, R.layout.item_layout,
						itemArry);

				ListView dataList = (ListView) findViewById(R.id.itemList);
				dataList.setAdapter(adapter);
				return true;
			}
		});
		return super.onCreateOptionsMenu(menu);
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
		default: {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		}
		}
		drawerLayout.closeDrawers();
		finish();
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


}
