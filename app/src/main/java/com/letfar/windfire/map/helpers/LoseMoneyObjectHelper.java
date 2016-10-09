package com.letfar.windfire.map.helpers;

import com.letfar.windfire.map.concrete_objects.ObjectType;
import com.letfar.windfire.map.core.RegionObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 03.10.2016.
 */
public class LoseMoneyObjectHelper {
    private static Map<ObjectType, Boolean> activatedToLoseMoneyObjects = new HashMap<>();

    public static List<ObjectType> getActivatedToLoseMoneyObjects() {
        List<ObjectType> objectTypeList = new ArrayList<>();

        for (Map.Entry<ObjectType, Boolean> objectType : activatedToLoseMoneyObjects.entrySet())
            if (objectType.getValue())
                objectTypeList.add(objectType.getKey());

        return objectTypeList;
    }
    public static void activateLoseMoneyFor(RegionObject regionObject) {
        ObjectType type = ObjectTypeHelper.checkType(regionObject);
        activatedToLoseMoneyObjects.put(type, true);
    }

    public static boolean isLoseMoneyActivatedFor(RegionObject regionObject) {
        ObjectType type = ObjectTypeHelper.checkType(regionObject);
        return activatedToLoseMoneyObjects.containsKey(type) && activatedToLoseMoneyObjects.get(type);
    }
}
