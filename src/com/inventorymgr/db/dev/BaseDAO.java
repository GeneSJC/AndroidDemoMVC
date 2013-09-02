package com.inventorymgr.db.dev;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.inventorymgr.util.Util;

/**
 * Data Access Object - contains: - schema details - helper methods for DB CRUD
 * operations
 * 
 * 
 * @author gene
 * 
 */
abstract public class BaseDAO
{
	public static int DATABASE_VERSION = 0;
	public static String DATABASE_NAME = null;
	public static String TABLE_NAME = null;
	public static String PRIMARY_KEY_ID = null;

	protected DatabaseHelper dbHelper;
	protected SQLiteDatabase database;


	/**
	 * 
	 * @param context
	 */
	public BaseDAO(Context context)
	{
		dbHelper = new DatabaseHelper(context, this);
		database = dbHelper.getWritableDatabase();

		doSubclassValidation();
	}

	private void doSubclassValidation()
	{
		if ( DATABASE_NAME == null)
			throw new RuntimeException("Subclass did not set member DATABASE_NAME");
		
		if ( TABLE_NAME == null)
			throw new RuntimeException("Subclass did not set member TABLE_NAME");
		
		if ( PRIMARY_KEY_ID == null)
			throw new RuntimeException("Subclass did not set member PRIMARY_KEY_ID");
		
		if ( DATABASE_VERSION < 1)
			throw new RuntimeException("Subclass did not set member DATABASE_VERSION");
		
		
	}
	
	public String getDatabaseName()
	{
		return DATABASE_NAME;
	}

	public String getTableName()
	{
		return TABLE_NAME;
	}

	public int getDatabaseVersion()
	{
		return DATABASE_VERSION;
	}

	public String getPrimaryKeyId()
	{
		return PRIMARY_KEY_ID;
	}
	
	
	/**
	 * =======================================
	 * ======================================= String helper methods to
	 * construct to SQL
	 * 
	 * @return =======================================
	 *         =======================================
	 */

	abstract public String[] getTableCreateColumns();
	
	public String getTableCreate()
	{
		String[] columns = this.getTableCreateColumns();
		
		String createStmt = Util.getCreateSql(this.getTableName(), columns);

		return createStmt;
	}


	/**
	 * Override this method if you need something
	 * 
	 * @param dbHelperDatabase
	 *            We have to use the caller's DB reference, since the instance
	 *            member 'database' is still being constructed at this point
	 */
	public void initializeDatabase(SQLiteDatabase dbHelperDatabase)
	{

	}
	

	/**
	 * Deletes element
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id)
	{
		return database.delete(getTableName(), PRIMARY_KEY_ID + "=" + id, null) > 0;
	}
	
	public boolean delete(int id)
	{
		return database.delete(getTableName(), PRIMARY_KEY_ID + "=" + id, null) > 0;
	}
	

}