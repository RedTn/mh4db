package com.example.mh4db;

public class WeaponTypeset {
	private int _classid;
	private String _name;
	private byte[] _image;
	
	public WeaponTypeset() {
		
	}

	public WeaponTypeset(int classid, String name, byte[] image) {
		this._classid = classid;
		this._name = name;
		this._image = image;
	}

	public int get_classid() {
		return _classid;
	}

	public void set_classid(int _classid) {
		this._classid = _classid;
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