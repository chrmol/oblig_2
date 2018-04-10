package com.example.chris.oblig_2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "travel_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "country";
    private static final String COL3 = "city";
    private static final String COL4 = "resort";
    private static final String COL5 = "avgPrice";
    private static final String COL6 = "flightPrice";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String country, String city, String resort, String avgPrice, String flightPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, country);
        contentValues.put(COL3, city);
        contentValues.put(COL4, resort);
        contentValues.put(COL5, avgPrice);
        contentValues.put(COL6, flightPrice);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param country
     * @return
     */
    public Cursor getItemID(String country){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + country + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param country,
     * @return
     */
    // Returnerer prisen til en reise
    public Cursor getPris(String country){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL5 + ", " + COL6 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + country + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getItemID2(String country){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + ", " + COL3 + ", " + COL4 + ", " + COL5 + ", " + COL6 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + country + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    /**
     * Updates the name field
     * @param land
     * @param nyHotellPris
     * @param nyFlyPris

     //* @param oldExpend
     //* @param oldFly
     */
    // TODO endre hva som endres, navn osv. Slette ekstra felt i WHERE som er ubrukelige
    public void updateData(String land, String nyHotellPris, String nyFlyPris){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL5 +
                " = '" + nyHotellPris + "', " + COL6 + " = '" + nyFlyPris + "' WHERE " + COL2 + " = '" + land + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + nyHotellPris + " AND " + nyFlyPris);
        db.execSQL(query);

    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    // TODO endre til delete from med bare navn
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
}
