package com.sudoku;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuField {
    private int value;

    public SudokuField() {
    }

    public SudokuField(int fieldValue) {
        setFieldValue(fieldValue);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("Value",this.value).toString();
    }
}
