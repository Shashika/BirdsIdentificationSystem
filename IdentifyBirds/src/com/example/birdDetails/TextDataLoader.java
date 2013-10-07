package com.example.birdDetails;

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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class TextDataLoader extends AsyncTask<Integer, Void, String[]>{

	String result="";
	InputStream isr=null;
	Bitmap btmp;
	
	@Override
	protected void onPostExecute(String[] result) {
		super.onPostExecute(result);
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
	}

	@Override
	protected String[] doInBackground(Integer... params) {
		
		String result="";
		InputStream is=null;
		String ID=String.valueOf(params[0]);
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://shashikaranga.site50.net/textData.php");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("ID",ID));
		
		
		
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

			try{
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb=new StringBuilder();
				
				String line=null;
				
				while((line=reader.readLine())!=null){
					
					sb.append(line+"\n");
				}
				is.close();
				
				result=sb.toString();
			}
			catch(Exception e){
				
				Log.e("log_tag","Error converting result" +e.toString());
			}
			
			String[] s=new String[3];
			
			try{
				
				JSONArray jarray=new JSONArray(result);
				
				for(int i=0;i<jarray.length();i++){
					JSONObject json=jarray.getJSONObject(i);
	
					s[0]=json.getString("Name");
					s[1]=json.getString("ScientificName");
					s[2]=json.getString("Description");
				}
				
			}
			catch(Exception e){
				
				Log.e("log_tag","Error parsing data" +e.toString());
			}
			
			
			return s;
	}

}
