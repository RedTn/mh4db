package com.example.mh4db;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends FragmentActivity implements FragmentA.OnFragmentInteractionListener,FragmentB.OnFragmentInteractionListener,FragmentC.OnFragmentInteractionListener,TabListener {
	long passedVal;
	DBAdapter myDb;
	private ImageView imagepic;
	private TextView qty = null;
	private TextView rare = null;
	ActionBar actionBar;
	ViewPager viewPager;
	MyAdapter adapter;
	private FragmentTransaction mCurTransaction = null;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		
		
		myDb = new DBAdapter(this);
		myDb.open();
		

		passedVal = getIntent().getLongExtra(MonsterActivity.ID_EXTRA, -1);
		Log.i("pass", "passedVal = " + Long.toString(passedVal));
		
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
		tab1.setText("Tab 1");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2= actionBar.newTab();
		tab2.setText("Tab 2");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3= actionBar.newTab();
		tab3.setText("Tab 3");
		tab3.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		
		
		Itemset itemset = myDb.getItemset((int) passedVal);
		/*
		byte[] byteArray = itemset.get_image();
		Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
*/
		setTitle(itemset.get_name());
		myDb.close();
		bundle = new Bundle();
		bundle.putInt("passed", (int) passedVal);
		
	//	qty = (TextView) findViewById(R.id.qty);
	//	rare = (TextView) findViewById(R.id.rare);
	//	imagepic = (ImageView) findViewById(R.id.itempic);
	//	imagepic.setImageBitmap(bm);
	//	qty.setText(itemset.get_qty());
	//	rare.setText(itemset.get_rare());
	//	FragmentA fragment = new FragmentA();
	//	fragment.setText(itemset.get_qty(), itemset.get_rare());
		//FragmentA fragment_obj = (FragmentA) adapter.getRegisteredFragment(viewPager.getCurrentItem());
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
				fragment = new FragmentA();
				fragment.setArguments(bundle);
			}
			if(arg0 == 1) {
				fragment = new FragmentB();
			}
			if(arg0 == 2) {
				fragment = new FragmentC();
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


