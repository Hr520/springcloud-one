package com.web.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheClass {

    private static Map<String,String> cache=new ConcurrentHashMap<String, String>();
    private static  void setCache(String key,String obj,long seconds){
        cache.put(key,obj);
    }

    public  static  String getCache(String key){
        return cache.get(key);
    }

    public static void removeCache(String key){
        cache.remove(key);
    }

    public static void main(String[] args) {
        removeCache("china");
        String name = getCache("China");
        System.out.println("name="+name);
        setCache("China","中国",60*60*24*30);
        name = getCache("China");
        System.out.println("第二次取值name="+name);
        removeCache("China");
        name = getCache("China");
        System.out.println("第三次取值name="+name);
    }
}

