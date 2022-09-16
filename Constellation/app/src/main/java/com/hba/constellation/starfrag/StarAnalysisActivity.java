package com.hba.constellation.starfrag;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hba.constellation.R;
import com.hba.constellation.bean.StarAnalysisBean;
import com.hba.constellation.bean.StarBean;
import com.hba.constellation.utils.AssetsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StarAnalysisActivity extends AppCompatActivity implements View.OnClickListener {
    TextView titleTv;
    ImageView backTv;
    CircleImageView iconIv;
    TextView nameTv,dateTv;
    ListView analysisLv;
    StarBean.StarinfoBean bean;
    private Map<String, Bitmap> contentlogoImgMap;
    private TextView footTv; //底部文字
    List<StarAnalysisBean>mDatas;
    private AnalysisBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_analysis);
        //获取上一级界面带来的数据
        Intent intent = getIntent();
        bean=(StarBean.StarinfoBean)intent.getSerializableExtra("star");
        initView();
        mDatas=new ArrayList<>();
        adapter = new AnalysisBaseAdapter(this, mDatas);
        analysisLv.setAdapter(adapter);
        addDataToList();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        titleTv=findViewById(R.id.title_tv);
        backTv=findViewById(R.id.title_iv_back);
        iconIv=findViewById(R.id.staranalysis_iv);
        nameTv=findViewById(R.id.staranalysis_tv_name);
        dateTv=findViewById(R.id.staranalysis_tv_date);
        analysisLv=findViewById(R.id.staranalysis_lv);

//      为ListView底部布局
        View footView = LayoutInflater.from(this).inflate(R.layout.footer_star_analysis, null);
        analysisLv.addFooterView(footView);
        footTv=footView.findViewById(R.id.footerstar_tv_info);
//      数据显示
        titleTv.setText("星座详情");
        backTv.setOnClickListener(this);
        nameTv.setText(bean.getName());
        dateTv.setText(bean.getDate());
        contentlogoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap bitmap = contentlogoImgMap.get(bean.getLogoname());
        iconIv.setImageBitmap(bitmap);
        footTv.setText(bean.getInfo());
    }

    /**
     * 加载数据源的内容
     */
    private void addDataToList() {
        StarAnalysisBean sab1 = new StarAnalysisBean("性格特点:", bean.getTd(), R.color.grey);
        StarAnalysisBean sab2 = new StarAnalysisBean("掌管宫位:", bean.getGw(), R.color.grey);
        StarAnalysisBean sab3 = new StarAnalysisBean("显阴阳性:", bean.getYy(), R.color.grey);
        StarAnalysisBean sab4 = new StarAnalysisBean("最大特征:", bean.getTz(), R.color.grey);
        StarAnalysisBean sab5 = new StarAnalysisBean("主管星球:", bean.getZg(), R.color.grey);
        StarAnalysisBean sab6 = new StarAnalysisBean("幸运颜色:", bean.getYs(), R.color.grey);
        StarAnalysisBean sab7 = new StarAnalysisBean("搭配珠宝:", bean.getZb(), R.color.grey);
        StarAnalysisBean sab8 = new StarAnalysisBean("幸运号码:", bean.getHm(), R.color.grey);
        StarAnalysisBean sab9 = new StarAnalysisBean("相配金属:", bean.getJs(), R.color.grey);
        mDatas.add(sab1);
        mDatas.add(sab2);
        mDatas.add(sab3);
        mDatas.add(sab4);
        mDatas.add(sab5);
        mDatas.add(sab6);
        mDatas.add(sab7);
        mDatas.add(sab8);
        mDatas.add(sab9);

//        数据源变化，提示适配器更新
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}