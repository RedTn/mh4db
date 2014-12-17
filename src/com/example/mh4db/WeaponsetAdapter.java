package com.example.mh4db;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeaponsetAdapter extends ArrayAdapter<WeaponTypeset>{
	Context context;
	int layoutResourceId;
	ArrayList<WeaponTypeset> data=new ArrayList<WeaponTypeset>();
	public WeaponsetAdapter(Context context, int layoutResourceId, ArrayList<WeaponTypeset> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		WeaponHolder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new WeaponHolder();
			holder.weapon_name = (TextView)row.findViewById(R.id.txtTitle);
			holder.weapon_icon = (ImageView)row.findViewById(R.id.imgIcon);
			row.setTag(holder);
		}
		else
		{
			holder = (WeaponHolder)row.getTag();
		}
		WeaponTypeset picture = data.get(position);
		holder.weapon_name.setText(picture.get_name());
		
		byte[] outImage=picture.get_image();
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		holder.weapon_icon.setImageBitmap(theImage);
		return row;
	}
	static class WeaponHolder
	{
		ImageView weapon_icon;
		TextView weapon_name;
	}
}




