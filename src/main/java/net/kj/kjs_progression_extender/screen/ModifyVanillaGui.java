package net.kj.kjs_progression_extender.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ModifyVanillaGui {
    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id().equals(VanillaGuiOverlay.ARMOR_LEVEL.id()) && Minecraft.getInstance().player.getMaxHealth() > 20) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            guiGraphics.pose().pushPose();

            int heartRows = ((int) Math.floor(Minecraft.getInstance().player.getMaxHealth() / 20));
            int pixelsPerRow = Math.max(10 - heartRows, 3);

            guiGraphics.pose().translate(0, (heartRows - 1) * pixelsPerRow, 0);
        }

        if (event.getOverlay().id().equals(VanillaGuiOverlay.AIR_LEVEL.id())) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            guiGraphics.pose().pushPose();

            guiGraphics.pose().translate(0, -10, 0);
        }
    }

    @SubscribeEvent
    public static void onRenderPost(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay().id().equals(VanillaGuiOverlay.ARMOR_LEVEL.id()) && Minecraft.getInstance().player.getMaxHealth() > 20) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            guiGraphics.pose().popPose();
        }

        if (event.getOverlay().id().equals(VanillaGuiOverlay.AIR_LEVEL.id())) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            guiGraphics.pose().popPose();
        }
    }
}
