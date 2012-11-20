package org.giv2giv;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity 
{
	public String[] CHARITIES = new String[]
	{ 
		"First Charity"
	};
	public String blurb = "This is a blurb that is inteded to talk about how awesome charities are" +
			"so when this actually gets put up on the alert dialog, it will look legit.";
	public ArrayList<String> myCharities;
	public ArrayAdapter<String> adapter;
	private boolean charityListPulled;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.charity_list);
        charityListPulled = false;
        myCharities = new ArrayList<String>();
        Charity[] charityList = UpdateQueue.GetCharityList(this);
        CHARITIES = new String[charityList.length];
        for (int i = 0; i < charityList.length; i++)
        {
        	CHARITIES[i] = charityList[i].mName;
        }
        blurb = charityList[1].mBlurb;
        ListView charities = (ListView)findViewById(R.id.charities);
        charities.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CHARITIES));
        charities.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
              // When clicked, show a toast with the TextView text
            	final TextView text = (TextView)view;
        		AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            	if (text.isEnabled())
            	{
                	builder.setTitle(text.getText()).setCancelable(false)
                	.setMessage(blurb)
                	.setPositiveButton("Add to my charities", new DialogInterface.OnClickListener() 
                	{
                		public void onClick(DialogInterface dialog, int id) 
                		{
                			text.setEnabled(false);
                			myCharities.add((String)text.getText());
                		}
                	})
                	.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
                	{
                		public void onClick(DialogInterface dialog, int id) 
                		{
                			dialog.cancel();
                		}
                	});
            	}
            	else
            	{
                	builder.setTitle(text.getText()).setCancelable(false)
                	.setMessage(blurb)
                	.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
                	{
                		public void onClick(DialogInterface dialog, int id) 
                		{
                			dialog.cancel();
                		}
                	})
                	.setPositiveButton("Remove from my charities", new DialogInterface.OnClickListener() 
                	{
                		public void onClick(DialogInterface dialog, int id) 
                		{
                			text.setEnabled(true);
                			myCharities.remove((String)text.getText());
                		}
                	});
            	}
            	AlertDialog alert = builder.create();
            	alert.show();
            }
        });

        Button listToDashButton = (Button)findViewById(R.id.listToDashButton);
        listToDashButton.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event)
        	{
                //CHARITIES = UpdateQueue.GetCharityList(v.getContext());
        		Intent dashboardScreen = new Intent(v.getContext(), DashboardActivity.class);
				Bundle charities = new Bundle();
				charities.putStringArrayList("charities", myCharities);
				dashboardScreen.putExtra("charity_info", charities);
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
}
