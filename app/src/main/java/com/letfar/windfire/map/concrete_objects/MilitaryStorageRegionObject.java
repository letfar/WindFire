package com.letfar.windfire.map.concrete_objects;

import com.letfar.windfire.map.core.RegionObject;

/**
 * Created by Alex on 08.09.2016.
 */
public class MilitaryStorageRegionObject extends RegionObject {

    @Override
    public boolean isHere(double x, double y) {
        boolean b1 = y <= -500 && y >= -1000;
        boolean b2 = x >= 500 && x <= 1000;
        return  b1 && b2;
    }

    @Override
    public String toString() {
        return " M ";
    }
}
