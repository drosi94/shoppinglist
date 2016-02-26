package com.bill.shoppinglist;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InformationShare {

	private DatabaseClass dbHelperClass;
	private SQLiteDatabase db;

	public InformationShare(String mode) {
		dbHelperClass = new DatabaseClass(FirstActivity.context);
		if (mode.equals("read")) {
			db = dbHelperClass.getReadableDatabase();
		} else {
			db = dbHelperClass.getWritableDatabase();
		}
	}


	public void addItem(String item_name, String priority_level) {
		ContentValues values = new ContentValues();
		values.put("Items_names", item_name);
		values.put("Priority_level", priority_level);
		db.beginTransaction();
		db.insert("Items", null, values);
		db.setTransactionSuccessful();
		db.endTransaction();
	}
	




	public ArrayList<Item> getAllItems() {
		ArrayList<Item> temp = new ArrayList<Item>();
		db.beginTransaction();
		Cursor cursor = db.query("Items", new String[] { "Items_names",
				"Priority_level" }, null, null, null, null,
				"Priority_level DESC");

		while (cursor.moveToNext()) {
			Item tempItem = new Item(cursor.getString(cursor
					.getColumnIndexOrThrow("Items_names")), new Level(
					Integer.parseInt(cursor.getString(cursor
							.getColumnIndexOrThrow("Priority_level")))));
			temp.add(tempItem);
		}

		cursor.close();
		db.endTransaction();
		return temp;
	}

	public void finishDb() {
		this.db.close();		
		

	}
}
