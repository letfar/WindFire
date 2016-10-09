package com.letfar.windfire.map.core.wind;

/**
 * Created by Alex on 15.09.2016.
 */

import com.letfar.windfire.map.concrete_objects.FireRegionObject;
import com.letfar.windfire.map.concrete_objects.ObjectType;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionObject;
import com.letfar.windfire.map.helpers.ArrayIndex;
import com.letfar.windfire.map.helpers.ObjectTypeHelper;
import com.letfar.windfire.map.helpers.RegionMapHelper;
import com.letfar.windfire.map.helpers.WindHelper;

import java.util.List;


public class RegionWind {

    public enum Direction {
        N, NE, E, SE, S, SW, W, NW
    }

    public RegionWind(RegionMap regionMap) {
        this.regionMap = regionMap;
    }

    private RegionMap regionMap;
    private WindBlow blow;
    private FireChangeCellBehavior fireChangeCellBehavior;

    private int cellToBurnCount;
    private int cellToBurnRemaining;
    private double distanceToBurn;
    private double cellDistance;

    public void registerBlow(WindBlow blow) {
        this.blow = blow;
        this.fireChangeCellBehavior = new FireChangeCellBehavior();
        init();
    }

    private void init() {
        double cellWidth = regionMap.getCellWidth();
        double cellHeight = regionMap.getCellHeight();
        distanceToBurn = blow.getDistance() * 0.1; // include fire speed
        cellDistance = WindHelper.getCellDistance(blow, cellWidth, cellHeight);
        cellToBurnCount = WindHelper.getCellToBurnCount(cellDistance, distanceToBurn);
        cellToBurnRemaining = cellToBurnCount;
    }

    public boolean blowByCell() {
        if (blow == null) return false;
        if (cellDistance > distanceToBurn) return false;
        if (cellToBurnRemaining <= 0) return false;

        List<ArrayIndex> fireObjectIndexList =
                RegionMapHelper.findObjectPositionsInMap(FireRegionObject.class, regionMap);

        for (ArrayIndex fireObjectIndex : fireObjectIndexList)
            moveFireObjectToNextCell(fireObjectIndex);

        --cellToBurnRemaining;

        return cellToBurnRemaining > 0;
    }

    private void moveFireObjectToNextCell(ArrayIndex fireObjectIndex) {
        ArrayIndex nexCellIndex = getNextCellIndex(fireObjectIndex);

        RegionObject cellObject = regionMap.getObject(nexCellIndex);
        ObjectType type = ObjectTypeHelper.checkType(cellObject);

        fireChangeCellBehavior.apply(regionMap, cellObject, type);

        moveFireOnCellIfCan(nexCellIndex, cellObject);
    }

    private void moveFireOnCellIfCan(ArrayIndex nexCellIndex, RegionObject object) {
        if (FireRegionObject.canBurn(object))
            regionMap.setObject(nexCellIndex, FireRegionObject.getDefaultFireObject());
    }

    private ArrayIndex getNextCellIndex(ArrayIndex fireObjectIndex) {
        return WindHelper
                .calcNextCellIndex(blow.getDirection(), fireObjectIndex)
                .normalize(regionMap.getLastIndexRight(), regionMap.getLastIndexTop());
    }
}
