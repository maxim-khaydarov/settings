package ua.mkh.settings.full;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

public class ActivityUsageInfo extends Activity implements  SimpleGestureListener{

	TextView textStatus;
	Button btn_back;
	
	SharedPreferences mSettings;
	 
	 private SimpleGestureFilter detector;

	 public static final String APP_PREFERENCES = "mysettings";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		
	   
	   public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	  

		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	 
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_osnova);
        
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		Typeface typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		Typeface typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		Typeface typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		

		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);

		textStatus = (TextView)findViewById(R.id.textOk);
		 
        
        btn_back = (Button) findViewById(R.id.buttonBack);
        textStatus.setText(R.string.app_name);
        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);	
	}
	
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
	    	  Intent intent18 = new Intent(this, ActivityUsage.class);
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
	     
	     
	     
	     public void onClick(View v) {
	    	 switch(v.getId()){
	    	 
	    	 default:
	    	 break;
	    	 }
	    	 
	    	 
	    	 
	    	 
	     }
	     public void BackClick(View v)  
	     {  
	     	Intent intent18 = new Intent(this, ActivityUsage.class);
	      	 startActivity(intent18);

	 		overridePendingTransition(center_to_right, center_to_right2);
	    	 }
	     
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  
	    
	      if (resultCode == Activity.RESULT_OK) {
	        // TODO Extract the data returned from the child Activity.
	    	  String datas = data.getStringExtra("key title");
	      }
	      
	    } 
	  }
	
	

