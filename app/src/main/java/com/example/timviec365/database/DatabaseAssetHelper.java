package com.example.timviec365.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseAssetHelper extends SQLiteAssetHelper {
private static final  String DB_NAME = "history.db";
private static final  int DB_VER =1;


    public DatabaseAssetHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
}
