package com.hba.constellation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.hba.constellation.R;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences first_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean aBoolean = getSharedPreferences("star", MODE_PRIVATE).getBoolean("login", false);
                //判断是否登录过
                if (aBoolean){
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));

                }else {
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));

                }
            }
        },2000);
    }

}