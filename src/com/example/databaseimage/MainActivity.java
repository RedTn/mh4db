package com.example.databaseimage;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private DBHelper dbHelper = null;
	//private Cursor ourCursor = null;
	DBAdapter myDb;
	ArrayList<Imgset> imageArry = new ArrayList<Imgset>();
	ImgsetAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //create our database Helper
        dbHelper = new DBHelper(this);
        //we call the create right after initializing the helper, just in case
        //they have never run the app before
        dbHelper.createDatabase();
        
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
     		adapter = new ImgsetAdapter(this, R.layout.item_layout,
     				imageArry);
     		ListView dataList = (ListView) findViewById(R.id.list);
     		dataList.setAdapter(adapter);
    }
    
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
    
    private void displayText(String message) {
    
    }

    public void onClick_ClearAll(View v) {
    	displayText("Clicked clear all");
    }
    
    public void onClick_DisplayRecords(View v) {
    	displayText("Clicked display record");
    	
    	Cursor cursor = myDb.getAllRecords();
    	displayRecordSet(cursor);
    }
    
    private void displayRecordSet(Cursor cursor) {
		
		/*
	    String message = "";
		//Reset cursor to start
		if (cursor.moveToFirst()) {
			do {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			String title = cursor.getString(2);
			
			// Append data to the message
			message += "id=" + id
						+ ", name=" + name
						+ ", title=" + title
						+ "\n";
			} while(cursor.moveToNext());
		}
		//Resource leak
		cursor.close();
		
		displayText(message);
		*/
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
