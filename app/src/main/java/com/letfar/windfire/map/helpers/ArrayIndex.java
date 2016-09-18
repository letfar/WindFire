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
        this.i = a.i;
        this.j = a.j;
    }
}
