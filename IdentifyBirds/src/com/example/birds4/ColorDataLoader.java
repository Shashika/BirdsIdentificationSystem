package com.example.birds4;

import android.os.AsyncTask;

public class ColorDataLoader extends AsyncTask<Void, Void, String>{

	private int billColorValue;
	private int headColorValue;
	private int faceColorValue;
	private int breastColorValue;
	private int featherColorValue;
	private int tailColorValue;
	private int legColorValue;
	private int weightedValue;

	
	public ColorDataLoader(int billVal,int headVal,int faceVal,int breastVal,int feathersVal,int tailVal,int legVal,int weightVal){
		
		billColorValue=billVal;
		headColorValue=headVal;
		faceColorValue=faceVal;
		breastColorValue=breastVal;
		featherColorValue=feathersVal;
		tailColorValue=tailVal;
		legColorValue=legVal;
		
		weightedValue=weightVal;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	

	@Override
	protected String doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
