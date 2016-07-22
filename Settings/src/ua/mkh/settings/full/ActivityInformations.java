package ua.mkh.settings.full;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

public class ActivityInformations extends Activity implements View.OnClickListener{
	
	Typeface typefaceRoman, typefaceMedium, typefaceBold;
	Animation animationRotateDown, animationRotateUp;
	Button btn_back, Button02, button1, button2;
	ImageView Up, Down;
	LinearLayout LinearLayour122;
	int status = 0;
	TextView textView1,  textView2, textView3, textView4, textView5, textView6, textView7, textView8;
	
	int OS = android.os.Build.VERSION.SDK_INT;
	
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
        setContentView(R.layout.activity_informations);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		
		TextView textStatus = (TextView)findViewById(R.id.textOk);
		
		Up = (ImageView)findViewById(R.id.imageView3);
		Down = (ImageView)findViewById(R.id.imageView2);
		Up.setVisibility(View.GONE);
		
		animationRotateDown = AnimationUtils.loadAnimation(
				this, R.anim.rotate_down);
	    animationRotateUp = AnimationUtils.loadAnimation(
				this, R.anim.rotate_up);
		
		btn_back = (Button) findViewById(R.id.buttonBack);
		btn_back.setText(R.string.about_this);
		Button02 = (Button) findViewById(R.id.Button02);
		Button02.setOnClickListener(this);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		
		LinearLayour122 = (LinearLayout)findViewById(R.id.LinearLayour122);
		LinearLayour122.setVisibility(View.GONE);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView5 = (TextView) findViewById(R.id.textView5);
		textView6 = (TextView) findViewById(R.id.textView6);
		textView7 = (TextView) findViewById(R.id.textView7);
		textView8 = (TextView) findViewById(R.id.textView8);
		
		
		textStatus.setText(R.string.informations);
	    textStatus.setTypeface(typefaceBold);
	    btn_back.setTypeface(typefaceMedium);
	    Button02.setTypeface(typefaceRoman);
	    button1.setTypeface(typefaceRoman);
	    button2.setTypeface(typefaceRoman);
	    textView1.setTypeface(typefaceRoman);
	    textView2.setTypeface(typefaceRoman);
	    textView3.setTypeface(typefaceRoman);
	    textView4.setTypeface(typefaceRoman);
	    textView5.setTypeface(typefaceRoman);
	    textView6.setTypeface(typefaceRoman);
	    textView7.setTypeface(typefaceRoman);
	    textView8.setTypeface(typefaceRoman);
	    
		
	}

	public void BackClick(View v)  
    {  
		Intent intent18 = new Intent(this, ActivityAbout.class);
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
	 
	 if (id == R.id.Button02){
        	
        	if (status == 0){
        		Down.startAnimation(animationRotateUp);
        		 HideLayoutDown();
        		 LinearLayour122.setVisibility(View.VISIBLE);
        	status = 1;
        	}
        	else {
        		Up.startAnimation(animationRotateDown);
        		HideLayoutUp();
        		LinearLayour122.setVisibility(View.GONE);
	        	status = 0;
        	}
        	}   
	 
	 else if (id == R.id.button1){
		 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com/maxim_khaydarov")));
	 }
	 
	 else if (id == R.id.button2){
		 String[] TO = {"maxim.khaydarov@yandex.ru"};
	      
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/rfc822");


	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "fineSettings|FULL support");
	      emailIntent.putExtra(Intent.EXTRA_TEXT, getText(R.string.enter_text));

	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         finish();
	         Log.i("Finished sending email...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(this, 
	         "There is no email client installed.", Toast.LENGTH_SHORT).show();
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
        	 Intent intent18 = new Intent(this, ActivityAbout.class);
          	 startActivity(intent18);

    		overridePendingTransition(center_to_right, center_to_right2);
             return true;
         
     }
     return super.onKeyDown(keycode, e);
}
 
 
  
 protected void onResume() {
     super.onResume();
     
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
		    textView1.setTypeface(typefaceBold);
		    textView2.setTypeface(typefaceBold);
		    textView3.setTypeface(typefaceBold);
		    textView4.setTypeface(typefaceBold);
		    textView5.setTypeface(typefaceBold);
		    textView6.setTypeface(typefaceBold);
		    textView7.setTypeface(typefaceBold);
		    textView8.setTypeface(typefaceBold);
		    button1.setTypeface(typefaceBold);
		    button2.setTypeface(typefaceBold);
			
		}
 }
		
if (mSettings.contains(APP_PREFERENCES_text_size)) {
		// Получаем число из настроек
 	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
		if (size .contains( "Small")){
			Button02.setTextSize(13);
			button1.setTextSize(13);
			button2.setTextSize(13);
		    textView1.setTextSize(18);
		    textView2.setTextSize(11);
		    textView3.setTextSize(11);
		    textView4.setTextSize(11);
		    textView5.setTextSize(11);
		    textView6.setTextSize(11);
		    textView7.setTextSize(10);
		    textView8.setTextSize(10);
		}
		if (size .contains( "Normal")){
			Button02.setTextSize(15);
			button1.setTextSize(15);
			button2.setTextSize(15);
		    textView1.setTextSize(20);
		    textView2.setTextSize(13);
		    textView3.setTextSize(13);
		    textView4.setTextSize(13);
		    textView5.setTextSize(13);
		    textView6.setTextSize(13);
		    textView7.setTextSize(12);
		    textView8.setTextSize(12);
		}
		if (size .contains( "Large")){
			Button02.setTextSize(18);
			button1.setTextSize(18);
			button2.setTextSize(18);
		    textView1.setTextSize(23);
		    textView2.setTextSize(17);
		    textView3.setTextSize(17);
		    textView4.setTextSize(17);
		    textView5.setTextSize(17);
		    textView6.setTextSize(15);
		    textView7.setTextSize(15);
		    textView8.setTextSize(15);
		}
		if (size .contains( "xLarge")){
			Button02.setTextSize(20);
			button1.setTextSize(20);
			button2.setTextSize(20);
		    textView1.setTextSize(25);
		    textView2.setTextSize(18);
		    textView3.setTextSize(18);
		    textView4.setTextSize(18);
		    textView5.setTextSize(18);
		    textView6.setTextSize(18);
		    textView7.setTextSize(17);
		    textView8.setTextSize(17);
		}
}
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
            Intent it = new Intent(ActivityInformations.this, SettingsActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
            startActivity(it); 
 	        	overridePendingTransition(center_to_left, center_to_left2);
 	        	 
        }
   	

}
