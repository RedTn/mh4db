package com.example.mh4db;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.polites.android.GestureImageView;

public class MapActivity extends ActionBarActivity implements
		ActionBar.OnNavigationListener, OnItemClickListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle drawerListener;
	private DrawerAdapter myAdapter;
	public final static String ID_EXTRA="com.example.mh4db._ID";
	private int curpos = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		fillDrawer();

		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);
		String[] mapArray;
		mapArray = getResources().getStringArray(R.array.maps);  
		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
				
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, mapArray), this);
		
		/*
		 * TODO: make this display only once ever
		Toast.makeText(this, "Map is zoomable",
      		   Toast.LENGTH_LONG).show();
		
		
		Toast details = Toast.makeText(this, "Click above for details",
	      		   Toast.LENGTH_SHORT);
		details.setGravity(Gravity.TOP|Gravity.RIGHT, 0, 100);
		details.show();
		
		*/
		
		
	}

	public int getCurpos() {
		return curpos;
	}

	public void setCurpos(int curpos) {
		this.curpos = curpos;
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
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
		if(item.getItemId() == R.id.action_about){
			Intent i = new Intent(this, MapDetailActivity.class);
			i.putExtra(ID_EXTRA, getCurpos());
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		getFragmentManager()
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
		setCurpos(position+1);
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_map,
					container, false);
		
			Context context = getActivity().getApplicationContext();
			DBAdapter myDb = new DBAdapter(context);
			myDb.open();
			
			int position = getArguments().getInt(ARG_SECTION_NUMBER);
			 
			Mapset mapset = myDb.getMapset(position);
			if(mapset != null) {
			
			GestureImageView mapview = (GestureImageView) rootView.findViewById(R.id.image_map2);
			byte[] byteArray = mapset.get_image();
			Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
			mapview.setImageBitmap(bm);
			}
			else {
				Toast.makeText(context, "Whoops, I haven't added that map yet",
		          		   Toast.LENGTH_SHORT).show();
			}
			
			myDb.close();
			return rootView;
		}
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
