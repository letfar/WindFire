package com.letfar.windfire.map.concrete_objects;

import com.letfar.windfire.map.core.RegionObject;

/**
 * Created by Alex on 15.09.2016.
 */
public class FireRegionObject extends RegionObject {
    @Override
    public boolean isHere(double x, double y) {
        boolean b1 = x >= -100 && x <= 100;
        boolean b2 = y >= -100 && y <= 100;

        return b1 && b2;
    }

    @Override
    public String toString() {
        return " @ ";
    }
}
