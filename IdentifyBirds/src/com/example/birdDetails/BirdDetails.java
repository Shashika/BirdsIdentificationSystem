package com.example.birdDetails;

import java.util.concurrent.ExecutionException;

import com.example.birds4.ConnectionDetector;
import com.example.birds4.MainActivity;
import com.example.birds4.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class BirdDetails extends Activity{

	private TextView birdName,scientificName,description;
	private ImageView image;
	private Bundle bundle;
	private int id;
	
	private Boolean isInternetPresent = false;
	private ConnectionDetector cd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.birddetails);
		birdName=(TextView) findViewById(R.id.textName);
		scientificName=(TextView) findViewById(R.id.textScientificName);
		description=(TextView) findViewById(R.id.textDescription);
		image=(ImageView) findViewById(R.id.imageView1);
		  cd = new ConnectionDetector(getApplicationContext());
		 isInternetPresent = cd.isConnectingToInternet();
         
         // check for Internet status
         if (isInternetPresent) {
        	 sendBirdIDtoDatabaseAndGetBirdDescription();
         	
         } else {
             // Internet connection is not present
             // Ask user to connect to Internet
             showAlertDialog(BirdDetails.this, "No Internet Connection",
                     "You don't have internet connection.", false);
             finish();
         }
		
		
	
	}
	
	public void sendBirdIDtoDatabaseAndGetBirdDescription(){
		
      	 setTitle("Details");
  		
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
	
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
	
	
}
