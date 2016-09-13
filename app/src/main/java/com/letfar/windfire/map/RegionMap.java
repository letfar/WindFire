package com.letfar.windfire.map;

/**
 * Created by Alex on 08.09.2016.
 */
public class RegionMap {
    private double mapHeight;
    private double mapWidth;
    private double cellHeight;
    private double cellWidth;

    private RegionObject[][] regionObjectMapMatrix;

    private RegionMap() {}

    public static RegionMap createMap(int cellWidth, int cellHeight,
                                      int mapWidth, int mapHeight) {
        RegionMap map = new RegionMap();

        // Map size
        map.mapHeight = mapHeight;
        map.mapWidth = mapWidth;

        // Map cell size
        map.cellHeight = cellHeight;
        map.cellWidth = cellWidth;

        map.regionObjectMapMatrix = new RegionObject[map.getLastIndexWidth()][map.getLastIndexHeight()];

        return map;
    }

    public double getMapHeight() { return mapHeight; }
    public double getMapWidth() { return mapWidth; }
    public double getMapRight() {return getMapWidth() / 2.0; }
    public double getMapTop() {return getMapHeight() / 2.0; }
    public double getMapLeft() { return - getMapRight(); }
    public double getMapBottom() { return - getMapTop(); }

    public double getCellHeight() { return cellHeight; }
    public double getCellWidth() { return cellWidth; }

    public int getLastIndexHeight() { return ((int) (getMapHeight() / getCellHeight())) - 1 ; }
    public int getLastIndexWidth() { return ((int) (getMapWidth() / getCellWidth())) -1; }

    public RegionObject[][] getRegionObjectMapMatrix() { return regionObjectMapMatrix; }

/*    public void addRegionObject(RegionObject regionObject) {
        // Traverse map
        for (int dirRight = -mapWidth, i = 0; dirRight < mapWidth; dirRight += squareWidth, i++) {

            for (int dirTop = -mapHeight, j = 0; dirTop < mapHeight; dirTop += squareHeight, j++) {

                if (regionObject.isHere(dirTop, dirRight))
                    regionObjects[i][j] = regionObject;
            }

        }
    }*/

    public void print() {
        for (int i = 0; i <= getLastIndexWidth(); i++) {
            for (int j = 0; j < getLastIndexHeight(); j++) {
                if (regionObjectMapMatrix[i][j] == null)
                    System.out.print(" * ");
                else
                    System.out.print(" # ");
            }
            System.out.println();
        }
    }
}
