package ua.mkh.settings.full;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ActivityFromTime extends Activity implements OnClickListener, SimpleGestureListener {

	TimePicker timePicker1, timePicker2;
	DatePicker pickerDate;
	Button  btn_back, Button01, Button02, btn_save;
	final static int RQS_1 = 1;
	final String LOG_TAG = "myLogs";
	TextView textView1, textStatus;
	String strdate_from = null;
	String strdate_to = null;
	LinearLayout LinearLayoutFrom, LinearLayoutTo;
	public final static String from_time_text = "from_time.txt";
	public final static String to_time_text = "to_time.txt";
	
	private boolean timeChanged = false;
	private boolean timeScrolled = false;
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   private SimpleGestureFilter detector;
	   
	   SharedPreferences mSettings;
	   Typeface typefaceRoman, typefaceMedium, typefaceBold;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_from_time);
		
		String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);
		
		textStatus = (TextView)findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_save = (Button) findViewById(R.id.buttonSave);
		pickerDate = (DatePicker)findViewById(R.id.pickerdate);
		
		timePicker1 = (TimePicker)findViewById(R.id.timePicker1);
		timePicker1.setIs24HourView(true);
		timePicker2 = (TimePicker)findViewById(R.id.timePicker2);
		timePicker2.setIs24HourView(true);
		
		LinearLayoutFrom = (LinearLayout)findViewById(R.id.LinearLayoutFrom);
		LinearLayoutTo = (LinearLayout)findViewById(R.id.LinearLayoutTo);
		Button01 = (Button)findViewById(R.id.Button01);
		Button01.setOnClickListener(this);
		Button02 = (Button)findViewById(R.id.Button02);
		Button02.setOnClickListener(this);
		
		Button02.setBackgroundColor(-0x1F1F20);
		LinearLayoutTo.setVisibility(View.GONE);
		
		textView1 = (TextView)findViewById(R.id.textView1);
		Calendar now = Calendar.getInstance();

		  
		timePicker1.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
		timePicker1.setCurrentMinute(now.get(Calendar.MINUTE));
		timePicker2.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
		timePicker2.setCurrentMinute(now.get(Calendar.MINUTE));
		
		textStatus.setText(R.string.disturb);
        textStatus.setTypeface(typefaceMedium);
        btn_back.setTypeface(typefaceMedium);
        btn_save.setTypeface(typefaceRoman);
        Button01.setTypeface(typefaceRoman);
        Button02.setTypeface(typefaceRoman);
        
        ////////////////////
        final WheelView hours1 = (WheelView) findViewById(R.id.hour1);
		hours1.setViewAdapter(new NumericWheelAdapter(this, 0, 23));
		hours1.setCyclic(true);
		
		final WheelView mins1 = (WheelView) findViewById(R.id.mins1);
		mins1.setViewAdapter(new NumericWheelAdapter(this, 0, 59, "%02d"));
		mins1.setCyclic(true);
	
		
	
		// set current time
		Calendar c1 = Calendar.getInstance();
		int curHours1 = c1.get(Calendar.HOUR_OF_DAY);
		int curMinutes1 = c1.get(Calendar.MINUTE);
	
		hours1.setCurrentItem(curHours1);
		mins1.setCurrentItem(curMinutes1);
	
		timePicker1.setCurrentHour(curHours1);
		timePicker1.setCurrentMinute(curMinutes1);
	
		// add listeners
		addChangingListener1(mins1, "min");
		addChangingListener1(hours1, "hour");
	
		OnWheelChangedListener wheelListener = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!timeScrolled) {
					timeChanged = true;
					timePicker1.setCurrentHour(hours1.getCurrentItem());
					timePicker1.setCurrentMinute(mins1.getCurrentItem());
					timeChanged = false;
				}
			}
		};
		hours1.addChangingListener(wheelListener);
		mins1.addChangingListener(wheelListener);
		
		OnWheelClickedListener click1 = new OnWheelClickedListener() {
            public void onItemClicked(WheelView wheel, int itemIndex) {
                wheel.setCurrentItem(itemIndex, true);
            }
        };
        hours1.addClickingListener(click1);
        mins1.addClickingListener(click1);

		OnWheelScrollListener scrollListener1 = new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
				timeScrolled = true;
			}
			public void onScrollingFinished(WheelView wheel) {
				timeScrolled = false;
				timeChanged = true;
				timePicker1.setCurrentHour(hours1.getCurrentItem());
				timePicker1.setCurrentMinute(mins1.getCurrentItem());
				timeChanged = false;
			}
		};
		
		hours1.addScrollingListener(scrollListener1);
		mins1.addScrollingListener(scrollListener1);
		
		timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			public void onTimeChanged(TimePicker  view, int hourOfDay, int minute) {
				if (!timeChanged) {
					hours1.setCurrentItem(hourOfDay, true);
					mins1.setCurrentItem(minute, true);
				}
			}
		});
		//////////////////////////
		
////////////////////
final WheelView hours2 = (WheelView) findViewById(R.id.hour2);
hours2.setViewAdapter(new NumericWheelAdapter(this, 0, 23));
hours2.setCyclic(true);

final WheelView mins2 = (WheelView) findViewById(R.id.mins2);
mins2.setViewAdapter(new NumericWheelAdapter(this, 0, 59, "%02d"));
mins2.setCyclic(true);



// set current time
Calendar c2 = Calendar.getInstance();
int curHours2 = c2.get(Calendar.HOUR_OF_DAY);
int curMinutes2 = c2.get(Calendar.MINUTE);

hours2.setCurrentItem(curHours2);
mins2.setCurrentItem(curMinutes2);

timePicker2.setCurrentHour(curHours2);
timePicker2.setCurrentMinute(curMinutes2);

// add listeners
addChangingListener2(mins2, "min");
addChangingListener2(hours2, "hour");

OnWheelChangedListener wheelListener2 = new OnWheelChangedListener() {
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (!timeScrolled) {
			timeChanged = true;
			timePicker2.setCurrentHour(hours2.getCurrentItem());
			timePicker2.setCurrentMinute(mins2.getCurrentItem());
			timeChanged = false;
		}
	}
};
hours2.addChangingListener(wheelListener2);
mins2.addChangingListener(wheelListener2);

OnWheelClickedListener click2 = new OnWheelClickedListener() {
  public void onItemClicked(WheelView wheel, int itemIndex) {
      wheel.setCurrentItem(itemIndex, true);
  }
};
hours2.addClickingListener(click2);
mins2.addClickingListener(click2);

OnWheelScrollListener scrollListener2 = new OnWheelScrollListener() {
	public void onScrollingStarted(WheelView wheel) {
		timeScrolled = true;
	}
	public void onScrollingFinished(WheelView wheel) {
		timeScrolled = false;
		timeChanged = true;
		timePicker2.setCurrentHour(hours2.getCurrentItem());
		timePicker2.setCurrentMinute(mins2.getCurrentItem());
		timeChanged = false;
	}
};

hours2.addScrollingListener(scrollListener2);
mins2.addScrollingListener(scrollListener2);

timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
	public void onTimeChanged(TimePicker  view, int hourOfDay, int minute) {
		if (!timeChanged) {
			hours2.setCurrentItem(hourOfDay, true);
			mins2.setCurrentItem(minute, true);
		}
	}
});
//////////////////////////
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		
		if (id == R.id.Button02){
			Button01.setBackgroundColor(-0x1);
			LinearLayoutTo.setVisibility(View.GONE);
			LinearLayoutFrom.setVisibility(View.VISIBLE);
			Button02.setBackgroundColor(-0x1F1F20);
		}
		if (id ==R.id.Button01){
			Button02.setBackgroundColor(-0x1);
			LinearLayoutFrom.setVisibility(View.GONE);
			LinearLayoutTo.setVisibility(View.VISIBLE);
			Button01.setBackgroundColor(-0x1F1F20);
		}
			
         }
        
		public void start_notif (){
			timeSet t = new timeSet (getBaseContext()); 
			t.start_notif (getBaseContext());		}
		
	public void  start_from(Calendar targetCalFrom) {
		timeSet t = new timeSet (getBaseContext()); 
		t.setAlarm_from (targetCalFrom);
	}
	public void  start_to(Calendar targetCalTo) {
		timeSet t = new timeSet (getBaseContext()); 
		t.setAlarm_to (targetCalTo);
	}
	
	
	public void from_time_text(View view) {
		Intent intent = new Intent(this, ActivityDisturb.class);
        // передаем введенный текст в качестве параметра
		
		try {
			OutputStream outputstream = openFileOutput(from_time_text, 0);
			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
			osw.write(strdate_from.toString());
			osw.close();
		} catch (Throwable t) {
			
		}
		startActivity(intent);
	}
	
	public void to_time_text(View view) {
		Intent intent = new Intent(this, ActivityDisturb.class);
        // передаем введенный текст в качестве параметра
		
		try {
			OutputStream outputstream = openFileOutput(to_time_text, 0);
			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
			osw.write(strdate_to.toString());
			osw.close();
		} catch (Throwable t) {
			
		}
		startActivity(intent);
		overridePendingTransition(center_to_right, center_to_right2);
	}
	
	private void addChangingListener1(final WheelView wheel1, final String label) {
		wheel1.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				//wheel.setLabel(newValue != 1 ? label + "s" : label);
			}
		});
	}
	private void addChangingListener2(final WheelView wheel2, final String label) {
		wheel2.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				//wheel.setLabel(newValue != 1 ? label + "s" : label);
			}
		});
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
    	  Intent intent18 = new Intent(this, ActivityDisturb.class);
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
        
       
			// ѕолучаем число из настроек
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
		// ѕолучаем число из настроек
    	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
		if (bold == true){
	        Button01.setTypeface(typefaceBold);
	        Button02.setTypeface(typefaceBold);
			
		}
    }
		
   if (mSettings.contains(APP_PREFERENCES_text_size)) {
		// ѕолучаем число из настроек
    	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
		if (size .contains( "Small")){
	        Button01.setTextSize(14);
	        Button02.setTextSize(14);
		}
		if (size .contains( "Normal")){
	        Button01.setTextSize(16);
	        Button02.setTextSize(16);
		}
		if (size .contains( "Large")){
	        Button01.setTextSize(19);
	        Button02.setTextSize(19);
		}
		if (size .contains( "xLarge")){
	        Button01.setTextSize(21);
	        Button02.setTextSize(21);
		}
   }
	}
   
	public void BackClick(View v)  
    {  
		Intent intent18 = new Intent(this, ActivityDisturb.class);
     	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
   	 }
	@Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
            	
                return true;
            case KeyEvent.KEYCODE_BACK:
            	Intent intent18 = new Intent(this, ActivityDisturb.class);
   	       	 startActivity(intent18);
        		overridePendingTransition(center_to_right, center_to_right2);
                return true;
            
        }
        return super.onKeyDown(keycode, e);
   }
    

   
	
	public void SaveClick(View v)  
    {  
		Calendar current1 = Calendar.getInstance();
		current1.set(Calendar.SECOND, 0);
		current1.set(Calendar.MILLISECOND, 0);
		
		
		
		Calendar calFrom = Calendar.getInstance();
		Calendar calFromText = Calendar.getInstance();
		
		Calendar calTo = Calendar.getInstance();
		Calendar calToText = Calendar.getInstance();
				
		calTo.set( 
        		pickerDate.getYear(), 
        	      pickerDate.getMonth(), 
        	      pickerDate.getDayOfMonth(), 
        	      timePicker1.getCurrentHour(), 
        	      timePicker1.getCurrentMinute(), 00
          );
		calToText.set(pickerDate.getYear(), 
      	      pickerDate.getMonth(), 
      	      pickerDate.getDayOfMonth(), 
      	    timePicker1.getCurrentHour(), 
      	  timePicker1.getCurrentMinute());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		strdate_from = sdf1.format(calToText.getTime());
		
		
		
		
		calFrom.set( 
        		pickerDate.getYear(), 
        	      pickerDate.getMonth(), 
        	      pickerDate.getDayOfMonth(), 
        	      timePicker2.getCurrentHour(), 
        	      timePicker2.getCurrentMinute(), 00
          );
		calFromText.set(pickerDate.getYear(), 
      	      pickerDate.getMonth(), 
      	      pickerDate.getDayOfMonth(), 
      	    timePicker2.getCurrentHour(), 
      	  timePicker2.getCurrentMinute());
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		strdate_to = sdf2.format(calFromText.getTime());
		
		if (calFrom.compareTo(current1) < 0){
			if (calTo.compareTo(current1) > 0){
				start_notif ();
			}
		}
	////	/////////
		Calendar calFrom_s = Calendar.getInstance();
		Calendar calTo_s = Calendar.getInstance();
		
		calFrom_s.set(timePicker2.getCurrentHour(), 
      	      timePicker2.getCurrentMinute());
		
		calTo_s.set(
				timePicker2.getCurrentHour(), 
	      	      timePicker2.getCurrentMinute());
		
		if (calFrom.compareTo(current1) < 0){
			if (calFrom.compareTo(calTo) > 0 ){
				start_notif ();
			}
		}
	////	//////////
		if (calFrom.compareTo(current1) < 0){
			if (calTo.compareTo(current1) < 0){
				calFrom.add(Calendar.DATE, 1);
				calTo.add(Calendar.DATE, 1);
			}
		}
		
		start_from(calFrom);
		start_to(calTo);
		
		try {
 			OutputStream outputstream = openFileOutput(from_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write(strdate_from);
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		
		try {
 			OutputStream outputstream = openFileOutput(to_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write(strdate_to);
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		Intent intent = new Intent(this, ActivityDisturb.class);
	 	 startActivity(intent);
	 	overridePendingTransition(center_to_right, center_to_right2);
     	 }
	
	
}