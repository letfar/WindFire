package com.letfar.windfire.map.concrete_objects;

import com.letfar.windfire.map.core.RegionObject;

/**
 * Created by Alex on 14.09.2016.
 */
public class AiroportRegionObject extends RegionObject{

    @Override
    public boolean isInitialPosition(double x, double y) {
        boolean b1 = x >= -2000 && x <= -1000;
        boolean b2 = y <= -500 && y >= -2000;
        boolean b3 = y <= getLineY(x);

        return b1 && b2 && b3;
    }

    // Line
    private double getLineY(double x) {
        return -2*x - 3500;
    }

    @Override
    public String toString() {
        return " A ";
    }
}
