package ua.mkh.settings.full;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingsActivity extends Activity implements View.OnClickListener{

	
	 TextView textStatus, TextView01;
	 Button btn_back, Button01, Button12, Button13, Button14, Button15, Button16, Button17, 
	 Button19, Button20, Button23;
	 int app = 0;
	 ImageView Up, Down;
	 LinearLayout LinearLayoutApp;
	 Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	 ToggleButton tgb_2, tgb_3, tgb_4;
	 Boolean tgb1_s, tgb2_s, tgb3_s, tgb4_s; 
	 TextView txt1, txt2;

	 View toggleCircle, background_oval_off, background_oval_on;
	 int dimen;
	 
	 SharedPreferences mSettings;
	 
	 

	 String apn1;
	 
	 public static final String APP_PREFERENCES = "mysettings";
	 public static final String APP_PREFERENCES_tgb_passcode = "tgb_passcode";
	 public static final String APP_PREFERENCES_tgb_privacy = "tgb_privacy";

	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	
	   
	   public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	  
  
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	 
	 Animation animationRotateDown, animationRotateUp;
	   
	@Override
    public void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_settings);
        
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String thin = "fonts/Thin.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		

		
		
		tgb_2 = (ToggleButton)findViewById(R.id.ToggleButton02);
		tgb_3 = (ToggleButton)findViewById(R.id.ToggleButton03);
		tgb_4 = (ToggleButton)findViewById(R.id.ToggleButton04);
		
		tgb_2.setOnClickListener(this);
		tgb_3.setOnClickListener(this);
		tgb_4.setOnClickListener(this);
		
		txt1 = (TextView) findViewById(R.id.textView1);
		TextView01 = (TextView) findViewById(R.id.TextView01);
		
		LinearLayoutApp = (LinearLayout)findViewById(R.id.LinearLayoutApp);
		LinearLayoutApp.setVisibility(View.GONE);
		Down = (ImageView)findViewById(R.id.down);
		 Up = (ImageView)findViewById(R.id.up);
		 Up.setVisibility(View.GONE);
		 
		 animationRotateDown = AnimationUtils.loadAnimation(
					this, R.anim.rotate_down);
		    animationRotateUp = AnimationUtils.loadAnimation(
					this, R.anim.rotate_up);
		
		Button01 = (Button)findViewById(R.id.Button01);
		Button01.setOnClickListener(this); 
		Button12 = (Button)findViewById(R.id.Button12);
		Button12.setOnClickListener(this);
		Button13 = (Button)findViewById(R.id.Button13);
		Button13.setOnClickListener(this);
		Button14 = (Button)findViewById(R.id.Button14);
		Button14.setOnClickListener(this);
		Button15 = (Button)findViewById(R.id.Button15);
		Button15.setOnClickListener(this);
		Button16 = (Button)findViewById(R.id.Button16);
		Button16.setOnClickListener(this);
		Button17 = (Button)findViewById(R.id.Button17);
		Button17.setOnClickListener(this);
		
		Button19 = (Button)findViewById(R.id.Button19);
		Button20 = (Button)findViewById(R.id.Button20);
		Button23 = (Button)findViewById(R.id.Button23);
		Button23.setOnClickListener(this);
		
		 textStatus = (TextView)findViewById(R.id.textOk);
		 
	        
	        btn_back = (Button) findViewById(R.id.buttonBack);
	        textStatus.setText(R.string.app_name);
	        textStatus.setTypeface(typefaceBold);
	        btn_back.setTypeface(typefaceMedium);
            Button01.setTypeface(typefaceRoman);
            Button12.setTypeface(typefaceRoman);
            Button13.setTypeface(typefaceRoman);
            Button14.setTypeface(typefaceRoman);
            Button15.setTypeface(typefaceRoman);
            Button16.setTypeface(typefaceRoman);
            Button17.setTypeface(typefaceRoman);
            txt1.setTypeface(typefaceRoman);
            TextView01.setTypeface(typefaceRoman);
            
            Button19.setTypeface(typefaceRoman);
            Button20.setTypeface(typefaceRoman);
            Button23.setTypeface(typefaceRoman);
            
	}
    
	private void HideLayoutDown() {

	     final View view=Down;
	     view.postDelayed(new Runnable() {

	        public void run() {
	            if(!Down.isPressed())
	            {
	            view.setVisibility(View.GONE);
	            Up.setVisibility(View.VISIBLE);
	            
	            }
	            else
	            {
	                HideLayoutDown();
	            }
	        }
	    }, 200); // (3000 == 3secs)

	    }
  private void HideLayoutUp() {

	     final View view=Up;
	     view.postDelayed(new Runnable() {

	        public void run() {
	            if(!Up.isPressed())
	            {
	            view.setVisibility(View.GONE);
	            Down.setVisibility(View.VISIBLE);
	            }
	            else
	            {
	                HideLayoutUp();
	            }
	        }
	    }, 200); // (3000 == 3secs)

	    }
	
	
	 public void onClick(View v) {
		 int id = v.getId();
		 
		 if (id == R.id.Button01){
			if (app == 0){
				 Down.startAnimation(animationRotateUp);
        		 HideLayoutDown();
	        	LinearLayoutApp.setVisibility(View.VISIBLE);
	        	app = 1;
	        	}
	        	else {
	        		Up.startAnimation(animationRotateDown);
	        		HideLayoutUp();
		        	LinearLayoutApp.setVisibility(View.GONE);
		        	app = 0;
	        	}
		}
		 
		 
		if (id == R.id.Button21){
			Intent intent = new Intent(this, ActivityAppsiTunes.class);
	       	 startActivity(intent);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
		
		if (id == R.id.Button14){
			Intent intent = new Intent(this, ActivityAppsNew1.class);
	       	 startActivity(intent);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
		
		if (id == R.id.Button15){
			Intent intent = new Intent(this, ActivityAppsNew2.class);
	       	 startActivity(intent);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
		
		if (id == R.id.Button16){
			Intent intent = new Intent(this, ActivityAppsNew3.class);
	       	 startActivity(intent);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
		
		if (id == R.id.Button17){
			Intent intent = new Intent(this, ActivityAppsNew4.class);
	       	 startActivity(intent);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
		
		if (id == R.id.Button12){
			Intent intent = new Intent(this, ActivityAppsNotifications.class);
	       	 startActivity(intent);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
		
		if (id == R.id.Button13){
			Intent intent = new Intent(this, ActivityAppsControl.class);
	       	 startActivity(intent);
 	        	overridePendingTransition(center_to_left, center_to_left2);
 	        	 }
		
		
		
		
		if (tgb_2.isChecked()) 
	    {
			Editor editor = mSettings.edit();
		   	editor.putBoolean(APP_PREFERENCES_tgb_passcode, true);
		   	editor.apply();
	    }
	    else
	    {
	    	Editor editor = mSettings.edit();
		   	editor.putBoolean(APP_PREFERENCES_tgb_passcode, false);
		   	editor.apply();
	    }
		
		if (tgb_3.isChecked()) 
	    {
			Editor editor = mSettings.edit();
		   	editor.putBoolean(APP_PREFERENCES_tgb_privacy, true);
		   	editor.apply();
	    }
	    else
	    {
	    	Editor editor = mSettings.edit();
		   	editor.putBoolean(APP_PREFERENCES_tgb_privacy, false);
		   	editor.apply();
	    }
		if (tgb_4.isChecked()) 
	    {
			Editor editor = mSettings.edit();
		   	editor.putBoolean(APP_PREFERENCES_tgb_menu, true);
		   	editor.apply();
	    }
	    else
	    {
	    	Editor editor = mSettings.edit();
		   	editor.putBoolean(APP_PREFERENCES_tgb_menu, false);
		   	editor.apply();
	    }
		
	 }
	 
	 
	 
	 
		
	 @Override
	    public boolean onKeyDown(int keycode, KeyEvent e) {
	        switch(keycode) {
	           
	            case KeyEvent.KEYCODE_BACK:
	            	Intent intent18 = new Intent(this, MainActivity.class);
	             	 startActivity(intent18);

	       		overridePendingTransition(center_to_right, center_to_right2);
	                return true;
	            
	        }
	        return super.onKeyDown(keycode, e);
	   }
	 
	 
	        
	 protected void onResume() {
	        super.onResume();
	        
	        
	        
	        Log.d("Resume", "Start");
	        
	       
	        if (mSettings.contains(APP_PREFERENCES_tgb_menu)) {
				// �������� ����� �� ��������
	        	 Boolean menu = mSettings.getBoolean(APP_PREFERENCES_tgb_menu, true);
				if (menu == true){
				tgb_4.setChecked(true);}
				else
					tgb_4.setChecked(false);
				}
	        
	        
	        
	        if (mSettings.contains(APP_PREFERENCES_tgb_passcode)) {
				// �������� ����� �� ��������
	        	Boolean passcode;
				passcode = mSettings.getBoolean(APP_PREFERENCES_tgb_passcode, true);
				if (passcode == true){
				tgb_2.setChecked(true); }
				else
					tgb_2.setChecked(false);
	        }
	        
	        if (mSettings.contains(APP_PREFERENCES_tgb_privacy)) {
				// �������� ����� �� ��������
	        	Boolean privacy;
				privacy = mSettings.getBoolean(APP_PREFERENCES_tgb_privacy, true);
				if (privacy == true){
					tgb_3.setChecked(true);}
				else
					tgb_3.setChecked(false);
				}
	        
	        if (mSettings.contains(APP_PREFERENCES_bold_text)) {
				// �������� ����� �� ��������
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					Button01.setTypeface(typefaceBold);
		            Button12.setTypeface(typefaceBold);
		            Button13.setTypeface(typefaceBold);
		            Button14.setTypeface(typefaceBold);
		            Button15.setTypeface(typefaceBold);
		            Button16.setTypeface(typefaceBold);
		            Button17.setTypeface(typefaceBold);
		            txt1.setTypeface(typefaceBold);
		            TextView01.setTypeface(typefaceBold);
		            Button19.setTypeface(typefaceBold);
		            Button20.setTypeface(typefaceBold);
		            Button23.setTypeface(typefaceBold);
		            
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// �������� ����� �� ��������
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					Button01.setTextSize(13);
		            Button12.setTextSize(13);
		            Button13.setTextSize(13);
		            Button14.setTextSize(13);
		            Button15.setTextSize(13);
		            Button16.setTextSize(13);
		            Button17.setTextSize(13);
		            txt1.setTextSize(11);
		            TextView01.setTextSize(11);
		            Button19.setTextSize(13);
		            Button20.setTextSize(13);
		            Button23.setTextSize(13);
				}
				if (size .contains( "Normal")){
					Button01.setTextSize(15);
		            Button12.setTextSize(15);
		            Button13.setTextSize(15);
		            Button14.setTextSize(15);
		            Button15.setTextSize(15);
		            Button16.setTextSize(15);
		            Button17.setTextSize(15);
		            txt1.setTextSize(13);
		            TextView01.setTextSize(13);
		            Button19.setTextSize(15);
		            Button20.setTextSize(15);
		            Button23.setTextSize(15);
				}
				if (size .contains( "Large")){
					Button01.setTextSize(18);
		            Button12.setTextSize(18);
		            Button13.setTextSize(18);
		            Button14.setTextSize(18);
		            Button15.setTextSize(18);
		            Button16.setTextSize(18);
		            Button17.setTextSize(18);
		            txt1.setTextSize(15);
		            TextView01.setTextSize(15);
		            Button19.setTextSize(18);
		            Button20.setTextSize(18);
		            Button23.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					Button01.setTextSize(20);
		            Button12.setTextSize(20);
		            Button13.setTextSize(20);
		            Button14.setTextSize(20);
		            Button15.setTextSize(20);
		            Button16.setTextSize(20);
		            Button17.setTextSize(20);
		            txt1.setTextSize(18);
		            TextView01.setTextSize(18);
		            Button19.setTextSize(20);
		            Button20.setTextSize(20);
		            Button23.setTextSize(20);
				}
	       }
	       
	      
				// �������� ����� �� ��������
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
		
	   	
	 
	 public void BackClick(View v)  
	    {  
		 Intent intent18 = new Intent(this, MainActivity.class);
      	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
	   	 }
	 
	 
	 
	 
	 
	 
	 
	 
}
    
   


