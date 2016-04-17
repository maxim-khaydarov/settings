package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

public class ActivitySearchiPhone  extends Activity implements OnClickListener, SimpleGestureListener{

	ToggleButton tb_search, tb_geo;
	TextView textStatus, textView1, textView2;
	Button btn_back, Button01, Button02;
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	
	
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   public static final String APP_PREFERENCES_SEARCH = "search";
	   public static final String APP_PREFERENCES_GEO = "geo";
	   
	   int menui = 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	   private SimpleGestureFilter detector;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String thin = "fonts/Thin.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);
		
		
		textStatus  = (TextView)findViewById(R.id.textOk);
		 btn_back = (Button) findViewById(R.id.buttonBack);
		 
		
		Button01 = (Button) findViewById(R.id.Button01);
		Button02 = (Button) findViewById(R.id.Button02);
		tb_search = (ToggleButton) findViewById(R.id.ToggleButton01);
		tb_search.setOnClickListener(this);
		tb_geo = (ToggleButton) findViewById(R.id.BTtoggle);
		tb_geo.setOnClickListener(this);
		textView1 = (TextView) findViewById(R.id.TextView01);
		textView2 = (TextView) findViewById(R.id.textView2);
		
		String t1 = getString(R.string.search_iphone_text_1_1);
        String t2s = getString(R.string.search_iphone_text_1_2);
        
        String t2 = "<font color=\"#0071ED\">" + t2s + "</font>";
        String t12 = t1 + " " + t2;
        textView1.setText(Html.fromHtml(t12), TextView.BufferType.SPANNABLE);
        
        textStatus.setTypeface(typefaceBold);
		 textStatus.setText(R.string.search_iphone);
	     btn_back.setTypeface(typefaceMedium);
	     btn_back.setText(R.string.icloud);
	     textView1.setTypeface(typefaceRoman);
	     textView2.setTypeface(typefaceRoman);
	     Button01.setTypeface(typefaceRoman);
	     Button02.setTypeface(typefaceRoman);
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
    	  Intent intent18 = new Intent(this, ActivityiCloud.class);
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
         
         Boolean search = mSettings.getBoolean(APP_PREFERENCES_SEARCH, true);
			if (search == true){
				tb_search.setChecked(true);
			}
			else{
				tb_search.setChecked(false);
			}
			
			 Boolean geo = mSettings.getBoolean(APP_PREFERENCES_GEO, false);
				if (geo == true){
					tb_geo.setChecked(true);
				}
				else{
					tb_geo.setChecked(false);
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
					 textView1.setTypeface(typefaceBold);
				    textView2.setTypeface(typefaceBold);
				    
				    Button01.setTypeface(typefaceBold);
				    Button02.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					textView1.setTextSize(11);
				    textView2.setTextSize(11);
				    
				    Button01.setTextSize(13);
				    Button02.setTextSize(13);
				}
				if (size.contains( "Normal")){
					textView1.setTextSize(13);
				    textView2.setTextSize(13);
				    
				    Button01.setTextSize(15);
				    Button02.setTextSize(15);
				}
				if (size .contains( "Large")){
					textView1.setTextSize(15);
				    textView2.setTextSize(15);
				    
				    Button01.setTextSize(18);
				    Button02.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					textView1.setTextSize(18);
				    textView2.setTextSize(18);
				    
				    Button01.setTextSize(20);
				    Button02.setTextSize(20);
				}
	       }
	       
     }
     
     public void BackClick(View v)  
     {  
  	   Intent intent18 = new Intent(this, ActivityiCloud.class);
      	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
    	 }
     
     
     public void onClick(View v) {
		switch(v.getId()){
		case R.id.ToggleButton01:
			if((tb_search).isChecked()) {
				Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_SEARCH, true).commit();
         	 }
         	 else {
         		Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_SEARCH, false).commit();
         	 }
			break;
			
		case R.id.BTtoggle:
			if((tb_geo).isChecked()) {
				Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_GEO, true);
			   	editor.apply();
         	 }
         	 else {
         		Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_GEO, false);
			   	editor.apply();
         	 }
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
	            	Intent intent18 = new Intent(this, ActivityiCloud.class);
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
	   	
}
