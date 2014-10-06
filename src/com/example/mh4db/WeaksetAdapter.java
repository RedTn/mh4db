package com.example.mh4db;

import java.util.ArrayList;

import com.example.databaseimage.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WeaksetAdapter extends ArrayAdapter<Weakset>{
	Context context;
	int layoutResourceId;
	ArrayList<Weakset> data=new ArrayList<Weakset>();
	public WeaksetAdapter(Context context, int layoutResourceId, ArrayList<Weakset> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		WeakHolder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new WeakHolder();
			holder.weakView1 = (TextView)row.findViewById(R.id.weakView1);
			holder.weakView2 = (TextView)row.findViewById(R.id.weakView2);
			holder.weakView3 = (TextView)row.findViewById(R.id.weakView3);
			holder.weakView4 = (TextView)row.findViewById(R.id.weakView4);
			holder.weakView5 = (TextView)row.findViewById(R.id.weakView5);
			holder.weakView6 = (TextView)row.findViewById(R.id.weakView6);
			holder.weakView7 = (TextView)row.findViewById(R.id.weakView7);
			holder.weakView8 = (TextView)row.findViewById(R.id.weakView8);
			holder.weakView9 = (TextView)row.findViewById(R.id.weakView9);
			holder.weakView10 = (TextView)row.findViewById(R.id.weakView10);
			row.setTag(holder);
		}
		else
		{
			holder = (WeakHolder)row.getTag();
		}
		Weakset set = data.get(position);
		holder.weakView1.setText(set ._location);
		holder.weakView2.setText(set ._cut);
		holder.weakView3.setText(set ._impact);
		holder.weakView4.setText(set ._bullet);
		holder.weakView5.setText(set ._fire);
		holder.weakView6.setText(set ._water);
		holder.weakView7.setText(set ._thunder);
		holder.weakView8.setText(set ._ice);
		holder.weakView9.setText(set ._dragon);
		holder.weakView10.setText(set ._stun);
		
		return row;
	}
	static class WeakHolder
	{
		TextView weakView1;
		TextView weakView2;
		TextView weakView3;
		TextView weakView4;
		TextView weakView5;
		TextView weakView6;
		TextView weakView7;
		TextView weakView8;
		TextView weakView9;
		TextView weakView10;
		
	}
}




