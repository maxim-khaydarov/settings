package ua.mkh.settings.full;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

public class ActivitySetTime extends Activity implements OnClickListener, SimpleGestureListener {
	
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui= 0;
	   
	   private boolean timeScrolled = false;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   Button btn_back, date, Button01, Button02, Button03, ButtonSave;
	   TextView  textStatus, textView1;
	   ToggleButton tg24, tgAuto;
	   RelativeLayout timerPic;
	   
	   SharedPreferences mSettings;
	   private SimpleGestureFilter detector;
	   
	   int timer = 0;
	   
	   WheelView day, year, month, hour, min;
	   
	   @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_set_time);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			date = (Button) findViewById(R.id.Button04);
			date.setText(new SimpleDateFormat("d MMMM yyyy").format(new Date()) + "                  " + new SimpleDateFormat("HH:mm").format(new Date()));
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			 detector = new SimpleGestureFilter(this,this);
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
			 textStatus  = (TextView)findViewById(R.id.textOk);
			 textView1 = (TextView) findViewById(R.id.textView1);
			 btn_back.setText(R.string.button_general);
			 Button01 = (Button) findViewById(R.id.Button01);
			 Button02 = (Button) findViewById(R.id.Button02);
			 Button03 = (Button) findViewById(R.id.Button03);
			 ButtonSave = (Button) findViewById(R.id.buttonSave);
			 ButtonSave.setVisibility(View.INVISIBLE);
			 
			 timerPic = (RelativeLayout) findViewById(R.id.RelativeLayout2);
			 date.setOnClickListener(this);
			 tg24 = (ToggleButton) findViewById(R.id.rotatetoggle);
			 tg24.setOnClickListener(this);
			 tg24.setClickable(false);
			 tgAuto = (ToggleButton) findViewById(R.id.ToggleButton01);
			 tgAuto.setClickable(false);
			 
			 
				
				textStatus.setText(R.string.date_time);
				textView1.setTypeface(typefaceRoman);
			    textStatus.setTypeface(typefaceBold);
			    btn_back.setTypeface(typefaceMedium);
			    Button01.setTypeface(typefaceRoman);
			    Button02.setTypeface(typefaceRoman);
			    Button03.setTypeface(typefaceRoman);
			    date.setTypeface(typefaceRoman);
			    ButtonSave.setTypeface(typefaceMedium);
			    
			    
			    
			 Calendar calendar = Calendar.getInstance();
			 day = (WheelView) findViewById(R.id.day);
			 day.setCyclic(true);
			 month = (WheelView) findViewById(R.id.month);
			 month.setCyclic(true);
			 year = (WheelView) findViewById(R.id.year);
			 year.setCyclic(true);
			 
			 
			 hour = (WheelView) findViewById(R.id.hour);
			 hour.setCyclic(true);
			 min = (WheelView) findViewById(R.id.min);
			 min.setCyclic(true);
			 
			 
			 
			 OnWheelChangedListener listener = new OnWheelChangedListener() {
					@Override
					public void onChanged(WheelView wheel, int oldValue, int newValue) {
						updateDays(year, month, day);
					}
				};
			 
				OnWheelChangedListener wheelListener = new OnWheelChangedListener() {
					public void onChanged(WheelView wheel, int oldValue, int newValue) {
						if (!timeScrolled) {
							
						}
					}
				};
				
				OnWheelScrollListener scrollListener1 = new OnWheelScrollListener() {
					public void onScrollingStarted(WheelView wheel) {
						timeScrolled = true;
						ButtonSave.setVisibility(View.VISIBLE);
					}
					public void onScrollingFinished(WheelView wheel) {
						//timeScrolled = false;
						String h = "";
						if (hour.getCurrentItem() < 10){
							h = "0" + String.valueOf(hour.getCurrentItem());
						}
						else{
							h = String.valueOf(hour.getCurrentItem());
						}
						
						
						String m = "";
						if (min.getCurrentItem() < 10){
							m = "0" + String.valueOf(min.getCurrentItem());
						}
						else{
							m = String.valueOf(min.getCurrentItem());
						}
						
						
						String d = "";
						if ((day.getCurrentItem() + 1) < 10){
							d = "0" + String.valueOf(day.getCurrentItem() + 1);
						}
						else{
						d = String.valueOf(day.getCurrentItem() + 1);
						} 
						String y = String.valueOf(year.getCurrentItem() - 30 + 2000);
						
						String mo0 ="";
						if((month.getCurrentItem()+1) < 10){
							mo0 = "0" + String.valueOf(month.getCurrentItem()+1);
						}
						else{
							mo0 = String.valueOf(month.getCurrentItem()+1);
						}
						
						
						String mo = new DateFormatSymbols().getMonths()[month.getCurrentItem()];
						
						date.setText(d + " " + mo + " " + y + "                  " + h + ":" + m);
						Log.d("SEND TIME", d+" "+mo0+" "+y+"-"+h+":"+m);
						//changeSystemTime(y, mo0, d, h, m, "30");
						//changeSystemTime("2015","04","06","13","09","30");
						
					}
				};
				
			 
			 //////////////DAY
				updateDays(year, month, day);
				day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
				
				
			///////////////YEAR
				int curYear = calendar.get(Calendar.YEAR);
				year.setViewAdapter(new DateNumericAdapter(this, 1970, curYear + 10, 45));
				year.setCurrentItem(curYear-10);
				year.addChangingListener(listener);
			 
			 
			 //////////////MONTH
			 int curMonth = calendar.get(Calendar.MONTH);
			// String monthsEN[] = new String[] {"January", "February", "March", "April", "May",
			//			"June", "July", "August", "September", "October", "November", "December"};
			 
			 String[] months = getResources().getStringArray(R.array.month);
				month.setViewAdapter(new DateArrayAdapter(this, months, curMonth));
				month.setCurrentItem(curMonth);
				month.addChangingListener(listener);
				
			 ////////////////MIN
				
				String min_ar[] = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08",
						"09", "10",	"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
						"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
						"33", "34", "35", "36", "37", "38", "39", "40",	"41", "42", "43", "44",
						"45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56",
						"57", "58", "59"};
				min.setViewAdapter(new DateArrayAdapter(this, min_ar, 0));
				min.setCurrentItem(calendar.get(Calendar.MINUTE));
				
				
			///////////////HOUR
				String hour_ar[] = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08",
						"09", "10",	"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
						"21", "22", "23"};
				hour.setViewAdapter(new DateArrayAdapter(this, hour_ar, 0));
				hour.setCurrentItem(calendar.get(Calendar.HOUR_OF_DAY));
			 
			 
			 
				
				
		        
				 
				hour.addScrollingListener(scrollListener1);
				min.addScrollingListener(scrollListener1);
				day.addScrollingListener(scrollListener1);
				year.addScrollingListener(scrollListener1);
				month.addScrollingListener(scrollListener1);
			 
			 
			 
	    }
	   
	   void updateDays(WheelView year, WheelView month, WheelView day) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
			calendar.set(Calendar.MONTH, month.getCurrentItem());

			int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			day.setViewAdapter(new DateNumericAdapter(this, 1, maxDays, calendar.get(Calendar.DAY_OF_MONTH) - 1));
			int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
			day.setCurrentItem(curDay - 1, true);
		}
		/**
		 * Adapter for numeric wheels. Highlights the current value.
		 */
		private class DateNumericAdapter extends NumericWheelAdapter {
			// Index of current item
			int currentItem;
			// Index of item to be highlighted
			int currentValue;

			/**
			 * Constructor
			 */
			public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
				super(context, minValue, maxValue);
				this.currentValue = current;
				setTextSize(20);
			}

			@Override
			protected void configureTextView(TextView view) {
				super.configureTextView(view);
				if (currentItem == currentValue) {
					//view.setTextColor(0xFF0000F0);
				}
				view.setTypeface(Typeface.SANS_SERIF);
			}

			@Override
			public View getItem(int index, View cachedView, ViewGroup parent) {
				currentItem = index;
				return super.getItem(index, cachedView, parent);
			}
		}

		/**
		 * Adapter for string based wheel. Highlights the current value.
		 */
		private class DateArrayAdapter extends ArrayWheelAdapter<String> {
			// Index of current item
			int currentItem;
			// Index of item to be highlighted
			int currentValue;

			/**
			 * Constructor
			 */
			public DateArrayAdapter(Context context, String[] items, int current) {
				super(context, items);
				this.currentValue = current;
				setTextSize(20);
			}

			@Override
			protected void configureTextView(TextView view) {
				super.configureTextView(view);
				if (currentItem == currentValue) {
					//view.setTextColor(0xFF0000F0);
				}
				view.setTypeface(Typeface.SANS_SERIF);
			}

			@Override
			public View getItem(int index, View cachedView, ViewGroup parent) {
				currentItem = index;
				return super.getItem(index, cachedView, parent);
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
	        
	        checkAUTOtime();
	        check24time();
	        //checkRoot();
	        
	        
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
				    Button02.setTypeface(typefaceBold);
				    Button03.setTypeface(typefaceBold);
				    date.setTypeface(typefaceBold);
				    textView1.setTypeface(typefaceBold);
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					Button01.setTextSize(13);
					Button02.setTextSize(13);
					Button03.setTextSize(13);
					date.setTextSize(13);
					textView1.setTextSize(13);
				}
				if (size .contains( "Normal")){
					Button01.setTextSize(15);
					Button02.setTextSize(15);
					Button03.setTextSize(15);
					date.setTextSize(15);
					textView1.setTextSize(15);
				}
				if (size .contains( "Large")){
					Button01.setTextSize(18);
					Button02.setTextSize(18);
					Button03.setTextSize(18);
					date.setTextSize(18);
					textView1.setTextSize(18);
				}
				if (size .contains( "xLarge")){
					Button01.setTextSize(20);
					Button02.setTextSize(20);
					Button03.setTextSize(20);
					date.setTextSize(20);
					textView1.setTextSize(20);
					
				}
	       }
	        
	    }
	    
	    private void changeSystemTime(String year,String month,String day,String hour,String minute,String second){
	    	Process loProcess;
			try {
				loProcess = Runtime.getRuntime().exec("su");
				DataOutputStream loDataOutputStream = new       DataOutputStream(loProcess.getOutputStream());
		    	loDataOutputStream.writeBytes("date -s " + year + month + day + "." + hour + minute + second + "; \n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	/*try {
		    	
		    	
		    	//loDataOutputStream.writeBytes("date -s" + theStringReturnedValue+ "; \n");
		    	
		        Process process = Runtime.getRuntime().exec("su");
		        DataOutputStream os = new DataOutputStream(process.getOutputStream());
		        String command = "date -s "+year+month+day+"."+hour+minute+second;
		        Log.e("command",command);
		        os.writeBytes(command);
		        os.flush();
		        os.writeBytes("exit\n");
		        os.flush();
		        process.waitFor();
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }*/
		}
	    
	   
	    
	    
	    public void checkAUTOtime(){
	    	String timeSettings = android.provider.Settings.System.getString(
	                this.getContentResolver(),
	                android.provider.Settings.System.AUTO_TIME);
			
			if (timeSettings.contentEquals("0")){
				tgAuto.setChecked(false);
				
			}
			else{
				tgAuto.setChecked(true);
			}
	    }
	    
	    public void check24time(){
	    	if (android.text.format.DateFormat.is24HourFormat(this)){
	        	tg24.setChecked(true);
	        }
	    	TimeZone tz = TimeZone.getDefault();
	    	textView1.setText(tz.getID());
	    	
	    }
	    /*
	    public void checkRoot(){
	    	if (isRooted() == true ){
	        	//changeSystemTime("2015","04","06","13","09","30");
	    		
	        	}
	        	else{
	        		//date.setClickable(false);
	        	}
	    }*/
	    public static boolean findBinary(String binaryName) {
	        boolean found = false;
	        if (!found) {
	            String[] places = { "/sbin/", "/system/bin/", "/system/xbin/",
	                    "/data/local/xbin/", "/data/local/bin/",
	                    "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/" };
	            for (String where : places) {
	                if (new File(where + binaryName).exists()) {
	                    found = true;

	                    break;
	                }
	            }
	        }
	        return found;
	    }
	 
	 private static boolean isRooted() {
	        return findBinary("su");
	    }
	    
	    
	    public void BackClick(View v)  
	    {  
	   	 Intent intent18 = new Intent(this, ActivityOsnova.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	   	 }
	    
	    public void SaveClick(View v)  
	    {  
	    	if(timeScrolled){
	        	String h = "";
				if (hour.getCurrentItem() < 10){
					h = "0" + String.valueOf(hour.getCurrentItem());
				}
				else{
					h = String.valueOf(hour.getCurrentItem());
				}
	        	String m = "";
				if (min.getCurrentItem() < 10){
					m = "0" + String.valueOf(min.getCurrentItem());
				}
				else{
					m = String.valueOf(min.getCurrentItem());
				}
				
				String d = "";
				if (day.getCurrentItem() + 1 < 10){
					d = "0" + String.valueOf(day.getCurrentItem() + 1);
				}
				else{
				d = String.valueOf(day.getCurrentItem() + 1);
				} 
				
				String y = String.valueOf(year.getCurrentItem() - 30 + 2000);
				String mo = "";
				if(month.getCurrentItem() + 1 < 10){
					mo = "0" + String.valueOf(month.getCurrentItem() + 1);
				}
				else{
				mo = String.valueOf(month.getCurrentItem() + 1);
				}
				
	        	changeSystemTime(y, mo, d, h, m, "00");
				//changeSystemTime("2015","04","06","13","09","30");
	        	timeScrolled = false;
	        	
	        	Log.e("TIME SEND", h + ":" + m + "  " + d + "-" + mo + "-" + y);
	    	}
	   	 Intent intent18 = new Intent(this, ActivityOsnova.class);
	         	 startActivity(intent18);

	   		overridePendingTransition(center_to_right, center_to_right2);
	   	 }
	    
	    public void onClick(View v) {
			 switch (v.getId()){
			 
			 case R.id.Button04:
				 if (isRooted() == true ){
				 if(timer == 0){
					 timerPic.setVisibility(View.VISIBLE);
					 timer = 1;
				 }
				 else{
					 timerPic.setVisibility(View.GONE);
					 timer = 0;
				 }
			 }
				 else{
					 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_DATE_SETTINGS);
			        	startActivity(settingsIntent);
			 	        	overridePendingTransition(center_to_left, center_to_left2); 
				 }
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
	            Intent it = new Intent(ActivitySetTime.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }

}
