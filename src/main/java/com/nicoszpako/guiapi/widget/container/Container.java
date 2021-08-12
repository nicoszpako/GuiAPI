package com.nicoszpako.guiapi.widget.container;

import com.nicoszpako.guiapi.EnumMouseInteraction;
import com.nicoszpako.guiapi.MouseState;
import com.nicoszpako.guiapi.layout.EnumAlignment;
import com.nicoszpako.guiapi.layout.FlowLayout;
import com.nicoszpako.guiapi.layout.Layout;
import com.nicoszpako.guiapi.listeners.IKeyListener;
import com.nicoszpako.guiapi.listeners.IMouseListener;
import com.nicoszpako.guiapi.listeners.IWheelListener;
import com.nicoszpako.guiapi.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class Container extends Widget implements IWheelListener, IMouseListener, IKeyListener {

    private final List<Widget> widgets = new ArrayList<>();

    private final MouseState mouseState = new MouseState();

    private Layout layout = new FlowLayout();

    @Override
    protected void drawContent(int mouseX, int mouseY, float partialTicks) {
        for(Widget widget : getWidgets()){
            if(widget instanceof IMouseListener){
                getMouseState().handleMouseListener(widget,mouseX,mouseY);
            }
            widget.draw(mouseX,mouseY,partialTicks);
        }
        getMouseState().update(mouseX,mouseY);
    }

    @Override
    public void init() {
        getWidgets().clear();
    }

    public void buildLayout(){
        Layout layout = getLayout();
        if(layout != null){
            layout.organise(getWidgets(), getGeometry(), getPadding());
        }
        for(Widget widget : getWidgets()){
            widget.init();
        }
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void keyEvent(char typedChar, int keyCode) {
        for(Widget widget : getWidgets()){
            if(widget instanceof IKeyListener){
                ((IKeyListener)(widget)).keyEvent(typedChar,keyCode);
            }
        }
    }

    @Override
    public void mouseEvent(EnumMouseInteraction mouseInteraction) {

    }

    @Override
    public void wheelEvent(int dWheel) {
        for(Widget widget : getWidgets()){
            if(widget instanceof IWheelListener){
                ((IWheelListener)(widget)).wheelEvent(dWheel);
            }
        }
    }

    public MouseState getMouseState() {
        return mouseState;
    }

    public void add(Widget widget){
        getWidgets().add(widget);
        widget.setParentContainer(this);
        buildLayout();
    }

    public void remove(Widget widget){
        getWidgets().remove(widget);
        buildLayout();
    }

    public List<Widget> getWidgets() {
        return widgets;
    }
}
