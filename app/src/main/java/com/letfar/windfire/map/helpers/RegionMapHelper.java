package com.letfar.windfire.map.helpers;

import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionMapIterationAction;
import com.letfar.windfire.map.core.RegionObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 14.09.2016.
 */
public class RegionMapHelper {
    public static void fillMapByEmptyObjects(final RegionMap regionMap) {
        regionMap.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, ArrayIndex cellIndex) {
                regionMap.setObject(cellIndex, new RegionObject.EmptyObject());
            }
        });
    }

    public static void addObjectsToMap(final RegionObject[] objects, final RegionMap regionMap) {
        regionMap.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, ArrayIndex cellIndex) {
                for (RegionObject object : objects) {
                    if (object.isInitialPosition(x, y))
//                        System.out.printf("(x=%s, y=%s)\n", x, y);
                        regionMap.setObject(cellIndex, object);
                }
            }
        });
    }

    public static List<ArrayIndex> findObjectPositionsInMap(final Class<?> classToFound, final RegionMap regionMap) {
        final List<ArrayIndex> foundedObjIndexes = new ArrayList<>();

        regionMap.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, ArrayIndex cellIndex) {
                RegionObject current = regionMap.getObject(cellIndex);

                if (classToFound.isInstance(current))
                    foundedObjIndexes.add(cellIndex);
            }
        });

        return foundedObjIndexes;
    }

    public static void printMap(final RegionMap regionMap) {
        final ArrayIndex lastCellIndex = new ArrayIndex(0, 0);

        regionMap.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, ArrayIndex cellIndex) {
                boolean isNewLine = cellIndex.j < lastCellIndex.j;

                if (isNewLine) System.out.println();

                RegionObject current = regionMap.getObject(cellIndex);

                System.out.print((current != null)
                        ? current.toString()
                        : " * ");

                lastCellIndex.setOf(cellIndex);
            }
        });

        System.out.println();
    }

    public static void replaceObjects(final Class<?> classToFound, final Class<? extends RegionObject> classToReplace, RegionMap regionMap) {
        for (ArrayIndex i : findObjectPositionsInMap(classToFound, regionMap)) {
            try {
                regionMap.setObject(i, classToReplace.newInstance());
            } catch (Exception e) {
                throw new RuntimeException("Can't call default constructor to replace object in map", e);
            }
        }
    }
}
