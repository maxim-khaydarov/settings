package ua.mkh.settings.full;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;



public class Blocker extends BroadcastReceiver {
	private static final int MODE_WORLD_READABLE = 1;
	private ITelephony telephonyService;
	private String incommingNumber;
	private String incommingName=null;
	private SharedPreferences Repeat; 
	SharedPreferences mSettings;
	MyTimerTask myTask;
	Timer myTimer;
	NotificationManager notificationManager;
	Notification myNotification;
	KeyguardManager myKM;
	String ring = null;
	AudioManager am;
	private SharedPreferences.Editor editor; 
	public static final String APP_PREFERENCES_DISTURB_ENABLE = "disturb_enable";
	public static final String APP_PREFERENCES_ALLOW_CALL = "allow_call";
	public static final String APP_PREFERENCES = "mysettings";
	public static final String APP_PREFERENCES_REPEAT_CALL = "repeat_call";
	public static final String APP_PREFERENCES_SILENCE_CALL = "silence_call";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// add PhoneStateListener for monitoring
				MyPhoneListener phoneListener = new MyPhoneListener(context);
				TelephonyManager telephonyManager = 
					(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				// receive notifications of telephony state changes 
				telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
				
				am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		
		 Repeat = context.getSharedPreferences("Repeat", MODE_WORLD_READABLE);
		 editor= Repeat.edit();
		 
		 mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		 String blockingMode = mSettings.getString(APP_PREFERENCES_ALLOW_CALL, "no one");
		 String numberBlock = Repeat.getString("number", "not number");
		 Boolean enable_disturb = mSettings.getBoolean(APP_PREFERENCES_DISTURB_ENABLE, false);
		 Boolean enable_repeate = mSettings.getBoolean(APP_PREFERENCES_REPEAT_CALL, false);
		 String silence = mSettings.getString(APP_PREFERENCES_SILENCE_CALL, "always");
		 
		 myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
		 myTask = new MyTimerTask();
	        Timer myTimer = new Timer();
		 
       if(enable_disturb != false) 
       {
		Bundle bb = intent.getExtras();  
	  String state = bb.getString(TelephonyManager.EXTRA_STATE);
	  if ((state != null)&& (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)))     
	  {
		  incommingNumber = bb.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
		  
		  
////////////////		  
		  if(blockingMode.contains("no one")){
		  
			  //////Блокировка экрана
			  if (enable_repeate == true){
				  if (numberBlock.contains(incommingNumber)){
					  editor.remove("number").commit();
				        if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
				        	ring = "1";
				        }
					  norm (context);
					  /*
					  if (silence.contains("locked")){
						  if( myKM.inKeyguardRestrictedInputMode()) {
						  blockCall(context, bb);
						  }
						  else {
							  
						  }*/
					  }
					  else {
						  if (silence.contains("locked")){
							  if( myKM.inKeyguardRestrictedInputMode()) {
							  blockCall(context, bb);
							  }
							  else {
							        if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
							        	ring = "1";
							        }
								  norm (context);
							  }
						  }
						  else {
							  blockCall(context, bb);
						  }
					  editor.putString("number", incommingNumber);
					  editor.commit();
					  myTimer.schedule(myTask, 180000);
					  }
				  }
			  else if (enable_repeate == false){
				  if (silence.contains("locked")){
					  if( myKM.inKeyguardRestrictedInputMode()) {
						  blockCall(context, bb);
					  }
					  else {
					        if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
					        	ring = "1";
					        }
						  norm (context);
					  }
				  }
				  else {
					  blockCall(context, bb);
				  }
		
			  }
			  
			  
		  }
		  
/////////////////////////		  
		  
		  else if(blockingMode.equals("contacts"))
		  {
		        incommingName=getContactDisplayNameByNumber(incommingNumber, context);
		        
		        
			   if((incommingName==null)||(incommingName.length()<=1)){
				   if (silence.contains("locked")){
						  if( myKM.inKeyguardRestrictedInputMode()) {
							  blockCall(context, bb);
						  }
						  else {
						        if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
						        	ring = "1";
						        }
							  norm (context);
						  }
				   }
			   }
			   else {
			        if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
			        	ring = "1";
			        }
				   norm (context);
			   }
			   
			   
			   
		  }
		   /*
		  else if(blockingMode.equals("list"))
		  {
			  RemindersDbAdapter mDbAdapter=new RemindersDbAdapter(context);
			  mDbAdapter.open();
			  Cursor c= mDbAdapter.fetchAllReminders();  
				  if(c.moveToFirst())
				  {
			   
			        while (c.isAfterLast() == false) {  
			        String	title= c.getString(c.getColumnIndex(RemindersDbAdapter.KEY_TITLE)); 
			        	  if(title.equals(incommingNumber))      
			        	 {
			        		 
								blockCall(context, bb);
			        	 }
			        	  c.moveToNext();
			        } 
				  }
		     c.close();
		     mDbAdapter.close();
		  }*/
		  else
		  {
			  
		        if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
		        	ring = "1";
		        }
			  norm (context);
		  }
          
		 
		 
		  
 }
       }
       String action = intent.getAction();

       PackageManager pm = context.getPackageManager();

       List<ApplicationInfo> list = pm.getInstalledApplications(0);

       for (int i = 0; i < list.size(); i++) {         
           if(list.get(i).packageName.equals("ua.mkh.finecharge")){
            
		  if(action == Intent.ACTION_POWER_CONNECTED){
			  
			  Intent intentone = new Intent(context, ActivityCharge.class);
			  intentone.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  intentone.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK); 
			  context.startActivity(intentone);
			  
		  }
		  else if(action == Intent.ACTION_BATTERY_LOW){
			  Intent scheduledIntent = new Intent(context, MessageBox.class);
		        scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        scheduledIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK); 
		        context.startActivity(scheduledIntent);
		  }
		  
       }

       }

    }
	
	
	
	
	public void blockCall(Context c, Bundle b)
	{
		
      TelephonyManager telephony = (TelephonyManager) 
      c.getSystemService(Context.TELEPHONY_SERVICE);  
      try {
       Class cls = Class.forName(telephony.getClass().getName());
       Method m = cls.getDeclaredMethod("getITelephony");
       m.setAccessible(true);
       telephonyService = (ITelephony) m.invoke(telephony);
       //telephonyService.silenceRinger();
       telephonyService.endCall();
      } catch (Exception e) {
       e.printStackTrace();
      }
      notif_block(c);
	}
	
	public void norm (Context context){
		AudioManager audio_mngr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		  audio_mngr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	}
	public void sile (Context context){
		AudioManager audio_mngr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		  audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	}
	
	public void notif_block (Context mContext){
		 Intent myIntent = new Intent(Intent.ACTION_DIAL);
		  PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		  
		  myNotification = new NotificationCompat.Builder(mContext)
		    .setContentTitle("")
		    .setContentText("Пропущенный вызов")
		    .setTicker("")
		    .setWhen(System.currentTimeMillis())
		    .setContentIntent(pendingIntent)
		    .setSmallIcon(R.drawable.ic_launcher)
		    .build();
		 
		  notificationManager = 
		    (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		  
		  notificationManager.notify(111111, myNotification);
	 }
	
	public String getContactDisplayNameByNumber(String number, Context c) {

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String name = "?";
        String data=null;
        ContentResolver contentResolver =c.getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
                ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

        try {
            if (contactLookup != null && contactLookup.getCount() > 0) {
                contactLookup.moveToNext();
                data = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                //String contactId = contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID));
            }
        } finally {
            if (contactLookup != null) {
                contactLookup.close();
               
            }
        }
        

        return data;
    }  
	
	class MyTimerTask extends TimerTask {
		  public void run() {
			  // ERROR
			  Editor editor = Repeat.edit();
			  editor.remove("number").commit();
			 // how update TextView in link below  
	                 // http://android.okhelp.cz/timer-task-timertask-run-cancel-android-example/
			 
		    
		  }
		}
	
	
	private class MyPhoneListener extends PhoneStateListener {
		 
		private boolean onCall = false;
		Context context;
		
		 public MyPhoneListener(Context context){
		       this.context = context;
		  }
		 
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
 
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				// phone ringing...
				
				
				break;
			
			case TelephonyManager.CALL_STATE_OFFHOOK:
				// one call exists that is dialing, active, or on hold
				
				//because user answers the incoming call
				onCall = true;
				break;

			case TelephonyManager.CALL_STATE_IDLE:
				// in initialization of the class and at the end of phone call 
				
				// detect flag from CALL_STATE_OFFHOOK
				try{
				if (ring.contains("1")) {
					sile (context);
					// restart our application
				}
				}
				catch (NullPointerException e){
					
				}
				break;
			default:
				break;
			}
			
		}
	}
}

