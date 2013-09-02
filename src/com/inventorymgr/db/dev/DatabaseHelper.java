package com.inventorymgr.db.dev;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A subclass of SQLiteOpenHelper used to get readable or writable database of
 * our app
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
	private BaseDAO baseDAO;

	public DatabaseHelper(Context context, BaseDAO daoObject)
	{
		super(context,
				daoObject.getDatabaseName(),
				null,
				daoObject.getDatabaseVersion());

		this.baseDAO = daoObject;
	}


	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database)
	{
		String tableCreate = baseDAO.getTableCreate();

		database.execSQL(tableCreate);

		baseDAO.initializeDatabase(database);
	}


	// Method is called during an upgrade of the database,
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + baseDAO.getTableName());

		onCreate(database);
	}

}