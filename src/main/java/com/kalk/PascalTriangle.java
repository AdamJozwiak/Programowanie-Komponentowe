package com.kalk;

public class PascalTriangle {
    private static int[][] triangle;
    public static void compute1(int n)
    {
        int number = 1;

        for(int i=0; i<n; i++)
        {
            number = 1;

            for(int j=0; j<=i; j++ )
            {
                System.out.print(number);
                number = number * (i-j)/(j+1);
            }
            System.out.println();
        }
    }

    public void compute(int n)
    {
        triangle=new int[n][];

        for(int i=0; i<n; i++)
        {
            triangle[i]=new int[i+1];
            for(int j=0; j<=i; j++ )
            {
                if(j == 0 || j == i)
                {
                    triangle[i][j] = 1;
                }

                else
                {
                    triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
                }
                //System.out.print(triangle[i][j]);
            }
           // System.out.println();
        }
    }
    public int[][] getTriangle()
    {
        return triangle;
    }
}
