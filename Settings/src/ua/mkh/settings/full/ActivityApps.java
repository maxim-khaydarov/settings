package ua.mkh.settings.full;

import java.util.ArrayList;
import java.util.List;

import ua.mkh.settings.full.SimpleGestureFilter.SimpleGestureListener;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class ActivityApps extends ListActivity implements  SimpleGestureListener{
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private ApplicationAdapter listadaptor = null;
    Button Button08, Button09, btn_back;
    TextView text_app_main, usage, aviable;
    int number = 0;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
    public static final String APP_PREFERENCES_text_size = "txt_size";
	   public static final String APP_PREFERENCES_bold_text = "bold_txt";
	
    int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   SharedPreferences mSettings;
	   
	   private SimpleGestureFilter detector;
	   
	   Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	   
	   
	   ListView u;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_osnova);
        
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		Typeface typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		Typeface typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
        text_app_main = (TextView)findViewById(R.id.textOk);
	      text_app_main.setText(R.string.manage_storage);
	      btn_back = (Button) findViewById(R.id.buttonBack);
			btn_back.setText(R.string.button_usage);
	      
	      usage = (TextView) findViewById(R.id.TextView07);
	      aviable = (TextView) findViewById(R.id.TextView08);
	      
	      Button08 = (Button) findViewById(R.id.Button08);
	      Button09 = (Button) findViewById(R.id.Button09);
	      
	      u = getListView();
	      
	      
	      btn_back.setTypeface(typefaceMedium);
	      text_app_main.setTypeface(typefaceBold);
	      usage.setTypeface(typefaceRoman);
	      aviable.setTypeface(typefaceRoman);
	      Button08.setTypeface(typefaceRoman);
	      Button09.setTypeface(typefaceRoman);
	      
	      Intent intent = getIntent();
	      
	      usage.setText(intent.getStringExtra("used"));
	      aviable.setText(intent.getStringExtra("aviable"));
	      
	      
	      mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

	      detector = new SimpleGestureFilter(this,this);
	      
 
        packageManager = getPackageManager();
 
        new LoadApplications().execute();
    }
 
    protected void onResume() {
        super.onResume();
        
        
       
        
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
					text_app_main.setTypeface(typefaceBold);
	    			btn_back.setTypeface(typefaceBold);
	    			usage.setTypeface(typefaceBold);
	    		      aviable.setTypeface(typefaceBold);
	    		      Button08.setTypeface(typefaceBold);
	    		      Button09.setTypeface(typefaceBold);
					
				}
	        }
				
	       if (mSettings.contains(APP_PREFERENCES_text_size)) {
				// Получаем число из настроек
	        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
				if (size .contains( "Small")){
					usage.setTextSize(13);
	    		      aviable.setTextSize(13);
	    		      Button08.setTextSize(13);
	    		      Button09.setTextSize(13);
				}
				if (size .contains( "Normal")){
					usage.setTextSize(16);
	    		      aviable.setTextSize(16);
	    		      Button08.setTextSize(16);
	    		      Button09.setTextSize(16);
				}
				if (size .contains( "Large")){
					usage.setTextSize(19);
	    		      aviable.setTextSize(19);
	    		      Button08.setTextSize(19);
	    		      Button09.setTextSize(19);
				}
				if (size .contains( "xLarge")){
					usage.setTextSize(21);
	    		      aviable.setTextSize(21);
	    		      Button08.setTextSize(21);
	    		      Button09.setTextSize(21);
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
    	  Intent intent18 = new Intent(this, ActivityUsage.class);
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
      /*
     @Override
     public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
     }
 */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
 
        ApplicationInfo app = applist.get(position);
        ApplicationInfo data = applist.get(position);
        
        
        	
        
        
            Intent intent = packageManager
                    .getLaunchIntentForPackage(app.packageName);
                startActivity(intent);
            
    }
 
    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        return applist;
    }
 
    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;
 
        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadaptor = new ApplicationAdapter(ActivityApps.this,
                    R.layout.snippet_list_row_del, applist);
 
            
            
            return null;
        }
 
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            
            
            setListAdapter(listadaptor);
            setListViewHeightBasedOnChildren(u);
            progress.dismiss();
            
        }
 
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ActivityApps.this, null,"Download");
            super.onPreExecute();
        }
 
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    
    
    
    
    
    
    public void BackClick(View v)  
    {  
    	Intent intent18 = new Intent(this, ActivityUsage.class);
     	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
   	 }

	@Override
	public void onDoubleTap() {
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

}
