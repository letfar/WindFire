package com.letfar.windfire.map.wind;

import com.letfar.windfire.map.concrete_objects.FireRegionObject;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.helpers.ArrayIndex;
import com.letfar.windfire.map.helpers.RegionMapHelper;
import com.letfar.windfire.map.wind.RegionWind.Direction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by Alex on 17.09.2016.
 */
public class FireWindBlowHandler implements WindBlowHandler {
    private RegionMap regionMap;
    private List<FireChangeCellHandler> fireChangeCellHandlerList;

    public FireWindBlowHandler(RegionMap regionMap) {
        this.regionMap = regionMap;
        fireChangeCellHandlerList = new ArrayList<>();
    }

    public List<FireChangeCellHandler> fireChangeCellHandlers() { return fireChangeCellHandlerList; }

    @Override
    public void handleBlow(Direction direction, double windSpeed, int durationSeconds) {
        double fireSpeed = getFireSpeed(windSpeed);
        double areaToBurn = fireSpeed * durationSeconds;

        int cellToBurnCount = getCellToBurnCount(areaToBurn, direction);

        List<ArrayIndex> fireObjectPositions = RegionMapHelper.findObjectPositionsInMap(
                FireRegionObject.class,
                regionMap);

        for (ArrayIndex fireCellIndex : fireObjectPositions)
            moveFireByDirection(fireCellIndex, direction, cellToBurnCount);
    }

    private void moveFireByDirection(ArrayIndex fireCellIndex, Direction direction, int burnedCellCount) {

        for (int cellToBurnNo = 1; cellToBurnNo <= burnedCellCount; cellToBurnNo++) {
            ArrayIndex nextFireCellIndex = getNextCellIndex(direction, fireCellIndex, cellToBurnNo);

            for (FireChangeCellHandler handler : fireChangeCellHandlerList)
                handler.handleFireMoving(nextFireCellIndex, regionMap);

            regionMap.setObject(nextFireCellIndex, new FireRegionObject());
        }
    }

    private ArrayIndex getNextCellIndex(Direction direction, ArrayIndex fireCellIndex, int cellToBurnNo) {
        int nextFireCell_i = fireCellIndex.i;
        int nextFireCell_j = fireCellIndex.j;

        switch (direction) {
            case N:
                nextFireCell_i -= cellToBurnNo;
                break;
            case S:
                nextFireCell_i += cellToBurnNo;
                break;
            case E:
                nextFireCell_j += cellToBurnNo;
                break;
            case W:
                nextFireCell_j -= cellToBurnNo;
            case NE:
                nextFireCell_i -= cellToBurnNo;
                nextFireCell_j += cellToBurnNo;
                break;
            case NW:
                nextFireCell_i -= cellToBurnNo;
                nextFireCell_j -= cellToBurnNo;
                break;
            case SE:
                nextFireCell_i += cellToBurnNo;
                nextFireCell_j += cellToBurnNo;
                break;
            case SW:
                nextFireCell_i += cellToBurnNo;
                nextFireCell_j -= cellToBurnNo;
                break;
        }

        int maxI = regionMap.getLastIndexRight();
        int maxJ = regionMap.getLastIndexTop();

        if (nextFireCell_i > maxI)
            nextFireCell_i = maxI;

        if (nextFireCell_j > maxJ)
            nextFireCell_j = maxJ;

        return new ArrayIndex(nextFireCell_i, nextFireCell_j);
    }

    private int getCellToBurnCount(double areaToBurn, Direction direction) {
        double cellWidth = regionMap.getCellWidth();
        double cellHeight = regionMap.getCellHeight();

        boolean verticalDirection = direction == Direction.N || direction == Direction.S;
        boolean horizontalDirection = direction == Direction.S || direction == Direction.W;
        boolean diagonalDirection = direction == Direction.NE || direction == Direction.NW
                || direction == Direction.SE || direction == Direction.SW;

        if (verticalDirection)
            return (int) round(areaToBurn / cellHeight);

        if (horizontalDirection)
            return (int) round(areaToBurn / cellWidth);

        if (diagonalDirection) {
            double diagonalLength = sqrt(pow(cellHeight, 2) + pow(cellWidth, 2));
            return (int) round(areaToBurn / diagonalLength);
        }

        // Never happens
        return 0;
    }

    private double getFireSpeed(double windSpeed) {
        return 0.1 * windSpeed;
    }
}
