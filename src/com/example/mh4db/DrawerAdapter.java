package com.example.mh4db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {
		private Context context;
		String[] mainmenu;
		int[] images = {R.drawable.ic_home,R.drawable.ic_monster, R.drawable.ic_item, R.drawable.ic_map, 
				R.drawable.ic_weapon};
		public DrawerAdapter(Context context) {
			this.context = context;
			mainmenu=context.getResources().getStringArray(R.array.mainmenu);
		}
		@Override
		public int getCount() {

			return mainmenu.length;
		}

		@Override
		public Object getItem(int position) {

			return mainmenu[position];
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = null;
			if(convertView==null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row=inflater.inflate(R.layout.custom_row, parent, false);
			}
			else {
				row = convertView;
			}
			TextView title = (TextView) row.findViewById(R.id.text_drawer);
			ImageView image = (ImageView) row.findViewById(R.id.image_drawer);
			title.setText(mainmenu[position]);
			image.setImageResource(images[position]);

			return row;
		}

	}