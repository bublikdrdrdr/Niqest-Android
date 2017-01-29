package com.bublik.drdrdr.niqest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Currency;

/**
 * Created by Bublik on 28-Jan-17.
 */

//for preferences (connecting (or creating/update) to SQLite on device)
public class Preferences extends SQLiteOpenHelper {

    public static final String DB_NAME = "NiqestDB";
    public static final int VERSION = 1;

    private static final boolean testDeleteTable = false;
    //remove everything before launch (clear start)

    public Preferences(Context context) {
        super(context, DB_NAME, null, VERSION);
        if (testDeleteTable) {
            Log.e("SQL", "delete");
            context.deleteDatabase(DB_NAME);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("SQL", "create");
        db.execSQL("CREATE TABLE accounts (\n" +
                "  id_user integer primary key,\n" +
                "  email text,\n" +
                "  phone text ,\n" +
                "  password text\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("SQL", "update");
    }

    public int getAccountsNumber() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT COUNT(id_user) FROM accounts";
        Cursor cursor = db.rawQuery(sql, null);
        int count = 0;
        if (cursor .moveToFirst()) {
            count = cursor.getInt(0);
        }
        db.close();
        return count;
    }

    /*example

    SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("insert into accounts (email, phone, password) values (\"bublik@gmail.com\", \"576548246\", \"2001\");");

        String sql = "SELECT COUNT(id_user) FROM accounts";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String name = cursor.getString(i);
                    if (name == null)
                    Log.e("DB", "null"); else  Log.e("DB", name);
                }
                Log.e("DB", "----------");
                cursor.moveToNext();
            }
        }
        db.close();
     */


}
