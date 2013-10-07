package com.example.birdDetails;

import java.io.IOException;
import java.io.InputStream;
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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

public class ImageLoader extends AsyncTask<Integer, Void, Bitmap>{

	String result="";
	InputStream isr=null;
	Bitmap btmp;
	
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
	}

	@Override
	protected Bitmap doInBackground(Integer... params) {
		
		String result="";
		InputStream is=null;
		String ID=String.valueOf(params[0]);
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://shashikaranga.site50.net/imageData.php");
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
					btmp = BitmapFactory.decodeStream(is);
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

			return btmp;
	}
}
