package com.letfar.windfire.map.concrete_objects;

import com.letfar.windfire.map.core.RegionObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex on 22.09.2016.
 */
public class WoodRegionObject extends RegionObject {


    private static List<RegionObject> otherObjects = Arrays.asList(
            new AiroportRegionObject(),
            new LakeRegionObject(),
            new MilitaryStorageRegionObject(),
            new SanatoryRegionObject(),
            new UrbanRegionObject());

    @Override
    public boolean isInitialPosition(double x, double y) {
        for (RegionObject object : otherObjects) {
            if (object.isInitialPosition(x, y)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " ^ ";
    }
}
