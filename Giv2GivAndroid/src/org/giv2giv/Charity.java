package org.giv2giv;

public class Charity 
{
	public String mName;
	public String mBlurb;
	
	public Charity(String name, String blurb)
	{
		mName = name;
		mBlurb = blurb;
	}
	
	public static String[] getCharityNameArray(Charity[] charities)
	{
		String[] names = new String[charities.length];
		for (int i = 0; i < names.length; i++)
		{
			names[i] = charities[i].mName;
		}
		return names;
	}
	
	public static String getBlurbFromName(Charity[] charities, String name)
	{
		for (int i = 0; i < charities.length; i++)
		{
			if (charities[i].mName.equalsIgnoreCase(name))
			{
				return charities[i].mBlurb;
			}
		}
		return "";
	}
}
