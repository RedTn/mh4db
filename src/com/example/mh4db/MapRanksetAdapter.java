package com.example.mh4db;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.Activity;
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

public class MapRanksetAdapter extends ArrayAdapter<MapRankset>{
	Context context;
	int layoutResourceId;
	ArrayList<MapRankset> data=new ArrayList<MapRankset>();
	public final static String ID_EXTRA="com.example.mh4db._ID";
	MapRankset mr;

	public MapRanksetAdapter(Context context, int layoutResourceId, ArrayList<MapRankset> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Mapholder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new Mapholder();
			holder.marea = (TextView)row.findViewById(R.id.marea);
			holder.mitem_name = (TextView)row.findViewById(R.id.mitem_name);
			holder.mitem_image = (ImageView)row.findViewById(R.id.mitem_image);
			holder.mtype = (ImageView)row.findViewById(R.id.mtype);
			holder.mtype_header = (TextView) row.findViewById(R.id.mtype_header);
			row.setTag(holder);
		}
		else
		{
			holder = (Mapholder)row.getTag();
		}
		mr = data.get(position);
		holder.marea.setText(mr ._area);
		holder.mitem_name.setText(mr ._itemname);
		if(mr ._header) {
			holder.marea.setTextColor(Color.parseColor("#000000"));
			holder.mitem_name.setTextColor(Color.parseColor("#000000"));
			holder.mitem_image.setVisibility(View.INVISIBLE);
			holder.mtype.setVisibility(View.GONE);
			holder.mtype_header.setVisibility(View.VISIBLE);
		}

		byte[] outImage=mr._itemimage;
		if (outImage != null) {
			ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
			Bitmap theImage = BitmapFactory.decodeStream(imageStream);
			holder.mitem_image.setImageBitmap(theImage);
		}

		Drawable outImage2=mr._typeimage;
		holder.mtype.setImageDrawable(outImage2);

		
		holder.mitem_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, ItemDetailActivity.class);
				i.putExtra(ID_EXTRA,mr ._itemid);
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
	static class Mapholder
	{
		TextView marea;
		TextView mitem_name;
		ImageView mitem_image;
		ImageView mtype;
		TextView mtype_header;
	}
}




