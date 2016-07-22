package ua.mkh.settings.full;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ActivityBrightness extends Activity implements View.OnClickListener{
	
	 private static final String SCREEN_BRIGHTNESS_MODE = "screen_brightness_mode";
		private static final int SCREEN_BRIGHTNESS_MODE_MANUAL = 0;
		private static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = 1;
		final int RESULT_LOAD_IMAGE=1;
			
		int OS = android.os.Build.VERSION.SDK_INT;
		
		
		SeekBar brightbar;
		int brightness;
		TextView txtPerc, textView1, textView2, textStatus;
		ContentResolver cResolver;
		ToggleButton tb_brtns, tb_bold;
		Window window;
		Button  btn_back, brtn1, Button01, btn_textSize,
		btn_textBold;
		ToggleButton tb_oboi;
		ImageView img_menu, img_lock;
		Typeface typefaceRoman,  typefaceMedium, typefaceBold;
		
		String text_size;
		int menui = 0;
		SharedPreferences mSettings;
		public static final String APP_PREFERENCES = "mysettings";
		public static final String APP_PREFERENCES_text_size = "txt_size";
		 public static final String APP_PREFERENCES_bold_text = "bold_txt";
		 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		 public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
		 
			
		   int center_to_right, center_to_right2;
		   int center_to_left, center_to_left2;
		
		private final static int BUFFER_SIZE = 2048;
		
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String thin = "fonts/Thin.otf";
		String bold = "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		Typeface typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		textStatus = (TextView)findViewById(R.id.textOk);
		textView1 = (TextView)findViewById(R.id.textView1);
		textView2 = (TextView)findViewById(R.id.textView2);
		
		btn_back = (Button) findViewById(R.id.buttonBack);
		btn_back.setText(R.string.app_name);
		btn_textSize = (Button) findViewById(R.id.ButtonTextSize);
		btn_textSize.setOnClickListener(this);
		btn_textBold = (Button) findViewById(R.id.ButtonTextBold);
		brtn1 = (Button) findViewById(R.id.brtn1);
		tb_bold = (ToggleButton) findViewById(R.id.ToggleButtonBoldText);
		tb_bold.setOnClickListener(this);
		Button01 = (Button) findViewById(R.id.Button01);
		Button01.setOnClickListener(this);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		
		
		 textStatus.setText(R.string.button_brightness);
	        textStatus.setTypeface(typefaceBold);
	        btn_back.setTypeface(typefaceMedium);
	        textView1.setTypeface(typefaceRoman);
	        textView2.setTypeface(typefaceRoman);
	        brtn1.setTypeface(typefaceRoman);

	        Button01.setTypeface(typefaceRoman);
	        btn_textSize.setTypeface(typefaceRoman);
	        btn_textBold.setTypeface(typefaceRoman);
	        
	        tb_brtns = (ToggleButton) findViewById(R.id.brtntoggle);
	        tb_brtns.setOnClickListener(this);
	        brightbar = (SeekBar) findViewById(R.id.brightbar);
	        txtPerc = (TextView) findViewById(R.id.txtPercentage);
	        cResolver = getContentResolver();
	        window = getWindow();
	        brightbar.setMax(255);
	        brightbar.setKeyProgressIncrement(1);
	
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
	        brightbar.setProgress(brightness);
	        
	        brightbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
	        {
	            public void onStopTrackingTouch(SeekBar seekBar) 
	            {
	                //Set the system brightness using the brightness variable value
	                System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
	                //Get the current window attributes
	                LayoutParams layoutpars = window.getAttributes();
	                //Set the brightness of this window
	                layoutpars.screenBrightness = brightness / (float)255;
	                //Apply attribute changes to this window
	                window.setAttributes(layoutpars);
	            }
	 
	            public void onStartTrackingTouch(SeekBar seekBar) 
	            {
	                //Nothing handled here
	            }
	 
	            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
	            {
	            	//Set the system brightness using the brightness variable value
	                System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
	                //Get the current window attributes
	                LayoutParams layoutpars = window.getAttributes();
	                //Set the brightness of this window
	                layoutpars.screenBrightness = brightness / (float)255;
	                //Apply attribute changes to this window
	                window.setAttributes(layoutpars);
	                //Set the minimal brightness level
	                //if seek bar is 20 or any value below
	                if(progress<=10)
	                {
	                    //Set the brightness to 20
	                    brightness=10;
	                }
	                else //brightness is greater than 20
	                {
	                    //Set brightness variable based on the progress bar 
	                    brightness = progress;
	                }
	                //Calculate the brightness percentage
	                /*float perc = (brightness /(float)255)*100;
	                //Set the brightness percentage 
	                txtPerc.setText(" "+(int)perc +" %");*/
	            }
	        });  
	      	}
	
	 protected void onResume() {
	        super.onResume();
	       ButtonBrtns();
	      
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
	        
	       
	       
	       if (mSettings.contains(APP_PREFERENCES_bold_text)) {
				// Получаем число из настроек
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
				tb_bold.setChecked(true);
				textView1.setTypeface(typefaceBold);
				textView2.setTypeface(typefaceBold);
				brtn1.setTypeface(typefaceBold);
				btn_textSize.setTypeface(typefaceBold);
				btn_textBold.setTypeface(typefaceBold);
				Button01.setTypeface(typefaceBold);
				}
				else
					tb_bold.setChecked(false);
				}
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, null);
				if (size .contains( "Small")){
				textView1.setTextSize(11);
				textView2.setTextSize(11);
				brtn1.setTextSize(13);
				btn_textSize.setTextSize(13);
				btn_textBold.setTextSize(13);
				Button01.setTextSize(13);
				}
				if (size .contains( "Normal")){
					textView1.setTextSize(13);
					textView2.setTextSize(13);
					brtn1.setTextSize(15);
					btn_textSize.setTextSize(15);
					btn_textBold.setTextSize(15);
					Button01.setTextSize(15);
				}
				if (size .contains( "Large")){
					textView1.setTextSize(15);
					textView2.setTextSize(15);
					brtn1.setTextSize(18);
					btn_textSize.setTextSize(18);
					btn_textBold.setTextSize(18);
					Button01.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					textView1.setTextSize(18);
					textView2.setTextSize(18);
					brtn1.setTextSize(20);
					btn_textSize.setTextSize(20);
					btn_textBold.setTextSize(20);
					Button01.setTextSize(20);
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
	
	 
	 
	/// BRIGHTNESS MODE   		 
	    private void refreshBrightness(float brightness) {
		    WindowManager.LayoutParams lp = getWindow().getAttributes();
		    if (brightness < 0) {         lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
		    } else {         lp.screenBrightness = brightness;
		    }     getWindow().setAttributes(lp);
		}
	    
	    private void ButtonBrtns () {
	    	int status=Settings.System.getInt(getContentResolver(), SCREEN_BRIGHTNESS_MODE, 0);
	    	if (status == 1)  {
	    		tb_brtns.setChecked(true);
	    	}    
	    }
	    
	    
	    public void onClick(View v) {
	    	int id = v.getId();
	    	
	    	if (id == R.id.ToggleButtonBoldText){
            	if((tb_bold).isChecked()) {
    				textView1.setTypeface(typefaceBold);
    				textView2.setTypeface(typefaceBold);
    				brtn1.setTypeface(typefaceBold);
    				btn_textSize.setTypeface(typefaceBold);
    				btn_textBold.setTypeface(typefaceBold);
    				Button01.setTypeface(typefaceBold);
    				
            		Editor editor = mSettings.edit();
				   	editor.putBoolean(APP_PREFERENCES_bold_text, true);
				   	editor.apply();
            	}
            	else {
    				textView1.setTypeface(typefaceRoman);
    				textView2.setTypeface(typefaceRoman);
    				brtn1.setTypeface(typefaceRoman);
    				btn_textSize.setTypeface(typefaceRoman);
    				btn_textBold.setTypeface(typefaceRoman);
    				Button01.setTypeface(typefaceRoman);
    				
            		Editor editor = mSettings.edit();
    			   	editor.putBoolean(APP_PREFERENCES_bold_text, false);
    			   	editor.apply();
            	}
	    	}
	    	
	    	else if (id == R.id.brtntoggle){
            	if((tb_brtns).isChecked()) {
            		Settings.System.putInt(getContentResolver(), SCREEN_BRIGHTNESS_MODE, SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        			refreshBrightness(-1);
            	}
            	else {
            		Settings.System.putInt(getContentResolver(), SCREEN_BRIGHTNESS_MODE, SCREEN_BRIGHTNESS_MODE_MANUAL);
        			refreshBrightness(-1);
            	}
	    	}
            	
	        	
	    	else if (id == R.id.Button04){
	        	Intent settingsIntent = new Intent(android.provider.Settings.ACTION_DISPLAY_SETTINGS);
	        	startActivity(settingsIntent);
	              	overridePendingTransition(center_to_left, center_to_left2);
	              	 }
	    	
	    	else if (id == R.id.ButtonTextSize){
	    		Intent intent18 = new Intent(this, ActivityTextSize.class);
		     	 startActivity(intent18);

				overridePendingTransition(center_to_right, center_to_right2);
	    		
	    	}
	    	
	    	else if (id == R.id.Button01){
	    		speed_anim();
	    	}
	    	
	    	

	    }
	    private void speed_anim() {
	    	final String[] mCatsName ={"Long", "Medium", "Short"};
	    	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
	         builder = new AlertDialog.Builder(this);
	         //builder.setTitle(R.string.text_size); // заголовок для диалога

	         builder.setItems(mCatsName, new DialogInterface.OnClickListener() {
	             @Override
	             public void onClick(DialogInterface dialog, int item) {
	            	 int s = 0;
	            	 if (mCatsName[item] .contains( "Long")){
	     				s = 3;
	     				}
	     				if (mCatsName[item] .contains( "Medium")){
	     				s = 2;
	     				}
	     				if (mCatsName[item] .contains( "Short")){
	     					s = 1;
	     				}
	            	 
	                 // TODO Auto-generated method stub
	            	 Editor editor = mSettings.edit();
	         	   	editor.putInt(APP_PREFERENCES_ANIM_SPEED, s);
	         	   	editor.apply();
	         	   	
	         	   Intent intent = getIntent();
	         	    finish();
	         	   intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	         	    startActivity(intent);
	            	 
	             }
	         });
	         builder.setCancelable(false);
	         builder.show();
			
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
     final Dialog dialog = new Dialog(ActivityBrightness.this,android.R.style.Theme_Translucent);
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
        Intent it = new Intent(ActivityBrightness.this, SettingsActivity.class);  
        startActivity(it);
        	overridePendingTransition(center_to_right, center_to_right2);
        	 }
    
	
    }

	
    
	

