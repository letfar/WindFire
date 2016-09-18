package com.letfar.windfire.map.core;

import com.letfar.windfire.map.helpers.ArrayIndex;

/**
 * Created by Alex on 14.09.2016.
 */
public interface RegionMapIterationAction {
    /**
     * Action that called for each matrix cell
     * @param x current cell distance (from left to right)
     * @param y current cell distance (from top to bottom)
     * @param cellIndex current cell i and j indexes
     */
    void doAction(double x, double y, ArrayIndex cellIndex);
}
