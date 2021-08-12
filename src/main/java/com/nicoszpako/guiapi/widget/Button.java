package com.nicoszpako.guiapi.widget;

import com.nicoszpako.guiapi.EnumMouseInteraction;
import com.nicoszpako.guiapi.layout.EnumAlignment;
import com.nicoszpako.guiapi.listeners.IMouseListener;
import com.nicoszpako.guiapi.widget.container.Container;

public class Button extends Widget implements IMouseListener {

    private Widget innerWidget = null;

    public Button(String text){
        setInnerWidget(new Label(text));
    }

    @Override
    protected void drawContent(int mouseX, int mouseY, float partialTicks) {
        getInnerWidget().drawContent(mouseX,mouseY,partialTicks);
    }

    @Override
    public void init() {
        updateDimensions();
    }

    public void click(){

    }

    @Override
    public void mouseEvent(EnumMouseInteraction mouseInteraction) {
        if(mouseInteraction == EnumMouseInteraction.LEFT_CLICK){
            click();
        }
    }

    public void setText(String text){
        setInnerWidget(new Label(text));
    }

    public Widget getInnerWidget() {
        return innerWidget;
    }

    public void setInnerWidget(Widget innerWidget) {
        this.innerWidget = innerWidget;
        updateDimensions();
        notifyLayoutChanged();
    }

    private void updateDimensions() {
        getInnerWidget().getGeometry().moveAt(getGeometry().getOrigin().x + getPadding().getLeft(),getGeometry().getOrigin().y + getPadding().getTop());
        getGeometry().setWidth(getInnerWidget().getGeometry().getWidth() + getPadding().getLeft() + getPadding().getRight());
        getGeometry().setHeight(getInnerWidget().getGeometry().getHeight() + getPadding().getTop() + getPadding().getBottom());
    }
}
