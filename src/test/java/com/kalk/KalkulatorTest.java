package com.kalk;

import org.junit.Test;

import static org.junit.Assert.*;

public class KalkulatorTest {

    @Test
    public void add() {
        Kalkulator kalkulator=new Kalkulator();
        int c=3;
        int a=1;
        int b=2;
        assertEquals(c,kalkulator.add(a,b));
        assertEquals(Integer.MIN_VALUE,kalkulator.add(Integer.MAX_VALUE,1));
    }

    @Test
    public void add1() {
    }

    @Test
    public void diff() {
    }

    @Test
    public void diff1() {
    }

    @Test
    public void mul() {
    }

    @Test
    public void mul1() {
    }

    @Test
    public void div() {
    }

    @Test
    public void div1() {
    }

    @Test
    public void main() {
    }
}