package com.letfar.windfire.map.helpers;

/**
 * Created by Alex on 18.09.2016.
 */
public class ArrayIndex {
    public int i;
    public int j;

    public ArrayIndex(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void setOf(ArrayIndex a) {
        set(a.i, a.j);
    }

    public ArrayIndex set(int i, int j) {
        this.i = i;
        this.j = j;
        return this;
    }

    public ArrayIndex normalize(int maxI, int maxJ) {
        if (i > maxI)
            i = maxI;
        if (j > maxJ)
            j = maxJ;
        if (i < 0)
            i = 0;
        if (j < 0)
            j = 0;

        return this;
    }
}
