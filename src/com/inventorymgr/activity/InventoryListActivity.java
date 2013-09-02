package com.inventorymgr.activity;

import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.inventorymgr.db.InventoryDAO;

public class InventoryListActivity extends ListActivity
{
	// UI Helpers
	private int CUR_SELECTION = 0; // global to store current selected list position
	private View CUR_SELECTION_VIEW = null; // stores the View object from the last list item selected

	// DB Helpers
	private InventoryDAO inventoryDAO;
	private Cursor ITEM_LIST_CURSOR;
	private SimpleCursorAdapter dbAdapter;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_list);

		databaseLogic();

		listenerLogic();
	}

	@SuppressWarnings("deprecation")
	private void databaseLogic()
	{
		inventoryDAO = new InventoryDAO(this);

		ITEM_LIST_CURSOR = inventoryDAO.fetchAll_Items();

		String[] columns = new String[] 
				{ 
					InventoryDAO.ITEM_TITLE,
					InventoryDAO.ITEM_ASK_PRICE ,
					InventoryDAO.ITEM_LOWEST_PRICE
				};

		int[] to = new int[] { 
								R.id.rowItemTitle, 
								R.id.rowAskPrice, 
								R.id.rowLowestPrice 
							};

		startManagingCursor(ITEM_LIST_CURSOR);

		dbAdapter = new SimpleCursorAdapter(
				this, 
				R.layout.inventory_list_row,
				ITEM_LIST_CURSOR, 
				columns, 
				to);

		setListAdapter(dbAdapter);
	}

	/**
	 * This logic is based on the UX observed with the Android SDK Emulator. In
	 * the Emulator you can select list items in one of two ways: a) Using the
	 * mouse, as if it was via touch screen b) Use the arrow keys on the
	 * keyboard
	 * 
	 * Therefore, a) the "selected" logic is for the arrow keys access b) the
	 * "clicked" logic is for the touch screen access
	 */
	private void listenerLogic()
	{
		OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id)
			{
				// Toast.makeText(getBaseContext(), "You SELECTED :" + position,
				// Toast.LENGTH_SHORT).show(); // debug

				Log.d("onItemSelected", "arg1 = " + arg1);

				CUR_SELECTION = position;

				if (CUR_SELECTION_VIEW != null)
				{
					CUR_SELECTION_VIEW.setBackgroundColor(Color.TRANSPARENT);
				}

				CUR_SELECTION_VIEW = arg1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO Auto-generated method stub

			}
		};

		OnItemClickListener itemClickListener = new OnItemClickListener()
		{
			// @Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id)
			{
				// Toast.makeText(getBaseContext(), "You CLICKED :" + position,
				// Toast.LENGTH_SHORT).show(); // debug

				Log.d("onItemSelected", "arg1 = " + arg1);

				CUR_SELECTION = position;

				if (CUR_SELECTION_VIEW != null)
				{
					CUR_SELECTION_VIEW.setBackgroundColor(Color.TRANSPARENT);
				}

				CUR_SELECTION_VIEW = arg1;

				Log.d("EXT_CLICK", "arg1 view = " + arg1);
				arg1.setBackgroundColor(Color.rgb(255, 248, 220));

			}
		};

		final ListView listView = getListView();
		Log.d("EXT_CLICK", "listView = " + listView);

		listView.setOnItemClickListener(itemClickListener);
		listView.setOnItemSelectedListener(itemSelectedListener);

	}


}