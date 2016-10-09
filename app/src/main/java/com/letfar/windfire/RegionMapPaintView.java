package com.letfar.windfire;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.letfar.windfire.map.concrete_objects.ObjectType;
import com.letfar.windfire.map.core.RegionMap;
import com.letfar.windfire.map.core.RegionMapIterationAction;
import com.letfar.windfire.map.core.wind.RegionWind;
import com.letfar.windfire.map.core.wind.WindBlow;
import com.letfar.windfire.map.helpers.ArrayIndex;
import com.letfar.windfire.map.helpers.LoseMoneyObjectHelper;
import com.letfar.windfire.map.helpers.PriceHelper;
import com.letfar.windfire.map.helpers.XYCursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static com.letfar.windfire.map.helpers.DrawObjectHelper.getColorPaintFor;

/**
 * Created by Alex on 19.09.2016.
 */
public class RegionMapPaintView extends View {
    private RegionMap map;
    private int horizontalCellsCount;
    private int verticalCellsCount;

    private float cellWidth;
    private float cellHeight;

    private final XYCursor xyCellCursor = new XYCursor(0, 0);
    private final ArrayIndex lastIndex = new ArrayIndex(0, 0);

    private RegionWind wind;
    private Queue<WindBlow> blows;
    private boolean hasCellToBlow = false;
    private LineChart chart;
    private List<Entry> chartEntryList = new ArrayList<Entry>();
    private float entryNo = 1f;

    protected Paint textPaint = new Paint() {{
        setColor(Color.BLACK);
        setTextSize(30);
    }};

    public RegionMapPaintView(Context context, RegionMap map) {
        super(context);
        this.map = map;
    }

    public RegionMapPaintView(Context context) {super(context);}

    public RegionMapPaintView(Context context, @Nullable AttributeSet attrs) {super(context, attrs);}

    public RegionMapPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);}

    public void setMap(RegionMap map) {
        this.map = map;
    }

    public void setWind(RegionWind wind) { this.wind = wind;}

    public void setBlows(Queue<WindBlow> blows) {this.blows = blows;}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        horizontalCellsCount = map.getLastIndexRight() + 1;
        verticalCellsCount = map.getLastIndexTop() + 1;

        cellWidth = (float) Math.floor(getMeasuredWidth() / horizontalCellsCount);
        cellHeight = (float) Math.floor(getMeasuredHeight() / verticalCellsCount);

        xyCellCursor.setxStep(cellWidth);
        xyCellCursor.setyStep(cellHeight);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (map == null) return;


        if (hasCellToBlow == false && !blows.isEmpty()) {
            wind.registerBlow(blows.poll());
            hasCellToBlow = true;
        }

        if (hasCellToBlow)
            hasCellToBlow = wind.blowByCell();


        xyCellCursor.resetXY();

        map.iterateMap(new RegionMapIterationAction() {
            @Override
            public void doAction(double x, double y, ArrayIndex currentCellIndex) {
                // If new line
                if (lastIndex.j > currentCellIndex.j) {
                    xyCellCursor.incY();
                    xyCellCursor.resetX();
                }

                // Draw cell
                canvas.drawRect(
                        // left
                        xyCellCursor.getX(),
                        // top
                        xyCellCursor.getY(),
                        // right
                        xyCellCursor.getX() + cellWidth,
                        // bottom
                        xyCellCursor.getY() + cellHeight,
                        // color for current object
                        getColorPaintFor(map.getObject(currentCellIndex).getClass()));

                // Move cursor to next position
                xyCellCursor.incX();

                // Save index on this step
                lastIndex.setOf(currentCellIndex);
            }
        });

        for (ObjectType objectType : LoseMoneyObjectHelper.getActivatedToLoseMoneyObjects()) {
            long downtimePrice = PriceHelper.getPrice(objectType, map);
            PriceHelper.totalPrice += downtimePrice;
        }

        canvas.drawText(PriceHelper.totalPrice + "$", 10, 50, textPaint);

        if (chart != null) {
            this.chart.resetViewPortOffsets();
            this.chart.resetTracking();

            chartEntryList.add(new Entry(entryNo++, PriceHelper.totalPrice));
            LineData data = new LineData(new LineDataSet(chartEntryList, "Cost"));
            data.setDrawValues(false);

            if (chartEntryList.size() > 50) {

                chartEntryList = chartEntryList.subList(chartEntryList.size() - 25, chartEntryList.size());
                entryNo = 1f;
                for (Entry entry : chartEntryList) {
                    entry.setX(entryNo++);
                }
            }
                this.chart.setData(data);
            this.chart.postInvalidate();
        }

        postInvalidateDelayed(1000);
    }

    public void setChart(LineChart chart) {
        this.chart = chart;
    }
}
