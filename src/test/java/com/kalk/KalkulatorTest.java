package com.kalk;

import org.junit.Test;

import static org.junit.Assert.*;

public class KalkulatorTest {

    @Test
    public void add() {
        Kalkulator kalkulator = new Kalkulator();
        int c = 3;
        int a = 1;
        int b = 2;
        assertEquals(c, kalkulator.add(a, b));
        assertEquals(Integer.MIN_VALUE, kalkulator.add(Integer.MAX_VALUE, 1));
    }

    @Test
    public void add1() {
        Kalkulator kalkulator = new Kalkulator();
        double c = 5.20;
        double a = 1.50;
        double b = 3.70;
        assertEquals(c, kalkulator.add(a, b), 0.01);
        assertEquals(Double.POSITIVE_INFINITY, kalkulator.add(Double.MAX_VALUE, Double.MAX_VALUE), 0.01);
        assertNotEquals(Double.MIN_VALUE, kalkulator.add(Double.MAX_VALUE, 1.00), 0.01);
        assertNotEquals(Double.MIN_VALUE, kalkulator.add(Double.MAX_VALUE, Double.MIN_VALUE), 0.01);
        assertEquals(Double.MAX_VALUE, kalkulator.add(Double.MAX_VALUE, 1.00), 0.01);
    }

    @Test
    public void diff() {
        Kalkulator kalkulator = new Kalkulator();
        int a = 6;
        int b = 3;
        int c = 3;
        assertEquals(c, kalkulator.diff(a, b));
        assertEquals(Integer.MAX_VALUE, kalkulator.diff(Integer.MIN_VALUE, 1));
    }

    @Test
    public void diff1() {
        Kalkulator kalkulator = new Kalkulator();
        double a = 10.00;
        double b = 6.50;
        double c = 3.50;
        assertEquals(c, kalkulator.diff(a, b), 0.01);
        assertEquals(-1.0, kalkulator.diff(Double.MIN_VALUE, 1), 0.01);
        assertNotEquals(Double.NEGATIVE_INFINITY, kalkulator.diff(Double.MIN_VALUE, Double.MAX_VALUE), 0.01);
        assertNotEquals(Double.NEGATIVE_INFINITY, kalkulator.diff(Double.MIN_VALUE, Double.MIN_VALUE), 0.01);
    }

    @Test
    public void mul() {
        Kalkulator kalkulator = new Kalkulator();
        int a = 5;
        int b = 10;
        int c = 50;
        assertEquals(c, kalkulator.mul(a, b));
    }

    @Test
    public void mul1() {
        Kalkulator kalkulator = new Kalkulator();
        double a = 2.0;
        double b = 6.5;
        double c = 13.0;
        assertEquals(c, kalkulator.mul(a, b), 0.01);
    }

    @Test(expected = ArithmeticException.class)
    public void div() {
        Kalkulator kalkulator = new Kalkulator();
        int a = 4;
        int b = 2;
        int c = 2;
        assertEquals(c, kalkulator.div(a, b));
        b = 0;
        c = 0;
        assertEquals(c, kalkulator.div(a, b));
        a = 0;
        assertEquals(c, kalkulator.div(a, b));
    }

    @Test
    public void div1() {
        Kalkulator kalkulator = new Kalkulator();
        double a = 6.0;
        double b = 3.0;
        double c = 2.0;
        assertEquals(c, kalkulator.div(a, b), 0.01);
        b = 0.0;
        c = 0.0;
        assertEquals(Double.POSITIVE_INFINITY, kalkulator.div(a, b), 0.01);
        a = 0.0;
        assertEquals(Double.NaN, kalkulator.div(a, b), 0.01);
    }
}