package ua.mkh.settings.full;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ActivityStart extends Activity implements OnClickListener {
	TextView textView1, textView2;
	Button button1;
	public static final String APP_PREFERENCES = "mysettings";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_start);
		String roman = "fonts/Regular.otf";
		String ultra = "fonts/Ultralight.otf";
		Typeface typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		Typeface typefaceUltra = Typeface.createFromAsset(getAssets(), ultra);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		textView1 = (TextView)findViewById(R.id.textView1);
		button1 = (Button)findViewById(R.id.button1);
		
		textView1.setTypeface(typefaceUltra);
		button1.setTypeface(typefaceRoman);

		

	    }

	    

	

		
	
	protected void onResume() {
        super.onResume();
        
        Log.d("Resume", "Start");
        
       
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
			
    
	}
	



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent1 = new Intent(this, MainActivity.class);
   	 startActivity(intent1);
     	overridePendingTransition(center_to_left, center_to_left2);
     	 }

}
