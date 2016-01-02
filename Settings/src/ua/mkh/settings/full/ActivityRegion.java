package ua.mkh.settings.full;

import java.util.Currency;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

public class ActivityRegion extends Activity implements OnClickListener, SimpleGestureListener{

	private SimpleGestureFilter detector;
	
	Button  btn_back, Button01, Button02, Button03, Button08, Button09;
	TextView textStatus, TextView01, TextView02, TextView07, TextView08, textView1, textView2;
	int menui = 0;
	SharedPreferences mSettings;
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	 public static final String APP_PREFERENCES_bold_text = "bold_txt";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	 public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	 
	 Typeface typefaceRoman,  typefaceMedium, typefaceBold;
	 
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_region);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String thin = "fonts/Thin.otf";
			String bold = "fonts/Bold.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			Typeface typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			textStatus = (TextView)findViewById(R.id.textOk);
			
			TextView01 = (TextView) findViewById(R.id.TextView01);
			TextView02 = (TextView) findViewById(R.id.TextView02);
			TextView07 = (TextView) findViewById(R.id.TextView07);
			TextView08 = (TextView) findViewById(R.id.TextView08);
			textView1 = (TextView) findViewById(R.id.textView1);
			textView2 = (TextView) findViewById(R.id.textView2);
			
			Button01 = (Button) findViewById(R.id.Button01);
			Button02 = (Button) findViewById(R.id.Button02);
			Button02.setOnClickListener(this);
			Button03 = (Button) findViewById(R.id.Button03);
			Button03.setOnClickListener(this);
			Button08 = (Button) findViewById(R.id.Button08);
			Button09 = (Button) findViewById(R.id.Button09);
			
			btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.app_name);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			detector = new SimpleGestureFilter(this,this);
			
			textStatus.setTypeface(typefaceBold);
			textStatus.setText(R.string.language_region);
			btn_back.setTypeface(typefaceMedium);
			Button01.setTypeface(typefaceRoman);
			Button02.setTypeface(typefaceRoman);
			Button03.setTypeface(typefaceRoman);
			Button08.setTypeface(typefaceRoman);
			Button09.setTypeface(typefaceRoman);
			TextView01.setTypeface(typefaceRoman);
			TextView02.setTypeface(typefaceRoman);
			TextView07.setTypeface(typefaceRoman);
			TextView08.setTypeface(typefaceRoman);
			textView1.setTypeface(typefaceRoman);
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
		      
		        get_lang();
		        
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
					
					}
		       if (mSettings.contains(APP_PREFERENCES_text_size)) {
					// Получаем число из настроек
		        	 String size = mSettings.getString(APP_PREFERENCES_text_size, null);
					if (size .contains( "Small")){
					
					}
					if (size .contains( "Normal")){
						
					}
					if (size .contains( "Large")){
						
					}
					if (size .contains( "xLarge")){
						
					}
		    }
		       }
		 }
					
		

		 
		 private void get_lang() {
			
				 TextView02.setText(Locale.getDefault().getDisplayLanguage());
				 TextView07.setText(Locale.getDefault().getDisplayCountry());
			 
				 String str;
				 str = getText(R.string.region_time).toString();
				 
				 if (!DateFormat.is24HourFormat(this)){
					 str = str + getText(R.string.am).toString();
				 }
				 
				 Locale defaultLocale = Locale.getDefault();
				 Currency currency = Currency.getInstance(defaultLocale);
				 
				 str = str + "\n" + getText(R.string.region_date).toString() +  " " + currency.getSymbol() + "      " + getText(R.string.region_number).toString();;
				 
				 textView2.setText(str);
			
		}

		 public static void main(String[] args) throws Exception {
				Locale defaultLocale = Locale.getDefault();
				displayCurrencyInfoForLocale(defaultLocale);

			}

			public static void displayCurrencyInfoForLocale(Locale locale) {
				System.out.println("Locale: " + locale.getDisplayName());
				Currency currency = Currency.getInstance(locale);
				System.out.println("Currency Code: " + currency.getCurrencyCode());
				System.out.println("Symbol: " + currency.getSymbol());
				System.out.println("Default Fraction Digits: " + currency.getDefaultFractionDigits());
				System.out.println();
			}
			

		public void onClick(View v) {
			 switch(v.getId()){
				 
			 case R.id.Button03:
				 startActivity(new Intent("android.settings.LOCALE_SETTINGS"));
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

	 
		
	     
	     
		private void launchIntent() {
	        Intent it = new Intent(this, SettingsActivity.class);  
	        startActivity(it);
	        	overridePendingTransition(center_to_right, center_to_right2);
	        	 }
	    
}
