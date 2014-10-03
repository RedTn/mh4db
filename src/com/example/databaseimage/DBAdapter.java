package com.example.databaseimage;

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

    private static final String DATABASE_NAME = "mh.dbmh.db";	//may be different
    private static final String DATABASE_TABLE = "image";
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE =
    		"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_NAME + " string not null, "
			+ KEY_IMAGE + " text not null, "
			
			// Rest  of creation:
			+ ");";
        
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
        	try {
        		db.execSQL(DATABASE_CREATE);	
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
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
    public boolean deleteContact(long rowId) 
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

}
