package ua.mkh.settings.full;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ActivityGeo extends Activity implements OnClickListener, SimpleGestureListener{

	TextView textView1, textView2, textView3;
	ToggleButton tb_gps;
	Intent intent;
	Button bt_geo;
	int OS = android.os.Build.VERSION.SDK_INT;
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   private SimpleGestureFilter detector;
	   

	SharedPreferences mSettings;
	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_geo);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			detector = new SimpleGestureFilter(this,this);
			
			Button btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.app_name);
			bt_geo = (Button)findViewById(R.id.ButtonGeo); 
			tb_gps = (ToggleButton)findViewById(R.id.gpstoggle);
			 tb_gps.setOnClickListener(this);
			
			TextView textStatus = (TextView)findViewById(R.id.textOk);
			textView1 = (TextView)findViewById(R.id.textView1);
			textView2 = (TextView)findViewById(R.id.textView2);
			textView3 = (TextView)findViewById(R.id.textView3);
			
			textStatus.setText(R.string.button_geolocatoin);
	        textStatus.setTypeface(typefaceMedium);
	        btn_back.setTypeface(typefaceMedium);
	        textView1.setTypeface(typefaceRoman);
	        textView2.setTypeface(typefaceMedium);
	        textView3.setTypeface(typefaceRoman);
	        bt_geo.setTypeface(typefaceRoman);
	        
	        
	        
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
	    	  Intent intent18 = new Intent(this, MainActivity.class);
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
	 
	 protected void onResume() {
	        super.onResume();
	       ButtonGps();
	       
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
				
	    
	       
	       if (mSettings.contains(APP_PREFERENCES_bold_text)) {
				// Получаем число из настроек
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					textView1.setTypeface(typefaceBold);
			        textView2.setTypeface(typefaceBold);
			        textView3.setTypeface(typefaceBold);
			        bt_geo.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					textView1.setTextSize(11);
			        textView2.setTextSize(13);
			        textView3.setTextSize(11);
			        bt_geo.setTextSize(14);
				}
				if (size .contains( "Normal")){
					textView1.setTextSize(13);
			        textView2.setTextSize(15);
			        textView3.setTextSize(12);
			        bt_geo.setTextSize(16);
				}
				if (size .contains( "Large")){
					textView1.setTextSize(16);
			        textView2.setTextSize(18);
			        textView3.setTextSize(16);
			        bt_geo.setTextSize(19); 
				}
				if (size .contains( "xLarge")){
					textView1.setTextSize(18);
			        textView2.setTextSize(20);
			        textView3.setTextSize(18);
			        bt_geo.setTextSize(21);
				}
	       }
	    }
	 
	 public void onClick(View v) {
		 int id = v.getId();
		 
	        if (id == R.id.gpstoggle) {
	        	if (OS > 10) {
          			 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
          	        	startActivity(settingsIntent);
        	 	        	overridePendingTransition(center_to_left, center_to_left2);
        	 	        	 }
          		 else {
            	if ((tb_gps).isChecked()) {
            		
            		String provider = Settings.Secure.getString(getContentResolver(),                  
                            Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            		if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
      		poke.setClassName("com.android.settings",  
      		                   "com.android.settings.widget.SettingsAppWidgetProvider");
      		poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
      		poke.setData(Uri.parse("3"));
      		sendBroadcast(poke);
            		}   
            	} 
      		else {
      			String provider2 = Settings.Secure.getString(getContentResolver(),                  
                        Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
      	if(provider2.contains("gps")){ //if gps is disabled
   		final Intent poke = new Intent();
   		poke.setClassName("com.android.settings",  
   		                   "com.android.settings.widget.SettingsAppWidgetProvider");
   		poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
   		poke.setData(Uri.parse("3"));
   		sendBroadcast(poke);
      	}
      	}
	        } }
	 }
	 
	 
	        
	 
	 ///GPS MODE
	    private void ButtonGps() {
	    	LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	            tb_gps.setChecked(true);        
	    }
	    
	    }
	 
	    public void BackClick(View v)  
	    {  
	    	Intent intent18 = new Intent(this, MainActivity.class);
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
	            	Intent intent18 = new Intent(this, MainActivity.class);
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
	   	
	        private void launchIntent() {
	            Intent it = new Intent(ActivityGeo.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
	              	overridePendingTransition(center_to_left, center_to_left2);
	              	 }
		
}
