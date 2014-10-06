package com.example.databaseimage;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class DescriptActivity extends ActionBarActivity {

	DBAdapter myDb;
	long passedVal;
	private TextView title = null;
	private ImageView mainicon;
	ArrayList<Weakset> weakArry = new ArrayList<Weakset>();
	ArrayList<Rankset> rankArry = new ArrayList<Rankset>();
	
	private static final String location_header = "Part";
	private static final String Cut_header = "Cut";
	private static final String Impact_header = "Imp";
	private static final String Bullet_header = "Shot";
	private static final String Fire_header = "Fire";
	private static final String Water_header = "Water";
	private static final String Thunder_header = "Thun";
	private static final String Ice_header = "Ice";
	private static final String Dragon_header = "Drag";
	private static final String Stun_header = "Stun";
	private static final String Name_header = "Item";
	private static final String Qty_header = "Qty";
	private static final String Prob_header = "Prob";
	private static final String Obtain_header = "How to obtain";
	private static final int whitebox_id = -2;
	private static final int tableLow = 1;
	private static final int tableHigh = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.descript);
		Log.i("ActivityCheck", "onCreate (DescriptActivity)");

		myDb = new DBAdapter(this);
		myDb.open();

		passedVal = getIntent().getLongExtra(MainActivity.ID_EXTRA, -1);
		passedVal++;

		Imgset imgset = myDb.getImgset((int) passedVal);
		byte[] byteArray = imgset.getImage();
		Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

		setTitle(imgset.getName());

		title = (TextView) findViewById(R.id.titletxt);
		mainicon = (ImageView) findViewById(R.id.mainicon);
		title.setText(imgset.getName());
		mainicon.setImageBitmap(bm);

	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch(view.getId()) {
		case R.id.radioweak:
			if (checked) {
				weakArry.clear();
				
				fillHeaderWeak();
				
				List<Weakset> weaksets = myDb.getAllWeaksets();
				for (Weakset ws : weaksets) {
					weakArry.add(ws);
				}

				WeaksetAdapter adapter = new WeaksetAdapter(this, R.layout.weak_layout,
						weakArry);

				ListView dataList = (ListView) findViewById(R.id.weaklist);
				dataList.setAdapter(adapter);
			}
			break;
		case R.id.radiolow:
			if (checked) {
				rankArry.clear();
			
				fillHeaderRank();
				
			List<Rankset> ranksets = myDb.getAllRanksets((int)passedVal, tableLow);
			for (Rankset rs : ranksets) {
			Itemset itemset = myDb.getItemset(rs.get_iid());
			rs.set_image(itemset.get_image());
			rs.set_name(itemset.get_name());
				rankArry.add(rs);
			}

			RanksetAdapter adapter = new RanksetAdapter(this, R.layout.rank_layout,
					rankArry);

			ListView dataList = (ListView) findViewById(R.id.weaklist);
			dataList.setAdapter(adapter);
			}
			break;
		case R.id.radiohigh:
			if (checked) {
				rankArry.clear();
				
				fillHeaderRank();
				
			List<Rankset> ranksets = myDb.getAllRanksets((int)passedVal, tableHigh);
			for (Rankset rs : ranksets) {
			Itemset itemset = myDb.getItemset(rs.get_iid());
			rs.set_image(itemset.get_image());
			rs.set_name(itemset.get_name());
				rankArry.add(rs);
			}

			RanksetAdapter adapter = new RanksetAdapter(this, R.layout.rank_layout,
					rankArry);

			ListView dataList = (ListView) findViewById(R.id.weaklist);
			dataList.setAdapter(adapter);
			}
				break;    
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.descript, menu);
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
		Log.i("ActivityCheck", "onResume (DescriptActivity)");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("ActivityCheck", "onPause (DescriptActivity)");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("ActivityCheck", "onStop (DescriptActivity)");
	}
	
	private void fillHeaderWeak() {
		Weakset ws = new Weakset(location_header, Cut_header, Impact_header, Bullet_header, Fire_header, Water_header, 
				Thunder_header, Ice_header, Dragon_header, Stun_header);
		weakArry.add(ws);
	}
	
	private void fillHeaderRank() {
		Rankset rs = new Rankset(Name_header, Qty_header, Prob_header, Obtain_header);
		Itemset itemset = myDb.getItemset(whitebox_id);
		rs.set_image(itemset.get_image());
		rankArry.add(rs);
	}
}
