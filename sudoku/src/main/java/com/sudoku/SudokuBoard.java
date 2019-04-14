package com.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SudokuBoard {

    List<List<SudokuField>> sudokuField;

    public SudokuBoard(int n) {

        sudokuField = (List) Arrays.asList(new List[9]);

        for (int i = 0; i < 9; i++) {
            sudokuField.set(i, Arrays.asList(new SudokuField[9]));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuField.get(i).set(j, new SudokuField());
            }
        }

        int w, k;
        for (int i = 0; i < n; i++) {
            do {
                w = rand() - 1;
                k = rand() - 1;
            } while (sudokuField.get(w).get(k).getFieldValue() != 0);

            do {
                sudokuField.get(w).get(k).setFieldValue(rand());
            } while (!checkBoard());
        }
    }

    public int rand() {
        Random generator = new Random();
        return generator.nextInt(9) + 1;
    }


    public int get(int w, int k) {
        return sudokuField.get(w).get(k).getFieldValue();
    }

    public void set(int w, int k, int value) {
        sudokuField.get(w).get(k).setFieldValue(value);
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuRow getRow(int y) {
        List<SudokuField> row = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < row.size(); i++) {
            row.set(i, new SudokuField(sudokuField.get(y).get(i).getFieldValue()));
        }

        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> column = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            column.set(i, new SudokuField(sudokuField.get(i).get(x).getFieldValue()));
        }

        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> box = Arrays.asList(new SudokuField[9]);

        int pom1 = (x / 3) * 3;
        int pom2 = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //box[3 * i + j] = sudokuField[pom1 + i][pom2 + j];
                box.set(3 * i + j, new SudokuField(sudokuField.get(pom1 + i).get(pom2 + j).getFieldValue()));
            }
        }

        return new SudokuBox(box);
    }

   /* @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("Board",this.sudokuField).toString();

    }*/

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.SIMPLE_STYLE).append("sudokuField", sudokuField).toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        SudokuBoard sudokuBoard=(SudokuBoard) object;

        return new EqualsBuilder()
                .append(sudokuField, sudokuBoard.sudokuField)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.sudokuField).toHashCode();
    }

   // @Override


    public static void main(final String[] args) {

        SudokuBoard sudokuBoard = new SudokuBoard(4);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.get(i, j) + " ");
            }
            System.out.println();

        }

        System.out.println();
        System.out.println();
        System.out.println();

        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        backtrackingSudokuSolver.solve(sudokuBoard);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuBoard.get(i, j) + " ");
            }
            System.out.println();

        }
        //String a=sudokuBoard.toString();
       // int b=sudokuBoard.hashCode();
        //System.out.println(sudokuBoard.toString());
    }
}