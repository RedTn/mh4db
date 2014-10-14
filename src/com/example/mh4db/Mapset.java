package com.example.mh4db;

public class Mapset {

	// private variables
	int _mapid;
	String _name;
	byte[] _image;

	// Empty constructor
	public Mapset() {

	}

	public Mapset(int _mapid, String _name, byte[] _image) {
		super();
		this._mapid = _mapid;
		this._name = _name;
		this._image = _image;
	}

	public int get_mapid() {
		return _mapid;
	}

	public void set_mapid(int _mapid) {
		this._mapid = _mapid;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public byte[] get_image() {
		return _image;
	}

	public void set_image(byte[] _image) {
		this._image = _image;
	}
	
	
}