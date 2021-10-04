package com.nicoszpako.guiapi.widget;

import com.nicoszpako.guiapi.EnumMouseInteraction;
import com.nicoszpako.guiapi.listeners.IMouseListener;
import com.nicoszpako.guiapi.util.Render;
import com.nicoszpako.guiapi.widget.container.Container;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class Button extends Container implements IMouseListener {

    private String text = "";

    private IMouseListener action;

    public Button(String text){
        setText(text);
        adaptDimensionsToText();
    }

    public Button(String text, float width, float height){
        setFixed(true);
        setText(text);
        getGeometry().setWidth(width);
        getGeometry().setHeight(height);
    }

    public Button(){
        setFixed(false);
    }

    public Button(Widget widget){
        setFixed(true);
        add(widget);
    }

    @Override
    protected void drawContent(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(getGeometry().getLeft(), getGeometry().getTop(),0);
        if(!getText().isEmpty())
            Minecraft.getMinecraft().fontRenderer.drawString(getText(),1,1, isMouseOnWidget(mouseX,mouseY) ? getStyle().getColor() : getStyle().getHoverColor());
        GlStateManager.popMatrix();
        super.drawContent(mouseX, mouseY, partialTicks);
    }

    public void click(){
    }

    public void adaptDimensionsToText(){
        getGeometry().setWidth(Minecraft.getMinecraft().fontRenderer.getStringWidth(text)+1);
        getGeometry().setHeight(Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT);
        buildLayout();
    }

    @Override
    public void mouseEvent(EnumMouseInteraction mouseInteraction) {
        if(mouseInteraction == EnumMouseInteraction.LEFT_CLICK){
            click();
        }
        if(action != null)
            action.mouseEvent(mouseInteraction);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextAndAdaptDimensions(String text) {
        setText(text);
        adaptDimensionsToText();
    }

    public IMouseListener getAction() {
        return action;
    }

    public void setAction(IMouseListener action) {
        this.action = action;
    }

}
