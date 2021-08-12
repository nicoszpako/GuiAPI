package com.nicoszpako.guiapi;

import com.nicoszpako.guiapi.listeners.IMouseListener;
import com.nicoszpako.guiapi.listeners.IWheelListener;
import com.nicoszpako.guiapi.widget.Widget;
import org.lwjgl.input.Mouse;

public class MouseState {

    private boolean enabled = true;

    private boolean leftButtonDown = false;
    private boolean rightButtonDown = false;
    private boolean middleButtonDown = false;
    private int mouseX,mouseY;

    public void update(int mouseX, int mouseY){
        if(isEnabled()){
            setMouseX(mouseX);
            setMouseY(mouseY);
            leftButtonDown = Mouse.isButtonDown(0);
            rightButtonDown = Mouse.isButtonDown(1);
            middleButtonDown = Mouse.isButtonDown(2);
        }
    }

    public boolean isLeftButtonDown() {
        return leftButtonDown;
    }

    public void setLeftButtonDown(boolean leftButtonDown) {
        this.leftButtonDown = leftButtonDown;
    }

    public boolean isRightButtonDown() {
        return rightButtonDown;
    }

    public void setRightButtonDown(boolean rightButtonDown) {
        this.rightButtonDown = rightButtonDown;
    }

    public boolean isMiddleButtonDown() {
        return middleButtonDown;
    }

    public void setMiddleButtonDown(boolean middleButtonDown) {
        this.middleButtonDown = middleButtonDown;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public boolean isLeftClickPressed(){
        return Mouse.isButtonDown(0) && !isLeftButtonDown();
    }

    public boolean isRightClickPressed(){
        return Mouse.isButtonDown(1) && !isRightButtonDown();
    }

    public boolean isMiddleClickPressed(){
        return Mouse.isButtonDown(2) && !isMiddleButtonDown();
    }

    public void handleMouseListener(Widget widget, int mouseX, int mouseY){
        if(isEnabled()){
            if(widget.getGeometry().isPointInsideExpandedRectangle(widget.getBorder(), mouseX, mouseY)
                && isLeftClickPressed())
                ((IMouseListener)widget).mouseEvent(EnumMouseInteraction.LEFT_CLICK);
            if(widget.getGeometry().isPointInsideExpandedRectangle(widget.getBorder(), mouseX, mouseY)
                && isRightClickPressed())
                ((IMouseListener)widget).mouseEvent(EnumMouseInteraction.RIGHT_CLICK);
            if(widget.getGeometry().isPointInsideExpandedRectangle(widget.getBorder(), mouseX, mouseY)
                && isMiddleClickPressed())
                ((IMouseListener)widget).mouseEvent(EnumMouseInteraction.MIDDLE_CLICK);
            if(widget.getGeometry().isPointInsideExpandedRectangle(widget.getBorder(), mouseX, mouseY)
                    && !widget.getGeometry().isPointInsideExpandedRectangle(widget.getBorder(), this.mouseX, this.mouseY))
                ((IMouseListener)widget).mouseEvent(EnumMouseInteraction.MOUSE_ENTER);
            if(!widget.getGeometry().isPointInside(mouseX,mouseY)
                    && widget.getGeometry().isPointInside(this.mouseX,this.mouseY))
                ((IMouseListener)widget).mouseEvent(EnumMouseInteraction.MOUSE_LEAVE);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
