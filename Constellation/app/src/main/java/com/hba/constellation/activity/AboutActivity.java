package com.hba.constellation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hba.constellation.R;

public class AboutActivity extends AppCompatActivity {

    private TextView titleTv;
    private ImageView titleIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        titleTv = (TextView) findViewById(R.id.title_tv);
        titleIvBack = (ImageView) findViewById(R.id.title_iv_back);
        titleIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}