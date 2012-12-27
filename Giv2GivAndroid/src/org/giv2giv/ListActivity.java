package org.giv2giv;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
	public Charity[] CHARITIES = {new Charity("cats", "Blurb") };
	public String blurb = "This is a blurb that is inteded to talk about how awesome charities are" +
			"so when this actually gets put up on the alert dialog, it will look legit.";
	public ArrayList<String> myCharities;
	public ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.charity_list);
        myCharities = new ArrayList<String>();
        //CHARITIES = UpdateQueue.GetCharityList(this);
        ListView charities = (ListView)findViewById(R.id.charities);
        charities.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
        		Charity.getCharityNameArray(CHARITIES)));
        charities.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
              // When clicked, show a toast with the TextView text
            	final TextView text = (TextView)view;
        		AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        		String charityName = text.getText().toString();
        		String blurb = Charity.getBlurbFromName(CHARITIES, charityName);
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
        
        Button searchButton = (Button)findViewById(R.id.charitySearch);
        searchButton.setOnTouchListener(new OnTouchListener()
        {
        		public boolean onTouch(View v, MotionEvent event)
        		{
        			DownloadCharitiesTask download = new DownloadCharitiesTask(v.getContext());
        			download.execute("");
        			return true;
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
    
    private class DownloadCharitiesTask extends AsyncTask<String, Void, Charity[]>
    {
    	private Context mContext;
    	private ProgressDialog mLoadingDialog;
    	
    	public DownloadCharitiesTask(Context context)
    	{
    		mContext = context;
    	}
    	
    	@Override
    	protected void onPreExecute()
    	{
    		mLoadingDialog = ProgressDialog.show(mContext, "", 
                    "Loading. Please wait...", true);
    	}
		@Override
		protected Charity[] doInBackground(String... queries) 
		{
			Charity[] response = new Charity[0];
			//Queries are unimplemented until we have a list so large
			//that a search function is necessary and is implemented
			//Until then just fetch the full charity list
			for (String query : queries)
			{
				response = UpdateQueue.GetCharityList(mContext);
			}
			return response;
		}
		@Override
		protected void onPostExecute(Charity[] result)
		{
	        ListView charities = (ListView)findViewById(R.id.charities);
	        charities.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, 
	        		Charity.getCharityNameArray(result)));
	        CHARITIES = result;
			mLoadingDialog.dismiss();
		}
    	
    }
    
    public static double round(double unrounded, int precision)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();
    }
}
