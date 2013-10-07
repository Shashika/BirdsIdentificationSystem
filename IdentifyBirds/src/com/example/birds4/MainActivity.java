/**
 *
 * @author Shashika Ranga 100346P
 */

package com.example.birds4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

import com.example.birdNameList.BirdListView;
import com.example.colorList.GridViewList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Bitmap.Config;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


	private RelativeLayout dashBoard;
	private BirdColors birdCol;
	private ImageView image;
	private Button search;
	
	TextView testView;
	
	private Paint paint;
	private Bitmap mBitmap;
	private int x,y;
	private float xScreen,yScreen;
	private Context mContext;
	private int height,width,disHeight,disWidth;
	private float modified;
	private int p,q;
	private Point point=new Point();
	private int part;
	
															/*initial background color of the bird sketch*/
	private int billTarget=Color.rgb(245, 243, 241);
	private int headTarget=Color.rgb(245, 243, 241);
	private int faceTarget=Color.rgb(245, 243, 241);
	private int breastTarget=Color.rgb(245, 243, 241);
	private int featherTarget=Color.rgb(245, 243, 241);
	private int tailTarget=Color.rgb(245, 243, 241);
	private int legTarget=Color.rgb(245, 243, 241);
	
	private boolean isAddColorBill=false;
	private boolean isAddColorHead=false;
	private boolean isAddColorFace=false;
	private boolean isAddColorBreast=false;
	private boolean isAddColorFeather=false;
	private boolean isAddColorTail=false;
	private boolean isAddColorLeg=false;

															/*color list*/
	private int beige_color=Color.rgb(246, 240, 197);
	private int black_color=Color.rgb(1,1,1);
	private int blue_color=Color.rgb(0, 0, 255);
	private int brown_color=Color.rgb(170, 80, 2);
	private int darkBlue_color=Color.rgb(24, 44, 95);		
	private int darkGreen_color=Color.rgb(1, 102, 0);
	private int grey_color=Color.rgb(131, 131, 131);
	private int green_color=Color.rgb(5, 187, 7);
	private int greenishBlue_color=Color.rgb(17, 162, 105);
	private int lightBrown_color=Color.rgb(222, 110, 12);
	private int lightGreen_color=Color.rgb(119, 237, 1);
	private int orange_color=Color.rgb(255, 163, 0);
	private int purple_color=Color.rgb(141, 55, 142);
	private int red_color=Color.rgb(211, 1, 0);
	private int white_color=Color.rgb(255, 255, 255);
	private int yellow_color=Color.rgb(254, 237, 33);
	private int reddishBrown_color=Color.rgb(170, 42, 43);
	private int pink_color=Color.rgb(252, 178, 211);
	private int lightBlue_color=Color.rgb(154, 206, 255);
	
	
	
	private int numericColorValue;
	private int weightedValue=0;
	
	private ColorDataLoader dataLoader;
	private String[] birdDataBaseData;
	
	private BirdListView birdDataList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		birdCol=new BirdColors(this);
		
		dashBoard = (RelativeLayout) findViewById(R.id.dashBoard);
	    image = (ImageView) findViewById(R.id.BirdPhoto);
	    search=(Button) findViewById(R.id.searchButton);
	    testView=(TextView) findViewById(R.id.textView1);
	    
	    dashBoard.addView(birdCol);

	    setTitle("Add Colors");
	    
	    search.setOnClickListener(new View.OnClickListener(
	    		) {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
	
				 
				 
				dataLoader=new ColorDataLoader();
				
				dataLoader.execute(getColorValue(getBillTargetColor()),
						getColorValue(getHeadTargetColor()),
						getColorValue(getFaceTargetColor()),
						getColorValue(getBreastTargetColor()),
						getColorValue(getFeathersTargetColor()),
						getColorValue(getTailTargetColor()),
						getColorValue(getLegTargetColor()),
						getWeightedValue());
				
				try {
					
					//birdDataList=new BirdListView(dataLoader.get());
					birdDataBaseData=dataLoader.get();
					
					Bundle bundle = new Bundle();
					bundle.putStringArray("BirdData", birdDataBaseData);
					Intent intent = new Intent(mContext,BirdListView.class );
					intent.putExtras(bundle);
					startActivity(intent);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	

	class BirdColors extends View{			/*inner View class*/
		
		public BirdColors(Context context) {
			super(context);
			// TODO Auto-generated constructor stub	
			mContext = context;
			paint=new Paint();
			paint.setAntiAlias(true);	
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			

			mBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.birdsketchfinal2).copy(Config.ARGB_8888, true);
			height=mBitmap.getHeight();
			width=mBitmap.getWidth();
			
			disWidth = getResources().getDisplayMetrics().widthPixels;
			disHeight = getResources().getDisplayMetrics().heightPixels;
			
			modified=((float)disWidth/(float)width)*height;
			
			mBitmap= Bitmap.createScaledBitmap(mBitmap, disWidth, (int)modified, true);
			
		}
		
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
		
			
			canvas.drawBitmap(mBitmap,0,0, null);
			//canvas.drawText("Screen Cordinates" +x+"x"+y, 10, 220, paint);
			//canvas.drawText("Display width,height " +(float)disWidth+" x "+(float)disHeight, 10, 240, paint);
			//canvas.drawText("Bitmap  width,height " +width+" x "+height, 10, 260, paint);
			//canvas.drawText("Modified value       " +modified, 10, 280, paint);
		
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			
			
			p=(int)modified;		/*calculated bird sketch new height(p)*/
			q=disWidth;				/*screen width = new width(q)*/
			
			switch(event.getAction()){
		        
	  		case MotionEvent.ACTION_DOWN:
	  		   														
	  			xScreen=event.getX();					/*get screen coordinates*/
				yScreen=event.getY();
				
														
				x=(int)((600.0/(float)q)*xScreen);		/*pixel conversion = new scaled bitmap to original image (600x583) */ 
				
				y=(int)((583.0/(float)p)*yScreen);
				
				if((x>10 && x<100)&&(y>75 && y<140)){	//bill
			
						passData(1);
				}
				
				if(((x>105 && x<170)&&(y>60 && y<90))|| ((x>130 && x<185)&&(y>90 && y<115))){	//head
					
					passData(2);
				}
				
				if(((x>95 && x<125)&&(y>85 && y<180))|| ((x>125 && x<180)&&(y>115 && y<155))){	//face
					
					passData(3);
				}
				
				if(((x>100 && x<160)&&(y>180 && y<330))|| ((x>160 && x<310)&&(y>290 && y<365))){	//breast
					
					passData(4);
				}
				
				if(((x>155 && x<245)&&(y>155 && y<260))|| ((x>245 && x<315)&&(y>220 && y<300)) || ((x>315 && x<405)&&(y>270 && y<320))){	//feathers
					
					passData(5);
				}
				
				if(((x>280 && x<315)&&(y>350 && y<385))|| ((x>315 && x<455)&&(y>320 && y<365)) || ((x>455 && x<540)&&(y>355 && y<390))){	//tail
					
					passData(6);
				}
				
				if(((x>215 && x<265)&&(y>410 && y<450))|| ((x>200 && x<245)&&(y>450 && y<485)) || ((x>175 && x<235)&&(y>480 && y<520))){	//legs
					
					passData(7);
				}
				invalidate();
			 }
			return true;
		}
			
	}
	
	
	public void passData(int part){		/*pass data to the color list which part of the bird was selected*/
		
		Bundle bundle = new Bundle();
		bundle.putInt("Part", part);
		Intent intent = new Intent(mContext, GridViewList.class);
		intent.putExtras(bundle);
		startActivityForResult(intent,part);
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		//Toast.makeText(mContext, "Req" +requestCode +" Res "+resultCode, Toast.LENGTH_SHORT).show();
		
		if(requestCode==1){	//bill
			
			x=(int)(((float)q/(float)600.0)*60);
			y=(int)(((float)p/(float)583.0)*100);
				
			point.x=x;
			point.y=y;
			
			chooseColor(requestCode,resultCode,billTarget);
			
			if(isAddColorBill==false){
				
				weightedValue+=1;
				isAddColorBill=true;
			}
		}
		
		
		if(requestCode==2){	//head
			
			x=(int)(((float)q/(float)600.0)*145);
			y=(int)(((float)p/(float)583.0)*95);
				
			point.x=x;
			point.y=y;
			
			chooseColor(requestCode,resultCode,headTarget);
			
			if(isAddColorHead==false){
				
				weightedValue+=1;
				isAddColorHead=true;
			}
		}
		
		if(requestCode==3){	//face
			
			x=(int)(((float)q/(float)600.0)*130);
			y=(int)(((float)p/(float)583.0)*130);
				
			point.x=x;
			point.y=y;
			
			chooseColor(requestCode,resultCode,faceTarget);
			
			if(isAddColorFace==false){
				
				weightedValue+=2;
				isAddColorFace=true;
			}
		}
		
		if(requestCode==4){	//breast
			
			x=(int)(((float)q/(float)600.0)*190);
			y=(int)(((float)p/(float)583.0)*320);
				
			point.x=x;
			point.y=y;
			
			chooseColor(requestCode,resultCode,breastTarget);
			
			if(isAddColorBreast==false){
				
				weightedValue+=2;
				isAddColorBreast=true;
			}
		}
		
		if(requestCode==5){	//feathers
			
			x=(int)(((float)q/(float)600.0)*260);
			y=(int)(((float)p/(float)583.0)*255);
				
			point.x=x;
			point.y=y;
			
			chooseColor(requestCode,resultCode,featherTarget);
			
			if(isAddColorFeather==false){
				
				weightedValue+=2;
				isAddColorFeather=true;
			}
		}
		
		if(requestCode==6){	//tail
			
			x=(int)(((float)q/(float)600.0)*380);
			y=(int)(((float)p/(float)583.0)*350);
				
			point.x=x;
			point.y=y;
			
			chooseColor(requestCode,resultCode,tailTarget);
			
			if(isAddColorTail==false){
				
				weightedValue+=2;
				isAddColorTail=true;
			}
		}
		
		if(requestCode==7){	//legs
			
			x=(int)(((float)q/(float)600.0)*220);
			y=(int)(((float)p/(float)583.0)*455);
				
			point.x=x;
			point.y=y;
			
			chooseColor(requestCode,resultCode,legTarget);
			
			if(isAddColorLeg==false){
				
				weightedValue+=1;
				isAddColorLeg=true;
			}
		}
	}
	
	public void chooseColor(int request,int result,int targetColor) {	/*according to the selected color list position, give the color*/
		// TODO Auto-generated method stub
		
		int requestCode=request;
		int resultCode=result;
		
		if(resultCode==1){
			
			floodFill(point,targetColor,beige_color);	//Beige
			changeTargetColor(requestCode,beige_color);
		}
		
		else if(resultCode==2){
			
			floodFill(point,targetColor,black_color);	//Black
			changeTargetColor(requestCode,black_color);
		}
		
		else if(resultCode==3){

			floodFill(point,targetColor,blue_color);	//Blue
			changeTargetColor(requestCode,blue_color);
		}
		
		else if(resultCode==4){

			floodFill(point,targetColor,brown_color);	//Brown
			changeTargetColor(requestCode,brown_color);
		}
		

		else if(resultCode==5){

			floodFill(point,targetColor,darkBlue_color);	//Dark Blue
			changeTargetColor(requestCode,darkBlue_color);
		}
		

		else if(resultCode==6){

			floodFill(point,targetColor,darkGreen_color);	//Dark Green
			changeTargetColor(requestCode,darkGreen_color);
		}
		

		else if(resultCode==7){

			floodFill(point,targetColor,grey_color);	//Grey
			changeTargetColor(requestCode,grey_color);
		}
		

		else if(resultCode==8){

			floodFill(point,targetColor,green_color);		//Green
			changeTargetColor(requestCode,green_color);
		}
		

		else if(resultCode==9){

			floodFill(point,targetColor,greenishBlue_color);	//GreenishBlue
			changeTargetColor(requestCode,greenishBlue_color);
		}
		

		else if(resultCode==10){

			floodFill(point,targetColor,lightBrown_color);	//Light Brown
			changeTargetColor(requestCode,lightBrown_color);
		}
		
		else if(resultCode==11){

			floodFill(point,targetColor,lightGreen_color);	//Light Green
			changeTargetColor(requestCode,lightGreen_color);
		}
		
		else if(resultCode==12){

			floodFill(point,targetColor,orange_color);	//Orange
			changeTargetColor(requestCode,orange_color);
		}
		
		else if(resultCode==13){

			floodFill(point,targetColor,purple_color);	//Purple
			changeTargetColor(requestCode,purple_color);
		}
		
		else if(resultCode==14){

			floodFill(point,targetColor,red_color);	//Red
			changeTargetColor(requestCode,red_color);
		}
		
		else if(resultCode==15){

			floodFill(point,targetColor,white_color);	//White
			changeTargetColor(requestCode,white_color);
		}
		
		else if(resultCode==16){

			floodFill(point,targetColor,yellow_color);	//Yellow
			changeTargetColor(requestCode,yellow_color);
		}
		
		else if(resultCode==17){

			floodFill(point,targetColor,reddishBrown_color);	//ReddishBrown
			changeTargetColor(requestCode,reddishBrown_color);
		}
		
		else if(resultCode==18){

			floodFill(point,targetColor,pink_color);	//Pink
			changeTargetColor(requestCode,pink_color);
		}
		
		else if(resultCode==19){

			floodFill(point,targetColor,lightBlue_color);	//Light Blue
			changeTargetColor(requestCode,lightBlue_color);
		}
	
	}

	public void changeTargetColor(int part,int targetColor) {		/*need of the flood-fill algorithm-before add colors it needs to know the previous color of the area */
		// TODO Auto-generated method stub
		this.part=part;
		
		if(part==1){
			
			billTarget=targetColor;
		}
		else if(part==2){
			
			headTarget=targetColor;
		}
		else if(part==3){
			
			faceTarget=targetColor;
		}
		else if(part==4){
	
			breastTarget=targetColor;
		}
		else if(part==5){
	
			featherTarget=targetColor;
		}
		else if(part==6){
			
			tailTarget=targetColor;
		}
		else if(part==7){
			
			legTarget=targetColor;
		}
	}

	public void floodFill(Point node,int targetColor,int replacementColor){
		
		int width=q;
		int height=p;
		int target=targetColor;
		int replacement=replacementColor;
		
		
		if(target!=replacement){
			
			Queue<Point> queue=new LinkedList<Point>();
			
			
			do{
				int x=node.x;
				int y=node.y;
				//Toast.makeText(this, "Flood "+x+" "+y, Toast.LENGTH_SHORT).show();
				//Toast.makeText(this, "Flood "+mBitmap.getPixel(x-1, y), Toast.LENGTH_SHORT).show();
				
				while(x>0 && mBitmap.getPixel(x-1, y)==target){
					
					x--;
				}
				
				boolean up=false;
				boolean down=false;
				
				while(x<width && mBitmap.getPixel(x, y) == target){
					
					mBitmap.setPixel(x, y, replacement);
					
					if(!up && y>0 && mBitmap.getPixel(x, y-1)==target){
						
						queue.add(new Point(x,y-1));
						up=true;
					}
					else if(up && y>0 && mBitmap.getPixel(x, y-1)!=target){
						
						up=false;
					}
					
					
					if(!down && y<(height-1) && mBitmap.getPixel(x, y+1) == target){
						
						queue.add(new Point(x,y+1));
						down=true;
					}
					
					else if(down && y<(height-1) && mBitmap.getPixel(x, y+1) != target){
						
						down=false;
					}
					
					x++;
				}
				
			}
			while((node=queue.poll()) != null);
			
		}
	}
	
	public int getpassdata(){
		
		return part;
	}
	
	public int getWeightedValue(){
		
		return weightedValue;
	}
	
	public int getBillTargetColor(){
		
		return billTarget;
	}
	
	public int getFaceTargetColor(){
		
		return faceTarget;
	}
	
	public int getHeadTargetColor(){
		
		return headTarget;
	}
	
	public int getFeathersTargetColor(){
		
		return featherTarget;
	}
	
	public int getBreastTargetColor(){
		
		return breastTarget;
	}
	
	public int getTailTargetColor(){
		
		return tailTarget;
	}
	
	public int getLegTargetColor(){
		
		return legTarget;
	}
	
	
	
	public int getColorValue(int billColor){
		
		
		if(beige_color==billColor){
			
			numericColorValue=1;
		}
		
		else if(black_color==billColor){
			
			numericColorValue=2;
		}
		
		else if(blue_color==billColor){
			
			numericColorValue=3;
		}
		
		else if(brown_color==billColor){
			
			numericColorValue=4;
		}
		
		else if(darkBlue_color==billColor){
			
			numericColorValue=5;
		}
		
		else if(darkGreen_color==billColor){
			
			numericColorValue=6;
		}
		
		else if(grey_color==billColor){
			
			numericColorValue=7;
		}
		
		else if(green_color==billColor){
			
			numericColorValue=8;
		}
		
		else if(greenishBlue_color==billColor){
			
			numericColorValue=9;
		}
		
		else if(lightBrown_color==billColor){
			
			numericColorValue=10;
		}
		
		else if(lightGreen_color==billColor){
			
			numericColorValue=11;
		}
		
		else if(orange_color==billColor){
			
			numericColorValue=12;
		}
		
		else if(purple_color==billColor){
			
			numericColorValue=13;
		}
		
		else if(red_color==billColor){
			
			numericColorValue=14;
		}
		
		else if(white_color==billColor){
			
			numericColorValue=15;
		}
		
		else if(yellow_color==billColor){
			
			numericColorValue=16;
		}
		
		else if(reddishBrown_color==billColor){
			
			numericColorValue=17;
		}
		
		else if(pink_color==billColor){
			
			numericColorValue=18;
		}
		
		else if(lightBlue_color==billColor){
			
			numericColorValue=19;
		}
		
		return numericColorValue;
	}
}


