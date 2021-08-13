package com.nicoszpako.guiapi.layout;

import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends Layout {

    private float contentWidth = 0;
    private float contentHeight = 0;

    public FlowLayout() {
        setAlignment(EnumAlignment.LEFT_TOP);
    }



    @Override
    public void organise(List<Widget> widgets, Rectangle boundaries, Rectangle padding) {
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

            //We start a new line only if the previous one is not empty. It allows computing FlowLayout with empty boundaries.
            if (currentOffsetX + widget.getMargin().getRight() + widget.getGeometry().getWidth() > maxWidth - padding.getRight()
                    && !widgetRows.get(currentRowIndex).isEmpty()) {
                currentOffsetX = 0;
                currentOffsetY += maxWidgetHeight;
                maxWidgetHeight = 0;
                widgetRows.add(new ArrayList<>());
                currentRowIndex++;
            }

            float totalWidgetHeight = widget.getGeometry().getHeight() + widget.getMargin().getTop() + widget.getMargin().getBottom() + widget.getBorder().getTop() + widget.getBorder().getBottom();
            if (totalWidgetHeight > maxWidgetHeight)
                maxWidgetHeight = totalWidgetHeight;
            widget.getGeometry().move(currentOffsetX, currentOffsetY + widget.getMargin().getTop() + widget.getBorder().getTop());
            widgetRows.get(currentRowIndex).add(widget);
            currentOffsetX += widget.getGeometry().getWidth() + widget.getMargin().getRight();
        }

        //Now we fix widgets positions according to selected alignment
        float widgetsHeight = (float) (widgetRows.get(widgetRows.size() - 1).stream().mapToDouble(a -> a.getGeometry().getBottom() + a.getBorder().getBottom() + a.getMargin().getBottom()).max().getAsDouble()
                - widgetRows.get(0).get(0).getGeometry().getTop());
        setContentHeight(widgetsHeight);
        for (List<Widget> row : widgetRows) {
            float widgetsWidth = row.get(row.size() - 1).getGeometry().getRight() - row.get(0).getGeometry().getLeft();
            if(widgetsWidth > getContentWidth())
                setContentWidth(widgetsWidth);
            for (Widget widget : row) {

                float totalWidth = widget.getGeometry().getWidth() + widget.getMargin().getRight() + widget.getBorder().getRight();
                float totalHeight = widget.getGeometry().getHeight() + widget.getMargin().getBottom() + widget.getBorder().getBottom();

                float x = widget.getGeometry().getLeft();
                float y = widget.getGeometry().getTop();

                switch (getAlignment()) {
                    case LEFT_TOP:
                        widget.getGeometry().move(x, y);
                        break;
                    case RIGHT_TOP:
                        widget.getGeometry().move(boundaries.getRight() - (x + totalWidth), y);
                        break;
                    case RIGHT_BOTTOM:
                        widget.getGeometry().move(boundaries.getRight() - (x + totalWidth), boundaries.getBottom() - (y + totalHeight));
                        break;
                    case LEFT_BOTTOM:
                        widget.getGeometry().move(x, boundaries.getBottom() - (y + totalHeight));
                        break;
                    case LEFT:
                        widget.getGeometry().move(x, y + (boundaries.getHeight() - widgetsHeight) / 2);
                        break;
                    case RIGHT:
                        widget.getGeometry().move(boundaries.getRight() - (x + totalWidth), y + (boundaries.getHeight() - widgetsHeight) / 2);
                        break;
                    case TOP:
                        widget.getGeometry().move(x + (boundaries.getWidth() - widgetsWidth) / 2, y);
                        break;
                    case CENTER:
                        widget.getGeometry().move(x + (boundaries.getWidth() - widgetsWidth) / 2, y + (boundaries.getHeight() - widgetsHeight) / 2);
                        break;
                    case BOTTOM:
                        widget.getGeometry().move(x + (boundaries.getWidth() - widgetsWidth) / 2, boundaries.getBottom() - (y + totalHeight));
                        break;
                }
            }

        }
    }

    public float getContentWidth() {
        return contentWidth;
    }

    public void setContentWidth(float contentWidth) {
        this.contentWidth = contentWidth;
    }

    public float getContentHeight() {
        return contentHeight;
    }

    public void setContentHeight(float contentHeight) {
        this.contentHeight = contentHeight;
    }

    @Override
    public Rectangle getContentSize() {
        return new Rectangle(0, 0, getContentWidth(), getContentHeight());
    }


}
