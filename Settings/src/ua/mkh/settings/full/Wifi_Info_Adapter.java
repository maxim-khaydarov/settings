package ua.mkh.settings.full;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ua.mkh.settings.full.DeviceListAdapter.OnPairButtonClickListener;

public class Wifi_Info_Adapter extends ArrayAdapter<Wifi_Info> {

	Context context;
	int layoutResourceId;
	ArrayList<Wifi_Info> wifi_infoq = new ArrayList<Wifi_Info>();
	private OnPairButtonClickListener mListener;
	

	public Wifi_Info_Adapter(Context context, int layoutResourceId,
			ArrayList<Wifi_Info> wifi_info) {
		super(context, layoutResourceId, wifi_info);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.wifi_infoq = wifi_info;
	}
	
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View item = convertView;
		StudentWrapper StudentWrapper = null;

		if (item == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			item = inflater.inflate(layoutResourceId, parent, false);
			StudentWrapper = new StudentWrapper();
			StudentWrapper.name = (TextView) item.findViewById(R.id.list_value);
			StudentWrapper.lock = (ImageView) item.findViewById(R.id.imageView1111);
			StudentWrapper.rssi = (ImageView) item.findViewById(R.id.imageView1);
			StudentWrapper.info = (Button) item.findViewById(R.id.button1);
			item.setTag(StudentWrapper);
		} else {
			StudentWrapper = (StudentWrapper) item.getTag();
		}

		final Wifi_Info wifi_info2 = wifi_infoq.get(position);
		StudentWrapper.name.setText(wifi_info2.getName());
		
		
		StudentWrapper.rssi.setImageResource(Integer.parseInt(wifi_info2.getRssi()));
		
		if(wifi_info2.getLock() != null){
			StudentWrapper.lock.setImageResource(Integer.parseInt(wifi_info2.getLock()));
		}
		
		//StudentWrapper.mac.setText(wifi_info2.getMac());
		//StudentWrapper.capabilities.setText(wifi_info2.getCapabilities());
		/*
		if(wifi_info2.getCapabilities().contains("WEP")){
		Log.e("CAP", wifi_info2.getCapabilities());
	}*/
		StudentWrapper.info.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((ActivityWifi)context).info_wifi(position, wifi_info2.getName(), wifi_info2.getRssilevel(), wifi_info2.getMac(), wifi_info2.getCapabilities(), wifi_info2.getRssilevel());
				
			}
		});
		
		item.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onPairButtonClick(position);
					((ActivityWifi)context).connectToWifi(position);
				}
			}
		});

		
		return item;

	}

	static class StudentWrapper {
		TextView name;
		ImageView lock;
		ImageView rssi;
		TextView mac;
		TextView capabilities;
		Button info;
		String rssilevel;
	}
	
	public interface OnPairButtonClickListener {
		public abstract void onPairButtonClick(int position);
	}
	
	public void setListener(OnPairButtonClickListener listener) {
		mListener = listener;
	}
}
