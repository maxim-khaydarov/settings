package ua.mkh.settings.full;

import java.io.File;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

public class ChooseWallpaper extends Activity implements OnClickListener, SimpleGestureListener{

	public static final String APP_PREFERENCES = "mysettings"; 
	public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
	   
	   int menui = 0;
	   
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold;	   
	   
	   SharedPreferences mSettings;
	   private SimpleGestureFilter detector;
	   
	   Button btn_back, my_phot, button1;
	   
	   TextView din, stat, phot, textView4, textView5;
	   ProgressBar pb1;
	   MyTask mt;
	   Bitmap scaledBitmapCamera;
	   
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_choose_oboi);
	        String roman = "fonts/Regular.otf";
			String medium = "fonts/Medium.otf";
			String bold =  "fonts/Bold.otf";
			typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
			typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
			typefaceBold = Typeface.createFromAsset(getAssets(), bold);
			
			
			mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
			detector = new SimpleGestureFilter(this,this);
			
			TextView textStatus = (TextView)findViewById(R.id.textOk);
			btn_back = (Button) findViewById(R.id.buttonBack);
			
			din = (TextView) findViewById(R.id.textView1);
			stat = (TextView) findViewById(R.id.textView2);
			phot = (TextView) findViewById(R.id.textView3);
			textView4 = (TextView) findViewById(R.id.textView4);
			textView5 = (TextView) findViewById(R.id.textView5);
			my_phot = (Button) findViewById(R.id.Button04);
			button1 = (Button) findViewById(R.id.Button01);
			button1.setOnClickListener(this);
			my_phot.setOnClickListener(this);
			
			pb1 = (ProgressBar) findViewById(R.id.progressBar1);
			
			textStatus.setText(R.string.button_wallpaper);
			btn_back.setText(R.string.button_wallpaper);
	        textStatus.setTypeface(typefaceBold);
	        btn_back.setTypeface(typefaceMedium);
	        din.setTypeface(typefaceRoman);
	        stat.setTypeface(typefaceRoman);
	        phot.setTypeface(typefaceRoman);
	        textView4.setTypeface(typefaceRoman);
	        textView5.setTypeface(typefaceRoman);
	        my_phot.setTypeface(typefaceRoman);
	        button1.setTypeface(typefaceRoman);
	        
	       
	        mt = new MyTask();
	        mt.execute();
	        
	        RelativeLayout dynamikLayout = (RelativeLayout) findViewById(R.id.DynamikLayout);
	        dynamikLayout.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent();
		    		intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
		    		startActivity(intent);
		    		overridePendingTransition(center_to_left, center_to_left2);
	                  
	            }

	        });
	        
	        RelativeLayout staticLayout = (RelativeLayout) findViewById(R.id.StaticLayout);
	        staticLayout.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent(ChooseWallpaper.this, setWallpaper.class);
		    	 	 startActivity(intent);
		 	        	overridePendingTransition(center_to_left, center_to_left2);
	                  
	            }

	        });
	        
	   }

	   
	   private void preview() {
		   final String[] imageColumns = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA };
	        final String imageOrderBy = MediaStore.Images.Media._ID + " DESC";
	        Cursor imageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageColumns, null, null, imageOrderBy);
	        imageCursor.moveToFirst();
	        do {
	            String fullPath = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
	            if (fullPath.contains("DCIM")) {
	                //--last image from camera --
	     	            Bitmap myBitmap = BitmapFactory.decodeFile(fullPath);
	     	            
	     	           scaledBitmapCamera = scaleDown(myBitmap, 200, true);
	                return;
	            }
	        }
	        while (imageCursor.moveToNext());
	        
	        
	        	

		
	}
	   
	   public static Bitmap RotateBitmap(Bitmap source, float angle)
	   {
	         Matrix matrix = new Matrix();
	         matrix.postRotate(angle);
	         return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	   }


	public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
		        boolean filter) {
		    float ratio = Math.min(
		            (float) maxImageSize / realImage.getWidth(),
		            (float) maxImageSize / realImage.getHeight());
		    int width = Math.round((float) ratio * realImage.getWidth());
		    int height = Math.round((float) ratio * realImage.getHeight());

		    Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
		            height, filter);
		    return newBitmap;
		}
	   
	
	class MyTask extends AsyncTask<Void, Void, Void> {

	    @Override
	    protected void onPreExecute() {
	      super.onPreExecute();
	      pb1.setVisibility(View.VISIBLE);
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	preview() ;
	    	

	      return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	      super.onPostExecute(result);
	      ImageView myImageCamera = (ImageView) findViewById(R.id.imageView1);
	      try{
	      myImageCamera.setImageBitmap(scaledBitmapCamera);
	      }
	      catch(Exception s){
	    	  myImageCamera.setBackgroundResource(R.drawable.oboi);
	      }
	      pb1.setVisibility(View.GONE);
	    }
	  }
	
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		 switch(view.getId())  { 
		    case  R.id.Button04:
		    	Intent intent = new Intent("android.intent.action.SET_WALLPAPER");
		    	startActivity(intent);
		    	break;
		    case R.id.Button01:
		    	Intent intentw = new Intent("android.intent.action.SET_WALLPAPER");
		    	startActivity(intentw);
		    	break;
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
        
        getPhotoList();
        
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
				din.setTypeface(typefaceBold);
				stat.setTypeface(typefaceBold);
				phot.setTypeface(typefaceBold);
				my_phot.setTypeface(typefaceBold);
				textView4.setTypeface(typefaceBold);
				textView5.setTypeface(typefaceBold);
				
			}
        }
			
       if (mSettings.contains(APP_PREFERENCES_text_size)) {
			// Получаем число из настроек
        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
			if (size .contains( "Small")){
				din.setTextSize(14);
				stat.setTextSize(14);
				phot.setTextSize(11);
				my_phot.setTextSize(14);
				textView4.setTextSize(14);
				textView5.setTextSize(14);
			}
			if (size .contains( "Normal")){
				din.setTextSize(16);
				stat.setTextSize(16);
				phot.setTextSize(13);
				my_phot.setTextSize(16);
				textView4.setTextSize(16);
				textView5.setTextSize(16);
			}
			if (size .contains( "Large")){
				din.setTextSize(19);
				stat.setTextSize(19);
				phot.setTextSize(16);
				my_phot.setTextSize(19);
				textView4.setTextSize(19);
				textView5.setTextSize(19);
			}
			if (size .contains( "xLarge")){
				din.setTextSize(21);
				stat.setTextSize(21);
				phot.setTextSize(18);
				my_phot.setTextSize(21);
				textView4.setTextSize(21);
				textView5.setTextSize(21);
			}
       }
        
	}
	
	private void getPhotoList() {
   	 int photo_col = 0;
   	 
       final Cursor mCursor = getContentResolver().query(
               MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
               new String[] { MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA }, null, null,
               null);

       int i = 0;
       if (mCursor.moveToFirst()) {
           do {
               
               photo_col = photo_col + 1;
               i++;
           } while (mCursor.moveToNext());
       }   

       mCursor.close();
       
       textView5.setText(String.valueOf(photo_col));
       
       
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
            Intent it = new Intent(this, SettingsActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
            startActivity(it); 
 	        	overridePendingTransition(center_to_left, center_to_left2);
 	        	 }
        
   	
    	public void BackClick(View v)  
        {  
    		Intent intent18 = new Intent(this, ActivityOboi.class);
	       	 startActivity(intent18);
	        	overridePendingTransition(center_to_right, center_to_right2);
       	 }

}

