package com.sudoku;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class SudokuField implements Cloneable, Comparable, Serializable {
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
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("Value", this.value).toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Object o) {
        return 0;                                               //???????????????????????????????????????????????
    }
}
