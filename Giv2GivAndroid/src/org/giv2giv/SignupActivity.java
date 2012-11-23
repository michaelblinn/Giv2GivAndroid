package org.giv2giv;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity
{
	private String[] signupInfo;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		signupInfo = new String[9];
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.sign_up_screen);
		Button nextButton = (Button)findViewById(R.id.signUpButton);
		/*Button loginButton = (Button)findViewById(R.id.connectButton);
		final EditText usernameEntry = (EditText)findViewById(R.id.usernameField);
		final EditText connectEntry = (EditText)findViewById(R.id.connectField);
		ImageButton setButton = (ImageButton)findViewById(R.id.dashSetButton);*/
        nextButton.setOnClickListener(new OnClickListener() 
        {
            @Override
            public void onClick(View v)
            {
            	Intent nextScreen = new Intent(v.getContext(), ConfirmationActivity.class);
            	//Second address is at the end as it is the only non-mandatory field
            	//This is done to simplify the check for empty responses immediately below
            	signupInfo[0] = ((EditText)findViewById(R.id.firstNameField)).getText().toString();
            	signupInfo[1] = ((EditText)findViewById(R.id.lastNameField)).getText().toString();
            	signupInfo[2] = ((EditText)findViewById(R.id.firstAddressField)).getText().toString();
            	signupInfo[3] = ((EditText)findViewById(R.id.cityField)).getText().toString();
            	signupInfo[4] = ((EditText)findViewById(R.id.stateField)).getText().toString();
            	signupInfo[5] = ((EditText)findViewById(R.id.zipcodeField)).getText().toString();
            	signupInfo[6] = ((EditText)findViewById(R.id.emailField)).getText().toString();
            	signupInfo[7] = ((EditText)findViewById(R.id.passwordField)).getText().toString();
            	signupInfo[8] = ((EditText)findViewById(R.id.secondAddressField)).getText().toString();
            	for (int i = 0; i < signupInfo.length - 1; i++)
            	{
            		if (signupInfo[i].equals(""))
            		{
            			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    	builder.setTitle("Missing Information").setCancelable(false)
                    	.setMessage("Please fill in all required fields")
                    	.setNegativeButton("Return", new DialogInterface.OnClickListener() 
                    	{
                    		public void onClick(DialogInterface dialog, int id) 
                    		{
                    			dialog.cancel();
                    		}
                    	});
                    	AlertDialog alert = builder.create();
                    	alert.show();
                    	return;
            		}
            	}
            	Bundle info = new Bundle();
            	info.putString("firstName", ((EditText)findViewById(R.id.firstNameField))
            			.getText().toString());
            	info.putString("lastName", ((EditText)findViewById(R.id.lastNameField))
            			.getText().toString());
            	info.putString("firstAddress", ((EditText)findViewById(R.id.firstAddressField))
            			.getText().toString());
            	info.putString("secondAddress", ((EditText)findViewById(R.id.secondAddressField))
            			.getText().toString());
            	info.putString("city", ((EditText)findViewById(R.id.cityField))
            			.getText().toString());
            	info.putString("state", ((EditText)findViewById(R.id.stateField))
            			.getText().toString());
            	info.putString("zip", ((EditText)findViewById(R.id.zipcodeField))
            			.getText().toString());
            	info.putString("email", ((EditText)findViewById(R.id.emailField))
            			.getText().toString());
            	info.putString("pass", ((EditText)findViewById(R.id.passwordField))
            			.getText().toString());
            	nextScreen.putExtra("info", info);
            	startActivity(nextScreen);
            	finish();
            	return;
            }
        });
	}
}