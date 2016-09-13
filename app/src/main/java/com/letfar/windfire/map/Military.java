package com.letfar.windfire.map;

import java.util.HashMap;

/**
 * Created by Alex on 08.09.2016.
 */
public class Military extends RegionObject {

    public Military(RegionMap regionMap) {
        super(regionMap);
    }

    public Military(HashMap<String, Object> properties, RegionMap regionMap) {
        super(properties, regionMap);
    }

    @Override
    public boolean isHere(double dirTop, double dirRight) {
        boolean b1 = dirTop < -500 && dirTop > -1000;
        boolean b2 = dirRight > 500 && dirRight < 100;
        return  b1 && b2;
    }
}
