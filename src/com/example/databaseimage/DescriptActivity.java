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
			List<Rankset> ranksets = myDb.getAllRanksets((int)passedVal);
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
			if (checked)

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
}
