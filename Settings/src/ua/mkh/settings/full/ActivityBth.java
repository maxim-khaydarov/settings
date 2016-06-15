package ua.mkh.settings.full;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;



import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ProgressBar;


public class ActivityBth extends Activity implements OnClickListener, SimpleGestureListener{
	 private static final int REQUEST_ENABLE_BT = 1;
	
	 private ToggleButton tb_bt;  
	 Button  btn_back;
	 TextView stateBluetooth;
	 BluetoothAdapter bluetoothAdapter;
	 //ArrayAdapter<String> btArrayAdapter;
	 TextView textView1, textView2, textStatus, nameBT, textView4;
	 Button btn_Bth;
	 int OS = android.os.Build.VERSION.SDK_INT;
	 
	
		private BluetoothAdapter mBluetoothAdapter;
	 
	 private SimpleGestureFilter detector;
	 
	 Typeface typefaceRoman, typefaceMedium, typefaceBold;
	 
	 LinearLayout mainLayout;
	 
	 public static final String APP_PREFERENCES = "mysettings"; 
	 public static final String APP_PREFERENCES_text_size = "txt_size";
	 public static final String APP_PREFERENCES_bold_text = "bold_txt";
	 public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	 public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	 
	 int menui = 0;
	 int scan_number = 0;
		
	 int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	 
	 SharedPreferences mSettings;
//////////////////////////////////////////	 
	 
	 private DeviceListAdapter mAdapter_pair;
	 private DeviceListAdapter mAdapter_scan;
	 private ArrayList<BluetoothDevice> mDeviceListPair = new ArrayList<BluetoothDevice>();
	 private ArrayList<BluetoothDevice> mDeviceListScan = new ArrayList<BluetoothDevice>();
	 ProgressBar progressBarScan;
	 
	 
	 ListView scan, listDevicesFound ;
	 
	 
	 /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_bth);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String thin = "fonts/Thin.otf";
			String bold =  "fonts/Bold.otf";
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			
			mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
			mainLayout.setVisibility(View.GONE);
			
			
			Typeface typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
	        progressBarScan = (ProgressBar) findViewById (R.id.progressBar1);
	        progressBarScan.setVisibility(View.GONE);
	        
	        tb_bt = (ToggleButton) findViewById(R.id.BTtoggle);
	        tb_bt.setOnClickListener(this);
	        
	       
	        nameBT = (TextView) findViewById(R.id.nameBT);
	        nameBT.setVisibility(View.GONE);
	        btn_Bth = (Button) findViewById(R.id.ButtonBth);
	        textView4 = (TextView) findViewById(R.id.textView4);
	        textView2 = (TextView) findViewById(R.id.textView2);
	        textView1 = (TextView) findViewById(R.id.textView1);
	        btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.app_name);
			textStatus = (TextView)findViewById(R.id.textOk);
	        
	        listDevicesFound = (ListView)findViewById(R.id.devicesfound);
	        scan = (ListView)findViewById(R.id.listView1);
	        
	        detector = new SimpleGestureFilter(this,this);
	        
	        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
	      
	        
	        String t1 = getString(R.string.bluetooth_watch1);
	        String t2s = getString(R.string.bluetooth_watch2);
	        
	        
	        
	        String t2 = "<font color=\"#0071ED\">" + t2s + "</font>";
	        String t12 = t1 + " " + t2;
	        textView4.setText(Html.fromHtml(t12), TextView.BufferType.SPANNABLE);
	        
	        
	        IntentFilter filter = new IntentFilter();
			
			filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
			filter.addAction(BluetoothDevice.ACTION_FOUND);
			filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
			filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
			
			registerReceiver(mReceiver, filter);
	        
			textView4.setTypeface(typefaceRoman);
	        textView2.setTypeface(typefaceRoman);
	        textView1.setTypeface(typefaceRoman);
	        btn_Bth.setTypeface(typefaceRoman);
	        textStatus.setTypeface(typefaceBold);
			btn_back.setTypeface(typefaceMedium);
			textStatus.setText(R.string.button_bluetooth);
			
	        
	        nameBT.setTypeface(typefaceRoman);
	        
	        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
/////////////////////////////////////
	        
	        
	        
	        
				
				mAdapter_scan		= new DeviceListAdapter(this);
				mAdapter_pair		= new DeviceListAdapter(this);
		        
		        mAdapter_pair.setListener(new DeviceListAdapter.OnPairButtonClickListener() {			
					@Override
					public void onPairButtonClick(int position) {
						BluetoothDevice device = mDeviceListPair.get(position);
						String mac = device.getAddress();
						String name = device.getName();
						connection(device, name, mac, getString(R.string.disconnected));
						
					}
				});
		        mAdapter_scan.setListener(new DeviceListAdapter.OnPairButtonClickListener() {			
					@Override
					public void onPairButtonClick(int position) {
						BluetoothDevice device = mDeviceListScan.get(position);
						String mac = device.getAddress();
						String name = device.getName();
						/*
						if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
							unpairDevice(device);
						} else {
							showToast(getString(R.string.bluetooth_pairing));
							
							pairDevice(device);
						}*/
						connection(device, name, mac, getString(R.string.connected));
					}
				});
				
		        listDevicesFound.setAdapter(mAdapter_pair);
		        setListViewHeightBasedOnChildren(listDevicesFound);
				
				registerReceiver(mPairReceiver, new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED)); 
				
				
				
/////////////////////////////////////////	        
	        CheckBlueToothState();
	        
	        

	        //registerReceiver(ActionFoundReceiver, 
	         // new IntentFilter(BluetoothDevice.ACTION_FOUND));
	    }
	    
	    private void pair (){
	    	mDeviceListPair.clear();
	    	listDevicesFound.setAdapter(null);
	    	
	        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
			
			if (pairedDevices == null || pairedDevices.size() == 0) { 
				//showToast("No Paired Devices Found");
				textView1.setText(R.string.bluetooth_no_pair);
			} else {
				ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>();
				
				mDeviceListPair.addAll(pairedDevices);
				
			}	
			
			 mAdapter_pair.setData(mDeviceListPair);
			 listDevicesFound.setAdapter(mAdapter_pair);
		        setListViewHeightBasedOnChildren(listDevicesFound);
		}
	    
	    private void connection (final BluetoothDevice device, final String name, final String mac, final String status){
	    	final Dialog Activation = new Dialog(ActivityBth.this,android.R.style.Theme_Translucent);
	        Activation.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        Activation.setContentView(R.layout.dialog_2_button);
				
				// set the custom dialog components - text, image and button

				Button dialogButton = (Button) Activation.findViewById(R.id.dialogButtonOK);
				Button dialogButtonCancel = (Button) Activation.findViewById(R.id.dialogButtonCancel);
				TextView text = (TextView)Activation.findViewById(R.id.textView1);
				TextView textver = (TextView)Activation.findViewById(R.id.textView2);
				
				dialogButton.setTypeface(typefaceMedium);
				dialogButtonCancel.setTypeface(typefaceRoman);
				dialogButton.setText(status);
				text.setTypeface(typefaceBold);
				text.setText(R.string.information);
				textver.setTypeface(typefaceRoman);
				textver.setText(getString(R.string.name_about) + ": " + name + "\n" + getString(R.string.mac) + " " + mac);
				textver.setTextSize(15);
				
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
							unpairDevice(device);
						} else {
							showToast(getString(R.string.bluetooth_pairing));
							pairDevice(device);
						}
			            
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
	    
	    private void showToast(String message) {
    		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    	}
    	
    	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    	    public void onReceive(Context context, Intent intent) {	    	
    	        String action = intent.getAction();
    	        
    	        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
    	        	final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
    	        	 
    	        	if (state == BluetoothAdapter.STATE_ON) {
    	        		//showToast("Enabled");
    	        		bluetoothAdapter.startDiscovery();
   	        		 progressBarScan.setVisibility(View.VISIBLE);
    	        		//showEnabled();
    	        	 }
    	        } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
    	        	mDeviceListScan = new ArrayList<BluetoothDevice>();
    	        	progressBarScan.setVisibility(View.VISIBLE);
    	        	scan_number = 0;
    	        	textView2.setText(R.string.bluetooth_devices_other);
    	        	scan.setVisibility(View.GONE);
    	        	pair();
    	        	
    	        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
    	        	
    	        	mAdapter_scan.setData(mDeviceListScan);
    	        	scan.setAdapter(mAdapter_scan);
    	        	setListViewHeightBasedOnChildren(scan);
    	        	progressBarScan.setVisibility(View.GONE);
    	        	scan.setVisibility(View.VISIBLE);
    	        	scan.setVisibility(View.VISIBLE);
    	        	if (scan_number == 0){
    	        		textView2.setText(R.string.bluetooth_no_device);
    	        		scan.setVisibility(View.GONE);
    	        	}
    	        	//Intent newIntent = new Intent(ActivityBth.this, DeviceListActivity.class);
    	        	
    	        	//newIntent.putParcelableArrayListExtra("device.list", mDeviceList);
    				
    				//startActivity(newIntent);
    	        } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
    	        	BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    		        
    	        	mDeviceListScan.add(device);
    	        	scan_number = 1;
    	        	
    	        	mAdapter_scan.setData(mDeviceListScan);
    	        	scan.setAdapter(mAdapter_scan);
    	        	setListViewHeightBasedOnChildren(scan);
    	        	scan.setVisibility(View.VISIBLE);
    	        	scan.setVisibility(View.VISIBLE);
    	        	
    	        	//showToast("Found device " + device.getName());
    	        }
    	    }
    	};
    	
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
	    
	    private final BroadcastReceiver mPairReceiver = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		        String action = intent.getAction();
		        
		        if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {	        	
		        	 final int state 		= intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
		        	 final int prevState	= intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);
		        	 
		        	 if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
		        		 showToast(getString(R.string.bluetooth_pair));
		        		 Intent intentx = getIntent();
		                   overridePendingTransition(0, 0);
		                   intentx.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		                   startActivity(intentx);
		        	 } else if (state == BluetoothDevice.BOND_NONE && prevState == BluetoothDevice.BOND_BONDED){
		        		 showToast(getString(R.string.bluetooth_disable_pair));
		        		 Intent intentc = getIntent();
		                   overridePendingTransition(0, 0);
		                   intentc.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		                   startActivity(intentc);
		        	 }
		        	 
		        	 mAdapter_pair.notifyDataSetChanged();
		        }
		    }
		};
	    
	    private void pairDevice(BluetoothDevice device) {
	        try {
	            Method method = device.getClass().getMethod("createBond", (Class[]) null);
	            method.invoke(device, (Object[]) null);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private void unpairDevice(BluetoothDevice device) {
	        try {
	            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
	            method.invoke(device, (Object[]) null);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	 protected void onDestroy() {
	  // TODO Auto-generated method stub
			unregisterReceiver(mReceiver);
	  super.onDestroy();
	  //ProgressBar progressBar = (ProgressBar) findViewById (R.id.progressBar1);
	  //unregisterReceiver(ActionFoundReceiver);
	  //progressBar.setVisibility(View.INVISIBLE);
	 }

	 private void CheckBlueToothState(){
	     if (bluetoothAdapter == null){
	         
	        }else{
	         if (bluetoothAdapter.isEnabled()){
	          if(bluetoothAdapter.isDiscovering()){
	           
	          }else{
	           
	          }
	         }else{
	         
	         }
	        }
	     
	    	 
	    }
	    
	    

	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // TODO Auto-generated method stub
	  if(requestCode == REQUEST_ENABLE_BT){
	   CheckBlueToothState();
	  }
	 }
	 /*   
	 private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){

	  @Override
	  public void onReceive(Context context, Intent intent) {
	   // TODO Auto-generated method stub
	   String action = intent.getAction();
	   if(BluetoothDevice.ACTION_FOUND.equals(action)) {
	             BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	             btArrayAdapter.add(device.getName() );
	             btArrayAdapter.notifyDataSetChanged();
	         }
	  }};
	  */
	  
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
	       ButtonBT();
	       
	       
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
					textView4.setTypeface(typefaceBold);
					textView2.setTypeface(typefaceBold);
			        textView1.setTypeface(typefaceBold);
			        btn_Bth.setTypeface(typefaceBold);
			        nameBT.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					textView4.setTextSize(11);
					nameBT.setTextSize(13);
					textView2.setTextSize(11);
			        textView1.setTextSize(11);
			        btn_Bth.setTextSize(13);
				}
				if (size .contains( "Normal")){
					textView4.setTextSize(13);
					nameBT.setTextSize(13);
					textView2.setTextSize(13);
			        textView1.setTextSize(13);
			        btn_Bth.setTextSize(15);
				}
				if (size .contains( "Large")){
					textView4.setTextSize(15);
					nameBT.setTextSize(15);
					textView2.setTextSize(15);
			        textView1.setTextSize(15);
			        btn_Bth.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					textView4.setTextSize(18);
					nameBT.setTextSize(18);
					textView2.setTextSize(17);
			        textView1.setTextSize(17);
			        btn_Bth.setTextSize(20);
				}
	       }
	       
	    }
	/// BLUETOOTH MODE
	    private void ButtonBT () {
	    	//BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	    	
	    	 
	    	if (bluetoothAdapter.isEnabled()) {
				mainLayout.setVisibility(View.VISIBLE);
	    	     tb_bt.setChecked(true);
	 	        nameBT.setVisibility(View.VISIBLE);
	 	       nameBT.setText(getLocalBluetoothName());
	 	      bluetoothAdapter.startDiscovery();
	 	     pair();

	    	} 
	    	else {
				mainLayout.setVisibility(View.GONE);
	    		tb_bt.setChecked(false);
		        nameBT.setVisibility(View.GONE);

	    	}
	    
	    }

	   
	    
	    
	  public void onClick(View v) {
		  int id = v.getId();
		  
	        if (id == R.id.BTtoggle){
	        	if ((tb_bt).isChecked())
	        	{
	    			mainLayout.setVisibility(View.VISIBLE);
	    			bluetoothAdapter.enable();
	        		//textView2.setText(R.string.bluetooth_search_on);
	        		scan.setVisibility(View.GONE);
	    	        nameBT.setVisibility(View.VISIBLE);
	    	        nameBT.setText(getLocalBluetoothName());
	    	        //pair();
	    	        

	        	}
	        	else {
	    			mainLayout.setVisibility(View.GONE);
	    			bluetoothAdapter.disable();
	        		//textView2.setText(R.string.bluetooth_search_off);
	        		progressBarScan.setVisibility(View.INVISIBLE);
	    	        nameBT.setVisibility(View.GONE);
	    	        
	        	}
	        }
	        	
	        else if (id == R.id.Button04) {
	        	Intent settingsIntent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
	        	startActivity(settingsIntent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        
	        
	  }

	  
	  public String getLocalBluetoothName(){
		    if(bluetoothAdapter == null){
		    	bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		    }
		    String name = bluetoothAdapter.getName();
		    if(name == null){
		        System.out.println("Name is null!");
		        name = bluetoothAdapter.getAddress();
		    }
		    return getString(R.string.bt_name) + " " + "\"" + name + "\"";
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
	            Intent it = new Intent(ActivityBth.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	  
	        public void BackClick(View v)  
	        {  
	        	Intent intent18 = new Intent(this, MainActivity.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	       	 }
	  
	        
	    	
	    	@Override
	    	public void onPause() {
	    		if (bluetoothAdapter != null) {
	    			if (bluetoothAdapter.isDiscovering()) {
	    				bluetoothAdapter.cancelDiscovery();
	    			}
	    		}
	    		
	    		super.onPause();
	    	}
	    	
}
	  
