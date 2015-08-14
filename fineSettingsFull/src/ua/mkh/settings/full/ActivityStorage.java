package ua.mkh.settings.full;

import java.io.File;
import java.util.List;
import java.util.Locale;

import ua.mkh.settings.full.R.color;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;



public class ActivityStorage extends Activity implements SimpleGestureListener{
	
	TextView txt1, txt2, txt3, txt4, txt5, txt6;
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	   
	   private SimpleGestureFilter detector;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	
	 @Override
	  public void onCreate(Bundle b) {
	    super.onCreate(b);
	    setContentView(R.layout.activity_storage);
	    
	    String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String thin = "fonts/Thin.otf";
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);
		
		 Button btn_back = (Button) findViewById(R.id.buttonBack);
		TextView textStatus = (TextView)findViewById(R.id.textOk);
		 
		 textStatus.setTypeface(typefaceMedium);
		 textStatus.setText(R.string.memory);
	     btn_back.setTypeface(typefaceMedium);
	    
	List<StorageUtils.StorageInfo> storageList = StorageUtils.getStorageList();
    for (StorageUtils.StorageInfo storageInfo : storageList) {
    	LinearLayout LinearLayout1 = (LinearLayout)findViewById(R.id.linearLayout2);

    	
    	float scale = getResources().getDisplayMetrics().density;
    	int dpAsPixels1 = (int) (20*scale + 0.5f);
    	int dpAsPixels2 = (int) (5*scale + 0.5f);
    	
    	LinearLayout1.setPadding(dpAsPixels1,0,dpAsPixels1,0);
    	
        		txt1 = new TextView(ActivityStorage.this);
        		txt2 = new TextView(ActivityStorage.this);
        		txt3 = new TextView(ActivityStorage.this);
        		txt4 = new TextView(ActivityStorage.this);
        		txt5 = new TextView(ActivityStorage.this);
        		txt6 = new TextView(ActivityStorage.this);
        		LinearLayout LinearLayout2 = new LinearLayout(this);
        		txt1.setTypeface(typefaceRoman);
        		txt2.setTypeface(typefaceRoman);
        		txt3.setTypeface(typefaceRoman);
        		txt4.setTypeface(typefaceRoman);
        		txt5.setTypeface(typefaceRoman);
        		txt1.setTextColor(getResources().getColor(R.color.black));
        		txt2.setTextColor(getResources().getColor(R.color.black));
        		txt3.setTextColor(getResources().getColor(R.color.black));
        		txt4.setTextColor(getResources().getColor(R.color.black));
        		txt5.setTextColor(getResources().getColor(R.color.black));
        		txt1.setTextSize(16);
        		txt2.setTextSize(16);
        		txt3.setTextSize(16);
        		txt4.setTextSize(16);
        		txt5.setTextSize(16);
        		LinearLayout1.setBackgroundColor(Color.parseColor("#ffffff"));
        		
        		txt5. setGravity(Gravity.CENTER);
        		ProgressBar pg = new ProgressBar(ActivityStorage.this, null, android.R.attr.progressBarStyleHorizontal);
        		pg.setProgressDrawable(getResources().getDrawable(R.drawable.customprogressbar));
        		
        		LinearLayout2 .setGravity(Gravity.LEFT);
        		txt1.setPadding(0,dpAsPixels1,0,0);
        		txt5.setPadding(0,dpAsPixels1,0,0);
        		txt6.setPadding(0,0,0,dpAsPixels2);
        		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,dpAsPixels2);
        		pg.setLayoutParams(lp);
        		
        		
        		LinearLayout1.addView(txt1);
        		LinearLayout1.addView(txt2);
        		LinearLayout1.addView(txt3);
        		LinearLayout1.addView(txt4);
        		LinearLayout1.addView(txt5);
        		LinearLayout1.addView(LinearLayout2);
        		LinearLayout2.addView(pg);
        		LinearLayout1.addView(txt6);

        		
        		
        txt1.setText(storageInfo.getDisplayName());
        boolean isLangRU = Locale.getDefault().getLanguage().equals("ru");
        boolean isLangUK = Locale.getDefault().getLanguage().equals("uk");
        
        
       
        txt3.setText("Total space : "+String.valueOf(new File(storageInfo.path).getTotalSpace()/(1048576L))+" MB");
        txt4.setText("Aviable : "+String.valueOf(new File(storageInfo.path).getFreeSpace()/(1048576L))+" MB");

        if (isLangRU == true) { 
        	txt3.setText("Всего : "+String.valueOf(new File(storageInfo.path).getTotalSpace()/(1048576L))+" MB");
        	txt4.setText("Доступно : "+String.valueOf(new File(storageInfo.path).getFreeSpace()/(1048576L))+" MB");
        } 
        if (isLangUK == true) { 
        	txt3.setText("Всього : "+String.valueOf(new File(storageInfo.path).getTotalSpace()/(1048576L))+" MB");
        	txt4.setText("Доступна пам'ять : "+String.valueOf(new File(storageInfo.path).getFreeSpace()/(1048576L))+" MB");
        } 
        
        long ul = 100-((new File(storageInfo.path).getFreeSpace()*100L)/new File(storageInfo.path).getTotalSpace());
        int ui = (int)ul;
        int myProgress = ui ;
        pg.setProgress(myProgress);

        txt5.setText(String.valueOf(myProgress)+"%");
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
					txt1.setTypeface(typefaceBold);
	        		txt2.setTypeface(typefaceBold);
	        		txt3.setTypeface(typefaceBold);
	        		txt4.setTypeface(typefaceBold);
	        		txt5.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					txt1.setTextSize(14);
	        		txt2.setTextSize(14);
	        		txt3.setTextSize(14);
	        		txt4.setTextSize(14);
	        		txt5.setTextSize(14);
				}
				if (size .contains( "Normal")){
					txt1.setTextSize(16);
	        		txt2.setTextSize(16);
	        		txt3.setTextSize(16);
	        		txt4.setTextSize(16);
	        		txt5.setTextSize(16);
				}
				if (size .contains( "Large")){
					txt1.setTextSize(19);
	        		txt2.setTextSize(19);
	        		txt3.setTextSize(19);
	        		txt4.setTextSize(19);
	        		txt5.setTextSize(19);
				}
				if (size .contains( "xLarge")){
					txt1.setTextSize(21);
	        		txt2.setTextSize(21);
	        		txt3.setTextSize(21);
	        		txt4.setTextSize(21);
	        		txt5.setTextSize(21);
				}
	       }
	 }
		
		
	 public void BackClick(View v)  
	    {  
		 Intent intent18 = new Intent(this, ActivityOsnova.class);
      	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
	   	 }
}

