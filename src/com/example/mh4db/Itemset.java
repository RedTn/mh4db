package com.example.mh4db;

public class Itemset {
	
	// private variables
		int _id;
		byte[] _image;
		String _name;
		String _rare;
		String _qty;
		String _buy;
		String _sell;

		// Empty constructor
		public Itemset() {

		}

		public Itemset(int _id, byte[] _image, String _name, String _rare,
				String _qty, String _buy, String _sell) {
			super();
			this._id = _id;
			this._image = _image;
			this._name = _name;
			this._rare = _rare;
			this._qty = _qty;
			this._buy = _buy;
			this._sell = _sell;
		}
		
		public Itemset(byte[] image, String name) {
			this._image = image;
			this._name = name;
		}

		public int get_id() {
			return _id;
		}

		public void set_id(int _id) {
			this._id = _id;
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

		public String get_rare() {
			return _rare;
		}

		public void set_rare(String _rare) {
			this._rare = _rare;
		}

		public String get_qty() {
			return _qty;
		}

		public void set_qty(String _qty) {
			this._qty = _qty;
		}
		
		public String get_buy() {
			return _buy;
		}

		public void set_buy(String _buy) {
			this._buy = _buy;
		}

		public String get_sell() {
			return _sell;
		}

		public void set_sell(String _sell) {
			this._sell = _sell;
		}
		
}