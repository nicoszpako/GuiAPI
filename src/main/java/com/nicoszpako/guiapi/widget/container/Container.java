package com.nicoszpako.guiapi.widget.container;

import com.nicoszpako.guiapi.EnumMouseInteraction;
import com.nicoszpako.guiapi.MouseState;
import com.nicoszpako.guiapi.Rectangle;
import com.nicoszpako.guiapi.layout.EnumAlignment;
import com.nicoszpako.guiapi.layout.FlowLayout;
import com.nicoszpako.guiapi.layout.Layout;
import com.nicoszpako.guiapi.listeners.IKeyListener;
import com.nicoszpako.guiapi.listeners.IMouseListener;
import com.nicoszpako.guiapi.listeners.IWheelListener;
import com.nicoszpako.guiapi.widget.Widget;
import net.minecraft.client.renderer.GlStateManager;

import javax.vecmath.Vector2f;
import java.util.ArrayList;
import java.util.List;

public class Container extends Widget implements IWheelListener, IMouseListener, IKeyListener {

    private final List<Widget> widgets = new ArrayList<>();

    private final MouseState mouseState = new MouseState();

    private Rectangle padding = new Rectangle();

    private Layout layout = new FlowLayout();

    public Container() {
    }

    public Container(float width, float height){
        setFixed(true);
        getGeometry().setWidth(width);
        getGeometry().setHeight(height);
    }

    @Override
    protected void drawContent(int mouseX, int mouseY, float partialTicks) {
        //Content is drawn relative to this container origin, so we also have to change the mouse coordinates system.
        GlStateManager.pushMatrix();
        GlStateManager.translate(getGeometry().getLeft(),getGeometry().getTop(),0);
        mouseX-=getGeometry().getLeft();
        mouseY-=getGeometry().getTop();
        for(Widget widget : getWidgets()){
            if(widget instanceof IMouseListener){
                getMouseState().handleMouseListener(widget,mouseX,mouseY);
            }
            widget.draw(mouseX,mouseY,partialTicks);
        }
        getMouseState().update(mouseX,mouseY);
        GlStateManager.popMatrix();
    }

    @Override
    public void init() {
        buildLayout();
    }

    public void buildLayout(){
        System.out.println("Building layout");
        Layout layout = getLayout();
        if(layout != null && !getWidgets().isEmpty()){
            layout.organise(getWidgets(), getGeometry(), getPadding());
            if(!isFixed()){
                getGeometry().setWidth(layout.getContentSize().getWidth() + getPadding().getLeft() + getPadding().getRight());
                getGeometry().setHeight(layout.getContentSize().getHeight() + getPadding().getLeft() + getPadding().getRight());
            }
        }
        if (getParentContainer() != null) {
            getParentContainer().buildLayout();
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
        widget.init();
        buildLayout();

    }

    public void remove(Widget widget){
        getWidgets().remove(widget);
        buildLayout();
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public Container with(Widget widget){
        add(widget);
        return this;
    }

    public Container lay(Layout layout){
        setLayout(layout);
        return this;
    }

    public Container align(EnumAlignment alignment){
        getLayout().setAlignment(alignment);
        return this;
    }

    public void clear(){
        getWidgets().clear();
    }

    public Rectangle getPadding() {
        return padding;
    }

    public void setPadding(Rectangle padding) {
        this.padding = padding;
    }

    public void setPadding(float left, float top, float right, float bottom){
        setPadding(new Rectangle(left,top,right,bottom));
    }
}
