package com.inventorymgr.activity.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inventorymgr.activity.InventoryEditorActivity;
import com.inventorymgr.activity.InventoryListActivity;
import com.inventorymgr.activity.R;
import com.inventorymgr.activity.SplashActivity;
import com.inventorymgr.activity.WebRequestActivity;
import com.inventorymgr.util.ActivityUtil;
import com.inventorymgr.util.Util;

public class DemoLaunchButtonsActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_launch_buttons);

		uiDemoButtons();
	}

	// =======================================
	// BUTTON LOAD LOGIC

	private void uiDemoButtons()
	{
		final Button toast1Button = (Button) findViewById(R.id.toast1_button);
		toast1Button.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Util.myToast(v, "Simple toast");
			}
		});

		ActivityUtil.connectButtonToActivity (this, R.id.splashactivity_button, SplashActivity.class );
		ActivityUtil.connectButtonToActivity (this, R.id.webrequest_button, WebRequestActivity.class );
		ActivityUtil.connectButtonToActivity (this, R.id.drawerdemo_button, DrawerDemoActivity.class );
		ActivityUtil.connectButtonToActivity (this, R.id.inventoryeditoractivity_button, InventoryEditorActivity.class );
		ActivityUtil.connectButtonToActivity (this, R.id.inventorylistactivity_button, InventoryListActivity.class );
	}

}


