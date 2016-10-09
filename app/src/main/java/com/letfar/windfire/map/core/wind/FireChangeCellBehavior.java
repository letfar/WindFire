package com.letfar.windfire.map.core.wind;

import com.letfar.windfire.map.concrete_objects.FireRegionObject;
import com.letfar.windfire.map.concrete_objects.MilitaryStorageRegionObject;
import com.letfar.windfire.map.concrete_objects.ObjectType;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionObject;
import com.letfar.windfire.map.helpers.LoseMoneyObjectHelper;
import com.letfar.windfire.map.helpers.PriceHelper;
import com.letfar.windfire.map.helpers.RegionMapHelper;

import static com.letfar.windfire.map.concrete_objects.ObjectType.FIRE;
import static com.letfar.windfire.map.concrete_objects.ObjectType.MILITARY;
import static com.letfar.windfire.map.helpers.ObjectTypeHelper.canLoseMoneyPerSecond;
import static com.letfar.windfire.map.helpers.ObjectTypeHelper.hasPriceBySquareMeter;

/**
 * Created by Alex on 07.10.2016.
 */
public class FireChangeCellBehavior {
    private RegionMap regionMap;
    private RegionObject regionObject;
    private ObjectType type;

    public void apply(RegionMap regionMap, RegionObject object, ObjectType type) {
        init(regionMap, object, type);

        boolean cellNotBurned = type != FIRE;

        if (cellNotBurned) {

            if (MILITARY == type) {
                bombMilitaryObject();
                return;
            }

            incTotalPriceForBurn();
        }
    }

    private void init(RegionMap regionMap, RegionObject object, ObjectType type) {
        this.regionMap = regionMap;
        this.regionObject = object;
        this.type = type;
    }

    private void incTotalPriceForBurn() {
        if (hasPriceBySquareMeter(regionObject))
            PriceHelper.incTotalPriceFor(regionObject, regionMap);

        if (canLoseMoneyPerSecond(regionObject))
            LoseMoneyObjectHelper.activateLoseMoneyFor(regionObject);
    }

    private void bombMilitaryObject() {
        RegionMapHelper.replaceObjects(
                MilitaryStorageRegionObject.class,
                FireRegionObject.class, regionMap);

        PriceHelper.incTotalPriceFor(MILITARY, regionMap);
    }
}
