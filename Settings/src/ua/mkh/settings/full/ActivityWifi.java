package ua.mkh.settings.full;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

import android.app.ActionBar;
import android.app.Activity;    
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;    
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.InputType;
import android.text.format.Formatter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivityWifi extends Activity implements OnClickListener, SimpleGestureListener {
	
	TextView textConnected;
	final Context context = this;
	ToggleButton tb_in, tb_wifi, tb_ch, buttonWifi; 
	WifiManager wifi;
	TextView  textStatus, txtPercentage, textView3, textView4, textView5, textView6, textView1;
	Button btn_back, btn_1, Button04;
	TableLayout tableLayout;
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	LinearLayout LinearLayoutCon, MainLayout2;
	RelativeLayout LayoutMain;
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui= 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   
	   SharedPreferences mSettings;
	   private SimpleGestureFilter detector;
	   
	   ListView lv;
	   ImageView img1, img2;
	   String Capabilities;

	  Button buttonScan, info;
	  int size = 0;
	  List<ScanResult> results;
	  private String m_Text = "";


	  String ITEM_KEY = "key", WIFI_KEY = "wifi_key", RSSI_KEY = "rssi_key";
	  ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
	  SimpleAdapter adapter;
	
	    /* Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_wifi);
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
			
			LinearLayoutCon = (LinearLayout) findViewById(R.id.LinearLayoutCon);
			LayoutMain = (RelativeLayout) findViewById(R.id.LayoutMain);
			MainLayout2 = (LinearLayout) findViewById(R.id.MainLayout2);
			LayoutMain.setVisibility(View.GONE);
			MainLayout2.setVisibility(View.GONE);
			
			
			btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.app_name);
			textStatus = (TextView)findViewById(R.id.textOk);
			btn_1 = (Button) findViewById(R.id.ButtonWifi);
			buttonWifi = (ToggleButton)findViewById(R.id.ToggleButton01);
			buttonWifi.setOnClickListener(this);
			
			
				
				
			
			tb_wifi = (ToggleButton) findViewById(R.id.ToggleButton01);
			tb_wifi.setOnClickListener(this);
			 tb_in = (ToggleButton) findViewById(R.id.toggleButtonInfo);
			 tb_in.setOnClickListener(this);
			 wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE); 
			 tb_ch = (ToggleButton) findViewById(R.id.toggleButtonChek);
			 tb_ch.setOnClickListener(this);
			 
			 info = (Button) findViewById(R.id.button1);
			 info.setOnClickListener(this);
			 
			 textConnected = (TextView)findViewById(R.id.Connected);
			 textConnected.setOnClickListener(this);
		      
		       txtPercentage= (TextView)findViewById(R.id.txtPercentage);
		       textView1 = (TextView)findViewById(R.id.textView1);
		       textView3 = (TextView)findViewById(R.id.textView3);
		       textView4 = (TextView)findViewById(R.id.textView4);
		       textView5 = (TextView)findViewById(R.id.textView5);
		       textView6 = (TextView)findViewById(R.id.textView6);
		       
		       
		      
		       
		       textConnected.setTypeface(typefaceRoman);
		      
		       tb_in.setVisibility(View.INVISIBLE);
          		tb_ch.setVisibility(View.INVISIBLE);
          		img2 = (ImageView) findViewById(R.id.imageView1);
          		img2.setVisibility(View.INVISIBLE);
          		
          		textStatus.setTypeface(typefaceMedium);
    			btn_back.setTypeface(typefaceMedium);
    			textStatus.setText(R.string.button_wifi);
    			btn_1.setTypeface(typefaceRoman);
    			
    			/*
    			textView1.setTypeface(typefaceRoman);
    			textView3.setTypeface(typefaceRoman);
    			textView4.setTypeface(typefaceRoman);
    			textView5.setTypeface(typefaceRoman);
    			textView6.setTypeface(typefaceRoman);
    			*/
    			
    			
		       DisplayWifiState();
		       
		       
		      
		       
	       
		       lv = (ListView)findViewById(R.id.list);	 
		       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		           @Override
		           public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		                   long arg3) 
		           {
		               connectToWifi(arg2);
		           }
		           
		           

		           private void connectToWifi(final int position)
		           {

		                   final int value = results.size()-1 - position;



		                   Capabilities =  results.get(value).capabilities;
		                  
		                  

		                   //Then you could add some code to check for a specific security type. 
		                   if(Capabilities.contains("WPA"))

		                {/*
		                       AlertDialog.Builder builder = new AlertDialog.Builder(ActivityWifi.this);
		                       builder.setTitle("Password:");

		                       // Set up the input
		                       final EditText input = new EditText(ActivityWifi.this);
		                       // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
		                       input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		                       builder.setView(input);

		                       // Set up the buttons
		                       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		                           @Override
		                           public void onClick(DialogInterface dialog, int which) {
		                               m_Text = input.getText().toString();
		                               WifiConfiguration wifiConfiguration = new WifiConfiguration(); 
		                               wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
		                               wifiConfiguration.preSharedKey = "\""+ m_Text +"\"";
		                               wifiConfiguration.hiddenSSID = true;
		                               wifiConfiguration.status = WifiConfiguration.Status.ENABLED;        
		                               wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA); // For WPA
		                               wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN); // For WPA2
		                               wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		                               wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
		                               wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		                               wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		                               int res = wifi.addNetwork(wifiConfiguration);
		                               Log.d("WifiPreference", "add Network returned " + res );
		                               boolean b = wifi.enableNetwork(res, true);        
		                               Log.d("WifiPreference", "enableNetwork returned " + b );

		                           }
		                       });
		                       builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		                           @Override
		                           public void onClick(DialogInterface dialog, int which) {
		                               dialog.cancel();
		                           }
		                       });

		                       builder.show();
		                 	*/
		                	   
		                	   final Dialog dialog = new Dialog(ActivityWifi.this,android.R.style.Theme_Translucent);
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
			          		     Ed.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			          		     
			          		     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

			          		   @Override
			          		   public void onClick(View v) {
			          		    dialog.dismiss();
			          		   }});
			          		     
			          		     ButtonMenuContinue.setOnClickListener(new OnClickListener(){

			          				   @Override
			          				   public void onClick(View v) {
			          					 m_Text = Ed.getText().toString();
			                               WifiConfiguration wifiConfiguration = new WifiConfiguration(); 
			                               wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
			                               wifiConfiguration.preSharedKey = "\""+ m_Text +"\"";
			                               wifiConfiguration.hiddenSSID = true;
			                               wifiConfiguration.status = WifiConfiguration.Status.ENABLED;        
			                               wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA); // For WPA
			                               wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN); // For WPA2
			                               wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			                               wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
			                               wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
			                               wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
			                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
			                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
			                               int res = wifi.addNetwork(wifiConfiguration);
			                               Log.d("WifiPreference", "add Network returned " + res );
			                               boolean b = wifi.enableNetwork(res, true);        
			                               Log.d("WifiPreference", "enableNetwork returned " + b );
			          					   	dialog.dismiss();
			          				   }
			          				});
			          		     
			          		     ButtonMenuClean.setOnClickListener(new OnClickListener(){

			          				   @Override
			          				   public void onClick(View v) {
			          					   	Ed.setText("");
			          				   }
			          				});
			          		     dialog.show();
		                    }


		                   else if(Capabilities.contains("WEP"))
		                    {
		                	   final Dialog dialog = new Dialog(ActivityWifi.this,android.R.style.Theme_Translucent);
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
		          		     Ed.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		          		     
		          		     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

		          		   @Override
		          		   public void onClick(View v) {
		          		    dialog.dismiss();
		          		   }});
		          		     
		          		     ButtonMenuContinue.setOnClickListener(new OnClickListener(){

		          				   @Override
		          				   public void onClick(View v) {
		          					 m_Text = Ed.getText().toString();
		                               WifiConfiguration wifiConfiguration = new WifiConfiguration(); 
		                               wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
		                               wifiConfiguration.wepKeys[0] = "\"" + m_Text + "\""; 
		                               wifiConfiguration.wepTxKeyIndex = 0;
		                               wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		                               int res = wifi.addNetwork(wifiConfiguration);
		                               Log.d("WifiPreference", "add Network returned " + res );
		                               boolean b = wifi.enableNetwork(res, true);        
		                               Log.d("WifiPreference", "enableNetwork returned " + b );
		          					   	dialog.dismiss();
		          				   }
		          				});
		          		     
		          		     ButtonMenuClean.setOnClickListener(new OnClickListener(){

		          				   @Override
		          				   public void onClick(View v) {
		          					   	Ed.setText("");
		          				   }
		          				});
		          		     dialog.show();
		          		     
		          		     //////////////////////////////////////////////////////////
		          		     /*
		                       AlertDialog.Builder builder = new AlertDialog.Builder(ActivityWifi.this);
		                       builder.setTitle("Title");

		                       // Set up the input
		                       final EditText input = new EditText(ActivityWifi.this);
		                       // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
		                       input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		                       builder.setView(input);

		                       // Set up the buttons
		                       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		                           @Override
		                           public void onClick(DialogInterface dialog, int which) {
		                               m_Text = input.getText().toString();
		                               WifiConfiguration wifiConfiguration = new WifiConfiguration(); 
		                               wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
		                               wifiConfiguration.wepKeys[0] = "\"" + m_Text + "\""; 
		                               wifiConfiguration.wepTxKeyIndex = 0;
		                               wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		                               wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		                               int res = wifi.addNetwork(wifiConfiguration);
		                               Log.d("WifiPreference", "add Network returned " + res );
		                               boolean b = wifi.enableNetwork(res, true);        
		                               Log.d("WifiPreference", "enableNetwork returned " + b );
		                               
		                               
		                           }
		                       });
		                       builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		                           @Override
		                           public void onClick(DialogInterface dialog, int which) {
		                               dialog.cancel();
		                           }
		                       });

		                       builder.show();

		          		      */
		                    }
		                   else
		                    { 
		                       WifiConfiguration wifiConfiguration = new WifiConfiguration(); 
		                       wifiConfiguration.SSID = "\"" + results.get(value).SSID + "\"";
		                       wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		                       int res = wifi.addNetwork(wifiConfiguration);
		                       Log.d("WifiPreference", "add Network returned " + res );
		                       boolean b = wifi.enableNetwork(res, true);        
		                       Log.d("WifiPreference", "enableNetwork returned " + b );
		                    }

		                   	

		           }
		       });
		       
		       this.adapter = new SimpleAdapter(ActivityWifi.this, arraylist, R.layout.row_wifi, new String[] { ITEM_KEY, WIFI_KEY, RSSI_KEY }, new int[] { R.id.list_value, R.id.imageView1111, R.id.imageView1 });
		       lv.setAdapter(this.adapter);

		       registerReceiver(new BroadcastReceiver()
		       {
		           @Override
		           public void onReceive(Context c, Intent intent) 
		           {
		           arraylist.clear(); 
		              results = wifi.getScanResults();
		              size = results.size();
		              try 
		              {
		                  size = size - 1;
		                  while (size >= 0) 
		                  {   
		                      HashMap<String, String> item = new HashMap<String, String>(); 
		                      int result = results.get(size).level;
		                      
		                      if(results.get(size).SSID.length() != 0){
		                    	  item.put(ITEM_KEY, results.get(size).SSID);
		                      }
		                      else{
		                    	  item.put(ITEM_KEY, getString(R.string.bluetooth_hidden)); }
		                      
		                      
		                      
		                      int w = result;
		                      
		                      if (w > -67){
		                    	  item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_4));
		                      }
		                      else if (w > -79){
		                    	  item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_3));
		                      }
		                      else if (w > -91){
		                    	  item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_2));
		                      }
		                      else {
		                    	  item.put(RSSI_KEY, Integer.toString(R.drawable.wifi_signal_1));
		                      }
		                      		                      
		                      Capabilities =  results.get(size).capabilities;
		                      if(Capabilities.contains("WPA"))

				                {
		                    	  item.put(WIFI_KEY, Integer.toString(R.drawable.lock_wifi));
				                }
		                      
		                      if(Capabilities.contains("WEP"))
			                    {
		                    	  item.put(WIFI_KEY, Integer.toString(R.drawable.lock_wifi));
			                    }
		                      
		                      
		                      arraylist.add(item);
		                      size--;
		                      adapter.notifyDataSetChanged();                 
		                  } 
		              }
		              catch (Exception e)
		              {  }  
		              setListViewHeightBasedOnChildren(lv);
		           }
		       }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		       
		       
	    }
	    
	    @Override
		public void onPause() {
			super.onPause();
			unregisterReceiver ( myWifiReceiver );
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
	    	  Intent intent18 = new Intent(this, MainActivity.class);
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
	        wifi.startScan();
	       ButtonWifi();
	       ButtonWifi1();
	       this.registerReceiver(this.myWifiReceiver,
			         new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	       
	       
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
					btn_1.setTypeface(typefaceBold);
	    			txtPercentage.setTypeface(typefaceBold);
	    			textView1.setTypeface(typefaceBold);
	    			textView3.setTypeface(typefaceBold);
	    			textView4.setTypeface(typefaceBold);
	    			textView5.setTypeface(typefaceBold);
	    			textView6.setTypeface(typefaceBold);
	    			textConnected.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					btn_1.setTextSize(14);
	    			txtPercentage.setTextSize(13);
	    			textView1.setTextSize(11);
	    			textView3.setTextSize(13);
	    			textView4.setTextSize(13);
	    			textView5.setTextSize(13);
	    			textView6.setTextSize(13);
	    			textConnected.setTextSize(14);
				}
				if (size .contains( "Normal")){
					btn_1.setTextSize(16);
	    			txtPercentage.setTextSize(15);
	    			textView1.setTextSize(13);
	    			textView3.setTextSize(15);
	    			textView4.setTextSize(15);
	    			textView5.setTextSize(15);
	    			textView6.setTextSize(15);
	    			textConnected.setTextSize(16);
				}
				if (size .contains( "Large")){
					btn_1.setTextSize(19);
	    			txtPercentage.setTextSize(18);
	    			textView1.setTextSize(16);
	    			textView3.setTextSize(18);
	    			textView4.setTextSize(18);
	    			textView5.setTextSize(18);
	    			textView6.setTextSize(18);
	    			textConnected.setTextSize(19);
				}
				if (size .contains( "xLarge")){
					btn_1.setTextSize(21);
	    			txtPercentage.setTextSize(20);
	    			textView1.setTextSize(18);
	    			textView3.setTextSize(20);
	    			textView4.setTextSize(20);
	    			textView5.setTextSize(20);
	    			textView6.setTextSize(20);
	    			textConnected.setTextSize(21);
				}
	       }
	    }
	    
	    private void ButtonWifi1() {
	    	ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    	NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    	if(mWifi.isConnected()){
	    		info.setVisibility(View.VISIBLE);
           		tb_ch.setVisibility(View.VISIBLE);
           		img2.setVisibility(View.VISIBLE);
           		//LayoutMain.setVisibility(View.VISIBLE);
	            
	        }
	    	else {
	    		info.setVisibility(View.INVISIBLE);
           		//tb_ch.setVisibility(View.INVISIBLE);
           		//LayoutMain.setVisibility(View.GONE);
	    	}
	    	
	    } 
 private void ButtonWifi() {
	    	
	    	if(wifi.isWifiEnabled()){
	            tb_wifi.setChecked(true);
	            LayoutMain.setVisibility(View.VISIBLE);
           		MainLayout2.setVisibility(View.VISIBLE);
           		img2.setVisibility(View.VISIBLE);
	            
	        }
	    	else {
	    		LinearLayoutCon.setVisibility(View.GONE);
	    		LayoutMain.setVisibility(View.GONE);
           		MainLayout2.setVisibility(View.GONE);
	    	}
	    	
	    } 
  
 public void BackClick(View v)  
 {  
	 Intent intent18 = new Intent(this, MainActivity.class);
      	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
	 }

	    private BroadcastReceiver myWifiReceiver
	    = new BroadcastReceiver(){

	   @Override
	   public void onReceive(Context arg0, Intent arg1) {
	    // TODO Auto-generated method stub
	    NetworkInfo networkInfo = (NetworkInfo) arg1.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
	    if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
	     DisplayWifiState();
	     
	    }
	   }};
	   
	    private void DisplayWifiState(){
	     
	     ConnectivityManager myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	     NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	     WifiManager myWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
	   WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
	   
	   
	  
	   
	     if (myNetworkInfo.isConnected()){
	     
	      
	      info.setVisibility(View.VISIBLE);
			tb_ch.setVisibility(View.VISIBLE);
			img2.setVisibility(View.VISIBLE);
	      
	       
	     
	     
	       String wi = myWifiInfo.getSSID().replace('"',' ');
	     
	      textConnected.setText(wi);
	     
	     }
	     else{
	      textConnected.setText(R.string.no_connections);
	      img2.setVisibility(View.INVISIBLE);
	      
	     }
	     
	    }

		
		
		
		 public void onClick(View v) {
			 int id = v.getId();
			 /*
			 if (id == R.id.toggleButtonInfo){
		        	TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout1);
		        	if ((tb_in).isChecked())
		        	{
		        		tableLayout.setVisibility(View.VISIBLE);
		        	}
		        	else {
		        		tableLayout.setVisibility(View.GONE);
		        	}
			 }
		        */	
		        if (id ==  R.id.ToggleButton01){
		        	//TableLayout tableLayout2 = (TableLayout)findViewById(R.id.tableLayout1);
		        	tb_in = (ToggleButton) findViewById(R.id.toggleButtonInfo); 
		        	if((tb_wifi).isChecked())
	               	 {
	               		 wifi.setWifiEnabled(true);
	               		LinearLayoutCon.setVisibility(View.VISIBLE);
	               		LayoutMain.setVisibility(View.VISIBLE);
	               		MainLayout2.setVisibility(View.VISIBLE);
	               		
	               	 }
	               	 else {
	               		 wifi.setWifiEnabled(false);
	               		LinearLayoutCon.setVisibility(View.GONE);
	               		//tableLayout2.setVisibility(View.GONE);
	               		info.setVisibility(View.INVISIBLE);
	               		tb_ch.setVisibility(View.INVISIBLE);
	               		LayoutMain.setVisibility(View.GONE);
	               		MainLayout2.setVisibility(View.GONE);
	               		img2.setVisibility(View.INVISIBLE);
	               	 }
		        }
		        	
		        if (id == R.id.Button04){
		        	Intent settingsIntent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
		        	startActivity(settingsIntent);
			        	overridePendingTransition(center_to_left, center_to_left2);
			        	 }
		        
		        else if (id == R.id.Connected){
		        	WifiManager myWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		        	WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
 					DhcpInfo info = wifi.getDhcpInfo();
 					
 					
 					
 					String s_netmask = Formatter.formatIpAddress(info.netmask);
 					String s_ipAddress = Formatter.formatIpAddress(info.ipAddress); 
 					String s_serverAddress = Formatter.formatIpAddress(info.serverAddress);
 					String s_dns1 =Formatter.formatIpAddress(info.dns1);
 					String s_dns2 =Formatter.formatIpAddress(info.dns2);
 					String wi = myWifiInfo.getSSID().replace('"',' ');
 					
		        	Intent settingsIntent = new Intent(this, ActivityWifiInfo.class);
		        	settingsIntent.putExtra("ip", s_ipAddress);
		        	settingsIntent.putExtra("dns1", s_dns1);
		        	settingsIntent.putExtra("dns2", s_dns2);
		        	settingsIntent.putExtra("mask", s_netmask);
		        	settingsIntent.putExtra("marsh", s_serverAddress);
		        	settingsIntent.putExtra("name", wi);
		        	startActivity(settingsIntent);
			        	overridePendingTransition(center_to_left, center_to_left2);
		        	
		        }
		        
		        else if (id == R.id.button1){
		        	WifiManager myWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		        	WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
 					DhcpInfo info = wifi.getDhcpInfo();
 					
 					
 					
 					String s_netmask = Formatter.formatIpAddress(info.netmask);
 					String s_ipAddress = Formatter.formatIpAddress(info.ipAddress); 
 					String s_serverAddress = Formatter.formatIpAddress(info.serverAddress);
 					String s_dns1 =Formatter.formatIpAddress(info.dns1);
 					String s_dns2 =Formatter.formatIpAddress(info.dns2);
 					String wi = myWifiInfo.getSSID().replace('"',' ');
 					
		        	Intent settingsIntent = new Intent(this, ActivityWifiInfo.class);
		        	settingsIntent.putExtra("ip", s_ipAddress);
		        	settingsIntent.putExtra("dns1", s_dns1);
		        	settingsIntent.putExtra("dns2", s_dns2);
		        	settingsIntent.putExtra("mask", s_netmask);
		        	settingsIntent.putExtra("marsh", s_serverAddress);
		        	settingsIntent.putExtra("name", wi);
		        	startActivity(settingsIntent);
			        	overridePendingTransition(center_to_left, center_to_left2);
			        	
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
		            	Intent intent18 = new Intent(this, MainActivity.class);
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
		            Intent it = new Intent(ActivityWifi.this, SettingsActivity.class);
		            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		            startActivity(it); 
			        	overridePendingTransition(center_to_left, center_to_left2);
			        	 }
		        
		        public static void setListViewHeightBasedOnChildren(ListView lv) {
		            ListAdapter listAdapter = lv.getAdapter();
		            if (listAdapter == null)
		                return;

		            int desiredWidth = MeasureSpec.makeMeasureSpec(lv.getWidth(), MeasureSpec.UNSPECIFIED);
		            int totalHeight = 0;
		            View view = null;
		            for (int i = 0; i < listAdapter.getCount(); i++) {
		                view = listAdapter.getView(i, view, lv);
		                if (i == 0)
		                    view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

		                view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
		                totalHeight += view.getMeasuredHeight();
		            }
		            ViewGroup.LayoutParams params = lv.getLayoutParams();
		            params.height = totalHeight + (lv.getDividerHeight() * (listAdapter.getCount() - 1));
		            lv.setLayoutParams(params);
		            lv.requestLayout();
		        }
		        
		        
		       
		        
}

        
       






