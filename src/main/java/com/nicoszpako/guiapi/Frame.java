package com.nicoszpako.guiapi;

import com.nicoszpako.guiapi.widget.container.Container;
import com.nicoszpako.guiapi.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public abstract class Frame extends GuiScreen {

    private final FrameProperties frameProperties = new FrameProperties();

    private final Container frameContainer = new Container();


    public Frame() {
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

    }

    @Override
    public void handleMouseInput() {
        int dWheel = Mouse.getEventDWheel();
        getFrameContainer().wheelEvent(dWheel);
    }

    @Override
    public void initGui() {
        super.initGui();
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        getFrameContainer().getGeometry().setWidth(resolution.getScaledWidth());
        getFrameContainer().getGeometry().setHeight(resolution.getScaledHeight());
        getFrameContainer().init();
        addWidgets(resolution);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        getFrameContainer().draw(mouseX,mouseY,partialTicks);
    }

    public abstract void addWidgets(ScaledResolution resolution);

    public void add(Widget widget){
        getFrameContainer().add(widget);
    }


    public Container getFrameContainer() {
        return frameContainer;
    }

    public FrameProperties getFrameProperties() {
        return frameProperties;
    }
}
