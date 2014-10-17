package com.example.mh4db;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationAdapter extends ArrayAdapter<MapRankset>{
	Context context;
	int layoutResourceId;
	ArrayList<MapRankset> data=new ArrayList<MapRankset>();
	public final static String ID_EXTRA="com.example.mh4db._ID";
	MapRankset ms;

	public LocationAdapter(Context context, int layoutResourceId, ArrayList<MapRankset> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Locationholder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);	//Important for fragment listviews
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new Locationholder();
			holder.clvl = (TextView)row.findViewById(R.id.clvl);
			holder.clocation = (TextView)row.findViewById(R.id.clocation);
			holder.carea = (TextView)row.findViewById(R.id.carea);
			holder.ctype = (ImageView)row.findViewById(R.id.ctype);
			holder.ctype_header = (TextView) row.findViewById(R.id.ctype_header);
			row.setTag(holder);
		}
		else
		{
			holder = (Locationholder)row.getTag();
		}
		ms = data.get(position);
		holder.clvl.setText(ms.get_lvl());
		holder.clocation.setText(ms.get_location());
		holder.carea.setText(ms.get_area());
		holder.clvl.setTextColor(Color.parseColor("#000000"));
		holder.clocation.setTextColor(Color.parseColor("#000000"));
		holder.carea.setTextColor(Color.parseColor("#000000"));
		holder.ctype_header.setTextColor(Color.parseColor("#000000"));
		if(ms.is_header()) {
			holder.ctype.setVisibility(View.GONE);
			holder.ctype_header.setVisibility(View.VISIBLE);
		}

		Drawable outImage2=ms.get_typeimage();
		holder.ctype.setImageDrawable(outImage2);
		
		holder.clocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, MapActivity.class);
			
				// i.putExtra(ID_EXTRA, ms ._iid);
				// TODO: Find better way, be careful here
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//
				v.getContext().startActivity(i);
			}
		});


		return row;
	}

	public boolean areAllItemsEnabled() {
		return false;
	}

	public boolean isEnabled(int position) {
		if(position == 0) return false;
		else return true;
	}
	static class Locationholder
	{
		TextView clvl;
		TextView clocation;
		TextView carea;
		TextView ctype_header;
		ImageView ctype;
	}
}




