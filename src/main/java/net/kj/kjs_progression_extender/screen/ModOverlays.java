package net.kj.kjs_progression_extender.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.attribute.ModAttributes;
import net.kj.kjs_progression_extender.mana.ManaCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = KJsProgressionExtender.MOD_ID)
public class ModOverlays {
    private static final ResourceLocation MANA_BAR_TEXTURE =
            new ResourceLocation(KJsProgressionExtender.MOD_ID, "textures/gui/mana_icons.png");

    public static final IGuiOverlay MANA_BAR = (forgeGui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.player == null)) {
            var info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
            if (info != null) {
                GameType mode = info.getGameMode();
                if (mode == GameType.SURVIVAL || mode == GameType.ADVENTURE) {
                    int startX = (screenWidth / 2) + 2;

                    mc.player.getCapability(ManaCapability.MANA).ifPresent(iMana -> {
                        double current = iMana.getMana();
                        double max = mc.player.getAttribute(ModAttributes.MAX_MANA.get()).getValue();
                        double manaPerDrop = max / 10;
                        for (int i = 0; i < 10; i++) {
                            if ((i + 0.7) * manaPerDrop < current) {
                                guiGraphics.blit(MANA_BAR_TEXTURE, startX + 8 * (i + 1), screenHeight - 49, 0, 0, 9, 9);
                            } else if (i * manaPerDrop < current) {
                                guiGraphics.blit(MANA_BAR_TEXTURE, startX + 8 * (i + 1), screenHeight - 49, 9, 0, 9, 9);
                            } else {
                                guiGraphics.blit(MANA_BAR_TEXTURE, startX + 8 * (i + 1), screenHeight - 49, 18, 0, 9, 9);
                            }
                        }
                    });
                }
            }
        }
    };

    public static final IGuiOverlay HEALTH_TEXT = (forgeGui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.player == null)) {
            var info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
            if (info != null) {
                GameType mode = info.getGameMode();
                if (mode == GameType.SURVIVAL || mode == GameType.ADVENTURE) {
                    Font font = mc.font;

                    String text = String.format("%.0f", mc.player.getHealth()) + "/" + String.format("%.0f", mc.player.getMaxHealth());
                    int color = 0xF01111;

                    int yOffsetAboveHotbar = 29;
                    if (mc.player.getMaxHealth() <= 20) {
                        yOffsetAboveHotbar = 18;
                    }
                    int textY = screenHeight - 20 - yOffsetAboveHotbar;

                    int textWidth = font.width(text);
                    int textX = (screenWidth - textWidth) / 2;

                    RenderSystem.disableDepthTest();
                    guiGraphics.drawString(font, text, textX - 110, textY, color, false);
                    RenderSystem.enableDepthTest();
                }
            }
        }
    };

    public static final IGuiOverlay MANA_TEXT = (forgeGui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.player == null)) {
            var info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
            if (info != null) {
                GameType mode = info.getGameMode();
                if (mode == GameType.SURVIVAL || mode == GameType.ADVENTURE) {
                    Font font = mc.font;

                    mc.player.getCapability(ManaCapability.MANA).ifPresent(iMana -> {
                        double current = iMana.getMana();
                        double max = mc.player.getAttribute(ModAttributes.MAX_MANA.get()).getValue();

                        String text = String.format("%.0f", current) + "/" + String.format("%.0f", max);
                        int color = 0x3300FF;

                        int yOffsetAboveHotbar = 29;
                        int textY = screenHeight - 20 - yOffsetAboveHotbar;

                        int textWidth = font.width(text);
                        int textX = (screenWidth - textWidth) / 2;

                        RenderSystem.disableDepthTest();
                        guiGraphics.drawString(font, text, textX + 110, textY, color, false);
                        RenderSystem.enableDepthTest();
                    });
                }
            }
        }
    };
}
