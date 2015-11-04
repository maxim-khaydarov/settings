package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

public class ActivityHandoff extends Activity implements OnClickListener, SimpleGestureListener{

	Button  btn_back;
	Button Button01, Button02, Button03;
	TextView TextView01, TextView02, textView2;
	ToggleButton tg1, tg2, tg3;
	
	private SimpleGestureFilter detector;
	 
	 Typeface typefaceRoman, typefaceMedium, typefaceBold;
	 TextView textStatus;
	 
	 
	 public static final String APP_PREFERENCES = "mysettings"; 
	 public static final String APP_PREFERENCES_text_size = "txt_size";
	 public static final String APP_PREFERENCES_bold_text = "bold_txt";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	 public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	 public static final String APP_PREFERENCES_HANDOFF = "handoff";
	 public static final String APP_PREFERENCES_SUGGEST_APP = "suggest_app";
	 public static final String APP_PREFERENCES_SUGGEST_STORE = "suggest_store";
	 
	 int menui = 0;
	 
	 int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	 
	 SharedPreferences mSettings;
	 
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_handoff);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String thin = "fonts/Thin.otf";
			String bold =  "fonts/Bold.otf";
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			 detector = new SimpleGestureFilter(this,this);
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
				btn_back.setText(R.string.app_name);
				textStatus = (TextView)findViewById(R.id.textOk);
				
				Button01 = (Button) findViewById(R.id.Button01);
				Button02 = (Button) findViewById(R.id.Button02);
				Button03 = (Button) findViewById(R.id.Button03);
				
				TextView01 = (TextView) findViewById(R.id.TextView01);
				TextView02 = (TextView) findViewById(R.id.TextView02);
				textView2 = (TextView) findViewById(R.id.textView2);
				
				tg1 = (ToggleButton) findViewById(R.id.ToggleButton01);
				tg1.setOnClickListener(this);
				tg2 = (ToggleButton) findViewById(R.id.BTtoggle);
				tg2.setOnClickListener(this);
				tg3 = (ToggleButton) findViewById(R.id.ToggleButton02);
				tg3.setOnClickListener(this);
				
				textStatus.setTypeface(typefaceBold);
				btn_back.setTypeface(typefaceMedium);
				btn_back.setText(R.string.button_general);
				textStatus.setText(R.string.button_handoff);
				Button01.setTypeface(typefaceRoman);
				Button02.setTypeface(typefaceRoman);
				Button03.setTypeface(typefaceRoman);
				TextView01.setTypeface(typefaceRoman);
				TextView02.setTypeface(typefaceRoman);
				textView2.setTypeface(typefaceRoman);
				
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
				
	    
	       
	       if (mSettings.contains(APP_PREFERENCES_bold_text)) {
				// Получаем число из настроек
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					Button01.setTypeface(typefaceBold);
					Button02.setTypeface(typefaceBold);
					Button03.setTypeface(typefaceBold);
					TextView01.setTypeface(typefaceBold);
					TextView02.setTypeface(typefaceBold);
					textView2.setTypeface(typefaceBold);
			        
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					Button01.setTextSize(13);
					Button02.setTextSize(13);
					Button03.setTextSize(13);
					TextView01.setTextSize(11);
					TextView02.setTextSize(11);
					textView2.setTextSize(11);
				}
				if (size .contains( "Normal")){
					Button01.setTextSize(16);
					Button02.setTextSize(16);
					Button03.setTextSize(16);
					TextView01.setTextSize(13);
					TextView02.setTextSize(13);
					textView2.setTextSize(13);
				}
				if (size .contains( "Large")){
					Button01.setTextSize(19);
					Button02.setTextSize(19);
					Button03.setTextSize(19);
					TextView01.setTextSize(16);
					TextView02.setTextSize(16);
					textView2.setTextSize(16);
				}
				if (size .contains( "xLarge")){
					Button01.setTextSize(18);
					Button02.setTextSize(18);
					Button03.setTextSize(18);
					TextView01.setTextSize(21);
					TextView02.setTextSize(21);
					textView2.setTextSize(21);
				}
	       }
	       
	       Boolean handoff = mSettings.getBoolean(APP_PREFERENCES_HANDOFF, true);
	       if(handoff == true){
	    	   tg1.setChecked(true);
	       }
	       else{
	    	   tg1.setChecked(false);
	       }
	       
	       Boolean suggest_app = mSettings.getBoolean(APP_PREFERENCES_SUGGEST_APP, false);
	       if(suggest_app == true){
	    	   tg2.setChecked(true);
	       }
	       else{
	    	   tg2.setChecked(false);
	       }
	       
	       Boolean suggest_store = mSettings.getBoolean(APP_PREFERENCES_SUGGEST_STORE, true);
	       if(suggest_store == true){
	    	   tg3.setChecked(true);
	       }
	       else{
	    	   tg3.setChecked(false);
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
	            Intent it = new Intent(this, SettingsActivity.class);
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
	  
	        
	        public void onClick(View v) {
	        	switch(v.getId()){
	        	
	        	case R.id.ToggleButton01:
	        		if((tg1).isChecked()){
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_HANDOFF, true).commit();
	        		}
	        		else{
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_HANDOFF, false).commit();
	        		}
	        		break;
	        		
	        	case R.id.BTtoggle:
	        		if((tg2).isChecked()){
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_SUGGEST_APP, true).commit();
	        		}
	        		else{
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_SUGGEST_APP, false).commit();
	        		}
	        		break;
	        		
	        	case R.id.ToggleButton02:
	        		if((tg3).isChecked()){
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_SUGGEST_STORE, true).commit();
	        		}
	        		else{
	        			Editor editor = mSettings.edit();
	    			   	editor.putBoolean(APP_PREFERENCES_SUGGEST_STORE, false).commit();
	        		}
	        		break;
	        		
	        	}
	        }
}
