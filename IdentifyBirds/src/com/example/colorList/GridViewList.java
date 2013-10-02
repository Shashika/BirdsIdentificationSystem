/**
 *
 * @author Shashika Ranga 100346P
 */

package com.example.colorList;

import java.util.ArrayList;

import com.example.birds4.MainActivity;
import com.example.birds4.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

public class GridViewList extends Activity {

	private GridView gridView;
	private ArrayList<Item> gridArray = new ArrayList<Item>();
	private CustomGridViewAdapter customGridAdapter;
	private Context context=this;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		
		bundle = getIntent().getExtras();						/*get selected part from main activity*/
		
		if(bundle != null) {
			int part=bundle.getInt("Part");
			
			if(part==1)											/*define heading for color list*/
				setTitle("Bill - Add Colors");
			
			else if(part==2)
				setTitle("Head - Add Colors");
			
			else if(part==3)
				setTitle("Face - Add Colors");
			
			else if(part==4)
				setTitle("Breast - Add Colors");
			
			else if(part==5)
				setTitle("Feathers - Add Colors");
			
			else if(part==6)
				setTitle("Tail - Add Colors");
			
			else if(part==7)
				setTitle("Legs - Add Colors");
		}
		
		/*set color bitmaps*/
		
		Bitmap beige = BitmapFactory.decodeResource(this.getResources(), R.drawable.beige);
		Bitmap black = BitmapFactory.decodeResource(this.getResources(), R.drawable.black);
		Bitmap blue = BitmapFactory.decodeResource(this.getResources(), R.drawable.blue);
		Bitmap brown = BitmapFactory.decodeResource(this.getResources(), R.drawable.brown);
		Bitmap darkBlue = BitmapFactory.decodeResource(this.getResources(), R.drawable.darkblue);
		Bitmap darkGreen = BitmapFactory.decodeResource(this.getResources(), R.drawable.darkgreen);
		Bitmap gray = BitmapFactory.decodeResource(this.getResources(), R.drawable.gray);
		Bitmap green = BitmapFactory.decodeResource(this.getResources(), R.drawable.green);
		Bitmap greenishBlue = BitmapFactory.decodeResource(this.getResources(), R.drawable.greenishblue);
		Bitmap lightBrown = BitmapFactory.decodeResource(this.getResources(), R.drawable.lightbrown);
		Bitmap lightGreen = BitmapFactory.decodeResource(this.getResources(), R.drawable.lightgreen);
		Bitmap orange = BitmapFactory.decodeResource(this.getResources(), R.drawable.orange);
		Bitmap purple = BitmapFactory.decodeResource(this.getResources(), R.drawable.purple);
		Bitmap red = BitmapFactory.decodeResource(this.getResources(), R.drawable.red);
		Bitmap white = BitmapFactory.decodeResource(this.getResources(), R.drawable.white);
		Bitmap yellow = BitmapFactory.decodeResource(this.getResources(), R.drawable.yellow);
		Bitmap reddishBrown = BitmapFactory.decodeResource(this.getResources(), R.drawable.reddishbrown);
		Bitmap pink = BitmapFactory.decodeResource(this.getResources(), R.drawable.pink);
		Bitmap lightBlue = BitmapFactory.decodeResource(this.getResources(), R.drawable.lightblue);
		
		gridArray.add(new Item(beige,"Beige"));
		gridArray.add(new Item(black,"Black"));
		gridArray.add(new Item(blue,"Blue"));
		gridArray.add(new Item(brown,"Brown"));
		gridArray.add(new Item(darkBlue,"Dark Blue"));
		gridArray.add(new Item(darkGreen,"Dark Green"));
		gridArray.add(new Item(gray,"Grey"));
		gridArray.add(new Item(green,"Green"));
		gridArray.add(new Item(greenishBlue,"Greenish Blue"));
		gridArray.add(new Item(lightBrown,"Light Brown"));
		gridArray.add(new Item(lightGreen,"Light Green"));
		gridArray.add(new Item(orange,"Orange"));
		gridArray.add(new Item(purple,"Purple"));
		gridArray.add(new Item(red,"Red"));
		gridArray.add(new Item(white,"White"));
		gridArray.add(new Item(yellow,"Yellow"));
		gridArray.add(new Item(reddishBrown,"Reddish Brown"));
		gridArray.add(new Item(pink,"Pink"));
		gridArray.add(new Item(lightBlue,"Light Blue "));
		
		
		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row, gridArray);
		gridView.setAdapter((ListAdapter) customGridAdapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
       

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				// TODO Auto-generated method stub
				/*1st position taken return 0 as position and also when user doesn't select any color it returns 0, So avoid that conflict, 1st position of the color list taken as 1*/
					setResult((int)id+1);	
					finish();
	
			}
        });
		
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
