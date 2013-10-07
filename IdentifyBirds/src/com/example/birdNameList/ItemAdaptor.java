package com.example.birdNameList;

import java.io.InputStream;
import java.util.ArrayList;

import com.example.birds4.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdaptor extends ArrayAdapter<Item>{

	Context context;
	int layoutResourceId;
	ArrayList<Item> data = new ArrayList<Item>();
	
	public ItemAdaptor(Context context, int textViewResourceId,ArrayList<Item> data) {
		super(context, textViewResourceId, data);
		// TODO Auto-generated constructor stub
		this.layoutResourceId = textViewResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		RecordHolder holder = null;
		
		if (row == null) {
			   LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			   row = inflater.inflate(layoutResourceId, parent, false);
				 
			   holder = new RecordHolder();
			   holder.txtTitle = (TextView) row.findViewById(R.id.textRank);
			   holder.imageItem = (ImageView) row.findViewById(R.id.imageRankView);
			   row.setTag(holder);
			   
		} else {
				holder = (RecordHolder) row.getTag();
		}
		
		Item item = data.get(position);
		holder.txtTitle.setText(item.getName());
		holder.imageItem.setImageBitmap(item.getBitmap());
		
		return row;
	}

	static class RecordHolder {
			  TextView txtTitle;
			  ImageView imageItem;
			 
	
    }
}
