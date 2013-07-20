package org.giv2giv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login_page);
		
//		Button loginButton = (Button)findViewById(R.id.connectButton);
//		final EditText usernameEntry = (EditText)findViewById(R.id.usernameField);
//		final EditText connectEntry = (EditText)findViewById(R.id.connectField);
//        loginButton.setOnClickListener(new OnClickListener() 
//        {
//            @Override
//            public void onClick(View v) 
//            {
//        		Intent listScreen = new Intent(v.getContext(), ListActivity.class);
//        		//UpdateQueue.GetUserToken(v.getContext(), connectEntry.getText().toString());
//        		if (UpdateQueue.GetUserToken(v.getContext(), "dodge"))
//        		{
//        			startActivity(listScreen);
//        			finish();
//        		}
//            	return;
//            }
//        });
	}
}