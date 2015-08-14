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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class ActivityApps extends ListActivity implements  SimpleGestureListener{
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private ApplicationAdapter listadaptor = null;
    Button buttondel, buttonDel, buttonCancel;
    TextView text_app_main;
    int number = 0;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
	
    int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   SharedPreferences mSettings;
	   
	   private SimpleGestureFilter detector;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_osnova);
        
        String roman = "fonts/Regular.ttf";
		String medium = "fonts/Medium.otf";
		Typeface typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		Typeface typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        text_app_main = (TextView)findViewById(R.id.textOk);
	      text_app_main.setText(R.string.app_settings);
	      Button buttonBack = (Button) findViewById(R.id.buttonBack);
	      buttonBack.setTypeface(typefaceMedium);
	      text_app_main.setTypeface(typefaceMedium);
	      buttonDel = (Button)findViewById(R.id.buttonDel);
	      buttonCancel = (Button)findViewById(R.id.buttonCancel);
	      buttonCancel.setVisibility(View.INVISIBLE);
	      
	      mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

	      detector = new SimpleGestureFilter(this,this);
	      
 
        packageManager = getPackageManager();
 
        new LoadApplications().execute();
    }
 
    protected void onResume() {
        super.onResume();
        text_app_main.setText(R.string.app_settings);
        buttonDel.setVisibility(View.VISIBLE);
        buttonCancel.setVisibility(View.INVISIBLE);
        number = 0;
        
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
    	  Intent intent18 = new Intent(this, ActivityOsnova.class);
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
        
        
        	
        
        try {
        	if (number == 0){
            Intent intent = packageManager
                    .getLaunchIntentForPackage(app.packageName);
                startActivity(intent);
            }
        	if (number == 1){
        		Intent intent = new Intent(Intent.ACTION_DELETE);
        		intent.setData(Uri.parse("package:"+data.packageName));
        		startActivity(intent);
        	}
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ActivityApps.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ActivityApps.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
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
            setListAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
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
    
    
    
    
    public void DelClick(View v)  
    {  
    	buttonCancel.setVisibility(View.VISIBLE);
    	buttonDel.setVisibility(View.INVISIBLE);
    	text_app_main.setText(R.string.delete);
    	number = 1;
    }
    public void CancelClick(View v)  
    {  
    	buttonCancel.setVisibility(View.INVISIBLE);
    	buttonDel.setVisibility(View.VISIBLE);
    	text_app_main.setText(R.string.app_settings);
    	number = 0;
    }
    
    public void BackClick(View v)  
    {  
    	Intent intent18 = new Intent(this, ActivityOsnova.class);
     	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
   	 }

	@Override
	public void onDoubleTap() {
		// TODO Auto-generated method stub
		
	}
}
