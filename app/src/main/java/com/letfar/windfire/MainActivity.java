package com.letfar.windfire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.letfar.windfire.map.concrete_objects.*;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionObject;
import com.letfar.windfire.map.helpers.RegionMapHelper;

public class MainActivity extends AppCompatActivity {
    private final RegionMap regionMap = new RegionMap(30, 30, 4000, 4000);

    private ViewGroup layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.layoutMain = (ViewGroup) findViewById(R.id.layoutMain);

        layoutMain.addView(
                new RegionMapPaintView(getApplicationContext(), regionMap));

        final RegionObject[] initObjects = {
                new MilitaryStorageRegionObject(),
                new AiroportRegionObject(),
                new LakeRegionObject(),
                new SanatoryRegionObject(),
                new UrbanRegionObject(),
                new WoodRegionObject(),
                new FireRegionObject()
        };

        RegionMapHelper.addObjectsToMap(initObjects, regionMap);
    }
}
