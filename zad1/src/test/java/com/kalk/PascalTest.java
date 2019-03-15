package com.kalk;

import org.junit.Test;

import static org.junit.Assert.*;

public class PascalTest {

    @Test
    public void compute(){
        PascalTriangle pascalTriangle = new PascalTriangle();

    }

    @Test(expected = NegativeArraySizeException.class)
    public void compute1()
    {
        PascalTriangle pascalTriangle = new PascalTriangle();
        int[][] t=new int[0][0];
        pascalTriangle.compute(0);
        assertArrayEquals(t,pascalTriangle.getTriangle());

        t=new int[][]{{1}};
        pascalTriangle.compute(1);
        assertArrayEquals(t,pascalTriangle.getTriangle());

       int[][] t2={{1},{1,1},{1,2,1},{1,3,3,1}};

        pascalTriangle.compute(4);
        assertArrayEquals(t2,pascalTriangle.getTriangle());

        pascalTriangle.compute(-3);


    }

}
