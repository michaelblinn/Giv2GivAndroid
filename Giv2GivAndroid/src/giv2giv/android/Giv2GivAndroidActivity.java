package giv2giv.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Giv2GivAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard);
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
				System.out.println(seekBar.getProgress());
				System.out.println(progress);
				int otherProgress = 100-progress;
		    	switch (seekBar.getId())
		    	{
		    	case R.id.conservativeSlider:
		    		if (otherProgress % 2 != 0)
		    		{
		    			int medProgress = (otherProgress / 2) + 1;
		    			int highProgress = medProgress - 1;
		    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
		    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
		    			medSeek.setProgress(medProgress);
		    			highSeek.setProgress(highProgress);
		    		}
		    		break;
		    		
		    	case R.id.moderateSlider:
					if (otherProgress % 2 != 0)
					{
						int lowProgress = (otherProgress / 2) + 1;
						int highProgress = lowProgress - 1;
		    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
		    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
		    			lowSeek.setProgress(lowProgress);
		    			highSeek.setProgress(highProgress);
		    			
					}
		    		break;
		    		
		    	case R.id.liberalSlider:
		    		if (otherProgress % 2 != 0)
		    		{
		    			int lowProgress = (otherProgress / 2) + 1;
		    			int medProgress = lowProgress - 1;
		    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
		    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
		    			lowSeek.setProgress(lowProgress);
		    			medSeek.setProgress(medProgress);
		    		}
		    		break;
		    	}
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
		    	switch (seekBar.getId())
		    	{
		    	case R.id.conservativeSlider:
		    		if (otherProgress % 2 != 0)
		    		{
		    			int medProgress = (otherProgress / 2) + 1;
		    			int highProgress = medProgress - 1;
		    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
		    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
		    			medSeek.setProgress(medProgress);
		    			highSeek.setProgress(highProgress);
		    		}
		    		break;
		    		
		    	case R.id.moderateSlider:
					if (otherProgress % 2 != 0)
					{
						int lowProgress = (otherProgress / 2) + 1;
						int highProgress = lowProgress - 1;
		    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
		    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
		    			lowSeek.setProgress(lowProgress);
		    			highSeek.setProgress(highProgress);
		    			
					}
		    		break;
		    		
		    	case R.id.liberalSlider:
		    		if (otherProgress % 2 != 0)
		    		{
		    			int lowProgress = (otherProgress / 2) + 1;
		    			int medProgress = lowProgress - 1;
		    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
		    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
		    			lowSeek.setProgress(lowProgress);
		    			medSeek.setProgress(medProgress);
		    		}
		    		break;
		    	}
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
		    	switch (seekBar.getId())
		    	{
		    	case R.id.conservativeSlider:
		    		if (otherProgress % 2 != 0)
		    		{
		    			int medProgress = (otherProgress / 2) + 1;
		    			int highProgress = medProgress - 1;
		    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
		    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
		    			medSeek.setProgress(medProgress);
		    			highSeek.setProgress(highProgress);
		    		}
		    		break;
		    		
		    	case R.id.moderateSlider:
					if (otherProgress % 2 != 0)
					{
						int lowProgress = (otherProgress / 2) + 1;
						int highProgress = lowProgress - 1;
		    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
		    			SeekBar highSeek = (SeekBar)findViewById(R.id.liberalSlider);
		    			lowSeek.setProgress(lowProgress);
		    			highSeek.setProgress(highProgress);
		    			
					}
		    		break;
		    		
		    	case R.id.liberalSlider:
		    		if (otherProgress % 2 != 0)
		    		{
		    			int lowProgress = (otherProgress / 2) + 1;
		    			int medProgress = lowProgress - 1;
		    			SeekBar lowSeek = (SeekBar)findViewById(R.id.conservativeSlider);
		    			SeekBar medSeek = (SeekBar)findViewById(R.id.moderateSlider);
		    			lowSeek.setProgress(lowProgress);
		    			medSeek.setProgress(medProgress);
		    		}
		    		break;
		    	}
			}
		});
        TextView conservativeValue = (TextView)findViewById(R.id.conservativeLabel);
        //conservativeValue.setText("" + conservativeSlider.getProgress());
        TextView moderateValue = (TextView)findViewById(R.id.moderateLabel);
        //moderateValue.setText("" + moderateSlider.getProgress());
        TextView liberalValue = (TextView)findViewById(R.id.liberalLabel);
        //liberalValue.setText("" + liberalSlider.getProgress());
    }
}