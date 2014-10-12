package com.example.mh4db;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CombosetAdapter extends ArrayAdapter<Comboset>{
	Context context;
	int layoutResourceId;
	ArrayList<Comboset> data=new ArrayList<Comboset>();

	public CombosetAdapter(Context context, int layoutResourceId, ArrayList<Comboset> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Comboholder holder = null;
		if(row == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);	//Important for fragment listviews
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new Comboholder();
			holder.bname_a = (TextView)row.findViewById(R.id.bname_a);
			holder.bname_b = (TextView)row.findViewById(R.id.bname_b);
			holder.bprob = (TextView)row.findViewById(R.id.bprob);
			holder.bqty = (TextView)row.findViewById(R.id.bqty);
			holder.bitem_a = (ImageView)row.findViewById(R.id.bitem_a);
			holder.bitem_b = (ImageView)row.findViewById(R.id.bitem_b);
			row.setTag(holder);
		}
		else
		{
			holder = (Comboholder)row.getTag();
		}
		Comboset cs = data.get(position);
		holder.bname_a.setText(cs ._namea);
		holder.bname_b.setText(cs ._nameb);
		holder.bprob.setText(cs ._prob);
		holder.bqty.setText(cs ._qty);
		if(cs ._header) {
			holder.bname_a.setTextColor(Color.parseColor("#000000"));
			holder.bname_b.setTextColor(Color.parseColor("#000000"));
			holder.bprob.setTextColor(Color.parseColor("#000000"));
			holder.bqty.setTextColor(Color.parseColor("#000000"));
		}
		
		byte[] outImage=cs._imagea;
		if (outImage != null) {
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		holder.bitem_a.setImageBitmap(theImage);
		}
		
		byte[] outImage2=cs._imageb;
		if (outImage2 != null) {
		ByteArrayInputStream imageStream2 = new ByteArrayInputStream(outImage2);
		Bitmap theImage2 = BitmapFactory.decodeStream(imageStream2);
		holder.bitem_b.setImageBitmap(theImage2);
		}
		
		return row;
	}
	
	public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
       if(position == 0) return false;
       else return true;
    }
	static class Comboholder
	{
		TextView bname_a;
		TextView bname_b;
		TextView bprob;
		TextView bqty;	
		ImageView bitem_a;
		ImageView bitem_b;
	}
}




