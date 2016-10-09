package com.letfar.windfire.map.helpers;

import com.letfar.windfire.map.concrete_objects.ObjectType;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionObject;

/**
 * Created by Alex on 30.09.2016.
 */
public class PriceHelper {
    public static long totalPrice = 0;

    // Price for square meter
    private static final long WOOD_PRICE = 10;
    private static final long URBAN_PRICE = 100;
    // Price for whole object
    private static final long MILITARY_PRICE = 130000000L;
    // Price for delay (per second)
    private static final long AIROPORT_PRICE = 10000000L;
    private static final long SANATORY_PRICE = 100000000L;


    public static long getPrice(RegionObject object, RegionMap map) {
        ObjectType type = ObjectTypeHelper.checkType(object);
        return getPrice(type, map);
    }

    public static long getPrice(ObjectType type, RegionMap map) {
        double cellSquare = map.getCellHeight() * map.getCellWidth();
        switch (type) {
            case WOOD:
                return (long) cellSquare * WOOD_PRICE;
            case URBAN:
                return (long) cellSquare * URBAN_PRICE;
            case MILITARY:
                return MILITARY_PRICE;
            case AIROPORT:
                return AIROPORT_PRICE;
            case SANATORY:
                return SANATORY_PRICE;
        }
        return 0;
    }

    public static void incTotalPriceFor(RegionObject regionObject, RegionMap regionMap) {
        long objectPrice = PriceHelper.getPrice(regionObject, regionMap);
        PriceHelper.totalPrice += objectPrice;
    }

    public static void incTotalPriceFor(ObjectType objectType, RegionMap regionMap) {
        long objectPrice = PriceHelper.getPrice(objectType, regionMap);
        PriceHelper.totalPrice += objectPrice;
    }
}
