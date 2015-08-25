package ua.mkh.settings.full;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiverOff extends BroadcastReceiver {

	
 @Override
 public void onReceive(Context mContext, Intent intent) {
  

	 timeSet t = new timeSet (mContext); 
		t.stop_notif (mContext);
	  
	  
 }
 
 
 
}

