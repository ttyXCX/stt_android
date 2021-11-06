package com.fdu.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_item.db";
    public static final String TABLE_NAME = "items";
    public static final String[] TABLE_COLUMNS = {"id", "content", "date", "checked"};

    public ItemDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY, "
                + "content TEXT, "
                + "date TEXT, "
                + "checked INTEGER)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i ++) {
            if (i == 1) {
                String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
                db.execSQL(SQL_DELETE_ENTRIES);
                onCreate(db);
            }
        }

    }
}
