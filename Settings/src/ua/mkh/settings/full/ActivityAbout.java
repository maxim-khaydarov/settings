package ua.mkh.settings.full;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAbout extends Activity implements View.OnClickListener{
	
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;

	Button btn_back, btn_name;
	Button Button01, Button02, Button03, Button04, Button05, Button06, Button07, Button08, Button09, Button10, Button11,
	Button12, Button13, Button14, Button15;
	TextView textStatus, textView001, TextView01, TextView02, TextView03, TextView04,
	 TextView05, TextView06, text_name, TextView07, TextView08, TextView09, TextView10, TextView11, TextView12, TextView13, TextView14;
	String IMEI, serialNumber;
	String address, nameDevice;
	
	
	LinearLayout LinearLayoutNumber, LinearLayoutMacBluetooth, LinearLayoutMacWifi;
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	public static final String APP_PREFERENCES_bold_text = "bold_txt";
	public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	public static final String APP_PREFERENCES_NETWORK = "networkText";
	public static final String APP_PREFERENCES_NAME_DEVICE= "nameText";

	   int menui = 0;
	   
	  
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   int wifi_con;
	   
	   SharedPreferences mSettings;
	
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_about);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			
			

			LinearLayoutNumber = (LinearLayout) findViewById(R.id.LinearLayoutNumber);
			LinearLayoutNumber.setVisibility(View.GONE);
			
			LinearLayoutMacBluetooth = (LinearLayout) findViewById(R.id.LinearLayoutMacBluetooth);
			LinearLayoutMacBluetooth.setVisibility(View.GONE);
			
			LinearLayoutMacWifi  = (LinearLayout) findViewById(R.id.LinearLayoutMacWifi);
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
			 btn_back.setText(R.string.button_general);
			 textStatus = (TextView)findViewById(R.id.textOk);
			 textStatus.setText(R.string.about_this);
			 
			 
			 btn_name = (Button) findViewById(R.id.ButtonName);
			 btn_name.setOnClickListener(this);
			 
			 Button01 = (Button) findViewById(R.id.Button01);
			 Button02 = (Button) findViewById(R.id.Button02);
			 Button03 = (Button) findViewById(R.id.Button03);
			 Button04 = (Button) findViewById(R.id.Button04);
			 Button05 = (Button) findViewById(R.id.Button05);
			 Button06 = (Button) findViewById(R.id.Button06);
			 Button07 = (Button) findViewById(R.id.Button07);
			 Button08 = (Button) findViewById(R.id.Button08);
			 Button09 = (Button) findViewById(R.id.Button09);
			 Button10 = (Button) findViewById(R.id.Button10);
			 Button10.setOnClickListener(this);
			 Button11 = (Button) findViewById(R.id.Button11);
			 Button12 = (Button) findViewById(R.id.Button12);
			 Button13 = (Button) findViewById(R.id.Button13);
			 Button14 = (Button) findViewById(R.id.Button14);
			 Button15 = (Button) findViewById(R.id.Button15);
			 
			 textView001 = (TextView) findViewById(R.id.textView001);
			 TextView01 = (TextView) findViewById(R.id.TextView01);
			 TextView02 = (TextView) findViewById(R.id.TextView02);
			 TextView03 = (TextView) findViewById(R.id.TextView03);
			 TextView04 = (TextView) findViewById(R.id.TextView04);
			 TextView05 = (TextView) findViewById(R.id.TextView05);
			 TextView06 = (TextView) findViewById(R.id.TextView06);
			 TextView07  = (TextView) findViewById(R.id.TextView07);
			 TextView08  = (TextView) findViewById(R.id.TextView08);
			 TextView09  = (TextView) findViewById(R.id.TextView09);
			 TextView10  = (TextView) findViewById(R.id.TextView10);
			 TextView11  = (TextView) findViewById(R.id.TextView11);
			 TextView12  = (TextView) findViewById(R.id.TextView12);
			 TextView13  = (TextView) findViewById(R.id.TextView13);
			 TextView14  = (TextView) findViewById(R.id.TextView14);
			 text_name = (TextView) findViewById(R.id.textName);
			 
			 TextView01.setText(R.string.version_about_c);
			 textStatus.setTypeface(typefaceMedium);
			 btn_back.setTypeface(typefaceBold);
			 
			 textView001.setTypeface(typefaceRoman);
			 TextView01.setTypeface(typefaceRoman);
			 TextView02.setTypeface(typefaceRoman);
			 TextView03.setTypeface(typefaceRoman);
			 TextView04.setTypeface(typefaceRoman);
			 TextView05.setTypeface(typefaceRoman);
			 TextView06.setTypeface(typefaceRoman);
			 TextView07.setTypeface(typefaceRoman);
			 TextView08.setTypeface(typefaceRoman);
			 TextView09.setTypeface(typefaceRoman);
			 TextView10.setTypeface(typefaceRoman);
			 TextView11.setTypeface(typefaceRoman);
			 TextView12.setTypeface(typefaceRoman);
			 TextView13.setTypeface(typefaceRoman);
			 TextView14.setTypeface(typefaceRoman);
			 text_name.setTypeface(typefaceRoman);
			 
			 Button01.setTypeface(typefaceRoman);
			 Button02.setTypeface(typefaceRoman);
			 Button03.setTypeface(typefaceRoman);
			 Button04.setTypeface(typefaceRoman);
			 Button05.setTypeface(typefaceRoman);
			 Button06.setTypeface(typefaceRoman);
			 Button07.setTypeface(typefaceRoman);
			 Button08.setTypeface(typefaceRoman);
			 Button09.setTypeface(typefaceRoman);
			 Button10.setTypeface(typefaceRoman);
			 Button11.setTypeface(typefaceRoman);
			 Button12.setTypeface(typefaceRoman);
			 Button13.setTypeface(typefaceRoman);
			 Button14.setTypeface(typefaceRoman);
			 Button15.setTypeface(typefaceRoman);
			 btn_name.setTypeface(typefaceRoman);
			 
			 
			 
}
	   private void mac_bluetooth (){
		   BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		   /* mBluetoothAdapter.disable(); */
		   if(mBluetoothAdapter != null){
			   LinearLayoutMacBluetooth.setVisibility(View.VISIBLE);
			   String mb = mBluetoothAdapter.getAddress();
			   TextView06.setText(mb);
		    }
	   }
	   
	   private void mac_wifi (){
		   
		   WifiInfo localWifiInfo = ((WifiManager)getSystemService("wifi")).getConnectionInfo();
		   
		   TextView05.setText(localWifiInfo.getMacAddress());
		   
	   }
	   
	    
	    
	    private void getAudioList() {
	    	try{
	    	 int music_col = 0;
	  	  
	        final Cursor mCursor = getContentResolver().query(
	                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
	                new String[] { MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA }, null, null,
	                "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");

	        int i = 0;
	        if (mCursor.moveToFirst()) {
	            do {
	                
	                music_col = music_col + 1;
	                i++;
	            } while (mCursor.moveToNext());
	        }   

	        mCursor.close();
	        
	        TextView10.setText(String.valueOf(music_col));
	    	}catch(NullPointerException e){
	    		 TextView10.setText("Unknow");
	    	}
	        
	    }
	    
	    private void getPhotoList() {
	    	try{
	    	 int photo_col = 0;
	    	 
	        final Cursor mCursor = getContentResolver().query(
	                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	                new String[] { MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA }, null, null,
	                null);

	        int i = 0;
	        if (mCursor.moveToFirst()) {
	            do {
	                
	                photo_col = photo_col + 1;
	                i++;
	            } while (mCursor.moveToNext());
	        }   

	        mCursor.close();
	        
	        TextView12.setText(String.valueOf(photo_col));
	    	}catch(NullPointerException e){
	    		TextView12.setText("Unknow");
	    	}
	        
	    }
	    
	    private void getVideoList() {
	    	try{
	    	int video_col = 0;
		  	  
		  	  
	        final Cursor mCursor = getContentResolver().query(
	                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
	               null, null, null,
	                null);

	        int i = 0;
	        if (mCursor.moveToFirst()) {
	            do {
	                
	                video_col = video_col + 1;
	                i++;
	            } while (mCursor.moveToNext());
	        }   

	        mCursor.close();
	        TextView11.setText(String.valueOf(video_col));
	    	}catch(NullPointerException e){
	    		TextView11.setText("Unknow");
	    	}
	    }
	    
	    private void getAppsList() {
	    	 int apps_col = 0;
	    final PackageManager packageManager = getPackageManager();
	    List<ApplicationInfo> installedApplications = 
	       packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

	    for (ApplicationInfo appInfo : installedApplications)
	    {
	        apps_col = apps_col + 1; 
	    } 
	    
	    TextView13.setText(String.valueOf(apps_col));
	    }
	    
	    
	   private void operator(){
		   TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		   int status=Settings.System.getInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 0);
	        if(status==1) //Means Airplane mode is ON
	        		{
	        	textView001.setText("Unknow");
	 		   TextView04.setText("Unknow");
	 		   TextView03.setText("Unknow");
	 		   TextView14.setText("Unknow");
	 		   TextView06.setText("Unknow");
	            	}
	        	
		   textView001.setText(tManager.getSimOperatorName());
  		   TextView04.setText(tManager.getNetworkOperatorName());
  		   TextView14.setText(tManager.getSimSerialNumber());
  		   
  		   if (textView001.getText().toString().length() == 0){
  		 
  	  textView001.setText("Unknow");
	   TextView04.setText("Unknow");
	   TextView03.setText("Unknow");
	   TextView14.setText("Unknow");}
		  
	   }
	   
	   private void serial_number() {
		   TextView02.setText(Build.SERIAL);
	   }
	   
	   private void telephone_number_IMEI (){
		   SimNoInfo telephonyInfo = SimNoInfo.getInstance(this);

		    String imeiSIM1 = telephonyInfo.getImeiSIM1();
		    String imeiSIM2 = telephonyInfo.getImeiSIM2();

		    boolean isSIM1Ready = telephonyInfo.isSIM1Ready();
		    boolean isSIM2Ready = telephonyInfo.isSIM2Ready();

		    boolean isDualSIM = telephonyInfo.isDualSIM();
		    if (isDualSIM == true){
		    	TextView03.setText(imeiSIM1 + "\n" + imeiSIM2);
		    	LinearLayoutNumber.setVisibility(View.VISIBLE);
		    	TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		    	String phoneNumber = tManager.getLine1Number();
		    	/*if (phoneNumber.contains("")){
		    	}
		    	else{
		    		TextView04.setText(phoneNumber);
		    	}*/
		    }
		    else{
		    	LinearLayoutNumber.setVisibility(View.VISIBLE);
		    	TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		    	String phoneNumber = tManager.getLine1Number();
		    	if (phoneNumber != null){
		    		//TextView04.setText(phoneNumber);
		    	}
		    	
		    	
		    	if (isSIM1Ready == true){
		    		TextView03.setText(imeiSIM1);
		    	}
		    	else if (isSIM2Ready == true){
		    		TextView03.setText(imeiSIM2);
		    	}
		    }
	   }
	   
	   protected void onResume() {
	        super.onResume();    
	        
	        telephone_number_IMEI();
	        serial_number();
	       
	        mac_wifi ();
	        mac_bluetooth();
	        memory ();
	        operator();
	        
	        getAudioList();
	        getPhotoList();
	        getVideoList();
	        getAppsList();
	        
	        PackageInfo pInfo;
	        String versionNow = null;
			try {
				pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
				 versionNow = pInfo.versionName;
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			TextView09.setText(versionNow);
	        
	        if (mSettings.contains(APP_PREFERENCES_NAME_DEVICE)) {
				// �������� ����� �� ��������
	        	 String name = mSettings.getString(APP_PREFERENCES_NAME_DEVICE, null);
	        	 nameDevice = name;
	        	 text_name.setText(name);
	        	 
				}
	        else {
	        	nameDevice ();
	        }
	        
	        
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
				
	    
	        
	        if (mSettings.contains(APP_PREFERENCES_NETWORK)) {
				// �������� ����� �� ��������
	        	 String network = mSettings.getString(APP_PREFERENCES_NETWORK, null);
	        	 textView001.setText(network);
				}
	       
	       if (mSettings.contains(APP_PREFERENCES_tgb_menu)) {
				// �������� ����� �� ��������
	        	 Boolean menu = mSettings.getBoolean(APP_PREFERENCES_tgb_menu, true);
				if (menu == true){
					menui=1;
				}
				else{
					menui=0;
				}
	        }
	       
	       
	       
	       if (mSettings.contains(APP_PREFERENCES_bold_text)) {
				// �������� ����� �� ��������
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					btn_name.setTypeface(typefaceBold);
					 Button01.setTypeface(typefaceBold);
				     Button02.setTypeface(typefaceBold);
				     Button03.setTypeface(typefaceBold);
				     Button04.setTypeface(typefaceBold);
				     Button05.setTypeface(typefaceBold);
				     Button06.setTypeface(typefaceBold);
				     Button07.setTypeface(typefaceBold);
				     Button08.setTypeface(typefaceBold);
				     Button09.setTypeface(typefaceBold);
				     Button10.setTypeface(typefaceBold);
				     Button11.setTypeface(typefaceBold);
				     Button12.setTypeface(typefaceBold);
				     Button13.setTypeface(typefaceBold);
				     Button14.setTypeface(typefaceBold);
				     Button15.setTypeface(typefaceBold);
				     
				     textView001.setTypeface(typefaceBold);
				     TextView01.setTypeface(typefaceBold);
				     TextView02.setTypeface(typefaceBold);
				     TextView03.setTypeface(typefaceBold);
				     TextView04.setTypeface(typefaceBold);
				     TextView05.setTypeface(typefaceBold);
				     TextView06.setTypeface(typefaceBold);
				     TextView07.setTypeface(typefaceBold);
				     TextView08.setTypeface(typefaceBold);
				     TextView09.setTypeface(typefaceBold);
				     TextView10.setTypeface(typefaceBold);
				     TextView11.setTypeface(typefaceBold);
				     TextView12.setTypeface(typefaceBold);
				     TextView13.setTypeface(typefaceBold);
				     TextView14.setTypeface(typefaceBold);
				     text_name.setTypeface(typefaceBold);
					
				}
	        }
	       
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// �������� ����� �� ��������
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					btn_name.setTextSize(13);
					 Button01.setTextSize(13);
				     Button02.setTextSize(13);
				     Button03.setTextSize(13);
				     Button04.setTextSize(13);
				     Button05.setTextSize(13);
				     Button06.setTextSize(13);
				     Button07.setTextSize(13);
				     Button08.setTextSize(13);
				     Button09.setTextSize(13);
				     Button10.setTextSize(13);
				     Button11.setTextSize(13);
				     Button12.setTextSize(13);
				     Button13.setTextSize(13);
				     Button14.setTextSize(13);
				     Button15.setTextSize(13);
				     
				     textView001.setTextSize(13);
				     TextView01.setTextSize(13);
				     TextView02.setTextSize(13);
				     TextView03.setTextSize(13);
				     TextView04.setTextSize(13);
				     TextView05.setTextSize(13);
				     TextView06.setTextSize(13);
				     TextView07.setTextSize(13);
				     TextView08.setTextSize(13);
				     TextView09.setTextSize(13);
				     TextView10.setTextSize(13);
				     TextView11.setTextSize(13);
				     TextView12.setTextSize(13);
				     TextView13.setTextSize(13);
				     TextView14.setTextSize(13);
				     text_name.setTextSize(13);
				}
				if (size .contains( "Normal")){
					btn_name.setTextSize(15);
					 Button01.setTextSize(15);
				     Button02.setTextSize(15);
				     Button03.setTextSize(15);
				     Button04.setTextSize(15);
				     Button05.setTextSize(15);
				     Button06.setTextSize(15);
				     Button07.setTextSize(15);
				     Button08.setTextSize(15);
				     Button09.setTextSize(15);
				     Button10.setTextSize(15);
				     Button11.setTextSize(15);
				     Button12.setTextSize(15);
				     Button13.setTextSize(15);
				     Button14.setTextSize(15);
				     Button15.setTextSize(15);
				     
				     textView001.setTextSize(15);
				     TextView01.setTextSize(15);
				     TextView02.setTextSize(15);
				     TextView03.setTextSize(15);
				     TextView04.setTextSize(15);
				     TextView05.setTextSize(15);
				     TextView06.setTextSize(15);
				     TextView07.setTextSize(15);
				     TextView08.setTextSize(15);
				     TextView09.setTextSize(15);
				     TextView10.setTextSize(15);
				     TextView11.setTextSize(15);
				     TextView12.setTextSize(15);
				     TextView13.setTextSize(15);
				     TextView14.setTextSize(15);
				     text_name.setTextSize(15);
				}
				if (size .contains( "Large")){
					btn_name.setTextSize(18);
				     Button02.setTextSize(18);
				     Button01.setTextSize(18);
				     Button03.setTextSize(18);
				     Button04.setTextSize(18);
				     Button05.setTextSize(18);
				     Button06.setTextSize(18);
				     Button07.setTextSize(18);
				     Button08.setTextSize(18);
				     Button09.setTextSize(18);
				     Button10.setTextSize(18);
				     Button11.setTextSize(18);
				     Button12.setTextSize(18);
				     Button13.setTextSize(18);
				     Button14.setTextSize(18);
				     Button15.setTextSize(18);
				     
				     textView001.setTextSize(18);
				     TextView01.setTextSize(18);
				     TextView02.setTextSize(18);
				     TextView03.setTextSize(18);
				     TextView04.setTextSize(18);
				     TextView05.setTextSize(18);
				     TextView06.setTextSize(18);
				     TextView07.setTextSize(18);
				     TextView08.setTextSize(18);
				     TextView09.setTextSize(18);
				     TextView10.setTextSize(18);
				     TextView11.setTextSize(18);
				     TextView12.setTextSize(18);
				     TextView13.setTextSize(18);
				     TextView14.setTextSize(18);
				     text_name.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					btn_name.setTextSize(20);
				     Button02.setTextSize(20);
				     Button01.setTextSize(20);
				     Button03.setTextSize(20);
				     Button04.setTextSize(20);
				     Button05.setTextSize(20);
				     Button06.setTextSize(20);
				     Button07.setTextSize(20);
				     Button08.setTextSize(20);
				     Button09.setTextSize(20);
				     Button10.setTextSize(20);
				     Button11.setTextSize(20);
				     Button12.setTextSize(20);
				     Button13.setTextSize(20);
				     Button14.setTextSize(20);
				     Button15.setTextSize(20);
				     
				     textView001.setTextSize(20);
				     TextView01.setTextSize(20);
				     TextView02.setTextSize(20);
				     TextView03.setTextSize(20);
				     TextView04.setTextSize(20);
				     TextView05.setTextSize(20);
				     TextView06.setTextSize(20);
				     TextView07.setTextSize(20);
				     TextView08.setTextSize(20);
				     TextView09.setTextSize(20);
				     TextView10.setTextSize(20);
				     TextView11.setTextSize(20);
				     TextView12.setTextSize(20);
				     TextView13.setTextSize(20);
				     TextView14.setTextSize(20);
				     text_name.setTextSize(20);
				}
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
		    
		    TextView07.setText(twoDForm.format(ios/1073741824f) +" " + getResources().getString(R.string.GigaByte));
	    	TextView08.setText(twoDForm.format(iss/1073741824f) +" " + getResources().getString(R.string.GigaByte));
	   }
	   
	   private void nameDevice (){
		   
			   text_name.setText(R.string.version_about_c);
			
				
	   }
	   
	  
	   
	   public void onClick(View v) {
	    	int id = v.getId();
	    	
	    	if (id == R.id.ButtonName){
	    		enter_name_device();
	    		/*
	    		AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAbout.this);
	    		final EditText input = new EditText(this);
	    		input.setText(nameDevice);
	    		builder.setView(input);
	    		builder
	    				.setCancelable(false)
	    				.setNegativeButton("Cancel",
	    						new DialogInterface.OnClickListener() {
	    							public void onClick(DialogInterface dialog, int id) {
	    								dialog.cancel();
	    							}
	    						})
	    						.setNeutralButton("Clear",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int id) {
                                	String dd = getResources().getString(R.string.version_about_c);
                                	Editor editor = mSettings.edit();
    							   	editor.putString(APP_PREFERENCES_NAME_DEVICE, dd);
    							   	editor.apply();
                                    
    							   	text_name.setText(dd);
    							   	
                                    dialog.cancel();
                                }
                            })
	    						.setPositiveButton("��",
	    						new DialogInterface.OnClickListener() {
	    							public void onClick(DialogInterface dialog, int id) {
	    								String value = input.getText().toString();
	    								
	    								Editor editor = mSettings.edit();
	    								nameDevice = value;
	    							   	editor.putString(APP_PREFERENCES_NAME_DEVICE, value);
	    							   	editor.apply();
	    							   	text_name.setText(value);
	    								
	    							}
	    						});
	    		AlertDialog alert = builder.create();
	    		alert.show();*/
	    	}
	    	
	    	else if (id == R.id.Button10){
	    		Intent intent18 = new Intent(this, ActivityInformations.class);
            	 startActivity(intent18);

      		overridePendingTransition(center_to_left, center_to_left2);
	    	}
	   }
	   
	   private void enter_name_device() {
	    	final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     dialog.setContentView(R.layout.dialog_3_button);
		     
		     
		     Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.dialogButtonCancel);
		     Button ButtonMenuContinue = (Button)dialog.getWindow().findViewById(R.id.dialogButtonOK);
		     Button ButtonMenuClean = (Button)dialog.getWindow().findViewById(R.id.dialogButtonClean);
		     final EditText Ed = (EditText) dialog.getWindow().findViewById(R.id.editText1);
	     
		     ButtonMenuCancel.setTypeface(typefaceRoman);
		     ButtonMenuClean.setTypeface(typefaceRoman);
		     ButtonMenuContinue.setTypeface(typefaceRoman);
		     Ed.setTypeface(typefaceRoman);
		     
		     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View v) {
		    dialog.dismiss();
		   }});
		     
		     ButtonMenuContinue.setOnClickListener(new OnClickListener(){

				   @Override
				   public void onClick(View v) {
					   String value = Ed.getText().toString();
						Editor editor = mSettings.edit();
						nameDevice = value;
					   	editor.putString(APP_PREFERENCES_NAME_DEVICE, value);
					   	editor.apply();
					   	text_name.setText(value);
					   	BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
					   	bluetoothAdapter.setName(value);
					   	dialog.dismiss();
				   }
				});
		     
		     ButtonMenuClean.setOnClickListener(new OnClickListener(){

				   @Override
				   public void onClick(View v) {
					   String dd = getResources().getString(R.string.version_about_c);
                   	Editor editor = mSettings.edit();
					   	editor.putString(APP_PREFERENCES_NAME_DEVICE, dd);
					   	editor.apply();
					   	text_name.setText(dd);
					   	dialog.dismiss();
				   }
				});
		     dialog.show();
		    }
	   
	   @Override
	   protected void onPause() {
	     super.onPause();
	    if (wifi_con == 0){
	    	WifiManager wifi = (WifiManager) this
                    .getSystemService(this.WIFI_SERVICE);
	    	wifi.setWifiEnabled(false);
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
	            	Intent intent18 = new Intent(this, ActivityOsnova.class);
	             	 startActivity(intent18);

	       		overridePendingTransition(center_to_right, center_to_right2);
	                return true;
	            
	        }
	        return super.onKeyDown(keycode, e);
	   }
	        
	        private void openDialog(){
	   	     final Dialog dialog = new Dialog(ActivityAbout.this,android.R.style.Theme_Translucent);
	   	     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	   	     dialog.setContentView(R.layout.dialog_menu);
	   	     dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	   	     Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuCancel);
	   	     Button ButtonMenuSettings = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuSettings);
	   	     ButtonMenuSettings.setTypeface(typefaceRoman);
	   	     ButtonMenuCancel.setTypeface(typefaceMedium);
	   	     
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
	            Intent it = new Intent(ActivityAbout.this, SettingsActivity.class);
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
