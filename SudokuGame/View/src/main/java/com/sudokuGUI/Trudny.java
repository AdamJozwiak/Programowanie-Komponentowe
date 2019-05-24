package com.sudokuGUI;

import com.sudoku.*;

public class Trudny extends PoziomTrudnosci{
    public SudokuBoard trudny() {
        return super.wybierzPoziom(60);
    }
}
