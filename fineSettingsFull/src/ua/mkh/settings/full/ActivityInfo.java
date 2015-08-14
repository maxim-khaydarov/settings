package ua.mkh.settings.full;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ua.mkh.settings.full.JSONParserUpdate;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ActivityInfo extends Activity implements OnClickListener, SimpleGestureListener{
	Button btn_back, button1 ;
	TextView textStatus, textView1;
	int v = 0;
	int versionCode;
	LinearLayout LinearLayour122;
	ImageView Up, Down;
	int status = 0;
	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	
	String versionName;

	 String idd;
	 
	 int OS = android.os.Build.VERSION.SDK_INT;
	
	Animation animationRotateDown, animationRotateUp;
	
	ProgressBar progressBar1;
	
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   	  
	   SharedPreferences mSettings;
	
	// URL to get contacts JSON
	    private static String url = "http://web-server-mkh.ucoz.ua/login.txt";
	 
	    // JSON Node names
	    private static final String TAG_CONTACTS = "version";
	    private static final String TAG_ID = "id";
	    
	    private SimpleGestureFilter detector;
	    
	 
	    // contacts JSONArray
	    JSONArray version = null;
	 
	    // Hashmap for ListView
	    ArrayList<HashMap<String, String>> contactList;
	   
	   
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_info);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			detector = new SimpleGestureFilter(this,this);
			
			
			
			progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
			progressBar1.setVisibility(View.GONE);
			
			
			contactList = new ArrayList<HashMap<String, String>>();
			
		    
		    
			// LinearLayour122 = (LinearLayout)findViewById(R.id.LinearLayour122);
			// LinearLayour122.setVisibility(View.GONE);
			 
			 textStatus = (TextView)findViewById(R.id.textOk);
			 textView1 = (TextView)findViewById(R.id.textView1);
			 button1 = (Button) findViewById(R.id.button1);
			 button1.setOnClickListener(this);
			 
			 button1.setVisibility(View.GONE);
			 //Up = (ImageView)findViewById(R.id.imageView3);
			 //Down = (ImageView)findViewById(R.id.imageView2);
			 //Up.setVisibility(View.GONE);
			 
			 //animationRotateDown = AnimationUtils.loadAnimation(
			//			this, R.anim.rotate_down);
			 //animationRotateUp = AnimationUtils.loadAnimation(
			//			this, R.anim.rotate_up);
				
			btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.button_general);
			//Button02 = (Button)findViewById(R.id.Button02);
			//Button02.setOnClickListener(this);
			
			
			
			 
			 textStatus.setTypeface(typefaceMedium);
			 textStatus.setText(R.string.button_update);
		     btn_back.setTypeface(typefaceMedium);
		     textView1.setTypeface(typefaceRoman);
		     button1.setTypeface(typefaceRoman);
	
		     ConnectivityManager conMgr  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
			 NetworkInfo info = conMgr.getActiveNetworkInfo(); 

			if(info != null && info.isConnected()) 
			{
				new GetContacts().execute();
			}
			else
			{
				textView1.setText(R.string.no_connections);
			}
		    	    
	 }
	 
	 @Override
	    public boolean dispatchTouchEvent(MotionEvent me){
	        // Call onTouchEvent of SimpleGestureFilter class
	         this.detector.onTouchEvent(me);
	       return super.dispatchTouchEvent(me);
	    }
	    @Override
	     public void onSwipe(int direction) {
	      String str = "";
	      
	      switch (direction) {
	      
	      case SimpleGestureFilter.SWIPE_RIGHT : 
	    	  Intent intent18 = new Intent(this, ActivityOsnova.class);
	          	 startActivity(intent18);

	    		overridePendingTransition(center_to_right, center_to_right2);
	                                               break;
	     // case SimpleGestureFilter.SWIPE_LEFT :
	    	  //str = "Swipe Left";
	    	 
	                                                    // break;
	      //case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
	                                                    // break;
	      //case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
	                                                     //break;
	      
	      }
	       //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	     }
	      
	     @Override
	     public void onDoubleTap() {
	        //Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	     }
	 
	 private void HideLayoutDown() {

	     final View view=Down;
	     view.postDelayed(new Runnable() {

	        public void run() {
	            if(!Down.isPressed())
	            {
	            view.setVisibility(View.GONE);
	            Up.setVisibility(View.VISIBLE);
	            
	            }
	            else
	            {
	                HideLayoutDown();
	            }
	        }
	    }, 200); // (3000 == 3secs)

	    }
   private void HideLayoutUp() {

	     final View view=Up;
	     view.postDelayed(new Runnable() {

	        public void run() {
	            if(!Up.isPressed())
	            {
	            view.setVisibility(View.GONE);
	            Down.setVisibility(View.VISIBLE);
	            }
	            else
	            {
	                HideLayoutUp();
	            }
	        }
	    }, 200); // (3000 == 3secs)

	    }

	 public void onClick(View v) {
		 int id = v.getId();
		 /*
		  if (id == R.id.Button08){
			 Intent intent = new Intent(this, ActivityInformations.class);
			 startActivity(intent);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
		  
		  else */if (id == R.id.button1){
			  
			  final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
			  try {
			      startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("yastore://details?id=" + appPackageName)));
			  } catch (android.content.ActivityNotFoundException anfe) {
			      //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://khaydarov-studio.bl.ee/fineSettings/")));
			  }
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        	
	        }
	 
	 public void BackClick(View v)  
	    {  
		 Intent intent18 = new Intent(this, ActivityOsnova.class);
      	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
	   	 }
	 
	 @Override
	    public boolean onKeyDown(int keycode, KeyEvent e) {
	        switch(keycode) {
	            case KeyEvent.KEYCODE_MENU:
	            	if (menui == 1){
	           		 openDialog(); 
	           		 
	           	 }
	                return true;
	            case KeyEvent.KEYCODE_BACK:
	            	Intent intent18 = new Intent(this, ActivityOsnova.class);
	             	 startActivity(intent18);

	       		overridePendingTransition(center_to_right, center_to_right2);
	                return true;
	            
	        }
	        return super.onKeyDown(keycode, e);
	   }
	        
	 private void openDialog(){
	     final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
	     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     dialog.setContentView(R.layout.dialog_menu);
	     dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	     Button ButtonInfo = (Button)dialog.getWindow().findViewById(R.id.button1);
	     Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuCancel);
	     Button ButtonMenuSettings = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuSettings);
	     ButtonMenuSettings.setTypeface(typefaceRoman);
	     ButtonMenuCancel.setTypeface(typefaceMedium);
	     ButtonInfo.setTypeface(typefaceRoman);
	     ButtonInfo.setText(R.string.menu_info_main);
	     
	     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View v) {
	    dialog.dismiss();
	   }});
	     
	     ButtonMenuSettings.setOnClickListener(new OnClickListener(){

	  	   @Override
	  	   public void onClick(View v) {
	  		 launchIntent();
	  	   }});
	     
	     dialog.show();
	    }
	   	
	        protected void onResume() {
		        super.onResume();
		        
		        if (mSettings.contains(APP_PREFERENCES_tgb_menu)) {
					// Получаем число из настроек
		        	 Boolean menu = mSettings.getBoolean(APP_PREFERENCES_tgb_menu, true);
					if (menu == true){
						menui=1;
					}
					else{
						menui=0;
					}
		        }
		        
		       
					// Получаем число из настроек
		        	 int speed = mSettings.getInt(APP_PREFERENCES_ANIM_SPEED, 1);
					if (speed == 1){
						center_to_right = R.anim.slide_center_to_right_short;
						center_to_right2 = R.anim.slide_center_to_right2_short;
						center_to_left = R.anim.slide_center_to_left_short;
						center_to_left2 = R.anim.slide_center_to_left2_short;
					}
					if (speed == 2){
						center_to_right = R.anim.slide_center_to_right_medium;
						center_to_right2 = R.anim.slide_center_to_right2_medium;
						center_to_left = R.anim.slide_center_to_left_medium;
						center_to_left2 = R.anim.slide_center_to_left2_medium;
					}
					if (speed == 3){
						center_to_right = R.anim.slide_center_to_right_long;
						center_to_right2 = R.anim.slide_center_to_right2_long;
						center_to_left = R.anim.slide_center_to_left_long;
						center_to_left2 = R.anim.slide_center_to_left2_long;
					}
					
		    
		        
		        ConnectivityManager conMgr  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
				 NetworkInfo info = conMgr.getActiveNetworkInfo(); 
		        if(info != null && info.isConnected()) 
				{
					new GetContacts().execute();
					textView1.setText(R.string.proverka_ios);
				}
				else
				{
					textView1.setText(R.string.no_connections);
				}
		        
	        if (mSettings.contains(APP_PREFERENCES_bold_text)) {
				// Получаем число из настроек
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					textView1.setTypeface(typefaceBold);
				     
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, null);
				if (size .contains( "Small")){
					 textView1.setTextSize(14);
					 button1.setTextSize(14);
				     
				}
				if (size .contains( "Normal")){
					 textView1.setTextSize(16);
					 button1.setTextSize(16);
				     
				}
				if (size .contains( "Large")){
					 textView1.setTextSize(19);
					 button1.setTextSize(19);
				     
				}
				if (size .contains( "xLarge")){
					 textView1.setTextSize(21);
					 button1.setTextSize(21);
				     
				}
	       }
}
	        
	        
	        private void launchIntent() {
	            Intent it = new Intent(ActivityInfo.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 
	        }
	    	
	    	 private class GetContacts extends AsyncTask<Void, Void, Void> {
	    		 
	    	        @Override
	    	        protected void onPreExecute() {
	    	            super.onPreExecute();
	    	            progressBar1.setVisibility(View.VISIBLE);
	    	 
	    	        }
	    	 
	    	        @Override
	    	        protected Void doInBackground(Void... arg0) {
	    	        	
	    	        	
	    	        	
	    	        	
	    	        	try {
	    	        		PackageManager manager = getPackageManager();
	    	        		PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
	    	        		
	    	        		versionCode = info.versionCode;
	    	        		/*
							versionName = getPackageManager()
								    .getPackageInfo(getPackageName(), 0).versionName;*/
						} catch (NameNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    	        	
	    	            // Creating service handler class instance
	    	            ServiceHandler sh = new ServiceHandler();
	    	 
	    	            // Making a request to url and getting response
	    	            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
	    	 
	    	            Log.d("Response: ", "> " + jsonStr);
	    	 
	    	            if (jsonStr != null) {
	    	            	

	    	                try {
	    	                	
	    	                    JSONObject jsonObj = new JSONObject(jsonStr);
	    	                     
	    	                    // Getting JSON Array node
	    	                    version = jsonObj.getJSONArray(TAG_CONTACTS);
	    	                    
	    	                    // looping through All Contacts
	    	                    for (int i = 0; i < version.length(); i++) {
	    	                        JSONObject c = version.getJSONObject(i);
	    	                         
	    	                         idd = c.getString(TAG_ID);

	    	                         
	    	                         
	    	 	    	            /*
	    	 	    	            
	    	                         */
	    	                        // tmp hashmap for single contact
	    	                        HashMap<String, String> contact = new HashMap<String, String>();
	    	 
	    	                        // adding each child node to HashMap key => value
	    	                        contact.put(TAG_ID, idd);

	    	                        // adding contact to contact list
	    	                        contactList.add(contact);
	    	                    }
	    	                } catch (JSONException e) {
	    	                    e.printStackTrace();
	    	                }
	    	            } else {
	    	                Log.e("ServiceHandler", "Couldn't get any data from the url");
	    	            }
	    	            
	    	            
	    	 
	    	            return null;
	    	        }
	    	 
	    	        @Override
	    	        protected void onPostExecute(Void result) {
	    	            super.onPostExecute(result);
	    	            try {
	    	            if (versionCode >= Integer.parseInt(idd)){
 	    	            	textView1.setText(R.string.proverka_ios_no);
 	    	            }
 	    	            else {
 	    	            	/////ЕСТЬ НОВЫЕ ВЕРСИИ
 	    	            	textView1.setText(R.string.proverka_ios_yes);
 	    	            	button1.setVisibility(View.VISIBLE);
 	    	            }
						
	    	            // Dismiss the progress dialog
	    	            progressBar1.setVisibility(View.GONE);
	    	        }
	    	            catch (NumberFormatException e){
	    	            	
	    	            }
	    	        }
	    	 
	    	    }
		 
}
