package ua.mkh.settings.full;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.support.v4.app.NotificationCompat;

public class timeSet {
	final static int RQS_1 = 1;
	Context kContext;
	private static final int MY_NOTIFICATION_ID=1;
	NotificationManager notificationManager;
	Notification myNotification;
	public static final String APP_PREFERENCES_DISTURB_ENABLE = "disturb_enable";

	public static final String APP_PREFERENCES = "mysettings";
		
	
	 public timeSet(Context kContext){
	       this.kContext = kContext;
	  }
	
	 public void setAlarm_from(Calendar targetCalFrom){
		  
		  Intent intent = new Intent(kContext, AlarmReceiverOn.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,targetCalFrom.getTimeInMillis(), 24*60*60*1000, pendingIntent);
		  
		 }
	 
	 public void setStop_from () {
			
			Intent intent = new Intent(kContext, AlarmReceiverOn.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(pendingIntent);
		}

	 public void setAlarm_to(Calendar targetCalTo){
		  
		  Intent intent = new Intent(kContext, AlarmReceiverOff.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCalTo.getTimeInMillis(), 24*60*60*1000, pendingIntent);
		  
		 }
	 
	 public void setStop_to () {
			
			Intent intent = new Intent(kContext, AlarmReceiverOff.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(pendingIntent);
		}
///////////////////////////////////////////////////////
	 
	 public void setAlarm_from_manual(){
		  
		  Intent intent = new Intent(kContext, AlarmReceiverOn.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		  alarmManager.set(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
		  
		 }
	 
	 public void setStop_from_manual() {
			
			Intent intent = new Intent(kContext, AlarmReceiverOff.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(pendingIntent);
		}
	 
	 public void setAlarm_to_manual(){
		  
		  Intent intent = new Intent(kContext, AlarmReceiverOff.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
		  AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
		  alarmManager.set(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
		  
		 }
	 
	 public void setStop_to_manual() {
			
			Intent intent = new Intent(kContext, AlarmReceiverOff.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(kContext, RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager)kContext.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(pendingIntent);
		}
	 
	 
	 /////////////////////////////////////////////
	 ///////////////////////////////////////
	 
	 public void start_notif (Context kContext){
		 AudioManager audio_mngr = (AudioManager) kContext.getSystemService(Context.AUDIO_SERVICE);
		  audio_mngr.setRingerMode(AudioManager.RINGER_MODE_SILENT); 
		  
		  SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		  
		  Intent myIntent = new Intent(kContext, ActivityDisturb.class);
		  PendingIntent pendingIntent = PendingIntent.getActivity(kContext, 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		  
		  CharSequence Title = kContext.getString(R.string.do_not_disturb_title);
		  CharSequence Ticker = kContext.getString(R.string.do_not_disturb_on);
		  
		  myNotification = new NotificationCompat.Builder(kContext)
		    .setContentTitle(Title)
		    .setContentText("")
		    .setTicker(Ticker)
		    .setWhen(System.currentTimeMillis())
		    .setContentIntent(pendingIntent)
		    .setSmallIcon(R.drawable.ic_stat_notify)
		    .build();
		  
		  myNotification.flags = Notification.FLAG_NO_CLEAR;
		  notificationManager = 
		    (NotificationManager)kContext.getSystemService(Context.NOTIFICATION_SERVICE);
		  
		  notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
		  
		  Editor editor = mSettings.edit();
		 	editor.putBoolean(APP_PREFERENCES_DISTURB_ENABLE, true);
		 	editor.commit();
	 }
	 
	 public void stop_notif (Context kContext){
		 AudioManager audio_mngr = (AudioManager) kContext.getSystemService(Context.AUDIO_SERVICE);
		  audio_mngr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		  notificationManager = (NotificationManager)kContext.getSystemService(Context.NOTIFICATION_SERVICE);
		  notificationManager.cancel(MY_NOTIFICATION_ID);

		  
		  SharedPreferences mSettings = kContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		  
		  Editor editor = mSettings.edit();
		 	editor.putBoolean(APP_PREFERENCES_DISTURB_ENABLE, false);
		 	editor.commit();
	 }
	
}

