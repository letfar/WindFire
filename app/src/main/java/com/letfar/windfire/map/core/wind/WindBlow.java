package com.letfar.windfire.map.core.wind;

/**
 * Created by Alex on 25.09.2016.
 */
public class WindBlow {
    private RegionWind.Direction direction;
    private double speed;
    private int duration;

    public WindBlow(RegionWind.Direction direction, double speed, int duration) {
        this.speed = speed;
        this.direction = direction;
        this.duration = duration;
    }

    public RegionWind.Direction getDirection() { return direction; }
    public double getSpeed() { return speed; }
    public int getDuration() { return duration; }
    public double getDistance() { return speed * duration; }
}
