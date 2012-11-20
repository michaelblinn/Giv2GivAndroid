package org.giv2giv;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SliderGroup 
{
	private ArrayList<SeekBar> sliders;
	private ArrayList<Integer> values;
	private ArrayList<TextView> percents;
	private ArrayList<TextView> dollars;
	private double ourMoney;
	
	public SliderGroup(ArrayList<SeekBar> sliderGroup, ArrayList<TextView> percentViews, ArrayList<TextView> dollarViews, double dollarCap)
	{
		if (sliderGroup.size() == 0)
		{
			return;
		}
		sliders = new ArrayList<SeekBar>(sliderGroup);
		if (percentViews != null)
		{
			percents = new ArrayList<TextView>(percentViews);
		}
		else
		{
			percents = null;
		}
		if (dollarViews != null)
		{ 
			dollars = new ArrayList<TextView>(dollarViews);
		}
		else
		{
			dollars = null;
		}
		values = new ArrayList<Integer>();
		ourMoney = dollarCap;
		int initVal = 100 / sliders.size();
		int overflow = 100 % sliders.size();
		for (SeekBar slider : sliders)
		{
			if (overflow > 0)
			{
				slider.setProgress(initVal+1);
				overflow--;
			}
			else
			{
				slider.setProgress(initVal);
			}
			if (percents != null)
			{
				percents.get(sliders.indexOf(slider)).setText("" + slider.getProgress() + "%");
			}
			if (dollars != null)
			{
				double percent = ((double)slider.getProgress()) / 100;
				dollars.get(sliders.indexOf(slider)).setText("=$" + (round(percent * ourMoney,2)));
			}
			values.add(slider.getProgress());
			slider.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
			{
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
				{
					ArrayList<SeekBar> activeSliders = new ArrayList<SeekBar>();
					if (!fromUser)
					{
						return;
					}
					
					int index = sliders.indexOf(seekBar);
					int difference = progress - values.get(index);

					for (int i = 0; i < sliders.size(); i++)
					{
						if (i == index)
						{
							continue;
						}
						if (sliders.get(i).isEnabled())
						{
							activeSliders.add(sliders.get(i));
						}
					}
					
					if (activeSliders.size() == 0)
					{
						seekBar.setProgress(values.get(index));
						return;
					}
					Collections.sort(activeSliders, new SliderComparator());
					
					if (difference > 0)
					{
						int currVal, nextVal;
						int currDiff = difference;
						int base = 0;
						int every = 0;
						int over = 0;
						for (int i = 1; i < activeSliders.size(); i++)
						{
							currVal = activeSliders.get(0).getProgress();
							nextVal = activeSliders.get(i).getProgress();
							if (currDiff > 0)
							{
								base = currDiff / i;
								every = base;
								over = currDiff % i;
								if (base > currVal - nextVal)
								{
									base = currVal - nextVal;
									every = base;
									over = 0;
								}
								for (int j = 0; j < i; j++)
								{
									if (over > 0)
									{
										every++;
										over--;
									}
									if (activeSliders.get(j).getProgress() - every < 0)
									{
										every = activeSliders.get(j).getProgress();
									}
									activeSliders.get(j).setProgress(
											activeSliders.get(j).getProgress() - every);
									currDiff -= every;
									every = base;
								}
							}	
						}
						if (currDiff > 0)
						{
							base = currDiff / activeSliders.size();
							every = base;
							over = currDiff % activeSliders.size();
							for (int j = 0; j < activeSliders.size(); j++)
							{
								if (over > 0)
								{
									every++;
									over--;
								}
								if (activeSliders.get(j).getProgress() - every < 0)
								{
									every = activeSliders.get(j).getProgress();
								}
								activeSliders.get(j).setProgress(
										activeSliders.get(j).getProgress() - every);
								currDiff -= every;
								every = base;
							}
						}
						seekBar.setProgress(progress - currDiff);
					}
					else if (difference < 0)
					{
						Collections.sort(activeSliders, new SliderComparator2());
						int currVal, nextVal;
						int currDiff = difference * -1;
						int base = 0;
						int every = 0;
						int over = 0;
						for (int i = 1; i < activeSliders.size(); i++)
						{
							currVal = activeSliders.get(0).getProgress();
							nextVal = activeSliders.get(i).getProgress();
							if (currDiff > 0)
							{
								base = currDiff / i;
								every = base;
								over = currDiff % i;
								if (base > nextVal - currVal)
								{
									base = nextVal - currVal;
									every = base;
									over = 0;
								}
								for (int j = 0; j < i; j++)
								{
									if (over > 0)
									{
										every++;
										over--;
									}
									if (activeSliders.get(j).getProgress() + every > 100)
									{
										every = 100 - activeSliders.get(j).getProgress();
									}
									activeSliders.get(j).setProgress(
											activeSliders.get(j).getProgress() + every);
									currDiff -= every;
									every = base;
								}
							}	
						}
						if (currDiff > 0)
						{
							base = currDiff / activeSliders.size();
							every = base;
							over = currDiff % activeSliders.size();
							for (int j = 0; j < activeSliders.size(); j++)
							{
								if (over > 0)
								{
									every++;
									over--;
								}
								if (activeSliders.get(j).getProgress() + every > 100)
								{
									every = 100 - activeSliders.get(j).getProgress();
								}
								activeSliders.get(j).setProgress(
										activeSliders.get(j).getProgress() + every);
								currDiff -= every;
								every = base;
							}
						}
						seekBar.setProgress(progress + currDiff);
					}
					for (int i = 0; i < sliders.size(); i++)
					{
						values.set(i, sliders.get(i).getProgress()); 
						if (percents != null)
						{
							percents.get(i).setText("" + values.get(i) + "%");
						}
						if (dollars != null)
						{
			    			double percent = ((double)sliders.get(i).getProgress()) / 100;
			    			dollars.get(i).setText("=$" + (round(percent * ourMoney, 2)));
						}
					}
					activeSliders.add(seekBar);
					for (int i = 0; i < activeSliders.size(); i++)
					{
						UpdateQueue.MakeLocalChange("id: " + activeSliders.get(i).getId(),
								"value: " + activeSliders.get(i).getProgress());
					}
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) 
				{
					
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) 
				{
					
				}
				
			});
		}
	}

    public static double round(double unrounded, int precision)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();
    }
}

class SliderComparator implements Comparator<SeekBar>
{
	@Override
	public int compare(SeekBar slider1, SeekBar slider2)
	{
		if (slider1.getProgress() > slider2.getProgress())
		{
			return -1;
		}
		else if (slider1.getProgress() < slider2.getProgress())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}

class SliderComparator2 implements Comparator<SeekBar>
{
	@Override
	public int compare(SeekBar slider1, SeekBar slider2)
	{
		if (slider1.getProgress() > slider2.getProgress())
		{
			return 1;
		}
		else if (slider1.getProgress() < slider2.getProgress())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}