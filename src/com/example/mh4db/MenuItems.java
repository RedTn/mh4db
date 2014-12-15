package com.example.mh4db;

public enum MenuItems {
	HOME(0),
	MONSTERS(1),
	ITEMS(2),
	MAPS(3),
	WEAPONS(4);
	
	private final int _item;
	
	private MenuItems(int item){
		this._item = item;
	}
	
	public int getItem() {
		return _item;
	}
	
	public static MenuItems get(int item) {
		switch(item) {
		case 0: return HOME;
		case 1: return MONSTERS;
		case 2: return ITEMS;
		case 3: return MAPS;
		case 4: return WEAPONS;
		default: return null;
		}
	}
}