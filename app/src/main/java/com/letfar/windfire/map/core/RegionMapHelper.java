package com.letfar.windfire.map.core;

/**
 * Created by Alex on 14.09.2016.
 */
public class RegionMapHelper {

    public static void addObjectsToMap(final RegionObject[] objects, final RegionMap regionMap) {
        regionMap.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, int i, int j) {
                for (RegionObject object : objects) {
                    if (object.isHere(x, y))
                        regionMap.getObjectMatrix()[i][j] = object;
                }
            }
        });
    }

    public static void fillMapByEmptyObjects(final RegionMap regionMap) {
        regionMap.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, int i, int j) {
                regionMap.getObjectMatrix()[i][j] = new EmptyRegionObject();
            }
        });
    }

    public static void printRegionMap(final RegionMap regionMap) {
        final int[] lastJ = new int[1];

        regionMap.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, int i, int j) {
                boolean isNewLine = j < lastJ[0];

                if (isNewLine) System.out.println();

                RegionObject current = regionMap.getObjectMatrix()[i][j];

                System.out.print((current != null)
                        ? current.toString()
                        : " * ");

                lastJ[0] = j;
            }
        });
    }
}
