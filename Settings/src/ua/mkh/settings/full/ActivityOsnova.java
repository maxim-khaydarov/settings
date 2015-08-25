package ua.mkh.settings.full;



import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class ActivityOsnova extends Activity implements OnClickListener, SimpleGestureListener{

	Button btn_back, btn_info, Button02, Button01, Button03, Button04, Button06, Button05, Button07, Button08,
	btn_setup_app, Button09, Button10, Button11, Button12, Button13;
	TextView textStatus, textView1, textView2, textView3, textView4, textView5, textView6,
	textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14,
	TextView02, TextView01, TextView03, TextView04, timeOutsT;
	ToggleButton tb_rotate;
	//LinearLayout layout12;
	
	
	
	 Intent notif_inoty, notif_espier, control_hi, control_espier, mail_ino, mail_stok, 
		notes_any, notes_espier, phone_kuandroid, phone_espier, phone_phone_stok, phone_stok,
		messages_easyandroid, messages_iphonestyle,	messages_espier, messages_stok, 
		safari_ios, safari_stok, music_easyandroid1, music_easyandroid2, music_hi, music_stok, compass_hanimobile, weather_cybob,
		weather_yahoo, weather_kuandroid, games_mkh, new1,new2, new3, new4, vk_stok, maps_stok, viber_stok, appstore_stok,
		ok_stok, skype_stok, whatsapp_stok, twitter_stok, facebook_stok, instagram_stok, siri, spotlight, bat1, bat2;
	 
	 String music_app, mail_app, notes_app, phone_app, messages_app, safari_app, 
	   gamecenter_app, weather_app, compass_app, notifications_app, control_app, 
	   new1_app, new2_app, new3_app, new4_app, vk_app, maps_app, viber_app, ok_app, skype_app, whatsapp_app, twitter_app, facebook_app, instagram_app,
	   appstore_app;
	 
	ImageView Up, Down;
	int status = 0;
	int OS = android.os.Build.VERSION.SDK_INT;
	
	 private SimpleGestureFilter detector;
	
	 Animation animationRotateDown, animationRotateUp;
		
	 Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	
	 public static final String APP_PREFERENCES = "mysettings";
	 public static final String APP_PREFERENCES_tgb_apn = "tgb_apn";
	 public static final String APP_PREFERENCES_tgb_passcode = "tgb_passcode";
	 public static final String APP_PREFERENCES_tgb_privacy = "tgb_privacy";
	 public static final String APP_PREFERENCES_MAIL = "mail_app";
	   public static final String APP_PREFERENCES_NOTES = "notes_app";
	   public static final String APP_PREFERENCES_PHONE = "phone_app";
	   public static final String APP_PREFERENCES_MESSAGES = "messages_app";
	   public static final String APP_PREFERENCES_SAFARI = "safari_app";
	   public static final String APP_PREFERENCES_MUSIC = "music_app";
	   public static final String APP_PREFERENCES_GAMECENTER = "gamecenter_app";
	   public static final String APP_PREFERENCES_WEATHER = "weather_app";
	   public static final String APP_PREFERENCES_COMPASS = "compass_app";
	   public static final String APP_PREFERENCES_MAPS = "maps_app";
	   public static final String APP_PREFERENCES_VK = "vk_app";
	   public static final String APP_PREFERENCES_VIBER = "viber_app";
	   public static final String APP_PREFERENCES_OK = "ok_app";
	   public static final String APP_PREFERENCES_SKYPE = "skype_app";
	   public static final String APP_PREFERENCES_WHATSAPP = "whatsapp_app";
	   public static final String APP_PREFERENCES_TWITTER = "twitter_app";
	   public static final String APP_PREFERENCES_FACEBOOK = "facebook_app";
	   public static final String APP_PREFERENCES_INSTAGRAM = "instagram_app";
	   
	   public static final String APP_PREFERENCES_NOTIFICATIONS = "notifications_app";
	   public static final String APP_PREFERENCES_CONTROL = "control_app";
	   public static final String APP_PREFERENCES_NEW1 = "new1_app";
	   public static final String APP_PREFERENCES_NEW2 = "new2_app";
	   public static final String APP_PREFERENCES_NEW3 = "new3_app";
	   public static final String APP_PREFERENCES_NEW4 = "new4_app";
	   public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ICLOUD = "icloud_app";
	   public static final String APP_PREFERENCES_ITUNES = "itunes_app";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
	   
	   PackageManager pm;

	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_osnova);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			String thin = "fonts/Thin.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
			imageView1.setVisibility(View.GONE);
			
			
			Intent i = getIntent();
			// Get the result of rank
			int VerIntent = i.getIntExtra("verintent", 0);
			
			if(VerIntent == 1){
				imageView1.setVisibility(View.VISIBLE);
			}
			
			
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			 btn_back = (Button) findViewById(R.id.buttonBack);
			 btn_back.setText(R.string.app_name);
			 
			 
			 
			// btn_info = (Button)findViewById(R.id.Button04);
			// btn_info.setOnClickListener(this);
			 detector = new SimpleGestureFilter(this,this);
			 
			 
			 tb_rotate = (ToggleButton)findViewById(R.id.rotatetoggle);
			 tb_rotate.setOnClickListener(this);
			 
			 
			 
			 //Button02 = (Button)findViewById(R.id.Button02);
			 
			 Button03 = (Button)findViewById(R.id.Button03);
			 Button03.setOnClickListener(this);
			 
			 Button01 = (Button)findViewById(R.id.Button01);
			 
			 Button02 = (Button)findViewById(R.id.Button02);
			 Button02.setOnClickListener(this);
			 
			 Button04 = (Button)findViewById(R.id.Button04);
			 Button04.setOnClickListener(this);
			 
			 Button06 = (Button)findViewById(R.id.Button06);
			 Button06.setOnClickListener(this);
			 
			 Button07 = (Button)findViewById(R.id.Button07);
			 Button07.setOnClickListener(this);
			 
			 Button08 = (Button)findViewById(R.id.Button08);
			 Button08.setOnClickListener(this);
			 
			 Button09 = (Button)findViewById(R.id.Button09);
			 Button09.setOnClickListener(this);
			 
			 Button05 = (Button)findViewById(R.id.Button05);
			 Button05.setOnClickListener(this);
			 
			 Button10 = (Button)findViewById(R.id.Button10);
			 Button10.setOnClickListener(this);
			 
			 Button11 = (Button)findViewById(R.id.Button11);
			 Button11.setOnClickListener(this);
			 
			 Button12 = (Button)findViewById(R.id.Button12);
			 Button12.setOnClickListener(this);
			 
			 Button13 = (Button)findViewById(R.id.Button13);
			 Button13.setOnClickListener(this);
			 
			
			 
			 btn_setup_app = (Button) findViewById(R.id.ButtonSetupApp);
			 btn_setup_app.setOnClickListener(this);
			 /*
			 Down = (ImageView)findViewById(R.id.imageView2);
			 Up = (ImageView)findViewById(R.id.imageView3);
			 Up.setVisibility(View.GONE);
			 */
			 textStatus = (TextView)findViewById(R.id.textOk);
			 textView1= (TextView)findViewById(R.id.textView1);
			// textView2= (TextView)findViewById(R.id.textView2);
			// textView3= (TextView)findViewById(R.id.textView3);
			 //textView4= (TextView)findViewById(R.id.textView4);
			// textView5= (TextView)findViewById(R.id.textView5);
			// textView6= (TextView)findViewById(R.id.textView6);
			// textView7= (TextView)findViewById(R.id.textView7);
			// textView8= (TextView)findViewById(R.id.textView8);
			 textView13= (TextView)findViewById(R.id.textView13);
			 //textView14= (TextView)findViewById(R.id.textView14);
			 TextView01= (TextView)findViewById(R.id.TextView01);
			 TextView02= (TextView)findViewById(R.id.TextView02);
			 TextView03= (TextView)findViewById(R.id.TextView03);
			 TextView04= (TextView)findViewById(R.id.TextView04);
			 timeOutsT = (TextView)findViewById(R.id.timeOutsT);
			 
			 
			 textStatus.setTypeface(typefaceMedium);
			 textStatus.setText(R.string.button_general);
		     btn_back.setTypeface(typefaceMedium);
		     //btn_info.setTypeface(typefaceRoman);
		     //Button02.setTypeface(typefaceRoman);
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
		     btn_setup_app.setTypeface(typefaceRoman);
		     textView1.setTypeface(typefaceRoman);
		   //  textView2.setTypeface(typefaceThin);
		   //  textView3.setTypeface(typefaceThin);
		   //  textView4.setTypeface(typefaceThin);
		   //  textView5.setTypeface(typefaceThin);
		   //  textView6.setTypeface(typefaceThin);
		   //  textView7.setTypeface(typefaceThin);
		   //  textView8.setTypeface(typefaceThin);
		    // textView9.setTypeface(typefaceThin);
		   //  textView10.setTypeface(typefaceThin);
		   //  textView11.setTypeface(typefaceThin);
		   //  textView12.setTypeface(typefaceThin);
		     textView13.setTypeface(typefaceRoman);
		     //textView14.setTypeface(typefaceRoman);
		     TextView01.setTypeface(typefaceRoman);
		     TextView02.setTypeface(typefaceRoman);
		     TextView03.setTypeface(typefaceRoman);
		     TextView04.setTypeface(typefaceRoman);
		     timeOutsT.setTypeface(typefaceRoman);
		     
		    
		    /*textView2.setText(android.os.Build.BRAND);
		    textView3.setText(System.getProperty("os.version"));
		    textView5.setText(android.os.Build.HARDWARE);
		    textView6.setText(System.getProperty("os.arch"));
		    textView7.setText(System.getProperty("os.name"));
		    */
		    animationRotateDown = AnimationUtils.loadAnimation(
					this, R.anim.rotate_down);
		    animationRotateUp = AnimationUtils.loadAnimation(
					this, R.anim.rotate_up);   
		    
		    LinearLayout main = (LinearLayout) findViewById(R.id.main);
		    
		    
		    
		    
		    ///////////////////////////////////////////////////////////////////////////////
		    pm = this.getPackageManager();
		      //Battery 
		    /*bat1 = pm.getLaunchIntentForPackage("android.settings.ACTION_BATTERY_SAVER_SETTINGS");
		    
		    final Intent bat2 = new Intent(Intent.ACTION_MAIN, null);
			bat2.addCategory(Intent.CATEGORY_LAUNCHER);
			final ComponentName cn = new ComponentName(
					"com.android.settings",
					"com.android.settings.fuelgauge.PowerUsageSummary");
			bat2.setComponent(cn);
			bat2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
		      //Siri
		    	siri = pm.getLaunchIntentForPackage("com.google.android.googlequicksearchbox.VoiceSearchActivity");
		      //Spotlight
		    	spotlight = pm.getLaunchIntentForPackage("com.google.android.googlequicksearchbox");
	  	      //Notification 
	  	        notif_inoty = pm.getLaunchIntentForPackage("net.suckga.inoty");
	  	        notif_espier = pm.getLaunchIntentForPackage("mobi.espier.launcher.plugin.notifications7pro");
	  	      //Control Center
	  	        control_hi = pm.getLaunchIntentForPackage("com.hi.apps.studio.control.center");
	  	        control_espier = pm.getLaunchIntentForPackage("mobi.espier.launcher.plugin.controller7pro");
	  	      //Mail
	  	        mail_stok = pm.getLaunchIntentForPackage("com.android.email"); 
	  	      //Phone
	  	        phone_stok = pm.getLaunchIntentForPackage("com.android.dialer");
	  	      //Messages
	  	    	messages_stok = pm.getLaunchIntentForPackage("com.android.mms");
	  	      //Safari
	  	    	safari_stok = pm.getLaunchIntentForPackage("com.android.browser");
	  	      //Music;
	  	    	music_stok = pm.getLaunchIntentForPackage("com.android.music");  
	  	     //VK
	  	    	vk_stok = pm.getLaunchIntentForPackage("com.vkontakte.android");
	  	     //Viber
	  	    	viber_stok = pm.getLaunchIntentForPackage("com.viber.voip");
	  	     //Ok
	  	    	ok_stok = pm.getLaunchIntentForPackage("ru.ok.android");
	  	     //Skype
	  	    	skype_stok = pm.getLaunchIntentForPackage("com.skype.raider");
	  	     //WhatsApp!!!!
	  	    	whatsapp_stok = pm.getLaunchIntentForPackage("com.whatsapp");
	  	     //Twitter
	  	    	twitter_stok = pm.getLaunchIntentForPackage("com.twitter.android");
	  	     //Facebook
	  	    	facebook_stok = pm.getLaunchIntentForPackage("com.facebook.katana");
	  	     //Instagram
	  	    	instagram_stok = pm.getLaunchIntentForPackage("com.instagram.android");
	  	     //Maps
	  	    	maps_stok = pm.getLaunchIntentForPackage("com.google.android.apps.maps");
	  	    //Appstore	
	  	    	appstore_stok = pm.getLaunchIntentForPackage("com.android.vending");
	  	    	///////////////////////////////////////////////////////////////////////////////
	   }
	   
	   
	   /*
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
	   */
	   
	   
	   
	   protected void onResume() {
	        super.onResume();       
	       ButtonRotate();
	       TimeOuts();
	       
	    	if (null == spotlight){
	    		Button13.setTextColor(getResources().getColor(R.color.hint_text));
	    		Button12.setTextColor(getResources().getColor(R.color.hint_text));
	    	}/*
	    	if (null == bat1 && null == bat2){
	    		
	    		LinearLayoutBat.setVisibility(View.GONE);
	    			TextView05.setVisibility(View.GONE);
	    		
	    	}
	    	*/
	    	
	       
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
				     Button02.setTypeface(typefaceBold);
				     Button03.setTypeface(typefaceBold);
				     Button01.setTypeface(typefaceBold);
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
				     btn_setup_app.setTypeface(typefaceBold);
				     textView1.setTypeface(typefaceBold);
				  //   textView2.setTypeface(typefaceRoman);
				  //   textView3.setTypeface(typefaceRoman);
				  //   textView4.setTypeface(typefaceRoman);
				  //   textView5.setTypeface(typefaceRoman);
				  //   textView6.setTypeface(typefaceRoman);
				 //    textView7.setTypeface(typefaceRoman);
				 //    textView8.setTypeface(typefaceRoman);
				 //    textView9.setTypeface(typefaceRoman);
				 //    textView10.setTypeface(typefaceRoman);
				 //    textView11.setTypeface(typefaceRoman);
				 //    textView12.setTypeface(typefaceRoman);
				     textView13.setTypeface(typefaceBold);
				    // textView14.setTypeface(typefaceBold);
				     TextView01.setTypeface(typefaceBold);
				     TextView02.setTypeface(typefaceBold);
				     TextView03.setTypeface(typefaceBold);
				     timeOutsT.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
				     Button02.setTextSize(14);
				     Button03.setTextSize(14);
				     Button01.setTextSize(14);
				     Button04.setTextSize(14);
				     Button05.setTextSize(14);
				     Button06.setTextSize(14);
				     Button07.setTextSize(14);
				     Button08.setTextSize(14);
				     Button09.setTextSize(14);
				     Button10.setTextSize(14);
				     Button11.setTextSize(14);
				     Button12.setTextSize(14);
				     Button13.setTextSize(14);
				     btn_setup_app.setTextSize(14);
				     textView1.setTextSize(11);
				  //   textView2.setTextSize(15);
				  //   textView3.setTextSize(15);
				  //   textView4.setTextSize(15);
				  //   textView5.setTextSize(15);
				   //  textView6.setTextSize(15);
				   //  textView7.setTextSize(15);
				  //   textView8.setTextSize(15);
				  //   textView9.setTextSize(15);
				  //   textView10.setTextSize(15);
				  //   textView11.setTextSize(15);
				  //   textView12.setTextSize(15);
				     textView13.setTextSize(11);
				    // textView14.setTextSize(10);
				     TextView01.setTextSize(11);
				     TextView02.setTextSize(11);
				     TextView03.setTextSize(11);
				     timeOutsT.setTextSize(14);
				}
				if (size .contains( "Normal")){
				     Button02.setTextSize(16);
				     Button03.setTextSize(16);
				     Button01.setTextSize(16);
				     Button04.setTextSize(16);
				     Button05.setTextSize(16);
				     Button06.setTextSize(16);
				     Button07.setTextSize(16);
				     Button08.setTextSize(16);
				     Button09.setTextSize(16);
				     Button10.setTextSize(16);
				     Button11.setTextSize(16);
				     Button12.setTextSize(16);
				     Button13.setTextSize(16);
				     btn_setup_app.setTextSize(16);
				     textView1.setTextSize(13);
				   //  textView2.setTextSize(17);
				   //  textView3.setTextSize(17);
				   //  textView4.setTextSize(17);
				   //  textView5.setTextSize(17);
				   //  textView6.setTextSize(17);
				  //   textView7.setTextSize(17);
				  //   textView8.setTextSize(17);
				   //  textView9.setTextSize(17);
				   //  textView10.setTextSize(17);
				   //  textView11.setTextSize(17);
				   //  textView12.setTextSize(17);
				     textView13.setTextSize(13);
				   //  textView14.setTextSize(12);
				     TextView01.setTextSize(13);
				     TextView02.setTextSize(13);
				     timeOutsT.setTextSize(16);
				}
				if (size .contains( "Large")){
				     Button02.setTextSize(19);
				     Button03.setTextSize(19);
				     Button01.setTextSize(19);
				     Button04.setTextSize(19);
				     Button05.setTextSize(19);
				     Button06.setTextSize(19);
				     Button07.setTextSize(19);
				     Button08.setTextSize(19);
				     Button09.setTextSize(19);
				     Button10.setTextSize(19);
				     Button11.setTextSize(19);
				     Button12.setTextSize(19);
				     Button13.setTextSize(19);
				     btn_setup_app.setTextSize(19);
				     textView1.setTextSize(16);
				 //    textView2.setTextSize(20);
				  //   textView3.setTextSize(20);
				  //   textView4.setTextSize(20);
				  //   textView5.setTextSize(20);
				  //   textView6.setTextSize(20);
				  //   textView7.setTextSize(20);
				  //   textView8.setTextSize(20);
				  //   textView9.setTextSize(20);
				   //  textView10.setTextSize(20);
				   //  textView11.setTextSize(20);
				    // textView12.setTextSize(20);
				     textView13.setTextSize(16);
				  //   textView14.setTextSize(15);
				     TextView01.setTextSize(16);
				     TextView02.setTextSize(16);
				     TextView03.setTextSize(16);
				     timeOutsT.setTextSize(19);
				}
				if (size .contains( "xLarge")){
				     Button02.setTextSize(21);
				     Button03.setTextSize(21);
				     Button01.setTextSize(21);
				     Button04.setTextSize(21);
				     Button05.setTextSize(21);
				     Button06.setTextSize(21);
				     Button07.setTextSize(21);
				     Button08.setTextSize(21);
				     Button09.setTextSize(21);
				     Button10.setTextSize(21);
				     Button11.setTextSize(21);
				     Button12.setTextSize(21);
				     Button13.setTextSize(21);
				     btn_setup_app.setTextSize(21);
				     textView1.setTextSize(18);
				 //    textView2.setTextSize(22);
				  //   textView3.setTextSize(22);
				  //   textView4.setTextSize(22);
				 //    textView5.setTextSize(22);
				  //   textView6.setTextSize(22);
				  //   textView7.setTextSize(22);
				  //   textView8.setTextSize(22);
				  //   textView9.setTextSize(22);
				  //   textView10.setTextSize(22);
				  //   textView11.setTextSize(22);
				  //   textView12.setTextSize(22);
				     textView13.setTextSize(18);
				   //  textView14.setTextSize(17);
				     TextView01.setTextSize(18);
				     TextView02.setTextSize(18);
				     TextView03.setTextSize(18);
				     timeOutsT.setTextSize(21);
				}
	       }
	       if (OS > 18){
	    	   TextView TextView01 = (TextView) findViewById(R.id.TextView01);
	    	   LinearLayout LinearLayout03 = (LinearLayout) findViewById(R.id.LinearLayout03);
	    	   LinearLayout03.setVisibility(View.GONE);
	    	   TextView01.setVisibility(View.GONE);
	       }
	    }
	   
	  
	     
	 private void TimeOuts() {
		 long stand = Settings.System.getLong(
                 this.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT,
                 -1);
         long sec = stand / 1000;
         String time = null;
                             if(stand<0) {
                                  //close.
                             }
         else if( sec >= 60) {//to minute
             time = String.format( (sec / 60) + " " +  getResources().getString(R.string.minutes));
         } else {
             time = String.format( sec + " " + getResources().getString(R.string.secundes));
         }
            timeOutsT.setText(time);
	}


	//ROTATE MODE
	    
	    private void ButtonRotate() {
	    	if(android.provider.Settings.System.getInt(getContentResolver(),Settings.System.ACCELEROMETER_ROTATION, 0) == 1)
	    	{
	            tb_rotate.setChecked(true);
	    	}
	    }
	 
	    public void onClick(View v) {
	    	int id = v.getId();
	    	
	    	
	    	if (id == R.id.rotatetoggle){
              	 if((tb_rotate).isChecked()) {
              		 if(android.provider.Settings.System.getInt(getContentResolver(),Settings.System.ACCELEROMETER_ROTATION, 1) == 0)
              	    	{
              		 android.provider.Settings.System.putInt(getContentResolver(),Settings.System.ACCELEROMETER_ROTATION, 1);
              	    	}
              	 	}
              	 else {
              		 if(android.provider.Settings.System.getInt(getContentResolver(),Settings.System.ACCELEROMETER_ROTATION, 0) == 1){ 
              		 android.provider.Settings.System.putInt(getContentResolver(),Settings.System.ACCELEROMETER_ROTATION, 0);}
              	 }
	        }
	    	
	    	else if (id == R.id.Button02){
	    		Intent intent = new Intent(this, ActivityUsage.class);
	        	 startActivity(intent);
	  	        	overridePendingTransition(center_to_left, center_to_left2);
	    	}
	    	else  if (id == R.id.Button04){
	        	Intent intent = new Intent(this, ActivityInfo.class);
	        	 startActivity(intent);
	  	        	overridePendingTransition(center_to_left, center_to_left2);
	  	        	 }
	    	else  if (id == R.id.Button06){
	        	Intent intent = new Intent(this, ActivityApps.class);
	        	 startActivity(intent);
	  	        	overridePendingTransition(center_to_left, center_to_left2);
	  	        	 }
	    	else  if (id == R.id.Button05){
	        	Intent intent = new Intent(this, ActivityStorage.class);
	        	 startActivity(intent);
	  	        	overridePendingTransition(center_to_left, center_to_left2);
	  	        	 }
	    	else if (id == R.id.Button07){
	    		Intent settingsIntent = new Intent(android.provider.Settings.ACTION_DATE_SETTINGS);
	        	startActivity(settingsIntent);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	    	else if (id == R.id.Button08){
	    		Intent settingsIntent = new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS);
	        	startActivity(settingsIntent);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	    	else if (id == R.id.ButtonSetupApp){
	    		launchIntent();
	    	}
	    	else if (id == R.id.Button09){
	    		Intent intent = new Intent(this, ActivityAbout.class);
	        	startActivity(intent);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	    	
	    	else if (id == R.id.Button10){
	    		
	        	startActivity(new Intent("android.settings.LOCALE_SETTINGS"));
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	    	
	    	else if (id == R.id.Button03){
	    		stok_clear();
	    	}
	    	
	    	else if (id == R.id.Button11){
	    		Intent intent = new Intent(this, ActivityTimeout.class);
	        	startActivity(intent);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 
	    	}
	    	
	    	else if (id == R.id.Button12){
	    		try{
	    		final Intent intent = new Intent(Intent.ACTION_MAIN, null);

				intent.addCategory(Intent.CATEGORY_LAUNCHER);

				final ComponentName cn = new ComponentName(
						"com.google.android.googlequicksearchbox",
						"com.google.android.googlequicksearchbox.VoiceSearchActivity");

				intent.setComponent(cn);

				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				startActivity(intent);
	    	}
    		catch(Exception e){
    			try {
    			    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.googlequicksearchbox")));
    			    overridePendingTransition(center_to_left, center_to_left2);
    			} catch (android.content.ActivityNotFoundException anfe) {
    			    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.googlequicksearchbox")));
    			    overridePendingTransition(center_to_left, center_to_left2);
    			}
    		}

	    	}
	    	
	    	else if (id == R.id.Button13){
	    		try{
		    		startActivity(spotlight);
		 	        	overridePendingTransition(center_to_left, center_to_left2);
		    		}
		    		catch(Exception e){
		    			try {
		    			    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.googlequicksearchbox")));
		    			    overridePendingTransition(center_to_left, center_to_left2);
		    			} catch (android.content.ActivityNotFoundException anfe) {
		    			    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.googlequicksearchbox")));
		    			    overridePendingTransition(center_to_left, center_to_left2);
		    			}
		    		}
	 	        	 
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
	    	  Intent intent18 = new Intent(this, MainActivity.class);
	          	 startActivity(intent18);
	          	break;
	      /*case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
	                                                     break;
	      */
	      }
	       //Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	     }
	      
	     @Override
	     public void onDoubleTap() {
	        //Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
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
	            Intent it = new Intent(ActivityOsnova.this, SettingsActivity.class);
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
	    
	    private void stok_clear (){
			 
			// File file2= new File("/data/data/ua.mkh.settings.full/shared_prefs/my_settings.xml");
			// File file1= new File("/data/data/ua.mkh.settings.full/shared_prefs/mysettings.xml");
			// file1.delete();
			// file2.delete();
		
			 final Dialog dialog = new Dialog(ActivityOsnova.this,android.R.style.Theme_Translucent);
		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     dialog.setContentView(R.layout.dialog_menu);
		     dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		     Button ButtonInfo = (Button)dialog.getWindow().findViewById(R.id.button1);
		     Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuCancel);
		     Button ButtonMenuSettings = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuSettings);
		     ButtonMenuSettings.setTypeface(typefaceRoman);
		     ButtonMenuCancel.setTypeface(typefaceMedium);
		     
		     ButtonInfo.setTypeface(typefaceRoman);
		     ButtonInfo.setText(R.string.menu_info_reset);
		     ButtonMenuSettings.setText(R.string.reset_all_settings);
		     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View v) {
		    dialog.dismiss();
		   }});
		     
		     ButtonMenuSettings.setOnClickListener(new OnClickListener(){

		  	   @Override
		  	   public void onClick(View v) {
		  		 Editor editor = mSettings.edit();
				 editor.clear();
				 editor.apply();
				 stok ();
				 dialog.dismiss();
				 Intent intent = new Intent(ActivityOsnova.this, MainActivity.class);
		        	startActivity(intent);
		 	        	overridePendingTransition(center_to_left, center_to_left2);
		  	   }});
		     
		     dialog.show();
		     
		 }
	    private void stok (){
	    	
			Editor editor = mSettings.edit();
			if (null != mail_stok){
				mail_app = "com.android.email";
		   	editor.putString(APP_PREFERENCES_MAIL, mail_app); }
			
			if (null != phone_stok){
				phone_app = "com.android.dialer";
		   	editor.putString(APP_PREFERENCES_PHONE, phone_app); }
			
			if (null != messages_stok){
				messages_app = "com.android.mms";
		   	editor.putString(APP_PREFERENCES_MESSAGES, messages_app); }
			
			if (null != safari_stok){
				safari_app = "com.android.browser";
		   	editor.putString(APP_PREFERENCES_SAFARI, safari_app); }
			
			if (null != music_stok){
				music_app = "com.android.music";
		   	editor.putString(APP_PREFERENCES_MUSIC, music_app); }
			
			if (null != notif_inoty){
				notifications_app = "net.suckga.inoty";
		   	editor.putString(APP_PREFERENCES_NOTIFICATIONS, notifications_app); }
			
			if (null != notif_espier){
				notifications_app = "mobi.espier.launcher.plugin.notifications7pro";
		   	editor.putString(APP_PREFERENCES_NOTIFICATIONS, notifications_app); }
			
			if (null != control_hi){
				control_app = "com.hi.apps.studio.control.center";
		   	editor.putString(APP_PREFERENCES_CONTROL, control_app); }
			
			if (null != control_espier){
				control_app = "mobi.espier.launcher.plugin.controller7pro";
		   	editor.putString(APP_PREFERENCES_CONTROL, control_app); }
			
			if (null != vk_stok){
				vk_app = "com.vkontakte.android";
			   	editor.putString(APP_PREFERENCES_VK, vk_app);
			}
			
			if (null != viber_stok){
				viber_app = "com.viber.voip";
			   	editor.putString(APP_PREFERENCES_VIBER, viber_app);
			}
			if (null != ok_stok){
				ok_app = "ru.ok.android";
			   	editor.putString(APP_PREFERENCES_OK, ok_app);
			}
			
			if (null != skype_stok){
				skype_app = "com.skype.raider";
			   	editor.putString(APP_PREFERENCES_SKYPE, skype_app);
			}
			
			if (null != whatsapp_stok){
				whatsapp_app = "com.whatsapp";
			   	editor.putString(APP_PREFERENCES_WHATSAPP, whatsapp_app);
			}
			
			if (null != twitter_stok){
				twitter_app = "com.twitter.android";
			   	editor.putString(APP_PREFERENCES_TWITTER, twitter_app);
			}
			
			if (null != facebook_stok){
				facebook_app = "com.facebook.katana";
			   	editor.putString(APP_PREFERENCES_FACEBOOK, facebook_app);
			}
			
			if (null != instagram_stok){
				instagram_app = "com.instagram.android";
			   	editor.putString(APP_PREFERENCES_INSTAGRAM, instagram_app);
			}
			
			if (null != appstore_stok){
				appstore_app = "com.android.vending";
			   	editor.putString(APP_PREFERENCES_ITUNES, appstore_app);
			}
			
		   	editor.apply();
		}

		
	    
}
