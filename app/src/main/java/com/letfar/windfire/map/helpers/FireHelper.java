package com.letfar.windfire.map.helpers;

import com.letfar.windfire.map.concrete_objects.FireRegionObject;
import com.letfar.windfire.map.concrete_objects.MilitaryStorageRegionObject;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionObject;

import java.util.List;

/**
 * Created by Alex on 22.09.2016.
 */
public class FireHelper {
    public static void killMilitaryObjectIfFireTouch(RegionMap regionMap, RegionObject object) {
        if (object instanceof MilitaryStorageRegionObject) {
            List<ArrayIndex> indexList = RegionMapHelper.findObjectPositionsInMap(MilitaryStorageRegionObject.class, regionMap);

            for (ArrayIndex militaryPos : indexList) {
                regionMap.setObject(militaryPos, new FireRegionObject());
            }
        }
    }
}
