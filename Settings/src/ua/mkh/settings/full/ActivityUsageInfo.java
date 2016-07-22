package ua.mkh.settings.full;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.os.StatFs;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivityUsageInfo extends Activity implements View.OnClickListener{

	TextView textStatus, textView02, textView1, textView2, textView3, textView4;
	Button btn_back, Button01, Button03;
	
	SharedPreferences mSettings;
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	

	 public static final String APP_PREFERENCES = "mysettings";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		
	   
	   public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	  
	   int UNINSTALL_REQUEST_CODE = 1;
		
	   int menui = 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	 
	   long apk_size;
	   String title, code;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_info);
        
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		

		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		

		textStatus = (TextView)findViewById(R.id.textOk);
		textView02 = (TextView) findViewById(R.id.textView02);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		
		Button01 = (Button) findViewById(R.id.Button01);
		Button01.setOnClickListener(this);
		Button03 = (Button) findViewById(R.id.Button03);
		 
		try {
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
  	  String version = intent.getStringExtra("version");
  	  apk_size = intent.getLongExtra("apk_size", 0);
  	  code = intent.getStringExtra("code");
  	  
  	  getApkSize(code);
  	  
  	
  	String strMeatFormat = getResources().getString(R.string.usage_info);
  	String strMeatMsg = String.format(strMeatFormat, getLocalBluetoothName(), getFileSize(getAvailableInternalMemorySize()));
  	  textView4.setText(strMeatMsg);
  	  
  	 textView02.setText( title );
 	  textView2.setText(getString(R.string.version) + " " + version);
 	  textView3.setText(getString(R.string.size) + ": " + getFileSize(apk_size));
  	
		Drawable icon = getPackageManager().getApplicationIcon(code);
		ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
	  	imageView1.setImageDrawable(icon);
	} catch (NameNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	
  	
  	 
		
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_back.setText(R.string.storage_usage2);
        textStatus.setText(title);
        textStatus.setTypeface(typefaceBold);
        btn_back.setTypeface(typefaceMedium);
        textView02.setTypeface(typefaceRoman);
        textView1.setTypeface(typefaceRoman);
        textView2.setTypeface(typefaceRoman);
        textView3.setTypeface(typefaceRoman);
        textView4.setTypeface(typefaceRoman);
        Button01.setTypeface(typefaceRoman);
        Button03.setTypeface(typefaceRoman);
	}
	
	     
	     public void getApkSize(String packageName3){
	     PackageManager pm = getPackageManager();
	     
	     

         Method getPackageSizeInfo;
			try {
				getPackageSizeInfo = pm.getClass().getMethod(
				    "getPackageSizeInfo", String.class, IPackageStatsObserver.class);
			

         getPackageSizeInfo.invoke(pm, packageName3,
             new IPackageStatsObserver.Stub() {

                 @Override
                 public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
                     throws RemoteException {

                     Log.i("TAG", "codeSize: " + pStats.codeSize);
                     textView1.setText(getFileSize(pStats.codeSize - apk_size));
                 }
             });

			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
     }
	 	
	     public static long getAvailableInternalMemorySize() {
		        File path = Environment.getDataDirectory();
		        StatFs stat = new StatFs(path.getPath());
		        long blockSize = stat.getBlockSize();
		        long availableBlocks = stat.getAvailableBlocks();
		        return availableBlocks * blockSize;
		    }
	     
	     public String getLocalBluetoothName(){
	    	 BluetoothAdapter bluetoothAdapter = null;
			    if(bluetoothAdapter == null){
			    	bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			    }
			    String name = bluetoothAdapter.getName();
			    if(name == null){
			        System.out.println("Name is null!");
			        name = bluetoothAdapter.getAddress();
			    }
			    return name;
			}
	     
	     public String getFileSize(long size) {
	         if (size <= 0)
	             return "0";
	         //final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
	         final String[] unit = getResources().getStringArray(R.array.units);
	         int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
	         return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + unit[digitGroups];
	     }
	     
	     
	     
	     public void onClick(View v) {
	    	 switch(v.getId()){
	    	 
	    	 case R.id.Button01:
	    		 
	    		 openDialog();
	    		 break;
	    	 default:
	    	 break;
	    	 }
	    	 
	     }
	     public void BackClick(View v)  
	     {  
	     	Intent intent18 = new Intent(this, ActivityApps.class);
	      	 startActivity(intent18);

	 		overridePendingTransition(center_to_right, center_to_right2);
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
		     ButtonMenuSettings.setText(R.string.delete_app);
		     
		     String strMeatFormat1 = getResources().getString(R.string.delete_app_info);
		   	String strMeatMsg2 = String.format(strMeatFormat1, title, getFileSize(getAvailableInternalMemorySize()));
		     
		   	ButtonInfo.setText(strMeatMsg2);
		   	
		     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View v) {
		    dialog.dismiss();
		   }});
		     
		     ButtonMenuSettings.setOnClickListener(new OnClickListener(){

		  	   @Override
		  	   public void onClick(View v) {
		  		   
		  		 Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);  
		  		intent.setData(Uri.parse("package:" + code));  
		  		intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
		  		startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
		  		
		  		 
		  	   }});
		     
		     dialog.show();
		    }
	   	
	     @Override
	     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	         super.onActivityResult(requestCode, resultCode, data);
	         if (requestCode == UNINSTALL_REQUEST_CODE) {
	             if (resultCode == RESULT_OK) {
	                 Log.d("TAG", "onActivityResult: user accepted the (un)install");
	                 Intent intent18 = new Intent(this, ActivityApps.class);
	             	 startActivity(intent18);
	       		overridePendingTransition(center_to_right, center_to_right2);
	       		
	             } else if (resultCode == RESULT_CANCELED) {
	            	 Log.d("TAG", "onActivityResult: user canceled the (un)install");
	                 
	             } else if (resultCode == RESULT_FIRST_USER) {
	                 Log.d("TAG", "onActivityResult: failed to (un)install");
	             }
	         }
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
						Button01.setTypeface(typefaceBold);
				        Button03.setTypeface(typefaceBold);
				      
				        textView1.setTypeface(typefaceBold);
				        textView02.setTypeface(typefaceBold);
				        textView2.setTypeface(typefaceBold);
				        textView3.setTypeface(typefaceBold);
					}
		        }
					
		       if (mSettings.contains(APP_PREFERENCES_text_size)) {
					// Получаем число из настроек
		        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
					if (size .contains( "Small")){
						Button01.setTextSize(13);
				        Button03.setTextSize(13);
				     
				        textView1.setTextSize(13);
						textView02.setTextSize(11);
						textView2.setTextSize(11);
						textView3.setTextSize(11);
				        
					}
					if (size .contains( "Normal")){
						Button01.setTextSize(15);
				        Button03.setTextSize(15);
				     
				        textView1.setTextSize(15);
						textView02.setTextSize(13);
						textView2.setTextSize(13);
						textView3.setTextSize(13);
					}
					if (size .contains( "Large")){
						Button01.setTextSize(18);
				        Button03.setTextSize(18);
				     
				        textView1.setTextSize(18);
						textView02.setTextSize(16);
						textView2.setTextSize(16);
						textView3.setTextSize(16);
					}
					if (size .contains( "xLarge")){
						Button01.setTextSize(20);
				        Button03.setTextSize(20);
				     
				        textView1.setTextSize(20);
						textView02.setTextSize(18);
						textView2.setTextSize(18);
						textView3.setTextSize(18);
					}
		       }
		       
		    }
	     
	     
	     
	     @Override
		    public boolean onKeyDown(int keycode, KeyEvent e) {
		        switch(keycode) {
		           
		            case KeyEvent.KEYCODE_BACK:
		            	Intent intent18 = new Intent(this, ActivityApps.class);
		             	 startActivity(intent18);

		       		overridePendingTransition(center_to_right, center_to_right2);
		                return true;
		            
		        }
		        return super.onKeyDown(keycode, e);
		   }
	     
	  }
	
	

