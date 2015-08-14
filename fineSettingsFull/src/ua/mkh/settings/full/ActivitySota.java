package ua.mkh.settings.full;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;





import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivitySota extends Activity implements OnClickListener, SimpleGestureListener{

	ToggleButton tb_data, tb_roum, tb_3g;
	ConnectivityManager dataManager;
	Method dataMtd = null;
	TelephonyManager manager;
	Button Button01, Button02, btn_back, Button03;
	TextView textView1, textView2, textStatus, type;
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	   
	   private SimpleGestureFilter detector;
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_sota);
	        String roman = "fonts/Regular.ttf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.ttf";
			String thin = "fonts/Thin.ttf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			detector = new SimpleGestureFilter(this,this);
			
			
			textView2 = (TextView)findViewById(R.id.textView02);
			textView1 = (TextView)findViewById(R.id.textView1);
	        tb_data = (ToggleButton) findViewById(R.id.soundtoggle);
	        tb_data.setOnClickListener(this);
	        
	        Button01 = (Button)findViewById(R.id.Button01);
	        Button02 = (Button)findViewById(R.id.Button02);
	        Button03 = (Button)findViewById(R.id.Button03);
	        Button03.setOnClickListener(this);
	        
	        textStatus = (TextView)findViewById(R.id.textOk);
	        type = (TextView)findViewById(R.id.textView007);
	        btn_back = (Button) findViewById(R.id.buttonBack);
	        btn_back.setText(R.string.app_name);
	        textStatus.setText(R.string.button_sota);
	        textStatus.setTypeface(typefaceMedium);
	        btn_back.setTypeface(typefaceMedium);
	        
	        textView2.setTypeface(typefaceRoman);
	        textView1.setTypeface(typefaceRoman);
	        Button01.setTypeface(typefaceRoman);
	        Button02.setTypeface(typefaceRoman);
	        Button03.setTypeface(typefaceRoman);
	        type.setTypeface(typefaceRoman);
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
	       ButtonData();
	       state();
	       
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
					textView2.setTypeface(typefaceBold);
			        textView1.setTypeface(typefaceBold);
			        Button01.setTypeface(typefaceBold);
			        Button02.setTypeface(typefaceBold);
			        Button03.setTypeface(typefaceBold);
			        type.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					textView2.setTextSize(11);
			        textView1.setTextSize(11);
			        Button01.setTextSize(14);
			        Button02.setTextSize(14);
			        Button03.setTextSize(14);
			        type.setTextSize(14);
				}
				if (size .contains( "Normal")){
					textView2.setTextSize(13);
			        textView1.setTextSize(13);
			        Button01.setTextSize(16);
			        Button02.setTextSize(16);
			        Button02.setTextSize(16);
			        type.setTextSize(16);
				}
				if (size .contains( "Large")){
					textView2.setTextSize(16);
			        textView1.setTextSize(16);
			        Button01.setTextSize(19);
			        Button02.setTextSize(19);
			        Button02.setTextSize(19);
			        type.setTextSize(19);
				}
				if (size .contains( "xLarge")){
					textView2.setTextSize(18);
			        textView1.setTextSize(18);
			        Button01.setTextSize(21);
			        Button02.setTextSize(21);
			        Button02.setTextSize(21);
			        type.setTextSize(21);
				}
	       }
	       
	    }
	  private void ButtonData () {
	    	dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    	android.net.NetworkInfo mobile = dataManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    	if (mobile.isAvailable() && mobile.getDetailedState() == DetailedState.CONNECTED) {
	    		tb_data.setChecked(true);
	    	}
	    }
	  
	  
	  
	  
	  public void onClick(View v) {
		  int id = v.getId();
		  
	        if (id == R.id.soundtoggle){
	        	try {
            	dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            	if((tb_data).isChecked()) {
            		 try {
     		            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
     		        } catch (NoSuchMethodException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        }
     		        dataMtd.setAccessible(true);
     		        try {
     		            dataMtd.invoke(dataManager, true);
     		        } catch (IllegalArgumentException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        } catch (IllegalAccessException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        } catch (InvocationTargetException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        }   
     			 }
            	else {
            		 try {
     		            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
     		        } catch (NoSuchMethodException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        }
     		        dataMtd.setAccessible(true);
     		        try {
     		            dataMtd.invoke(dataManager, false);
     		        } catch (IllegalArgumentException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        } catch (IllegalAccessException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        } catch (InvocationTargetException e) {
     		            // TODO Auto-generated catch block
     		            e.printStackTrace();
     		        }   
            	}
	        }catch(Exception e){
	        	
	        }
	        }
	        
	        
	        else if (id == R.id.Button03){
	        	Intent settingsIntent = new Intent(android.provider.Settings.ACTION_APN_SETTINGS);
	        	startActivity(settingsIntent);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        
	        }
	        
	       
	       
	  }
	  
	  
	  
	  private void state() {
		  TextView type = (TextView)findViewById(R.id.textView007);
		  TelephonyManager teleMan =  
		            (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		int networkType = teleMan.getNetworkType();	  
	  switch (networkType)
	  {
	        
	  case 4:
	      type.setText(R.string.cdma);
	      break;      
	  case 2:
	      type.setText(R.string.edge);
	      break;  
	        
	  case 1:
	      type.setText(R.string.gprs);
	      break;      
	  case 8:
	      type.setText(R.string.hsdpa);
	      break;      
	  case 10:
	      type.setText(R.string.hspa);
	      break;          
	  case 15:
	      type.setText(R.string.hspa_plus);
	      break;          
	  case 9:
	      type.setText(R.string.hsupa);
	      break;          
	  case 11:
	      type.setText(R.string.iden);
	      break;
	  case 13:
	      type.setText(R.string.lte);
	      break;
	  case 3:
	      type.setText(R.string.umts);
	      break;          
	  case 0:
	      type.setText(R.string.unknown);
	      break;
	  }
	 
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
	            Intent it = new Intent(ActivitySota.this, SettingsActivity.class);
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

