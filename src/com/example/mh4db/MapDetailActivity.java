package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MapDetailActivity extends Activity {

	public final static String ID_EXTRA="com.example.mh4db._ID";
	int passedVal;
	DBAdapter myDb;
	ArrayList<MapRankset> mapArry = new ArrayList<MapRankset>();
	ArrayList<MapRankset> mapArry2 = new ArrayList<MapRankset>();
	MapRanksetAdapter adapter;
	MapRanksetAdapter adapter2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_detail);

		passedVal = getIntent().getIntExtra(MonsterActivity.ID_EXTRA, -1);

		myDb = new DBAdapter(this);
		myDb.open();
		
		setTitle(myDb.getMapset(passedVal).get_name());

		fillHeader();

		List<MapRankset> mapsets = myDb.getAllRankMapsetByLid(passedVal, 1);
		for(MapRankset ms : mapsets) {
			Itemset itemset = myDb.getItemset(ms.get_itemid());
			ms.set_itemimage(itemset.get_image());
			ms.set_itemname(itemset.get_name());
			switch(ms.get_tid()) {
			case 0: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_gather));
			break;
			case 1: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_fish));
			break;
			case 2: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_bug));
			break;
			case 3: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_mine));
			break;
			default: ms.set_typeimage(getResources().getDrawable(R.drawable.ic_gather));
			break;
			}
			mapArry.add(ms);
		}
		
		mapsets.clear();
		
		mapsets = myDb.getAllRankMapsetByLid(passedVal, 2);
		for(MapRankset ms2 : mapsets) {
			Itemset itemset2 = myDb.getItemset(ms2.get_itemid());
			ms2.set_itemimage(itemset2.get_image());
			ms2.set_itemname(itemset2.get_name());
			switch(ms2.get_tid()) {
			case 0: ms2.set_typeimage(getResources().getDrawable(R.drawable.ic_gather));
			break;
			case 1: ms2.set_typeimage(getResources().getDrawable(R.drawable.ic_fish));
			break;
			case 2: ms2.set_typeimage(getResources().getDrawable(R.drawable.ic_bug));
			break;
			case 3: ms2.set_typeimage(getResources().getDrawable(R.drawable.ic_mine));
			break;
			default: ms2.set_typeimage(getResources().getDrawable(R.drawable.ic_gather));
			break;
			}
			mapArry2.add(ms2);
		}

		adapter = new MapRanksetAdapter(this, R.layout.map_detail_layout,
				mapArry);

		ListView dataList = (ListView) findViewById(R.id.low_map_list);
		dataList.setAdapter(adapter);
		
		adapter2 = new MapRanksetAdapter(this, R.layout.map_detail_layout,
				mapArry2);

		ListView dataList2 = (ListView) findViewById(R.id.high_map_list);
		dataList2.setAdapter(adapter2);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_detail, menu);
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

	private void fillHeader() {
		MapRankset ms = new MapRankset();
		ms.set_itemimage(myDb.getExtra(-2));
		ms.set_itemname("Item");
		ms.set_area("Area");
		ms.set_header(true);
		mapArry.add(ms);
		mapArry2.add(ms);
	}
}
