package com.nicoszpako.guiapi.widget;

import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.style.Style;
import com.nicoszpako.guiapi.util.Render;
import com.nicoszpako.guiapi.widget.container.Container;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Vector2f;

public abstract class Widget extends Gui {

    /**
     * The parent container of this widget. Null if it's a top level widget, like a frame's one.
     */
    private Container parentContainer = null;

    /**
     * The geometrical dimensions of this widget. Allows defining what space this widget is going to cover on the screen, and how it will behave.
     */
    private Rectangle geometry = new Rectangle();
    private Rectangle margin = new Rectangle();
    private Rectangle border = new Rectangle();

    /**
     * Display purposes only. Use geometry to change dimension within a container.
     */
    private Vector3f translation = new Vector3f(0,0,0);
    private Vector3f scale = new Vector3f(1,1,1);
    private Vector3f rotation = new Vector3f(0,0,0);

    /**
     * The style of this widget. Mostly defines colors
     */
    private Style style = new Style();

    /**
     * Indicates whether dimensions of this widget should adapt to it's content or container.
     * If true, this widget's dimensions won't change except if user wants to.
     */
    private boolean fixed = false;

    public void draw(int mouseX, int mouseY, float partialTicks){
        GlStateManager.pushMatrix();
        GlStateManager.translate(translation.x,translation.y,translation.z);
        GlStateManager.rotate(rotation.x,1,0,0);
        GlStateManager.rotate(rotation.y,0,1,0);
        GlStateManager.rotate(rotation.z,0,0,1);
        GlStateManager.scale(scale.x,scale.y,scale.z);
        drawBorder(mouseX,mouseY,partialTicks);
        drawBackground(mouseX,mouseY,partialTicks);
        drawContent(mouseX,mouseY,partialTicks);
        GlStateManager.popMatrix();
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
        GL11.glColor4f(1,1,1,0.4f);
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
        GL11.glColor4f(1,1,1,1f);

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

    public boolean isMouseOnWidget(int mouseX,int mouseY){
        return getGeometry().isPointInsideExpandedRectangle(getBorder(),mouseX,mouseY);
    }

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

    public void setBorder(float left, float top, float right, float bottom){
        setBorder(new Rectangle(left,top,right,bottom));
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

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void scale(float scale){
        setScale(new Vector3f(scale,scale,1));
    }
}
