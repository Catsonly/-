package com.hba.constellation.luckfrag;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.hba.constellation.R;


import java.util.List;

/**

 * @Describe: 适配器
 */
public class LuckAnalysisAdapter extends BaseAdapter {
    Context context;
    List<LuckItemBean> mDatas;

    public LuckAnalysisAdapter(Context context, List<LuckItemBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
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
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_luckanalysis_lv, null);
            holder = new ViewHodler(convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHodler) convertView.getTag();
        }
        //获取定位数据
        LuckItemBean itemBean = mDatas.get(position);
        holder.titleTv.setText(itemBean.getTitle());
        holder.contentTv.setText(itemBean.getContent());
        //改变背景颜色
        GradientDrawable drawable=(GradientDrawable) holder.titleTv.getBackground();
        drawable.setColor(itemBean.getColorId());
        return convertView;
    }
//     class ViewHolder{
//        TextView titleTv,contentTv;
//        public  ViewHodler(View view){
//            titleTv=view.findViewById(R.id.item_luck_tv_title);
//            contentTv=view.findViewById(R.id.item_luck_tv_content);
//        }
//    }
}
