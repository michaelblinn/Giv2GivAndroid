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

public class SignupActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
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
            	String firstName = ((EditText)findViewById(R.id.firstNameField)).getText().toString();
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