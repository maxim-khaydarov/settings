package ua.mkh.settings.full;



import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
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
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.view.ViewPager.LayoutParams;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;




public class MainActivity extends Activity implements OnClickListener, SearchView.OnQueryTextListener,
SearchView.OnCloseListener, OnFocusChangeListener {
	
	
    
	WifiManager wifi;
	Button btn_avia, btn_wifi, btn_bluetooth, btn_sota, btn_operator, btn_osnova,
			btn_zvuki, btn_oboi, btn_gps, buttonBack, btn_notification, btn_control, 
			btn_disturb, btn_mail, btn_notes, btn_messages, btn_phone, 
			btn_safari, btn_music, btn_compass, btn_weather, btn_games, btn_menu_settings, 
			btn_menu_cancel, btn_passcode, btn_privacy, btn_new1, btn_new2, btn_new3,
			btn_new4, btn_vpn, btn_display, ButtonMenuCancel, ButtonMenuSettings,
			btn_iTunes, btn_iCloud, btn_maps, btn_vk, btn_viber, btn_ok, btn_skype, btn_whatsapp, 
			btn_twitter, btn_facebook, btn_instagram, btn_battery;
	
	ToggleButton tb_am;
	
	LinearLayout LinearLayoutWiFi, LinearLayoutBluetooth, LinearLayoutSotSvyaz, 
	LinearLayoutOperator, LinearLayoutGeo, LinearLayoutNotif, LinearLayoutControl,
	LinearLayoutMail, LinearLayoutNotes, LinearLayoutMessages, 
	LinearLayoutPhone, LinearLayoutSafari, LinearLayoutMusic, LinearLayoutCompass, 
	LinearLayoutWeather, LinearLayoutGameCenter, LinearLayoutNew1, LinearLayoutNew2,
	LinearLayoutNew3,LinearLayoutNew4, LinearLayoutPasscode, LinearLayoutPrivacy, LinearLayoutApn,
	LinearLayoutCloud, LinearLayoutTunes, LinearLayoutMaps, LinearLayoutVk, LinearLayoutViber, LinearLayoutOk, LinearLayoutSkype,
	LinearLayoutWhatsapp, LinearLayoutTwitter, LinearLayoutFacebook, LinearLayoutInstagram;
	
	
	TextView textwifi, textbt, text_app_main, TextOper, txt1, textView1, textView2, textView3, textVPN;
	private static final String MY_SETTINGS = "my_settings";
	private static final String MY_SETTINGS_CHANGELOG = "my_settings_changeLog";
	TelephonyManager manager;
	int OS = android.os.Build.VERSION.SDK_INT;
	
	boolean sear = false;
	
	String account_name;
	
	Intent notif_inoty, notif_espier, control_hi, control_espier, mail_ino, mail_stok, 
	notes_any, notes_espier, phone_kuandroid, phone_espier, phone_phone_stok, phone_stok,
	messages_easyandroid, messages_iphonestyle,	messages_espier, messages_stok, 
	safari_ios, safari_stok, music_easyandroid1, music_easyandroid2, music_hi, music_stok, compass_hanimobile, weather_cybob,
	weather_yahoo, weather_kuandroid, games_mkh, new1,new2, new3, new4, vk_stok, maps_stok, viber_stok,
	ok_stok, skype_stok, whatsapp_stok, twitter_stok, facebook_stok, instagram_stok;
	

	   public static final String APP_PREFERENCES = "mysettings"; 
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
	   public static final String APP_PREFERENCES_tgb_apn = "tgb_apn";
	   public static final String APP_PREFERENCES_tgb_passcode = "tgb_passcode";
	   public static final String APP_PREFERENCES_tgb_privacy = "tgb_privacy";
	   public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ICLOUD = "icloud_app";
	   public static final String APP_PREFERENCES_ITUNES = "itunes_app";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   public static final String APP_PREFERENCES_NETWORK = "networkText";
	   public static final String APP_PREFERENCES_ACTIVATION = "activation";
	   public static final String APP_PREFERENCES_UPDATE = "update";
	   
	   public static final String APP_PREFERENCES_DATABASE = "ver_database";
	   
	   int menui = 0;
	   int versionCode;
	   
	   int task = 0;
	   
	   
	   private static String url = "http://web-server-mkh.ucoz.ua/login.txt";
	   
	   String versionName;

		 String idd;
		 int VerIntent;
		 
		 private static final String TAG_CONTACTS = "version";
		    private static final String TAG_ID = "id";
		    
		 
		    // contacts JSONArray
		    JSONArray version = null;
		    ArrayList<HashMap<String, String>> contactList;
	   
		    int center_to_right = R.anim.slide_center_to_right_short, center_to_right2 = R.anim.slide_center_to_right2_short;
			   int center_to_left = R.anim.slide_center_to_left_short, center_to_left2 = R.anim.slide_center_to_left2_short;
	  
	   SharedPreferences mSettings;
	   String music_app = null, mail_app = null, notes_app = null, phone_app = null, messages_app = null, safari_app = null, 
	   gamecenter_app = null, weather_app = null, compass_app = null, notifications_app = null, control_app = null, 
	   new1_app = null, new2_app = null, new3_app = null, new4_app = null, itunes_app = null, icloud_app = null, maps_app = null,
	   vk_app = null, viber_app = null, ok_app = null, skype_app = null, whatsapp_app = null, twitter_app = null, facebook_app = null,
	   instagram_app = null;
	   
	  
	   String versionNow;
	   
	   Dialog dialog;
	   String possibleEmail;
	   
	   String YA = null, G = null;
	   String svoi = null;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;

	   
	   private static final String cPASS = null;
	   private static final String cPRIVACY = null;
	   private static final String cAPN = null;
	   
	   private final static String NETWORK="networktext.txt";


	   Animation animationFalling;
	   
	   PackageManager pm;
	   BluetoothAdapter bt;
	   
	   private GetContacts mTask;
	   
	   
	   EditText ed1, ed11;
	   LinearLayout ll1, ll11;
	   private ListView mListView;
	    private SearchView searchView;
	    private CustomersDbAdapter mDbHelper;
	    String base;
	    
	    Button b1, b2, b11, b21;
	   
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		//try {
		String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String thin =  "fonts/Thin.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		
		
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		
		ll1 = (LinearLayout) findViewById(R.id.ll1);
		ll1.requestFocus();
		searchView = (SearchView) findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
 
        mListView = (ListView) findViewById(R.id.list);
		
        
       
        
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b11 = (Button) findViewById(R.id.button11);
		b21 = (Button) findViewById(R.id.button21);
		ed1 = (EditText) findViewById(R.id.EditText01);
		ed11 = (EditText) findViewById(R.id.EditText011);
		
		b21.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				b11.setVisibility(View.GONE);
				b21.setVisibility(View.GONE);
				ed11.clearFocus();
				ed1.clearFocus();
				InputMethodManager imm = (InputMethodManager)getSystemService(
					      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
				ed11.setText("");
				ed1.setText("");
				LinearLayout list_res_top = (LinearLayout) findViewById(R.id.list_res_top);
            	list_res_top.setVisibility(View.GONE);
            	LinearLayout list_res = (LinearLayout) findViewById(R.id.list_res);
            	list_res.setVisibility(View.GONE);
            	ll1.setVisibility(View.VISIBLE);
            	sear = false;
			}
		});

	    ed1.setOnFocusChangeListener(new OnFocusChangeListener() {

	        @Override
	        public void onFocusChange(View v, boolean hasFocus) {
	            if (hasFocus) {
	            	b11.setVisibility(View.VISIBLE);
	            	b21.setVisibility(View.VISIBLE);
	            	LinearLayout list_res_top = (LinearLayout) findViewById(R.id.list_res_top);
	            	list_res_top.setVisibility(View.VISIBLE);
	            	LinearLayout list_res = (LinearLayout) findViewById(R.id.list_res);
	            	list_res.setVisibility(View.VISIBLE);
	            	sear = true;
	            	
	            	
	            	//ll1.setVisibility(View.INVISIBLE);
	            	
	            	ed11.requestFocus();
	            	ed1.clearFocus();
	            	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	            	imm.showSoftInput(ed11, InputMethodManager.SHOW_IMPLICIT);
	            	//LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	            	//lp.setMargins(0, 0, 0, 0);
	            	//ll1.setLayoutParams(lp);

	                
	            }
	            else {
	            	ll1.setVisibility(View.GONE);
	            	b11.setVisibility(View.VISIBLE);
	            	LinearLayout list_res = (LinearLayout) findViewById(R.id.list_res);
	            	list_res.setVisibility(View.VISIBLE);
	            	//LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	            	//lp.setMargins(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 39, getResources().getDisplayMetrics()), 0, 0);
	            	//ll1.setLayoutParams(lp);
	            }
	        }
	    });
		
		ed11.addTextChangedListener(new TextWatcher() {
		     
		    @Override
		    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		        // When user changed the Text
		    	searchView.setQuery(cs, false);
		    	mListView.setVisibility(View.VISIBLE);
		    	
		    }
		     
		    @Override
		    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
		            int arg3) {
		        
		         
		    }
		     
		    @Override
		    public void afterTextChanged(Editable arg0) {
		                                  
		    }
		});
		
			
				
			
        
        	mDbHelper = new CustomersDbAdapter(this);
        	mDbHelper.open();
        
        
        /*
 
        //Clean all Customers
        mDbHelper.deleteAllCustomers();
        
	*/
        //////////////////////
		
		LinearLayoutWiFi = (LinearLayout)findViewById(R.id.LinearLayoutWiFi);
		LinearLayoutBluetooth = (LinearLayout)findViewById(R.id.LinearLayoutBluetooth);
		LinearLayoutSotSvyaz = (LinearLayout)findViewById(R.id.LinearLayoutSotSvyaz);
		LinearLayoutOperator = (LinearLayout)findViewById(R.id.LinearLayoutOperator);
		LinearLayoutGeo = (LinearLayout)findViewById(R.id.LinearLayoutGeo);
		LinearLayoutNotif = (LinearLayout)findViewById(R.id.LinearLayoutNotif);
		LinearLayoutControl = (LinearLayout)findViewById(R.id.LinearLayoutControl);
		LinearLayoutMail = (LinearLayout)findViewById(R.id.LinearLayoutMail);
		LinearLayoutNotes = (LinearLayout)findViewById(R.id.LinearLayoutNotes);
		LinearLayoutMessages = (LinearLayout)findViewById(R.id.LinearLayoutMessages);
		LinearLayoutPhone = (LinearLayout)findViewById(R.id.LinearLayoutPhone);
		LinearLayoutSafari = (LinearLayout)findViewById(R.id.LinearLayoutSafari);
		LinearLayoutMusic = (LinearLayout)findViewById(R.id.LinearLayoutMusic);
		LinearLayoutCompass = (LinearLayout)findViewById(R.id.LinearLayoutCompass);
		LinearLayoutWeather = (LinearLayout)findViewById(R.id.LinearLayoutWeather);
		LinearLayoutGameCenter = (LinearLayout)findViewById(R.id.LinearLayoutGameCenter);
		LinearLayoutPasscode = (LinearLayout)findViewById(R.id.LinearLayoutPasscode);
		LinearLayoutPrivacy = (LinearLayout)findViewById(R.id.LinearLayoutPrivacy);
		LinearLayoutApn = (LinearLayout)findViewById(R.id.LinearLayoutApn);
		LinearLayoutCloud = (LinearLayout) findViewById(R.id.LinearLayoutCloud);
		LinearLayoutTunes = (LinearLayout) findViewById(R.id.LinearLayoutTunes);
		LinearLayoutMaps = (LinearLayout) findViewById(R.id.LinearLayoutMaps);
		LinearLayoutVk = (LinearLayout) findViewById(R.id.LinearLayoutVk);
		LinearLayoutViber = (LinearLayout) findViewById(R.id.LinearLayoutViber);
		LinearLayoutOk = (LinearLayout) findViewById(R.id.LinearLayoutOK);
		LinearLayoutSkype = (LinearLayout) findViewById(R.id.LinearLayoutSkype);
		LinearLayoutWhatsapp = (LinearLayout) findViewById(R.id.LinearLayoutWhatsApp);
		LinearLayoutTwitter = (LinearLayout) findViewById(R.id.LinearLayoutTwitter);
		LinearLayoutFacebook = (LinearLayout) findViewById(R.id.LinearLayoutFacebook);
		LinearLayoutInstagram = (LinearLayout) findViewById(R.id.LinearLayoutInstagram);
		
		
		LinearLayoutNew1 = (LinearLayout)findViewById(R.id.LinearLayoutApp1);
		LinearLayoutNew2 = (LinearLayout)findViewById(R.id.LinearLayoutApp2);
		LinearLayoutNew3 = (LinearLayout)findViewById(R.id.LinearLayoutApp3);
		LinearLayoutNew4 = (LinearLayout)findViewById(R.id.LinearLayoutApp4);
		
		LinearLayoutNew1.setVisibility(View.GONE);
		LinearLayoutNew2.setVisibility(View.GONE);
		LinearLayoutNew3.setVisibility(View.GONE);
		LinearLayoutNew4.setVisibility(View.GONE);
		
		LinearLayoutGeo.setVisibility(View.GONE);
		LinearLayoutMail.setVisibility(View.GONE);
		LinearLayoutNotes.setVisibility(View.GONE);
		LinearLayoutMessages.setVisibility(View.GONE);
		LinearLayoutPhone.setVisibility(View.GONE);
		LinearLayoutSafari.setVisibility(View.GONE);
		LinearLayoutMusic.setVisibility(View.GONE);
		LinearLayoutCompass.setVisibility(View.GONE);
		LinearLayoutWeather.setVisibility(View.GONE);
		LinearLayoutGameCenter.setVisibility(View.GONE);
		LinearLayoutNotif.setVisibility(View.GONE);
		LinearLayoutControl.setVisibility(View.GONE);
		LinearLayoutTunes.setVisibility(View.GONE);
		LinearLayoutMaps.setVisibility(View.GONE);
		LinearLayoutVk.setVisibility(View.GONE);
		LinearLayoutViber.setVisibility(View.GONE);
		LinearLayoutOk.setVisibility(View.GONE);
		LinearLayoutSkype.setVisibility(View.GONE);
		LinearLayoutWhatsapp.setVisibility(View.GONE);
		LinearLayoutTwitter.setVisibility(View.GONE);
		LinearLayoutFacebook.setVisibility(View.GONE);
		LinearLayoutInstagram.setVisibility(View.GONE);
			
		textwifi = (TextView)findViewById(R.id.textwifi);
		textbt = (TextView) findViewById(R.id.textbt);
		TextOper = (TextView)findViewById(R.id.TextOper);
		textVPN = (TextView) findViewById(R.id.TextView01);
		
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		btn_avia = (Button) findViewById(R.id.Button01);
		tb_am = (ToggleButton)findViewById(R.id.ToggleButton01);
		tb_am.setOnClickListener(this);
		
		btn_wifi = (Button) findViewById(R.id.ButtonWifi);
		btn_wifi.setOnClickListener(this);
		
		btn_bluetooth = (Button) findViewById(R.id.ButtonBluetooth);
		btn_bluetooth.setOnClickListener(this);
		
		btn_sota = (Button) findViewById(R.id.ButtonSota);
		btn_sota.setOnClickListener(this);
		
		btn_operator = (Button) findViewById(R.id.ButtonOperator);
		btn_operator.setOnClickListener(this);
		
		btn_osnova = (Button) findViewById(R.id.ButtonOsnova);
		btn_osnova.setOnClickListener(this);
		
		btn_zvuki = (Button) findViewById(R.id.ButtonZvuki);
		btn_zvuki.setOnClickListener(this);
		
		btn_oboi = (Button) findViewById(R.id.ButtonOboi);
		btn_oboi.setOnClickListener(this);
		
		btn_gps = (Button) findViewById(R.id.ButtonGeo);
		btn_gps.setOnClickListener(this);
		
		btn_passcode = (Button)findViewById(R.id.ButtonPasscode); 
		btn_passcode.setOnClickListener(this);
		
		btn_battery = (Button)findViewById(R.id.ButtonBattery); 
		btn_battery.setOnClickListener(this);
		
		btn_privacy = (Button)findViewById(R.id.ButtonPrivacy); 
		btn_privacy.setOnClickListener(this);
		
		btn_notification = (Button)findViewById(R.id.ButtonNotification);
		btn_notification.setOnClickListener(this);
		
		btn_control = (Button)findViewById(R.id.ButtonControl);
		btn_control.setOnClickListener(this);
		
		btn_disturb = (Button)findViewById(R.id.ButtonDisturb);
		btn_disturb.setOnClickListener(this);
		
		btn_mail = (Button)findViewById(R.id.ButtonMail);
		btn_mail.setOnClickListener(this);
		
		btn_notes = (Button)findViewById(R.id.ButtonNotes);
		btn_notes.setOnClickListener(this);
		
		btn_messages = (Button)findViewById(R.id.ButtonMessages);
		btn_messages.setOnClickListener(this);
		
		btn_phone = (Button)findViewById(R.id.ButtonPhone);
		btn_phone.setOnClickListener(this);
		
		btn_safari = (Button)findViewById(R.id.ButtonSafari);
		btn_safari.setOnClickListener(this);
		
		btn_music = (Button)findViewById(R.id.ButtonMusic);
		btn_music.setOnClickListener(this);
		
		btn_compass = (Button)findViewById(R.id.ButtonCompass);
		btn_compass.setOnClickListener(this);
		
		btn_weather = (Button)findViewById(R.id.ButtonWeather);
		btn_weather.setOnClickListener(this);
		
		btn_games  = (Button)findViewById(R.id.ButtonGameCenter);
		btn_games.setOnClickListener(this);
		
		btn_maps = (Button)findViewById(R.id.ButtonMaps);
		btn_maps.setOnClickListener(this);
		
		btn_vk = (Button)findViewById(R.id.ButtonVk);
		btn_vk.setOnClickListener(this);
		
		btn_viber = (Button)findViewById(R.id.ButtonViber);
		btn_viber.setOnClickListener(this);
		
		btn_ok= (Button)findViewById(R.id.ButtonOk);
		btn_ok.setOnClickListener(this);
		
		btn_skype = (Button)findViewById(R.id.ButtonSkype);
		btn_skype.setOnClickListener(this);
		
		btn_whatsapp= (Button)findViewById(R.id.ButtonWhatsapp);
		btn_whatsapp.setOnClickListener(this);
		
		btn_twitter = (Button)findViewById(R.id.ButtonTwitter);
		btn_twitter.setOnClickListener(this);
		
		btn_facebook = (Button)findViewById(R.id.ButtonFacebook);
		btn_facebook.setOnClickListener(this);
		
		btn_instagram = (Button)findViewById(R.id.ButtonInstagram);
		btn_instagram.setOnClickListener(this);
		
		btn_new1 = (Button)findViewById(R.id.Button02);
		btn_new1.setOnClickListener(this);
		
		btn_new2 = (Button)findViewById(R.id.Button03);
		btn_new2.setOnClickListener(this);
		
		btn_new3 = (Button)findViewById(R.id.Button04);
		btn_new3.setOnClickListener(this);
		
		btn_new4 = (Button)findViewById(R.id.Button05);
		btn_new4.setOnClickListener(this);
		
		btn_vpn  = (Button)findViewById(R.id.Button06);
		btn_vpn.setOnClickListener(this);
		
		btn_display = (Button)findViewById(R.id.ButtonDisplay);
		btn_display.setOnClickListener(this);
		
		btn_iCloud = (Button)findViewById(R.id.ButtonCloud);
		btn_iCloud.setOnClickListener(this);
		
		btn_iTunes = (Button)findViewById(R.id.ButtonTunes);
		btn_iTunes.setOnClickListener(this);
		
		
		buttonBack = (Button) findViewById(R.id.buttonBack);
		btn_menu_settings = (Button) findViewById(R.id.ButtonMenuSettings);
		btn_menu_cancel = (Button) findViewById(R.id.ButtonMenuCancel);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		
		text_app_main = (TextView)findViewById(R.id.text_app_main);
		
		text_app_main.setText(R.string.text_app_name);
		
		ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
    	imageView2.setVisibility(View.GONE);
		
    	 
    	text_app_main.setTypeface(typefaceBold);
		btn_avia.setTypeface(typefaceRoman);
		//textView1.setTypeface(typefaceRoman);
		textView3.setTypeface(typefaceRoman);
		btn_wifi.setTypeface(typefaceRoman);
		btn_bluetooth.setTypeface(typefaceRoman);
		btn_sota.setTypeface(typefaceRoman);
		btn_operator.setTypeface(typefaceRoman);
		btn_osnova.setTypeface(typefaceRoman);
		btn_passcode.setTypeface(typefaceRoman);
		btn_battery.setTypeface(typefaceRoman);
		btn_privacy.setTypeface(typefaceRoman);
		btn_zvuki.setTypeface(typefaceRoman);
		btn_oboi.setTypeface(typefaceRoman);
		btn_notification.setTypeface(typefaceRoman);
		btn_control.setTypeface(typefaceRoman);
		btn_disturb.setTypeface(typefaceRoman);
		btn_gps.setTypeface(typefaceRoman);	
		btn_mail.setTypeface(typefaceRoman);	
		btn_notes.setTypeface(typefaceRoman);	
		btn_messages.setTypeface(typefaceRoman);	
		btn_phone.setTypeface(typefaceRoman);	
		btn_safari.setTypeface(typefaceRoman);	
		btn_music.setTypeface(typefaceRoman);	
		btn_compass.setTypeface(typefaceRoman);
		btn_weather.setTypeface(typefaceRoman);
		btn_games.setTypeface(typefaceRoman);
		btn_new1.setTypeface(typefaceRoman);
		btn_new2.setTypeface(typefaceRoman);
		btn_new3.setTypeface(typefaceRoman);
		btn_new4.setTypeface(typefaceRoman);
		btn_vpn.setTypeface(typefaceRoman);
		btn_display.setTypeface(typefaceRoman);
		btn_maps.setTypeface(typefaceRoman);
		btn_vk.setTypeface(typefaceRoman);
		btn_viber.setTypeface(typefaceRoman);
		btn_ok.setTypeface(typefaceRoman);
		btn_skype.setTypeface(typefaceRoman);
		btn_whatsapp.setTypeface(typefaceRoman);
		btn_twitter.setTypeface(typefaceRoman);
		btn_facebook.setTypeface(typefaceRoman);
		btn_instagram.setTypeface(typefaceRoman);
		
		
		btn_iCloud.setTypeface(typefaceRoman);
		btn_iTunes.setTypeface(typefaceRoman);
		
		
		textwifi.setTypeface(typefaceRoman);
		textbt.setTypeface(typefaceRoman);
		TextOper.setTypeface(typefaceRoman);
		textVPN.setTypeface(typefaceRoman);
		
	
		//LinearLayoutPrivacy.setVisibility(View.GONE);
		//LinearLayoutApn.setVisibility(View.GONE);
		//LinearLayoutPasscode.setVisibility(View.GONE);
		//layoutTunes.setVisibility(View.GONE);
		
      		
	//////////////////START///////////////////	
		 
		
		
		

	        
	    ///////////////////APP//////////////
		pm = this.getPackageManager();
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
		
	    	
	    	bt=BluetoothAdapter.getDefaultAdapter();
	    	//Check Availability of bluetooth
	    	if(bt==null)
	    	{
	    		LinearLayoutBluetooth.setVisibility(View.GONE);
	    	}
		}
		/*catch (Exception e){
			 StringWriter sw = new StringWriter();
             e.printStackTrace(new PrintWriter(sw));
             String stacktrace = sw.toString();
 
             // CREATE AN EMAIL INTENT TO SEND TO YOURSELF
             final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
             emailIntent.setType("plain/text");
             emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "maxim.khaydarov@yandex.ru" });
             emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "On Create BUG REPORT");
             emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, stacktrace);
 
             // START THE EMAIL ACTIVITY - NOTE YOU NEED TO START IT WITH A CHOOSER
             startActivity(Intent.createChooser(emailIntent, "Send error report..."));
		}

	}
	 */
	
	
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
		
	   	editor.apply();
	}
   
	       
	private void openDialog(){
	     final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent);
	     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     dialog.setContentView(R.layout.dialog_menu);
	     dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	     Button ButtonInfo = (Button)dialog.getWindow().findViewById(R.id.button1);
	     ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuCancel);
	     ButtonMenuSettings = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuSettings);
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
		Intent intent = new Intent(this, SettingsActivity.class);
   	 startActivity(intent);
    }
	

	 public void onClick(View v) {
		 int id = v.getId();
		 
		 if (id == R.id.ButtonWifi){
	        	 Intent intent = new Intent(this, ActivityWifi.class);
	        	 startActivity(intent);
		 	        	overridePendingTransition(center_to_left, center_to_left2);
		 	        	 }
	        	 
		 else if (id == R.id.ButtonBluetooth){
	        	Intent intent1 = new Intent(this, ActivityBth.class);
	        	 startActivity(intent1);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        	 
		 else if (id == R.id.ButtonSota){
	        	Intent intent2 = new Intent(this, ActivitySota.class);
	        	 startActivity(intent2);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        	 
		 else if (id == R.id.ButtonDisturb){
	        	Intent intent11 = new Intent(this, ActivityDisturb.class);
	        	 startActivity(intent11);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
		 
		 else if (id == R.id.ButtonOperator){
			
			 Intent settingsIntent = new Intent(this, ActivityOperator.class);
	        	startActivity(settingsIntent);
		 	        	overridePendingTransition(center_to_left, center_to_left2);
		 	        	
		 }
	        
		 else if (id == R.id.ButtonZvuki){
	        	Intent intent4 = new Intent(this, ActivityZvuki.class);
	        	 startActivity(intent4);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        	 
		 else if (id == R.id.ButtonOboi){
	        	Intent intent5 = new Intent(this, ActivityOboi.class);
	        	//intent5.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
	        	 startActivity(intent5);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        	 
		 else if (id == R.id.ButtonOsnova){
	        	Intent intent6 = new Intent(this, ActivityOsnova.class);
	        	intent6.putExtra("verintent", VerIntent);
	        	 startActivity(intent6);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
		 
		 else if (id == R.id.ButtonPasscode){
			 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS);
	        	startActivity(settingsIntent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 else if (id == R.id.ButtonBattery){
			 Intent intent4 = new Intent(this, ActivityBattery.class);
        	 startActivity(intent4);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonPrivacy){
			 Intent settingsIntent = new Intent(android.provider.Settings.ACTION_PRIVACY_SETTINGS);
	        	startActivity(settingsIntent);
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	 
		 else if (id == R.id.ToggleButton01){
	        	if(tb_am.isChecked())//means this is the request to turn ON AIRPLANE mode
	        	{
	        		if (OS > 16) {
	        			Intent settingsIntent = new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS);
	    	        	startActivity(settingsIntent);
	    		        	overridePendingTransition(center_to_left, center_to_left2);
	    		        	 }
	        		else {
	        		Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 1);//Turning ON Airplane mode.
	        		Intent intent10 = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);//creating intent and Specifying action for AIRPLANE mode.
	                intent10.putExtra("state", true);////indicate the "state" of airplane mode is changed to ON
	                sendBroadcast(intent10);//Broadcasting and Intent
	                //
	                btn_sota.setTextColor(Color.parseColor("#808080"));
	            	btn_operator.setTextColor(Color.parseColor("#808080"));
	            	btn_gps.setTextColor(Color.parseColor("#808080"));
	            	btn_sota.setClickable(false);
	            	btn_operator.setClickable(false);
	            	btn_gps.setClickable(false);
	        	} }
	        	else//means this is the request to turn OFF AIRPLANE mode
	        	{
	        		if (OS > 16) {
	        			Intent settingsIntent = new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS);
	    	        	startActivity(settingsIntent);
	    		        	overridePendingTransition(center_to_left, center_to_left2);
	    		        	 }
	        		else {
	        		Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 0);//Turning OFF Airplane mode.
	        		Intent intent9 = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);//creating intent and Specifying action for AIRPLANE mode.
	                intent9.putExtra("state", false);//indicate the "state" of airplane mode is changed to OFF
	                sendBroadcast(intent9);//Broadcasting and Intent
	                //
	                btn_sota.setTextColor(Color.parseColor("#000000"));
	            	btn_operator.setTextColor(Color.parseColor("#000000"));
	            	btn_gps.setTextColor(Color.parseColor("#000000"));
	            	btn_sota.setClickable(true);
	            	btn_operator.setClickable(true);
	            	btn_gps.setClickable(true);
	        	} }
		 }
	        	
		 else if (id == R.id.ButtonGeo){
			 
	        	Intent intent8 = new Intent(this, ActivityGeo.class);
	        	 startActivity(intent8);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	 	        	 }
	        	 
		 else if (id == R.id.ButtonNotification){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(notifications_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if (id == R.id.ButtonControl){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(control_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if (id == R.id.ButtonMail){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(mail_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if (id == R.id.ButtonNotes){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(notes_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if (id == R.id.ButtonPhone){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(phone_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
	        	
		 else if (id == R.id.ButtonMessages){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(messages_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if (id == R.id.ButtonSafari){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(safari_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if (id == R.id.ButtonMusic){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(music_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if ( id == R.id.ButtonCompass){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(compass_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	        	
		 else if (id == R.id.ButtonWeather){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(weather_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	 
		 else if (id == R.id.ButtonGameCenter){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(gamecenter_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonMaps){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(maps_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.Button02){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(new1_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.Button03){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(new2_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.Button04){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(new3_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.Button05){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(new4_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonCloud){
			 Intent intent10 = new Intent(this, ActivityiCloud.class);
        	 startActivity(intent10);
 	        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonTunes){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(itunes_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.Button06){
			 Intent intent10 = new Intent(this, ActivityVPN.class);
        	 startActivity(intent10);
 	        	overridePendingTransition(center_to_left, center_to_left2);
 	        	}
		 
		 else if (id == R.id.ButtonDisplay){
			 Intent intent12 = new Intent(this, ActivityBrightness.class);
        	 startActivity(intent12);
 	        	overridePendingTransition(center_to_left, center_to_left2);
 	        	 }
		 
		 else if (id == R.id.ButtonVk){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(vk_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonViber){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(viber_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonOk){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(ok_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonSkype){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(skype_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonWhatsapp){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(whatsapp_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonTwitter){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(twitter_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonFacebook){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(facebook_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
		 
		 else if (id == R.id.ButtonInstagram){
			 Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(instagram_app);
			 startActivity( LaunchIntent );
		        	overridePendingTransition(center_to_left, center_to_left2);
		        	 }
	 }
	
	 
	 public static boolean isSharingWiFi(final WifiManager manager)
	    {
	        try
	        {
	            final Method method = manager.getClass().getDeclaredMethod("isWifiApEnabled");
	            method.setAccessible(true); //in the case of visibility change in future APIs
	            return (Boolean) method.invoke(manager);
	        }
	        catch (final Throwable ignored)
	        {
	        }

	        return false;
	    }
	
	 
	 
	 
	 
	 
	 protected void onResume() {
	        super.onResume();
	       // try{
	        ButtonTextWifi();
	        Airmode();
	        ButtonTextBth();
	        operator();
	        check_pirat();
	        zimowets();
	       
	       
	        ConnectivityManager conMgr  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
			 NetworkInfo info = conMgr.getActiveNetworkInfo(); 

			if(info != null && info.isConnected()) 
			{
				mTask = new GetContacts();
				mTask.execute();
			}
	        	

	        	
	        
	       
	       
	        
	        /////////////////Проверка есть ли аккаунт Google
			try{
	        if (account_name.length() == 0) {
        		btn_iCloud.setText(R.string.icloud);
        		//textView2.setText("");
        		//textView1.setText("");
				}
	        else{
	        	String t2 = getString(R.string.icloud) + "<br />" + "<font color=\"#808080\" >" + "<small><small>" + account_name +  "</small></small>" + "</font>";
	        	btn_iCloud.setText(Html.fromHtml(t2), TextView.BufferType.SPANNABLE);
	        }
			}catch(NullPointerException e){
				btn_iCloud.setText(R.string.icloud);
			}
	        
	        if (isSharingWiFi(wifi) == true){
	        	textVPN.setText(R.string.on);
	        }
	        else {
	        	textVPN.setText(R.string.off);
	        }
	        
	        ////Start page
	        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, 
	                Context.MODE_PRIVATE);
	        // проверяем, первый ли раз открывается программа
	        boolean hasVisited = sp.getBoolean("hasVisited", false);
			
	        if (!hasVisited) {
	            // выводим нужную активность
	        	 Intent intent = new Intent(this, ActivityStart.class);
	        	 startActivity(intent);
	            Editor e = sp.edit();
	            e.putBoolean("hasVisited", true);
	            e.apply(); // не забудьте подтвердить изменения
	            stok();
	        }
	//////////////////////////////////////        ////////
	        
	        
	        
	        
////ChangeLog
	        
	        PackageInfo pInfo;
	        
			try {
				pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
				 versionNow = pInfo.versionName;
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	       
			Log.d("HEY!", versionNow);
			
	       String changeLog = sp.getString("version", "1.0");
	       
	       if (changeLog.contains(versionNow)){
	    	   
	       }
	       else {
	    	   Handler handler = new Handler(); 
	    	    handler.postDelayed(new Runnable() { 
	    	         public void run() { 
	    	              update(); 
	    	         } 
	    	    }, 500); 
	    	   Editor e = sp.edit();
	            e.putString("version", versionNow);
	            e.apply(); // не забудьте подтвердить изменения
	            
	       }
	       
	       
	        
	       
	   /////DATABASE     
	        	 String DATA = mSettings.getString(APP_PREFERENCES_DATABASE, "0");
	        	 if(DATA.contains(getString(R.string.ver_database))){
	        		 
	        	 }
	        	 else{
	        		 copyDataBase();
	        		 Editor editor = mSettings.edit();
	        		 editor.putString(APP_PREFERENCES_DATABASE, getString(R.string.ver_database));
					  editor.commit();
	        	 }
				

	        	
	      
	        
	       if (mSettings.contains(APP_PREFERENCES_NETWORK)) {
				// Получаем число из настроек
	        	 String network = mSettings.getString(APP_PREFERENCES_NETWORK, null);
	        	 TextOper.setText(network);
	        	 ImageView ImageView07 = (ImageView)findViewById(R.id.ImageView07);
		        	ImageView07.setVisibility(View.VISIBLE);
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
	        
	       
	        
	        
	        if (mSettings.contains(APP_PREFERENCES_MAIL)) {
				// Получаем число из настроек
				mail_app = mSettings.getString(APP_PREFERENCES_MAIL, null);
				LinearLayoutMail.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_NOTES)) {
				// Получаем число из настроек
				notes_app = mSettings.getString(APP_PREFERENCES_NOTES, null);
				LinearLayoutNotes.setVisibility(View.VISIBLE);}

	        
	        
	        if (mSettings.contains(APP_PREFERENCES_PHONE)) {
				// Получаем число из настроек
				phone_app = mSettings.getString(APP_PREFERENCES_PHONE, null);
				LinearLayoutPhone.setVisibility(View.VISIBLE);}

	        
	        
	        if (mSettings.contains(APP_PREFERENCES_MESSAGES)) {
				// Получаем число из настроек
				messages_app = mSettings.getString(APP_PREFERENCES_MESSAGES, null);
				LinearLayoutMessages.setVisibility(View.VISIBLE);}

	        
	        
	        if (mSettings.contains(APP_PREFERENCES_SAFARI)) {
				// Получаем число из настроек
				safari_app = mSettings.getString(APP_PREFERENCES_SAFARI, null); 
				LinearLayoutSafari.setVisibility(View.VISIBLE);}

	        
	        
	        if (mSettings.contains(APP_PREFERENCES_MUSIC)) {
				// Получаем число из настроек
				music_app = mSettings.getString(APP_PREFERENCES_MUSIC, null); 
				LinearLayoutMusic.setVisibility(View.VISIBLE);}

	        
	        
	        if (mSettings.contains(APP_PREFERENCES_GAMECENTER)) {
				// Получаем число из настроек
				gamecenter_app = mSettings.getString(APP_PREFERENCES_GAMECENTER, null);
				LinearLayoutGameCenter.setVisibility(View.VISIBLE);}

	        
	        
	        if (mSettings.contains(APP_PREFERENCES_WEATHER)) {
				// Получаем число из настроек
				weather_app = mSettings.getString(APP_PREFERENCES_WEATHER, null);
				LinearLayoutWeather.setVisibility(View.VISIBLE);}

	        
	        
	        if (mSettings.contains(APP_PREFERENCES_COMPASS)) {
				// Получаем число из настроек
				compass_app = mSettings.getString(APP_PREFERENCES_COMPASS, null); 
				LinearLayoutCompass.setVisibility(View.VISIBLE); }
	        
	        if (mSettings.contains(APP_PREFERENCES_MAPS)) {
				// Получаем число из настроек
				maps_app = mSettings.getString(APP_PREFERENCES_MAPS, null);
				LinearLayoutMaps.setVisibility(View.VISIBLE);}
	        
	        
	        if (mSettings.contains(APP_PREFERENCES_NOTIFICATIONS)) {
				// Получаем число из настроек
				notifications_app = mSettings.getString(APP_PREFERENCES_NOTIFICATIONS, null); 
				LinearLayoutNotif.setVisibility(View.VISIBLE); }
	        
	        if (mSettings.contains(APP_PREFERENCES_CONTROL)) {
				// Получаем число из настроек
				control_app = mSettings.getString(APP_PREFERENCES_CONTROL, null); 
				LinearLayoutControl.setVisibility(View.VISIBLE); }
	        /*
	        if (mSettings.contains(APP_PREFERENCES_ICLOUD)) {
				// Получаем число из настроек
				icloud_app = mSettings.getString(APP_PREFERENCES_ICLOUD, null); }
	        else {
	        	btn_iCloud.setEnabled(false);
	        	ImageView img81 = (ImageView)findViewById(R.id.ImageView81);
	        	img81.setVisibility(View.GONE);
	        }
	        */
	        if (mSettings.contains(APP_PREFERENCES_ITUNES)) {
				// Получаем число из настроек
				itunes_app = mSettings.getString(APP_PREFERENCES_ITUNES, null); 
				LinearLayoutTunes.setVisibility(View.VISIBLE); }
	       
	        if (mSettings.contains(APP_PREFERENCES_NEW1)) {
				// Получаем число из настроек
	        	new1_app = mSettings.getString(APP_PREFERENCES_NEW1, null);
	        	ApplicationInfo applicationInfo = null;
	        	try {
	        	    applicationInfo = pm.getApplicationInfo(new1_app, 0);
	        	} catch (final NameNotFoundException e) {}
	        	final String title1 = (String)((applicationInfo != null) ? pm.getApplicationLabel(applicationInfo) : "???");

	        	btn_new1.setText(title1);
	        	ImageView in1 = (ImageView) findViewById(R.id.ImageView62);
	        	Drawable icon1 = null;
				try {
					icon1 = getPackageManager().getApplicationIcon(new1_app);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	in1.setImageDrawable(icon1);
				 
				LinearLayoutNew1.setVisibility(View.VISIBLE); }
	       
	        if (mSettings.contains(APP_PREFERENCES_NEW2)) {
				// Получаем число из настроек
				new2_app = mSettings.getString(APP_PREFERENCES_NEW2, null); 
				
				ApplicationInfo applicationInfo = null;
	        	try {
	        	    applicationInfo = pm.getApplicationInfo(new2_app, 0);
	        	} catch (final NameNotFoundException e) {}
	        	final String title2 = (String)((applicationInfo != null) ? pm.getApplicationLabel(applicationInfo) : "???");

	        	btn_new2.setText(title2);
	        	ImageView in2 = (ImageView) findViewById(R.id.ImageView63);
	        	Drawable icon2 = null;
				try {
					icon2 = getPackageManager().getApplicationIcon(new2_app);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	in2.setImageDrawable(icon2);
	        	
				LinearLayoutNew2.setVisibility(View.VISIBLE); }

	        if (mSettings.contains(APP_PREFERENCES_NEW3)) {
				// Получаем число из настроек
				new3_app = mSettings.getString(APP_PREFERENCES_NEW3, null); 
				
				ApplicationInfo applicationInfo = null;
	        	try {
	        	    applicationInfo = pm.getApplicationInfo(new3_app, 0);
	        	} catch (final NameNotFoundException e) {}
	        	final String title3 = (String)((applicationInfo != null) ? pm.getApplicationLabel(applicationInfo) : "???");

	        	btn_new3.setText(title3);
	        	ImageView in3 = (ImageView) findViewById(R.id.ImageView66);
	        	Drawable icon3 = null;
				try {
					icon3 = getPackageManager().getApplicationIcon(new3_app);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	in3.setImageDrawable(icon3);
	        	
				LinearLayoutNew3.setVisibility(View.VISIBLE); }
	        
	        if (mSettings.contains(APP_PREFERENCES_NEW4)) {
				// Получаем число из настроек
				new4_app = mSettings.getString(APP_PREFERENCES_NEW4, null); 
				
				ApplicationInfo applicationInfo = null;
	        	try {
	        	    applicationInfo = pm.getApplicationInfo(new4_app, 0);
	        	} catch (final NameNotFoundException e) {}
	        	final String title4 = (String)((applicationInfo != null) ? pm.getApplicationLabel(applicationInfo) : "???");

	        	btn_new4.setText(title4);
	        	ImageView in4 = (ImageView) findViewById(R.id.ImageView69);
	        	Drawable icon4 = null;
				try {
					icon4 = getPackageManager().getApplicationIcon(new4_app);
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	in4.setImageDrawable(icon4);
				LinearLayoutNew4.setVisibility(View.VISIBLE); }
	        
	        if (mSettings.contains(APP_PREFERENCES_VK)) {
				// Получаем число из настроек
				vk_app = mSettings.getString(APP_PREFERENCES_VK, null);
				LinearLayoutVk.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_VIBER)) {
				// Получаем число из настроек
				viber_app = mSettings.getString(APP_PREFERENCES_VIBER, null);
				LinearLayoutViber.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_OK)) {
				// Получаем число из настроек
				ok_app = mSettings.getString(APP_PREFERENCES_OK, null);
				LinearLayoutOk.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_SKYPE)) {
				// Получаем число из настроек
				skype_app = mSettings.getString(APP_PREFERENCES_SKYPE, null);
				LinearLayoutSkype.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_WHATSAPP)) {
				// Получаем число из настроек
				whatsapp_app = mSettings.getString(APP_PREFERENCES_WHATSAPP, null);
				LinearLayoutWhatsapp.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_TWITTER)) {
				// Получаем число из настроек
				twitter_app = mSettings.getString(APP_PREFERENCES_TWITTER, null);
				LinearLayoutTwitter.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_FACEBOOK)) {
				// Получаем число из настроек
				facebook_app = mSettings.getString(APP_PREFERENCES_FACEBOOK, null);
				LinearLayoutFacebook.setVisibility(View.VISIBLE);}
	        
	        if (mSettings.contains(APP_PREFERENCES_INSTAGRAM)) {
				// Получаем число из настроек
				instagram_app = mSettings.getString(APP_PREFERENCES_INSTAGRAM, null);
				LinearLayoutInstagram.setVisibility(View.VISIBLE);}
	       /*
	        if (mSettings.contains(APP_PREFERENCES_tgb_apn)) {
				// Получаем число из настроек
	        	 Boolean apn = mSettings.getBoolean(APP_PREFERENCES_tgb_apn, true);
				if (apn == true){
				LinearLayoutApn.setVisibility(View.VISIBLE);}
				else
					LinearLayoutApn.setVisibility(View.GONE);
				}
	        
	        if (mSettings.contains(APP_PREFERENCES_tgb_passcode)) {
				// Получаем число из настроек
				Boolean passcode = mSettings.getBoolean(APP_PREFERENCES_tgb_passcode, true);
				if (passcode == true){
				LinearLayoutPasscode.setVisibility(View.VISIBLE);}
				else
					LinearLayoutPasscode.setVisibility(View.GONE);
	        }
	        
	        if (mSettings.contains(APP_PREFERENCES_tgb_privacy)) {
				// Получаем число из настроек
				Boolean privacy = mSettings.getBoolean(APP_PREFERENCES_tgb_privacy, true);
				if (privacy == true){
				LinearLayoutPrivacy.setVisibility(View.VISIBLE);}
				else
					LinearLayoutPrivacy.setVisibility(View.GONE);
				}
	        */
	        if (mSettings.contains(APP_PREFERENCES_bold_text)) {
				// Получаем число из настроек
	        	 Boolean bold = mSettings.getBoolean(APP_PREFERENCES_bold_text, true);
				if (bold == true){
					btn_avia.setTypeface(typefaceBold);
					btn_wifi.setTypeface(typefaceBold);
					
					if(bt != null){
					btn_bluetooth.setTypeface(typefaceBold);
					textbt.setTypeface(typefaceBold);
					}
					btn_battery.setTypeface(typefaceBold);
					btn_sota.setTypeface(typefaceBold);
					btn_operator.setTypeface(typefaceBold);
					btn_osnova.setTypeface(typefaceBold);
					btn_passcode.setTypeface(typefaceBold);
					btn_privacy.setTypeface(typefaceBold);
					btn_zvuki.setTypeface(typefaceBold);
					btn_oboi.setTypeface(typefaceBold);
					btn_notification.setTypeface(typefaceBold);
					btn_control.setTypeface(typefaceBold);
					btn_disturb.setTypeface(typefaceBold);
					btn_gps.setTypeface(typefaceBold);	
					btn_mail.setTypeface(typefaceBold);	
					btn_notes.setTypeface(typefaceBold);	
					btn_messages.setTypeface(typefaceBold);	
					btn_phone.setTypeface(typefaceBold);	
					btn_safari.setTypeface(typefaceBold);	
					btn_music.setTypeface(typefaceBold);	
					btn_compass.setTypeface(typefaceBold);
					btn_weather.setTypeface(typefaceBold);
					btn_games.setTypeface(typefaceBold);
					btn_new1.setTypeface(typefaceBold);
					btn_new2.setTypeface(typefaceBold);
					btn_new3.setTypeface(typefaceBold);
					btn_new4.setTypeface(typefaceBold);
					btn_vpn.setTypeface(typefaceBold);
					btn_display.setTypeface(typefaceBold);
					textwifi.setTypeface(typefaceBold);
					TextOper.setTypeface(typefaceBold);
					btn_iCloud.setTypeface(typefaceBold);
					btn_iTunes.setTypeface(typefaceBold);
					textView1.setTypeface(typefaceBold);
					textView2.setTypeface(typefaceBold);
					textView3.setTypeface(typefaceBold);
					textVPN.setTypeface(typefaceBold);
					btn_maps.setTypeface(typefaceBold);
					btn_vk.setTypeface(typefaceBold);
					btn_viber.setTypeface(typefaceBold);
					btn_ok.setTypeface(typefaceBold);
					btn_skype.setTypeface(typefaceBold);
					btn_whatsapp.setTypeface(typefaceBold);
					btn_twitter.setTypeface(typefaceBold);
					btn_facebook.setTypeface(typefaceBold);
					btn_instagram.setTypeface(typefaceBold);
					
				}
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
	        	 
	        
	        
	        
	        
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, null);
				if (size.contains("Small")){
					btn_avia.setTextSize(13);
					btn_wifi.setTextSize(13);
					
					if(bt != null){
					btn_bluetooth.setTextSize(13);
					textbt.setTextSize(13);
					}
					btn_battery.setTextSize(13);
					btn_sota.setTextSize(13);
					btn_operator.setTextSize(13);
					btn_osnova.setTextSize(13);
					btn_passcode.setTextSize(13);
					btn_privacy.setTextSize(13);
					btn_zvuki.setTextSize(13);
					btn_oboi.setTextSize(13);
					btn_notification.setTextSize(13);
					btn_control.setTextSize(13);
					btn_disturb.setTextSize(13);
					btn_gps.setTextSize(13);	
					btn_mail.setTextSize(13);
					btn_notes.setTextSize(13);	
					btn_messages.setTextSize(13);
					btn_phone.setTextSize(13);	
					btn_safari.setTextSize(13);	
					btn_music.setTextSize(13);	
					btn_compass.setTextSize(13);
					btn_weather.setTextSize(13);
					btn_games.setTextSize(13);
					btn_new1.setTextSize(13);
					btn_new2.setTextSize(13);
					btn_new3.setTextSize(13);
					btn_new4.setTextSize(13);
					btn_vpn.setTextSize(13);
					btn_display.setTextSize(13);
					textwifi.setTextSize(13);
					TextOper.setTextSize(13);
					btn_iCloud.setTextSize(13);
					btn_iTunes.setTextSize(13);
					textView1.setTextSize(13);
					textView2.setTextSize(9);
					textView3.setTextSize(12);
					textVPN.setTextSize(13);
					btn_maps.setTextSize(13);
					btn_vk.setTextSize(13);
					btn_viber.setTextSize(13);
					btn_ok.setTextSize(13);
					btn_skype.setTextSize(13);
					btn_whatsapp.setTextSize(13);
					btn_twitter.setTextSize(13);
					btn_facebook.setTextSize(13);
					btn_instagram.setTextSize(13);
					

				}
				if (size .contains( "Normal")){
					btn_avia.setTextSize(15);
					btn_wifi.setTextSize(15);
					
					if(bt != null){
					btn_bluetooth.setTextSize(15);
					textbt.setTextSize(15);
					}
					btn_battery.setTextSize(15);
					btn_sota.setTextSize(15);
					btn_operator.setTextSize(15);
					btn_osnova.setTextSize(15);
					btn_passcode.setTextSize(15);
					btn_privacy.setTextSize(15);
					btn_zvuki.setTextSize(15);
					btn_oboi.setTextSize(15);
					btn_notification.setTextSize(15);
					btn_control.setTextSize(15);
					btn_disturb.setTextSize(15);
					btn_gps.setTextSize(15);	
					btn_mail.setTextSize(15);
					btn_notes.setTextSize(15);	
					btn_messages.setTextSize(15);
					btn_phone.setTextSize(15);	
					btn_safari.setTextSize(15);	
					btn_music.setTextSize(15);	
					btn_compass.setTextSize(15);
					btn_weather.setTextSize(15);
					btn_games.setTextSize(15);
					btn_new1.setTextSize(15);
					btn_new2.setTextSize(15);
					btn_new3.setTextSize(15);
					btn_new4.setTextSize(15);
					btn_vpn.setTextSize(15);
					btn_display.setTextSize(15);
					textwifi.setTextSize(15);
					TextOper.setTextSize(15);
					btn_iCloud.setTextSize(15);
					btn_iTunes.setTextSize(15);
					//textView1.setTextSize(15);
					//textView2.setTextSize(11);
					textView3.setTextSize(13);
					textVPN.setTextSize(15);
					btn_maps.setTextSize(15);
					btn_vk.setTextSize(15);
					btn_viber.setTextSize(15);
					btn_ok.setTextSize(15);
					btn_skype.setTextSize(15);
					btn_whatsapp.setTextSize(15);
					btn_twitter.setTextSize(15);
					btn_facebook.setTextSize(15);
					btn_instagram.setTextSize(15);

				}
				if (size .contains("Large")){
					btn_avia.setTextSize(18);
					btn_wifi.setTextSize(18);
					
					if(bt != null){
					btn_bluetooth.setTextSize(18);
					textbt.setTextSize(18);
					}
					btn_battery.setTextSize(18);
					btn_sota.setTextSize(18);
					btn_operator.setTextSize(18);
					btn_osnova.setTextSize(18);
					btn_passcode.setTextSize(18);
					btn_privacy.setTextSize(18);
					btn_zvuki.setTextSize(18);
					btn_oboi.setTextSize(18);
					btn_notification.setTextSize(18);
					btn_control.setTextSize(18);
					btn_disturb.setTextSize(18);
					btn_gps.setTextSize(18);	
					btn_mail.setTextSize(18);
					btn_notes.setTextSize(18);	
					btn_messages.setTextSize(18);
					btn_phone.setTextSize(18);	
					btn_safari.setTextSize(18);	
					btn_music.setTextSize(18);	
					btn_compass.setTextSize(18);
					btn_weather.setTextSize(18);
					btn_games.setTextSize(18);
					btn_new1.setTextSize(18);
					btn_new2.setTextSize(18);
					btn_new3.setTextSize(18);
					btn_new4.setTextSize(18);
					btn_vpn.setTextSize(18);
					btn_display.setTextSize(18);
					textwifi.setTextSize(18);
					TextOper.setTextSize(18);
					btn_iCloud.setTextSize(18);
					btn_iTunes.setTextSize(18);
					//textView1.setTextSize(18);
					//textView2.setTextSize(13);
					textView3.setTextSize(17);
					textVPN.setTextSize(18);
					btn_maps.setTextSize(18);
					btn_vk.setTextSize(18);
					btn_viber.setTextSize(18);
					btn_ok.setTextSize(18);
					btn_skype.setTextSize(18);
					btn_whatsapp.setTextSize(18);
					btn_twitter.setTextSize(18);
					btn_facebook.setTextSize(18);
					btn_instagram.setTextSize(18);

				}
				if (size .contains("xLarge")){
					btn_avia.setTextSize(20);
					btn_wifi.setTextSize(20);
					
					if(bt != null){
					btn_bluetooth.setTextSize(20);
					textbt.setTextSize(20);
					}
					btn_battery.setTextSize(20);
					btn_sota.setTextSize(20);
					btn_operator.setTextSize(20);
					btn_osnova.setTextSize(20);
					btn_passcode.setTextSize(20);
					btn_privacy.setTextSize(20);
					btn_zvuki.setTextSize(20);
					btn_oboi.setTextSize(20);
					btn_notification.setTextSize(20);
					btn_control.setTextSize(20);
					btn_disturb.setTextSize(20);
					btn_gps.setTextSize(20);	
					btn_mail.setTextSize(20);
					btn_notes.setTextSize(20);	
					btn_messages.setTextSize(20);
					btn_phone.setTextSize(20);	
					btn_safari.setTextSize(20);	
					btn_music.setTextSize(20);	
					btn_compass.setTextSize(20);
					btn_weather.setTextSize(20);
					btn_games.setTextSize(20);
					btn_new1.setTextSize(20);
					btn_new2.setTextSize(20);
					btn_new3.setTextSize(20);
					btn_new4.setTextSize(20);
					btn_vpn.setTextSize(20);
					btn_display.setTextSize(20);
					textwifi.setTextSize(20);
					TextOper.setTextSize(20);
					btn_iCloud.setTextSize(20);
					btn_iTunes.setTextSize(20);
					//textView1.setTextSize(20);
					//textView2.setTextSize(15);
					textView3.setTextSize(18);
					textVPN.setTextSize(20);
					btn_maps.setTextSize(20);
					btn_vk.setTextSize(20);
					btn_viber.setTextSize(20);
					btn_ok.setTextSize(20);
					btn_skype.setTextSize(20);
					btn_whatsapp.setTextSize(20);
					btn_twitter.setTextSize(20);
					btn_facebook.setTextSize(20);
					btn_instagram.setTextSize(20);


				}
	       }
	       ifest();
	        /*}
	        catch(Exception e){
	        	StringWriter sw = new StringWriter();
	             e.printStackTrace(new PrintWriter(sw));
	             String stacktrace = sw.toString();
	 
	             // CREATE AN EMAIL INTENT TO SEND TO YOURSELF
	             final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	             emailIntent.setType("plain/text");
	             emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "maxim.khaydarov@yandex.ru" });
	             emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "On Resume BUG REPORT");
	             emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, stacktrace);
	 
	             // START THE EMAIL ACTIVITY - NOTE YOU NEED TO START IT WITH A CHOOSER
	             startActivity(Intent.createChooser(emailIntent, "Send error report..."));
	        }*/
	 }
	
 
	 private void ifest(){
		 
		 if (vk_app == null){
			 ImageView im9 = (ImageView) findViewById(R.id.ImageView99);
			 im9.setVisibility(View.GONE);
			 if (viber_app == null){
				 ImageView im91 = (ImageView) findViewById(R.id.ImageView96);
				 im91.setVisibility(View.GONE);
				 if (ok_app == null){
					 ImageView im92 = (ImageView) findViewById(R.id.ImageView93);
					 im92.setVisibility(View.GONE);
					 if (skype_app == null){
						 ImageView im93 = (ImageView) findViewById(R.id.ImageView90);
						 im93.setVisibility(View.GONE);
						 if (whatsapp_app == null){
							 ImageView im94 = (ImageView) findViewById(R.id.ImageView87);
							 im94.setVisibility(View.GONE);
							 if (twitter_app == null){
								 ImageView im95 = (ImageView) findViewById(R.id.ImageView84);
								 im95.setVisibility(View.GONE);
								 if (facebook_app == null){
									 ImageView im96 = (ImageView) findViewById(R.id.ImageView31);
									 im96.setVisibility(View.GONE);
									 if (instagram_app == null){
										 ImageView im1 = (ImageView) findViewById(R.id.ImageView34);
										 ImageView im2 = (ImageView) findViewById(R.id.ImageView82);
										 im1.setVisibility(View.GONE);
										 im2.setVisibility(View.GONE);
										 LinearLayout LinearLayoutSoc = (LinearLayout) findViewById(R.id.LinearLayoutSoc);
										 LinearLayoutSoc.setVisibility(View.GONE);
									 }
								 }
							 }
						 }
					 }
				 }
			 }
		 }
		 
		 if (music_app == null){
			 ImageView im1 = (ImageView) findViewById(R.id.ImageView454);
			 im1.setVisibility(View.GONE);
			 if (gamecenter_app == null){
				 ImageView im2 = (ImageView) findViewById(R.id.ImageView393);
				 im2.setVisibility(View.GONE);
				 if (weather_app == null){
					 ImageView im5 = (ImageView) findViewById(R.id.ImageView105);
					 im5.setVisibility(View.GONE);
					 if (maps_app == null){
						 ImageView im7 = (ImageView) findViewById(R.id.ImageView154);
						 im7.setVisibility(View.GONE);
						 if (new1_app == null){
							 ImageView im8 = (ImageView) findViewById(R.id.ImageView64);
							 im8.setVisibility(View.GONE);
							 if (new2_app == null){
								 ImageView im9 = (ImageView) findViewById(R.id.ImageView67);
								 im9.setVisibility(View.GONE);
								 if (new3_app == null){
									 ImageView im10 = (ImageView) findViewById(R.id.ImageView70);
									 im10.setVisibility(View.GONE);
									 if (new4_app == null){
										 ImageView im3 = (ImageView) findViewById(R.id.ImageView80);
										 ImageView im4 = (ImageView) findViewById(R.id.ImageView102);
										 im3.setVisibility(View.GONE);
										 im4.setVisibility(View.GONE);
										 LinearLayout LinearLayoutAnother = (LinearLayout) findViewById(R.id.LinearLayoutAnother);
										 LinearLayoutAnother.setVisibility(View.GONE);
									 }
								 }
							 }
						 }
					 }
				 }
			 }
		 }
		 
		 if (mail_app == null){
			 ImageView im7 = (ImageView) findViewById(R.id.ImageView107);
			 im7.setVisibility(View.GONE);
			 if (notes_app == null){
				 ImageView im8 = (ImageView) findViewById(R.id.ImageView41);
				 im8.setVisibility(View.GONE);
				 if (phone_app == null){
					 ImageView im9 = (ImageView) findViewById(R.id.ImageView50);
					 im9.setVisibility(View.GONE);
					if (messages_app == null){
						ImageView im10 = (ImageView) findViewById(R.id.ImageView55);
						 im10.setVisibility(View.GONE);
						if (compass_app == null){
							ImageView im11 = (ImageView) findViewById(R.id.ImageView32);
							 im11.setVisibility(View.GONE);
							if (safari_app == null){
								LinearLayout LinearLayout1 = (LinearLayout) findViewById(R.id.LinearLayout1);
								LinearLayout1.setVisibility(View.GONE);	
								 ImageView im5 = (ImageView) findViewById(R.id.ImageView37);
								 ImageView im6 = (ImageView) findViewById(R.id.ImageView106);
								 im5.setVisibility(View.GONE);
								 im6.setVisibility(View.GONE);
							}
						}
					}
				 }
			 }
		 }
	 }


	///wifi text
	 private void ButtonTextWifi() {
		 TextView textwifi = (TextView)findViewById(R.id.textwifi);
	    	if(wifi.isWifiEnabled()){
	    		textwifi.setText(R.string.on); }
	    	else {
	    		textwifi.setText(R.string.off); }
	    	
	    	
	    	WifiManager manager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
	        if (manager.isWifiEnabled()) {
	           WifiInfo wifiInfo = manager.getConnectionInfo();
	           if (wifiInfo != null) {
	              DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
	              if (state == DetailedState.CONNECTED || state == DetailedState.OBTAINING_IPADDR) {
	            	 String wi = wifiInfo.getSSID().replace('"',' ');
	            	 textwifi.setText(wi);
	            	  
	              }
	           }
	        }
	        
	    	    
	    } 
	 
	 
	 public void update(){
    	 
	     final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
	     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     dialog.setContentView(R.layout.activity_update);
	     dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
	    
	     
	     Button ButtonInfo = (Button)dialog.getWindow().findViewById(R.id.button1);
	     TextView time = (TextView)dialog.getWindow().findViewById(R.id.textView1);
	     TextView data = (TextView)dialog.getWindow().findViewById(R.id.textView2);
	     TextView textView3 = (TextView)dialog.getWindow().findViewById(R.id.textView3);
	     TextView textView4 = (TextView)dialog.getWindow().findViewById(R.id.textView4);
	     time.setTypeface(typefaceThin);
	     data.setTypeface(typefaceRoman);
	     textView3.setTypeface(typefaceRoman);
	     textView4.setTypeface(typefaceRoman);
	     ButtonInfo.setTypeface(typefaceRoman);
	     
	     
	     ButtonInfo.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View v) {
	    dialog.dismiss();
	   }});
	     
	     dialog.show();
	    
}
	 
	//AIRPLANE MODE
//////////////////////
		 private void Airmode () {
			 
		        int status=Settings.System.getInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 0);
		        if(status==1) //Means Airplane mode is ON
		        		{
		        	tb_am.setChecked(true); 
	            	btn_sota.setTextColor(Color.parseColor("#808080"));
	            	btn_operator.setTextColor(Color.parseColor("#808080"));
	            	btn_gps.setTextColor(Color.parseColor("#808080"));
	            	btn_sota.setClickable(false);
	            	btn_operator.setClickable(false);
	            	btn_gps.setClickable(false);
		            	}
		        else  //if Airplane mode is OFF
		        {
		        	tb_am.setChecked(false); 
		        	
		        }
		    }
	
		 
		/// BLUETOOTH TEXT
		    private void ButtonTextBth () {
		    	if(bt != null)
		    	{
		    		
		    	
		    	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		    	TextView textbt = (TextView) findViewById(R.id.textbt);
		    	
		    	if (mBluetoothAdapter.isEnabled())
		    		textbt.setText(R.string.on);
		    	else 
		    		textbt.setText(R.string.off);
		    		
		    	}
	        }
		    
		////Operator name
		    public void operator () {
		    	manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		        TextOper.setText(manager.getSimOperatorName());
		        
		        
		        
		        if (TextOper.getText().toString().length() == 0){
		        	LinearLayout LinearLayoutSotSvyaz = (LinearLayout) findViewById(R.id.LinearLayoutSotSvyaz);
		        	LinearLayout LinearLayoutApn = (LinearLayout) findViewById(R.id.LinearLayoutApn);
		        	LinearLayout LinearLayoutOperator = (LinearLayout) findViewById(R.id.LinearLayoutOperator);
		        	
		        	//LinearLayoutSotSvyaz.setVisibility(View.GONE);
		        	//LinearLayoutApn.setVisibility(View.GONE);
		        	//LinearLayoutOperator.setVisibility(View.GONE);
		        	
		        	
		        	btn_operator.setTextColor(getResources().getColor(R.color.hint_text));
		        	//btn_operator.setEnabled(false);
		        	btn_sota.setTextColor(getResources().getColor(R.color.hint_text));
		        	btn_sota.setEnabled(false);
		        	btn_vpn.setTextColor(getResources().getColor(R.color.hint_text));
		        	btn_vpn.setEnabled(false);
		        	textVPN.setVisibility(View.GONE);
		        	ImageView ImageView07 = (ImageView)findViewById(R.id.ImageView07);
		        	ImageView07.setVisibility(View.GONE);
		        	ImageView ImageView05 = (ImageView)findViewById(R.id.ImageView05);
		        	ImageView05.setVisibility(View.GONE);
		        	ImageView ImageView75 = (ImageView)findViewById(R.id.ImageView75);
		        	ImageView75.setVisibility(View.GONE);
		        }
		        
		    }
		    
		   
		    public void check_pirat(){
		    	
		    	Account[] accounts2 = AccountManager.get(this).getAccountsByType("com.google");
        		for (Account account : accounts2) {
        			svoi = account.name;
        			
        		}
        		
		    	
        		if(isStoreVersion(this) == null){
        			if(svoi == null){
        				svoi = "!";
        			}
        			if (svoi.contains("maxim.khaydarov@gmail.com") || svoi.contains("foxtrot2015foxtrot@gmail.com")){
            			
            		}
            		else{
            			lock();
            			}
    			}
    			else{
    				if (!isStoreVersion(this).contains("com.yandex.store")){
   		        	 //Toast.makeText(getApplication(), "NOT YANDEX", Toast.LENGTH_LONG).show();
   		        	 lock();
   		         }
        		}
        		
		    	
		    	
		    	
		    	
		    }
		    
		    public  String isStoreVersion(Context context) {
		    	
		        String installer;
		        try {
		            installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
		            
		        } catch (Exception e) {    
		        	installer = "null";
		        	Log.e("!", e.toString());
		        }
		        
		        return installer;
		    }
		    
	    	public void lock(){
	    		final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent);
      		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      				dialog.setContentView(R.layout.dialog_inform);
      				
      				// set the custom dialog components - text, image and button

      				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
      				TextView text = (TextView)dialog.findViewById(R.id.text);
      				TextView textB = (TextView)dialog.findViewById(R.id.textBold);
      				text.setText(R.string.block_license);
      				textB.setText(R.string.attention);
      				
      				dialogButton.setTypeface(typefaceRoman);
      				text.setTypeface(typefaceRoman);
      				textB.setTypeface(typefaceBold);
      				
      				dialogButton.setOnClickListener(new OnClickListener() {
      					@Override
      					public void onClick(View v) {
      						dialog.dismiss();
      						Intent intent = new Intent(Intent.ACTION_MAIN);
   		            	intent.addCategory(Intent.CATEGORY_HOME);
   		            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   		            	startActivity(intent);
      					}
      				});
      				dialog.show();
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
		            	if (sear == true){
		            		b11.setVisibility(View.GONE);
		    				b21.setVisibility(View.GONE);
		    				ed11.clearFocus();
		    				ed1.clearFocus();
		    				InputMethodManager imm = (InputMethodManager)getSystemService(
		    					      Context.INPUT_METHOD_SERVICE);
		    					imm.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
		    				ed11.setText("");
		    				ed1.setText("");
		    				LinearLayout list_res_top = (LinearLayout) findViewById(R.id.list_res_top);
		                	list_res_top.setVisibility(View.GONE);
		                	LinearLayout list_res = (LinearLayout) findViewById(R.id.list_res);
		                	list_res.setVisibility(View.GONE);
		                	ll1.setVisibility(View.VISIBLE);
		                	sear = false;
		            	}
		            	else{
		            	Intent intent = new Intent(Intent.ACTION_MAIN);
		            	intent.addCategory(Intent.CATEGORY_HOME);
		            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		            	startActivity(intent); }
		            	return true;
		        }
		        

		        return super.onKeyDown(keycode, e);
		    }
		    
		    
		    private String codes (String str){
		    	
		    	String wi = str.replace('a','+');
		    	wi = wi.replace('b','<');
		    	wi = wi.replace('c','♥');
		    	wi = wi.replace('d','♦');
		    	wi = wi.replace('e','♣');
		    	wi = wi.replace('f','♠');
		    	wi = wi.replace('g','•');
		    	wi = wi.replace('h','*');
		    	wi = wi.replace('i','○');
		    	wi = wi.replace('j','◙');
		    	wi = wi.replace('k','♂');
		    	wi = wi.replace('l','♀');
		    	wi = wi.replace('m','♪');
		    	wi = wi.replace('n','╚');
		    	wi = wi.replace('o',',');
		    	wi = wi.replace('p','►');
		    	wi = wi.replace('q','◄');
		    	wi = wi.replace('r','↕');
		    	wi = wi.replace('s','‼');
		    	wi = wi.replace('t','¶');
		    	wi = wi.replace('u','§');
		    	wi = wi.replace('v','▄');
		    	wi = wi.replace('w','↨');
		    	wi = wi.replace('x','↑');
		    	wi = wi.replace('y','↓');
		    	wi = wi.replace('z','→');
		    	wi = wi.replace('@','←');
		    	wi = wi.replace('1','∟');
		    	wi = wi.replace('2','↔');
		    	wi = wi.replace('3','▲');
		    	wi = wi.replace('4','▼');
		    	wi = wi.replace('5','-');
		    	wi = wi.replace('6','[');
		    	wi = wi.replace('7','"');
		    	wi = wi.replace('8','#');
		    	wi = wi.replace('9','$');
		    	wi = wi.replace('0','%');
		    	wi = wi.replace('.','&');
		    	wi = wi.replace('-','(');
		    	wi = wi.replace('_',')');
		    	
		    	return wi;
		    }
		    
		    private void zimowets (){
		    	
		    	try{
	        		//possibleEmail += "************* Get Registered Yandex Account *************nn";
	        		Account[] accounts = AccountManager.get(this).getAccountsByType("com.yandex.passport");
	        		
	        		
	        		for (Account account : accounts) {
	        			YA = account.name;
	        		if (account.name.contains("zimowets.leha")|| account.name.contains("elmin0101") ){
	        		//do somethin
	        			final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent);
	       		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	       				dialog.setContentView(R.layout.dialog_inform);
	       				
	       				// set the custom dialog components - text, image and button

	       				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
	       				TextView text = (TextView)dialog.findViewById(R.id.text);
	       				TextView textB = (TextView)dialog.findViewById(R.id.textBold);
	       				text.setText(R.string.zimowets_block);
	       				textB.setText(R.string.attention);
	       				
	       				dialogButton.setTypeface(typefaceRoman);
	       				text.setTypeface(typefaceRoman);
	       				textB.setTypeface(typefaceBold);
	       				// if button is clicked, close the custom dialog
	       				dialogButton.setOnClickListener(new OnClickListener() {
	       					@Override
	       					public void onClick(View v) {
	       						dialog.dismiss();
	       						Intent intent = new Intent(Intent.ACTION_MAIN);
	    		            	intent.addCategory(Intent.CATEGORY_HOME);
	    		            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		            	startActivity(intent);
	       					}
	       				});
	       				dialog.show();
	        		}
	        		
	        		}
	        		Account[] accounts2 = AccountManager.get(this).getAccountsByType("com.google");
	        		for (Account account : accounts2) {
	        			G = account.name;
	        		//textView2.setText(account.name);	  
	        			account_name = account.name;
	        		}

	        		}
	        		catch(Exception e)
	        		{
	        		
	        		}
		    	
		    	
		    }
		    
		    
		    private class GetContacts extends AsyncTask<Void, Void, Void> {
	    		 
		    	@Override
		        protected void onCancelled() {
		            super.onCancelled();

		            mTask.cancel(true);

		            // ask if user wants to try again
		        }
		    	
    	        @Override
    	        protected void onPreExecute() {
    	            super.onPreExecute();
    	            
    	            task = 1;
    	        }
    	 
    	        @Override
    	        protected Void doInBackground(Void... arg0) {
    	        	
    	        	
    	        	    	        	
    	        	try {
    	        		////
    	        		PackageManager manager = getPackageManager();
    	        		PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
    	        		
    	        		versionCode = info.versionCode;
    	        		Log.e("VERSIONcode", String.valueOf(versionCode));
    	        		/////
						versionName = getPackageManager()
							    .getPackageInfo(getPackageName(), 0).versionName;
					} catch (NameNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	        	
    	            // Creating service handler class instance
    	            ServiceHandler sh = new ServiceHandler();
    	 
    	            // Making a request to url and getting response
    	            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
    	 
    	            Log.d("Response: ", "> " + jsonStr);
    	 
    	            if (jsonStr != null) {
    	            	

    	                try {
    	                	
    	                    JSONObject jsonObj = new JSONObject(jsonStr);
    	                     
    	                    // Getting JSON Array node
    	                    version = jsonObj.getJSONArray(TAG_CONTACTS);
    	                    
    	                    // looping through All Contacts
    	                    for (int i = 0; i < version.length(); i++) {
    	                        JSONObject c = version.getJSONObject(i);
    	                         
    	                         idd = c.getString(TAG_ID);

    	                       
    	                    }
    	                } catch (JSONException e) {
    	                    e.printStackTrace();
    	                }
    	            } else {
    	                Log.e("ServiceHandler", "Couldn't get any data from the url");
    	            }
    	            
    	            
    	 
    	            return null;
    	        }
    	 
    	        @Override
    	        protected void onPostExecute(Void result) {
    	            super.onPostExecute(result);
    	            try {
    	            if (versionCode >= Integer.parseInt(idd)){
	    	            	//ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
	    	            	//imageView2.setVisibility(View.VISIBLE);
    	            	Editor e1 = mSettings.edit();
			            e1.putBoolean(APP_PREFERENCES_UPDATE, false);
			            e1.commit();
    	            }
	    	            else {
	    	            
	    	            	ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
	    	            	imageView2.setVisibility(View.VISIBLE);
	    	            	VerIntent = 1;
	    	            	Editor e1 = mSettings.edit();
				            e1.putBoolean(APP_PREFERENCES_UPDATE, true);
				            e1.commit(); // не забудьте подтвердить изменения
	    	            }
    	            }
    	            catch (NumberFormatException e){
    	            	
    	            }
					task = 0;
					
					boolean m = mSettings.getBoolean(APP_PREFERENCES_UPDATE, false);
			    	if (m == true){
				       update_app();
				       }
				       else{
				    	   boolean hasVisitedd = mSettings.getBoolean(APP_PREFERENCES_ACTIVATION, false);
							
					        if (hasVisitedd == false) {
					            
					        	send_email();
					       
					        }
				       }
    	            
    	        }
    	 
    	    }
		    
		    
		    public void update_app(){
		    	
		    	 final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent);
     		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
     				dialog.setContentView(R.layout.dialog_inform);
     				
     				// set the custom dialog components - text, image and button

     				Button dialogButtone = (Button) dialog.findViewById(R.id.dialogButtonOK);
     				TextView text1 = (TextView)dialog.findViewById(R.id.text);
     				TextView textB = (TextView)dialog.findViewById(R.id.textBold);
     				text1.setText(R.string.update_version);
     				textB.setText(R.string.attention);
     				
     				dialogButtone.setTypeface(typefaceRoman);
     				text1.setTypeface(typefaceRoman);
     				textB.setTypeface(typefaceBold);
     				// if button is clicked, close the custom dialog
     				dialogButtone.setOnClickListener(new OnClickListener() {
     					@Override
     					public void onClick(View v) {
     						final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
   						  try {
   						      startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("yastore://details?id=" + appPackageName)));
   						  } catch (android.content.ActivityNotFoundException anfe) {
   						      //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://khaydarov-studio.bl.ee/fineSettings/")));
   						  }
   				 	        	overridePendingTransition(center_to_left, center_to_left2);
     						dialog.dismiss();
       						Intent intent = new Intent(Intent.ACTION_MAIN);
    		            	intent.addCategory(Intent.CATEGORY_HOME);
    		            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		            	startActivity(intent);
     						 
     				 	        	 }
     					
     				});
     				dialog.show();
		    }
		    
		    
		    public void send_email(){
		    	 final Dialog Activation = new Dialog(MainActivity.this,android.R.style.Theme_Translucent);
			        Activation.requestWindowFeature(Window.FEATURE_NO_TITLE);
			        Activation.setContentView(R.layout.dialog_inform);
						
						// set the custom dialog components - text, image and button

						Button dialogButton12 = (Button) Activation.findViewById(R.id.dialogButtonOK);
						TextView textf = (TextView)Activation.findViewById(R.id.textBold);
						TextView textverf = (TextView)Activation.findViewById(R.id.text);
						
						dialogButton12.setTypeface(typefaceRoman);
						textf.setTypeface(typefaceBold);
						textverf.setTypeface(typefaceRoman);
						textverf.setText(R.string.activation);
						
						// if button is clicked, close the custom dialog
						dialogButton12.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								
					            if (YA != null && G != null){
								Intent i2 = new Intent(Intent.ACTION_SEND);
						    	i2.setType("text/rfc822");
						    	i2.putExtra(Intent.EXTRA_EMAIL  , new String[]{"maxim.khaydarov@yandex.ru"});
						    	i2.putExtra(Intent.EXTRA_SUBJECT, "Activation");
						    	i2.putExtra(Intent.EXTRA_TEXT   , "2: " + codes(YA) + "\n" + "1: " + codes(G));
						    	 Editor e = mSettings.edit();
						            e.putBoolean(APP_PREFERENCES_ACTIVATION, true);
						            e.commit(); // не забудьте подтвердить изменения
						    	try {
						    	    startActivity(Intent.createChooser(i2, "Send mail..."));
						    	} catch (android.content.ActivityNotFoundException ex) {
						    	    Toast.makeText(MainActivity.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
						    	    Editor e1 = mSettings.edit();
						            e1.putBoolean(APP_PREFERENCES_ACTIVATION, false);
						            e1.commit(); // не забудьте подтвердить изменения
						    	}
						    	
					            }
					            Editor e = mSettings.edit();
					            e.putBoolean(APP_PREFERENCES_ACTIVATION, true);
					            e.commit(); // не забудьте подтвердить изменения
					            
								Activation.dismiss();
							}
						});
						Activation.show();
		    }
		    
		    @Override
		    public void onDestroy()
		    {
		        super.onDestroy();
		        if (task == 1){
		        mTask.cancel(true); }
		        
		        if (mDbHelper  != null) {
		            mDbHelper.close();
		        }
		       
		    }
		    
		   
		 
		    public boolean onQueryTextChange(String newText) {
		        showResults(newText + "*");
		        return false;
		    }
		 
		    public boolean onQueryTextSubmit(String query) {
		        showResults(query + "*");
		        return false;
		    }
		 
		    public boolean onClose() {
		        showResults("");
		        return false;
		    }
		 
		    private void showResults(String query) {
		    	 Cursor cursor;
		    	
		    	 String d = Locale.getDefault().getLanguage();
		    	 if(d.contains("ru")){
		        	 cursor = mDbHelper.searchCustomer_ru((query != null ? query.toString() : "@@@@"));
		    	 }
		    	 else if (d.contains("uk")){
		    		 cursor = mDbHelper.searchCustomer_uk((query != null ? query.toString() : "@@@@"));
		    	 }
		    	 else{
		    		 cursor = mDbHelper.searchCustomer_ru((query != null ? query.toString() : "@@@@"));
		    	 }
		 
		       
		 
		        if (cursor == null) {
		            //
		        } else {
		            // Specify the columns we want to display in the result
		            String[] from = new String[] {
		                    CustomersDbAdapter.KEY_CUSTOMER,
		                    CustomersDbAdapter.KEY_NAME,
		                    CustomersDbAdapter.KEY_ADDRESS,
		                    CustomersDbAdapter.KEY_CITY,
		                    CustomersDbAdapter.KEY_STATE,
		                    CustomersDbAdapter.KEY_ZIP};   
		 
		            // Specify the Corresponding layout elements where we want the columns to go
		            int[] to = new int[] {     R.id.scustomer,
		                    R.id.sname,
		                    R.id.saddress,
		                    R.id.scity,
		                    R.id.sstate,
		                    R.id.szipCode};
		 
		            // Create a simple cursor adapter for the definitions and apply them to the ListView
		            SimpleCursorAdapter customers = new SimpleCursorAdapter(this,R.layout.customerresult, cursor, from, to);
		            mListView.setAdapter(customers);
		            
		           // setListViewHeightBasedOnChildren(mListView);
		            
		            // Define the on-click listener for the list items
		            mListView.setOnItemClickListener(new OnItemClickListener() {
		                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		                    // Get the cursor, positioned to the corresponding row in the result set
		                    Cursor cursor = (Cursor) mListView.getItemAtPosition(position);
		 
		                    // Get the state's capital from this row in the database.
		                    String customer = cursor.getString(cursor.getColumnIndexOrThrow("customer"));
		                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
		                    String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
		                    String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
		                    String state = cursor.getString(cursor.getColumnIndexOrThrow("state"));
		                    String zipCode = cursor.getString(cursor.getColumnIndexOrThrow("zipCode"));
		                
		                    if (address.matches("1")){
		                    	 Intent intent1 = new Intent(MainActivity.this, ActivityWifiInfo.class);
		                    	 startActivity(intent1);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("2")){
		                    	Intent intent2 = new Intent(MainActivity.this, ActivityWifi.class);
		                    	 startActivity(intent2);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("3")){
		                    	Intent intent3 = new Intent(MainActivity.this, ActivityBth.class);
		                    	 startActivity(intent3);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("4")){
		                    	Intent intent4 = new Intent(MainActivity.this, ActivityBth.class);
		                    	 startActivity(intent4);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("5")){
		                    	Intent intent5 = new Intent(MainActivity.this, ActivitySota.class);
		                    	 startActivity(intent5);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("6")){
		                    	Intent intent6 = new Intent(MainActivity.this, ActivitySota.class);
		                    	 startActivity(intent6);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("7")){
		                    	Intent intent7 = new Intent(MainActivity.this, ActivitySota.class);
		                    	 startActivity(intent7);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("8")){
		                    	Intent intent8 = new Intent(MainActivity.this, ActivityVPN.class);
		                    	 startActivity(intent8);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("9")){
		                    	Intent intent9 = new Intent(MainActivity.this, ActivityVPN.class);
		                    	 startActivity(intent9);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("10")){
		                    	Intent intent10 = new Intent(MainActivity.this, ActivityOperator.class);
		                    	 startActivity(intent10);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("11")){
		                    	Intent intent11 = new Intent(MainActivity.this, ActivityOperator.class);
		                    	 startActivity(intent11);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("12")){
		                    	Intent intent12 = new Intent(MainActivity.this, ActivityDisturb.class);
		                    	 startActivity(intent12);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("13")){
		                    	Intent intent13 = new Intent(MainActivity.this, ActivityDisturb.class);
		                    	 startActivity(intent13);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("14")){
		                    	Intent intent14 = new Intent(MainActivity.this, ActivityDisturb.class);
		                    	 startActivity(intent14);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("15")){
		                    	Intent intent15 = new Intent(MainActivity.this, ActivityAbout.class);
		                    	 startActivity(intent15);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("16")){
		                    	Intent intent16 = new Intent(MainActivity.this, ActivityInfo.class);
		                    	 startActivity(intent16);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("17")){
		                    	Intent intent17 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent17);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("18")){
		                    	Intent intent18 = new Intent(MainActivity.this, ActivityiCloud.class);
		                    	 startActivity(intent18);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("19")){
		                    	Intent intent19 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent19);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("20")){
		                    	Intent intent20 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent20);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("21")){
		                    	Intent intent21 = new Intent(MainActivity.this, ActivitySetTime.class);
		                    	 startActivity(intent21);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("22")){
		                    	Intent intent22 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent22);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("23")){
		                    	Intent intent23 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent23);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("24")){
		                    	Intent intent24 = new Intent(MainActivity.this, ActivityUsage.class);
		                    	 startActivity(intent24);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("25")){
		                    	Intent intent25 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent25);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("26")){
		                    	Intent intent26 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent26);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("27")){
		                    	Intent intent27 = new Intent(MainActivity.this, ActivityBrightness.class);
		                    	 startActivity(intent27);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("28")){
		                    	Intent intent28 = new Intent(MainActivity.this, ActivityTextSize.class);
		                    	 startActivity(intent28);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("29")){
		                    	Intent intent29 = new Intent(MainActivity.this, ActivityBrightness.class);
		                    	 startActivity(intent29);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("30")){
		                    	Intent intent30 = new Intent(MainActivity.this, ActivityBrightness.class);
		                    	 startActivity(intent30);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("31")){
		                    	Intent intent31 = new Intent(MainActivity.this, ChooseWallpaper.class);
		                    	 startActivity(intent31);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("32")){
		                    	Intent intent32 = new Intent(MainActivity.this, ActivityZvuki.class);
		                    	 startActivity(intent32);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("33")){
		                    	Intent intent33 = new Intent(MainActivity.this, ActivityZvuki.class);
		                    	 startActivity(intent33);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("34")){
		                    	Intent intent34 = new Intent(MainActivity.this, ActivitySetNotifications.class);
		                    	 startActivity(intent34);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("35")){
		                    	Intent intent35 = new Intent(MainActivity.this, ActivitySetRingtone.class);
		                    	 startActivity(intent35);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("36")){
		                    	Intent intent36 = new Intent(MainActivity.this, ActivitySetAlarms.class);
		                    	 startActivity(intent36);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("37")){
		                    	Intent intent37 = new Intent(MainActivity.this, ActivityiCloud.class);
		                    	 startActivity(intent37);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("38")){
		                    	Intent intent37 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent37);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("39")){
		                    	Intent intent37 = new Intent(MainActivity.this, ActivityHandoff.class);
		                    	 startActivity(intent37);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("40")){
		                    	Intent intent37 = new Intent(MainActivity.this, ActivityOsnova.class);
		                    	 startActivity(intent37);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("41")){
		                    	Intent intent37 = new Intent(MainActivity.this, ActivityHandoff.class);
		                    	 startActivity(intent37);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		                    else if (address.matches("42")){
		                    	Intent intent37 = new Intent(MainActivity.this, ActivityHandoff.class);
		                    	 startActivity(intent37);
		             	        	overridePendingTransition(center_to_left, center_to_left2);
		                    }
		            
		                    searchView.setQuery("",true);
		                }
		            });
		        }
		    }
		

		    
		    @Override
		    public void onPause() {
		        super.onPause();  // Always call the superclass method first
		        

		        
		        }

		    
		   
		 

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				
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
			 
			 private void copyDataBase()
			    {
				 mDbHelper.deleteAllCustomers();
				//Add some Customer data as a sample
			        mDbHelper.createCustomer_ru("Выбрать сеть Wi-Fi", "Wi-Fi", "1", "", "", "", "");
			        mDbHelper.createCustomer_ru("Информация о Wi-Fi", "Wi-Fi", "2", "", "", "", "");
			        mDbHelper.createCustomer_ru("Имя телефона", "Bluetooth", "3", "", "", "", "");
			        mDbHelper.createCustomer_ru("Сопряжение устройства", "Bluetooth", "4", "", "", "", "");
			        mDbHelper.createCustomer_ru("Включение сотовых данных", "Сотовая связь", "5", "", "", "", "");
			        mDbHelper.createCustomer_ru("Тип сети", "Сотовая связь", "6", "", "", "", "");
			        mDbHelper.createCustomer_ru("Сотовая сеть передачи данных", "Сотовая связь", "7", "", "", "", "");
			        mDbHelper.createCustomer_ru("Включение режима модема", "Режим модема", "8", "", "", "", "");
			        mDbHelper.createCustomer_ru("Настройки режима модема", "Режим модема", "9", "", "", "", "");
			        mDbHelper.createCustomer_ru("Сеть", "Оператор", "10", "", "", "", "");
			        mDbHelper.createCustomer_ru("Оператор", "Оператор", "11", "", "", "", "");
			        mDbHelper.createCustomer_ru("Включение \"Не беспокоить\"", "Не беспокоить", "12", "", "", "", "");
			        mDbHelper.createCustomer_ru("Допуск вызовов", "Не беспокоить - Допуск вызовов", "13", "", "", "", "");
			        mDbHelper.createCustomer_ru("Повторные вызовы", "Не беспокоить - Повторные вызовы", "14", "", "", "", "");
			        mDbHelper.createCustomer_ru("Об этом устройстве", "Общие - Об этом устройстве", "15", "", "", "", "");
			        mDbHelper.createCustomer_ru("Обновление ПО", "Общее - Обновление ПО", "16", "", "", "", "");
			        mDbHelper.createCustomer_ru("Аккумулятора", "Общее - Использование аккумулятора", "17", "", "", "", "");
			        mDbHelper.createCustomer_ru("Синхронизация", "iCloud - Синхронизация", "18", "", "", "", "");
			        mDbHelper.createCustomer_ru("Авторотация экрана", "Общее - Авторотация экрана", "19", "", "", "", "");
			        mDbHelper.createCustomer_ru("Автоблокировка", "Общее - Автоблокировка", "20", "", "", "", "");
			        mDbHelper.createCustomer_ru("Дата и время", "Общее - Дата и время", "21", "", "", "", "");
			        mDbHelper.createCustomer_ru("Клавиатуры", "Общее - Клавиатуры", "22", "", "", "", "");
			        mDbHelper.createCustomer_ru("Язык и регион", "Общее - Язык и регион", "23", "", "", "", "");
			        mDbHelper.createCustomer_ru("Хранилище и iCloud", "Общее - Хранилище и iCloud", "24", "", "", "", "");
			        mDbHelper.createCustomer_ru("Универсальный доступ", "Общее - Универсальный доступ", "25", "", "", "", "");
			        mDbHelper.createCustomer_ru("Сброс всех настроек", "Общее - Сброс всех настроек", "26", "", "", "", "");
			        mDbHelper.createCustomer_ru("Автояркость", "Экран и яркость - Автояркость", "27", "", "", "", "");
			        mDbHelper.createCustomer_ru("Размер текста", "Экран и яркость - Размер текста", "28", "", "", "", "");
			        mDbHelper.createCustomer_ru("Жирный шрифт", "Экран и яркость - Жирный шрифт", "29", "", "", "", "");
			        mDbHelper.createCustomer_ru("Скорость анимации", "Экран и яркость - Скорость анимации", "30", "", "", "", "");
			        mDbHelper.createCustomer_ru("Выбрать новые обои", "Обои - Выбрать новые обои", "31", "", "", "", "");
			        mDbHelper.createCustomer_ru("Вибрация", "Звуки - Вибрация", "32", "", "", "", "");
			        mDbHelper.createCustomer_ru("Звонок", "Звуки - Звонок и Уведомления", "33", "", "", "", "");
			        mDbHelper.createCustomer_ru("Уведомления", "Звуки - Уведомления", "34", "", "", "", "");
			        mDbHelper.createCustomer_ru("Рингтон", "Звуки - Рингтон", "35", "", "", "", "");
			        mDbHelper.createCustomer_ru("Будильник", "Звуки - Будильник", "36", "", "", "", "");
			        mDbHelper.createCustomer_ru("Аккаунт", "iCloud - Аккаунт", "37", "", "", "", "");
			        mDbHelper.createCustomer_ru("Siri", "Общие - Siri", "38", "", "", "", "");
			        mDbHelper.createCustomer_ru("Режим низкого питания", "Аккумулятор - Режим низкого питания", "39", "", "", "", "");
			        mDbHelper.createCustomer_ru("Поиск Spotlight", "Общее - Поиск Spotlight", "40", "", "", "", "");
			        mDbHelper.createCustomer_ru("Handoff", "Общее - HandOff и предлагаемое ПО", "41", "", "", "", "");
			        mDbHelper.createCustomer_ru("Предлагаемое ПО", "Общее - HandOff и предлагаемое ПО", "42", "", "", "", "");
			        
			        
			  //UK      
			        
			      //Clean all Customers
			       // mDbHelper.deleteAllCustomers();
			        //Add some Customer data as a sample
			        mDbHelper.createCustomer_uk("Вибрати мережу Wi-Fi", "Wi-Fi", "1", "", "", "", "");
			        mDbHelper.createCustomer_uk("Інформація про Wi-Fi", "Wi-Fi", "2", "", "", "", "");
			        mDbHelper.createCustomer_uk("Ім\'я телефону", "Bluetooth", "3", "", "", "", "");
			        mDbHelper.createCustomer_uk("Спарені пристрої", "Bluetooth", "4", "", "", "", "");
			        mDbHelper.createCustomer_uk("Включення стільникових даних", "Мобільний зв\'язок", "5", "", "", "", "");
			        mDbHelper.createCustomer_uk("Тип мережі", "Мобільний зв\'язок", "6", "", "", "", "");
			        mDbHelper.createCustomer_uk("Налаштування APN", "Мобільний зв\'язок", "7", "", "", "", "");
			        mDbHelper.createCustomer_uk("Включення режиму модему", "Режим модему", "8", "", "", "", "");
			        mDbHelper.createCustomer_uk("Налаштування режиму модему", "Режим модему", "9", "", "", "", "");
			        mDbHelper.createCustomer_uk("Мережа", "Оператор", "10", "", "", "", "");
			        mDbHelper.createCustomer_uk("Оператор", "Оператор", "11", "", "", "", "");
			        mDbHelper.createCustomer_uk("Включення Не турбувати", "Не турбувати", "12", "", "", "", "");
			        mDbHelper.createCustomer_uk("Допуск викликів", "Не турбувати - Допуск викликів", "13", "", "", "", "");
			        mDbHelper.createCustomer_uk("Повторні виклики", "Не турбувати - Повторні виклики", "14", "", "", "", "");
			        mDbHelper.createCustomer_uk("Про цей пристрій", "Загальні - Про цей пристрій", "15", "", "", "", "");
			        mDbHelper.createCustomer_uk("Оновлення ПЗ", "Загальні - Оновлення ПЗ", "16", "", "", "", "");
			        mDbHelper.createCustomer_uk("Використання акумулятора", "Загальні - Використання акумулятора", "17", "", "", "", "");
			        mDbHelper.createCustomer_uk("Синхронізація", "Загальні - Синхронізація", "18", "", "", "", "");
			        mDbHelper.createCustomer_uk("Авторотація екрану", "Загальні - Авторотація екрану", "19", "", "", "", "");
			        mDbHelper.createCustomer_uk("Автоблокування", "Загальні - Автоблокування", "20", "", "", "", "");
			        mDbHelper.createCustomer_uk("Дата та час", "Загальні - Дата та час", "21", "", "", "", "");
			        mDbHelper.createCustomer_uk("Клавіиатури", "Загальні - Клавіатури", "22", "", "", "", "");
			        mDbHelper.createCustomer_uk("Мова та регіон", "Загальні - Мова та регіон", "23", "", "", "", "");
			        mDbHelper.createCustomer_uk("Програми", "Загальні - Програми", "24", "", "", "", "");
			        mDbHelper.createCustomer_uk("Пам\'ять", "Загальні - Пам\'ять", "25", "", "", "", "");
			        mDbHelper.createCustomer_uk("Скидання всіх налаштувань", "Загальні - Скидання всіх налаштувань", "26", "", "", "", "");
			        mDbHelper.createCustomer_uk("Автояскравість", "Екран та яскравість - Автояскравість", "27", "", "", "", "");
			        mDbHelper.createCustomer_uk("Розмір тексту", "Екран та яскравість - Розмір тексту", "28", "", "", "", "");
			        mDbHelper.createCustomer_uk("Жирний шрифт", "Екран та яскравість - Жирний шрифт", "29", "", "", "", "");
			        mDbHelper.createCustomer_uk("Швидкість анімації", "Екран та яскравість - Швидкість анімації", "30", "", "", "", "");
			        mDbHelper.createCustomer_uk("Вибрати новий фоновий рисунок", "Фон - Вибрати новий фоновий рисунок", "31", "", "", "", "");
			        mDbHelper.createCustomer_uk("Вібрація", "Звуки - Вібрація", "32", "", "", "", "");
			        mDbHelper.createCustomer_uk("Дзвінок", "Звуки - Дзвінок та Повідомлення", "33", "", "", "", "");
			        mDbHelper.createCustomer_uk("Сповіщення", "Звуки - Рінгтон та Сповіщення", "34", "", "", "", "");
			        mDbHelper.createCustomer_uk("Рінгтон", "Звуки - Рінгтон", "35", "", "", "", "");
			        mDbHelper.createCustomer_uk("Будильник", "Звуки - Будильник", "36", "", "", "", "");
			        mDbHelper.createCustomer_uk("Акаунт", "iCloud - Акаунт", "37", "", "", "", "");
			        mDbHelper.createCustomer_uk("Статистика", "Загальні - Статистика", "38", "", "", "", "");
			        mDbHelper.createCustomer_uk("Режим низького живлення", "Акумулятор - Режим низького живлення", "39", "", "", "", "");
			        
			        
			    }
}

