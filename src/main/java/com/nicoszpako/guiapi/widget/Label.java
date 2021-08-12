package com.nicoszpako.guiapi.widget;

import com.nicoszpako.guiapi.util.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Label extends Widget{

    private final String text;

    public Label(String text) {
        this.text = text;
        getGeometry().setWidth(Minecraft.getMinecraft().fontRenderer.getStringWidth(text) - 1);
        getGeometry().setHeight(Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 2);
    }

    @Override
    protected void drawContent(int mouseX, int mouseY, float partialTicks) {
        float normalWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text) - 1;
        float normalHeight = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 2;

        float actualWidth = getGeometry().getWidth();
        float actualHeight = getGeometry().getHeight();

        float widthScaleFactor = actualWidth/normalWidth;
        float heightScaleFactor = actualHeight/normalHeight;

        GlStateManager.pushMatrix();
        GlStateManager.translate(getGeometry().getLeft(),getGeometry().getTop(),0);
        GlStateManager.scale(widthScaleFactor,heightScaleFactor,1f);
        Minecraft.getMinecraft().fontRenderer.drawString(text,0,0, getStyle().getColor());
        GlStateManager.translate(0,0,50);

        GlStateManager.popMatrix();
    }

    @Override
    public void init() {

    }
}
