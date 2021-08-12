package com.nicoszpako.guiapi.layout;

import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.widget.Widget;

import java.util.List;

public abstract class Layout {

    private EnumAlignment alignment;

    public Layout() {
    }

    public abstract void organise(List<Widget> widgets, Rectangle boundaries, Rectangle padding);

    public EnumAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(EnumAlignment alignment) {
        this.alignment = alignment;
    }
}
