package com.example.birds4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ColorDataLoader extends AsyncTask<Integer, Void, String>{

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
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	

	@Override
	protected String doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		billColorValue=params[0];
		headColorValue=params[1];
		faceColorValue=params[2];
		breastColorValue=params[3];
		featherColorValue=params[4];
		tailColorValue=params[5];
		legColorValue=params[6];
		
		weightedValue=params[7];
		
		
		String result="";
		InputStream is=null;
		
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://shashikaranga.site50.net/birdsAlgorithm.php");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("bill",Integer.toString(billColorValue)));
		nameValuePairs.add(new BasicNameValuePair("head",Integer.toString(headColorValue)));
		nameValuePairs.add(new BasicNameValuePair("face",Integer.toString(faceColorValue)));
		nameValuePairs.add(new BasicNameValuePair("breast",Integer.toString(breastColorValue)));
		nameValuePairs.add(new BasicNameValuePair("feather",Integer.toString(featherColorValue)));
		nameValuePairs.add(new BasicNameValuePair("tail",Integer.toString(tailColorValue)));
		nameValuePairs.add(new BasicNameValuePair("leg",Integer.toString(legColorValue)));
		nameValuePairs.add(new BasicNameValuePair("weight",Integer.toString(weightedValue)));
		
		
		// Execute HTTP Post Request
		try {

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			} catch (UnsupportedEncodingException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			}

			try {

				
				HttpResponse response=httpclient.execute(httppost);
				int statusCode=response.getStatusLine().getStatusCode();
				

				if(statusCode==200){

					HttpEntity entity=response.getEntity();
					is=entity.getContent();
				}

				else{

					Log.e("log_tag", "error "+response.getStatusLine().getStatusCode()) ;
				}

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

			} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
			}
			
			String line=null;
			try{
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb=new StringBuilder();
				
				
				
				while((line=reader.readLine())!=null){
					
					sb.append(line+"\n");
				}
				is.close();
				
				result=sb.toString();
			}
			catch(Exception e){
				
				Log.e("log_tag","Error converting result" +e.toString());
			}
			
			//String[] s=new String[3];
			String len="";
			try{
				
				JSONArray jarray=new JSONArray(result);
				len=jarray.getString(0);
				
				/*for(int i=0;i<jarray.length();i++){
					JSONObject json=jarray.getJSONObject(i);
					//s=s+ "ID : "+ json.getString("ID")+"\nName : " +json.getString("Name")+"\n";
					len=json.getString(jarray.getString(index))
					
				}*/
				
			}
				
			catch(Exception e){
				
				Log.e("log_tag","Error parsing data" +e.toString());
			}
			
			
			
			//String tag=s;
			//Log.d(tag, s);*/
		
			//String line="Sha";
			//return line;
			return len;
	}

}
