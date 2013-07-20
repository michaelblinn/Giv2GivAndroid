package org.giv2giv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LandingPageActivity extends Activity 
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.landing_page);
		Button landingLoginButton = (Button)findViewById(R.id.landing_login);
		Button landingSignupButton = (Button)findViewById(R.id.landing_signup);
		setButtonListener(landingLoginButton, LoginActivity.class);
		setButtonListener(landingSignupButton, SignupActivity.class);
//		Button loginButton = (Button)findViewById(R.id.connectButton);
//		final EditText usernameEntry = (EditText)findViewById(R.id.usernameField);
//		final EditText connectEntry = (EditText)findViewById(R.id.connectField);
//	    loginButton.setOnClickListener(new OnClickListener() 
//	    {
//	        @Override
//	        public void onClick(View v) 
//	        {
//	    		Intent listScreen = new Intent(v.getContext(), ListActivity.class);
//	    		//UpdateQueue.GetUserToken(v.getContext(), connectEntry.getText().toString());
//	    		if (UpdateQueue.GetUserToken(v.getContext(), "dodge"))
//	    		{
//	    			startActivity(listScreen);
//	    			finish();
//	    		}
//	        	return;
//	        }
//	    });
	}
	
	private void setButtonListener(Button button, final Class<?> destination)
	{
		button.setOnClickListener(new OnClickListener() 
	    {
		    public void onClick(View v) 
		    {
				Intent destinationActivity = new Intent(v.getContext(), destination);
				startActivity(destinationActivity);
		    }
		});
	}
}
