package com.myapp.app119;

import android.app.Application;

public class SubApp extends Application {
    public TruyenPhatGiaoDB getTruyenPhatGiaoDB(){
        return TruyenPhatGiaoDB.getInstance(this);
    }
}
