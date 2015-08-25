package ua.mkh.settings.full;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityCharge extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		String roman = "fonts/Regular.otf";

		Typeface typefaceRoman = Typeface.createFromAsset(getAssets(), roman);
		
		TextView textView1 = (TextView) findViewById(R.id.textView1);
		textView1.setTypeface(typefaceRoman);
		
		ImageView img = (ImageView) findViewById(R.id.imageView1);
		img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
	    
		  

		 
	}
	
	@Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            
            case KeyEvent.KEYCODE_BACK:
            	finish();
                return true;
            
        }
        return super.onKeyDown(keycode, e);
   }

}
