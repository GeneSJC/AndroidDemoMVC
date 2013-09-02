package com.inventorymgr.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityUtil
{
	public static void goToActivity(View v, Class activityClass)
	{
		goToActivity(v, activityClass, null);
	}

	public static void goToActivity(View v, Class activityClass, Bundle bundle)
	{
		Util.myToast(v, "Now will go to Activity: " + activityClass.getSimpleName());

		Intent intent = new Intent(v.getContext(), activityClass);

		if ( bundle != null)
		{
			intent.putExtras(bundle);
		}

		v.getContext().startActivity(intent);
	}

	public static void connectButtonToActivity(Activity currentActivity, int androidResourceId, final Class activityClass)
	{
		connectButtonToActivity(currentActivity, androidResourceId, activityClass, null);
	}

	public static void connectButtonToActivity(Activity currentActivity, int androidResourceId, final Class activityClass, final Bundle bundle)
	{
		final Button button = (Button) currentActivity.findViewById(androidResourceId);
		button.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) {

				ActivityUtil.goToActivity(v, activityClass, bundle);
			}
		});
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
