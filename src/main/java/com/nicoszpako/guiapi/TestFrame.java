package com.nicoszpako.guiapi;

import com.nicoszpako.guiapi.layout.EnumAlignment;
import com.nicoszpako.guiapi.widget.Button;
import com.nicoszpako.guiapi.widget.Label;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Random;

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
        getFrameContainer().getLayout().setAlignment(EnumAlignment.LEFT_TOP);
        for (int i = 0; i < 700 ;i++) {
            int finalI = i;
            Button button = new Button(""+ finalI){
                int j = finalI;
                @Override
                public void click() {
                    super.click();
                    j++;
                    setTextAndAdaptDimensions(j+"");
                }
            };
            button.getStyle().setBackgroundColor((new Random()).nextInt());
            button.setBorder(3,3,3,3);
            button.getStyle().setBorderColor(0xff0055ff);
            button.getStyle().setHoverBackgroundColor(0xFF447722);
            button.setMargin(0,0,1,1);
            button.setPadding(2,2,2,2);
            add(button);
            
        }
    }
}
