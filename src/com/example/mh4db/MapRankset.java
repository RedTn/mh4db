package com.example.mh4db;

import android.graphics.drawable.Drawable;

public class MapRankset {

	// private variables
	int _itemid;
	int _mapid;
	int _tid;
	String _area;
	String _itemname;
	byte[] _itemimage;
	Drawable _typeimage;
	boolean _header = false;
	
	// Empty constructor
	public MapRankset() {

	}

	

	public MapRankset(int _itemid, int _mapid, int _tid, String _area) {
		super();
		this._itemid = _itemid;
		this._mapid = _mapid;
		this._tid = _tid;
		this._area = _area;
	}



	public int get_tid() {
		return _tid;
	}


	public void set_tid(int _tid) {
		this._tid = _tid;
	}


	public String get_area() {
		return _area;
	}


	public void set_area(String _area) {
		this._area = _area;
	}


	public boolean is_header() {
		return _header;
	}


	public void set_header(boolean _header) {
		this._header = _header;
	}


	public int get_itemid() {
		return _itemid;
	}


	public void set_itemid(int _itemid) {
		this._itemid = _itemid;
	}


	public int get_mapid() {
		return _mapid;
	}


	public void set_mapid(int _mapid) {
		this._mapid = _mapid;
	}

	public String get_itemname() {
		return _itemname;
	}


	public void set_itemname(String _itemname) {
		this._itemname = _itemname;
	}


	public byte[] get_itemimage() {
		return _itemimage;
	}


	public void set_itemimage(byte[] _itemimage) {
		this._itemimage = _itemimage;
	}



	public Drawable get_typeimage() {
		return _typeimage;
	}



	public void set_typeimage(Drawable _typeimage) {
		this._typeimage = _typeimage;
	}

	
}