package com.example.mh4db;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FragA_Adapter extends ArrayAdapter<Rankset>{
	Context context;
	int layoutResourceId;
	ArrayList<Rankset> data=new ArrayList<Rankset>();

	public FragA_Adapter(Context context, int layoutResourceId, ArrayList<Rankset> data) {
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
			LayoutInflater inflater = LayoutInflater.from(context);	//Important for fragment listviews
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new Rankholder();
			holder.iname = (TextView)row.findViewById(R.id.iname);
			holder.iqty = (TextView)row.findViewById(R.id.iqty);
			holder.iprob = (TextView)row.findViewById(R.id.iprob);
			holder.iobtain = (TextView)row.findViewById(R.id.iobtain);
			holder.irank = (TextView)row.findViewById(R.id.irank);
			row.setTag(holder);
		}
		else
		{
			holder = (Rankholder)row.getTag();
		}
		Rankset rs = data.get(position);
		holder.iname.setText(rs ._name);
		holder.iqty.setText(rs ._qty);
		holder.iprob.setText(rs ._prob);
		holder.iobtain.setText(rs ._obtain);
		holder.irank.setText(rs ._low);
		if(rs ._header) {
			holder.iname.setTextColor(Color.parseColor("#000000"));
			holder.iqty.setTextColor(Color.parseColor("#000000"));
			holder.iprob.setTextColor(Color.parseColor("#000000"));
			holder.iobtain.setTextColor(Color.parseColor("#000000"));
			holder.irank.setTextColor(Color.parseColor("#000000"));
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
	static class Rankholder
	{
		TextView iname;
		TextView iqty;
		TextView iprob;
		TextView iobtain;	
		TextView irank;
	}
}




