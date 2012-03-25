package giv2giv.android;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity 
{
	static final String[] CHARITIES = new String[]
	{ 
		"American Cancer Society", "American Red Cross","Boy Scouts of America", "Boys & Girls Clubs of America", 
		"Childs Play", "Doctors Without Borders", "Girls Scouts of America", "Habitat for Humanity", 
		"The Salvation Army","The United Way"
		
	};
	public ArrayList<String> myCharities;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.charity_list);
        myCharities = new ArrayList<String>();
        ListView charities = (ListView)findViewById(R.id.charities);
        charities.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CHARITIES));
        charities.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
              // When clicked, show a toast with the TextView text
            	TextView text = (TextView)view;
            	if (view.isEnabled())
            	{
            		if (!myCharities.contains((String)text.getText()))
            		{
            			myCharities.add((String)text.getText());
            		}
            		view.setEnabled(false);
            	}
            	else
            	{
            		if (myCharities.contains((String)text.getText()))
	        		{
	        			myCharities.remove((String)text.getText());
	        		}
            		view.setEnabled(true);
            	}
            }
        });
        ImageButton dashButton = (ImageButton)findViewById(R.id.listDashButton);
        dashButton.setOnTouchListener(new OnTouchListener() 
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
        		Intent dashboardScreen = new Intent(v.getContext(), Giv2GivAndroidActivity.class);
				Bundle charities = new Bundle();
				charities.putStringArrayList("charities", myCharities);
				dashboardScreen.putExtra("charity_info", charities);
        		startActivity(dashboardScreen);
            	return true;
            }
            
        });
        ImageButton setButton = (ImageButton)findViewById(R.id.listSetButton);
        setButton.setOnTouchListener(new OnTouchListener() 
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
        		Intent settingsScreen = new Intent(v.getContext(), SettingsActivity.class);
        		//startActivity(settingsScreen);
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
}
