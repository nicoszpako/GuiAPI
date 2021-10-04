package com.nicoszpako.guiapi.layout;

import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.widget.Widget;

import java.util.List;

public class GridLayout extends Layout {

    float[] columnsProportions;
    float[] rowsProportions;

    public GridLayout(Rectangle rectangle, int rows, int columns) {
        float columnsProportion = rectangle.getWidth() / (float) columns;
        float rowsProportion = rectangle.getHeight() / (float) rows;
        setColumnsProportions(new float[columns]);
        setRowsProportions(new float[rows]);
        for (int i = 0; i < columns; i++) {
            setColumnProportion(i, columnsProportion);
        }
        for (int i = 0; i < rows; i++) {
            setRowProportion(i, rowsProportion);
        }
    }

    /**
     * Builds a grid layout with the given proportions.
     * The sum of columnsProportions and rowsProportions values must be 1.
     * Ex : [1/2,1/2],[1/2,1/2] will give a 4-cell grid perfectly balanced.
     * @param columnsProportions An array of floats between 0 and 1 (exclusively) indicating the end of the kth column right border relative to the grid total size.
     * @param rowsProportions An array of floats between 0 and 1 (exclusively) indicating the end of the kth row bottom border relative to the grid total size.
     */
    public GridLayout(float[] columnsProportions, float[] rowsProportions) {
        this.columnsProportions = columnsProportions;
        this.rowsProportions = rowsProportions;
    }

    @Override
    public void organise(List<Widget> widgets, Rectangle boundaries, Rectangle padding) {
        int currentCellColumn = 0;
        int currentCellRow = 0;
        for (Widget widget : widgets) {
            float rowProportion = getRowsProportions()[currentCellRow];
            float columnProportion = getColumnsProportions()[currentCellColumn];
            widget.getGeometry().setLeft(boundaries.getWidth() * columnProportion + padding.getLeft());
            widget.getGeometry().setTop(boundaries.getHeight() * rowProportion + padding.getTop());
            if (currentCellColumn < getColumnCount()) {
                widget.getGeometry().setWidth(getColumnsProportions()[currentCellColumn + 1] * boundaries.getWidth() - padding.getRight());
            } else {
                widget.getGeometry().setWidth((1 - columnProportion) * boundaries.getWidth() - padding.getRight());
            }
            if (currentCellRow < getRowCount()) {
                widget.getGeometry().setHeight(getRowsProportions()[currentCellRow + 1] * boundaries.getHeight() - padding.getBottom());
            } else {
                widget.getGeometry().setHeight((1 - rowProportion) * boundaries.getHeight() - padding.getBottom());
            }
            currentCellColumn++;
            if(currentCellColumn >= getColumnCount()){
                currentCellColumn=0;
                currentCellRow++;
            }
            if(currentCellRow >= getRowCount())
                break;
        }
    }

    @Override
    public Rectangle getContentSize() {
        return null;
    }

    public void setRowProportion(int row, float proportion) {
        if (row >= getRowCount())
            throw new IndexOutOfBoundsException("Grid row n°" + row + " doesn't exist");
        getRowsProportions()[row] = proportion;
    }

    public void setColumnProportion(int column, float proportion) {
        if (column >= getColumnCount())
            throw new IndexOutOfBoundsException("Grid column n°" + column + " doesn't exist");
        getColumnsProportions()[column] = proportion;
    }

    public int getRowCount() {
        return getRowsProportions().length;
    }

    public int getColumnCount() {
        return getColumnsProportions().length;
    }

    public float[] getColumnsProportions() {
        return columnsProportions;
    }

    public void setColumnsProportions(float[] columnsProportions) {
        this.columnsProportions = columnsProportions;
    }

    public float[] getRowsProportions() {
        return rowsProportions;
    }

    public void setRowsProportions(float[] rowsProportions) {
        this.rowsProportions = rowsProportions;
    }
}
