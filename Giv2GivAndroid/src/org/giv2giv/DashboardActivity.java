package org.giv2giv;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class DashboardActivity extends Activity {
    /** Called when the activity is first created. */
    public ArrayList<SeekBar> charitySliders;
    public ArrayList<TextView> charityPercents;
    public ArrayList<Integer> sliderValues;
    public ArrayList<CheckBox> charityLocks;
    public int charitySlidersSum;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dashboard);
        final double ourMoney = 148.67;
            
        /*SeekBar earningsDonate = (SeekBar)findViewById(R.id.percentEarningSlider);
        earningsDonate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) 
			{
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) 
			{
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) 
			{
				TextView earningsPercent = (TextView)findViewById(R.id.percentEarning);
				earningsPercent.setText("" + seekBar.getProgress() + "%");
			}
		});
        TextView earningsPercent = (TextView)findViewById(R.id.percentEarning);
        earningsPercent.setText("" + earningsDonate.getProgress() + "%");*/
        
        //THIS WOULD BE PASSED IN BY INTENT FROM THE LIST OF CHARITIES
        ArrayList<String> myCharities = new ArrayList<String>();
        Bundle fromCharityList = this.getIntent().getBundleExtra("charity_info");
        if (fromCharityList != null)
        {
        	myCharities = fromCharityList.getStringArrayList("charities");
        }
        	RelativeLayout charityList = (RelativeLayout)findViewById(R.id.charityList);
       
        charitySlidersSum = 100;
        int charitySumOverflow = 0;
        int perCharitySum = 100;
        
        if (myCharities.size() > 0)
        {
        	charitySumOverflow = 100 % myCharities.size();
        	perCharitySum = 100 / myCharities.size();
        }
        charitySliders = new ArrayList<SeekBar>();
        charityPercents = new ArrayList<TextView>();
        sliderValues = new ArrayList<Integer>();
        charityLocks = new ArrayList<CheckBox>();
        int lastCharityId = R.id.charityHeader;
        int baseId = 0x7f0f0000;
        int sliderStart = perCharitySum;
        for (int i = 0; i < myCharities.size(); i++) 
        {
            if (charitySumOverflow > 0)
            {
            	sliderStart++;
            	charitySumOverflow--;
            }
            RelativeLayout charityDisplay = createCharityDisplay(myCharities.get(i), baseId, sliderStart);
            RelativeLayout.LayoutParams charityParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 
            		LayoutParams.WRAP_CONTENT);
            charityParams.addRule(RelativeLayout.BELOW, lastCharityId);
            lastCharityId = charityDisplay.getId();
            baseId += 4;
            charityList.addView(charityDisplay, charityParams);
            sliderStart = perCharitySum;
        }
        Button addMoreCharities = (Button)findViewById(R.id.dashAddCharity);
        addMoreCharities.setOnTouchListener(new OnTouchListener()
        {
			public boolean onTouch(View v, MotionEvent m)
			{
				Intent intent = new Intent(v.getContext(), ListActivity.class);
				startActivity(intent);
				return true;
			}
        });
        SliderGroup charityGroup = new SliderGroup(charitySliders, charityPercents, null, 0);
        /*for (int i = 0; i < charitySliders.size(); i++)
        {
        	SeekBar thisSlider = charitySliders.get(i);
        	thisSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) 
				{
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) 
				{
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) 
				{
					ArrayList<SeekBar> activeSliders = new ArrayList<SeekBar>();
					
					if (!fromUser)
					{
						return;
					}
					int sum = 0;
					for (int i = 0; i < charitySliders.size(); i++)
					{
						if (charitySliders.get(i).isEnabled())
						{
							activeSliders.add(charitySliders.get(i));
							if (!seekBar.equals(charitySliders.get(i)))
							{
								sum += charitySliders.get(i).getProgress();
							}
						}
					}
					if (activeSliders.size() < 2)
					{
						seekBar.setProgress(sliderValues.get(charitySliders.indexOf(seekBar)));
						return;
					}
					if (sum == 0 && sliderValues.get(charitySliders.indexOf(seekBar)) < progress)
					{
						seekBar.setProgress(sliderValues.get(charitySliders.indexOf(seekBar)));
						return;
					}
					int seekIndex = -1;
					int test = 5;
					int index = charitySliders.indexOf(seekBar);
					int difference = progress - sliderValues.get(index);
					int winIndex = -1;
					
					seekIndex = charitySliders.indexOf(seekBar);
					if (difference < 0)
					{
						int min = 100;
						for (int i = 0; i < activeSliders.size(); i++)
						{
							if (activeSliders.get(i).equals(seekBar))
							{
								continue;
							}
							if (activeSliders.get(i).getProgress() < min)
							{
								min = activeSliders.get(i).getProgress();
								winIndex = charitySliders.indexOf((activeSliders.get(i)));
							}
						}
					}
					else if (difference > 0)
					{
						int max = 0;
						for (int i = 0; i < activeSliders.size(); i++)
						{
							if (activeSliders.get(i).equals(seekBar))
							{
								continue;
							}
							if (activeSliders.get(i).getProgress() > max)
							{
								max = activeSliders.get(i).getProgress();
								winIndex = charitySliders.indexOf((activeSliders.get(i)));
							}
						}
					}
						charitySliders.get(winIndex).setProgress(sliderValues.get(winIndex) - difference);
						sliderValues.set(winIndex, new Integer(charitySliders.get(winIndex).getProgress()));
						charityPercents.get(winIndex).setText("" + charitySliders.get(winIndex).getProgress() + "%");
						charitySliders.get(seekIndex).setProgress(progress);
						sliderValues.set(seekIndex, new Integer(progress));
						charityPercents.get(seekIndex).setText("" + progress + "%");
				}
			});       
        }*/
    }
    
    public RelativeLayout createCharityDisplay(String charityName, int id, int sliderStart)
    {
    	RelativeLayout charityDisplay = new RelativeLayout(this);
    	charityDisplay.setId(id);
    	id += 4;
    	
    	RelativeLayout.LayoutParams labelParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
    			LayoutParams.WRAP_CONTENT);
    	RelativeLayout.LayoutParams percentParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
    			LayoutParams.WRAP_CONTENT);
    	RelativeLayout.LayoutParams seekParams = new RelativeLayout.LayoutParams(335, 
    			LayoutParams.WRAP_CONTENT);
    	RelativeLayout.LayoutParams lockParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
    			LayoutParams.WRAP_CONTENT);
    	TextView charityLabel = new TextView(this);
    	labelParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, id);
    	labelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, id);
    	labelParams.setMargins(0, 10, 0, 10);
    	charityLabel.setText(charityName);
    	charityLabel.setTextSize(20);
    	charityLabel.setId(id);
    	charityDisplay.addView(charityLabel, labelParams);
    	TextView charityPercent = new TextView(this);
    	percentParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, id);
    	percentParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, id);
    	percentParams.setMargins(0, 0, 10, 0);
    	charityPercent.setText("%");
    	charityPercent.setTextSize(20);
    	charityDisplay.addView(charityPercent, percentParams);
    	SeekBar charitySeek = new SeekBar(this);
    	charitySeek.setId(id);
    	id += 4;
    	seekParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, id);
    	seekParams.addRule(RelativeLayout.BELOW, charityLabel.getId());
    	seekParams.setMargins(10, 52, 10, 0);
    	charitySeek.setProgress(sliderStart);
    	sliderValues.add(sliderStart);
    	charityPercent.setText("" + charitySeek.getProgress() + "%");
    	charityDisplay.addView(charitySeek, seekParams);
    	CheckBox charityLock = new CheckBox(this);
    	lockParams.addRule(RelativeLayout.RIGHT_OF, charitySeek.getId());
    	lockParams.setMargins(0, 30, 57, 0);
    	charityDisplay.addView(charityLock, lockParams);
    	
    	charitySliders.add(charitySeek);
    	charityPercents.add(charityPercent);
    	charityLocks.add(charityLock);
    	charityLock.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
            	SeekBar sliderToCheck = charitySliders.get(charityLocks.indexOf(buttonView));
                if (sliderToCheck.isEnabled())
                {
                	int unlocked = 0;
                	for (int i = 0; i < charityLocks.size(); i++)
                	{
                		if (!charityLocks.get(i).isChecked())
                		{
                			unlocked++;
                		}
                	}
                	if (unlocked < 2)
                	{
                		for (int i = 0; i < charityLocks.size(); i++)
                		{
                			charityLocks.get(i).setChecked(true);
                			charitySliders.get(i).setEnabled(false);
                		}
                	}
                	sliderToCheck.setEnabled(false);
                }
                else
                {
                	sliderToCheck.setEnabled(true);
                }
            }
        });
    	return charityDisplay;
    }
    public static double round(double unrounded, int precision)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent Data)
    {
    	if (requestCode == 13 && resultCode == Activity.RESULT_FIRST_USER)
    	{
    		finish();
    	}
    }
}
