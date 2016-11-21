package com.test.kahdek.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 80086;
    // Database Name
    private static final String DATABASE_NAME = "User";
    // Contacts table name
    private static final String TABLE_USER = "User_Data";
    // Contacts Table Columns names
    // private static final String KEY_ID = "id";
    //main
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_OCCUPATION = "user_occupation";

    // All Static variables
    private static DatabaseHandler sInstance = null;
    private Context mCtx;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCtx = context;
    }

    public static DatabaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER = "CREATE TABLE " + TABLE_USER + "("
                + KEY_USER_NAME + " VARCHAR(70),"
                + KEY_USER_OCCUPATION + " VARCHAR(255),"
                + ")";
        db.execSQL(CREATE_USER);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    private ContentValues addValues(UserData userData) {
        ContentValues values = new ContentValues();
        //main
        values.put(KEY_USER_NAME, userData.getUserName());
        values.put(KEY_USER_OCCUPATION, userData.getUserOccupation());
        return values;
    }

    public void addUserData(UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = addValues(userData);

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }



    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
    }

    public List<UserData> getAllUserData() {
        List<UserData> userDataList = new ArrayList<UserData>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserData userData = new UserData();
                //main
                userData.setUserName(cursor.getString(1));
                userData.setUserOccupation(cursor.getString(1));

                // Adding contact to list
                userDataList.add(userData);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userDataList;
    }

    public boolean checkIfEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_USER;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return false;
        }
        return true;
    }

    public int getNumRows() {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT COUNT(*) FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(count, null);
        return cursor.getCount();
    }


}