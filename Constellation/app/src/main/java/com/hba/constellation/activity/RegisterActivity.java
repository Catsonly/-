package com.hba.constellation.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hba.constellation.MyAPP;
import com.hba.constellation.R;
import com.hba.constellation.db.bean.User;
import com.hba.constellation.db.dao.UserDao;


public class RegisterActivity extends AppCompatActivity {
    private EditText userName,passWord,rePassword;
    private TextView userLogin;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        init();
        ViewClick();
    }

    private void ViewClick() {

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin.setTextColor(Color.rgb(0, 0, 0));
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username=userName.getText().toString();
                final String password=passWord.getText().toString();
                final String repassword=rePassword.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(getApplicationContext(),"帐号不能为空", Toast.LENGTH_LONG).show();
                    return;
                }else if(password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }else if(!password.equals(repassword)){
                    Toast.makeText(getApplicationContext(),"两次密码输入不一致", Toast.LENGTH_LONG).show();
                    return;
                }
                UserDao userDao = MyAPP.getInstance().getWordDatabase().getUserDao();
                User exit = userDao.isExit(username.trim());
                if (exit!=null) {
                    Toast.makeText(getApplicationContext(),"该用户已被注册，请重新输入", Toast.LENGTH_LONG).show();
                    userName.requestFocus();
                }else{
                    User user = new User();
                    user.setUserName(username);
                    user.setUserPassword(password);
                    userDao.insert(user);
                    Toast.makeText(getApplicationContext(),"用户注册成功，请前往登录", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void init() {
        userName = (EditText) findViewById(R.id.et_userName);
        passWord = (EditText) findViewById(R.id.et_password);
        rePassword = (EditText) findViewById(R.id.et_repassword);
        userLogin = (TextView) findViewById(R.id.link_signup);
        register= (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
