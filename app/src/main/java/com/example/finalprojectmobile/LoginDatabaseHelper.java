package com.example.finalprojectmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "Login.db";
    public static final String TABLE_NAME = "USERS";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_NAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_CONTACT = "CONTACT";

    public LoginDatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_EMAIL + " TEXT PRIMARY KEY," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_PASSWORD + " TEXT NOT NULL," +
                COLUMN_CONTACT + " TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table is already exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // if not exist, call the onCreate method
        onCreate(db);
    }

    public Boolean loginCheck(String userEmail, String userPassword)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?", new String[]{userEmail, userPassword});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    // To check the Email to prevent duplication
    public Boolean checkUserEmail(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE EMAIL = ?", new String[]{email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public  Boolean register(String email, String name ,String password, String contact)
    {
        // Get the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column name are keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD,password);
        values.put(COLUMN_CONTACT, contact);

        // Insert new row, returning the primary key value of the new row
        long newRowID = db.insert(TABLE_NAME, null, values);
        // the "insert" will return the id for the newly created row, or it will return
        // -1 if there was an error inserting the data
        if(newRowID == -1)
            return false;
        else
            return true;
    }

    public boolean updateUserDetails(String email, String name,String contact, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_PASSWORD, password);
        db.update(TABLE_NAME, values, "EMAIL = ?", new String[] {email});
        return true;
    }

    public Cursor getUserDetails(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = '" + email + "'", null);
        return res;
    }

}
