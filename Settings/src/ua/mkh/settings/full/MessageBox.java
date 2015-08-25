package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageBox extends Activity 
{
    /** Called when the activity is first created. */
	
	Typeface typefaceRoman, typefaceMedium, typefaceBold, typefaceUltraLight, typefaceThin;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_inform);
        String roman = "fonts/Regular.otf";
		String medium = "fonts/Medium.otf";
		String bold =  "fonts/Bold.otf";
		String ultraLight = "fonts/Ultralight.otf";
		String thin = "fonts/Thin.otf";
		typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		typefaceMedium = Typeface.createFromAsset(getAssets(), medium);
		typefaceBold = Typeface.createFromAsset(getAssets(), bold);
		typefaceUltraLight = Typeface.createFromAsset(getAssets(), ultraLight);
		typefaceThin = Typeface.createFromAsset(getAssets(), thin);
		
        TextView textBold = (TextView) findViewById(R.id.textBold);
        TextView text = (TextView) findViewById(R.id.text);
        
        textBold.setText(R.string.low_battery);
        String b = Float.toString(getBatteryLevel()).substring(0, Float.toString(getBatteryLevel()).length()-2);
        text.setText( b + getString(R.string.low_battery_text));
        
        Button btn=(Button) findViewById(R.id.dialogButtonOK);
        
        textBold.setTypeface(typefaceMedium);
        text.setTypeface(typefaceRoman);
        btn.setTypeface(typefaceRoman);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }
    
    public float getBatteryLevel() {
        Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        // Error checking that probably isn't needed but I added just in case.
        if(level == -1 || scale == -1) {
            return 50.0f;
        }

        return ((float)level / (float)scale) * 100.0f; 
    }
}
