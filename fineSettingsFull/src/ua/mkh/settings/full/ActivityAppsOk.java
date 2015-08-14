package ua.mkh.settings.full;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class ActivityAppsOk extends ListActivity{
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    private ApplicationAdapter listadaptor = null;

    	public static final String APP_PREFERENCES = "mysettings"; 
	   public static final String APP_PREFERENCES_OK = "ok_app";
	   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		
	   int center_to_right, center_to_right2;
	   int center_to_left, center_to_left2;
	   
	   SharedPreferences mSettings;
	   String app_save_ok;
	   
	   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
 
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		Typeface typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		Typeface typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
        TextView text_app_main = (TextView)findViewById(R.id.textOk);
	      text_app_main.setText(R.string.choose);
	      Button buttonBack = (Button) findViewById(R.id.buttonBack);
	      buttonBack.setTypeface(typefaceMedium);
	      text_app_main.setTypeface(typefaceMedium);
	      
        packageManager = getPackageManager();
 
        new LoadApplications().execute();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
 
        ApplicationInfo app = applist.get(position);
        
        
            	 
        try {
        	app_save_ok = app.packageName;
   	      Intent intent = new Intent(this, ActivityiCloud.class);
            	 startActivity(intent);
            	      	overridePendingTransition(center_to_right, center_to_right2);
            	      	 
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ActivityAppsOk.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(ActivityAppsOk.this, e.getMessage(),
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
            listadaptor = new ApplicationAdapter(ActivityAppsOk.this,
                    R.layout.snippet_list_row, applist);
 
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
            progress = ProgressDialog.show(ActivityAppsOk.this, null,
                    "Загрузка приложений...");
            super.onPreExecute();
        }
 
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    
    @Override
	   protected void onPause() {
	   	// TODO Auto-generated method stub
	   	super.onPause();

	   	Editor editor = mSettings.edit();
	   	editor.putString(APP_PREFERENCES_OK, app_save_ok);
	   	editor.apply();
	   }
   
    
    public void BackClick(View v)  
    {  
    	Intent intent18 = new Intent(this, ActivityiCloud.class);
    	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
   	 }


    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_BACK:
            	Intent intent18 = new Intent(this, ActivityiCloud.class);
            	 startActivity(intent18);

       		overridePendingTransition(center_to_right, center_to_right2);
                return true;
            
        }
        return super.onKeyDown(keycode, e);
   }



	
}
