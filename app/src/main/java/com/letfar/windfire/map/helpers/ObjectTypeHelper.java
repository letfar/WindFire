package com.letfar.windfire.map.helpers;

import com.letfar.windfire.map.concrete_objects.*;
import com.letfar.windfire.map.core.RegionObject;

import static com.letfar.windfire.map.concrete_objects.ObjectType.*;

/**
 * Created by Alex on 03.10.2016.
 */
public class ObjectTypeHelper {
    public static ObjectType checkType(RegionObject object) {
        Class<? extends RegionObject> c = object.getClass();
        ObjectType type = null;

        if (c.isAssignableFrom(AiroportRegionObject.class))
            type = ObjectType.AIROPORT;
        if (c.isAssignableFrom(FireRegionObject.class))
            type = ObjectType.FIRE;
        if (c.isAssignableFrom(LakeRegionObject.class))
            type = ObjectType.LAKE;
        if (c.isAssignableFrom(MilitaryStorageRegionObject.class))
            type = ObjectType.MILITARY;
        if (c.isAssignableFrom(SanatoryRegionObject.class))
            type = ObjectType.SANATORY;
        if (c.isAssignableFrom(UrbanRegionObject.class))
            type = ObjectType.URBAN;
        if (c.isAssignableFrom(WoodRegionObject.class))
            type = ObjectType.WOOD;

        return type;
    }

    public static boolean hasPriceBySquareMeter(RegionObject object) {
        ObjectType type = ObjectTypeHelper.checkType(object);

        return type == WOOD ||
                type == URBAN ||
                type == MILITARY;
    }

    public static boolean canLoseMoneyPerSecond(RegionObject regionObject) {
        ObjectType type = checkType(regionObject);
        switch (type) {
            case SANATORY:
                return true;
            case AIROPORT:
                return true;
            default:
                return false;
        }
    }
}
