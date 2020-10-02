package com.web.util;


public class XssUtil {
    /**
     * 反转XSS编码使html代码在网页生效
     */
    public static String xssUncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        if(s.indexOf("&amp;")!=-1){
            s=s.replaceAll("&amp;", "&");
        }
        if(s.indexOf("&lt;")!=-1){
            s=s.replaceAll("&lt;", "<");
        }
        if(s.indexOf("&gt;")!=-1){
            s=s.replaceAll("&gt;", ">");
        }
        if(s.indexOf("&quot;")!=-1){
            s=s.replaceAll("&quot;","'");
        }
        if(s.indexOf("&#x27;")!=-1){
            s=s.replaceAll("&#x27;", "\'");
        }
        if(s.indexOf("&#x2F;")!=-1){
            s=s.replaceAll("&#x2F;","/");
        }
        return s;
    }

    /**
     *
     *
     * 获取域名
     * @param str
     */
    public static String domainIp(String str) {
        str = str.replace("http://", "").replace("https://", "");//去除http和https前缀
        String[] arr = str.split("/");//按‘/’分隔，取第一个
        str = arr[0];
        String [] st=str.split(":");
        str=st[0];
    return str;
    }



}
