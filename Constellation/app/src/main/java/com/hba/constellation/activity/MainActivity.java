package com.hba.constellation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.hba.constellation.R;
import com.hba.constellation.adapter.BaseFragmentAdapter;
import com.hba.constellation.bean.StarBean;
import com.hba.constellation.luckfrag.LuckFragment;
import com.hba.constellation.mefrag.MeFragment;
import com.hba.constellation.starfrag.StarFragment;
import com.hba.constellation.utils.AssetsUtils;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<Fragment> mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_home_pager);
        mBottomNavigationView = findViewById(R.id.bv_home_navigation);

        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        //设置底部按钮点击事件
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        initData();

    }


    protected void initData() {
        //给viewpager 添加fragment,对应主页的三个页面
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(StarFragment.newInstance());
        mPagerAdapter.addFragment(LuckFragment.newInstance());
        mPagerAdapter.addFragment(MeFragment.newInstance());

        // 设置成懒加载模式
        mPagerAdapter.setLazyMode(true);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_star:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.menu_luck:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.menu_me:
                mViewPager.setCurrentItem(2);
                return true;
            default:
                break;
        }
        return false;
    }



    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }


}