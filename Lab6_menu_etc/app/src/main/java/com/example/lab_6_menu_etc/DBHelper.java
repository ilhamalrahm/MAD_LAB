package com.example.lab_6_menu_etc;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper {
    private static SQLiteDatabase database;
    private static final int MODE_PRIVATE = 0;

    static void InitDB() {
//        database = openOrCreateDatabase("users.db",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, email VARCHAR)");
    }

    public static void Insert(String s, ContentValues values)
    {
        database.insert(s, null, values);
    }
}


