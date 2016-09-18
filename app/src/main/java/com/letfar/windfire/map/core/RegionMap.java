package com.letfar.windfire.map.core;

import com.letfar.windfire.map.helpers.ArrayIndex;

/**
 * Created by Alex on 08.09.2016.
 */
public class RegionMap {
    private double mapHeight;
    private double mapWidth;
    private double cellHeight;
    private double cellWidth;

    private RegionObject[][] objectMatrix;

    public RegionMap(int cellWidth, int cellHeight,
                     int mapWidth, int mapHeight) {
         // Map size
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        // Map cell size
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;

        // Create map object matrix
        int rows = this.getLastIndexRight() + 1;
        int columns = this.getLastIndexTop() + 1;
        this.objectMatrix = new RegionObject[rows][columns];
    }

    public RegionObject getObject(ArrayIndex index) { return objectMatrix[index.i][index.j]; }
    public void setObject(ArrayIndex index, RegionObject object) { objectMatrix[index.i][index.j] = object; }

    public double getMapHeight() { return mapHeight; }
    public double getMapWidth() { return mapWidth; }

    public double getMapRight() {return getMapWidth() / 2.0; }
    public double getMapTop() {return getMapHeight() / 2.0; }

    public double getMapLeft() { return -getMapRight(); }
    public double getMapBottom() { return -getMapTop(); }

    public double getCellHeight() { return cellHeight; }
    public double getCellWidth() { return cellWidth; }

    public int getLastIndexTop() { return ((int) (getMapHeight() / getCellHeight())) - 1; }
    public int getLastIndexRight() { return ((int) (getMapWidth() / getCellWidth())) - 1; }

    public void iterateMap(RegionMapIterationAction iterationAction) {
        // Start x,y position (left top corner)
        double x = getMapLeft();
        double y = getMapTop();

        // Move from Top to Bottom
        for (int i = 0; i <= getLastIndexTop(); i++, y -= getCellHeight()) {

            // Move from Left to Right
            for (int j = 0; j <= getLastIndexRight(); j++, x += getCellWidth())
                iterationAction.doAction(x, y, new ArrayIndex(i, j));

            // Now is next matrix line, so reset 'x'
            x = getMapLeft();
        }
    }
}
