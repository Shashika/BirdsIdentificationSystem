package com.example.birdNameList;

import java.util.ArrayList;

import com.example.birdDetails.BirdDetails;
import com.example.birds4.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BirdListView extends Activity{

	private ListView listView;
	private ArrayList<Item> listArray = new ArrayList<Item>();
	private ItemAdaptor itemAdapter;

	private Bundle bundle;
	private Bundle birdIDbundle;
	private String[] listBirdData;
	private int listLength;
	private int[] listID;
	private int[] listRank;
	private String[] listBirdName;
	private Bitmap rank[]=new Bitmap[7];
	private boolean isGetData;

	private ProgressDialog progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ranklistview);
		
		bundle = getIntent().getExtras();	
		
		if(bundle != null) {
			
			listBirdData=bundle.getStringArray("BirdData");
		}
		
		listLength=Integer.parseInt(listBirdData[0]);
		
		if(listLength==0){
			setTitle("No search results found...");
		}
		else{
			showBirdNameOnListView();	
		}
		
		listView = (ListView) findViewById(R.id.listView);
		itemAdapter = new ItemAdaptor(this, R.layout.listrow, listArray);
		listView.setAdapter((ListAdapter) itemAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
		       

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
				// TODO Auto-generated method stub
				
					isGetData=false;
					Thread t=new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(true){
								
								if(isGetData==true){
									progressBar.dismiss();
									
								}
								else{
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							}
						}
					});
					t.start();
									
					
					progressBar=new ProgressDialog(v.getContext());
					progressBar.setCancelable(true);
					progressBar.setMessage("Searching...");
					progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
					progressBar.show();
					
					
					birdIDbundle = new Bundle();			
					birdIDbundle.putInt("BirdID", listID[(int)id]);		
					showBirdDetails();
					isGetData=true;
			}
        });
		
	}
	
	private void showBirdDetails(){
		
		Intent intent = new Intent(this,BirdDetails.class);
		intent.putExtras(birdIDbundle);
		startActivity(intent);
	}
	
	
	private void showBirdNameOnListView() {
		// TODO Auto-generated method stub
		
		setTitle(listLength+" search results found...");
		
		listID=new int[listLength];
		listRank=new int[listLength];
		listBirdName=new String[listLength];
		
		for(int i=0;i<listLength;i++){
			
			listID[i]=Integer.parseInt(listBirdData[i+1]);
		}
		
		for(int i=0;i<listLength;i++){
			
			listRank[i]=Integer.parseInt(listBirdData[i+listLength+1]);
		}
		
		for(int i=0;i<listLength;i++){
			
			listBirdName[i]=listBirdData[i+listLength*2+1];
		}

		rank[0] = BitmapFactory.decodeResource(this.getResources(), R.drawable.rank1);
		rank[1] = BitmapFactory.decodeResource(this.getResources(), R.drawable.rank2);
		rank[2] = BitmapFactory.decodeResource(this.getResources(), R.drawable.rank3);
		rank[3] = BitmapFactory.decodeResource(this.getResources(), R.drawable.rank4);
		rank[4] = BitmapFactory.decodeResource(this.getResources(), R.drawable.rank5);
		rank[5] = BitmapFactory.decodeResource(this.getResources(), R.drawable.rank6);
		rank[6] = BitmapFactory.decodeResource(this.getResources(), R.drawable.rank7);
		
		for(int i=0;i<listLength;i++){
			
			if(listRank[i]==1){
				
				listArray.add(new Item(rank[0],listBirdName[i]));
			}
			
			else if(listRank[i]==2){
				
				listArray.add(new Item(rank[1],listBirdName[i]));
			}
			
			else if(listRank[i]==3){
				
				listArray.add(new Item(rank[2],listBirdName[i]));
			}
			
			else if(listRank[i]==4){
				
				listArray.add(new Item(rank[3],listBirdName[i]));
			}
			
			else if(listRank[i]==5){
				
				listArray.add(new Item(rank[4],listBirdName[i]));
			}
			
			else if(listRank[i]==6){
				
				listArray.add(new Item(rank[5],listBirdName[i]));
			}
			
			else if(listRank[i]==7){
	
				listArray.add(new Item(rank[6],listBirdName[i]));
			}
		}
		
	}


}
