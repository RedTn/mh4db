package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/*TODO -Fix resource leak on database open
 * -When searching, typing one character forces selection to first item in gridview
 */
public class MonsterActivity extends ActionBarActivity {

	DBAdapter myDb;
	ArrayList<Imgset> imageArry = new ArrayList<Imgset>();
	ImgsetAdapter adapter;
	public final static String ID_EXTRA="com.example.mh4db._ID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_monster);
			Log.i("ActivityCheck", "in onCreate (MonsterActivity)");

			setTitle("Monsters");

			openDB();

			// Reading all contacts from database
			List<Imgset> imgsets = myDb.getAllImgsets();
			for (Imgset is : imgsets) {
				String log = "ID:" + is.getID() + " Name: " + is.getName()
						+ " ,Image: " + is.getImage();

				// Writing Contacts to log
				Log.d("Result: ", log);
				//add contacts data in arrayList
				imageArry.add(is);

			}
			adapter = new ImgsetAdapter(this, R.layout.descript_layout,
					imageArry);
			//ListView dataList = (ListView) findViewById(R.id.list);
			GridView dataList = (GridView) findViewById(R.id.gridView1);
			dataList.setAdapter(adapter);

			dataList.setOnItemClickListener(onGridClick);
			
			/*
			EditText search = (EditText) findViewById(R.id.searchMonster);
			search.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					
					imageArry.clear();
					
					List<Imgset> imgsets = myDb.getImgsetsSearch(s.toString());
					for (Imgset is : imgsets) {
						String log = "ID:" + is.getID() + " Name: " + is.getName()
								+ " ,Image: " + is.getImage();

						// Writing Contacts to log
						Log.d("Result: ", log);
						//add contacts data in arrayList
						imageArry.add(is);

					}
					adapter = new ImgsetAdapter(MonsterActivity.this, R.layout.descript_layout,
							imageArry);
					//ListView dataList = (ListView) findViewById(R.id.list);
					GridView dataList = (GridView) findViewById(R.id.gridView1);
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
			*/
			//setupUI(findViewById(R.id.parentmonster));
		}
		catch (Exception e)
		{

			Log.e("ERROR", "ERROR IN CODE: " + e.toString());

			e.printStackTrace();
		}
	}

private AdapterView.OnItemClickListener onGridClick=new AdapterView.OnItemClickListener() {
	public void onItemClick(AdapterView<?> parent, 
			View view, int position,
			long id)
	{	
		Log.i("listen", "position: " + Integer.toString(position) + " id: " + Long.toString(id));
		Imgset imgset = imageArry.get((int)id);
		Intent i = new Intent(MonsterActivity.this, DescriptActivity.class);
		i.putExtra(ID_EXTRA, (long)imgset.getID());
		startActivity(i);

	}
};

@Override
protected void onDestroy() {
	super.onDestroy();

	closeDB();
}

private void openDB() {
	myDb = new DBAdapter(this);
	myDb.open();
}

private void closeDB() {
	myDb.close();
}

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
			imageArry.clear();
			
			List<Imgset> imgsets = myDb.getImgsetsSearch(arg0);
			for (Imgset is : imgsets) {
				String log = "ID:" + is.getID() + " Name: " + is.getName()
						+ " ,Image: " + is.getImage();

				// Writing Contacts to log
				Log.d("Result: ", log);
				//add contacts data in arrayList
				imageArry.add(is);

			}
			adapter = new ImgsetAdapter(MonsterActivity.this, R.layout.descript_layout,
					imageArry);
			//ListView dataList = (ListView) findViewById(R.id.list);
			GridView dataList = (GridView) findViewById(R.id.gridView1);
			dataList.setAdapter(adapter);
			return true;
		}
	});
    return super.onCreateOptionsMenu(menu);
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
/*
public static void hideSoftKeyboard(Activity activity) {
    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
}

public void setupUI(View view) {

    //Set up touch listener for non-text box views to hide keyboard.
    if(!(view instanceof EditText)) {

        view.setOnTouchListener(new OnTouchListener() {

        	@Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(MonsterActivity.this);
                return true;
            }

        });
    }

    //If a layout container, iterate over children and seed recursion.
    if (view instanceof ViewGroup) {

        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

            View innerView = ((ViewGroup) view).getChildAt(i);

            setupUI(innerView);
        }
    }
}
*/
@Override
protected void onResume() {
	super.onResume();
	Log.i("ActivityCheck", "in onResume (MonsterActivity)");
}
@Override
protected void onPause() {
	super.onPause();
	Log.i("ActivityCheck", "in onPause (MonsterActivity)");
}
@Override
protected void onStop() {
	super.onStop();
	Log.i("ActivityCheck", "in onStop (MonsterActivity)");
}
}
