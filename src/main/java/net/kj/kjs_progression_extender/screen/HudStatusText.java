package net.kj.kjs_progression_extender.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = KJsProgressionExtender.MOD_ID)
public class HudStatusText {
    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.player == null)) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            Font font = mc.font;

            String text = String.format("%.0f", mc.player.getHealth()) + "/" + String.format("%.0f", mc.player.getMaxHealth());
            int color = 0xF01111;

            int screenWidth = mc.getWindow().getGuiScaledWidth();
            int screenHeight = mc.getWindow().getGuiScaledHeight();

            int yOffsetAboveHotbar = 40;
            int textY = screenHeight - 20 - yOffsetAboveHotbar;

            int textWidth = font.width(text);
            int textX = (screenWidth - textWidth) / 2;

            RenderSystem.disableDepthTest();
            guiGraphics.drawString(font, text, textX - 50, textY, color, false);
            RenderSystem.enableDepthTest();
        }
    }
}
