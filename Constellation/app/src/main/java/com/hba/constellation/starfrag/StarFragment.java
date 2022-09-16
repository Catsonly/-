 package com.hba.constellation.starfrag;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hba.constellation.R;
import com.hba.constellation.bean.StarBean;
import com.hba.constellation.utils.AssetsUtils;

import java.util.ArrayList;
import java.util.List;

 /**
 * 星座Fragment
 */
public class StarFragment extends Fragment {
    ViewPager starVp;
    GridView starGv;
    LinearLayout pointLayout;
    private List<StarBean.StarinfoBean> mDatas;
    private StarBaseAdapter starBaseAdapter;
    private StarPageAdapter starPageAdapter;
     //声明图片数组
     int[] imgIds={R.mipmap.pic_star};
     //声明viewpaper的数据源
     List<ImageView>ivList;
     //声明管理指示器小圆点集合
     List<ImageView> pointList;
    //定时器 自动实现滑动效果
     Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what==1) {
//                获取当前ViewPager显示的页面
                int currentItem=starVp.getCurrentItem();
                if (currentItem==ivList.size()-1) {
                    starVp.setCurrentItem(0);
                }else{
                    currentItem++;
                    starVp.setCurrentItem(currentItem);
                }
                handler.sendEmptyMessageDelayed(1,3000);
            }
        }
    };

     public static StarFragment newInstance() {
         
         Bundle args = new Bundle();
         
         StarFragment fragment = new StarFragment();
         fragment.setArguments(args);
         return fragment;
     }
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_stars, container, false);
        initView(view);
        Bundle bundle=getArguments();
        StarBean infoBean=loadData();
        mDatas = infoBean.getStarinfo(); //获取关于集合的数据
        //创建设配器
        starBaseAdapter  = new StarBaseAdapter(getContext(), mDatas);
        starGv.setAdapter(starBaseAdapter);
        initPage();
        setVPListener();
        setGVListener();
        //3秒钟切换滑动
        handler.sendEmptyMessageDelayed(1,3000);
        return view;
    }

     //读取assets文件夹下的文件
     private StarBean loadData() {
         String json = AssetsUtils.getJsonFromAssets(getContext(), "xzcontent/xzcontent.json");
         Gson gson = new Gson();
         StarBean infoBean = gson.fromJson(json, StarBean.class);
         AssetsUtils.saveBitmapFromAssets(getContext(),infoBean);
         return infoBean;
     }
     /**
      * 设置监听事件
      */
     private void setGVListener() {
         starGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 StarBean.StarinfoBean bean = mDatas.get(position);
                 Intent intent = new Intent(getContext(), StarAnalysisActivity.class);
                 intent.putExtra("star",bean);
                 startActivity(intent);
             }
         });
     }

     /**
      * 设置viewpager监听函数
      */
     private void setVPListener() {
         starVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
             @Override
             public void onPageSelected(int position) {
                 for (int i=0;i<pointList.size();i++){
                     pointList.get(i).setImageResource(R.mipmap.point_normal);
                 }
                 pointList.get(position).setImageResource(R.mipmap.point_focus);
             }
         });
     }

     /**
      * 设置ViewPaper显示页面
      */
     private void initPage() {
         ivList=new ArrayList<>();
         pointList=new ArrayList<>();
         for (int i=0;i<imgIds.length;i++){
             ImageView iv=new ImageView(getContext());
             iv.setImageResource(imgIds[i]);
             iv.setScaleType(ImageView.ScaleType.FIT_XY);
             //设置图片的宽高
             LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
                     LinearLayout.LayoutParams.MATCH_PARENT,
                     LinearLayout.LayoutParams.MATCH_PARENT
             );
             iv.setLayoutParams(lp);
             //将图片加载到集合中
             ivList.add(iv);
             //创建小圆点
             ImageView piv = new ImageView(getContext());
             piv.setImageResource(R.mipmap.point_normal);
             LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(
                     LinearLayout.LayoutParams.WRAP_CONTENT,
                     LinearLayout.LayoutParams.WRAP_CONTENT
             );
             plp.setMargins(20,0,0,0);
             piv.setLayoutParams(plp);
             pointLayout.addView(piv);
             //将小圆点管理集合中
             pointList.add(piv);
         }
         pointList.get(0).setImageResource(R.mipmap.point_focus);
         starPageAdapter = new StarPageAdapter(getContext(), ivList);
         starVp.setAdapter(starPageAdapter);
     }

     //初始化控件
     private  void initView(View view){
        starVp=view.findViewById(R.id.starfrag_vp);
        starGv=view.findViewById(R.id.startfrag_gv);
        pointLayout=view.findViewById(R.id.starfrag_layout);

     }

}