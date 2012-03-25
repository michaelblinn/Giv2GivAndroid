package giv2giv.android;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;

public class ListActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charity_list);
    }
    public static double round(double unrounded, int precision)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_DOWN);
        return rounded.doubleValue();
    }
}
