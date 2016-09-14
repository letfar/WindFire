package com.letfar.windfire.map.concrete_objects;

import com.letfar.windfire.map.core.RegionObject;
import com.letfar.windfire.map.helpers.BezierCurveHelper;

/**
 * Created by Alex on 14.09.2016.
 */
public class SanatoryRegionObject extends RegionObject {
    @Override
    public boolean isHere(double x, double y) {
        boolean b1 = x >= 500 && x <= 1000;
        boolean b2 = y >= 1000;

        double tOfCurrentXY = BezierCurveHelper.besizerFor3Points_getT(x, 500, 1000, 2000);
        double yCurve = BezierCurveHelper.bezierFor3Points(2000, 1500, 1000, tOfCurrentXY);
        boolean yIsUpperCurve = y <= yCurve;

        return b1 && b2 && yIsUpperCurve;
    }

    @Override
    public String toString() {
        return " S ";
    }
}
