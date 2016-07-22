package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ActivityWifiInfo extends Activity implements View.OnClickListener {

	
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   WifiManager wifi;
	   
	   int menui= 0;
	   
	   TextView textView3, textView4, textView5;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	   
	   SharedPreferences mSettings;
	   
	   TextView textStatus, txtip, txtdns, txtmask, txtmarsh, textView1, textView2;
	   Button btn_back, foget, b1, b2, b3, Button01, Button02, Button03, Button04, b5, b6, b7;
	   String ip, dns, marsh, mask;
	   LinearLayout nopass;
	   
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
			
			 
			 txtip = (TextView) findViewById(R.id.TextViewIp);
			 txtmask = (TextView) findViewById(R.id.TextViewMask);
			 txtmarsh = (TextView) findViewById(R.id.TextViewMarsh);
			 txtdns = (TextView) findViewById(R.id.TextViewDns);
			 
			 textView1 = (TextView) findViewById(R.id.textView1);
			 textView2 = (TextView) findViewById(R.id.textView2);
			 textView3 = (TextView) findViewById(R.id.textView3);
			 textView4 = (TextView) findViewById(R.id.textView4);
			 textView5 = (TextView) findViewById(R.id.textView5);
			 textView5.setOnClickListener(new View.OnClickListener() {
				    @Override
				    public void onClick(View v) {
				    	
				    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.wifi_recomend_site)));
				    	startActivity(browserIntent);
				        
				    }
				});
			 
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
			 nopass = (LinearLayout) findViewById(R.id.Layout_no_pass);
			 
			 
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
				        
				        if (bundle.getInt("pass1") == 0){
				        	nopass.setVisibility(View.GONE);
			        		Log.e("PASS_REACTION", "GONE");
			        	}
			       
			        	  Log.e("PASS", String.valueOf(bundle.getInt("pass1")));
				        
				    }
				    
				   
				textStatus.setTypeface(typefaceBold);
    			btn_back.setTypeface(typefaceMedium);
    			foget.setTypeface(typefaceRoman);
    			textView1.setTypeface(typefaceRoman);
    			textView2.setTypeface(typefaceRoman);
    			textView3.setTypeface(typefaceBold);
    			textView4.setTypeface(typefaceRoman);
    			textView5.setTypeface(typefaceRoman);
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
					foget.setTextSize(13);
	    			textView1.setTextSize(11);
	    			textView2.setTextSize(11);
	    			Button01.setTextSize(13);
	    			Button02.setTextSize(13);
	    			Button03.setTextSize(13);
	    			Button04.setTextSize(13);
	    			txtip.setTextSize(13);
	    			txtmask.setTextSize(13);
	    			txtmarsh.setTextSize(13);
	    			txtdns.setTextSize(13);
				}
				if (size .contains( "Normal")){
					foget.setTextSize(15);
	    			textView1.setTextSize(13);
	    			textView2.setTextSize(13);
	    			Button01.setTextSize(15);
	    			Button02.setTextSize(15);
	    			Button03.setTextSize(15);
	    			Button04.setTextSize(15);
	    			txtip.setTextSize(15);
	    			txtmask.setTextSize(15);
	    			txtmarsh.setTextSize(15);
	    			txtdns.setTextSize(15);
				}
				if (size .contains( "Large")){
					foget.setTextSize(18);
	    			textView1.setTextSize(15);
	    			textView2.setTextSize(15);
	    			Button01.setTextSize(18);
	    			Button02.setTextSize(18);
	    			Button03.setTextSize(18);
	    			Button04.setTextSize(18);
	    			txtip.setTextSize(18);
	    			txtmask.setTextSize(18);
	    			txtmarsh.setTextSize(18);
	    			txtdns.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					foget.setTextSize(20);
	    			textView1.setTextSize(18);
	    			textView2.setTextSize(18);
	    			Button01.setTextSize(20);
	    			Button02.setTextSize(20);
	    			Button03.setTextSize(20);
	    			Button04.setTextSize(20);
	    			txtip.setTextSize(20);
	    			txtmask.setTextSize(20);
	    			txtmarsh.setTextSize(20);
	    			txtdns.setTextSize(20);
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
	    		
	    		forget_wifi();
	    		break;
	    	}
	    	
	    }
	    
	    public void forget_wifi (){
	    	final Dialog Activation = new Dialog(ActivityWifiInfo.this,android.R.style.Theme_Translucent);
	        Activation.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        Activation.setContentView(R.layout.dialog_2_button);
				
				// set the custom dialog components - text, image and button

				Button dialogButton = (Button) Activation.findViewById(R.id.dialogButtonOK);
				Button dialogButtonCancel = (Button) Activation.findViewById(R.id.dialogButtonCancel);
				TextView text = (TextView)Activation.findViewById(R.id.textView1);
				TextView textver = (TextView)Activation.findViewById(R.id.textView2);
				
				dialogButton.setTypeface(typefaceMedium);
				dialogButtonCancel.setTypeface(typefaceRoman);
				dialogButton.setText(R.string.forget_button);
				text.setTypeface(typefaceBold);
				text.setText(getString(R.string.forget_wifi_head) + "\n" + "\"" + textStatus.getText() + "\"");
				textver.setTypeface(typefaceRoman);
				textver.setText(R.string.forget_wifi_body);
				textver.setTextSize(15);
				text.setTextSize(15);
				
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						wifi.disconnect();
			    		 Intent intent18 = new Intent(ActivityWifiInfo.this, ActivityWifi.class);
			         	 startActivity(intent18);

			   		overridePendingTransition(center_to_right, center_to_right2);
			            
						Activation.dismiss();
					}
				});
				dialogButtonCancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Activation.dismiss();
					}
				});
				Activation.show();
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
