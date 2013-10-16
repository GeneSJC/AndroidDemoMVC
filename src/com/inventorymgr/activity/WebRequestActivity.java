package com.inventorymgr.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

public class WebRequestActivity extends Activity
{
//	static String LOCATION = "http://google.com/";
//	static String LOCATION = "http://localhost/dev/walkMVC/webapp/walk_auth/access/public/home";
//	static String LOCATION = "http://localhost:80/dev/walkMVC/ping.txt";
	static String LOCATION = "http://zf1.soluon.com/ping.txt";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		getdata();
		
		try
		{
//			URI uri = new URI("http://google.com/");
//			makeCall(uri);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	void makeCall(URI uri) throws Exception
	{

	}

	private void getdata()
	{
		try
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(LOCATION);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			readStream(con.getInputStream());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void readStream(InputStream in)
	{
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				// System.out.println(line);
				
				Toast
				.makeText(this.getApplicationContext(), line, Toast.LENGTH_LONG)
				.show();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
