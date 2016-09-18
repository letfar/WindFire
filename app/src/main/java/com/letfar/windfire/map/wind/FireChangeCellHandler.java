package com.letfar.windfire.map.wind;

import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.helpers.ArrayIndex;

/**
 * Created by Alex on 18.09.2016.
 */
public interface FireChangeCellHandler {
    void handleFireMoving(ArrayIndex fireCellIndex, RegionMap regionMap);
}
