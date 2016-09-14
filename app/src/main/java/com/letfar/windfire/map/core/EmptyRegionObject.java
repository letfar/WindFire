package com.letfar.windfire.map.core;

/**
 * Created by Alex on 14.09.2016.
 */
public class EmptyRegionObject extends RegionObject {

    @Override
    public boolean isHere(double right, double top) {
        return false;
    }

    @Override
    public String toString() {
        return " * ";
    }
}
