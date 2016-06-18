package ua.mkh.settings.full;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Wifi_Info {
	private String name;
	private String rssi;
	private String rssilevel;
	private String lock;
	private String mac;
	private String capabilities;

	public Wifi_Info(String name, String rssi, String lock, String mac, String capabilities, String rssilevel) {
		super();
		this.name = name;
		this.lock = lock;
		this.rssi = rssi;
		this.mac = mac;
		this.capabilities = capabilities;
		this.rssilevel = rssilevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}
	
	public String getRssilevel() {
		return rssilevel;
	}

	public void setRssilevel(String rssilevel) {
		this.rssilevel = rssilevel;
	}
}