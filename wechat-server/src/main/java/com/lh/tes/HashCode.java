package com.lh.tes;

public class HashCode {
    public static void main(String[] args) {
        String a = "123";
        String s = new String("123");
        System.out.println(a.hashCode());
        System.out.println(s.hashCode());
        System.out.println(a == s);
        System.out.println(a.equals(s));
    }
}
