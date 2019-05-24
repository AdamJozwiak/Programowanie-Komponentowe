package com.sudokuGUI;

import com.sudoku.BacktrackingSudokuSolver;
import com.sudoku.SudokuBoard;
import org.junit.Assert;
import org.junit.Test;

public class PoziomTrudnosciTest {

    @Test
    public void wybierzPoziom() {
        PoziomTrudnosci poziomTrudnosci = new PoziomTrudnosci();
        SudokuBoard sudokuBoard1 = poziomTrudnosci.wybierzPoziom(20);

        int pom = 0;
        for(int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if(sudokuBoard1.get(i, j) == 0)
                {
                    pom++;
                }
            }
        }
        Assert.assertEquals(20, pom);

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

        Assert.assertTrue(solver.solve(sudokuBoard1));

    }
}