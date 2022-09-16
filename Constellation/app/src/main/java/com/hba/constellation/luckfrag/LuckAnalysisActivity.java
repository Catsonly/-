package com.hba.constellation.luckfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hba.constellation.R;
import com.hba.constellation.bean.LuckBean;
import com.hba.constellation.utils.LoadDataAsyncTask;
import com.hba.constellation.utils.URLContent;

import java.util.ArrayList;
import java.util.List;

public class LuckAnalysisActivity extends AppCompatActivity implements View.OnClickListener,LoadDataAsyncTask.OnGetNetDataListener {

    ListView luckLv;
    TextView nameTv;
    ImageView backIv;
    List<LuckItemBean>mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_analysis);
        //获取传递的过来的
        Intent intent = getIntent();
        String name = intent.getStringExtra("name"); //星座名称
        initView(name);
        String luckURL = URLContent.getLuckURL(name);
        mDatas=new ArrayList<>();
        //获取网络请求
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(luckURL);

    }

    private void initView(String name) {
        luckLv=findViewById(R.id.luckanalysis_lv);
        nameTv=findViewById(R.id.title_tv);
        backIv=findViewById(R.id.title_iv_back);
        nameTv.setText(name);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /**
     * 获取网络请求方法
     * @param json
     */
    @Override
    public void onSuccess(String json) {
        System.out.println("json=====>>"+json);
        if (!TextUtils.isEmpty(json)) {
            //数据解析
            LuckBean luckBean = new Gson().fromJson(json, LuckBean.class);
            //重新整理数据
            addDataToList(luckBean);
            //设置适配器
            LuckAnalysisAdapter adapter = new LuckAnalysisAdapter(this, mDatas);
            luckLv.setAdapter(adapter);
        }
    }

    private void addDataToList(LuckBean luckBean) {
        System.out.println("=====>"+luckBean.toString());
        /**
         * code : 200
         * msg : success
         * newslist : [
         * {"type":"综合指数","content":"90%"},
         * {"type":"爱情指数","content":"90%"},
         * {"type":"工作指数","content":"80%"},
         * {"type":"财运指数","content":"80%"},
         * {"type":"健康指数","content":"80%"},
         * {"type":"幸运颜色","content":"蓝色"},
         * {"type":"幸运数字","content":"1"},
         * {"type":"速配Ｑ友","content":"双子"},
         * {"type":"今日概述","content":"今天的双子宝宝们可以说是最幸福的人了，无论遇到什么事情都会被解决。可以出去游玩的宝宝们当然是抓紧时间好好地享受旅游啦，说不定会在途中遇到对自己有帮助的人噢。要加班的宝宝今天的合作运不错，不如趁着运势，跟合作方谈谈合作吧。"}
         * ]
         */
        LuckItemBean lib1=new LuckItemBean("综合指数",luckBean.getNewslist().get(0).getContent(),R.color.lightblue);
        LuckItemBean lib2=new LuckItemBean("爱情指数",luckBean.getNewslist().get(1).getContent(),R.color.lightgreen);
        LuckItemBean lib3=new LuckItemBean("工作指数",luckBean.getNewslist().get(2).getContent(),R.color.colorPrimary);
        LuckItemBean lib4=new LuckItemBean("财运指数",luckBean.getNewslist().get(3).getContent(),R.color.colorAccent);
        LuckItemBean lib5=new LuckItemBean("健康指数",luckBean.getNewslist().get(4).getContent(),R.color.colorPrimaryDark);
        LuckItemBean lib6=new LuckItemBean("幸运颜色",luckBean.getNewslist().get(5).getContent(),R.color.design_default_color_error);
        LuckItemBean lib7=new LuckItemBean("幸运数字",luckBean.getNewslist().get(6).getContent(),R.color.design_default_color_on_primary);
        LuckItemBean lib8=new LuckItemBean("速配Ｑ友",luckBean.getNewslist().get(7).getContent(),R.color.design_default_color_primary_dark);
        LuckItemBean lib9=new LuckItemBean("今日概述",luckBean.getNewslist().get(8).getContent(),R.color.pink);

        mDatas.add(lib1);
        mDatas.add(lib2);
        mDatas.add(lib3);
        mDatas.add(lib4);
        mDatas.add(lib5);
        mDatas.add(lib6);
        mDatas.add(lib7);
        mDatas.add(lib8);
        mDatas.add(lib9);
    }
}