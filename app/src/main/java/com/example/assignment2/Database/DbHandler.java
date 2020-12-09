package com.example.assignment2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "storedb";

	private static final String TABLE_Users = "userdetails";
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_ADDRESS = "address";

	private static final String TABLE_Basket = "basketdetails";
	private static final String KEY_PRODUCTNAME = "productname";
	private static final String KEY_PRODUCTCODE = "productcode";
	private static final String KEY_PRODUCTCOUNT = "productcount";
	private static final String KEY_PRODUCTPRICE = "productprice";


	public DbHandler(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT,"
				+ KEY_PASSWORD + " TEXT,"
				+ KEY_ADDRESS + " TEXT"+ ")";
		db.execSQL(CREATE_TABLE);

		CREATE_TABLE = "CREATE TABLE " + TABLE_Basket + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PRODUCTNAME + " TEXT,"
				+ KEY_PRODUCTCODE + " TEXT,"
				+ KEY_PRODUCTCOUNT + " INT,"
				+ KEY_PRODUCTPRICE + " REAL"+ ")";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		// Drop older table if exist
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Basket);
		// Create tables again
		onCreate(db);
	}

	public long insertUserDetails(String username, String password, String address){
		//Get the Data Repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();
		//Create a new map of values, where column names are the keys
		ContentValues cValues = new ContentValues();
		cValues.put(KEY_USERNAME, username);
		cValues.put(KEY_PASSWORD, password);
		cValues.put(KEY_ADDRESS, address);
		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(TABLE_Users,null, cValues);
		db.close();
		return newRowId;
	}

	public long insertProductDetails(String productName, String productCode, double productPrice){
		//Get the Data Repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();
		//Create a new map of values, where column names are the keys
		ContentValues cValues = new ContentValues();
		cValues.put(KEY_PRODUCTNAME, productName);
		cValues.put(KEY_PRODUCTCODE, productCode);
		cValues.put(KEY_PRODUCTCOUNT, 1);
		cValues.put(KEY_PRODUCTPRICE, productPrice);
		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(TABLE_Basket,null, cValues);
		db.close();
		return newRowId;
	}

	public ArrayList<HashMap<String, String>> getBasketList(){
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<HashMap<String, String>> basketList = new ArrayList<>();
		String query = "SELECT " + KEY_ID + ", " + KEY_PRODUCTNAME + ", " + KEY_PRODUCTCODE + ", " + KEY_PRODUCTCOUNT + ", " + KEY_PRODUCTPRICE + " FROM "+ TABLE_Basket;
		Cursor cursor = db.rawQuery(query,null);
		while (cursor.moveToNext()){
			HashMap<String,String> item = new HashMap<>();
			item.put(KEY_ID,cursor.getString(cursor.getColumnIndex(KEY_ID)));
			item.put(KEY_PRODUCTNAME,cursor.getString(cursor.getColumnIndex(KEY_PRODUCTNAME)));
			item.put(KEY_PRODUCTCODE,cursor.getString(cursor.getColumnIndex(KEY_PRODUCTCODE)));
			item.put(KEY_PRODUCTCOUNT,cursor.getString(cursor.getColumnIndex(KEY_PRODUCTCOUNT)));
			item.put(KEY_PRODUCTPRICE,cursor.getString(cursor.getColumnIndex(KEY_PRODUCTPRICE)));
			basketList.add(item);
		}
		db.close();
		return basketList;
	}

//	public void emptyBasket(ArrayList<String> ids){
//		SQLiteDatabase db = this.getWritableDatabase();
//		ids.forEach((id) -> {
//			db.delete(TABLE_Basket, KEY_ID=" = ?", new)
//		});
//		db.close();
//	}

	public void emptyBasket(String[] itemIds){
		SQLiteDatabase db = this.getWritableDatabase();
		String arguments = TextUtils.join(", ", itemIds);
		db.execSQL(String.format("DELETE FROM %s WHERE %s IN (%s);", TABLE_Basket, KEY_ID, arguments));
		db.close();
	}
}
