package com.letfar.windfire.map.core;

/**
 * Created by Alex on 08.09.2016.
 */
public abstract class RegionObject {

    /**
     * Create object
     */
    public RegionObject(){}

    /**
     * Check if RegionObject located at this distance
     * @param x distance (from left to right)
     * @param y distance (from top to bottom)
     * @return true, if object is here
     */
    public abstract boolean isInitialPosition(double x, double y);

    /**
     * Symbol to represent object when printing matrix to console, for example
     */
    @Override
    abstract public String toString();

    /**
     * Empty RegionObject without properties.
     * Can use as alternative of null values in RegionMap matrix
     */
    public static class EmptyObject extends RegionObject {
        @Override
        public boolean isInitialPosition(double right, double top) {
            return false;
        }

        @Override
        public String toString() {
            return " * ";
        }
    }
}
