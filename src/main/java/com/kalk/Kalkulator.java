package com.kalk;

public class Kalkulator {

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int diff(int a, int b) {
        return a - b;
    }

    public double diff(double a, double b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public int div(int a, int b) {
        // if (b!=0)
        return a / b;
        //else
        //  System.out.println("Nie dzielimy przez zero!");
    }

    public double div(double a, double b) {
        //if (b!=0)
        return a / b;
        // else
        //   System.out.println("Nie dzielimy przez zero!");
    }


    public static void main(String[] args) {

        // dodawanie liczb
        int a = 0; // deklaracja zmiennej
        int b = 0;
        Kalkulator kalkulator = new Kalkulator();
        //System.out.println(0/0);
        //int c=a/b;
        // dzielenie z instrukcja warunkowa
        if (b != 0)
            System.out.println(a / b);
        else
            System.out.println("Nie dzielimy przez zero!");

        System.out.println(2.0 / 0.0);
    }
}