package com.hba.constellation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hba.constellation.MyAPP;
import com.hba.constellation.R;
import com.hba.constellation.db.bean.User;
import com.hba.constellation.db.dao.UserDao;

import de.hdodenhof.circleimageview.CircleImageView;


public class LoginActivity extends AppCompatActivity {

    private EditText userName,passWord;
    private TextView userReg;
    private CircleImageView iv_icon;
    private Button login;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();//初始化组件
        ViewClick();//注册组件点击事件
    }
    private void init(){
        userName = (EditText) findViewById(R.id.et_userName);
        passWord = (EditText) findViewById(R.id.et_password);
        iv_icon= (CircleImageView) findViewById(R.id.iv_icon);
        userReg = (TextView) findViewById(R.id.link_signup);
        login= (Button) findViewById(R.id.btn_login);

    }


    private void login() {
        final String username=userName.getText().toString();
        final String password=passWord.getText().toString();

        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(),"帐号不能为空", Toast.LENGTH_LONG).show();
            return;
        }else if(password.isEmpty()){
            Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        UserDao userDao = MyAPP.getInstance().getWordDatabase().getUserDao();
        User login = userDao.login(username, password);
        if (login!=null) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            intent.putExtra("login_user",username);
            getSharedPreferences("star",MODE_PRIVATE).edit().putBoolean("login",true).commit();
            startActivity(intent);
            finish();
        }else{

            Toast.makeText(LoginActivity.this, "密码验证失败，请重新验证登录", Toast.LENGTH_SHORT).show();
        }

    }

    private void goReg() {
        userReg.setTextColor(Color.rgb(0, 0, 0));
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void ViewClick() {

        userReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goReg();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });




    }

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            System.exit(0);
            return;
        }
        else { Toast.makeText(getBaseContext(), "再按一次返回退出程序", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }

}
