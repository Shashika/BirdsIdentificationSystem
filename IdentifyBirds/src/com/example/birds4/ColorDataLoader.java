package com.example.birds4;

import android.os.AsyncTask;
import android.widget.Toast;

public class ColorDataLoader extends AsyncTask<Integer, Void, Integer>{

	private int billColorValue;
	private int headColorValue;
	private int faceColorValue;
	private int breastColorValue;
	private int featherColorValue;
	private int tailColorValue;
	private int legColorValue;
	private int weightedValue;


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	

	@Override
	protected Integer doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		billColorValue=params[0];
		headColorValue=params[1];
		faceColorValue=params[2];
		breastColorValue=params[3];
		featherColorValue=params[4];
		tailColorValue=params[5];
		legColorValue=params[6];
		
		weightedValue=params[7];
		
		return weightedValue;
	}

}
