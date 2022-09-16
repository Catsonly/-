package com.hba.constellation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static String getJSONFromNet(String path){
        String json = "";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                json = is2String(is);//将流转换为字符串。
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String is2String(InputStream is) throws IOException {
        //连接后，创建一个输入流来读取response
        BufferedReader bufferedReader = new BufferedReader(new
                InputStreamReader(is,"utf-8"));
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        String response = "";
        //每次读取一行，若非空则添加至 stringBuilder
        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }
        //读取所有的数据后，赋值给 response
        response = stringBuilder.toString().trim();
        return response;
    }
}
