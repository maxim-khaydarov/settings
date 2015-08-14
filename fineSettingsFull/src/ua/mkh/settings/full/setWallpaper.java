package ua.mkh.settings.full;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ua.mkh.settings.full.ActivityOboi.MyTask;
import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class setWallpaper extends Activity implements SimpleGestureListener{

	
	ImageAdapter myImageAdapter;
	Bitmap decodedSampleBitmap;
	
	int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	
	   SharedPreferences mSettings;
	   
	   private SimpleGestureFilter detector;
	   
	TextView textStatus;
	Button btn_back;
	
	MyTask mt;
	
	int menui = 0;
	
	private final static int BUFFER_SIZE = 2048;
	
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin, typefaceUltraLight;
	
	public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	public static final String APP_PREFERENCES = "mysettings";
	
	 ImageAdapter imageAdapter;
	 GridView gridview;
	
	 
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setwall);
		
		String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String thin = "fonts/Thin.otf";
		String ultraLight = "fonts/Ultralight.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		typefaceUltraLight = Typeface.createFromAsset(getAssets(), ultraLight);
		
		textStatus = (TextView)findViewById(R.id.textOk);
        btn_back = (Button) findViewById(R.id.buttonBack);
        
        btn_back.setTypeface(typefaceMedium);
        textStatus.setTypeface(typefaceMedium);
        textStatus.setText(R.string.choose);
        btn_back.setText(R.string.button_wallpaper);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		detector = new SimpleGestureFilter(this,this);

		gridview = (GridView) findViewById(R.id.gridview);
		imageAdapter = new ImageAdapter(this);
		
		

		/*
		 * Move to asyncTaskLoadFiles String ExternalStorageDirectoryPath =
		 * Environment .getExternalStorageDirectory() .getAbsolutePath();
		 * 
		 * String targetPath = ExternalStorageDirectoryPath + "/test/";
		 * 
		 * Toast.makeText(getApplicationContext(), targetPath,
		 * Toast.LENGTH_LONG).show(); File targetDirector = new
		 * File(targetPath);
		 * 
		 * File[] files = targetDirector.listFiles(); for (File file : files){
		 * myImageAdapter.add(file.getAbsolutePath()); }
		 */
		
		TextView textView1 = new TextView(this);
		gridview.setOnItemClickListener(myOnItemClickListener);
		
		/*Button buttonReload = (Button)findViewById(R.id.reload);
		buttonReload.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				//Cancel the previous running task, if exist.
				myAsyncTaskLoadFiles.cancel(true);
				
				//new another ImageAdapter, to prevent the adapter have
				//mixed files
				myImageAdapter = new ImageAdapter(setWallpaper.this);
				gridview.setAdapter(myImageAdapter);
				myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
				myAsyncTaskLoadFiles.execute();
			}});
*/
	}

	OnItemClickListener myOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			wall(position); 
		}
	};
	public int calculateInSampleSize(

			BitmapFactory.Options options, int reqWidth, int reqHeight) {
				// Raw height and width of image
				final int height = options.outHeight;
				final int width = options.outWidth;
				int inSampleSize = 1;

				if (height > reqHeight || width > reqWidth) {
					if (width > height) {
						inSampleSize = Math.round((float) height
								/ (float) reqHeight);
					} else {
						inSampleSize = Math.round((float) width / (float) reqWidth);
					}
				}

				return inSampleSize;
			}

	public class ImageAdapter extends BaseAdapter {

		private Context mContext;
		 ArrayList<String> itemList = new ArrayList<String>();
	    // Keep all Images in array
	    public Integer[] mThumbIds = {
	            R.drawable.pic_1s, R.drawable.pic_2s,
	            R.drawable.pic_3s, R.drawable.pic_4s,
	            R.drawable.pic_5s, R.drawable.pic_6s,
	            R.drawable.pic_7s, R.drawable.pic_8s,
	            R.drawable.pic_9s, R.drawable.pic_10s,
	            R.drawable.pic_11s, R.drawable.pic_12s
	    };
	 
	    // Constructor
	    public ImageAdapter(Context c){
	        mContext = c;
	    }
	 
	    @Override
	    public int getCount() {
	        return mThumbIds.length;
	    }
	 
	    @Override
	    public Object getItem(int position) {
	    	return mThumbIds[position];
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return 0;
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView = new ImageView(mContext);
	        imageView.setImageResource(mThumbIds[position]);
	        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        
	        float scale = getResources().getDisplayMetrics().density;
	        int dpAsPixels100 = (int) (100*scale + 0.5f);
	        int dpAsPixels210 = (int) (210*scale + 0.5f);
	        
	        //imageView.setLayoutParams(new GridView.LayoutParams(dpAsPixels100, dpAsPixels210));
	        return imageView;
	    }
	 
		}

		public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth,
				int reqHeight) {

			Bitmap bm = null;
			// First decode with inJustDecodeBounds=true to check dimensions
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth,
					reqHeight);

			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			bm = BitmapFactory.decodeFile(path, options);

			return bm;
		}

		
	

class MyTask2 extends AsyncTask<Void, Void, Void> {

	private ProgressDialog progress = null;
	
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progress = ProgressDialog.show(setWallpaper.this, null,"Wait...");
    }

    @Override
    protected Void doInBackground(Void... params) {
    	WallpaperManager wm = WallpaperManager.getInstance(setWallpaper.this);
	    try {
	        wm.setBitmap(decodedSampleBitmap);
	        
	    } catch (IOException e) {
	        Log.e("TAG", "Cannot set image as wallpaper", e);
	    }
      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);
      progress.dismiss();
      Toast.makeText(getApplicationContext(), "Set Wallpaper", Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(setWallpaper.this, ActivityOboi.class);
 	 startActivity(intent);
	        	overridePendingTransition(center_to_right, center_to_right2);
    }
  }



protected void onResume() {
    super.onResume();
   
    gridview.setAdapter(imageAdapter);
    
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


public void wall(final int promp){
	 
    final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
    dialog.setContentView(R.layout.activity_view_wallpaper);
    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    final ImageView background = (ImageView)dialog.getWindow().findViewById(R.id.imageView1);
    
   
    if (promp == 0){
	 background.setImageResource(R.drawable.pic_1);
	 background.setTag(R.drawable.pic_1);
    }
    else if (promp == 1){
    	background.setTag(R.drawable.pic_2);;
    	background.setImageResource(R.drawable.pic_2);
    }
    else if (promp == 2){
    	background.setTag(R.drawable.pic_3);
    	background.setImageResource(R.drawable.pic_3);
    }
    else if (promp == 3){
    	background.setTag(R.drawable.pic_4);
    	background.setImageResource(R.drawable.pic_4);
    }
    else if (promp == 4){
    	background.setTag(R.drawable.pic_5);
    	background.setImageResource(R.drawable.pic_5);
    }
    else if (promp == 5){
    	background.setTag(R.drawable.pic_6);
    	background.setImageResource(R.drawable.pic_6);
    }
    else if (promp == 6){
    	background.setTag(R.drawable.pic_7);
    	background.setImageResource(R.drawable.pic_7);
    }
    else if (promp == 7){
    	background.setTag(R.drawable.pic_8);
    	background.setImageResource(R.drawable.pic_8);
    }
    else if (promp == 8){
    	background.setTag(R.drawable.pic_9);
    	background.setImageResource(R.drawable.pic_9);
    }
    else if (promp == 9){
    	background.setTag(R.drawable.pic_10);
    	background.setImageResource(R.drawable.pic_10);
    }
    else if (promp == 10){
    	background.setTag(R.drawable.pic_11);
    	background.setImageResource(R.drawable.pic_11);
    }
    else if (promp == 11){
    	background.setTag(R.drawable.pic_12);
    	background.setImageResource(R.drawable.pic_12);
    }
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
 		   
 		  //Drawable myDrawable = background.getDrawable();
 		 int uri = (Integer)background.getTag();
 		
 		Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(uri));

 		DisplayMetrics metrics = new DisplayMetrics(); 
 		getWindowManager().getDefaultDisplay().getMetrics(metrics);
 		// get the height and width of screen 
 		int height = metrics.heightPixels; 
 		int width = metrics.widthPixels;


 		WallpaperManager wallpaperManager = WallpaperManager.getInstance(setWallpaper.this); 
 		try {
 		wallpaperManager.setBitmap(bitmap);

 		wallpaperManager.suggestDesiredDimensions(width, height);
 		//Toast.makeText(setWallpaper.this, "Wallpaper Set", Toast.LENGTH_SHORT).show();
 		dialog.dismiss();
 		} catch (IOException e) {
 		e.printStackTrace();
 		//Toast.makeText(setWallpaper.this, "Error", Toast.LENGTH_SHORT).show();
 		dialog.dismiss();
 		}
 		
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
	  Intent intent18 = new Intent(this, ChooseWallpaper.class);
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

public void BackClick(View v)  
{  
	Intent intent18 = new Intent(this, ChooseWallpaper.class);
 	 startActivity(intent18);

	overridePendingTransition(center_to_right, center_to_right2);
     
}

@Override
public boolean onKeyDown(int keycode, KeyEvent e) {
    switch(keycode) {
       
        case KeyEvent.KEYCODE_BACK:
        	Intent intent18 = new Intent(this, ChooseWallpaper.class);
         	 startActivity(intent18);

   		overridePendingTransition(center_to_right, center_to_right2);
            return true;
        
    }
    return super.onKeyDown(keycode, e);
}




}
