package com.inventorymgr.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Util
{
	public final static String DRAGDROP_BOOLEAN_KEY = "dragDrop_boolean";

	public static void myToast(View v, String msg)
	{
		Toast.makeText(v.getContext(), msg, Toast.LENGTH_LONG).show();
		Util.debug1(msg);
	}

	public static void debug1 ( String msg)
	{
		Log.d("DEBUG1", msg);
	}

	public static String testReadTextFile(InputStream inputStream)
	{
		StringBuffer sb = new StringBuffer();

		BufferedReader in = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = in.readLine()) != null)
			{
				sb.append(line);
			}

		} catch (final IOException e)
		{
		} finally
		{
			try
			{
				in.close();
			} catch (IOException e)
			{
				// ignore //
			}
		}

		return sb.toString();

	}

	public static String getCreateSql(String tableName, String[] columns)
	{
		StringBuffer sb = new StringBuffer();
	
		sb.append("CREATE TABLE  " + tableName + " ( ");
	
		for (int count = 0; count < columns.length; count++)
		{
			String curColumn = columns[count];
	
			sb.append("\t");
			sb.append(curColumn);
	
			if (count < columns.length - 1)
			{
				sb.append(",");
			}
	
			sb.append("\n");
		}
	
		sb.append("); ");
	
		return sb.toString();
	}	
}

/*
 * (non-Javadoc)
 *
 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
 *
 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
 * menu; this adds items to the action bar if it is present.
 * getMenuInflater().inflate(R.menu.demo_buttons, menu); return true; }
 */
