package com.kalk;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void add() {
        Calculator calculator = new Calculator();
        int c = 3;
        int a = 1;
        int b = 2;
        assertEquals(c, calculator.add(a, b));
        assertEquals(Integer.MIN_VALUE, calculator.add(Integer.MAX_VALUE, 1));
    }

    @Test
    public void add1() {
        Calculator calculator = new Calculator();
        double c = 5.20;
        double a = 1.50;
        double b = 3.70;
        assertEquals(c, calculator.add(a, b), 0.01);
        assertEquals(Double.POSITIVE_INFINITY, calculator.add(Double.MAX_VALUE, Double.MAX_VALUE), 0.01);
        assertNotEquals(Double.MIN_VALUE, calculator.add(Double.MAX_VALUE, 1.00), 0.01);
        assertNotEquals(Double.MIN_VALUE, calculator.add(Double.MAX_VALUE, Double.MIN_VALUE), 0.01);
        assertEquals(Double.MAX_VALUE, calculator.add(Double.MAX_VALUE, 1.00), 0.01);
    }

    @Test
    public void diff() {
        Calculator calculator = new Calculator();
        int a = 6;
        int b = 3;
        int c = 3;
        assertEquals(c, calculator.diff(a, b));
        assertEquals(Integer.MAX_VALUE, calculator.diff(Integer.MIN_VALUE, 1));
    }

    @Test
    public void diff1() {
        Calculator calculator = new Calculator();
        double a = 10.00;
        double b = 6.50;
        double c = 3.50;
        assertEquals(c, calculator.diff(a, b), 0.01);
        assertEquals(-1.0, calculator.diff(Double.MIN_VALUE, 1), 0.01);
        assertNotEquals(Double.NEGATIVE_INFINITY, calculator.diff(Double.MIN_VALUE, Double.MAX_VALUE), 0.01);
        assertNotEquals(Double.NEGATIVE_INFINITY, calculator.diff(Double.MIN_VALUE, Double.MIN_VALUE), 0.01);
    }

    @Test
    public void mul() {
        Calculator calculator = new Calculator();
        int a = 5;
        int b = 10;
        int c = 50;
        assertEquals(c, calculator.mul(a, b));
    }

    @Test
    public void mul1() {
        Calculator calculator = new Calculator();
        double a = 2.0;
        double b = 6.5;
        double c = 13.0;
        assertEquals(c, calculator.mul(a, b), 0.01);
    }

    @Test(expected = ArithmeticException.class)
    public void div() {
        Calculator calculator = new Calculator();
        int a = 4;
        int b = 2;
        int c = 2;
        assertEquals(c, calculator.div(a, b));
        b = 0;
        c = 0;
        assertEquals(c, calculator.div(a, b));
        a = 0;
        assertEquals(c, calculator.div(a, b));
    }

    @Test
    public void div1() {
        Calculator calculator = new Calculator();
        double a = 6.0;
        double b = 3.0;
        double c = 2.0;
        assertEquals(c, calculator.div(a, b), 0.01);
        b = 0.0;
        c = 0.0;
        assertEquals(Double.POSITIVE_INFINITY, calculator.div(a, b), 0.01);
        a = 0.0;
        assertEquals(Double.NaN, calculator.div(a, b), 0.01);
    }
}