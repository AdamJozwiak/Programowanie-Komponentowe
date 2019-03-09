package com.kalk;

public class PascalTriangle {
    public static void compute(int n)
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

    public static void compute1(int n)
    {
        int[][] t = new int[n][n];

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<=i; j++ )
            {
                if(j == 0 || j == i)
                {
                    t[i][j] = 1;
                }

                else
                {
                    t[i][j] = t[i-1][j-1] + t[i-1][j];
                }
                System.out.print(t[i][j]);
            }
            System.out.println();
        }
    }
}
