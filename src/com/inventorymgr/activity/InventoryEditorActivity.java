package com.inventorymgr.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inventorymgr.db.InventoryDAO;
import com.inventorymgr.util.Util;

public class InventoryEditorActivity extends Activity implements OnClickListener
{
	private Button saveBtn = null;
	
	private InventoryDAO dao = null;
	
	private String action = null;
	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_editor);
		
		saveBtn = (Button)findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(this);
		
		//Create a dao to handle new movies
		dao = new InventoryDAO(this);
	}
	
	/**
	 * 
	 */
//	@Override
	public void onClick(View v) 
	{
		int clickedElement = v.getId();
		
		if(clickedElement == R.id.saveBtn)
		{
			addItem();
		}
	}

	/**
	 * Add a new movie quote
	 */
	private void addItem()
	{
		//Get movie name and description
		String itemTitle = ((TextView)findViewById(R.id.item_title)).getText().toString();
		String askPrice = ((TextView)findViewById(R.id.ask_price)).getText().toString();
		String lowestPrice = ((TextView)findViewById(R.id.lowest_price)).getText().toString();
		
		//Insert the item after data validation.for now, lets skip data validation
		long insertedId = dao.insertItem(itemTitle, askPrice, lowestPrice);
		
		String msg = "New item with id= "+insertedId+" has been created ";
		Util.debug1(msg);
		Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
		
		InventoryEditorActivity.hideKeyboard(this);
		
		super.finish();
	}

	    /* Hide Keyboard */
	   public static void hideKeyboard(Activity activity)
	   {
	       InputMethodManager inputMethodManager = 
	    		   (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
	   
	       View focus = activity.getCurrentFocus();
	       if(focus != null)
	       {
	    	   		inputMethodManager.hideSoftInputFromWindow
	    	   			(
	    	   				focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS
	    	   			);
	       }
	   }
}
