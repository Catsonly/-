package com.hba.constellation.mefrag;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hba.constellation.R;
import com.hba.constellation.activity.AboutActivity;
import com.hba.constellation.activity.LoginActivity;
import com.hba.constellation.bean.StarBean;
import com.hba.constellation.luckfrag.LuckBaseAdapter;
import com.hba.constellation.utils.AssetsUtils;

import java.util.List;
import java.util.Map;


public class MeFragment extends Fragment implements View.OnClickListener  {
    CircleImageView iconIv;
    TextView nameTv;
    private Map<String, Bitmap> logoImgMap;
     List<StarBean.StarinfoBean> mDatas;
    private SharedPreferences star_pref;

    //保存数据
    int selectPos=0;

    public static MeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        StarBean  infoBean =loadData();
        mDatas = infoBean.getStarinfo();
        star_pref = getContext().getSharedPreferences("star_pref", Context.MODE_PRIVATE);
    }

    //读取assets文件夹下的文件
    private StarBean loadData() {
        String json = AssetsUtils.getJsonFromAssets(getContext(), "xzcontent/xzcontent.json");
        Gson gson = new Gson();
        StarBean infoBean = gson.fromJson(json, StarBean.class);
        AssetsUtils.saveBitmapFromAssets(getContext(),infoBean);
        return infoBean;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mes, container, false);
        iconIv=view.findViewById(R.id.mefrag_iv);
        nameTv=view.findViewById(R.id.mefrag_tv_name);
        logoImgMap = AssetsUtils.getContentlogoImgMap();
        //读取保存的数据
        String name = star_pref.getString("name", "白羊座");
        String logoname = star_pref.getString("logoname", "baiyang");
        Bitmap bitmap = logoImgMap.get(logoname);
        iconIv.setImageBitmap(bitmap);
        nameTv.setText(name);
        iconIv.setOnClickListener(this);
        view.findViewById(R.id.mefrag_tv_about).setOnClickListener(this);
        view.findViewById(R.id.mefrag_tv_function).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mefrag_iv:
                showDialog();
                break;

            case R.id.mefrag_tv_about:
              startActivity(new Intent(getContext(), AboutActivity.class));
            break;
            case R.id.mefrag_tv_function:
                getActivity().getSharedPreferences("star",Context.MODE_PRIVATE).edit().putBoolean("login",false).commit();
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }
    }

    private void showDialog() {
        Dialog dialog=new Dialog(getContext());
        View dialogView=LayoutInflater.from(getContext()).inflate(R.layout.me_dialog,null);
        dialog.setContentView(dialogView);
        dialog.setTitle("请选择星座");
        GridView dialogGV=dialogView.findViewById(R.id.mefrag_dialog_gv);
        LuckBaseAdapter adapter=new LuckBaseAdapter(getContext(),mDatas);
        dialogGV.setAdapter(adapter);
        dialog.setCancelable(true);
        //设置点击外部取消
        dialog.setCanceledOnTouchOutside(true);
        //点击事件
        dialogGV.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        StarBean.StarinfoBean bean=mDatas.get(position);
                        String name = bean.getName();
                        String logoname = bean.getLogoname();
                        nameTv.setText(name);
                        Bitmap bitmap = logoImgMap.get(logoname);
                        iconIv.setImageBitmap(bitmap);
                        selectPos=position;
                        dialog.cancel();
                    }
                }
        );
        dialog.show();
    }

    @Override
    public void onPause() {
        /**
         * 退出时保存数据
         */
        super.onPause();
        StarBean.StarinfoBean bean = mDatas.get(selectPos);
        String name = bean.getName();
        String logoname = bean.getLogoname();
        SharedPreferences.Editor edit=star_pref.edit(); //保存
        edit.putString("name",name);
        edit.putString("logoname",logoname);
        edit.commit();
    }
}