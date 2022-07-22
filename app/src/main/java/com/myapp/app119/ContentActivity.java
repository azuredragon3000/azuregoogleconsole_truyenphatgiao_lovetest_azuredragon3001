package com.myapp.app119;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ContentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int position = getIntent().getIntExtra("Position",0);
        position = position+1;
        TruyenPGDB truyenPhatGiaoDB = ((SubApp) this.getApplication()).getTruyenPhatGiaoDB();
        String ct = truyenPhatGiaoDB.getContent(position+".0",2);
        String title = truyenPhatGiaoDB.getContent(position+".0",1);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
        TextView context = findViewById(R.id.noidung);
        context.setText(ct);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ContentActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}