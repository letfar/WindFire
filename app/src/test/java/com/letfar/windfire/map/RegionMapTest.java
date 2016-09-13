package com.letfar.windfire.map;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 08.09.2016.
 */
public class RegionMapTest {
    private double d = 0.0000000001;

    @Test
    public void testGetters() {

        RegionMap regionMap = RegionMap.createMap(10, 20, 140, 100);
        assertEquals(20, regionMap.getCellHeight(), d);
        assertEquals(10, regionMap.getCellWidth(), d);
        assertEquals(140, regionMap.getMapWidth(), d);
        assertEquals(100, regionMap.getMapHeight(), d);
        assertEquals(-50, regionMap.getMapBottom(), d);
        assertEquals(50, regionMap.getMapTop(), d);
        assertEquals(-70, regionMap.getMapLeft(), d);
        assertEquals(70, regionMap.getMapRight(), d);
        assertEquals(4, regionMap.getLastIndexHeight());
        assertEquals(13, regionMap.getLastIndexWidth());


        // Check width increase step by step
        double width = regionMap.getMapLeft();

        for (int i = 0; i <= regionMap.getLastIndexWidth(); i++)
            width += regionMap.getCellWidth();

        assertEquals(regionMap.getMapRight(), width, d);


        // Check height increase step by step
        double height = regionMap.getMapBottom();

        for (int i = 0; i <= regionMap.getLastIndexHeight(); i++)
            height += regionMap.getCellHeight();

        assertEquals(regionMap.getMapTop(), height, d);

    }
}