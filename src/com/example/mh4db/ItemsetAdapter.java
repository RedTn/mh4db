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

public class ItemsetAdapter extends ArrayAdapter<Itemset>{
	Context context;
	int layoutResourceId;
	ArrayList<Itemset> data=new ArrayList<Itemset>();
	public ItemsetAdapter(Context context, int layoutResourceId, ArrayList<Itemset> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ImageHolder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ImageHolder();
			holder.item_name = (TextView)row.findViewById(R.id.item_name);
			holder.item_icon = (ImageView)row.findViewById(R.id.item_icon);
			row.setTag(holder);
		}
		else
		{
			holder = (ImageHolder)row.getTag();
		}
		Itemset picture = data.get(position);
		holder.item_name.setText(picture ._name);
		//convert byte to bitmap take from Itemset class
		byte[] outImage=picture._image;
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		holder.item_icon.setImageBitmap(theImage);
		return row;
	}
	static class ImageHolder
	{
		ImageView item_icon;
		TextView item_name;
	}
}




