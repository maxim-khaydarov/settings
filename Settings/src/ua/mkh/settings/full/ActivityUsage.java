package ua.mkh.settings.full;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivityUsage extends Activity implements OnClickListener, SimpleGestureListener{
	
	
	
	
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
	   
	   TextView textStatus, textView1, TextView01, TextView07, TextView08;
	   Button btn_back, Button01, Button02, Button06, Button08, Button09;
	   ToggleButton tgb1;
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_usage);
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
			
			textView1 = (TextView) findViewById(R.id.textView1);
			TextView01 = (TextView) findViewById(R.id.TextView01);
			TextView07 = (TextView) findViewById(R.id.TextView07);
			TextView08 = (TextView) findViewById(R.id.TextView08);
			
			Button01 = (Button)findViewById(R.id.Button01);
			Button02 = (Button)findViewById(R.id.Button02);
			Button02.setOnClickListener(this);
			Button06 = (Button)findViewById(R.id.Button06);
			Button06.setOnClickListener(this);
			Button08 = (Button)findViewById(R.id.Button08);
			Button09 = (Button)findViewById(R.id.Button09);
			tgb1 = (ToggleButton) findViewById(R.id.ToggleButton01);
			tgb1.setChecked(true);
			
			textStatus = (TextView)findViewById(R.id.textOk);
			
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
		        btn_back.setText(R.string.button_general);
		        textStatus.setText(R.string.button_usage);
		        textStatus.setTypeface(typefaceBold);
		        btn_back.setTypeface(typefaceMedium);
		        
		        Button01.setTypeface(typefaceRoman);
		        Button02.setTypeface(typefaceRoman);
		        Button06.setTypeface(typefaceRoman);
		        Button08.setTypeface(typefaceRoman);
		        Button09.setTypeface(typefaceRoman);
		        textView1.setTypeface(typefaceRoman);
		        TextView01.setTypeface(typefaceRoman);
		        TextView07.setTypeface(typefaceRoman);
		        TextView08.setTypeface(typefaceRoman);
	
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
	        memory ();
	       
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
			        Button06.setTypeface(typefaceBold);
			        Button08.setTypeface(typefaceBold);
			        Button09.setTypeface(typefaceBold);
			        textView1.setTypeface(typefaceBold);
			        TextView01.setTypeface(typefaceBold);
			        TextView07.setTypeface(typefaceBold);
			        TextView08.setTypeface(typefaceBold);
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					Button01.setTextSize(14);
			        Button02.setTextSize(14);
			        Button06.setTextSize(14);
			        Button08.setTextSize(14);
			        Button09.setTextSize(14);
			        textView1.setTextSize(14);
					TextView01.setTextSize(14);
					TextView07.setTextSize(14);
					TextView08.setTextSize(14);
			        
				}
				if (size .contains( "Normal")){
					Button01.setTextSize(16);
			        Button02.setTextSize(16);
			        Button06.setTextSize(16);
			        Button08.setTextSize(16);
			        Button09.setTextSize(16);
			        textView1.setTextSize(16);
					TextView01.setTextSize(16);
					TextView07.setTextSize(16);
					TextView08.setTextSize(16);
				}
				if (size .contains( "Large")){
					Button01.setTextSize(19);
			        Button02.setTextSize(19);
			        Button06.setTextSize(19);
			        Button08.setTextSize(19);
			        Button09.setTextSize(19);
			        textView1.setTextSize(19);
					TextView01.setTextSize(19);
					TextView07.setTextSize(19);
					TextView08.setTextSize(19);
				}
				if (size .contains( "xLarge")){
					Button01.setTextSize(21);
			        Button02.setTextSize(21);
			        Button06.setTextSize(21);
			        Button08.setTextSize(21);
			        Button09.setTextSize(21);
			        textView1.setTextSize(21);
					TextView01.setTextSize(21);
					TextView07.setTextSize(21);
					TextView08.setTextSize(21);
				}
	       }
	       
	    }
	  
	  public void onClick(View v) {
		  switch(v.getId())  { 
		  case R.id.Button06:
			  Intent intentBatteryUsage = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
	            ResolveInfo resolveInfo = getPackageManager().resolveActivity(intentBatteryUsage,0);
	            
	            if(resolveInfo == null){
	             Toast.makeText(ActivityUsage.this, "Not Support!", 
	               Toast.LENGTH_LONG).show();
	            
	            }
	            startActivity(intentBatteryUsage);
	            overridePendingTransition(center_to_left, center_to_left2);
	            break;
	            
		  case R.id.Button02:
			  Intent intent = new Intent(this, ActivityApps.class);
			  intent.putExtra("used", TextView07.getText().toString());
			  intent.putExtra("aviable", TextView08.getText().toString());
	        	 startActivity(intent);
	  	        	overridePendingTransition(center_to_left, center_to_left2);
			  /*
			  Intent dialogIntent = new Intent(android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
              dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(dialogIntent);
              overridePendingTransition(center_to_left, center_to_left2);*/
              break;
		  }
		  
	  }
	  
	  public void memory (){
		   Float ios = 0f;
		   Float iss = 0f;
		   
		   List<StorageUtils.StorageInfo> storageList = StorageUtils.getStorageList();
		    for (StorageUtils.StorageInfo storageInfo : storageList) {
		    	
		    	Float o = Float.parseFloat(String.valueOf(new File(storageInfo.path).getTotalSpace()));
		    	Float s = Float.parseFloat(String.valueOf(new File(storageInfo.path).getFreeSpace()));
		    		
		    	ios = o + ios;
		    	iss = s + iss;

		    }
		    DecimalFormat twoDForm = new DecimalFormat("#.##");
		    
		    float p = ios - iss;
		    TextView07.setText(twoDForm.format(p/1073741824f) +" " + getResources().getString(R.string.GigaByte));
	    	TextView08.setText(twoDForm.format(iss/1073741824f) +" " + getResources().getString(R.string.GigaByte));
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
	            Intent it = new Intent(ActivityUsage.this, SettingsActivity.class);
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
	        
	        
}
