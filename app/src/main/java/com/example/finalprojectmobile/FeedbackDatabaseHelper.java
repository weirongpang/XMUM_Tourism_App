package com.example.finalprojectmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FeedbackDatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "Feedback.db";
    public static final String TABLE_NAME = "FEEDBACK";
    public static final String COLUMN_NAME = "USERNAME";
    public static final String COLUMN_CONTACT = "CONTACT";
    public static final String COLUMN_COMMENT = "COMMENT";
    public static final String COLUMN_DATE = "DATE";

    public FeedbackDatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NAME + " TEXT PRIMARY KEY," +
                COLUMN_CONTACT + " TEXT NOT NULL," +
                COLUMN_COMMENT + " TEXT NOT NULL," +
                COLUMN_DATE + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table is already exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // if not exist, call the onCreate method
        onCreate(db);
    }

    public Boolean booking(String username, String contact,String comment, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, username);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_COMMENT, comment);
        values.put(COLUMN_DATE, date);


        // Insert new row, returning the primary key value of the new row
        long newRowID = db.insert(TABLE_NAME, null, values);
        // the "insert" will return the id for the newly created row, or it will return
        // -1 if there was an error inserting the data
        if(newRowID == -1)
            return false;
        else
            return true;
    }

    public Cursor getUserBookingRecords()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}
