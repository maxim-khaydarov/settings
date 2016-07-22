package ua.mkh.settings.full;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivityZvuki extends Activity implements OnClickListener{
	private ToggleButton tb_d;
	
	TextView textView1, textView2, textView3, textView4, 
	textView5, textView6, textStatus, textView8, textView7, textView9, ringtone, notif, alarms;
	
	SeekBar alarm=null;
	SeekBar music=null;
	SeekBar ring=null;
	SeekBar system=null;
	SeekBar voice=null;
	AudioManager mgr=null;
	
	int status = 0;
	int silence = 0, call = 0;
	ImageView Up, Down;
	int mis = 0, vibro = 0;
	
	LinearLayout LinearLayoutZvuki1;
	
	 Animation animationRotateDown, animationRotateUp;
	
	ToggleButton tb_sound, tb_vibration, tb_s_vibro ;
	Button btn_back, Button02, Button08, Button01, Button10, Button07, Button11, Button12;
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
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zvuki);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String thin = "fonts/Thin.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		
		
		
		textStatus  = (TextView)findViewById(R.id.textOk);
		textView6 = (TextView)findViewById(R.id.textView6);
		textView7 = (TextView)findViewById(R.id.textView7);
		textView8 = (TextView)findViewById(R.id.textView8);
		textView2 = (TextView)findViewById(R.id.textView2);
		textView3 = (TextView)findViewById(R.id.textView3);
		textView4 = (TextView)findViewById(R.id.textView4);
		textView5 = (TextView)findViewById(R.id.textView5);
		textView9 = (TextView)findViewById(R.id.textView9);
		ringtone = (TextView) findViewById(R.id.textView10);
		notif = (TextView) findViewById(R.id.TextView01);
		alarms = (TextView) findViewById(R.id.TextView02);
		btn_back = (Button) findViewById(R.id.buttonBack);
		btn_back.setText(R.string.app_name);
		Button01= (Button) findViewById(R.id.Button01);
		Button01.setOnClickListener(this);
		Button02= (Button) findViewById(R.id.Button02);
		Button08= (Button) findViewById(R.id.Button08);
		Button10= (Button) findViewById(R.id.Button10);
		Button10.setOnClickListener(this);
		Button07= (Button) findViewById(R.id.Button07);
		Button07.setOnClickListener(this);
		Button11= (Button) findViewById(R.id.Button11);
		Button11.setOnClickListener(this);
		Button12= (Button) findViewById(R.id.Button12);
		Button12.setOnClickListener(this);
		

		textStatus.setText(R.string.button_sounds);
	    textStatus.setTypeface(typefaceBold);
	    btn_back.setTypeface(typefaceMedium);
	    textView6.setTypeface(typefaceRoman);
	    textView7.setTypeface(typefaceRoman);
	    textView8.setTypeface(typefaceRoman);
	    textView2.setTypeface(typefaceRoman);
	    textView3.setTypeface(typefaceRoman);
	    textView4.setTypeface(typefaceRoman);
	    textView5.setTypeface(typefaceRoman);
	    textView9.setTypeface(typefaceRoman);
	    Button02.setTypeface(typefaceRoman);
	    Button08.setTypeface(typefaceRoman);
	    Button10.setTypeface(typefaceRoman);
	    Button01.setTypeface(typefaceRoman);
	    Button07.setTypeface(typefaceRoman);
	    Button11.setTypeface(typefaceRoman);
	    Button12.setTypeface(typefaceRoman);
	    ringtone.setTypeface(typefaceRoman);
	    notif.setTypeface(typefaceRoman);
	    alarms.setTypeface(typefaceRoman);
	        
	    Up = (ImageView)findViewById(R.id.imageView3);
		Down = (ImageView)findViewById(R.id.imageView2);
		Up.setVisibility(View.GONE);
		
		animationRotateDown = AnimationUtils.loadAnimation(
				this, R.anim.rotate_down);
	    animationRotateUp = AnimationUtils.loadAnimation(
				this, R.anim.rotate_up);
		 
		
	        
	    LinearLayoutZvuki1 = (LinearLayout)findViewById(R.id.LinearLayoutZvuki1);
	    LinearLayoutZvuki1.setVisibility(View.GONE);
	        
	        
	        mgr=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
	        alarm=(SeekBar)findViewById(R.id.alarm);
	        music=(SeekBar)findViewById(R.id.music);
	        ring=(SeekBar)findViewById(R.id.ring);
	        system=(SeekBar)findViewById(R.id.system);
	        voice=(SeekBar)findViewById(R.id.voice);
	        
	        initBar(alarm, AudioManager.STREAM_ALARM);
	        initBar(music, AudioManager.STREAM_MUSIC);
	        initBar(ring, AudioManager.STREAM_RING);
	        initBar(system, AudioManager.STREAM_SYSTEM);
	        initBar(voice, AudioManager.STREAM_VOICE_CALL);
	        
	       // tb_sound = (ToggleButton) findViewById(R.id.soundtoggle);
	        tb_vibration = (ToggleButton) findViewById(R.id.vibrationtoggle);
	       // tb_sound.setOnClickListener(this);
	        tb_vibration.setOnClickListener(this);
	        tb_s_vibro = (ToggleButton) findViewById(R.id.ToggleButton01);
	        tb_s_vibro.setOnClickListener(this);
	}
	        
	        private void initBar(SeekBar bar, final int stream) {
	            bar.setMax(mgr.getStreamMaxVolume(stream));
	            bar.setProgress(mgr.getStreamVolume(stream));
	            
	            bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	              public void onProgressChanged(SeekBar bar, int progress,
	                                            boolean fromUser) {
	                mgr.setStreamVolume(stream, progress,
	                                    AudioManager.FLAG_PLAY_SOUND);
	                
	                AudioManager audio_mngr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
	                
	                if(progress == 0)
	                {
	                	
	                	if (vibro == 1) {
	            			audio_mngr.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	            		}
	            		else {
	            			audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	            		}
	                    mis = 0;
	                }
	                else {
	                	if (vibro == 1){
	        				audio_mngr .setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	            		  audio_mngr.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_ON);
	        		}
	                	else{
	        			audio_mngr .setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        		}
	                	mis = 1;
	                }
	              }
	              
	              public void onStartTrackingTouch(SeekBar bar) {
	                // no-op
	              }
	              
	              public void onStopTrackingTouch(SeekBar bar ) {
	            	  
	              }
	            });
	          
	}
	        
		    
		     private void ringer(){
		    	//ringtone
		    	 try{
			           Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(this.getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
			           Ringtone defaultRingtone = RingtoneManager.getRingtone(this, defaultRintoneUri);
			           String ringToneName=defaultRingtone.getTitle(this);
			          
			           ringtone.setText(ringToneName);
			           
			           }
			           catch (NullPointerException e){
			           }
		    	 //notification
		    	 try{
		    		 Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(this.getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION);
			           Ringtone defaultRingtone = RingtoneManager.getRingtone(this, defaultRintoneUri);
			           String ringToneName=defaultRingtone.getTitle(this);
			          
			           notif.setText(ringToneName);
		    	 }
		    	 catch (NullPointerException e){
		    	 }
		    	//alarm
		    	 try{
		    		 Uri defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(this.getApplicationContext(), RingtoneManager.TYPE_ALARM);
			           Ringtone defaultRingtone = RingtoneManager.getRingtone(this, defaultRintoneUri);
			           String ringToneName=defaultRingtone.getTitle(this);
			          
			           alarms.setText(ringToneName);
		    	 }
		    	 catch (NullPointerException e){
		    	 }
		     }
		     
		     
	        protected void onResume() {
	            super.onResume();
	           ButtonSound();
	           ButtonVibration();
	           
	           ringer();
	           
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
						ringtone.setTypeface(typefaceBold);
						notif.setTypeface(typefaceBold);
						alarms.setTypeface(typefaceBold);
						textView6.setTypeface(typefaceBold);
					    textView7.setTypeface(typefaceBold);
					    textView8.setTypeface(typefaceBold);
					    textView2.setTypeface(typefaceBold);
					    textView3.setTypeface(typefaceBold);
					    textView4.setTypeface(typefaceBold);
					    textView5.setTypeface(typefaceBold);
					    textView9.setTypeface(typefaceBold);
					    Button07.setTypeface(typefaceBold);
					    Button08.setTypeface(typefaceBold);
					    Button10.setTypeface(typefaceBold);
					    Button11.setTypeface(typefaceBold);
					    Button12.setTypeface(typefaceBold);
					    Button01.setTypeface(typefaceBold);
						
					}
		        }
					
		       if (mSettings.contains(APP_PREFERENCES_text_size)) {
					// Получаем число из настроек
		        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
					if (size .contains( "Small")){
						ringtone.setTextSize(13);
						notif.setTextSize(13);
						alarms.setTextSize(13);
						textView6.setTextSize(11);
					    textView7.setTextSize(11);
					    textView8.setTextSize(11);
					    textView2.setTextSize(11);
					    textView3.setTextSize(11);
					    textView4.setTextSize(11);
					    textView5.setTextSize(11);
					    textView9.setTextSize(11);
					    Button07.setTextSize(13);
					    Button08.setTextSize(13);
					    Button10.setTextSize(13);
					    Button11.setTextSize(13);
					    Button12.setTextSize(13);
					    Button01.setTextSize(13);
					}
					if (size.contains( "Normal")){
						ringtone.setTextSize(15);
						notif.setTextSize(15);
						alarms.setTextSize(15);
						textView6.setTextSize(13);
					    textView7.setTextSize(13);
					    textView8.setTextSize(13);
					    textView2.setTextSize(13);
					    textView3.setTextSize(13);
					    textView4.setTextSize(13);
					    textView5.setTextSize(13);
					    textView9.setTextSize(13);
					    Button07.setTextSize(15);
					    Button08.setTextSize(15);
					    Button10.setTextSize(15);
					    Button11.setTextSize(15);
					    Button12.setTextSize(15);
					    Button01.setTextSize(15);
					}
					if (size .contains( "Large")){
						ringtone.setTextSize(18);
						notif.setTextSize(18);
						alarms.setTextSize(18);
						textView6.setTextSize(15);
					    textView7.setTextSize(15);
					    textView8.setTextSize(15);
					    textView2.setTextSize(15);
					    textView3.setTextSize(15);
					    textView4.setTextSize(15);
					    textView5.setTextSize(15);
					    textView9.setTextSize(15);
					    Button07.setTextSize(18);
					    Button08.setTextSize(18);
					    Button10.setTextSize(18);
					    Button11.setTextSize(18);
					    Button12.setTextSize(18);
					    Button01.setTextSize(18);
					}
					if (size .contains( "xLarge")){
						ringtone.setTextSize(20);
						notif.setTextSize(20);
						alarms.setTextSize(20);
						textView6.setTextSize(18);
					    textView7.setTextSize(18);
					    textView8.setTextSize(18);
					    textView2.setTextSize(18);
					    textView3.setTextSize(18);
					    textView4.setTextSize(18);
					    textView5.setTextSize(18);
					    textView9.setTextSize(18);
					    Button07.setTextSize(20);
					    Button08.setTextSize(20);
					    Button10.setTextSize(20);
					    Button11.setTextSize(20);
					    Button12.setTextSize(20);
					    Button01.setTextSize(20);
					}
		       }
		       
	        }
	        
	        private void ButtonSound() {
	        	AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	        	int mod = am.getRingerMode ();
	        	if (mod ==  2) {
	        		mis = 1;
	        		
	        	}
	        }
	           //Vibration
	           private void ButtonVibration() {
	        	   AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	        	   int mod = am.getVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER);

	        	   if (mod == 1) {
	        		   vibro = 1;
	        		   tb_vibration.setChecked(true);
	        	   }
	        	   if(mod == 2) {
	        		   vibro = 1;
	        		   tb_vibration.setChecked(true);
	        	   }
	        	   
	           }
	           
	           

	           public void BackClick(View v)  
	           {  
	        	   Intent intent18 = new Intent(this, MainActivity.class);
	            	 startActivity(intent18);

	      		overridePendingTransition(center_to_right, center_to_right2);
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
		 /*
		 if (id == R.id.soundtoggle){
			 AudioManager audio_mngr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
	        	if((tb_sound).isChecked()) {
	        		if (vibro == 1){
	        				audio_mngr .setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	            		  audio_mngr.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_ON);
	        		}
	        		else{
	        			audio_mngr .setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        		}
	        		mis = 1;
            	}
            	else {
            		if (vibro == 1) {
            			audio_mngr.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            		}
            		else {
            			audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            		}
            		mis = 0;
            	}
		 }
    */
		 if (id == R.id.vibrationtoggle){
            			if ((tb_vibration).isChecked()) {
            				AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        	
                        	if (mis == 0) {
                        		
                      			am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                      			am.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_ON);
                      			am.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_ON);
                      			 }
                        		else {
                        			am .setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        			am.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_ON);
                          			am.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_ON);
                        			}
                        	vibro = 1;
            			}
                    	else {
                    		if (mis == 0){
                    			AudioManager audio_mngr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
                    			audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    			audio_mngr.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_OFF);
                    			audio_mngr.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_OFF);                    		}
                    		else {
                    		AudioManager audio_mngr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    	        			audio_mngr .setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    		audio_mngr.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_OFF);
                			audio_mngr.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_OFF);
                    		}
                    		vibro = 0;
                    	}
            }
		 
		 else if (id == R.id.ToggleButton01){
			 if ((tb_s_vibro).isChecked()) {
				 silence = 0;
			 }
			 else {
				 silence = 1;
			 }
		 }
            			
            else if (id == R.id.Button01){
	        	
	        	if (status == 0){
	        		Down.startAnimation(animationRotateUp);
	        		 HideLayoutDown();
	        	LinearLayoutZvuki1.setVisibility(View.VISIBLE);
	        	status = 1;
	        	}
	        	else {
	        		Up.startAnimation(animationRotateDown);
	        		HideLayoutUp();
		        	LinearLayoutZvuki1.setVisibility(View.GONE);
		        	status = 0;
	        	}
            	}   
	        	
            	
            	else if (id == R.id.Button07){
            		Intent settingsIntent = new Intent(this, ActivitySetRingtone.class);
    	        	startActivity(settingsIntent);
    		 	        	overridePendingTransition(center_to_left, center_to_left2);
            	}
		 
            	else if (id == R.id.Button11){
            		Intent settingsIntent = new Intent(this, ActivitySetNotifications.class);
    	        	startActivity(settingsIntent);
    		 	        	overridePendingTransition(center_to_left, center_to_left2);
            	}
		 
            	else if (id == R.id.Button12){
            		Intent settingsIntent = new Intent(this, ActivitySetAlarms.class);
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
	            Intent it = new Intent(ActivityZvuki.this, SettingsActivity.class);
	            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	            startActivity(it); 
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	   	
	 }


