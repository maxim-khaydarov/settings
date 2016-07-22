package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivityBattery extends Activity implements View.OnClickListener{
	
	
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	
	 private static final String SCREEN_BRIGHTNESS_MODE = "screen_brightness_mode";
		private static final int SCREEN_BRIGHTNESS_MODE_MANUAL = 0;
		private static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = 1;
	
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
	   
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	   
	   Button btn_back, btn_low, button01;
	   TextView textStatus, text_low, usage_battery;
	   
	   ToggleButton ToggleButtonLowBattery; 
	
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_battery);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			btn_back = (Button) findViewById(R.id.buttonBack);
			 btn_back.setText(R.string.app_name);
			 textStatus = (TextView)findViewById(R.id.textOk);
			 
			 btn_low = (Button) findViewById(R.id.ButtonLowMode);
			 text_low = (TextView) findViewById(R.id.textView1);
			 usage_battery = (TextView) findViewById(R.id.textView2);
			 button01 = (Button) findViewById(R.id.Button01);
			 button01.setOnClickListener(this);
			 
			 ToggleButtonLowBattery = (ToggleButton) findViewById(R.id.ToggleButtonLowBattery);
			 ToggleButtonLowBattery.setOnClickListener(this);
			 
			 
			 
			 textStatus.setTypeface(typefaceBold);
			 textStatus.setText(R.string.button_battery);
			 btn_low.setTypeface(typefaceRoman);
			 text_low.setTypeface(typefaceRoman);
			 usage_battery.setTypeface(typefaceRoman);
			 button01.setTypeface(typefaceRoman);
			
	   }
	   
	   protected void onResume() {
	        super.onResume();    
	        
	        
	        if (low_mode_check() == true){
	        	ToggleButtonLowBattery.setChecked(true);
	        }
	        
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
					btn_low.setTypeface(typefaceBold);					 
					 text_low.setTypeface(typefaceBold);	
					 usage_battery.setTypeface(typefaceBold);	
					 button01.setTypeface(typefaceBold);	
					
					
				}
	        
	   }
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					btn_low.setTextSize(13);
				    button01.setTextSize(13);
				    text_low.setTextSize(11);
				    usage_battery.setTextSize(11);
				}
				if (size .contains( "Normal")){
				    btn_low.setTextSize(15);
				    button01.setTextSize(15);
				    text_low.setTextSize(13);
				    usage_battery.setTextSize(13);
				}
				if (size .contains( "Large")){
					btn_low.setTextSize(18);
				    button01.setTextSize(18);
				    text_low.setTextSize(15);
				    usage_battery.setTextSize(15);
				}
				if (size .contains( "xLarge")){
					btn_low.setTextSize(20);
				    button01.setTextSize(20);
				    text_low.setTextSize(18);
				    usage_battery.setTextSize(18);
				}
	       }
	   }
	   
	   
	   
	   public boolean low_mode_check() {
		   boolean state = true;
		   boolean state_brightness = true;
		   boolean state_wifi = true;
		   boolean state_bt = true;
		   
		   int status=Settings.System.getInt(getContentResolver(), SCREEN_BRIGHTNESS_MODE, 0);
		   if(status == 1){
			   state_brightness = false;
		   }
		   //0
		   
		   //
		   ContentResolver cResolver = getContentResolver();
	        int brightness = 0;
		   try
	        {
	            //Get the current system brightness
	            brightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);
	        } 
	        catch (SettingNotFoundException e) 
	        {
	            //Throw an error case it couldn't be retrieved
	            Log.e("Error", "Cannot access system brightness");
	            e.printStackTrace();
	        }
		   
		   if (brightness > 25){
			   state_brightness = false;
		   }
		   
		 //WIFI
		   ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    	NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    	WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE); 
	    	if(wifi.isWifiEnabled()){
	    		if(mWifi.isConnected()){
	    			
	    		}
	    		else {
	    	    		state_wifi = false;
	    		}
	    	}
	    	
		//Bluetooth
	    	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	    	if (mBluetoothAdapter.isEnabled()) {
	    		state_bt = false;
	    	}
	    	
	    	if (state_brightness == false || state_wifi == false || state_bt == false){
	    		state = false;
	    	}
	    	
	    	return state;
	}

	          
	 
	     public void onClick(View view)  
	     {  
	         switch(view.getId())  { 
	         
	         case  R.id.ToggleButtonLowBattery: 
	        	 if((ToggleButtonLowBattery).isChecked()){
	     		 enable_low_mode();
	        	 }
	        	 else{
	        		 ContentResolver cResolver = getContentResolver();
				        Window window = getWindow();
				        int brightness = 50;
				    	System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
		                //Get the current window attributes
		                LayoutParams layoutpars = window.getAttributes();
		                //Set the brightness of this window
		                layoutpars.screenBrightness = brightness / (float)255;
		                //Apply attribute changes to this window
		                window.setAttributes(layoutpars);
	        		 Settings.System.putInt(getContentResolver(), SCREEN_BRIGHTNESS_MODE, SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	        			refreshBrightness(-1);
	        	 }
	        	 break;
	        	 
	         case R.id.Button01:
	        	 Intent intentBatteryUsage = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
		            ResolveInfo resolveInfo = getPackageManager().resolveActivity(intentBatteryUsage,0);
		            
		            if(resolveInfo == null){
		             Toast.makeText(ActivityBattery.this, "Not Support!", 
		               Toast.LENGTH_LONG).show();
		            
		            }
		            startActivity(intentBatteryUsage);
		            overridePendingTransition(center_to_left, center_to_left2);
		            
		            break;
	        	 
	         }
	     }  
	         
	         
	         
	   
	            
	    private void enable_low_mode() {
	    	final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     dialog.setContentView(R.layout.dialog_2_button);
		     
		     
		     Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.dialogButtonCancel);
		     Button ButtonMenuContinue = (Button)dialog.getWindow().findViewById(R.id.dialogButtonOK);
		     
		     TextView top = (TextView)dialog.getWindow(). findViewById(R.id.textView1);
		     TextView center = (TextView)dialog.getWindow(). findViewById(R.id.textView2);
		     
		     top.setText(R.string.top_low_mode_dialog);
		     center.setText(R.string.center_low_mode_dialog);
		     
		     ButtonMenuCancel.setTypeface(typefaceRoman);
		     ButtonMenuContinue.setTypeface(typefaceBold);
		     top.setTypeface(typefaceMedium);
		     center.setTypeface(typefaceRoman);
		     
		     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View v) {
		    dialog.dismiss();
		    ToggleButtonLowBattery.setChecked(false);
		   }});
		     
		     ButtonMenuContinue.setOnClickListener(new OnClickListener(){

				   @Override
				   public void onClick(View v) {
				   low_battery_mode();
				   dialog.dismiss();
				   }

				private void low_battery_mode() {
				//Wi-Fi	
					ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			    	NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			    	WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE); 
			    	if(mWifi.isConnected()){
			    		
			    	}
			    	else{
			    		wifi.setWifiEnabled(false);
			    	}
			    //Bluetooth	
			    	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); 
			    	mBluetoothAdapter.disable();
			    //Sync
			    	ContentResolver.setMasterSyncAutomatically(false); 
			    //Brightness
			    	ContentResolver cResolver = getContentResolver();
			        Window window = getWindow();
			        int brightness = 10;
			    	System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
	                //Get the current window attributes
	                LayoutParams layoutpars = window.getAttributes();
	                //Set the brightness of this window
	                layoutpars.screenBrightness = brightness / (float)255;
	                //Apply attribute changes to this window
	                window.setAttributes(layoutpars);
	                
	                //Settings.System.putInt(getContentResolver(), SCREEN_BRIGHTNESS_MODE, SCREEN_BRIGHTNESS_MODE_MANUAL);
        			//refreshBrightness(-1);
					
				}});
		     
		    
		     
		     dialog.show();
		    }
			
	    private void refreshBrightness(float brightness) {
		    WindowManager.LayoutParams lp = getWindow().getAttributes();
		    if (brightness < 0) {        
		    	lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
		    } else {        
		    	lp.screenBrightness = brightness;
		    } 
		    getWindow().setAttributes(lp);
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
	            Intent it = new Intent(ActivityBattery.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        
	   	
	        public void BackClick(View v)  
	        {  
	        	Intent intent18 = new Intent(this, MainActivity.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	       	 }

			
	    

}
