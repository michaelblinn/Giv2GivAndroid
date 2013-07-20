package org.giv2giv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity 
{
	private static final int SPLASH_TIME = 1500;
	private boolean skipLogin;
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        SharedPreferences prefs = this.getSharedPreferences("giv2givprefs", 
        		Context.MODE_WORLD_READABLE);
        if (prefs.contains("connectKey"))
        {
        	skipLogin = true;
        }
        else
        {
        	skipLogin = false;
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LandingPageActivity.class);
                //if (skipLogin)
//                if (false)
//                {
//                	intent.setClass(SplashActivity.this, ListActivity.class);
//                }
//                else
//                {
//                	intent.setClass(SplashActivity.this, SignupActivity.class);
//                }

                startActivity(intent);
                finish();

                // transition from splash to main menu
                overridePendingTransition(R.layout.fadein,
                        R.layout.fadeout);

            }
        }, SPLASH_TIME);
        
    }
}
