package com.nicoszpako.guiapi.layout;

import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.widget.Widget;

import java.util.List;

public abstract class Layout {

    /**
     * Indicates what anchor should be used to lay widgets.
     */
    private EnumAlignment alignment;

    public Layout() {
    }


    /**
     * Lay widgets to their relative position in their parent.
     * @param widgets The widgets to lay.
     * @param boundaries The boundaries of the container.
     * @param padding The padding of the container.
     */
    public abstract void organise(List<Widget> widgets, Rectangle boundaries, Rectangle padding);

    public EnumAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(EnumAlignment alignment) {
        this.alignment = alignment;
    }

    public abstract Rectangle getContentSize();
}
