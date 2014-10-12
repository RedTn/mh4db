package com.example.mh4db;

public class Rankset {

	// private variables
	int _mid;
	int _iid;
	byte[] _image = null;
	String _name;
	String _qty;
	String _prob;
	String _obtain;
	String _low;
	boolean _header = false;
	
	public boolean is_header() {
		return _header;
	}

	public void set_header(boolean _header) {
		this._header = _header;
	}

	
	public String get_low() {
		return _low;
	}

	public void set_low(String _low) {
		this._low = _low;
	}

	

	// Empty constructor
	public Rankset() {

	}

	// constructor
	public Rankset(int mid, int iid, byte[] image, String name, String qty, String prob, String obtain) {
		this._mid = mid;
		this._iid = iid;
		this._image = image;
		this._name = name;
		this._qty = qty;
		this._prob = prob;
		this._obtain = obtain;
	}

	// constructor
	public Rankset(String qty, String prob, String obtain) {
		this._qty = qty;
		this._prob = prob;
		this._obtain = obtain;
	}
	
	public Rankset(String name, String qty, String prob, String obtain) {
		this._name = name;
		this._qty = qty;
		this._prob = prob;
		this._obtain = obtain;
	}

	public int get_mid() {
		return _mid;
	}

	public void set_mid(int _mid) {
		this._mid = _mid;
	}

	public int get_iid() {
		return _iid;
	}

	public void set_iid(int _iid) {
		this._iid = _iid;
	}

	public String get_qty() {
		return _qty;
	}

	public void set_qty(String _qty) {
		this._qty = _qty;
	}

	public String get_prob() {
		return _prob;
	}

	public void set_prob(String _prob) {
		this._prob = _prob;
	}

	public String get_obtain() {
		return _obtain;
	}

	public void set_obtain(String _obtain) {
		this._obtain = _obtain;
	}

	public byte[] get_image() {
		return _image;
	}

	public void set_image(byte[] _image) {
		this._image = _image;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}



}


