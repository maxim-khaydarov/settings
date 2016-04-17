package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

public class ActivityRegulatory extends Activity implements OnClickListener, SimpleGestureListener{

	
	
	TextView textStatus;
	
	 private SimpleGestureFilter detector;
	 
	 Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	 
	 public static final String APP_PREFERENCES = "mysettings";
	 public static final String APP_PREFERENCES_tgb_apn = "tgb_apn";
	 public static final String APP_PREFERENCES_tgb_passcode = "tgb_passcode";
	 public static final String APP_PREFERENCES_tgb_privacy = "tgb_privacy";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
	   Button btn_back;
	   
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_regulatory);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
			 btn_back.setText(R.string.button_general);
			 
			 detector = new SimpleGestureFilter(this,this);
			 
			 textStatus = (TextView)findViewById(R.id.textOk);
			 
			 textStatus.setTypeface(typefaceBold);
			 textStatus.setText(R.string.regulatory);
		     btn_back.setTypeface(typefaceMedium);
		     
		     
		     
			
			
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
	      /*case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
	                                                     break;
	      */
	      }
	       //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	     }
	      
	     @Override
	     public void onDoubleTap() {
	        //Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
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
	   	
	        private void launchIntent() {
	            Intent it = new Intent(ActivityRegulatory.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        
	   	
	        public void BackClick(View v)  
	        {  
	        	Intent intent18 = new Intent(this, ActivityOsnova.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	       	 }

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
					
		    
			}
}
