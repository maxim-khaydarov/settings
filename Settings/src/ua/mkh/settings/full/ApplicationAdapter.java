package ua.mkh.settings.full;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.graphics.Typeface;
import android.os.RemoteException;
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
            appName.setTypeface(typefaceRoman);
            ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);
 
            appName.setText(data.loadLabel(packageManager));
            
            
            iconview.setImageDrawable(data.loadIcon(packageManager));
            
            
            
            
        }
        return view;
    }
};


