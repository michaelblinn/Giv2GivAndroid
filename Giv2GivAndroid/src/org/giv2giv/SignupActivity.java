package org.giv2giv;

import java.util.Hashtable;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity
{
	private Hashtable<String, Pair<Boolean, String>> signupInfo;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		signupInfo = new Hashtable<String, Pair<Boolean, String>>();
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
            	signupInfo.put("First Name", new Pair<Boolean, String>(true, 
            			((EditText)findViewById(R.id.firstNameField)).getText().toString()));
            	signupInfo.put("Last Name", new Pair<Boolean, String>(true, 
            			((EditText)findViewById(R.id.lastNameField)).getText().toString()));
            	signupInfo.put("First Address", new Pair<Boolean, String>(false, 
            			((EditText)findViewById(R.id.firstAddressField)).getText().toString()));
            	signupInfo.put("Second Address", new Pair<Boolean, String>(false, 
            			((EditText)findViewById(R.id.secondAddressField)).getText().toString()));
            	signupInfo.put("City",new Pair<Boolean, String>(false, 
            			((EditText)findViewById(R.id.cityField)).getText().toString()));
            	signupInfo.put("State", new Pair<Boolean, String>(false, 
            			((EditText)findViewById(R.id.stateField)).getText().toString()));
//            	signupInfo.put("country", new Pair<Boolean, String>(false, 
//            			((EditText)findViewById(R.id.stateField)).getText().toString()));
            	signupInfo.put("Email Address", new Pair<Boolean, String>(true, 
            			((EditText)findViewById(R.id.emailField)).getText().toString()));
            	signupInfo.put("Password", new Pair<Boolean, String>(true, 
            			((EditText)findViewById(R.id.passwordField)).getText().toString()));
            	signupInfo.put("Password Confirmation", new Pair<Boolean, String>(true, 
            			((EditText)findViewById(R.id.confPasswordField)).getText().toString()));

        		Log.i("SIGNUP_INFO", "" + signupInfo.entrySet().size());
            	for (Entry<String, Pair<Boolean, String>> entry : signupInfo.entrySet())
            	{
            		Pair<Boolean, String> value = (Pair<Boolean, String>)entry.getValue();
            		Log.i("SIGNUP_INFO", entry.getKey() + value.second);
            		if (value.first && value.second.equals(""))
            		{
            			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    	builder.setTitle("Missing Information").setCancelable(false)
                    	.setMessage("Please fill in all required fields: " + entry.getKey())
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
            		//if (signupInfo[])
            	}
//            	for (int i = 0; i < signupInfo.length - 1; i++)
//            	{
//            		if (signupInfo[i].equals(""))
//            		{
//            			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                    	builder.setTitle("Missing Information").setCancelable(false)
//                    	.setMessage("Please fill in all required fields")
//                    	.setNegativeButton("Return", new DialogInterface.OnClickListener() 
//                    	{
//                    		public void onClick(DialogInterface dialog, int id) 
//                    		{
//                    			dialog.cancel();
//                    		}
//                    	});
//                    	AlertDialog alert = builder.create();
//                    	alert.show();
//                    	return;
//            		}
//            	}
//            	if (!signupInfo[7].equals(signupInfo[8]))
//            	{
//            		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                	builder.setTitle("Incorrect Information").setCancelable(false)
//                	.setMessage("Passwords do not match")
//                	.setNegativeButton("Return", new DialogInterface.OnClickListener() 
//                	{
//                		public void onClick(DialogInterface dialog, int id) 
//                		{
//                			dialog.cancel();
//                		}
//                	});
//                	AlertDialog alert = builder.create();
//                	alert.show();
//                	return;
//            	}
//            	Bundle info = new Bundle();
//            	//Name
//            	String personalInfo = "\n" + signupInfo[0] + " " + signupInfo[1];
//            	//Address
//            	personalInfo += "\n" + signupInfo[2];
//            	if (!signupInfo[8].equals(""))
//            	{
//            		personalInfo += "\n" + signupInfo[8];
//            	}
//            	//City, State Zip
//            	personalInfo += "\n" + signupInfo[3] + ", " + signupInfo[4]
//            			+ " " + signupInfo[5];
//            	//Email
//            	personalInfo += "\n" + signupInfo[6];
//            	info.putString("personalInfo", personalInfo);
//            	nextScreen.putExtra("info", info);
            	startActivity(nextScreen);
            	return;
            }
        });
	}
}