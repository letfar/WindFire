package com.letfar.windfire.map.core;

import java.util.HashMap;

/**
 * Created by Alex on 08.09.2016.
 */
public abstract class RegionObject {

    private final HashMap<String, Object> properties;

    /**
     * Create object without any properties
     */
    public RegionObject() {
        this(new HashMap<String, Object>());
    }

    /**
     * Create object with custom properties
     * @param properties object properties
     */
    public RegionObject(HashMap<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * Check if RegionObject located at this distance
     * @param x distance (from left to right)
     * @param y distance (from top to bottom)
     * @return true, if object is here
     */
    public abstract boolean isHere(double x, double y);

    /**
     * Symbol to represent object when printing matrix
     */
    @Override
    abstract public String toString();

    /**
     * Empty RegionObject without properties.
     * Can use as alternative of null values in RegionMap matrix
     */
    public static class EmptyObject extends RegionObject {
        @Override
        public boolean isHere(double right, double top) {
            return false;
        }

        @Override
        public String toString() {
            return " * ";
        }
    }
}
