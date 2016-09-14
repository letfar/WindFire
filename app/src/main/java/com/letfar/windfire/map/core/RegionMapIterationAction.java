package com.letfar.windfire.map.core;

/**
 * Created by Alex on 14.09.2016.
 */
public interface RegionMapIterationAction {
    /**
     * Action that called for each matrix cell
     * @param x current cell distance (from left to right)
     * @param y current cell distance (from top to bottom)
     * @param i current cell row index
     * @param j current cell column index
     */
    void doAction(double x, double y, int i, int j);
}
