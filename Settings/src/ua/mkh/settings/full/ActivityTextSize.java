package ua.mkh.settings.full;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ActivityTextSize extends Activity   {

	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
	   
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   Button  btn_back;
	   
	   SeekBar textsize;
	   
	   SharedPreferences mSettings;
	   
	   
	   
	   TextView textView1, textView2;
	   
		Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceUltraLight, typefaceThin;

		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_text_size);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String ultraLight = "fonts/Ultralight.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceUltraLight = Typeface.createFromAsset(getAssets(), ultraLight);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			textView1 = (TextView) findViewById(R.id.textView1);
			textView2 = (TextView) findViewById(R.id.textView2);
			
			textView1.setTypeface(typefaceRoman);
			textView2.setTypeface(typefaceRoman);
			
			textsize = (SeekBar) findViewById(R.id.brightbar);
			textsize.setKeyProgressIncrement(1);
			
			textsize.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
	        {
				int progressValue = 0;
				
	            public void onStopTrackingTouch(SeekBar seekBar) 
	            {
	            	if(progressValue == 3)
	                {
	            		Editor editor = mSettings.edit();
	             	   	editor.putString(APP_PREFERENCES_text_size, "xLarge");
	             	   	editor.apply();
	                    
	                }
	                else if (progressValue == 2) 
	                {
	                	Editor editor = mSettings.edit();
	             	   	editor.putString(APP_PREFERENCES_text_size, "Large");
	             	   	editor.apply();
	                    
	                }
	                else if (progressValue == 1){
	                	Editor editor = mSettings.edit();
	             	   	editor.putString(APP_PREFERENCES_text_size, "Normal");
	             	   	editor.apply();
	                }
	                
	                else if (progressValue == 0){
	                	Editor editor = mSettings.edit();
	             	   	editor.putString(APP_PREFERENCES_text_size, "Small");
	             	   	editor.apply();
	                }
	            }
	 
	            public void onStartTrackingTouch(SeekBar seekBar) 
	            {
	                //Nothing handled here
	            }
	 
	            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
	            {
	            	progressValue = progress;

	                if(progress == 3)
	                {
	                    textView1.setTextSize(20);
	                    
	                }
	                else if (progress == 2) 
	                {
	                	textView1.setTextSize(18);
	                    
	                }
	                else if (progress == 1){
	                	textView1.setTextSize(15);
	                }
	                
	                else if (progress == 0){
	                	textView1.setTextSize(13);
	                }
	            }
	        });  
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			
			
			TextView textStatus = (TextView)findViewById(R.id.textOk);
			btn_back = (Button) findViewById(R.id.buttonBack);
			textStatus.setText(R.string.text_size);
			 btn_back.setText(R.string.button_brightness);
		        textStatus.setTypeface(typefaceBold);
		        btn_back.setTypeface(typefaceMedium);
		        
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
						
						textView1.setTypeface(typefaceBold);
						textView2.setTypeface(typefaceBold);
					}
		        }
					
		      
		        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "Normal");
					if (size .contains( "Small")){
						textsize.setProgress(0);
						textView1.setTextSize(13);
						textView2.setTextSize(13);
					}
					if (size .contains( "Normal")){
						textsize.setProgress(1);
						textView1.setTextSize(15);
						textView2.setTextSize(15);
					}
					if (size .contains( "Large")){
						textsize.setProgress(2);
						textView1.setTextSize(18);
						textView2.setTextSize(18);
					}
					if (size .contains( "xLarge")){
						textsize.setProgress(3);
						textView1.setTextSize(20);
						textView2.setTextSize(20);
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
		        	 Intent intent18 = new Intent(this, ActivityBrightness.class);
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
		        Intent it = new Intent(ActivityTextSize.this, SettingsActivity.class);
		        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		        startActivity(it); 
			        	overridePendingTransition(center_to_left, center_to_left2);
		    }
			
			public void BackClick(View v)  
		    {  
				Intent intent18 = new Intent(this, ActivityBrightness.class);
		     	 startActivity(intent18);

				overridePendingTransition(center_to_right, center_to_right2);
		   	 }
			
}
