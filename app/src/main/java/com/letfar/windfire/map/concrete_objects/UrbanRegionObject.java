package com.letfar.windfire.map.concrete_objects;

import com.letfar.windfire.map.core.RegionObject;

/**
 * Created by Alex on 08.09.2016.
 */
public class UrbanRegionObject extends RegionObject {

    @Override
    public boolean isHere(double x, double y) {
        if (x >= -2000 && x < -1500)
            if (y >= 500) return true;

        if (x >= -1500 && x < -1000)
            if (y >= 1000) return true;

        if (x >= -1000 && x <= -500)
            if (y >= 1500) return true;

        return false;
    }

    @Override
    public String toString() {
        return " U ";
    }
}
