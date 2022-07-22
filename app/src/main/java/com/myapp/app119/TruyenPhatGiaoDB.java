package com.myapp.app119;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TruyenPhatGiaoDB extends SQLiteOpenHelper {
    private static final String TABLE = "COMPANY";
    private static final String NUM = "Num";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static volatile TruyenPhatGiaoDB INSTANCE;
    public static final String DB_PATH = "/data/data/com.myapp.app119/databases/";
    public static final String DATABASE_NAME = "db_truyen_phat_giao.db";

    private TruyenPhatGiaoDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;

        this.getReadableDatabase();
        // coppy
        if (coppyDatabase(context)) {
            Log.d("truyentinhyeu", "truyentinhyeu: coppy success");
        } else {
            Log.d("truyentinhyeu", "truyentinhyeu: coppy fail");
        }
    }

    public static TruyenPhatGiaoDB getInstance(Application app){
        if (INSTANCE == null) {
            synchronized (TruyenPhatGiaoDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TruyenPhatGiaoDB(app);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<ItemTruyen> getArrayItem() {
        ArrayList<ItemTruyen> arr  = new ArrayList<>();
        openDatabase();

        // String s_item = item+"";
        String strQuery = "SELECT * FROM " + TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(strQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String ret = cursor.getString(1);
                ItemTruyen ret2 = new ItemTruyen(ret,"hoasen");
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

        // String s_item = item+"";
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

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    private boolean coppyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outFileName = DB_PATH + DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
