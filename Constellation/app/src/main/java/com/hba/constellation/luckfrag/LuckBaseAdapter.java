package com.hba.constellation.luckfrag;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hba.constellation.R;
import com.hba.constellation.bean.StarBean;
import com.hba.constellation.utils.AssetsUtils;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class LuckBaseAdapter extends BaseAdapter {
    Context context;
    List<StarBean.StarinfoBean> mDatas;
    private Map<String, Bitmap> contentlogoImgMap;

    public LuckBaseAdapter(Context context, List<StarBean.StarinfoBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        contentlogoImgMap=AssetsUtils.getContentlogoImgMap();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler holder=null;
        if(convertView ==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_luck_gv, null);
            holder = new ViewHodler(convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHodler)convertView.getTag();
        }
        //获取定位数据
        StarBean.StarinfoBean bean=mDatas.get(position);
        holder.starTv.setText(bean.getName());
        Bitmap bitmap = contentlogoImgMap.get(bean.getLogoname());
        holder.starIv.setImageBitmap(bitmap);
        return convertView;
    }

    class  ViewHodler{
        CircleImageView starIv;
        TextView starTv;
        public ViewHodler(View view){
            starIv=view.findViewById(R.id.item_luck_iv);
            starTv=view.findViewById(R.id.item_luck_tv);
        }
    }
}
