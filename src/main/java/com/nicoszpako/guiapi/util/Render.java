package com.nicoszpako.guiapi.util;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class Render {

    public static final int WHITE = 0xFFFFFFFF;

    public static void color(int color){
        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        GlStateManager.color(f, f1, f2, f3);
    }

    public static void rect(double x, double y, double w, double h) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(x + w, y, 0).endVertex();
        vertexbuffer.pos(x, y, 0).endVertex();
        vertexbuffer.pos(x, y + h, 0).endVertex();
        vertexbuffer.pos(x + w, y + h, 0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

}
