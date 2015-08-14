package ua.mkh.settings.full;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewWallpaper extends Activity implements OnClickListener, SimpleGestureListener{
	
	
	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
	   
	   final int RESULT_LOAD_IMAGE=1;
	   
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   
	   SharedPreferences mSettings;
	   
	   private SimpleGestureFilter detector;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceUltraLight, typefaceThin;
	   Button  btn_back, button1, button2;
	   TextView data, time;
	   ImageView background;
	   
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.activity_view_wallpaper);
	        String roman = "fonts/Regular.ttf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.ttf";
			String ultraLight = "fonts/Ultralight.ttf";
			String thin = "fonts/Thin.ttf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			typefaceUltraLight = Typeface.createFromAsset(getAssets(), ultraLight);
			typefaceThin = Typeface.createFromAsset(getAssets(), thin);
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			
			detector = new SimpleGestureFilter(this,this);
			
	//		TextView textStatus = (TextView)findViewById(R.id.textOk);
			time = (TextView) findViewById(R.id.time);
			data = (TextView) findViewById(R.id.data);
			background = (ImageView) findViewById(R.id.imageView1);
			button1 = (Button) findViewById(R.id.button1);
			button1.setOnClickListener(this);
			button2 = (Button) findViewById(R.id.button2);
			button2.setOnClickListener(this);
			
//			 textStatus.setText(R.string.button_wallpaper);
//			 btn_back.setText(R.string.app_name);
//		        textStatus.setTypeface(typefaceMedium);
//		        btn_back.setTypeface(typefaceMedium);
		        time.setTypeface(typefaceUltraLight);
		        data.setTypeface(typefaceThin);
			
			
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
	    	  Intent intent18 = new Intent(this, ActivityOboi.class);
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
		        
		      time_data();
		        
		        Intent myIntent = getIntent(); // gets the previously created intent
		        String firstKeyName = myIntent.getStringExtra("class"); 
		        
		        if(firstKeyName.contains("main")){
		        	 wallpaper_set();
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
							
							
						}
			        }
						
			       if (mSettings.contains(APP_PREFERENCES_text_size)) {
						// Получаем число из настроек
			        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "16");
						if (size .contains( "Small")){
							
						}
						if (size .contains( "Normal")){
							
						}
						if (size .contains( "Large")){
							
						}
						if (size .contains( "xLarge")){
							
						}
			       }
			       
			    }
			
			 
			public void time_data (){
				
				SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
				SimpleDateFormat day = new SimpleDateFormat("dd");
				SimpleDateFormat day2 = new SimpleDateFormat("EEEE");
				String currentDateday = day.format(new Date());
				String currentDateday2 = day2.format(new Date());
				
				//UK
				SimpleDateFormat sdfudd = new SimpleDateFormat("EEEE, ");
				SimpleDateFormat sdfud = new SimpleDateFormat("d ");
				SimpleDateFormat sdfum = new SimpleDateFormat("LLLL");
				String currentDateandudd = sdfudd.format(new Date());
				String currentDateandud = sdfud.format(new Date());
				String currentDateandum = sdfum.format(new Date());
				
				
				SimpleDateFormat sdfd = new SimpleDateFormat("EEEE, dd LLLL");
				String currentDateandTime = sdft.format(new Date());
				String currentDateandDate = sdfd.format(new Date());
				
				
				
				
				if (Locale.getDefault().getLanguage().contains("uk")){
					if (currentDateandum.contains("Грудень")){
						currentDateandum = "грудня";
					}
					else if (currentDateandum.contains("Січень")){
						currentDateandum = "січня";
					}
					else if (currentDateandum.contains("Лютий")){
						currentDateandum = "лютня";
					}
					else if (currentDateandum.contains("Березень")){
						currentDateandum = "березня";
					}
					else if (currentDateandum.contains("Квітень")){
						currentDateandum = "квітня";
					}
					else if (currentDateandum.contains("Травень")){
						currentDateandum = "травня";
					}
					else if (currentDateandum.contains("Червень")){
						currentDateandum = "червня";
					}
					else if (currentDateandum.contains("Липень")){
						currentDateandum = "липня";
					}
					else if (currentDateandum.contains("Серпень")){
						currentDateandum = "серпня";
					}
					else if (currentDateandum.contains("Вересень")){
						currentDateandum = "вересня";
					}
					else if (currentDateandum.contains("Жовтень")){
						currentDateandum = "жовтня";
					}
					else if (currentDateandum.contains("Листопад")){
						currentDateandum = "листопада";
					}
					data.setText(currentDateandudd + currentDateandud + currentDateandum);
				}
				
				
				
				else if (Locale.getDefault().getLanguage().contains("ru")){
					
					

					String ssz = currentDateandudd.substring(0,1).toUpperCase() + currentDateandudd.substring(1);
					
					if(currentDateandum.contains("Декабрь")){
						currentDateandum = "декабря";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Январь")){
						currentDateandum = "января";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Февраль")){
						currentDateandum = "февраля";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Март")){
						currentDateandum = "марта";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Апрель")){
						currentDateandum = "апреля";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Май")){
						currentDateandum = "мая";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Июнь")){
						currentDateandum = "июня";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Июль")){
						currentDateandum = "июля";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Август")){
						currentDateandum = "августа";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Сентябрь")){
						currentDateandum = "сентября";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Октябрь")){
						currentDateandum = "октября";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					else if(currentDateandum.contains("Ноябрь")){
						currentDateandum = "ноября";
						data.setText(ssz + currentDateandud + currentDateandum);
					}
					
					
					
				}
				else {
				data.setText(currentDateandDate);
				}
				
				time.setText(currentDateandTime);
				
				
				
			}
			
			
			public void wallpaper_set (){
		    	WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
				Drawable wallpaperDrawable = wallpaperManager.getDrawable();
				background.setImageDrawable(wallpaperDrawable);
				
		    }
		    
		    public void onClick(View v) {
		    	 switch(v.getId())  {
		    	 
		    	 case R.id.button1:
		    		  Intent intent = new Intent(this, ActivityOboi.class);
		    	 	 startActivity(intent);
		 	        	overridePendingTransition(R.anim.anim_up, R.anim.anim_down);
		 	        	break;
		 	        	
		    	 case R.id.button2:
		    		 Intent intent2 = new Intent(this, ActivityOboi.class);
		    	 	 startActivity(intent2);
		 	        	overridePendingTransition(R.anim.anim_null, R.anim.anim_down);
		 	        	break;
		    	 }
		    	
		    	
		 }
		    
		    
		    public void wall(){
		    	 
				     final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
				     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				     dialog.setContentView(R.layout.activity_view_wallpaper);
				     dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
				     //Button ButtonInfo = (Button)dialog.getWindow().findViewById(R.id.button1);
				     //Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuCancel);
				     //Button ButtonMenuSettings = (Button)dialog.getWindow().findViewById(R.id.ButtonMenuSettings);
				     //ButtonMenuSettings.setTypeface(typefaceRoman);
				     //ButtonMenuCancel.setTypeface(typefaceMedium);
				     //ButtonInfo.setTypeface(typefaceRoman);
				     //ButtonInfo.setText(R.string.menu_info_main);
				     
				     /*ButtonMenuCancel.setOnClickListener(new OnClickListener(){

				   @Override
				   public void onClick(View v) {
				    dialog.dismiss();
				   }});
				     
				     ButtonMenuSettings.setOnClickListener(new OnClickListener(){

				  	   @Override
				  	   public void onClick(View v) {
				  		 launchIntent();
				  	   }});
				     */
				     dialog.show();
				    
		    }
		    
		    
		    
		    public void BackClick(View v)  
		    {  
				Intent intent18 = new Intent(this, ActivityOboi.class);
		     	 startActivity(intent18);

				overridePendingTransition(center_to_right, center_to_right2);
		   	 }
			
			
			//// SET WALLPAPER
			protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		        super.onActivityResult(requestCode, resultCode, data);
		         
		        /*
		         * if request made to select a image from gallery
		         */
		        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
		           String wallpaper= selectedWallpaperImage(getApplicationContext(),data);
		           WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
		           
		           try {
		   wallpaperManager.setBitmap(BitmapFactory.decodeFile(wallpaper));
		                         //this line of code will set the selected image as your wallpaper...........
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		        }
		    }
		 public static String selectedWallpaperImage(Context context,Intent data){
			 
			  Uri selectedImage = data.getData();
			     String[] filePathColumn = { MediaStore.Images.Media.DATA };

			     Cursor cursor = context.getContentResolver().query(selectedImage,
			             filePathColumn, null, null, null);
			     cursor.moveToFirst();

			     int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			     String picturePath = cursor.getString(columnIndex);
			     cursor.close();
			 
			 return picturePath;
			 
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
		        	 Intent intent18 = new Intent(this, ActivityOboi.class);
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
		        Intent it = new Intent(ViewWallpaper.this, SettingsActivity.class);
		        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		        startActivity(it); 
			        	overridePendingTransition(center_to_left, center_to_left2);
		    }

			
		 
		 

}
