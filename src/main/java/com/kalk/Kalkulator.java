package com.kalk;

public class Kalkulator {
    public static void main(String[] args) {

        int a=4;
        int b=5;
        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a*b);
        System.out.println(a+b*b);

        if (b!=0)
            System.out.println(a/b);
        else
            System.out.println("Nie dzielimy przez zero!");
    }
}
