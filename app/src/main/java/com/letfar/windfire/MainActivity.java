package com.letfar.windfire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.LineChart;
import com.letfar.windfire.map.concrete_objects.*;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionObject;
import com.letfar.windfire.map.core.wind.RegionWind;
import com.letfar.windfire.map.core.wind.WindBlow;
import com.letfar.windfire.map.helpers.RegionMapHelper;

import java.util.ArrayDeque;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private final RegionMap regionMap = new RegionMap(30, 30, 4000, 4000);
    private ViewGroup layoutMain;
    private LineChart chart;
    private RegionMapPaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.layoutMain = (ViewGroup) findViewById(R.id.layoutMain);
        this.chart = (LineChart) findViewById(R.id.lineChart);
        this.paintView = (RegionMapPaintView) findViewById(R.id.regionMapPaint);

        this.paintView.setMap(regionMap);
        this.paintView.setBlows(new ArrayDeque<>(Arrays.asList(
                new WindBlow(RegionWind.Direction.NW, 5, 3000),
                new WindBlow(RegionWind.Direction.NE, 6, 3000),
                new WindBlow(RegionWind.Direction.SE, 5, 5000),
                new WindBlow(RegionWind.Direction.SW, 2, 3000))));
        this.paintView.setWind(new RegionWind(regionMap));

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

        this.chart.getXAxis().setEnabled(false);
        this.chart.getAxisLeft().setEnabled(false);
        this.paintView.setChart(this.chart);
    }
}
