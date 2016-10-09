package com.letfar.windfire.map.helpers;

import android.graphics.Color;
import android.graphics.Paint;
import com.letfar.windfire.map.concrete_objects.*;

/**
 * Created by Alex on 23.09.2016.
 */
public class DrawObjectHelper {
    private static int color = Color.WHITE;

    public static Paint colorPaint = new Paint() {{
        setColor(color);
    }};

    public static Paint getColorPaintFor(Class<?> regionObject) {
        if (regionObject.isAssignableFrom(AiroportRegionObject.class))
            color = Color.RED;
        if (regionObject.isAssignableFrom(FireRegionObject.class))
            color = Color.YELLOW;
        if (regionObject.isAssignableFrom(LakeRegionObject.class))
            color = Color.BLUE;
        if (regionObject.isAssignableFrom(MilitaryStorageRegionObject.class))
            color = Color.BLACK;
        if (regionObject.isAssignableFrom(SanatoryRegionObject.class))
            color = Color.MAGENTA;
        if (regionObject.isAssignableFrom(UrbanRegionObject.class))
            color = Color.GRAY;
        if (regionObject.isAssignableFrom(WoodRegionObject.class))
            color = Color.GREEN;

        colorPaint.setColor(color);
        return colorPaint;
    }
}
