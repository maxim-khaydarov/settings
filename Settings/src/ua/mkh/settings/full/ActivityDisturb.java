package ua.mkh.settings.full;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivityDisturb extends Activity implements OnClickListener, SimpleGestureListener,  RadioGroup.OnCheckedChangeListener{

	ToggleButton tb_on, tb_shedule, tb_repeat;
	TextView text_from, text_to, textView1, textView4, textStatus, TextView01, TextView02, TextView03, TextView04;
	LinearLayout LinearLayoutShedule;
	Button btn_back, Button01, Button02, Button03, Button04, Button05;
	AudioManager audio_mngr;
	public final static String to_time_text = "to_time.txt";
	public final static String from_time_text = "from_time.txt";
	int shedule = 0;
	final static int RQS_1 = 1;
	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	int OS = android.os.Build.VERSION.SDK_INT;
	Boolean enable_disturb;
	
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   public static final String APP_PREFERENCES_ALLOW_CALL = "allow_call";
	   public static final String APP_PREFERENCES_REPEAT_CALL = "repeat_call";
	   public static final String APP_PREFERENCES_SILENCE_CALL = "silence_call";
	   public static final String APP_PREFERENCES_DISTURB_ENABLE = "disturb_enable";
	   
	   int menui = 0;
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   private SimpleGestureFilter detector;
	
	SharedPreferences mSettings;
	
	private RadioButton always, locked;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disturb);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String thin = "fonts/Thin.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		Typeface typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);
		
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radiogroup.setOnCheckedChangeListener(this);
     // radio button setting
        always=(RadioButton) findViewById(R.id.radio0);
        locked=(RadioButton) findViewById(R.id.radio1);
		
        tb_on = (ToggleButton)findViewById(R.id.ToggleButton01);
        tb_on.setOnClickListener(this);
        tb_shedule =  (ToggleButton)findViewById(R.id.ToggleButton02);
        tb_shedule.setOnClickListener(this);
        tb_repeat =  (ToggleButton)findViewById(R.id.ToggleButton03);
        tb_repeat.setOnClickListener(this);
        
        text_from = (TextView)findViewById(R.id.textView2);
        text_to = (TextView)findViewById(R.id.textView3);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView4 = (TextView) findViewById(R.id.textView4);
        TextView01 = (TextView) findViewById(R.id.TextView01);
        TextView02 = (TextView) findViewById(R.id.TextView02);
        TextView03 = (TextView) findViewById(R.id.TextView03);
        TextView04 = (TextView) findViewById(R.id.TextView04);

        textStatus = (TextView)findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        btn_back.setText(R.string.app_name);
        Button01 = (Button) findViewById(R.id.Button01);
        Button03 = (Button) findViewById(R.id.Button03);
        Button04 = (Button) findViewById(R.id.Button04);
        Button04.setOnClickListener(this);
        Button02 = (Button) findViewById(R.id.Button02);
        Button02.setOnClickListener(this);
        Button05 = (Button) findViewById(R.id.Button05);
        
        
        LinearLayoutShedule = (LinearLayout)findViewById(R.id.LinearLayoutShedule);

        textStatus.setText(R.string.disturb);
        textStatus.setTypeface(typefaceMedium);
        btn_back.setTypeface(typefaceMedium);
        Button01.setTypeface(typefaceRoman);
        Button03.setTypeface(typefaceRoman);
        Button02.setTypeface(typefaceRoman);
        Button04.setTypeface(typefaceRoman);
        Button05.setTypeface(typefaceRoman);
        textView1.setTypeface(typefaceRoman);
        textView4.setTypeface(typefaceRoman);
        always.setTypeface(typefaceRoman);
        locked.setTypeface(typefaceRoman);
        TextView01.setTypeface(typefaceRoman);
        TextView02.setTypeface(typefaceRoman);
        TextView03.setTypeface(typefaceRoman);
        TextView04.setTypeface(typefaceRoman);
        
        
        text_from.setTypeface(typefaceRoman);
        text_to.setTypeface(typefaceRoman);
        
        audio_mngr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        
       
        
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
       ButtonSound();
       time_read();
       Shedule_list();
       
       
       
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
		        Button02.setTypeface(typefaceBold);
		        Button04.setTypeface(typefaceBold);
		        Button05.setTypeface(typefaceBold);
		        textView1.setTypeface(typefaceBold);
		        textView4.setTypeface(typefaceBold);
		        text_from.setTypeface(typefaceBold);
		        text_to.setTypeface(typefaceBold);
		        always.setTypeface(typefaceBold);
		        locked.setTypeface(typefaceBold);
		        TextView01.setTypeface(typefaceBold);
		        TextView02.setTypeface(typefaceBold);
		        TextView03.setTypeface(typefaceBold);
		        TextView04.setTypeface(typefaceBold);
				
			}
       }
			
      if (mSettings.contains(APP_PREFERENCES_text_size)) {
			// Получаем число из настроек
       	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
			if (size .contains( "Small")){
				Button01.setTextSize(14);
		        Button03.setTextSize(14);
		        Button04.setTextSize(14);
		        Button05.setTextSize(14);
		        Button02.setTextSize(14);
		        textView1.setTextSize(11);
		        TextView01.setTextSize(11);
		        TextView02.setTextSize(11);
		        TextView03.setTextSize(11);
		        TextView04.setTextSize(11);
		        textView4.setTextSize(14);
		        text_from.setTextSize(14);
		        text_to.setTextSize(14);
		        always.setTextSize(14);
		        locked.setTextSize(14);
			}
			if (size .contains( "Normal")){
				Button01.setTextSize(16);
		        Button03.setTextSize(16);
		        Button04.setTextSize(16);
		        Button05.setTextSize(16);
		        Button02.setTextSize(16);
		        textView1.setTextSize(13);
		        TextView01.setTextSize(13);
		        TextView02.setTextSize(13);
		        TextView03.setTextSize(13);
		        TextView04.setTextSize(13);
		        textView4.setTextSize(16);
		        text_from.setTextSize(16);
		        text_to.setTextSize(16);
		        always.setTextSize(16);
		        locked.setTextSize(16);
			}
			if (size .contains( "Large")){
				Button01.setTextSize(19);
		        Button03.setTextSize(19);
		        Button04.setTextSize(19);
		        Button05.setTextSize(19);
		        Button02.setTextSize(19);
		        TextView01.setTextSize(16);
		        TextView02.setTextSize(16);
		        TextView03.setTextSize(16);
		        TextView04.setTextSize(16);
		        textView1.setTextSize(16);
		        textView4.setTextSize(19);
		        text_from.setTextSize(19);
		        text_to.setTextSize(19);
		        always.setTextSize(19);
		        locked.setTextSize(19);
			}
			if (size .contains( "xLarge")){
				Button01.setTextSize(21);
		        Button03.setTextSize(21);
		        Button04.setTextSize(21);
		        Button05.setTextSize(21);
		        Button02.setTextSize(21);
		        textView1.setTextSize(18);
		        TextView01.setTextSize(18);
		        TextView02.setTextSize(18);
		        TextView03.setTextSize(18);
		        TextView04.setTextSize(18);
		        textView4.setTextSize(21);
		        text_from.setTextSize(21);
		        text_to.setTextSize(21);
		        always.setTextSize(21);
		        locked.setTextSize(21);
			}
			
      }
      
			String size = mSettings.getString(APP_PREFERENCES_SILENCE_CALL, "always");
			
			if(size.equals("always"))
    		{
    			always.setChecked(true);
    			TextView04.setText(R.string.silence_call_disturb_text_always);
    		}
			else if (size.contains("locked")){
				locked.setChecked(true);
				TextView04.setText(R.string.silence_call_disturb_text_lock);
			}
		
      
      if (mSettings.contains(APP_PREFERENCES_REPEAT_CALL)) {
			// Получаем число из настроек
     	 Boolean menu = mSettings.getBoolean(APP_PREFERENCES_REPEAT_CALL, false);
			if (menu == true){
				tb_repeat.setChecked(true);
			}
			else{
				tb_repeat.setChecked(false);
			}
     }
      
     // if (mSettings.contains(APP_PREFERENCES_ALLOW_CALL)) {
			// Получаем число из настроек
   	 String menu444 = mSettings.getString(APP_PREFERENCES_ALLOW_CALL, "no one");
			if (menu444.contains("everyone")){
				textView4.setText(R.string.allow_call_disturb_everyone);
			}
			else if (menu444.contains("no one")){
				textView4.setText(R.string.allow_call_disturb_noone);
			}
			else if (menu444.contains("contacts")){
				textView4.setText(R.string.allow_call_disturb_contacts);
			}
 //  }
      
	}
	
	private void ButtonSound() {
		enable_disturb = mSettings.getBoolean(APP_PREFERENCES_DISTURB_ENABLE, false);
    	if (enable_disturb ==  true) {
    		tb_on.setChecked(true);
    	}
    }
	
	
	private void Shedule_list () {

		if (text_from.getText().length() == 0) {
			LinearLayoutShedule.setVisibility(View.GONE);
			shedule = 0; 
			tb_shedule.setChecked(false);}
		else {
			LinearLayoutShedule.setVisibility(View.VISIBLE);
			shedule = 1;
			tb_shedule.setChecked(true);
		}
		
	}
	
	
	public void onClick(View v) {
        int id = v.getId();
        
		if (id == R.id.ToggleButton01) {
			if((tb_on).isChecked()) {
        		
        		start_notif ();}
        	else {
        		
        		stop_notif(); }
		} 
		else if (id == R.id.ToggleButton02) {
			if(tb_shedule.isChecked()) {
        		LinearLayoutShedule.setVisibility(View.VISIBLE);
        		shedule = 1; }
        	else {
        		LinearLayoutShedule.setVisibility(View.GONE);
        		shedule = 0; 
        		time_0 ();
        		stop_from();
        		stop_to(); }
		}
		else if (id == R.id.Button04) {
			Intent intent1 = new Intent(this, ActivityFromTime.class);
			startActivity(intent1);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
		else if (id == R.id.ToggleButton03) {
			if((tb_repeat).isChecked()) {
				Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_REPEAT_CALL, true);
			   	editor.commit();}
        	else {
        		Editor editor = mSettings.edit();
			   	editor.putBoolean(APP_PREFERENCES_REPEAT_CALL, false);
			   	editor.commit(); }
		} 
		else if (id == R.id.Button02){
			Intent intent1 = new Intent(this, ActivityAllowDisturb.class);
			startActivity(intent1);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
		}
	}
	
	private void time_read () {
		try {
			
			InputStream inputstream = openFileInput(to_time_text);
			InputStreamReader isr = new InputStreamReader(inputstream);
			BufferedReader reader = new BufferedReader(isr);
			String str;
			StringBuffer buffer = new StringBuffer();

			while ((str = reader.readLine()) != null) {
				buffer.append(str + "");
			}

			inputstream.close();
			text_to.setText(buffer.toString());
			
			} catch (Throwable t) {
				text_to.setText("");
			}
		//////
try {
			
			InputStream inputstream = openFileInput(from_time_text);
			InputStreamReader isr = new InputStreamReader(inputstream);
			BufferedReader reader = new BufferedReader(isr);
			String str;
			StringBuffer buffer = new StringBuffer();

			while ((str = reader.readLine()) != null) {
				buffer.append(str + "");
			}

			inputstream.close();
			text_from.setText(buffer.toString());
			
			} catch (Throwable t) {
				text_from.setText("");
			}

	}
	
	private void time_0 () {
		try {
 			OutputStream outputstream = openFileOutput(from_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write("");
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		try {
 			OutputStream outputstream = openFileOutput(to_time_text, 0);
 			OutputStreamWriter osw = new OutputStreamWriter(outputstream);
 			osw.write("");
 			osw.close();
 		} catch (Throwable t) {
 			
 		}
		
	}
	
	
	public void  stop_from() {
		timeSet t = new timeSet (getBaseContext()); 
		t.setStop_from_manual ();
	}
	
	public void  stop_to() {
		timeSet t = new timeSet (getBaseContext()); 
		t.setStop_to_manual ();
	}
	   
	public void start_notif (){
		timeSet t = new timeSet (getBaseContext()); 
		t.start_notif (getBaseContext());
	}
	public void stop_notif (){
		timeSet t = new timeSet (getBaseContext()); 
		t.stop_notif (getBaseContext());
	}
	
	public void BackClick(View v)  
    {  
		Intent intent18 = new Intent(this, MainActivity.class);
     	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
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
	            Intent it = new Intent(ActivityDisturb.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	        }
		
	        
	        
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
		        switch (checkedId) {
		        case R.id.radio0:
		        	TextView04.setText(R.string.silence_call_disturb_text_always);
		        	Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_SILENCE_CALL, "always");
				   	editor.commit();
		      
		          break;

		        case R.id.radio1:
		        	TextView04.setText(R.string.silence_call_disturb_text_lock);
		        	Editor editor2 = mSettings.edit();
				   	editor2.putString(APP_PREFERENCES_SILENCE_CALL, "locked");
				   	editor2.commit();
		         
		          break;

		       
		        }
		      }
	       
	        
}
