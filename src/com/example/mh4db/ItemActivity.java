package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class ItemActivity extends Activity {
	
	DBAdapter myDb;
	ArrayList<Itemset> itemArry = new ArrayList<Itemset>();
	ItemsetAdapter adapter;
	public final static String ID_EXTRA="com.example.mh4db._ID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		Log.i("ActivityCheck", "in onCreate (ItemActivity)");

		setTitle("Items");
		
		myDb = new DBAdapter(this);
		myDb.open();
		
		List<Itemset> itemsets = myDb.getAllItemsets();
		for(Itemset is : itemsets) {
			itemArry.add(is);
		}
		
		adapter = new ItemsetAdapter(this, R.layout.item_layout,
				itemArry);

		ListView dataList = (ListView) findViewById(R.id.itemList);
		dataList.setAdapter(adapter);
		
		dataList.setOnItemClickListener(onItemListClick);
		
		EditText search = (EditText) findViewById(R.id.searchItem);
		search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				itemArry.clear();
				
				List<Itemset> itemsets = myDb.getItemsetsSearch(s.toString());
				for (Itemset is : itemsets) {
					itemArry.add(is);
				}
				adapter = new ItemsetAdapter(ItemActivity.this, R.layout.item_layout,
						itemArry);

				ListView dataList = (ListView) findViewById(R.id.itemList);
				dataList.setAdapter(adapter);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				
			}
		});
	}

	private AdapterView.OnItemClickListener onItemListClick=new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, 
				View view, int position,
				long id)
		{	
			Itemset itemset = itemArry.get((int)id);
			Intent i = new Intent(ItemActivity.this, ItemDetailActivity.class);
			i.putExtra(ID_EXTRA, (long)itemset.get_id());
			startActivity(i);

		}
	};
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
