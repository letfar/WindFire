package com.letfar.windfire.map.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 14.09.2016.
 */
public class BezierCurveHelper {

    public static double bezierFor3Points(double p1Coordinate, double p2Coordinate, double p3Coordinate, double t) {
        return Math.pow(1 - t, 2) * p1Coordinate + 2 * (1 - t) * t * p2Coordinate + Math.pow(t, 2) * p3Coordinate;
    }

    public static double besizerFor3Points_getT(double coordinate, double p1Coordinate, double p2Coordinate, double p3Coordinate) {
        return Math.sqrt((coordinate - p1Coordinate)/(p3Coordinate - p2Coordinate));
    }

    public static List<Double> bezierFor3Points_getCoordinateList(double p1Coordinate, double p2Coordinate, double p3Coordinate,double tStep) {
        int stepCount = (int) (1/tStep) + 1;
        List<Double> resultList = new ArrayList<>(stepCount);

        double t = 0;
        for (int i = 0; i < stepCount; i++) {
            resultList.add(bezierFor3Points(p1Coordinate, p2Coordinate, p3Coordinate, t));
            t += tStep;
        }

        return resultList;
    }

}
