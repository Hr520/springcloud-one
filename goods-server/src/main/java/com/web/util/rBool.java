package com.web.util;

import java.io.*;

public class rBool {

    public  static Boolean ChangeIntToBoolean(int i){
        if (i==1){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        String encoding = "UTF-8";
        File file=new File("c://Users//Lenovo//Desktop//tt.txt");
        BufferedReader reader=null;
        StringBuffer sbf = new StringBuffer();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            System.out.println(sbf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
