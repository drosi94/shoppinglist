package com.bill.shoppinglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseClass  extends SQLiteOpenHelper{
	
	
	private static String DATABASE_NAME = "ItemsList";
	private static String TABLE_NAME = "Items";
	private static String ITEMS_COLUMN = "Items_names";
	private static String PRIORITY_COLUMN = "Priority_level";
	private static String ID_COLUMN = "_id";
	
	private static int DATABASE_VERSION = 1;
		
	private static String SQL_QUERY = "CREATE TABLE " + TABLE_NAME + "(" + ID_COLUMN +
			" INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEMS_COLUMN + " TEXT NOT NULL, "
			+ PRIORITY_COLUMN + " TEXT NOT NULL);";

	public DatabaseClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		this.onCreate(db);
	}

}
