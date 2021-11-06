package com.fdu.todolist.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fdu.todolist.database.ItemDBHelper;
import com.fdu.todolist.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ItemController {
    private ItemDBHelper itemDBHelper;
    private SQLiteDatabase db;

    public ItemController(Context context) {
        itemDBHelper = new ItemDBHelper(context);
        db = itemDBHelper.getWritableDatabase();
    }

    public void insertItem(ItemModel newItem) {
        db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", newItem.getId());
        contentValues.put("content", newItem.getContent());
        contentValues.put("date", newItem.getDate());
        contentValues.put("checked", newItem.isChecked()? 1: 0);
        long rs = db.insert(ItemDBHelper.TABLE_NAME, null, contentValues);

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void updateItem(ItemModel newItem) {
        db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put("content", newItem.getContent());
        contentValues.put("date", newItem.getDate());
        contentValues.put("checked", newItem.isChecked()? 1: 0);

        db.update(ItemDBHelper.TABLE_NAME,
                contentValues,
                "id = ?",
                new String[]{String.valueOf(newItem.getId())});

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void deleteItem(ItemModel item) {
        db.beginTransaction();

        db.delete(ItemDBHelper.TABLE_NAME,
                 "id = ?",
                 new String[]{String.valueOf(item.getId())});

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<ItemModel> queryItems() {
        List<ItemModel> items = new ArrayList<>();
        Cursor cursor;

        cursor = db.query(ItemDBHelper.TABLE_NAME,
                ItemDBHelper.TABLE_COLUMNS,
                null,
                null,
                null,
                null,
                "id ASC");

        while (cursor.moveToNext()) {
            ItemModel item = parseItem(cursor);
            items.add(item);
        }

        return items;
    }

    public ItemModel parseItem(Cursor cursor) {
        int idIndex = cursor.getColumnIndex("id");
        int id = cursor.getInt(idIndex);

        int contentIndex = cursor.getColumnIndex("content");
        String content = cursor.getString(contentIndex);

        int dateIndex = cursor.getColumnIndex("date");
        String date = cursor.getString(dateIndex);

        int checkedIndex = cursor.getColumnIndex("checked");
        boolean checked = cursor.getInt(checkedIndex) == 1;

        ItemModel item = new ItemModel(id, content, date, checked);
        return item;
    }
}
