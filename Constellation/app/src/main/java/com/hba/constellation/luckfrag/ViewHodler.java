package com.hba.constellation.luckfrag;

import android.view.View;
import android.widget.TextView;

import com.hba.constellation.R;


public class ViewHodler {
    TextView titleTv,contentTv;
    public  ViewHodler(View view){
        titleTv=view.findViewById(R.id.item_luck_tv_title);
        contentTv=view.findViewById(R.id.item_luck_tv_content);
    }
}
