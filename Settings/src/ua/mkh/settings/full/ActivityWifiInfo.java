package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

public class ActivityWifiInfo extends Activity implements OnClickListener, SimpleGestureListener {

	
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   WifiManager wifi;
	   
	   int menui= 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	   
	   SharedPreferences mSettings;
	   private SimpleGestureFilter detector;
	   
	   TextView textStatus, txtip, txtdns, txtmask, txtmarsh, textView1, textView2;
	   Button btn_back, foget, b1, b2, b3, Button01, Button02, Button03, Button04, b5, b6, b7;
	   String ip, dns, marsh, mask;
	   
	   @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_wifi_info);
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
			 
			 txtip = (TextView) findViewById(R.id.TextViewIp);
			 txtmask = (TextView) findViewById(R.id.TextViewMask);
			 txtmarsh = (TextView) findViewById(R.id.TextViewMarsh);
			 txtdns = (TextView) findViewById(R.id.TextViewDns);
			 
			 textView1 = (TextView) findViewById(R.id.textView1);
			 textView2 = (TextView) findViewById(R.id.textView2);
			 b1 = (Button) findViewById(R.id.button1);
			 b2 = (Button) findViewById(R.id.button2);
			 b3 = (Button) findViewById(R.id.button3);
			 b5 = (Button) findViewById(R.id.Button05);
			 b6 = (Button) findViewById(R.id.Button06);
			 b7 = (Button) findViewById(R.id.Button07);
			 Button01 = (Button) findViewById(R.id.Button01);
			 Button02 = (Button) findViewById(R.id.Button02);
			 Button03 = (Button) findViewById(R.id.Button03);
			 Button04 = (Button) findViewById(R.id.Button04);
			 
			 
			 foget = (Button) findViewById(R.id.ButtonWifi);
			 foget.setOnClickListener(this);
			 btn_back = (Button) findViewById(R.id.buttonBack);
				btn_back.setText(R.string.button_wifi);
				textStatus = (TextView)findViewById(R.id.textOk);
				
				 Intent intent = getIntent();
				    Bundle bundle = intent.getExtras();
				    if(bundle!=null){
				        
				        txtip.setText(bundle.getString("ip"));
				        txtmask.setText(bundle.getString("mask"));
				        txtmarsh.setText(bundle.getString("marsh"));
				        txtdns.setText(bundle.getString("dns1") + ", " + bundle.getString("dns2"));
				        
				        textStatus.setText(bundle.getString("name"));
				    }
				    
				    
				
				textStatus.setTypeface(typefaceMedium);
    			btn_back.setTypeface(typefaceMedium);
    			foget.setTypeface(typefaceRoman);
    			textView1.setTypeface(typefaceRoman);
    			textView2.setTypeface(typefaceRoman);
    			b1.setTypeface(typefaceRoman);
    			b2.setTypeface(typefaceRoman);
    			b3.setTypeface(typefaceRoman);
    			b5.setTypeface(typefaceRoman);
    			b6.setTypeface(typefaceRoman);
    			b7.setTypeface(typefaceRoman);
    			Button01.setTypeface(typefaceRoman);
    			Button02.setTypeface(typefaceRoman);
    			Button03.setTypeface(typefaceRoman);
    			Button04.setTypeface(typefaceRoman);
    			txtip.setTypeface(typefaceRoman);
    			txtmask.setTypeface(typefaceRoman);
    			txtmarsh.setTypeface(typefaceRoman);
    			txtdns.setTypeface(typefaceRoman);
    			
			 
    			 wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE); 
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
	    	  Intent intent18 = new Intent(this, ActivityWifi.class);
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
					textStatus.setTypeface(typefaceBold);
	    			btn_back.setTypeface(typefaceBold);
					foget.setTypeface(typefaceBold);
	    			textView1.setTypeface(typefaceBold);
	    			textView2.setTypeface(typefaceBold);
	    			b1.setTypeface(typefaceBold);
	    			b2.setTypeface(typefaceBold);
	    			b3.setTypeface(typefaceBold);
	    			b5.setTypeface(typefaceBold);
	    			b6.setTypeface(typefaceBold);
	    			b7.setTypeface(typefaceBold);
	    			Button01.setTypeface(typefaceBold);
	    			Button02.setTypeface(typefaceBold);
	    			Button03.setTypeface(typefaceBold);
	    			Button04.setTypeface(typefaceBold);
	    			txtip.setTypeface(typefaceBold);
	    			txtmask.setTypeface(typefaceBold);
	    			txtmarsh.setTypeface(typefaceBold);
	    			txtdns.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					foget.setTextSize(14);
	    			textView1.setTextSize(11);
	    			textView2.setTextSize(11);
	    			Button01.setTextSize(14);
	    			Button02.setTextSize(14);
	    			Button03.setTextSize(14);
	    			Button04.setTextSize(14);
	    			txtip.setTextSize(14);
	    			txtmask.setTextSize(14);
	    			txtmarsh.setTextSize(14);
	    			txtdns.setTextSize(14);
				}
				if (size .contains( "Normal")){
					foget.setTextSize(16);
	    			textView1.setTextSize(13);
	    			textView2.setTextSize(13);
	    			Button01.setTextSize(16);
	    			Button02.setTextSize(16);
	    			Button03.setTextSize(16);
	    			Button04.setTextSize(16);
	    			txtip.setTextSize(16);
	    			txtmask.setTextSize(16);
	    			txtmarsh.setTextSize(16);
	    			txtdns.setTextSize(16);
				}
				if (size .contains( "Large")){
					foget.setTextSize(19);
	    			textView1.setTextSize(16);
	    			textView2.setTextSize(16);
	    			Button01.setTextSize(19);
	    			Button02.setTextSize(19);
	    			Button03.setTextSize(19);
	    			Button04.setTextSize(19);
	    			txtip.setTextSize(19);
	    			txtmask.setTextSize(19);
	    			txtmarsh.setTextSize(19);
	    			txtdns.setTextSize(19);
				}
				if (size .contains( "xLarge")){
					foget.setTextSize(21);
	    			textView1.setTextSize(18);
	    			textView2.setTextSize(18);
	    			Button01.setTextSize(21);
	    			Button02.setTextSize(21);
	    			Button03.setTextSize(21);
	    			Button04.setTextSize(21);
	    			txtip.setTextSize(21);
	    			txtmask.setTextSize(21);
	    			txtmarsh.setTextSize(21);
	    			txtdns.setTextSize(21);
				}
	       }

	    }
	    
	    public void BackClick(View v)  
	    {  
	   	 Intent intent18 = new Intent(this, ActivityWifi.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	   	 }

	    
	    public void onClick(View v) {
	    	switch (v.getId()){
	    	
	    	case R.id.ButtonWifi:
	    		wifi.disconnect();
	    		 Intent intent18 = new Intent(this, ActivityWifi.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
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
	            	Intent intent18 = new Intent(this, ActivityWifi.class);
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
	            Intent it = new Intent(ActivityWifiInfo.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
}
