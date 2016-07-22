package ua.mkh.settings.full;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
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

public class ActivityUsage extends Activity implements View.OnClickListener{
	
	
	
	
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
	   
	   
	   TextView textStatus, TextView01, TextView07, TextView08;
	   Button btn_back, Button02, Button08, Button09;
	   
	
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
			
			
			//textView1 = (TextView) findViewById(R.id.textView1);
			TextView01 = (TextView) findViewById(R.id.TextView01);
			TextView07 = (TextView) findViewById(R.id.TextView07);
			TextView08 = (TextView) findViewById(R.id.TextView08);
			
			//Button01 = (Button)findViewById(R.id.Button01);
			Button02 = (Button)findViewById(R.id.Button02);
			Button02.setOnClickListener(this);
			//Button06 = (Button)findViewById(R.id.Button06);
			//Button06.setOnClickListener(this);
			Button08 = (Button)findViewById(R.id.Button08);
			Button09 = (Button)findViewById(R.id.Button09);
			//tgb1 = (ToggleButton) findViewById(R.id.ToggleButton01);
			//tgb1.setChecked(true);
			
			textStatus = (TextView)findViewById(R.id.textOk);
			
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
		        btn_back.setText(R.string.button_general);
		        textStatus.setText(R.string.button_usage);
		        textStatus.setTypeface(typefaceBold);
		        btn_back.setTypeface(typefaceMedium);
		        
		     //   Button01.setTypeface(typefaceRoman);
		        Button02.setTypeface(typefaceRoman);
		       // Button06.setTypeface(typefaceRoman);
		        Button08.setTypeface(typefaceRoman);
		        Button09.setTypeface(typefaceRoman);
		       // textView1.setTypeface(typefaceRoman);
		        TextView01.setTypeface(typefaceRoman);
		        TextView07.setTypeface(typefaceRoman);
		        TextView08.setTypeface(typefaceRoman);
	
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
				//	Button01.setTypeface(typefaceBold);
			        Button02.setTypeface(typefaceBold);
			      //  Button06.setTypeface(typefaceBold);
			        Button08.setTypeface(typefaceBold);
			        Button09.setTypeface(typefaceBold);
			      //  textView1.setTypeface(typefaceBold);
			        TextView01.setTypeface(typefaceBold);
			        TextView07.setTypeface(typefaceBold);
			        TextView08.setTypeface(typefaceBold);
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
				//	Button01.setTextSize(13);
			        Button02.setTextSize(13);
			     //   Button06.setTextSize(13);
			        Button08.setTextSize(13);
			        Button09.setTextSize(13);
			      //  textView1.setTextSize(13);
					TextView01.setTextSize(13);
					TextView07.setTextSize(13);
					TextView08.setTextSize(13);
			        
				}
				if (size .contains( "Normal")){
				//	Button01.setTextSize(15);
			        Button02.setTextSize(15);
			      //  Button06.setTextSize(15);
			        Button08.setTextSize(15);
			        Button09.setTextSize(15);
			      //  textView1.setTextSize(15);
					TextView01.setTextSize(15);
					TextView07.setTextSize(15);
					TextView08.setTextSize(15);
				}
				if (size .contains( "Large")){
				//	Button01.setTextSize(18);
			        Button02.setTextSize(18);
			      //  Button06.setTextSize(18);
			        Button08.setTextSize(18);
			        Button09.setTextSize(18);
			     //   textView1.setTextSize(18);
					TextView01.setTextSize(18);
					TextView07.setTextSize(18);
					TextView08.setTextSize(18);
				}
				if (size .contains( "xLarge")){
				//	Button01.setTextSize(20);
			        Button02.setTextSize(20);
			      //  Button06.setTextSize(20);
			        Button08.setTextSize(20);
			        Button09.setTextSize(20);
			      //  textView1.setTextSize(20);
					TextView01.setTextSize(20);
					TextView07.setTextSize(20);
					TextView08.setTextSize(20);
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
		  
		    TextView07.setText(getTotalInternalMemorySize());
		    TextView08.setText(getAvailableInternalMemorySize());
		    
	  }
	  
	  public  boolean externalMemoryAvailable() {
	        return //android.os.Environment.getExternalStorageState().equals(
	               // android.os.Environment.MEDIA_MOUNTED);
	        android.os.Environment.getRootDirectory().equals(android.os.Environment.MEDIA_MOUNTED);
	    }

	    public  String getAvailableInternalMemorySize() {
	        File path = Environment.getDataDirectory();
	        StatFs stat = new StatFs(path.getPath());
	        long blockSize = stat.getBlockSize();
	        long availableBlocks = stat.getAvailableBlocks();
	        return getFileSize(availableBlocks * blockSize);
	    }

	    public  String getTotalInternalMemorySize() {
	        File path = Environment.getDataDirectory();
	        StatFs stat = new StatFs(path.getPath());
	        long blockSize = stat.getBlockSize();
	        long totalBlocks = stat.getBlockCount();
	        return getFileSize(totalBlocks * blockSize);
	    }

	    static long ERROR = 0;
	    
	    public  String getAvailableExternalMemorySize() {
	        if (externalMemoryAvailable()) {
	            File path = Environment.getExternalStorageDirectory();
	            StatFs stat = new StatFs(path.getPath());
	            long blockSize = stat.getBlockSize();
	            long availableBlocks = stat.getAvailableBlocks();
	            return getFileSize(availableBlocks * blockSize);
	        } else {
	            return  getFileSize(ERROR);
	        }
	    }

	    public  String getTotalExternalMemorySize() {
	        if (externalMemoryAvailable()) {
	            File path = Environment.getExternalStorageDirectory();
	            StatFs stat = new StatFs(path.getPath());
	            long blockSize = stat.getBlockSize();
	            long totalBlocks = stat.getBlockCount();
	            return getFileSize(totalBlocks * blockSize);
	        } else {
	            return  getFileSize(ERROR);
	        }
	    }

	    
	  
	  public  String getFileSize(long size) {
	        if (size <= 0)
	            return "0";
	        final String[] unit = getResources().getStringArray(R.array.units);
	        
	        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
	        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + unit[digitGroups];
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
