package com.letfar.windfire.map.helpers;

import com.letfar.windfire.map.core.wind.RegionWind;
import com.letfar.windfire.map.core.wind.WindBlow;

import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;

/**
 * Created by Alex on 25.09.2016.
 */
public class WindHelper {

    public static int getCellToBurnCount(WindBlow blow, double distanceToBurn, double cellWidth, double cellHeight) {
        double cellDistance = getCellDistance(blow, cellWidth, cellHeight);
        return getCellToBurnCount(cellDistance, distanceToBurn);
    }

    public static int getCellToBurnCount(double cellDistance, double distanceToBurn) {
        return (int) round(distanceToBurn / cellDistance);
    }

    public static double getCellDistance(WindBlow blow, double cellWidth, double cellHeight) {
        boolean verticalDirection = blow.getDirection() == RegionWind.Direction.N || blow.getDirection() == RegionWind.Direction.S;
        boolean horizontalDirection = blow.getDirection() == RegionWind.Direction.E || blow.getDirection() == RegionWind.Direction.W;
        boolean diagonalDirection = blow.getDirection() == RegionWind.Direction.NE || blow.getDirection() == RegionWind.Direction.NW
                || blow.getDirection() == RegionWind.Direction.SE || blow.getDirection() == RegionWind.Direction.SW;

        if (verticalDirection)
            return cellHeight;

        if (horizontalDirection)
            return cellWidth;

        if (diagonalDirection) {
            double diagonalLength = sqrt(pow(cellHeight, 2) + pow(cellWidth, 2));
            return diagonalLength;
        }

        // Should never happen
        return 0;
    }


    public static ArrayIndex calcNextCellIndex(RegionWind.Direction direction, ArrayIndex currentIndex) {
        int nextFireCell_i = currentIndex.i;
        int nextFireCell_j = currentIndex.j;

        switch (direction) {
            case N:
                nextFireCell_i -= 1;
                break;
            case S:
                nextFireCell_i += 1;
                break;
            case E:
                nextFireCell_j += 1;
                break;
            case W:
                nextFireCell_j -= 1;
                break;
            case NE:
                nextFireCell_i -= 1;
                nextFireCell_j += 1;
                break;
            case NW:
                nextFireCell_i -= 1;
                nextFireCell_j -= 1;
                break;
            case SE:
                nextFireCell_i += 1;
                nextFireCell_j += 1;
                break;
            case SW:
                nextFireCell_i += 1;
                nextFireCell_j -= 1;
                break;
        }
        
        return new ArrayIndex(nextFireCell_i, nextFireCell_j);
    }

}
