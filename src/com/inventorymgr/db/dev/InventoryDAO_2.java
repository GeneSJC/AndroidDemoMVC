package com.inventorymgr.db.dev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.inventorymgr.util.Util;

/**
 * Data Access Object - contains: - schema details - helper methods for DB CRUD
 * operations
 * 
 * 
 * @author gene
 * 
 */
public class InventoryDAO_2 extends BaseDAO
{

	public static final int DATABASE_VERSION = 1;

	public static final String DATABASE_NAME = "inventoryDB";

	/**
	 * Movie table related constants.
	 */
	public final static String TABLE_NAME = "inventory_tbl";

	public final static String PK_ID = "_id";

	public final static String ITEM_TITLE = "item_title";
	public final static String ITEM_ASK_PRICE = "item_ask_price"; 
	public final static String ITEM_LOWEST_PRICE = "item_lowest_price"; 

	/**
	 * 
	 * @param context
	 */
	public InventoryDAO_2(Context context)
	{
		super(context);
	}


	/**
	 * Creates a new movie quote
	 * 
	 * @param name
	 * @param character
	 * @return
	 */
	public long insertItem(String itemTitle, String askPrice)
	{
		ContentValues values = new ContentValues();
		values.put(ITEM_TITLE, itemTitle);
		values.put(ITEM_ASK_PRICE, askPrice);
		values.put(ITEM_LOWEST_PRICE, askPrice);

		return database.insert(TABLE_NAME, null, values);
	}

	/**
	 * Fetch all movie quotes
	 * 
	 * @return
	 */
	public Cursor fetchAll_Items()
	{
		Cursor mCursor =
				database.query(true,
						TABLE_NAME,
						new String[] { PK_ID, ITEM_TITLE, ITEM_ASK_PRICE, ITEM_LOWEST_PRICE },
						null
						, null, null, null, null, null);
		
		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	/**
	 *
	 */
	public int updateItem(String itemTitle, String askPrice, String id)
	{
		ContentValues values = new ContentValues();

		values.put(ITEM_TITLE, itemTitle);
		values.put(ITEM_ASK_PRICE, askPrice);
		values.put(ITEM_LOWEST_PRICE, askPrice);

		return database.update(TABLE_NAME, values, PK_ID + "=?", new String[] { id });
	}

	/**
	 * =======================================
	 * ======================================= String helper methods to
	 * construct to SQL
	 * 
	 * @return =======================================
	 *         =======================================
	 */

	public String[] getTableCreateColumns()
	{
		String[] columns = new String[] {
				InventoryDAO_2.PK_ID + " INTEGER PRIMARY KEY",
				InventoryDAO_2.ITEM_TITLE + " TEXT NOT NULL",
				InventoryDAO_2.ITEM_ASK_PRICE + " TEXT NOT NULL",
				InventoryDAO_2.ITEM_LOWEST_PRICE + " TEXT NOT NULL",
		// InventoryDAO.MEDIA_TYPE + " NUMERIC"
		};

		return columns;
	}

	/**
	 * Placing this logic here so all column access is at least centralized to
	 * the .db package
	 * 
	 * @param cursor
	 *            cursor that has been set to the position of the element
	 *            selected in the UI
	 * @return main text from that cursor current position element
	 */
	public String getItemTitle(Cursor cursor)
	{
		String itemTitle = cursor.getString(cursor.getColumnIndex(InventoryDAO_2.ITEM_TITLE));
		
		String askPrice = cursor.getString(cursor.getColumnIndex(InventoryDAO_2.ITEM_ASK_PRICE));

		String ttsDebugMsg = "ITEM_TITLE: " + itemTitle + "; \n ITEM_ASK_PRICE: " + askPrice;

		Util.debug1(ttsDebugMsg);

		return itemTitle;
	}

}