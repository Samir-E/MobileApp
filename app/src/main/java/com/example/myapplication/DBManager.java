package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class DBManager {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void openDB(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void insertToDB(String title, String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TITLE, title);
        contentValues.put(Constants.DESC, description);
        sqLiteDatabase.insert(Constants.TABLE_NAME, null, contentValues);
    }

    public void deleteFromDB(String title){
        String selection = Constants.TITLE + " = '" + title + "'";
        //String[] selectionArgs = { title };
        sqLiteDatabase.delete(Constants.TABLE_NAME, selection, null);
    }

    public void deleteAllFromDB(String title){
        //String[] selectionArgs = { title };
        sqLiteDatabase.delete(Constants.TABLE_NAME, null, null);
    }

    public List<String> readFromDB(){
        List<String> listDB = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(Constants.TABLE_NAME,
                null,null, null, null, null, null);
        while (cursor.moveToNext()){
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(Constants.TITLE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(Constants.DESC));
            listDB.add(title);
            listDB.add(description);
        }
        cursor.close();
        return listDB;
    }

    public void closeDB(){
        sqLiteDatabase.close();
    }
}
