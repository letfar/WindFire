package com.letfar.windfire.map;

import com.letfar.windfire.map.concrete_objects.*;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionObject;
import com.letfar.windfire.map.helpers.RegionMapHelper;
import com.letfar.windfire.map.core.wind.RegionWind;
import com.letfar.windfire.map.core.wind.WindBlow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alex on 08.09.2016.
 */
public class RegionMapTest {
    private double d = 0.0000000001;

    @Test
    public void testGetters() {
        RegionMap regionMap = new RegionMap(10, 20, 140, 100);

        assertEquals(20, regionMap.getCellHeight(), d);
        assertEquals(10, regionMap.getCellWidth(), d);
        assertEquals(140, regionMap.getMapWidth(), d);
        assertEquals(100, regionMap.getMapHeight(), d);
        assertEquals(-50, regionMap.getMapBottom(), d);
        assertEquals(50, regionMap.getMapTop(), d);
        assertEquals(-70, regionMap.getMapLeft(), d);
        assertEquals(70, regionMap.getMapRight(), d);
        assertEquals(4, regionMap.getLastIndexTop());
        assertEquals(13, regionMap.getLastIndexRight());


        // Check width increase step by step
        double width = regionMap.getMapLeft();

        for (int i = 0; i <= regionMap.getLastIndexRight(); i++)
            width += regionMap.getCellWidth();

        assertEquals(regionMap.getMapRight(), width, d);


        // Check height increase step by step
        double height = regionMap.getMapBottom();

        for (int i = 0; i <= regionMap.getLastIndexTop(); i++)
            height += regionMap.getCellHeight();

        assertEquals(regionMap.getMapTop(), height, d);

    }

    @Test
    public void testMilitaryObject() {
        final RegionMap regionMap = new RegionMap(100, 100, 4000, 4000);

        final RegionObject[] initObjects = {
                new MilitaryStorageRegionObject(),
                new AiroportRegionObject(),
                new LakeRegionObject(),
                new SanatoryRegionObject(),
                new UrbanRegionObject(),
                new FireRegionObject()
        };

        RegionMapHelper.addObjectsToMap(initObjects, regionMap);

        RegionMapHelper.printMap(regionMap);
    }

    @Test
    public void test1() {
        final RegionMap regionMap = new RegionMap(100, 100, 4000, 4000);

        final RegionObject[] initObjects = {
                new MilitaryStorageRegionObject(),
                new AiroportRegionObject(),
                new LakeRegionObject(),
                new SanatoryRegionObject(),
                new UrbanRegionObject(),
                new WoodRegionObject(),
                new FireRegionObject()
        };

        RegionMapHelper.addObjectsToMap(initObjects, regionMap);
        RegionWind wind = new RegionWind(regionMap);
        wind.registerBlow(new WindBlow(RegionWind.Direction.SE, 5 ,4000));
        System.out.println();
        RegionMapHelper.printMap(regionMap);
    }
}