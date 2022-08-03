package com.myapp.lovetest_azuredragon3001;

import android.app.Application;

public class SubApp extends Application {
    public  String DB_PATH= "/data/data/com.myapp.lovetest_azuredragon3001/databases/";
    public  String DATABASE_NAME = "db_truyen_phat_giao.db";

    public TruyenPGDB getTruyenPhatGiaoDB(){
        return TruyenPGDB.getInstance(this,DB_PATH,DATABASE_NAME);
    }
}
