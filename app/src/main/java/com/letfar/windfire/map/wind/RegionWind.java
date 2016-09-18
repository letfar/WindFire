package com.letfar.windfire.map.wind;

/**
 * Created by Alex on 15.09.2016.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *         N
 *    NW      NE
 *  W     -      E
 *   SW       SE
*         S
 */
public class RegionWind {
    public enum Direction {
        N, NE, E, SE, S, SW, W, NW
    }

    private List<WindBlowHandler> windBlowHandlerList;

    public List<WindBlowHandler> windBlowHandlers() { return windBlowHandlerList; }

    public RegionWind() {
        this.windBlowHandlerList = new ArrayList<>();
    }

    public void blow(Direction direction, double speed, int seconds) {
        for (WindBlowHandler blowHandler : windBlowHandlerList)
            blowHandler.handleBlow(direction, speed, seconds);
    }
}
