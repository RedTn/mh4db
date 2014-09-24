package com.example.databaseimage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	//we declare a bunch of useful constants
	
	//private static final String DATABASE_PATH = "/data/data/com.example.databaseimage/databases/";
	private static final String DATABASE_NAME="mh.db";
	private static final int SCHEMA_VERSION=3;
	private String DB_PATH;
	
	public SQLiteDatabase dbSqlite;
	
	private final Context myContext;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
		this.myContext = context;
		setDB_PATH(myContext.getDatabasePath(DATABASE_NAME).getPath());
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void createDatabase() {
		createDB();
	}
	
	private void createDB(){
		boolean dbExist = DBExists();
		
		if(!dbExist) {
			
			this.getReadableDatabase();
			
			copyDBFromResource();
		}
	}
	
	private boolean DBExists() {
		
		SQLiteDatabase db = null;
		
		try {
			String databasePath = getDB_PATH() + DATABASE_NAME;
			
			//READ ONLY
			db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
			
			db.setLocale(Locale.getDefault());
			//db.setLockingEnabled(true);
			db.setVersion(SCHEMA_VERSION);
		} catch (SQLiteException e) {
			Log.e("SqlHelper", "database not found");
		}
		
		if (db != null) {
			db.close();
		}
		
		return db != null ? true : false;
		
		/*
		File databaseFile = new File(getDB_PATH() + DATABASE_NAME);
	    return databaseFile.exists(); 
	    */
	}
	
	private void copyDBFromResource() {
		
		InputStream inputStream = null;
		OutputStream outStream = null;
		String dbFilePath = getDB_PATH() + DATABASE_NAME;
		
		try {
			inputStream = myContext.getAssets().open(DATABASE_NAME);
			
			outStream = new FileOutputStream(dbFilePath);
			
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			
			outStream.flush();
			outStream.close();
			inputStream.close();
		} catch (IOException e) {
			throw new Error("Problem copying database from resource file.");
		}
	}
	@Override
	public synchronized void close() { 
	    if(dbSqlite != null)
	    	dbSqlite.close(); 
	    super.close(); 
	}

	public String getDB_PATH() {
		return this.DB_PATH;
	}

	public void setDB_PATH(String dB_PATH) {
		this.DB_PATH = dB_PATH;
	}
}
