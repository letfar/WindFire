package com.letfar.windfire.map.wind;

/**
 * Created by Alex on 17.09.2016.
 */
public interface WindBlowHandler {
    void handleBlow(RegionWind.Direction direction, double windSpeed, int durationSeconds);
}
