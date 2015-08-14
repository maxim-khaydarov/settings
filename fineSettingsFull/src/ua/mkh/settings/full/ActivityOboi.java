package ua.mkh.settings.full;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ActivityOboi extends Activity implements OnClickListener, SimpleGestureListener{

		final int RESULT_LOAD_IMAGE=1;
		
		public static final String APP_PREFERENCES = "mysettings"; 
		public static final String APP_PREFERENCES_text_size = "txt_size";
		   public static final String APP_PREFERENCES_bold_text = "bold_txt";
		   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
		   
		   int menui = 0;
		   
		   int center_to_right, center_to_right2;
		   int center_to_left, center_to_left2;
		   
		   
		   SharedPreferences mSettings;
		   
		   private SimpleGestureFilter detector;
			
		SeekBar brightbar;
		int brightness;
		TextView txtPerc, textView2, textView4, time, data, TextView01;
		ContentResolver cResolver;
		ToggleButton tb_brtns;
		Window window;
		Button  btn_back, Button03;
		ToggleButton tb_oboi;
		ImageView img_menu, img_lock;
		Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceUltraLight, typefaceThin;
		RelativeLayout RelativeLayoutView;
		MyTask mt;
		
		private final static int BUFFER_SIZE = 2048;
		
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oboi);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String ultraLight = "fonts/Ultralight.otf";
		String thin = "fonts/Thin.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceUltraLight = Typeface.createFromAsset(getAssets(), ultraLight);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);
		
		TextView textStatus = (TextView)findViewById(R.id.textOk);
		time = (TextView) findViewById(R.id.textView1);
		data = (TextView) findViewById(R.id.textView3);
		TextView01 = (TextView)findViewById(R.id.TextView01);
		textView2 = (TextView)findViewById(R.id.textView2);
		textView4 = (TextView)findViewById(R.id.textView4);
		
		btn_back = (Button) findViewById(R.id.buttonBack);
		Button03 = (Button) findViewById(R.id.Button03);
		Button03.setOnClickListener(this);
		img_menu = (ImageView)findViewById(R.id.imageView5);
		img_lock = (ImageView)findViewById(R.id.imageView4);
		
		RelativeLayoutView = (RelativeLayout) findViewById(R.id.RelativeLayoutView);
		RelativeLayoutView.setOnClickListener(this);
		
		WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
		Drawable wallpaperDrawable = wallpaperManager.getDrawable();
		img_menu.setImageDrawable(wallpaperDrawable);
		img_lock.setImageDrawable(wallpaperDrawable);
		img_menu.setScaleType(ImageView.ScaleType.CENTER_CROP);
		img_lock.setScaleType(ImageView.ScaleType.CENTER_CROP);
		/*
		mt = new MyTask();
	    mt.execute();
		*/
		 textStatus.setText(R.string.button_wallpaper);
		 btn_back.setText(R.string.app_name);
	        textStatus.setTypeface(typefaceMedium);
	        btn_back.setTypeface(typefaceMedium);
	        textView2.setTypeface(typefaceRoman);
	        textView4.setTypeface(typefaceThin);
	        TextView01.setTypeface(typefaceRoman);
	        Button03.setTypeface(typefaceRoman);
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
	        
	        
	        
	        
	       wallpaper_set();
	      // time_data();
	       
	       
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
					textView2.setTypeface(typefaceBold);
			        Button03.setTypeface(typefaceBold);
			        TextView01.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "16");
				if (size .contains( "Small")){
					textView2.setTextSize(11);
			        Button03.setTextSize(14);
			        TextView01.setTextSize(11);
				}
				if (size .contains( "Normal")){
					textView2.setTextSize(13);
			        Button03.setTextSize(16);
			        TextView01.setTextSize(13);
				}
				if (size .contains( "Large")){
					textView2.setTextSize(16);
			        Button03.setTextSize(19);
			        TextView01.setTextSize(16);
				}
				if (size .contains( "xLarge")){
					textView2.setTextSize(18);
			        Button03.setTextSize(21);
			        TextView01.setTextSize(18);
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
		textView4.setText(currentDateday);
		
		
	}
	
	    
		public void wallpaper_set (){
	    	WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
			Drawable wallpaperDrawable = wallpaperManager.getDrawable();
			img_menu.setImageDrawable(wallpaperDrawable);
			img_lock.setImageDrawable(wallpaperDrawable);
	    }
	    
	    public void onClick(View v) {
	    	int id = v.getId();
	    	
	    	if (id == R.id.Button03){
	    		Intent intent = new Intent(this, ChooseWallpaper.class);
	    	 	 startActivity(intent);
	 	        	overridePendingTransition(center_to_left, center_to_left2);
	    	}
	    	else if (id == R.id.RelativeLayoutView){
	    		wall();
	    		/*
	    		Intent myIntent = new Intent(this, ViewWallpaper.class);
	    		myIntent.putExtra("class","main");
	    		startActivity(myIntent);
	    		overridePendingTransition(R.anim.anim_up, R.anim.anim_null);*/
	    	}
	        	
	    }
	    
	    
	    public void wall(){
	    	 
		     final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
		     dialog.setContentView(R.layout.activity_view_wallpaper);
		     dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		     ImageView background = (ImageView)dialog.getWindow().findViewById(R.id.imageView1);
		     
		     WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
			 Drawable wallpaperDrawable = wallpaperManager.getDrawable();
			 background.setImageDrawable(wallpaperDrawable);//Button ButtonInfo = (Button)dialog.getWindow().findViewById(R.id.button1);
		     TextView time = (TextView)dialog.getWindow().findViewById(R.id.time);
		     TextView data = (TextView)dialog.getWindow().findViewById(R.id.data);
		     TextView textView1 = (TextView)dialog.getWindow().findViewById(R.id.textView1);
		     time.setTypeface(typefaceUltraLight);
		     data.setTypeface(typefaceThin);
		     textView1.setTypeface(typefaceThin);
			 Button ButtonMenuCancel = (Button)dialog.getWindow().findViewById(R.id.button1);
		     Button ButtonMenuSettings = (Button)dialog.getWindow().findViewById(R.id.button2);
		     ButtonMenuSettings.setTypeface(typefaceRoman);
		     ButtonMenuCancel.setTypeface(typefaceRoman);
		     //ButtonInfo.setTypeface(typefaceRoman);
		     //ButtonInfo.setText(R.string.menu_info_main);
		     
		     ButtonMenuCancel.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View v) {
		    dialog.dismiss();
		   }});
		     
		     ButtonMenuSettings.setOnClickListener(new OnClickListener(){

		  	   @Override
		  	   public void onClick(View v) {
		  		 dialog.dismiss();
		  	   }});
		     
		     
		     
		    
					
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
					
					
					
				
		     
		     dialog.show();
		    
   }
	    
	    
	public void BackClick(View v)  
    {  
		Intent intent18 = new Intent(this, MainActivity.class);
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
        Intent it = new Intent(ActivityOboi.this, SettingsActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        startActivity(it); 
	        	overridePendingTransition(center_to_left, center_to_left2);
    }

	
	class MyTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progress = null;
		
        @Override
        protected void onPreExecute() {
          super.onPreExecute();
          progress = ProgressDialog.show(ActivityOboi.this, null,"Wait...");
        }

        @Override
        protected Void doInBackground(Void... params) {
         // wallpaper ();
          return null;
        }

        @Override
        protected void onPostExecute(Void result) {
          super.onPostExecute(result);
          progress.dismiss();
        }
      }

    public void wallpaper (){
    	try {
            AssetManager assetFiles = getAssets();

            // MyHtmlFiles is the name of folder from inside our assets folder
            String[] files = assetFiles.list("wallpaper");

            // Initialize streams
            InputStream in = null;
            OutputStream out = null;

            for (int i = 0; i < files.length; i++) {

                if (files[i].toString().equalsIgnoreCase("images")
                        || files[i].toString().equalsIgnoreCase("js")) {

                    /*
                     * @Do nothing. images and js are folders but they will be
                     * interpreted as files.
                     * 
                     * @This is to prevent the app from throwing file not found
                     * exception.
                     */

                } else {
                	File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + "/fineSettings/wallpaper/");
                	// have the object build the directory structure, if needed.
                	wallpaperDirectory.mkdirs();
                    /*
                     * @Folder name is also case sensitive
                     * 
                     * @MyHtmlFiles is the folder from our assets
                     */
                    in = assetFiles.open("wallpaper/" + files[i]);

                    /*
                     * Currently we will copy the files to the root directory
                     * but you should create specific directory for your app
                     */
                    out = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/fineSettings/wallpaper/"
                                    + files[i]);
                    copyAssetFiles(in, out);

                }
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    
    private static void copyAssetFiles(InputStream in, OutputStream out) {
        try {

            byte[] buffer = new byte[BUFFER_SIZE];
            int read;

            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	}

