package com.example.mh4db;

public class Weakset {

	// private variables
	int _id;
	int _bid;
	String _location;
	String _cut;
	String _impact;
	String _bullet;
	String _fire;
	String _water;
	String _thunder;
	String _ice;
	String _dragon;
	String _stun;

	// Empty constructor
	public Weakset() {

	}

	// constructor
	public Weakset(int keyId, int bid, String location, String cut, String impact, String bullet, String fire, String water, String thunder,
			String ice, String dragon, String stun) {
		this._id = keyId;
		this._bid = bid;
		this._location = location;
		this._cut = cut;
		this._impact = impact;
		this._bullet = bullet;
		this._fire = fire;
		this._water = water;
		this._thunder = thunder;
		this._ice = ice;
		this._dragon = dragon;
		this._stun = stun;
	}

	// constructor
	public Weakset(String location, String cut, String impact, String bullet, String fire, String water, String thunder,
			String ice, String dragon, String stun) {
		this._location = location;
		this._cut = cut;
		this._impact = impact;
		this._bullet = bullet;
		this._fire = fire;
		this._water = water;
		this._thunder = thunder;
		this._ice = ice;
		this._dragon = dragon;
		this._stun = stun;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_bid() {
		return _bid;
	}

	public void set_bid(int _bid) {
		this._bid = _bid;
	}

	public String get_location() {
		return _location;
	}

	public void set_location(String _location) {
		this._location = _location;
	}

	public String get_cut() {
		return _cut;
	}

	public void set_cut(String _cut) {
		this._cut = _cut;
	}

	public String get_impact() {
		return _impact;
	}

	public void set_impact(String _impact) {
		this._impact = _impact;
	}

	public String get_bullet() {
		return _bullet;
	}

	public void set_bullet(String _bullet) {
		this._bullet = _bullet;
	}

	public String get_fire() {
		return _fire;
	}

	public void set_fire(String _fire) {
		this._fire = _fire;
	}

	public String get_water() {
		return _water;
	}

	public void set_water(String _water) {
		this._water = _water;
	}

	public String get_thunder() {
		return _thunder;
	}

	public void set_thunder(String _thunder) {
		this._thunder = _thunder;
	}

	public String get_ice() {
		return _ice;
	}

	public void set_ice(String _ice) {
		this._ice = _ice;
	}

	public String get_dragon() {
		return _dragon;
	}

	public void set_dragon(String _dragon) {
		this._dragon = _dragon;
	}

	public String get_stun() {
		return _stun;
	}

	public void set_stun(String _stun) {
		this._stun = _stun;
	}


}


