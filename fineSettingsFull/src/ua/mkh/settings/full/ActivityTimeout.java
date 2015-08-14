package ua.mkh.settings.full;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ActivityTimeout extends Activity implements OnClickListener, SimpleGestureListener, RadioGroup.OnCheckedChangeListener{

	Button btn_back;
    Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
    TextView textStatus;
    
    public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui= 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   private RadioButton s15, s30, m1, m2, m10, m30;
	   
	   
	   SharedPreferences mSettings;
	   private SimpleGestureFilter detector;
	   
	   protected void onCreate(Bundle savedInstanceState) {
		     super.onCreate(savedInstanceState);
		     setContentView(R.layout.activity_timeout);
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
		     
		     btn_back = (Button) findViewById(R.id.buttonBack);
				btn_back.setText(R.string.button_general);
				textStatus = (TextView)findViewById(R.id.textOk);
				
				s15 = (RadioButton) findViewById(R.id.radio0);
				s30 = (RadioButton) findViewById(R.id.radio1);
				m1 = (RadioButton) findViewById(R.id.radio2);
				m2 = (RadioButton) findViewById(R.id.radio3);
				m10 = (RadioButton) findViewById(R.id.radio4);
				m30 = (RadioButton) findViewById(R.id.radio5);
				
				s15.setText("15" + " " + getResources().getString(R.string.secundes));
				s30.setText("30" + " " +  getResources().getString(R.string.secundes));
				m1.setText("1"  + " " +  getResources().getString(R.string.minutes));
				m2.setText("2"  + " " +  getResources().getString(R.string.minutes));
				m10.setText("10"  + " " +  getResources().getString(R.string.minutes));
				m30.setText("30"  + " " +  getResources().getString(R.string.minutes));
				
				
				textStatus.setTypeface(typefaceMedium);
				btn_back.setTypeface(typefaceMedium);
				textStatus.setText(R.string.choose);
				
				RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
			     radiogroup.setOnCheckedChangeListener(this);
				
	   }
	   
	   
	   public void onCheckedChanged(RadioGroup group, int checkedId) {
	        switch (checkedId) {
	        case R.id.radio0:
	        	 android.provider.Settings.System.putInt(getContentResolver(),
	        	            Settings.System.SCREEN_OFF_TIMEOUT, 15000);
	      
	          break;

	        case R.id.radio1:
	        	 android.provider.Settings.System.putInt(getContentResolver(),
	        	            Settings.System.SCREEN_OFF_TIMEOUT, 30000);
	         
	          break;
	          
	        case R.id.radio2:
	        	android.provider.Settings.System.putInt(getContentResolver(),
        	            Settings.System.SCREEN_OFF_TIMEOUT, 60000);
	          break;
	          
	        case R.id.radio3:
	        	android.provider.Settings.System.putInt(getContentResolver(),
        	            Settings.System.SCREEN_OFF_TIMEOUT, 120000);
	          break;
	          
	        case R.id.radio4:
	        	android.provider.Settings.System.putInt(getContentResolver(),
        	            Settings.System.SCREEN_OFF_TIMEOUT, 600000);
	          break;
	          
	        case R.id.radio5:
	        	android.provider.Settings.System.putInt(getContentResolver(),
        	            Settings.System.SCREEN_OFF_TIMEOUT, 1800000);
	          break;
	       
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
	     
	     protected void onResume() {
		        super.onResume();
		        
		        loadData();
		        
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
						
						
					}
		        }
					
		       if (mSettings.contains(APP_PREFERENCES_text_size)) {
					// Получаем число из настроек
		        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
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
	     
	     private void loadData() {
	    	 long stand = Settings.System.getLong(
	                 this.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT,
	                 -1);
	         long sec = stand / 1000;
	         String time = null;
	         time = String.valueOf(sec);
	         
	         
	         
	         if (time.contains("15")){
	        	 s15.setChecked(true);
	         }
	         else if (time.contains("30")){
	        	 s30.setChecked(true);
	         }
	         else if (time.contains("60")){
	        	 m1.setChecked(true);
	         }
	         else if (time.contains("120")){
	        	 m2.setChecked(true);
	         }
	         else if (time.contains("600")){
	        	 m10.setChecked(true);
	         }
	         else if (time.contains("1800")){
	        	 m30.setChecked(true);
	         }
			
		}


		@Override
	 	public void onClick(View view) {
	 		// TODO Auto-generated method stub
	 		 switch(view.getId())  { 
	 		 case R.id.button1:
	 			
	 			break;
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
	             Intent it = new Intent(ActivityTimeout.this, SettingsActivity.class);
	             it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	             startActivity(it); 
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	         
}
