package com.web.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.protocol.types.Field;

import java.io.*;


public class AddressUtils {

    /**
     * @param
     * @return
     * @throws UnsupportedEncodingException
     * @param//encoding 服务器端请求编码。如GBK,UTF-8等
     */
    public static String getAddresses(String ip){
        // 这里调用淘宝API
        String urlStr = "http://ip.ws.126.net/ipquery";
        String data = HttpRequestUtils.sendGet(urlStr, ip);
        //处理中文乱码问题
        System.out.println(JSON.parseObject(data).get("province"));
        return data;
    }

    public static void main(String[] args) {
            String ip = "218.192.3.42";
           String str= AddressUtils.getAddresses(ip);
    }
}
