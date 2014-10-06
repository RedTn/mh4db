package com.example.databaseimage;

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

public class RanksetAdapter extends ArrayAdapter<Rankset>{
	Context context;
	int layoutResourceId;
	ArrayList<Rankset> data=new ArrayList<Rankset>();
	public RanksetAdapter(Context context, int layoutResourceId, ArrayList<Rankset> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Rankholder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new Rankholder();
			holder.ricon = (ImageView)row.findViewById(R.id.ricon);
			holder.rname = (TextView)row.findViewById(R.id.rname);
			holder.rqty = (TextView)row.findViewById(R.id.rqty);
			holder.rprob = (TextView)row.findViewById(R.id.rprob);
			holder.robtain = (TextView)row.findViewById(R.id.robtain);
			row.setTag(holder);
		}
		else
		{
			holder = (Rankholder)row.getTag();
		}
		Rankset rs = data.get(position);
		holder.rname.setText(rs ._name);
		holder.rqty.setText(rs ._qty);
		holder.rprob.setText(rs ._prob);
		holder.robtain.setText(rs ._obtain);
		//convert byte to bitmap take from Rankset class
		byte[] outImage=rs._image;
		if (outImage != null) {
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		holder.ricon.setImageBitmap(theImage);
		}
		return row;
	}
	static class Rankholder
	{
		ImageView ricon;
		TextView rname;
		TextView rqty;
		TextView rprob;
		TextView robtain;	
	}
}




