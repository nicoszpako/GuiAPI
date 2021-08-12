package com.nicoszpako.guiapi;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.util.Util;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Mod(modid = "guiapi")
public class GuiAPI {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(GuiAPI.class);
        Display.setTitle("GuiAPI");
        Util.EnumOS util$enumos = Util.getOSType();

        if (util$enumos != Util.EnumOS.OSX) {
            try {
                InputStream l16 = new BufferedInputStream(this.getClass().getClassLoader()
                        .getResourceAsStream("logo16.png"));
                InputStream l32 = new BufferedInputStream(this.getClass().getClassLoader()
                        .getResourceAsStream("logo32.png"));

                Display.setIcon(new ByteBuffer[]{loadIcon(l16), loadIcon(l32)});
            } catch (IOException ioexception) {
            }

        }
    }

    private static ByteBuffer loadIcon(InputStream imageStream) throws IOException {
        BufferedImage bufferedimage = ImageIO.read(imageStream);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[]) null, 0,
                bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);

        for (int i : aint) {
            bytebuffer.putInt(i << 8 | i >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onTickClient(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        GuiScreen currentScreen = mc.currentScreen;
        if (currentScreen instanceof GuiMainMenu) {
            if (event.phase == TickEvent.Phase.START) {
                TestFrame customMenu = new TestFrame();
                mc.currentScreen = null;
                mc.displayGuiScreen(customMenu);
            }
        }
    }
}
