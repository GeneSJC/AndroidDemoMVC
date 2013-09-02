package com.inventorymgr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A subclass of SQLiteOpenHelper used to get readable or writable database of
 * our app
 *
 */
public class InventoryDbHelper extends SQLiteOpenHelper
{
	private InventoryDAO inventoryDao;

	public InventoryDbHelper(Context context, InventoryDAO daoObject)
	{
		super(context,
				daoObject.getDatabaseName(),
				null,
				daoObject.getDatabaseVersion());

		this.inventoryDao = daoObject;
	}


	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		String tableCreate = inventoryDao.getTableCreate();

		database.execSQL(tableCreate);

		inventoryDao.initializeDatabase(database);
	}


	// Method is called during an upgrade of the database,
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		Log.w(InventoryDbHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + inventoryDao.getTableName());

		onCreate(database);
	}

}