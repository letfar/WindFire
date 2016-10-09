package com.letfar.windfire.map.concrete_objects;

import com.letfar.windfire.map.core.RegionObject;
import com.letfar.windfire.map.helpers.BezierCurveHelper;

/**
 * Created by Alex on 14.09.2016.
 */
public class LakeRegionObject extends RegionObject {

    @Override
    public boolean isInitialPosition(double x, double y) {
        boolean xBounds = x >= 500 && x <= 2000;
        boolean yBounds = y <= 2000;

        double tOfCurrentX = BezierCurveHelper.besizerFor3Points_getT(x, 500, 1000, 2000);
        double yOfCurveByT = BezierCurveHelper.bezierFor3Points_getCoordByT(2000, 1500, 1000, tOfCurrentX);

        boolean yIsUpperThenCurve = y >= yOfCurveByT;

        return xBounds && yBounds && yIsUpperThenCurve;
    }

    @Override
    public String toString() {
        return " L ";
    }
}
