package giv2giv.android;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;

public class Giv2GivAndroidActivity extends Activity {
    /** Called when the activity is first created. */
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
        
        TextView conservativeValue = (TextView)findViewById(R.id.conservativeLabel);
        TextView moderateValue = (TextView)findViewById(R.id.moderateLabel);
        TextView liberalValue = (TextView)findViewById(R.id.liberalLabel);
        
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
    }
    public static double round(double unrounded, int precision)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();
    }
}
