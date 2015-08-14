package ua.mkh.settings.full;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.auth.GoogleAuthUtil;



import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActivityiCloud extends Activity implements OnClickListener, SimpleGestureListener{
	
	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	SharedPreferences mSettings;
	
	int menui = 0;
	
	int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   private SimpleGestureFilter detector;
	   
	   PackageManager pm;
	   
	   AccountManager mAccountManager;
		String token;
		int serverCode;
		private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
		ImageView imageProfile;
		//TextView textViewName, textViewEmail, textViewGender, textViewBirthday;
		String textName, textEmail, textGender, textBirthday, userImageUrl;
		ProgressBar pg1;
		RelativeLayout ric;
	
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   public static final String APP_PREFERENCES_NAME = "name";
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
	   public static final String APP_PREFERENCES_ITUNES = "itunes_app";
	   
	   public static final boolean APP_PREFERENCES_MAIL_TGB = false;
	   public static final boolean APP_PREFERENCES_NOTEPAD_TGB = false;
	   public static final boolean APP_PREFERENCES_PHONE_TGB = false;
	   public static final boolean APP_PREFERENCES_MESSAGE_TGB = false;
	   public static final boolean APP_PREFERENCES_COMPASS_TGB = false;
	   public static final boolean APP_PREFERENCES_SAFARI_TGB = false;
	   public static final boolean APP_PREFERENCES_MUSIC_TGB = false;
	   public static final boolean APP_PREFERENCES_GAME_TGB = false;
	   public static final boolean APP_PREFERENCES_WEATHER_TGB = false;
	   public static final boolean APP_PREFERENCES_MAPS_TGB = false;
	   public static final boolean APP_PREFERENCES_VK_TGB = false;
	   public static final boolean APP_PREFERENCES_VIBER_TGB = false;
	   public static final boolean APP_PREFERENCES_OK_TGB = false;
	   public static final boolean APP_PREFERENCES_SKYPE_TGB = false;
	   public static final boolean APP_PREFERENCES_WHATSAPP_TGB = false;
	   public static final boolean APP_PREFERENCES_TWITTER_TGB = false;
	   public static final boolean APP_PREFERENCES_FACEBOOK_TGB = false;
	   public static final boolean APP_PREFERENCES_INSTAGRAM_TGB = false;
	   public static final boolean APP_PREFERENCES_APPSTORE_TGB = false;
	   
	
	Button btn_mail, btn_notepad, btn_phone, btn_message, btn_safari, btn_music, btn_game,
			btn_weather, btn_compass, btn_maps, btn_vk, btn_viber, btn_ok, btn_skype,
			btn_whatsapp, btn_twitter, btn_facebook, btn_instagram, btn_newacc, btn_delacc,
			btn_account, buttonBack, btn_menu_settings, btn_menu_cancel, button_update, button_sync, btn_appstore;
	
	Intent notif_inoty, notif_espier, control_hi, control_espier, mail_ino, mail_stok, 
	notes_any, notes_espier, phone_kuandroid, phone_espier, phone_phone_stok, phone_stok,
	messages_easyandroid, messages_iphonestyle,	messages_espier, messages_stok, 
	safari_ios, safari_stok, music_easyandroid1, music_easyandroid2, music_hi, music_stok, compass_hanimobile, weather_cybob,
	weather_yahoo, weather_kuandroid, games_mkh, new1,new2, new3, new4, vk_stok, maps_stok, viber_stok,
	ok_stok, skype_stok, whatsapp_stok, twitter_stok, facebook_stok, instagram_stok, appstore_stok;
	
	TextView acc, textStatus, name, textView3;
	ToggleButton tb_sync;
	
	ToggleButton mail, notes, phone, messages, compass, safari, music, game, weather, maps, vk, viber, ok, skype, whatsapp, twitter, facebook, instagram,
	appstore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icloud);
		String roman = "fonts/Regular.ttf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.ttf";
		
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		 detector = new SimpleGestureFilter(this,this);
		 pg1 = (ProgressBar) findViewById(R.id.progressBar1);
		 pg1.setVisibility(View.GONE);
		// syncGoogleAccount();
		 
		 tb_sync = (ToggleButton)findViewById(R.id.synctoggle);
		 tb_sync.setOnClickListener(this);
		 
		 
		 /**
			 * get user email using intent
			 */
/*
			Intent intent = getIntent();
			textEmail = intent.getStringExtra("email_id");
			System.out.println(textEmail);
			textViewEmail.setText(textEmail);
*/
			/**
			 * get user data from google account
			 */
		 name = (TextView) findViewById(R.id.textView2);
			try {
				System.out.println("On Home Page***"
						+ AbstractGetNameTask.GOOGLE_USER_DATA);
				JSONObject profileData = new JSONObject(
						AbstractGetNameTask.GOOGLE_USER_DATA);

				if (profileData.has("picture")) {
					userImageUrl = profileData.getString("picture");
					new GetImageFromUrl().execute(userImageUrl);
				}
				if (profileData.has("name")) {
					textName = profileData.getString("name");
					name.setText(textName);
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_NAME, textName);
				   	editor.commit(); }
				
		//		if (profileData.has("gender")) {
		//			textGender = profileData.getString("gender");
		//			textViewGender.setText(textGender);
		//		}
		//		if (profileData.has("birthday")) {
		//			textBirthday = profileData.getString("birthday");
		//			textViewBirthday.setText(textBirthday);
		//		}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//button_update = (Button) findViewById(R.id.button1);
		//button_update.setOnClickListener(this);
		imageProfile = (ImageView) findViewById(R.id.imageView_round);
		ric = (RelativeLayout) findViewById(R.id.iClouds);
		ric.setOnClickListener(this);
		
		button_sync = (Button) findViewById(R.id.Button19);
		
		btn_mail = (Button) findViewById(R.id.Button01);
		btn_mail.setOnClickListener(this);
		btn_mail.setEnabled(false);
		btn_notepad = (Button) findViewById(R.id.Button15);
		btn_notepad.setOnClickListener(this);
		btn_notepad.setEnabled(false);
		btn_phone = (Button) findViewById(R.id.Button14);
		btn_phone.setOnClickListener(this);
		btn_phone.setEnabled(false);
		btn_message = (Button) findViewById(R.id.Button13);
		btn_message.setOnClickListener(this);
		btn_message.setEnabled(false);
		btn_safari = (Button) findViewById(R.id.Button12);
		btn_safari.setOnClickListener(this);
		btn_safari.setEnabled(false);
		btn_music = (Button) findViewById(R.id.Button11);
		btn_music.setOnClickListener(this);
		btn_music.setEnabled(false);
		btn_game = (Button) findViewById(R.id.Button10);
		btn_game.setOnClickListener(this);
		btn_game.setEnabled(false);
		btn_weather = (Button) findViewById(R.id.Button09);
		btn_weather.setOnClickListener(this);
		btn_weather.setEnabled(false);
		btn_compass = (Button) findViewById(R.id.Button08);
		btn_compass.setOnClickListener(this);
		btn_compass.setEnabled(false);
		btn_maps = (Button) findViewById(R.id.Button07);
		btn_maps.setOnClickListener(this);
		btn_maps.setEnabled(false);
		btn_vk = (Button) findViewById(R.id.Button06);
		btn_vk.setOnClickListener(this);
		btn_vk.setEnabled(false);
		btn_viber = (Button) findViewById(R.id.Button05);
		btn_viber.setOnClickListener(this);
		btn_viber.setEnabled(false);
		btn_ok = (Button) findViewById(R.id.Button04);
		btn_ok.setOnClickListener(this);
		btn_ok.setEnabled(false);
		btn_skype = (Button) findViewById(R.id.Button03);
		btn_skype.setOnClickListener(this);
		btn_skype.setEnabled(false);
		btn_whatsapp = (Button) findViewById(R.id.Button02);
		btn_whatsapp.setOnClickListener(this);
		btn_whatsapp.setEnabled(false);
		btn_twitter = (Button) findViewById(R.id.Button18);
		btn_twitter.setOnClickListener(this);
		btn_twitter.setEnabled(false);
		btn_facebook = (Button) findViewById(R.id.Button17);
		btn_facebook.setOnClickListener(this);
		btn_facebook.setEnabled(false);
		btn_instagram = (Button) findViewById(R.id.Button16);
		btn_instagram.setOnClickListener(this);
		btn_instagram.setEnabled(false);
		btn_newacc = (Button) findViewById(R.id.Button20);
		btn_newacc.setOnClickListener(this);
		btn_appstore= (Button) findViewById(R.id.Button21);
		btn_appstore.setOnClickListener(this);
		btn_appstore.setEnabled(false);
		//btn_delacc = (Button) findViewById(R.id.Button19);
		btn_account = (Button) findViewById(R.id.ButtonWifi);
		
		mail = (ToggleButton) findViewById(R.id.ToggleButtonMail);
		mail.setOnClickListener(this);
		notes = (ToggleButton) findViewById(R.id.ToggleButtonNotes);
		notes.setOnClickListener(this);
		phone = (ToggleButton) findViewById(R.id.ToggleButtonPhone);
		phone.setOnClickListener(this);
		messages = (ToggleButton) findViewById(R.id.ToggleButtonMessage);
		messages.setOnClickListener(this);
		compass = (ToggleButton) findViewById(R.id.ToggleButtonCompass);
		compass.setOnClickListener(this);
		safari = (ToggleButton) findViewById(R.id.ToggleButtonSafari);
		safari.setOnClickListener(this);
		music = (ToggleButton) findViewById(R.id.ToggleButtonMusic);
		music.setOnClickListener(this);
		game = (ToggleButton) findViewById(R.id.ToggleButtonGame);
		game.setOnClickListener(this);
		weather = (ToggleButton) findViewById(R.id.ToggleButtonWeather);
		weather.setOnClickListener(this);
		maps = (ToggleButton) findViewById(R.id.ToggleButtonMaps);
		maps.setOnClickListener(this);
		vk = (ToggleButton) findViewById(R.id.ToggleButtonVk);
		vk.setOnClickListener(this);
		viber = (ToggleButton) findViewById(R.id.ToggleButtonViber);
		viber.setOnClickListener(this);
		ok = (ToggleButton) findViewById(R.id.ToggleButtonOk);
		ok.setOnClickListener(this);
		skype = (ToggleButton) findViewById(R.id.ToggleButtonSkype);
		skype.setOnClickListener(this);
		whatsapp = (ToggleButton) findViewById(R.id.ToggleButtonWhatsApp);
		whatsapp.setOnClickListener(this);
		twitter = (ToggleButton) findViewById(R.id.ToggleButtonTwitter);
		twitter.setOnClickListener(this);
		facebook = (ToggleButton) findViewById(R.id.ToggleButtonFacebook);
		facebook.setOnClickListener(this);
		instagram = (ToggleButton) findViewById(R.id.ToggleButtonInstagram);
		instagram.setOnClickListener(this);
		appstore = (ToggleButton) findViewById(R.id.ToggleButtonAppstore);
		appstore.setOnClickListener(this);
		buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setText(R.string.app_name);
		btn_menu_settings = (Button) findViewById(R.id.ButtonMenuSettings);
		btn_menu_cancel = (Button) findViewById(R.id.ButtonMenuCancel);
		textStatus = (TextView)findViewById(R.id.textOk);
		
		acc = (TextView) findViewById(R.id.textwifi);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView3.setVisibility(View.GONE);
		
		buttonBack.setTypeface(typefaceMedium);
		btn_mail.setTypeface(typefaceRoman);
		btn_notepad.setTypeface(typefaceRoman);
		btn_phone.setTypeface(typefaceRoman);
		btn_message.setTypeface(typefaceRoman);
		btn_safari.setTypeface(typefaceRoman);
		btn_music.setTypeface(typefaceRoman);
		btn_game.setTypeface(typefaceRoman);
		btn_weather.setTypeface(typefaceRoman);
		btn_compass.setTypeface(typefaceRoman);
		btn_maps.setTypeface(typefaceRoman);
		btn_vk.setTypeface(typefaceRoman);
		btn_viber.setTypeface(typefaceRoman);
		btn_ok.setTypeface(typefaceRoman);
		btn_skype.setTypeface(typefaceRoman);
		btn_whatsapp.setTypeface(typefaceRoman);
		btn_twitter.setTypeface(typefaceRoman);
		btn_facebook.setTypeface(typefaceRoman);
		btn_instagram.setTypeface(typefaceRoman);
		btn_newacc.setTypeface(typefaceRoman);
		button_sync.setTypeface(typefaceRoman);
		btn_appstore.setTypeface(typefaceRoman);
		//btn_account.setTypeface(typefaceRoman);
		acc.setTypeface(typefaceRoman);
		name.setTypeface(typefaceRoman);
		
		 textStatus.setTypeface(typefaceMedium);
		 textStatus.setText(R.string.icloud);
		
///////////////////APP//////////////
pm = this.getPackageManager();
 
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
     private String[] getAccountNames() {
 		mAccountManager = AccountManager.get(this);
 		Account[] accounts = mAccountManager
 				.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
 		String[] names = new String[accounts.length];
 		for (int i = 0; i < names.length; i++) {
 			names[i] = accounts[i].name;
 		}
 		return names;
 	}

 	private AbstractGetNameTask getTask(ActivityiCloud activity, String email,
 			String scope) {
 		return new GetNameInForeground(activity, email, scope);

 	}

 	public void syncGoogleAccount() {
 		if (isNetworkAvailable() == true) {
 			String[] accountarrs = getAccountNames();
 			if (accountarrs.length > 0) {
 				//you can set here account for login
 				getTask(ActivityiCloud.this, accountarrs[0], SCOPE).execute();
 			} else {
 				Toast.makeText(ActivityiCloud.this, "No Google Account Sync!",
 						Toast.LENGTH_SHORT).show();
 			}
 		} else {
 			Toast.makeText(ActivityiCloud.this, "No Network Service!",
 					Toast.LENGTH_SHORT).show();
 		}
 	}
 	public boolean isNetworkAvailable() {

 		ConnectivityManager cm = (ConnectivityManager) this
 				.getSystemService(Context.CONNECTIVITY_SERVICE);
 		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
 		if (networkInfo != null && networkInfo.isConnected()) {
 			Log.e("Network Testing", "***Available***");
 			return true;
 		}
 		Log.e("Network Testing", "***Not Available***");
 		return false;
 	}
 	
 	public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap map = null;
			for (String url : urls) {
				map = downloadImage(url);
			}
			return map;
		}

		// Sets the Bitmap returned by doInBackground
		@Override
		protected void onPostExecute(Bitmap result) {
			//imageProfile.setImageBitmap(result);
			
			String iconsStoragePath = Environment.getExternalStorageDirectory() + "/fineSettings/iCloud";
					File sdIconStorageDir = new File(iconsStoragePath);

					//create storage directories, if they don't exist
					sdIconStorageDir.mkdirs();

					try {
						String filePath = sdIconStorageDir.toString() + "/001.png";
						FileOutputStream fileOutputStream = new FileOutputStream(filePath);

						BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

						//choose another format if PNG doesn't suit you
						result.compress(CompressFormat.PNG, 100, bos);

						bos.flush();
						bos.close();

					} catch (FileNotFoundException e) {
						Log.w("TAG", "Error saving image file: " + e.getMessage());
						
					} catch (IOException e) {
						Log.w("TAG", "Error saving image file: " + e.getMessage());
						
					}

		}

		// Creates Bitmap from InputStream and returns it
		private Bitmap downloadImage(String url) {
			Bitmap bitmap = null;
			InputStream stream = null;
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			bmOptions.inSampleSize = 1;

			try {
				stream = getHttpConnection(url);
				bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
				stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return bitmap;
		}

		// Makes HttpURLConnection and returns InputStream
		private InputStream getHttpConnection(String urlString)
				throws IOException {
			InputStream stream = null;
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();

			try {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setRequestMethod("GET");
				httpConnection.connect();

				if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					stream = httpConnection.getInputStream();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}
	}
     
     
     
	protected void onResume() {
        super.onResume();
        
        ButtonSync();
        
        try {
			System.out.println("On Home Page***"
					+ AbstractGetNameTask.GOOGLE_USER_DATA);
			JSONObject profileData = new JSONObject(
					AbstractGetNameTask.GOOGLE_USER_DATA);

			if (profileData.has("picture")) {
				userImageUrl = profileData.getString("picture");
				new GetImageFromUrl().execute(userImageUrl);
			}
			if (profileData.has("name")) {
				textName = profileData.getString("name");
				name.setText(textName);
				Editor editor = mSettings.edit();
			   	editor.putString(APP_PREFERENCES_NAME, textName);
			   	editor.commit(); }
			
	//		if (profileData.has("gender")) {
	//			textGender = profileData.getString("gender");
	//			textViewGender.setText(textGender);
	//		}
	//		if (profileData.has("birthday")) {
	//			textBirthday = profileData.getString("birthday");
	//			textViewBirthday.setText(textBirthday);
	//		}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

        
        File dir = new File(Environment.getExternalStorageDirectory() + "/fineSettings/iCloud/001.png");
        if(!dir.exists() ||  !mSettings.contains(APP_PREFERENCES_NAME)) {
        	name.setText("");
        	acc.setText("");
        	textView3.setVisibility(View.VISIBLE);
            //Toast.makeText(getApplicationContext(), "НЕТ ФОТО", Toast.LENGTH_SHORT).show();
        	pg1.setVisibility(View.VISIBLE);
			syncGoogleAccount();
        }
        else{
        	//Toast.makeText(getApplicationContext(), "ЕСТЬ ФОТО", Toast.LENGTH_SHORT).show();
        	String iconsStoragePath = Environment.getExternalStorageDirectory() + "/fineSettings/iCloud/001.png";
            
            Bitmap bmp = BitmapFactory.decodeFile(iconsStoragePath.toString());
            imageProfile.setImageBitmap(bmp);
            String menu = mSettings.getString(APP_PREFERENCES_NAME, null);
        	name.setText(menu);
        	 email_idd();
        }
        
        
        
       
        
        
        

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
				btn_mail.setTypeface(typefaceBold);
				btn_notepad.setTypeface(typefaceBold);
				btn_phone.setTypeface(typefaceBold);
				btn_message.setTypeface(typefaceBold);
				btn_safari.setTypeface(typefaceBold);
				btn_music.setTypeface(typefaceBold);
				btn_game.setTypeface(typefaceBold);
				btn_weather.setTypeface(typefaceBold);
				btn_compass.setTypeface(typefaceBold);
				btn_maps.setTypeface(typefaceBold);
				btn_vk.setTypeface(typefaceBold);
				btn_viber.setTypeface(typefaceBold);
				btn_ok.setTypeface(typefaceBold);
				btn_skype.setTypeface(typefaceBold);
				btn_whatsapp.setTypeface(typefaceBold);
				btn_twitter.setTypeface(typefaceBold);
				btn_facebook.setTypeface(typefaceBold);
				btn_instagram.setTypeface(typefaceBold);
				btn_newacc.setTypeface(typefaceBold);
				//btn_delacc.setTypeface(typefaceBold);
				button_sync.setTypeface(typefaceBold);
				btn_appstore.setTypeface(typefaceBold);
				acc.setTypeface(typefaceBold);
				textView3.setTypeface(typefaceBold);
				name.setTypeface(typefaceBold);
				
			}
        }
			
       if (mSettings.contains(APP_PREFERENCES_text_size)) {
			// Получаем число из настроек
        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
			if (size .contains( "Small")){
				btn_mail.setTextSize(14);
				btn_notepad.setTextSize(14);
				btn_phone.setTextSize(14);
				btn_message.setTextSize(14);
				btn_safari.setTextSize(14);
				btn_music.setTextSize(14);
				btn_game.setTextSize(14);
				btn_weather.setTextSize(14);
				btn_compass.setTextSize(14);
				btn_maps.setTextSize(14);
				btn_vk.setTextSize(14);
				btn_viber.setTextSize(14);
				btn_ok.setTextSize(14);
				btn_skype.setTextSize(14);
				btn_whatsapp.setTextSize(14);
				btn_twitter.setTextSize(14);
				btn_facebook.setTextSize(14);
				btn_instagram.setTextSize(14);
				btn_newacc.setTextSize(14);
				button_sync.setTextSize(14);
				btn_appstore.setTextSize(14);
				acc.setTextSize(14);
				textView3.setTextSize(11);
				name.setTextSize(16);
			}
			if (size .contains( "Normal")){
				btn_mail.setTextSize(16);
				btn_notepad.setTextSize(16);
				btn_phone.setTextSize(16);
				btn_message.setTextSize(16);
				btn_safari.setTextSize(16);
				btn_music.setTextSize(16);
				btn_game.setTextSize(16);
				btn_weather.setTextSize(16);
				btn_compass.setTextSize(16);
				btn_maps.setTextSize(16);
				btn_vk.setTextSize(16);
				btn_viber.setTextSize(16);
				btn_ok.setTextSize(16);
				btn_skype.setTextSize(16);
				btn_whatsapp.setTextSize(16);
				btn_twitter.setTextSize(16);
				btn_facebook.setTextSize(16);
				btn_instagram.setTextSize(16);
				btn_newacc.setTextSize(16);
				button_sync.setTextSize(16);
				btn_appstore.setTextSize(16);
				acc.setTextSize(16);
				textView3.setTextSize(13);
				name.setTextSize(18);
			}
			if (size .contains( "Large")){
			     	btn_mail.setTextSize(19);
					btn_notepad.setTextSize(19);
					btn_phone.setTextSize(19);
					btn_message.setTextSize(19);
					btn_safari.setTextSize(19);
					btn_music.setTextSize(19);
					btn_game.setTextSize(19);
					btn_weather.setTextSize(19);
					btn_compass.setTextSize(19);
					btn_maps.setTextSize(19);
					btn_vk.setTextSize(19);
					btn_viber.setTextSize(19);
					btn_ok.setTextSize(19);
					btn_skype.setTextSize(19);
					btn_whatsapp.setTextSize(19);
					btn_twitter.setTextSize(19);
					btn_facebook.setTextSize(19);
					btn_instagram.setTextSize(19);
					btn_newacc.setTextSize(19);
					button_sync.setTextSize(19);
					btn_appstore.setTextSize(19);
					acc.setTextSize(19);
					textView3.setTextSize(16);
					name.setTextSize(21);
			}
			if (size .contains( "xLarge")){
				btn_mail.setTextSize(21);
				btn_notepad.setTextSize(21);
				btn_phone.setTextSize(21);
				btn_message.setTextSize(21);
				btn_safari.setTextSize(21);
				btn_music.setTextSize(21);
				btn_game.setTextSize(21);
				btn_weather.setTextSize(21);
				btn_compass.setTextSize(21);
				btn_maps.setTextSize(21);
				btn_vk.setTextSize(21);
				btn_viber.setTextSize(21);
				btn_ok.setTextSize(21);
				btn_skype.setTextSize(21);
				btn_whatsapp.setTextSize(21);
				btn_twitter.setTextSize(21);
				btn_facebook.setTextSize(21);
				btn_instagram.setTextSize(21);
				btn_newacc.setTextSize(21);
				button_sync.setTextSize(21);
				btn_appstore.setTextSize(21);
				acc.setTextSize(21);
				textView3.setTextSize(18);
				name.setTextSize(23);
			}
       }
       
       if (mSettings.contains(APP_PREFERENCES_MAIL)) {
			// Получаем число из настроек
			mail.setChecked(true);
			btn_mail.setEnabled(true);}
     
       
       if (mSettings.contains(APP_PREFERENCES_NOTES)) {
			// Получаем число из настроек
			notes.setChecked(true);
			btn_notepad.setEnabled(true);}

       
       
       if (mSettings.contains(APP_PREFERENCES_PHONE)) {
			// Получаем число из настроек
			phone.setChecked(true);
			btn_phone.setEnabled(true);}

       
       
       if (mSettings.contains(APP_PREFERENCES_MESSAGES)) {
			// Получаем число из настроек
			messages.setChecked(true);
			btn_message.setEnabled(true);}

       
       
       if (mSettings.contains(APP_PREFERENCES_SAFARI)) {
			// Получаем число из настроек
			safari.setChecked(true);
			btn_safari.setEnabled(true);}

       
       
       if (mSettings.contains(APP_PREFERENCES_MUSIC)) {
			// Получаем число из настроек
			music.setChecked(true);
			btn_music.setEnabled(true);}

       
       
       if (mSettings.contains(APP_PREFERENCES_GAMECENTER)) {
			// Получаем число из настроек
			game.setChecked(true);
			btn_game.setEnabled(true);}

       
       
       if (mSettings.contains(APP_PREFERENCES_WEATHER)) {
			// Получаем число из настроек
			weather.setChecked(true);
			btn_weather.setEnabled(true);}

       
       
       if (mSettings.contains(APP_PREFERENCES_COMPASS)) {
			// Получаем число из настроек
			 compass.setChecked(true);
			 btn_compass.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_MAPS)) {
			// Получаем число из настроек
			 maps.setChecked(true);
			 btn_maps.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_VK)) {
			// Получаем число из настроек
			 vk.setChecked(true);
			 btn_vk.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_VIBER)) {
			// Получаем число из настроек
			 viber.setChecked(true);
			 btn_viber.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_OK)) {
			// Получаем число из настроек
			 ok.setChecked(true);
			 btn_ok.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_SKYPE)) {
			// Получаем число из настроек
			 skype.setChecked(true);
			 btn_skype.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_WHATSAPP)) {
			// Получаем число из настроек
			 whatsapp.setChecked(true);
			 btn_whatsapp.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_TWITTER)) {
			// Получаем число из настроек
			 twitter.setChecked(true);
			 btn_twitter.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_FACEBOOK)) {
			// Получаем число из настроек
			 facebook.setChecked(true);
			 btn_facebook.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_INSTAGRAM)) {
			// Получаем число из настроек
			 instagram.setChecked(true);
			 btn_instagram.setEnabled(true);}
       
       if (mSettings.contains(APP_PREFERENCES_ITUNES)) {
			// Получаем число из настроек
			appstore.setChecked(true);
			btn_appstore.setEnabled(true);}
        
	}
	
	private void email_idd(){
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		 Account[] accounts = AccountManager.get(this).getAccounts();
		 for (Account account : accounts) {
		     if (emailPattern.matcher(account.name).matches()) {
		         String possibleEmail = account.name;
		         acc.setText(possibleEmail);
		         
		     }
		 }
	}
	
	
	 //SYNC MODE
    boolean isMasterSyncEnabled = ContentResolver.getMasterSyncAutomatically();
    private void ButtonSync() {
    	if(isMasterSyncEnabled == true){
            tb_sync.setChecked(true);
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
            Intent it = new Intent(this, SettingsActivity.class);
            startActivity(it); 
 	        	overridePendingTransition(center_to_right, center_to_right2);
 	        	 }
        
   	
        public void BackClick(View v)  
        {  
        	Intent intent18 = new Intent(this, MainActivity.class);
	       	 startActivity(intent18);
	        	overridePendingTransition(center_to_right, center_to_right2);
       	 }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.synctoggle:
	        	if((tb_sync).isChecked()) {
              		 ContentResolver.setMasterSyncAutomatically(true);
              	 }
              	 else {
              		 ContentResolver.setMasterSyncAutomatically(false); 
              	 }
	    	break;
		
		case R.id.ToggleButtonMail:
			if(mail.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != mail_stok){
					String mail_app = "com.android.email";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_MAIL, mail_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsMail.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_mail.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("mail_app").apply();
                mail.setChecked(false);
                btn_mail.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonNotes:
			if(notes.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsNotes.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_notepad.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("notes_app").commit();
                notes.setChecked(false);
                btn_notepad.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonPhone:
			if(phone.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != phone_stok){
					String phone_app = "com.android.dialer";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_PHONE, phone_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsPhone.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_phone.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("phone_app").commit();
                phone.setChecked(false);
                btn_phone.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonMessage:
			if(messages.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != messages_stok){
				String messages_app = "com.android.mms";
				Editor editor = mSettings.edit();
			   	editor.putString(APP_PREFERENCES_MESSAGES, messages_app);
			   	editor.apply();
			}
			else {
			Intent intent = new Intent(this, ActivityAppsMessages.class);
	       	 startActivity(intent);
	        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_message.setEnabled(true);
    	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("messages_app").commit();
                messages.setChecked(false);
                btn_message.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonSafari:
			if(safari.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != safari_stok){
					String safari_app = "com.android.browser";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_SAFARI, safari_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsSafari.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_safari.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("safari_app").commit();
                safari.setChecked(false);
                btn_safari.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonMusic:
			if(music.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != music_stok){
					String music_app = "com.android.music";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_MUSIC, music_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsMusic.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_music.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("music_app").commit();
                music.setChecked(false);
                btn_music.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonGame:
			if(game.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsGameCenter.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_game.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("gamecenter_app").commit();
                game.setChecked(false);
                btn_game.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonWeather:
			if(weather.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsWeather.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_weather.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("weather_app").commit();
                weather.setChecked(false);
                btn_weather.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonCompass:
			if(compass.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				Intent intent = new Intent(this, ActivityAppsCompass.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	btn_compass.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("compass_app").commit();
                compass.setChecked(false);
                btn_compass.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonMaps:
			if(maps.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != maps_stok){
					String maps_app = "com.google.android.apps.maps";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_MAPS, maps_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsMaps.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_maps.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("maps_app").commit();
                maps.setChecked(false);
                btn_maps.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonVk:
			if(vk.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != vk_stok){
					String vk_app = "com.vkontakte.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_VK, vk_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsVk.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_vk.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("vk_app").commit();
                vk.setChecked(false);
                btn_vk.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonViber:
			if(viber.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != viber_stok){
					String viber_app = "com.viber.voip";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_VIBER, viber_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsViber.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_viber.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("viber_app").commit();
                viber.setChecked(false);
                btn_viber.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonOk:
			if(ok.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != ok_stok){
					String ok_app = "ru.ok.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_OK, ok_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsOk.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_ok.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("ok_app").commit();
                ok.setChecked(false);
                btn_ok.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonSkype:
			if(skype.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != skype_stok){
					String skype_app = "com.skype.raider";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_SKYPE, skype_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsSkype.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_skype.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("skype_app").commit();
                skype.setChecked(false);
                btn_skype.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonWhatsApp:
			if(whatsapp.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != whatsapp_stok){
					String whatsapp_app = "com.whatsapp";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_WHATSAPP, whatsapp_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsWhatsapp.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_whatsapp.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("whatsapp_app").commit();
                whatsapp.setChecked(false);
                btn_whatsapp.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonTwitter:
			if(twitter.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != twitter_stok){
					String twitter_app = "com.twitter.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_TWITTER, twitter_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsTwitter.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_twitter.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("twitter_app").commit();
                twitter.setChecked(false);
                btn_twitter.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonFacebook:
			if(facebook.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != facebook_stok){
					String facebook_app = "com.facebook.katana";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_FACEBOOK, facebook_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsFacebook.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_facebook.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("facebook_app").commit();
                facebook.setChecked(false);
                btn_facebook.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonInstagram:
			if(instagram.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != instagram_stok){
					String instagram_app = "com.instagram.android";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_INSTAGRAM, instagram_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsInstagram.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_instagram.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove("instagram_app").commit();
                instagram.setChecked(false);
                btn_instagram.setEnabled(false);
			}
			break;
			
		case R.id.ToggleButtonAppstore:
			if(appstore.isChecked())//means this is the request to turn ON AIRPLANE mode
        	{
				if (null != appstore_stok){
					String mail_app = "com.android.vending";
					Editor editor = mSettings.edit();
				   	editor.putString(APP_PREFERENCES_ITUNES, mail_app);
				   	editor.apply();
				}
				else {
				Intent intent = new Intent(this, ActivityAppsiTunes.class);
		       	 startActivity(intent);
		        	overridePendingTransition(center_to_left, center_to_left2); }
				btn_appstore.setEnabled(true);
        	}
			else {
				Editor editor = mSettings.edit();
                editor.remove(APP_PREFERENCES_ITUNES).apply();
                appstore.setChecked(false);
                btn_appstore.setEnabled(false);
			}
			break;
			
		
		case R.id.Button01:
			Intent intent = new Intent(this, ActivityAppsMail.class);
	       	 startActivity(intent);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button15:
			Intent intent2 = new Intent(this, ActivityAppsNotes.class);
	       	 startActivity(intent2);
	        	overridePendingTransition(center_to_left, center_to_left2);	
	        	break;
		case R.id.Button14:
			Intent intent3 = new Intent(this, ActivityAppsPhone.class);
	       	 startActivity(intent3);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button13:
			Intent intent4 = new Intent(this, ActivityAppsMessages.class);
	       	 startActivity(intent4);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button12:
			Intent intent5 = new Intent(this, ActivityAppsSafari.class);
	       	 startActivity(intent5);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button11:
			Intent intent6 = new Intent(this, ActivityAppsMusic.class);
	       	 startActivity(intent6);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button10:
			Intent intent7 = new Intent(this, ActivityAppsGameCenter.class);
	       	 startActivity(intent7);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button09:
			Intent intent8 = new Intent(this, ActivityAppsWeather.class);
	       	 startActivity(intent8);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button08:
			Intent intent9 = new Intent(this, ActivityAppsCompass.class);
	       	 startActivity(intent9);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button07:
			Intent intent10 = new Intent(this, ActivityAppsMaps.class);
	       	 startActivity(intent10);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button06:
			Intent intent11 = new Intent(this, ActivityAppsVk.class);
	       	 startActivity(intent11);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button05:
			Intent intent12 = new Intent(this, ActivityAppsViber.class);
	       	 startActivity(intent12);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button04:
			Intent intent13 = new Intent(this, ActivityAppsOk.class);
	       	 startActivity(intent13);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button03:
			Intent intent14 = new Intent(this, ActivityAppsSkype.class);
	       	 startActivity(intent14);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button02:
			Intent intent15 = new Intent(this, ActivityAppsWhatsapp.class);
	       	 startActivity(intent15);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button18:
			Intent intent16 = new Intent(this, ActivityAppsTwitter.class);
	       	 startActivity(intent16);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button17:
			Intent intent17 = new Intent(this, ActivityAppsFacebook.class);
	       	 startActivity(intent17);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button16:
			Intent intent18 = new Intent(this, ActivityAppsInstagram.class);
	       	 startActivity(intent18);
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	break;
		case R.id.Button20:
			 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_ADD_ACCOUNT);
	        	startActivity(settingsIntent);
		        	overridePendingTransition(center_to_left, center_to_left2);
			break;
			
		case R.id.Button21:
			Intent intent18d = new Intent(this, ActivityAppsiTunes.class);
	       	 startActivity(intent18d);
	        	overridePendingTransition(center_to_left, center_to_left2);
			break;
			
		case R.id.iClouds:
			 pg1.setVisibility(View.VISIBLE);
			syncGoogleAccount();
        	textView3.setVisibility(View.GONE);

		}
	}

}
