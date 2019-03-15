package Sudoku;

import java.util.Random;

public class SudokuBoard {
    private int[][] board;

    public void firstFill(int n){
        board=new int[9][9];
        Random generator=new Random();
        int k,l;
        for(int i=0; i<n; i++)
        {
            do{
                k=generator.nextInt(9)+1;
                l=generator.nextInt(9)+1;
            } while(board[k][l]!=0);

            board[k][l]=generator.nextInt(9)+1;
        }
    }
    public boolean checkElement(int k, int l)
    {
        for(int i=0; i<9; i++)
        {
            if(i!=k && board[k][l]==board[i][l])
            {
                return false;
            }
            if(i!=l && board[k][l]==board[k][i])
            {
                return false;
            }

        }
        return true;

    }
    public void fillBoard(int k, int l)
    {

    }

    public static void main(String args[])
    {
        //int [][] test=new int [9][9];
        //System.out.println(test[1][2]);
    }
}
