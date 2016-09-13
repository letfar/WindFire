package com.letfar.windfire.map;

import java.util.HashMap;

/**
 * Created by Alex on 08.09.2016.
 */
public abstract class RegionObject {
    private final HashMap<String, Object> properties;
    private final RegionMap regionMap;

    public RegionObject(RegionMap regionMap) {
        this.regionMap = regionMap;
        this.properties = new HashMap<>();
    }

    public RegionObject(HashMap<String, Object> properties, RegionMap regionMap) {
        this.properties = properties;
        this.regionMap = regionMap;
    }

    public abstract boolean isHere(double dirTop, double dirRight);
}
