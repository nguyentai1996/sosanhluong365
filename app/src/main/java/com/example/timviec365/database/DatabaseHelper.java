package com.example.timviec365.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.timviec365.splDAO.HistoryDAO;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "tracuuluong365", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HistoryDAO.SQL_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HistoryDAO.TABLE_NAME);
        onCreate(db);
    }


}
