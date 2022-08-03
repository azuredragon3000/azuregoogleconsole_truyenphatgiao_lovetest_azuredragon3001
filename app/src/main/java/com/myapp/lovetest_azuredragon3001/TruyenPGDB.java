package com.myapp.lovetest_azuredragon3001;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.mylibrary.DB.HandleDB;
import com.myapp.mylibrary.DB.ItemTruyen;

import java.util.ArrayList;

public class TruyenPGDB extends HandleDB {
    private static final String TABLE = "COMPANY";
    private static final String NUM = "Num";
    private static volatile TruyenPGDB INSTANCE;

    private TruyenPGDB(Context context, String DB_PATH, String DATABASE_NAME){
        super(context,DB_PATH,DATABASE_NAME);
    }

    public static TruyenPGDB getInstance(Application app, String DB_PATH, String DATABASE_NAME){
        if (INSTANCE == null) {
            synchronized (TruyenPGDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TruyenPGDB(app,DB_PATH,DATABASE_NAME);
                }
            }
        }
        return INSTANCE;
    }

    public ArrayList<ItemTruyen> getArrayItem() {
        ArrayList<ItemTruyen> arr  = new ArrayList<>();
        openDatabase();
        String strQuery = "SELECT * FROM " + TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String ret = cursor.getString(1);
                ItemTruyen ret2 = new ItemTruyen(ret,"phat2");
                arr.add(ret2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return arr;
    }

    public String getContent(String item, int index) {
        String ret = null;
        openDatabase();

        String strQuery = "SELECT * FROM " + TABLE + " WHERE " + NUM + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, new String[]{item});

        if (cursor.moveToFirst()) {
            do {
                ret = cursor.getString(index);
            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();

        return ret;
    }
}
