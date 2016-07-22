package ua.mkh.settings.full;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ActivitySetRingtone extends Activity implements OnClickListener{

	 private List<String> myList;
	    File file;
	    private MediaPlayer mp;
	    
	    Button btn_back;
	    Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceThin;
	    TextView textStatus;
	    
	    public static final String APP_PREFERENCES = "mysettings"; 
		public static final String APP_PREFERENCES_text_size = "txt_size";
		   public static final String APP_PREFERENCES_bold_text = "bold_txt";
		   public static final String APP_PREFERENCES_ANIM_SPEED = "anim_speed";
		   public static final String APP_PREFERENCES_tgb_menu = "tgb_menu";
		   
		   int menui= 0;
			
		   int center_to_right, center_to_right2;
		   int center_to_left, center_to_left2;
		   
		   
		   SharedPreferences mSettings;

	  //  final int[] mSongs = new int[] { R.raw.ascending, R.raw.bark, R.raw.boing };
	    
	    private final String[] listContent = { "ascending", "bark", "boing" };
	    		
	    String aaa = null;		 

	    //String selectedName;
	    
   protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_set_ringtone);
     String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String thin = "fonts/Thin.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
     
     btn_back = (Button) findViewById(R.id.buttonBack);
		btn_back.setText(R.string.button_sound);
		textStatus = (TextView)findViewById(R.id.textOk);
		
		
		
		
		
		textStatus.setTypeface(typefaceBold);
		btn_back.setTypeface(typefaceMedium);
		textStatus.setText(R.string.ringtone);
		
		
     ListView listView = (ListView) findViewById(R.id.listtones);
     myList = new ArrayList<String>();
     
     mp = new MediaPlayer();

     File directory = Environment.getExternalStorageDirectory();
     //file = new File( directory + "/fineSettings/ringtones/" );
     file = new File( "/system/media/audio/ringtones" );
     /*
     boolean success = true;
     if (!file.exists()) {
         success = file.mkdir();
     }
     if (success) {
    	 
    	   for (int i = 0; i < mSongs.length; i++) {
    	       try {
    	           String path = Environment.getExternalStorageDirectory() + "/fineSettings/ringtones/";
    	           File dir = new File(path);
    	           if (dir.mkdirs() || dir.isDirectory()) {
    	               String str_song_name = "Ringtone " + i + ".ogg";
    	               CopyRAWtoSDCard(mSongs[i], path + File.separator + str_song_name);
    	           }
    	       } catch (IOException e) {
    	           e.printStackTrace();
    	       }
    	   }

    	
     } else {
         // Do something else on failure 
     }
     */
     
     
     
     File list[] = file.listFiles();

     for( int i=0; i< list.length; i++)
     {
    	 	String s = list[i].getName().substring(0,list[i].getName().length()-4);
             myList.add( s );
     }
     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
             R.layout.row_ringtone, R.id.tone, myList);
     listView.setAdapter(adapter); //Set all the file in the list.
     setListViewHeightBasedOnChildren(listView);
     
     
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
    				long id) {
    			String selectedName = ((TextView) itemClicked.findViewById(R.id.tone)).getText().toString();
    			
    			Toast.makeText(getApplicationContext(), selectedName,
    			        Toast.LENGTH_SHORT).show();
    			//playSong(position);
    			setRing(selectedName);
    			//aaa = selectedName;
    			BackClick();
    			
    			
    		}

			
    	});
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
   
   public void playSong(int songIndex) {
	   // Play song
	   mp.reset();// stops any current playing song
	//   mp = MediaPlayer.create(getApplicationContext(), mSongs[songIndex]);// create's
	   // new
	   // mediaplayer
	   // with
	   // song.
	   mp.start(); // starting mediaplayer
	   }
	 
	    
	   
	   @Override
	   
	   public void onDestroy() {
	   
	   super.onDestroy();
	   
	   mp.release();
	   
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
						
						
					}
		        }
					
		       if (mSettings.contains(APP_PREFERENCES_text_size)) {
					// Получаем число из настроек
		        	 String size = mSettings.getString(APP_PREFERENCES_text_size, "19");
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

private void CopyRAWtoSDCard(int id, String path) throws IOException {
   InputStream in = getResources().openRawResource(id);
   FileOutputStream out = new FileOutputStream(path);
   byte[] buff = new byte[1024];
   int read = 0;
   try {
       while ((read = in.read(buff)) > 0) {
           out.write(buff, 0, read);
       }
   } finally {
       in.close();
       out.close();
   }
}

	private void setRing (String selectedName){
		 //AssetManager assetManager = getAssets();
		/*
         File file = new File(Environment.getExternalStorageDirectory(),
                 "/myRingtonFolder/Audio/");
         if (!file.exists()) {
             file.mkdirs();
         }
		*/
         String path = Environment.getExternalStorageDirectory()
                 .getAbsolutePath() + "/fineSettings/ringtones";

         File out = new File(file + "/", selectedName);     
         /*if(!out.exists()){
             copyFile(assetManager, "Yeah.mp3", out);
         }           
*/			
         String s = selectedName.substring(0,selectedName.length()-4);
         
         ContentValues values = new ContentValues();
         values.put(MediaStore.MediaColumns.DATA, out.getAbsolutePath());
         values.put(MediaStore.MediaColumns.TITLE, selectedName);
         values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/ogg");
         values.put(MediaStore.MediaColumns.SIZE, out.length());
         values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
         values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
         values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
         values.put(MediaStore.Audio.Media.IS_ALARM, false);
         values.put(MediaStore.Audio.Media.IS_MUSIC, false);

         Uri uri = MediaStore.Audio.Media.getContentUriForPath(out.getAbsolutePath());
         ContentResolver mCr = getContentResolver();
         Uri newUri = mCr.insert(uri, values);

         try {
             RingtoneManager.setActualDefaultRingtoneUri(
                      getApplicationContext(), RingtoneManager.TYPE_RINGTONE, newUri);
             Settings.System.putString(mCr, Settings.System.RINGTONE,
                     newUri.toString());
         } 
         catch (Throwable t) 
         {
             //TODO Handle exception
         }
}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		 switch(view.getId())  { 
		 case R.id.button1:
			
			break;
		 }
	}

	public void BackClick(View v)  
    {  
    	Intent intent18 = new Intent(this, ActivityZvuki.class);
     	 startActivity(intent18);

		overridePendingTransition(center_to_right, center_to_right2);
   	 }
	private void BackClick() {
		// TODO Auto-generated method stub
		Intent intent18 = new Intent(this, ActivityZvuki.class);
     	 startActivity(intent18);
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
            	Intent intent18 = new Intent(this, ActivityZvuki.class);
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
            Intent it = new Intent(ActivitySetRingtone.this, SettingsActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
            startActivity(it); 
	        	overridePendingTransition(center_to_left, center_to_left2);
	        	 }
        
        
}


