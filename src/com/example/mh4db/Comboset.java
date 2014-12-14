package com.example.mh4db;

public class Comboset {

	// private variables
	private int _iid;
	private int _iid2;
	private int _result;
	private String _prob;
	private String _qty;
	private String _namea;
	private String _nameb;
	private byte[] _imagea;
	private byte[] _imageb;
	private boolean _header;
	private boolean _make;
	
	// Empty constructor
	public Comboset() {

	}

	public Comboset(int _iid, int _iid2, int _result, String _prob, String _qty) {
		super();
		this._iid = _iid;
		this._iid2 = _iid2;
		this._result = _result;
		this._prob = _prob;
		this._qty = _qty;
	}

	public boolean is_make() {
		return _make;
	}

	public void set_make(boolean _make) {
		this._make = _make;
	}
	public boolean is_header() {
		return _header;
	}

	public void set_header(boolean _header) {
		this._header = _header;
	}

	public String get_namea() {
		return _namea;
	}

	public void set_namea(String _namea) {
		this._namea = _namea;
	}

	public String get_nameb() {
		return _nameb;
	}

	public void set_nameb(String _nameb) {
		this._nameb = _nameb;
	}

	public byte[] get_imagea() {
		return _imagea;
	}

	public void set_imagea(byte[] _imagea) {
		this._imagea = _imagea;
	}

	public byte[] get_imageb() {
		return _imageb;
	}

	public void set_imageb(byte[] _imageb) {
		this._imageb = _imageb;
	}

	public int get_iid() {
		return _iid;
	}

	public void set_iid(int _iid) {
		this._iid = _iid;
	}

	public int get_iid2() {
		return _iid2;
	}

	public void set_iid2(int _iid2) {
		this._iid2 = _iid2;
	}

	public int get_result() {
		return _result;
	}

	public void set_result(int _result) {
		this._result = _result;
	}

	public String get_prob() {
		return _prob;
	}

	public void set_prob(String _prob) {
		this._prob = _prob;
	}

	public String get_qty() {
		return _qty;
	}

	public void set_qty(String _qty) {
		this._qty = _qty;
	}

}