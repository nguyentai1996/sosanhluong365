package com.example.timviec365.splDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.timviec365.database.DatabaseHelper;
import com.example.timviec365.model.History;

import java.util.ArrayList;
import java.util.List;
public class HistoryDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "History";
    public static final String TAG = "HistoryDAO";

    private static String idMainColum = "idMain";
    private static String idKeyColum = "idPut";
    private static String idCityColum = "idCity";
    private static String nameColum = "nameCity";

    public static final String SQL_HISTORY = "create table " + TABLE_NAME + "("
            + idMainColum + " text primary key, " +
            idKeyColum + " text, " +
            idCityColum + " text, " +
            nameColum + " text" +   ")";

    public HistoryDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int insertHistory(History history) {
        ContentValues values = new ContentValues();
        values.put("idMain", history.getIdMain());
        values.put("idPut", history.getKey());
        values.put("idCity", history.getIdCity());
        values.put("nameCity", history.getNameCity());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public List<History> getAllHistory() {
        List<History> historyList = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            History ee = new History();
            ee.setIdMain(c.getString(0));
            ee.setKey(c.getString(1));
            ee.setIdCity(c.getString(2));
            ee.setNameCity(c.getString(3));
            historyList.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return historyList;
    }

    public History getHistory(String idMain) {
        List<History> historyList = new ArrayList<>();
        History ee = new History();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            ee.setIdMain(c.getString(0));
            ee.setKey(c.getString(1));
            ee.setIdCity(c.getString(2));
            ee.setNameCity(c.getString(3));
            historyList.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return ee;
    }

    public int updateHistory(History history) {
        ContentValues values = new ContentValues();
        values.put("idMain", history.getIdMain());
        values.put("idPut", history.getKey());
        values.put("idCity", history.getIdCity());
        values.put("nameCity", history.getNameCity());

        int result = db.update(TABLE_NAME, values, "idMain=?", new
                String[]{history.getIdCity()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public int updateInfoHistory(String idMain, String key,String idCity, String nameCity) {
        ContentValues values = new ContentValues();

        values.put("idMain", idMain);
        values.put("idPut", key);
        values.put("idCity", idCity);
        values.put("nameCity", nameCity);
        int result = db.update(TABLE_NAME, values, "idMain=?", new
                String[]{idCity});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteHistoryByID(String idMain) {
        int result = db.delete(TABLE_NAME, "idMain=?", new String[]{idMain});
        if (result == 0)
            return -1;
        return 1;
    }


    public List<History> search(String keyword) {
        List<History> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where " + idKeyColum + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<History>();
                do {
                    History contact = new History();
                    contact.setIdMain(cursor.getString(0));
                    contact.setKey(cursor.getString(1));
                    contact.setIdCity(cursor.getString(2));
                    contact.setNameCity(cursor.getString(3));
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            contacts = null;
        }
        return contacts;
    }


}

