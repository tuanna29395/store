package com.anhtuan.store.service.impl;

public class test {
    public static void main(String[] args) {
            double res = div(10,0);


        System.out.println("abbc");
    }
    static double div(int a, int b){
        double c = 0;
       try {
            c=  a/b;
       } catch (Exception e){
           System.out.println("Abc");
       }
       return c;
    }
}
