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
	ArrayList<Monsterset> monsterArry = new ArrayList<Monsterset>();
	MonstersetAdapter adapter;
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
			List<Monsterset> monstersets = myDb.getAllMonstersets();
			for (Monsterset is : monstersets) {
				String log = "ID:" + is.getID() + " Name: " + is.getName()
						+ " ,Image: " + is.getImage();

				// Writing Contacts to log
				Log.d("Result: ", log);
				//add contacts data in arrayList
				monsterArry.add(is);

			}
			adapter = new MonstersetAdapter(this, R.layout.descript_layout,
					monsterArry);
			
			GridView dataList = (GridView) findViewById(R.id.gridView1);
			dataList.setAdapter(adapter);

			dataList.setOnItemClickListener(onGridClick);
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
		Monsterset monsterset = monsterArry.get((int)id);
		Intent i = new Intent(MonsterActivity.this, DescriptActivity.class);
		i.putExtra(ID_EXTRA, (long)monsterset.getID());
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
			monsterArry.clear();
			
			List<Monsterset> monstersets = myDb.getMonstersetsSearch(arg0);
			for (Monsterset is : monstersets) {
				String log = "ID:" + is.getID() + " Name: " + is.getName()
						+ " ,Image: " + is.getImage();

				// Writing Contacts to log
				Log.d("Result: ", log);
				//add contacts data in arrayList
				monsterArry.add(is);

			}
			adapter = new MonstersetAdapter(MonsterActivity.this, R.layout.descript_layout,
					monsterArry);
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
