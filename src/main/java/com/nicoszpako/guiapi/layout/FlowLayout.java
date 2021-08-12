package com.nicoszpako.guiapi.layout;

import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.widget.Widget;

import javax.vecmath.Vector2f;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends Layout {

    public FlowLayout() {
        setAlignment(EnumAlignment.TOP);
    }

    @Override
    public void organise(List<Widget> widgets, Rectangle boundaries, Rectangle padding) {
        Vector2f origin = boundaries.getOrigin();

        float maxWidth = boundaries.getWidth();

        float currentOffsetX = padding.getLeft();
        float currentOffsetY = padding.getTop();
        float maxWidgetHeight = 0;

        List<List<Widget>> widgetRows = new ArrayList<>();
        widgetRows.add(new ArrayList<>());
        int currentRowIndex = 0;


        //Computing widgets relative position to the container origin.
        for (Widget widget : widgets) {
            currentOffsetX += widget.getMargin().getLeft() + widget.getBorder().getLeft();
            if (currentOffsetX >= maxWidth - padding.getRight()) {
                currentOffsetX = 0;
                currentOffsetY += maxWidgetHeight;
                maxWidgetHeight = 0;
                widgetRows.add(new ArrayList<>());
                currentRowIndex++;
            }
            float totalWidgetHeight = widget.getGeometry().getHeight() + widget.getMargin().getTop() + widget.getMargin().getBottom() + widget.getBorder().getTop() + widget.getBorder().getBottom();
            if (totalWidgetHeight > maxWidgetHeight)
                maxWidgetHeight = totalWidgetHeight;
            widget.getGeometry().moveAt(currentOffsetX, currentOffsetY + widget.getMargin().getTop() + widget.getBorder().getTop());
            widgetRows.get(currentRowIndex).add(widget);
            currentOffsetX += widget.getGeometry().getWidth() + widget.getMargin().getRight();
        }

        //Now we fix widgets positions according to selected alignment
        float widgetsHeight = (float) (widgetRows.get(widgetRows.size() - 1).stream().mapToDouble(a -> a.getGeometry().getBottom() + a.getBorder().getBottom() + a.getMargin().getBottom()).max().getAsDouble()
                - widgetRows.get(0).get(0).getGeometry().getTop());
        for (List<Widget> row : widgetRows) {
            float widgetsWidth = row.get(row.size() - 1).getGeometry().getRight() - row.get(0).getGeometry().getLeft();
            for (Widget widget : row) {
                float totalWidth = widget.getGeometry().getWidth() + widget.getMargin().getRight() + widget.getBorder().getRight();
                float totalHeight = widget.getGeometry().getHeight() + widget.getMargin().getBottom() + widget.getBorder().getBottom();
                float x = widget.getGeometry().getLeft();
                float y = widget.getGeometry().getTop();
                switch (getAlignment()) {
                    case RIGHT_TOP:
                        widget.getGeometry().moveAt(boundaries.getRight() - (x + totalWidth), y);
                        break;
                    case RIGHT_BOTTOM:
                        widget.getGeometry().moveAt(boundaries.getRight() - (x + totalWidth), boundaries.getBottom() - (y + totalHeight));
                        break;
                    case LEFT_BOTTOM:
                        widget.getGeometry().moveAt(x, boundaries.getBottom() - (y + totalHeight));
                        break;
                    case LEFT:
                        widget.getGeometry().moveAt(x, y + (boundaries.getHeight() - widgetsHeight) / 2);
                        break;
                    case RIGHT:
                        widget.getGeometry().moveAt(boundaries.getRight() - (x + totalWidth), y + (boundaries.getHeight() - widgetsHeight) / 2);
                        break;
                    case TOP:
                        widget.getGeometry().moveAt(x + (boundaries.getWidth() - widgetsWidth) / 2, y);
                        break;
                    case CENTER:
                        widget.getGeometry().moveAt(x + (boundaries.getWidth() - widgetsWidth) / 2, y + (boundaries.getHeight() - widgetsHeight) / 2);
                        break;
                    case BOTTOM:
                        widget.getGeometry().moveAt(x + (boundaries.getWidth() - widgetsWidth) / 2, boundaries.getBottom() - (y + totalHeight));
                        break;
                }
            }

        }
    }


}
