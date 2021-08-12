package com.nicoszpako.guiapi.widget;

import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.style.Style;
import com.nicoszpako.guiapi.util.Render;
import com.nicoszpako.guiapi.widget.container.Container;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public abstract class Widget extends Gui {

    private Container parentContainer = null;

    private Rectangle geometry = new Rectangle();
    private Rectangle margin = new Rectangle();
    private Rectangle padding = new Rectangle();
    private Rectangle border = new Rectangle();

    private Style style = new Style();

    public void draw(int mouseX, int mouseY, float partialTicks){
        drawBorder(mouseX,mouseY,partialTicks);
        drawBackground(mouseX,mouseY,partialTicks);
        drawContent(mouseX,mouseY,partialTicks);
    }

    private void drawBackground(int mouseX, int mouseY, float partialTicks) {
        float x = getGeometry().getLeft();
        float y = getGeometry().getTop();
        GlStateManager.pushMatrix();
        Render.color(getGeometry().isPointInsideExpandedRectangle(getBorder(), mouseX, mouseY) ?
                getStyle().getHoverBackgroundColor() :
                getStyle().getBackgroundColor());
        Render.rect(x,y,getGeometry().getWidth(),getGeometry().getHeight());
        GlStateManager.popMatrix();
    }

    private void drawBorder(int mouseX, int mouseY, float partialTicks) {
        float x = getGeometry().getLeft();
        float y = getGeometry().getTop();
        GlStateManager.pushMatrix();
        Render.color(getGeometry().isPointInsideExpandedRectangle(getBorder(), mouseX, mouseY) ?
                getStyle().getHoverBorderColor() :
                getStyle().getBorderColor());
        // top border
        Render.rect(x, y - getBorder().getTop(), getGeometry().getWidth(), getBorder().getTop());
        // bottom border
        Render.rect(x, y + getGeometry().getHeight(), getGeometry().getWidth(), getBorder().getBottom());
        // left border
        Render.rect(x - getBorder().getLeft(), y, getBorder().getLeft(), getGeometry().getHeight());
        // right border
        Render.rect(x + getGeometry().getWidth(), y, getBorder().getRight(), getGeometry().getHeight());
        // top left corner border
        Render.rect(x - getBorder().getLeft(), y - getBorder().getTop(), getBorder().getLeft(), getBorder().getTop());
        // top right corner border
        Render.rect(x + getGeometry().getWidth(), y - getBorder().getTop(), getBorder().getRight(), getBorder().getTop());
        // bottom left corner border
        Render.rect(x - getBorder().getLeft(), y + getGeometry().getHeight(), getBorder().getLeft(), getBorder().getBottom());
        // bottom right corner border
        Render.rect(x + getGeometry().getWidth(), y + getGeometry().getHeight(), getBorder().getRight(), getBorder().getBottom());

        GlStateManager.popMatrix();
    }

    /**
     * Override this method to draw the content of the widget (everything excepted background and borders).
     * @param mouseX The mouse x position
     * @param mouseY The mouse y position
     * @param partialTicks Partial ticks amount since last frame was drawn
     */
    protected abstract void drawContent(int mouseX, int mouseY, float partialTicks);

    public abstract void init();

    public Rectangle getGeometry() {
        return geometry;
    }

    public void setGeometry(Rectangle geometry) {
        this.geometry = geometry;
    }

    public Rectangle getMargin() {
        return margin;
    }

    public void setMargin(Rectangle margin) {
        this.margin = margin;
    }

    public Rectangle getPadding() {
        return padding;
    }

    public void setPadding(Rectangle padding) {
        this.padding = padding;
    }

    public Rectangle getBorder() {
        return border;
    }

    public void setBorder(Rectangle border) {
        this.border = border;
    }

    public void setGeometry(float left, float top, float right, float bottom){
        setGeometry(new Rectangle(left,top,right,bottom));
    }

    public void setMargin(float left, float top, float right, float bottom){
        setMargin(new Rectangle(left,top,right,bottom));
    }

    public void setPadding(float left, float top, float right, float bottom){
        setPadding(new Rectangle(left,top,right,bottom));
    }

    public void setBorder(float left, float top, float right, float bottom){
        setPadding(new Rectangle(left,top,right,bottom));
    }

    public void notifyLayoutChanged(){
        if(getParentContainer()!=null)
            getParentContainer().buildLayout();
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Container getParentContainer() {
        return parentContainer;
    }

    public void setParentContainer(Container parentContainer) {
        this.parentContainer = parentContainer;
    }
}
