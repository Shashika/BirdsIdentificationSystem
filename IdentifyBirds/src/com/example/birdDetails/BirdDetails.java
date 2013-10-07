package com.example.birdDetails;

import java.util.concurrent.ExecutionException;

import com.example.birds4.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class BirdDetails extends Activity{

	private TextView birdName,scientificName,description;
	private ImageView image;
	private Bundle bundle;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.birddetails);
		birdName=(TextView) findViewById(R.id.textName);
		scientificName=(TextView) findViewById(R.id.textScientificName);
		description=(TextView) findViewById(R.id.textDescription);
		image=(ImageView) findViewById(R.id.imageView1);
		
		bundle = getIntent().getExtras();	
		
		if(bundle != null) {
			
			id=bundle.getInt("BirdID");
		}
		
		ImageLoader a=new ImageLoader();
		TextDataLoader b = new TextDataLoader();
		a.execute(id);
		b.execute(id);
		
		try {
			String[] s=b.get();
			birdName.setText(s[0]);
			scientificName.setText(s[1]);
			description.setText(s[2]);
			image.setImageBitmap(a.get());
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
