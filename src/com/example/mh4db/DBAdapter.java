package com.example.mh4db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	private static final String TAG = "DBAdapter";

	public static final String KEY_ROWID = "id";
	public static final int COL_ROWID = 0;

	public static final String KEY_NAME = "name";
	public static final String KEY_IMAGE = "image";

	public static final int COL_NAME = 1;
	public static final int COL_IMAGE = 2;

	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_IMAGE};

	private static final String DATABASE_NAME = "mh.db";	//may be different
	private static final String DATABASE_TABLE = "image";
	private static final String DATABASE_TABLE_WEAK = "weakness";
	private static final String DATABASE_TABLE_LOW = "lowrank";
	private static final String DATABASE_TABLE_HIGH = "highrank";
	private static final String DATABASE_TABLE_ITEM = "item";
	private static final String DATABASE_TABLE_EXTRA = "extra";
	private static final String DATABASE_TABLE_COMBO = "combo";
	private static final int DATABASE_VERSION = 3;

	//Shared with DescriptAtivity
	private static final int tableLow = 1;
	private static final int tableHigh = 2;

	private final Context context;    

	private DatabaseHelper DBHelp;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) 
	{
		this.context = ctx;
		DBHelp = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper 
	{
		DatabaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			/*
        	try {
        		db.execSQL(DATABASE_CREATE);	
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
			 */
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS Imgsets");
			onCreate(db);
		}
	}    


	//---opens the database---
	public DBAdapter open() throws SQLException 
	{
		db = DBHelp.getWritableDatabase();
		return this;
	}

	//---closes the database---    
	public void close() 
	{
		DBHelp.close();
	}


	//---deletes a particular record---
	public boolean deleteImgset(long rowId) 
	{
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	//---retrieves all the records---
	public Cursor getAllRecords() 
	{
		return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
				KEY_IMAGE}, null, null, null, null, null);
	}

	//---retrieves a particular record---
	public Cursor getRecord(long rowId) throws SQLException 
	{
		Cursor mCursor =
				db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
						KEY_NAME, KEY_IMAGE}, 
						KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// Getting All Imgsets
	public List<Imgset> getAllImgsets() {
		List<Imgset> imgList = new ArrayList<Imgset>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + DATABASE_TABLE  + " ORDER BY id";

		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Imgset imgset = new Imgset();
				imgset.setID(Integer.parseInt(cursor.getString(0)));
				imgset.setName(cursor.getString(1));
				imgset.setImage(cursor.getBlob(2));
				// Adding contact to list
				imgList.add(imgset);
			} while (cursor.moveToNext());
		}
		// return contact list
		return imgList;

	}

	public List<Itemset> getAllItemsets() {
		List<Itemset> itemList = new ArrayList<Itemset>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + DATABASE_TABLE_ITEM  + " ORDER BY id";

		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Itemset itemset = new Itemset(cursor.getInt(0),cursor.getBlob(1), cursor.getString(2));
				// Adding contact to list
				itemList.add(itemset);
			} while (cursor.moveToNext());
		}
		// return contact list
		return itemList;

	}

	// Searchbar Imgsets
	public List<Imgset> getImgsetsSearch(String search) {
		List<Imgset> imgList = new ArrayList<Imgset>();

		String selectQuery = "SELECT * FROM " + DATABASE_TABLE + " WHERE name LIKE '" + search + "%'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Imgset imgset = new Imgset();
				imgset.setID(Integer.parseInt(cursor.getString(0)));
				imgset.setName(cursor.getString(1));
				imgset.setImage(cursor.getBlob(2));
				// Adding contact to list
				imgList.add(imgset);
			} while (cursor.moveToNext());
		}
		// return contact list
		return imgList;
	}

	public List<Itemset> getItemsetsSearch(String search) {
		List<Itemset> itemList = new ArrayList<Itemset>();

		String selectQuery = "SELECT * FROM " + DATABASE_TABLE_ITEM + " WHERE name LIKE '" + search + "%'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Itemset itemset = new Itemset();
				itemset.set_id(cursor.getInt(0));
				itemset.set_name(cursor.getString(2));
				itemset.set_image(cursor.getBlob(1));

				itemList.add(itemset);
			} while (cursor.moveToNext());
		}

		return itemList;
	}

	// Getting All Weaksets
	public List<Weakset> getAllWeaksets(int id) {
		List<Weakset> weakList = new ArrayList<Weakset>();
		// Select All Query

		Cursor cursor = db.query(DATABASE_TABLE_WEAK, new String[] { "id",
				"bid", "location", "cut", "impact", "bullet", "fire", "water", "thunder", "ice", "dragon", "stun" }, "id" + "=?",
				new String[] { String.valueOf(id) }, null, null, "id", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Weakset weakset = new Weakset();
				weakset.set_id(cursor.getInt(0));
				weakset.set_bid(cursor.getInt(1));
				weakset.set_location(cursor.getString(2));
				weakset.set_cut(cursor.getString(3));
				weakset.set_impact(cursor.getString(4));
				weakset.set_bullet(cursor.getString(5));
				weakset.set_fire(cursor.getString(6));
				weakset.set_water(cursor.getString(7));
				weakset.set_thunder(cursor.getString(8));
				weakset.set_ice(cursor.getString(9));
				weakset.set_dragon(cursor.getString(10));
				weakset.set_stun(cursor.getString(11));
				// Adding contact to list
				weakList.add(weakset);
			} while (cursor.moveToNext());
		}
		// return contact list
		return weakList;

	}
	// Getting All Rankset
	public List<Rankset> getAllRanksets(int mid, int table) {
		List<Rankset> rankList = new ArrayList<Rankset>();
		// Select All Query
		String tablename = null;
		switch (table) {
		case tableLow: tablename = DATABASE_TABLE_LOW;
		break;
		case tableHigh: tablename = DATABASE_TABLE_HIGH;
		break;
		}

		Cursor cursor = db.query(tablename, new String[] { "mid",
				"iid", "qty", "prob", "obtain" }, "mid" + "=?",
				new String[] { String.valueOf(mid) }, null, null, "iid", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Rankset rankset = new Rankset();
				rankset.set_mid(cursor.getInt(0));
				rankset.set_iid(cursor.getInt(1));
				rankset.set_qty(cursor.getString(2));
				rankset.set_prob(cursor.getString(3));
				rankset.set_obtain(cursor.getString(4));

				// Adding contact to list
				rankList.add(rankset);
			} while (cursor.moveToNext());
		}
		// return contact list
		return rankList;

	}

	public List<Rankset> getAllRanksetsByIid(int iid, int table) {
		List<Rankset> rankList = new ArrayList<Rankset>();
		// Select All Query
		String tablename = null;
		switch (table) {
		case tableLow: tablename = DATABASE_TABLE_LOW;
		break;
		case tableHigh: tablename = DATABASE_TABLE_HIGH;
		break;
		}

		Cursor cursor = db.query(tablename, new String[] { "mid",
				"iid", "qty", "prob", "obtain" }, "iid" + "=?",
				new String[] { String.valueOf(iid) }, null, null, null, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Rankset rankset = new Rankset();
				rankset.set_mid(cursor.getInt(0));
				rankset.set_iid(cursor.getInt(1));
				rankset.set_qty(cursor.getString(2));
				rankset.set_prob(cursor.getString(3));
				rankset.set_obtain(cursor.getString(4));

				// Adding contact to list
				rankList.add(rankset);
			} while (cursor.moveToNext());
		}
		// return contact list
		return rankList;

	}

	Imgset getImgset(int id) {

		Cursor cursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_NAME, KEY_IMAGE }, KEY_ROWID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Imgset imgset = new Imgset(cursor.getInt(0),
				cursor.getString(1), cursor.getBlob(2));

		return imgset;

	}

	Itemset getItemset(int id) {
		Cursor cursor = db.query(DATABASE_TABLE_ITEM, new String[] { "id",
				"image", "name", "rare", "qty", "sell", "buy" }, "id" + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Itemset itemset = new Itemset(cursor.getInt(0),
				cursor.getBlob(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

		return itemset;
	}

	public List<Comboset> getCombosetByIid(int iid) {
		String selectQuery = "SELECT * FROM " + DATABASE_TABLE_COMBO + " WHERE iid = ? OR iid2 = ?";
		String[] args = {String.valueOf(iid), String.valueOf(iid)};
		List<Comboset> comboList = new ArrayList<Comboset>();
		
		Cursor cursor = db.rawQuery(selectQuery, args);
		if (cursor.moveToFirst()) {
			do {
				Comboset comboset = new Comboset(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4));
				comboList.add(comboset);
			} while (cursor.moveToNext());
		}


		return comboList;
	}
	
	public List<Comboset> getCombosetByResult(int result) {
		String selectQuery = "SELECT * FROM " + DATABASE_TABLE_COMBO + " WHERE result = ?";
		String[] args = {String.valueOf(result)};
		List<Comboset> comboList = new ArrayList<Comboset>();
		
		Cursor cursor = db.rawQuery(selectQuery, args);
		if (cursor.moveToFirst()) {
			do {
				Comboset comboset = new Comboset(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4));
				comboList.add(comboset);
			} while (cursor.moveToNext());
		}


		return comboList;
	}

	byte[] getExtra(int id) {
		Cursor cursor = db.query(DATABASE_TABLE_EXTRA, new String[] { "id",
		"image" }, "id" + "=?",
		new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		byte[] image = cursor.getBlob(1);

		return image;
	}


	// Getting contacts Count
	public int getImgsetCount() {
		String countQuery = "SELECT * FROM " + DATABASE_TABLE;

		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

}
