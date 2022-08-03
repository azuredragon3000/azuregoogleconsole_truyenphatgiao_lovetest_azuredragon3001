package com.myapp.app119;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.mylibrary.DB.ItemTruyen;
import com.myapp.mylibrary.ads.AdsInterstitial;
import com.myapp.mylibrary.recycleview.GridSpacingItemDecoration;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rc;
    int spanCount;
    int value;
    AdsInterstitial adsInterstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         value = this.getResources().getConfiguration().orientation;
        ArrayList<ItemTruyen> arrayList = getItems();
        adsInterstitial = new AdsInterstitial(
                "07CC7E40850ABA2DF210A2D2564CAD76",
                "ca-app-pub-8404443559572571/6514224617",
                this);

        ListenerItem listener = new ListenerItem() {
            @Override
            public void click(int index, Activity activity) {

                Intent intent = new Intent(getApplicationContext(),ContentActivity.class);
                intent.putExtra("Position",index);
                startActivity(intent);
                adsInterstitial.showAds(activity);
            }
        };

        rc = findViewById(R.id.recycle);
        AdapterTruyen adapterTruyen = new AdapterTruyen(this,arrayList,listener,this);
        rc.setAdapter(adapterTruyen);
        rc.setLayoutManager(new GridLayoutManager(this,2));
        if (value == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 2;
        }
        else if (value == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3;
        }
        int spacing_left = 10; // 50px
        int spacing_top=10;

        rc.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing_left, spacing_top));
    }

    private ArrayList<ItemTruyen> getItems() {

        TruyenPGDB truyenPhatGiaoDB = ((SubApp) this.getApplication()).getTruyenPhatGiaoDB();
        ArrayList<ItemTruyen> arr = truyenPhatGiaoDB.getArrayItem();
        return arr;
    }

    @Override
    protected void onResume() {

        if (value == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 2;
        }
        else if (value == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3;
        }

        super.onResume();
    }
}