package giv2giv.android;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class Giv2GivAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    public ArrayList<SeekBar> charitySliders;
    public ArrayList<TextView> charityPercents;
    public ArrayList<Integer> sliderValues;
    public ArrayList<CheckBox> charityLocks;
    public int charitySlidersSum;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard);
        final double ourMoney = 148.67;
        TextView currentFundTotal = (TextView)findViewById(R.id.currentFundTotal);
        currentFundTotal.setText(currentFundTotal.getText() + "" + ourMoney);
        SeekBar conservativeSlider = (SeekBar)findViewById(R.id.conservativeSlider);
        conservativeSlider.setProgress(34);
        conservativeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() 
        {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) 
			{
				if (!fromUser)
				{
					return;
				}
				int seek = seekBar.getProgress();
				int otherProgress = 100-progress;
				TextView consPerc = (TextView)findViewById(R.id.conservativePercent);
    			TextView medPerc = (TextView)findViewById(R.id.moderatePercent);
    			TextView highPerc = (TextView)findViewById(R.id.liberalPercent);
    			TextView consDolla = (TextView)findViewById(R.id.conservativeDollar);
    			TextView modDolla = (TextView)findViewById(R.id.moderateDollar);
    			TextView libDolla = (TextView)findViewById(R.id.liberalDollar);
    			int medProgress = (otherProgress / 2);
    			int highProgress = medProgress;
    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
    			if (!medSeek.isEnabled() && !highSeek.isEnabled())
    			{
    				medProgress = medSeek.getProgress();
    				highProgress = highSeek.getProgress();
    				seekBar.setProgress(100 - medProgress - highProgress);
        			consPerc.setText("" + seekBar.getProgress() + "%");
    			}
    			else if (!medSeek.isEnabled())
    			{
    				medProgress = medSeek.getProgress();
    				if (progress > (100 - medProgress))
    				{
    					seekBar.setProgress(100 - medProgress);
    				}
    				highSeek.setProgress(100 - (progress + medProgress));
        			consPerc.setText("" + seekBar.getProgress() + "%");
        			highPerc.setText("" + highSeek.getProgress() + "%");
    			}
    			else if (!highSeek.isEnabled())
    			{
    				highProgress = highSeek.getProgress();
    				if (progress > (100 - highProgress))
    				{
    					seekBar.setProgress(100 - highProgress);
    				}
    				medSeek.setProgress(100 - (progress + highProgress));
        			consPerc.setText("" + seekBar.getProgress() + "%");
        			medPerc.setText("" + medSeek.getProgress() + "%");
    			}
    			else
    			{
        			medSeek.setProgress(medProgress);
        			highSeek.setProgress(highProgress);
        			consPerc.setText("" + seekBar.getProgress() + "%");
        			medPerc.setText("" + medSeek.getProgress() + "%");
        			highPerc.setText("" + highSeek.getProgress() + "%");
    			}
    			double percent = ((double)seekBar.getProgress()) / 100;
    			consDolla.setText("=$" + (round(percent * ourMoney, 2)));
    			percent = ((double)medSeek.getProgress()) / 100;
    			modDolla.setText("=$" + (round(percent * ourMoney,2)));
    			percent = ((double)highSeek.getProgress()) / 100;
    			libDolla.setText("=$" + (round(percent * ourMoney,2)));
			}
		});
        SeekBar moderateSlider = (SeekBar)findViewById(R.id.moderateSlider);
        moderateSlider.setProgress(33);
        moderateSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() 
        {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) 
			{
				if (!fromUser)
				{
					return;
				}
				int otherProgress = 100-progress;
				TextView consPerc = (TextView)findViewById(R.id.conservativePercent);
    			TextView medPerc = (TextView)findViewById(R.id.moderatePercent);
    			TextView highPerc = (TextView)findViewById(R.id.liberalPercent);
    			TextView consDolla = (TextView)findViewById(R.id.conservativeDollar);
    			TextView modDolla = (TextView)findViewById(R.id.moderateDollar);
    			TextView libDolla = (TextView)findViewById(R.id.liberalDollar);
				int lowProgress = (otherProgress / 2);
				int highProgress = lowProgress;
    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
    			if (!lowSeek.isEnabled() && !highSeek.isEnabled())
    			{
    				lowProgress = lowSeek.getProgress();
    				highProgress = highSeek.getProgress();
    				seekBar.setProgress(100 - lowProgress - highProgress);
        			medPerc.setText("" + seekBar.getProgress() + "%");
    			}
    			else if (!lowSeek.isEnabled())
    			{
    				lowProgress = lowSeek.getProgress();
    				if (progress > (100 - lowProgress))
    				{
    					seekBar.setProgress(100 - lowProgress);
    				}
    				highSeek.setProgress(100 - (progress + lowProgress));
        			medPerc.setText("" + seekBar.getProgress() + "%");
        			highPerc.setText("" + highSeek.getProgress() + "%");
    			}
    			else if (!highSeek.isEnabled())
    			{
    				highProgress = highSeek.getProgress();
    				if (progress > (100 - highProgress))
    				{
    					seekBar.setProgress(100 - highProgress);
    				}
    				lowSeek.setProgress(100 - (progress + highProgress));
        			medPerc.setText("" + seekBar.getProgress() + "%");
        			consPerc.setText("" + lowSeek.getProgress() + "%");
    			}
    			else
    			{
        			lowSeek.setProgress(lowProgress);
        			highSeek.setProgress(highProgress);
        			medPerc.setText("" + seekBar.getProgress() + "%");
        			consPerc.setText("" + lowSeek.getProgress() + "%");
        			highPerc.setText("" + highSeek.getProgress() + "%");
    			}
    			double percent = ((double)lowSeek.getProgress()) / 100;
    			consDolla.setText("=$" + (round(percent * ourMoney, 2)));
    			percent = ((double)seekBar.getProgress()) / 100;
    			modDolla.setText("=$" + (round(percent * ourMoney,2)));
    			percent = ((double)highSeek.getProgress()) / 100;
    			libDolla.setText("=$" + (round(percent * ourMoney,2)));
			}
		});
        SeekBar liberalSlider = (SeekBar)findViewById(R.id.liberalSlider);
        liberalSlider.setProgress(33);
        liberalSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() 
        {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) 
			{
				if (!fromUser)
				{
					return;
				}
				int otherProgress = 100-progress;
				TextView consPerc = (TextView)findViewById(R.id.conservativePercent);
    			TextView medPerc = (TextView)findViewById(R.id.moderatePercent);
    			TextView highPerc = (TextView)findViewById(R.id.liberalPercent);
    			TextView consDolla = (TextView)findViewById(R.id.conservativeDollar);
    			TextView modDolla = (TextView)findViewById(R.id.moderateDollar);
    			TextView libDolla = (TextView)findViewById(R.id.liberalDollar);
    			int lowProgress = (otherProgress / 2);
    			int medProgress = lowProgress;
    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
    			if (!medSeek.isEnabled() && !lowSeek.isEnabled())
    			{
    				lowProgress = lowSeek.getProgress();
    				medProgress = medSeek.getProgress();
    				seekBar.setProgress(100 - lowProgress - medProgress);
        			highPerc.setText("" + seekBar.getProgress() + "%");
    			}
    			else if (!medSeek.isEnabled())
    			{
    				medProgress = medSeek.getProgress();
    				if (progress > (100 - medProgress))
    				{
    					seekBar.setProgress(100 - medProgress);
    				}
    				lowSeek.setProgress(100 - (progress + medProgress));
        			highPerc.setText("" + seekBar.getProgress() + "%");
        			consPerc.setText("" + lowSeek.getProgress() + "%");
    			}
    			else if (!lowSeek.isEnabled())
    			{
    				lowProgress = lowSeek.getProgress();
    				if (progress > (100 - lowProgress))
    				{
    					seekBar.setProgress(100 - lowProgress);
    				}
    				medSeek.setProgress(100 - (progress + lowProgress));
        			highPerc.setText("" + seekBar.getProgress() + "%");
        			medPerc.setText("" + medSeek.getProgress() + "%");
    			}
    			else
    			{
        			medSeek.setProgress(medProgress);
        			lowSeek.setProgress(lowProgress);
        			highPerc.setText("" + seekBar.getProgress() + "%");
        			consPerc.setText("" + lowSeek.getProgress() + "%");
        			medPerc.setText("" + medSeek.getProgress() + "%");
    			}
    			double percent = ((double)lowSeek.getProgress()) / 100;
    			consDolla.setText("=$" + (round(percent * ourMoney, 2)));
    			percent = ((double)medSeek.getProgress()) / 100;
    			modDolla.setText("=$" + (round(percent * ourMoney,2)));
    			percent = ((double)seekBar.getProgress()) / 100;
    			libDolla.setText("=$" + (round(percent * ourMoney,2)));
			}
		});
        
        TextView conservativeValue = (TextView)findViewById(R.id.conservativeDollar);
		double percent = ((double)conservativeSlider.getProgress()) / 100;
		conservativeValue.setText("=$" + (round(percent * ourMoney,2)));
        //conservativeValue.setText(conservativeSlider.getProgress())
        TextView moderateValue = (TextView)findViewById(R.id.moderateDollar);
		percent = ((double)moderateSlider.getProgress()) / 100;
		moderateValue.setText("=$" + (round(percent * ourMoney,2)));
        TextView liberalValue = (TextView)findViewById(R.id.liberalDollar);
		percent = ((double)liberalSlider.getProgress()) / 100;
		liberalValue.setText("=$" + (round(percent * ourMoney,2)));
        
        TextView conservativePerc = (TextView)findViewById(R.id.conservativePercent);
        conservativePerc.setText("" + conservativeSlider.getProgress() + "%");
        TextView moderatePerc = (TextView)findViewById(R.id.moderatePercent);
        moderatePerc.setText("" + moderateSlider.getProgress() + "%");
        TextView liberalPerc = (TextView)findViewById(R.id.liberalPercent);
        liberalPerc.setText("" + liberalSlider.getProgress() + "%");
        
        CheckBox consLock = (CheckBox)findViewById(R.id.conservativeLock);
        consLock.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                SeekBar consSlider = (SeekBar)findViewById(R.id.conservativeSlider);
                if (consSlider.isEnabled())
                {
                	CheckBox modLock = (CheckBox)findViewById(R.id.moderateLock);
                	CheckBox libLock = (CheckBox)findViewById(R.id.liberalLock);
                	SeekBar modSeek = (SeekBar)findViewById(R.id.moderateSlider);
                	SeekBar libSeek = (SeekBar)findViewById(R.id.liberalSlider);
                	if (modLock.isChecked() || libLock.isChecked())
                	{
                		modLock.setChecked(true);
                		modSeek.setEnabled(false);
                		libLock.setChecked(true);
                		libSeek.setEnabled(false);
                	}
                	consSlider.setEnabled(false);
                }
                else
                {
                	consSlider.setEnabled(true);
                }
            }
        });
        CheckBox modLock = (CheckBox)findViewById(R.id.moderateLock);
        modLock.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                SeekBar modSlider = (SeekBar)findViewById(R.id.moderateSlider);
                if (modSlider.isEnabled())
                {
                	CheckBox consLock = (CheckBox)findViewById(R.id.conservativeLock);
                	CheckBox libLock = (CheckBox)findViewById(R.id.liberalLock);
                	SeekBar consSeek = (SeekBar)findViewById(R.id.conservativeSlider);
                	SeekBar libSeek = (SeekBar)findViewById(R.id.liberalSlider);
                	if (consLock.isChecked() || libLock.isChecked())
                	{
                		consLock.setChecked(true);
                		consSeek.setEnabled(false);
                		libLock.setChecked(true);
                		libSeek.setEnabled(false);
                	}
                	modSlider.setEnabled(false);
                }
                else
                {
                	modSlider.setEnabled(true);
                }
            }
        });
        CheckBox libLock = (CheckBox)findViewById(R.id.liberalLock);
        libLock.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                SeekBar libSlider = (SeekBar)findViewById(R.id.liberalSlider);
                if (libSlider.isEnabled())
                {
                	CheckBox modLock = (CheckBox)findViewById(R.id.moderateLock);
                	CheckBox consLock = (CheckBox)findViewById(R.id.conservativeLock);
                	SeekBar modSeek = (SeekBar)findViewById(R.id.moderateSlider);
                	SeekBar consSeek = (SeekBar)findViewById(R.id.conservativeSlider);
                	if (modLock.isChecked() || consLock.isChecked())
                	{
                		modLock.setChecked(true);
                		modSeek.setEnabled(false);
                		consLock.setChecked(true);
                		consSeek.setEnabled(false);
                	}
                	libSlider.setEnabled(false);
                }
                else
                {
                	libSlider.setEnabled(true);
                }
            }
        });
    
        SeekBar earningsDonate = (SeekBar)findViewById(R.id.percentEarningSlider);
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
        earningsPercent.setText("" + earningsDonate.getProgress() + "%");
        
        //THIS WOULD BE PASSED IN BY INTENT FROM THE LIST OF CHARITIES
        ArrayList<String> myCharities = new ArrayList<String>();
        myCharities.add("American Red Cross");
        myCharities.add("Boy Scouts of America");
        myCharities.add("The United Way");
        RelativeLayout charityList = (RelativeLayout)findViewById(R.id.charityList);
       
        charitySlidersSum = 100;
        int charitySumOverflow = 100 % myCharities.size();
        int perCharitySum = 100 / myCharities.size();
        charitySliders = new ArrayList<SeekBar>();
        charityPercents = new ArrayList<TextView>();
        sliderValues = new ArrayList<Integer>();
        charityLocks = new ArrayList<CheckBox>();
        int lastCharityId = R.id.donateCharityDivide;
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
        for (int i = 0; i < charitySliders.size(); i++)
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
        }
        
        
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
    	seekParams.setMargins(10, 42, 0, 0);
    	charitySeek.setProgress(sliderStart);
    	sliderValues.add(sliderStart);
    	charityPercent.setText("" + charitySeek.getProgress() + "%");
    	charityDisplay.addView(charitySeek, seekParams);
    	CheckBox charityLock = new CheckBox(this);
    	lockParams.addRule(RelativeLayout.RIGHT_OF, charitySeek.getId());
    	lockParams.setMargins(0, 30, 97, 0);
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
}
