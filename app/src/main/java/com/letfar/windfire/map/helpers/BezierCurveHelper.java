package com.letfar.windfire.map.helpers;

/**
 * Created by Alex on 14.09.2016.
 */
public class BezierCurveHelper {

    public static double bezierFor3Points_getCoordByT(double p1Coordinate, double p2Coordinate, double p3Coordinate, double t) {
        return Math.pow(1 - t, 2) * p1Coordinate + 2 * (1 - t) * t * p2Coordinate + Math.pow(t, 2) * p3Coordinate;
    }

    public static double besizerFor3Points_getT(double coordinate, double p1Coordinate, double p2Coordinate, double p3Coordinate) {
        return Math.sqrt((coordinate - p1Coordinate)/(p3Coordinate - p2Coordinate));
    }
}
