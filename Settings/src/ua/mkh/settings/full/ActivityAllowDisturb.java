package ua.mkh.settings.full;


import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ActivityAllowDisturb extends Activity implements OnClickListener, SimpleGestureListener,  RadioGroup.OnCheckedChangeListener{

	 public static final String APP_PREFERENCES_ALLOW_CALL = "allow_call";
	 
	 public static final String APP_PREFERENCES = "mysettings";
		public static final String APP_PREFERENCES_text_size = "txt_size";
		public static final String APP_PREFERENCES_bold_text = "bold_txt";
		   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	 
	 Typeface typefaceRoman, typefaceMedium, typefaceBold;
	 
	 int menui = 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   Button btn_back;
	   TextView textStatus;
	   
	   private SimpleGestureFilter detector;
	   private RadioButton everyone, noone, contacts;
	
	SharedPreferences mSettings;
	String blockingMode;
	
	private RadioButton always, locked;
	
	
	
	@Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_allow_call);
      String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String thin = "fonts/Thin.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		Typeface typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);
		
		everyone=(RadioButton) findViewById(R.id.radio0);
		noone=(RadioButton) findViewById(R.id.radio1);
		contacts=(RadioButton) findViewById(R.id.radio2);
		
		
		btn_back = (Button) findViewById(R.id.buttonBack);
        btn_back.setText(R.string.back);
        textStatus = (TextView)findViewById(R.id.textOk);
        
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
      radiogroup.setOnCheckedChangeListener(this);
   // radio button setting
      
      textStatus.setText(R.string.disturb);
      textStatus.setTypeface(typefaceMedium);
      btn_back.setTypeface(typefaceBold);
      everyone.setTypeface(typefaceRoman);
      noone.setTypeface(typefaceRoman);
      contacts.setTypeface(typefaceRoman);
      
}

	
	public void onClick(View v) {
        int id = v.getId();
        
        
	}
	
	
	
	public void loadData (){
		blockingMode = mSettings.getString(APP_PREFERENCES_ALLOW_CALL, "no one");
		
		if (blockingMode.contains("no one")){
			noone.setChecked(true);
		}
		else if (blockingMode.contains("everyone")){
			everyone.setChecked(true);
		}
		else {
			contacts.setChecked(true);
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
    	  Intent intent18 = new Intent(this, ActivityDisturb.class);
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
     
     public void onCheckedChanged(RadioGroup group, int checkedId) {
	        switch (checkedId) {
	        case R.id.radio0:
	        	Editor editor = mSettings.edit();
			   	editor.putString(APP_PREFERENCES_ALLOW_CALL, "everyone");//от всех
			   	editor.commit();
	      
	          break;

	        case R.id.radio1:
	        	Editor editor2 = mSettings.edit();
			   	editor2.putString(APP_PREFERENCES_ALLOW_CALL, "no one");//ни от кого
			   	editor2.commit();
	         
	          break;
	          
	        case R.id.radio2:
	        	Editor editor3 = mSettings.edit();
			   	editor3.putString(APP_PREFERENCES_ALLOW_CALL, "contacts");//не сохраненные
			   	editor3.commit();
	         
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
	            	Intent intent18 = new Intent(this, ActivityDisturb.class);
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
	            Intent it = new Intent(ActivityAllowDisturb.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	        }
		
	        public void BackClick(View v)  
	        {  
	    		Intent intent18 = new Intent(this, ActivityDisturb.class);
	         	 startActivity(intent18);

	    		overridePendingTransition(center_to_right, center_to_right2);
	       	 }
	        
	        protected void onResume() {
	            super.onResume();
	           
	            loadData ();
	           
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
	    				everyone.setTypeface(typefaceBold);
	    			      noone.setTypeface(typefaceBold);
	    			      contacts.setTypeface(typefaceBold);
	    				
	    			}
	           }
	    			
	          if (mSettings.contains(APP_PREFERENCES_text_size)) {
	    			// Получаем число из настроек
	           	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
	    			if (size .contains( "Small")){
	    				everyone.setTextSize(13);
	    				noone.setTextSize(13);
	    				contacts.setTextSize(13);
	    			}
	    			if (size .contains( "Normal")){
	    				everyone.setTextSize(15);
	    				noone.setTextSize(15);
	    				contacts.setTextSize(15);
	    			}
	    			if (size .contains( "Large")){
	    				everyone.setTextSize(18);
	    				noone.setTextSize(18);
	    				contacts.setTextSize(18);
	    			}
	    			if (size .contains( "xLarge")){
	    				everyone.setTextSize(20);
	    				noone.setTextSize(20);
	    				contacts.setTextSize(20);
	    			}
	    			
	          }
	        }
	
}