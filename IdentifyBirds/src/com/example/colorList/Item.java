/**
 *
 * @author Shashika Ranga 100346P
 */

package com.example.colorList;

import android.graphics.Bitmap;

public class Item {

	private Bitmap bitmap;
	private String name;
	
	public Item(Bitmap bitmap,String name){
			this.bitmap=bitmap;
			this.name=name;
	}
	
	public Bitmap getBitmap(){
		
		return bitmap;
	}
	
	public String getName(){
		
		return name;
	}
}
