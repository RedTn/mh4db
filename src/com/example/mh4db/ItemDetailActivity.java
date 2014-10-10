package com.example.mh4db;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends Activity {
	long passedVal;
	DBAdapter myDb;
	private ImageView imagepic;
	private TextView qty = null;
	private TextView rare = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		
		myDb = new DBAdapter(this);
		myDb.open();

		passedVal = getIntent().getLongExtra(MonsterActivity.ID_EXTRA, -1);
		Log.i("pass", "passedVal = " + Long.toString(passedVal));
		
		Itemset itemset = myDb.getItemset((int) passedVal);
		byte[] byteArray = itemset.get_image();
		Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

		setTitle(itemset.get_name());
		
		qty = (TextView) findViewById(R.id.qty);
		rare = (TextView) findViewById(R.id.rare);
		imagepic = (ImageView) findViewById(R.id.itempic);
		imagepic.setImageBitmap(bm);
		qty.setText(itemset.get_qty());
		rare.setText(itemset.get_rare());
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
}
