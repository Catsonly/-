package com.hba.constellation.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hba.constellation.bean.StarBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * @Describe: 读取Assets文件夹下的工具类
 */
public class AssetsUtils {
    private static Map<String,Bitmap>logoImgMap;
    private static Map<String,Bitmap>contentlogoImgMap;
    /**
     * 读取assets文件夹中的内容方法
     */
    public static String getJsonFromAssets(Context context,String filename){
    //获取assets文件夹管理器
        AssetManager am= context.getResources().getAssets();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        //获取输入流
        try{
            InputStream is = am.open(filename);
            //读取内容
            int hasRead=0;
            byte[] buf =new byte[1024];
            while (true){
                hasRead=is.read(buf);
                if(hasRead==-1){
                    break;
                }else{
                    baos.write(buf,0,hasRead);
                }
            }
            String msg=baos.toString();
            is.close();
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取assates文件夹下的图片
     */
    public  static Bitmap getBitmapFromAssets(Context context,String filemane){
        Bitmap bitmap=null;
        //获取Assets文件夹管理者
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is=am.open(filemane);
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
    /**
     * @des 将Assets文件夹当中的图片一起读取，放到内存当中
     *
     */
    public  static void  saveBitmapFromAssets(Context context, StarBean startInfoBean){
        logoImgMap=new HashMap<>();
        contentlogoImgMap=new HashMap<>();
        List<StarBean.StarinfoBean>starList = startInfoBean.getStarinfo();
        for (int i=0;i<starList.size();i++){
            String logoname=starList.get(i).getLogoname();
            String filename="xzlogo/"+logoname+".png";
            Bitmap logoBm = getBitmapFromAssets(context, filename);
            logoImgMap.put(logoname,logoBm);
            String contentName="xzcontentlogo/"+logoname+".png";
            Bitmap bitmap=getBitmapFromAssets(context,contentName);
            contentlogoImgMap.put(logoname,bitmap);
        }
    }

    public static Map<String, Bitmap> getContentlogoImgMap() {
        return contentlogoImgMap;
    }

    public static Map<String, Bitmap> getLogoImgMap() {
        return logoImgMap;
    }
}
