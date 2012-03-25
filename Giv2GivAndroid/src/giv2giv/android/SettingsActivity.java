package giv2giv.android;

import java.math.BigDecimal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class SettingsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.settings);
        ImageButton charityListButton = (ImageButton)findViewById(R.id.setListButton);
        charityListButton.setOnTouchListener(new OnTouchListener() 
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
            	/*if (getCallingActivity() != null)
            	{
            		setResult(Activity.RESULT_FIRST_USER);
            		finish();
            	}
            	else
            	{
            		finish();
            	}*/
            	Intent listScreen = new Intent(v.getContext(), ListActivity.class);
            	startActivity(listScreen);
            	return true;
            }
            
        });
        ImageButton dashboardButton = (ImageButton)findViewById(R.id.setDashButton);
        dashboardButton.setOnTouchListener(new OnTouchListener() 
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
            	/*if (getCallingActivity() != null)
            	{
            		setResult(Activity.RESULT_OK);
            		finish();
            	}
            	else
            	{
            		Intent dashboardScreen = new Intent(v.getContext(), Giv2GivAndroidActivity.class);
            		startActivityForResult(dashboardScreen, 42);
            	}*/
        		Intent dashboardScreen = new Intent(v.getContext(), Giv2GivAndroidActivity.class);
        		startActivity(dashboardScreen);
            	return true;
            }
            
        });
    }
    
    public static double round(double unrounded, int precision)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent Data)
    {
    	if (requestCode == 42 && resultCode == Activity.RESULT_FIRST_USER)
    	{
    		finish();
    	}
    }
}
