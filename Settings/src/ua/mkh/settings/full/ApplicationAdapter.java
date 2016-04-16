package ua.mkh.settings.full;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
    private List<ApplicationInfo> appsList = null;
    private Context context;
    private PackageManager packageManager;
    private Context mCtx;
    
    
    
    
    
    public ApplicationAdapter(Context context, int textViewResourceId,
            List<ApplicationInfo> appsList) {
        super(context, textViewResourceId, appsList);
        this.context = context;
        this.appsList = appsList;
        packageManager = context.getPackageManager();
        mCtx = context;
    }
 
    @Override
    public int getCount() {
        return ((null != appsList) ? appsList.size() : 0);
    }
 
    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != appsList) ? appsList.get(position) : null);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final TextView appSize;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.snippet_list_row_del, null);
            
        }
 
        ApplicationInfo data = appsList.get(position);
        if (null != data) {
        	String roman = "fonts/Regular.otf";
        	Typeface typefaceRoman = Typeface.createFromAsset(mCtx.getAssets(), roman);
        	
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            appSize = (TextView) view.findViewById(R.id.textSize);
            appName.setTypeface(typefaceRoman);
            appSize.setTypeface(typefaceRoman);
            ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);
 
            appName.setText(data.loadLabel(packageManager));
            
            
            
            iconview.setImageDrawable(data.loadIcon(packageManager));
            
            
                
            /*
            try {
				appSize.setText(Integer.toString((int)getApkSize(mCtx, data.packageName)/1024/1024));
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            */
            
            PackageManager pm = mCtx.getPackageManager();

            Method getPackageSizeInfo;
			try {
				getPackageSizeInfo = pm.getClass().getMethod(
				    "getPackageSizeInfo", String.class, IPackageStatsObserver.class);
			

            getPackageSizeInfo.invoke(pm, data.packageName,
                new IPackageStatsObserver.Stub() {

                    @Override
                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
                        throws RemoteException {

                        Log.i("TAG", "codeSize: " + pStats.codeSize);
                        appSize.setText(getFileSize(pStats.codeSize));
                    }
                });

			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
            
           
        return view;
    }
    
    public static long getApkSize(Context context, String packageName3)
	        throws NameNotFoundException {
	    return new File(context.getPackageManager().getApplicationInfo(
	            packageName3, 0).publicSourceDir).length();
	}
    
    public static String getFileSize(long size) {
        if (size <= 0)
            return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
    
    
    
};


