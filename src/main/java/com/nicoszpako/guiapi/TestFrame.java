package com.nicoszpako.guiapi;

import com.nicoszpako.guiapi.layout.EnumAlignment;
import com.nicoszpako.guiapi.widget.Button;
import com.nicoszpako.guiapi.widget.Label;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class TestFrame extends Frame{

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(new TestFrame());
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void addWidgets(ScaledResolution resolution) {
        getFrameContainer().getLayout().setAlignment(EnumAlignment.CENTER);
        Button button = new Button("0"){
            int i = 0;
            @Override
            public void click() {
                super.click();
                i++;
                setText(i+"");
            }
        };
        button.getStyle().setBackgroundColor(0xFF558833);
        button.getStyle().setHoverBackgroundColor(0xFF447722);
        button.setPadding(1,1,1,1);
        add(button);
        Label label = new Label("TAest");
        label.getStyle().setBackgroundColor(0xFF880000);
        label.getStyle().setHoverBackgroundColor(0xFF770000);
        add(label);
    }
}