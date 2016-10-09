package com.letfar.windfire.map.helpers;

/**
 * Created by Alex on 23.09.2016.
 */

public class XYCursor {
    private float x = 0, y = 0;
    private float xStep = 10f, yStep = 10f;

    public XYCursor() {}
    public XYCursor(float xStep, float yStep) {
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setxStep(float xStep) { this.xStep = xStep; }
    public void setyStep(float yStep) { this.yStep = yStep; }

    public void incX() { x += xStep; }
    public void incY() { y += yStep; }

    public void resetX() { x = 0; }
    public void resetY() { y = 0; }
    public void resetXY() { resetX(); resetY(); }
}
