package giv2giv.android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;

public final class UpdateQueue 
{
	public static Hashtable<String, String> updateQueue;
	
	private UpdateQueue(Context context)
	{
		//updateQueue = new Hashtable<String, Double>();
		//netManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	
	public static void MakeLocalChange(String tag, String changedValue)
	{
		if (updateQueue == null)
		{
			updateQueue = new Hashtable<String, String>();
		}
		updateQueue.put(tag, changedValue);
	}
	
	public static String PushChanges(Context context)
	{
		if (updateQueue == null)
		{
			updateQueue = new Hashtable<String, String>();
		}
		ConnectivityManager netManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifiStatus = netManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileStatus = netManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        String out = "NoInternetConnection";
		if (wifiStatus.isConnected())
		{
			out = DoPush();
			//Send updates to server via wifi / internet
		}
		else if (mobileStatus.isConnected() && !mobileStatus.isRoaming())
		{
			out = DoPush();
			//Send updates to server via phone signal / 3G
		}
		else
		{
			//DoPush(); later in a runnable
			//Don't send updates as we have no net connection
		}
		return out;
	}
	
	private static String DoPush()
	{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://demo.giv2giv.org/api/test");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(updateQueue.size());
        String out = "RandomError";
        for (String tag : updateQueue.keySet())
        {
        	nameValuePairs.add(new BasicNameValuePair(tag, updateQueue.get(tag).toString()));
        }
        try
        {
        	httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        	try
        	{
        		HttpResponse response = httpClient.execute(httpPost);
        		out = EntityUtils.toString(response.getEntity());
        		updateQueue.clear();
        		//test.setText(EntityUtils.toString(response.getEntity()));
        	}
        	catch (ClientProtocolException c)
        	{
        		return "ClientProtocolException";
        	}
        	catch (IOException i)
        	{
        		return "IOException";
        	}
        }
        catch (UnsupportedEncodingException e)
        {
        	return "UnsupportedEncodingException";
        }
        return out;
	}
}
