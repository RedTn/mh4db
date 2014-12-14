package com.example.mh4db;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class ItemDetailActivity extends FragmentActivity implements FragmentMonster.OnFragmentInteractionListener,FragmentCombo.OnFragmentInteractionListener,FragmentMap.OnFragmentInteractionListener,TabListener {
	int passedVal;
	DBAdapter myDb;
	ActionBar actionBar;
	ViewPager viewPager;
	MyAdapter adapter;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		
		
		myDb = new DBAdapter(this);
		myDb.open();
		

		passedVal = getIntent().getIntExtra(MonsterActivity.ID_EXTRA, -1);
		Log.i("pass", "passedVal = " + Integer.toString(passedVal));
		
		adapter = new MyAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(adapter);
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		actionBar=getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab tab1= actionBar.newTab();
		tab1.setText("Monster");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2= actionBar.newTab();
		tab2.setText("Combo");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3= actionBar.newTab();
		tab3.setText("Location");
		tab3.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		
		
		Itemset itemset = myDb.getItemset(passedVal);

		setTitle(itemset.get_name());
		myDb.close();
		bundle = new Bundle();
		bundle.putInt("passed", passedVal);
		
	}
	
	class MyAdapter extends FragmentPagerAdapter {
		 SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment fragment = null;
			if(arg0 == 0) {
				fragment = new FragmentMonster();
				fragment.setArguments(bundle);
			}
			if(arg0 == 1) {
				fragment = new FragmentCombo();
				fragment.setArguments(bundle);
			}
			if(arg0 == 2) {
				fragment = new FragmentMap();
				fragment.setArguments(bundle);
			}
			return fragment;
		}

		@Override
		public int getCount() {

			return 3;
		}
		
		 @Override
		    public Object instantiateItem(ViewGroup container, int position) {
		        Fragment fragment = (Fragment) super.instantiateItem(container, position);
		        registeredFragments.put(position, fragment);
		        return fragment;
		    }

		    @Override
		    public void destroyItem(ViewGroup container, int position, Object object) {
		        registeredFragments.remove(position);
		        super.destroyItem(container, position, object);
		    }
		    
		    public Fragment getRegisteredFragment(int position) {
		        return registeredFragments.get(position);
		    }
		    
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_detail, menu);
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
	public void onFragmentInteraction(Uri uri) {

	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
}


