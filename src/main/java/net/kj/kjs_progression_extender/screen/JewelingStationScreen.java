package net.kj.kjs_progression_extender.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.network.ModNetworking;
import net.kj.kjs_progression_extender.network.SyncGemstoneBuffPacket;
import net.kj.kjs_progression_extender.util.GemstoneBuffs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JewelingStationScreen extends AbstractContainerScreen<JewelingStationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(KJsProgressionExtender.MOD_ID, "textures/gui/jeweling_station_gui.png");

    public JewelingStationScreen(JewelingStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderBuffSelection(pGuiGraphics, 1, x, y);
        renderBuffSelection(pGuiGraphics, 2, x, y);
        renderBuffTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    private void renderBuffTooltip(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (mouseOverBuffButton(i, j, pMouseX - leftPos, pMouseY - topPos)) {
                    List<Component> tooltip = new ArrayList<>();
                    Optional<TooltipComponent> optional = Optional.empty();
                    int type = menu.getTypeInSlot(i + 1);
                    tooltip.add(Component.literal(tooltipTitle(type, j)));
                    tooltip.add(Component.literal(tooltipBody(type, j, menu.getLevelInSlot(i + 1))));
                    guiGraphics.renderTooltip(this.font, tooltip, optional, pMouseX, pMouseY);
                }
            }
        }
    }

    private String tooltipTitle(int type, int row) {
        String name = "§l";
        switch (type) {
            case 1 -> name = "§aJade Buff";
            case 2 -> name = "§cRuby Buff";
            case 3 -> name = "§9Sapphire Buff";
            case 4 -> name = "§eTopaz Buff";
            case 5 -> {
                switch (row) {
                    case 0 -> name += "§aJade Singularity";
                    case 1 -> name += "§cRuby Singularity";
                    case 2 -> name += "§9Sapphire Singularity";
                    case 3 -> name += "§eTopaz Singularity";
                }
            }
        }
        return name;
    }

    private String tooltipBody(int type, int row, int level) {
        String start = "§7+";
        String buffNum = "";
        String buffName = "";
        switch (type) {
            case 1 -> {
                switch (row) {
                    case 0 -> {
                        buffNum = String.valueOf(GemstoneBuffs.healthPerLevel * level);
                        buffName = " Health";
                    }
                    case 1 -> {
                        buffNum = String.valueOf(GemstoneBuffs.regenPerLevel * level);
                        buffName = "/5s Health Regen";
                    }
                    case 2 -> {
                        buffNum = String.valueOf(GemstoneBuffs.defencePerLevel * level);
                        buffName = " Defence";
                    }
                }
            }
            case 2 -> {
                switch (row) {
                    case 0 -> {
                        buffNum = String.valueOf(GemstoneBuffs.strengthPerLevel * level);
                        buffName = " Strength";
                    }
                    case 1 -> {
                        buffNum = String.valueOf(GemstoneBuffs.critDamagePerLevel * level);
                        buffName = " Crit Damage";
                    }
                    case 2 -> {
                        buffNum = String.valueOf(GemstoneBuffs.lifeStealPerLevel * level);
                        buffName = " Lifesteal";
                    }
                }
            }
            case 3 -> {
                switch (row) {
                    case 0 -> {
                        buffNum = String.valueOf(GemstoneBuffs.manaPerLevel * level);
                        buffName = " Mana";
                    }
                    case 1 -> {
                        buffNum = String.valueOf(GemstoneBuffs.manaRegenPerLevel * level);
                        buffName = "/5s Mana Regen";
                    }
                    case 2 -> {
                        buffNum = String.valueOf(GemstoneBuffs.elementalDamagePerLevel * level);
                        buffName = " Elemental Damage";
                    }
                }
            }
            case 4 -> {
                switch (row) {
                    case 0 -> {
                        buffNum = String.valueOf(GemstoneBuffs.critChancePerLevel * level);
                        buffName = " Crit Chance";
                    }
                    case 1 -> {
                        buffNum = String.valueOf(GemstoneBuffs.attackSpeedPerLevel * level);
                        buffName = " Attack Speed";
                    }
                    case 2 -> {
                        buffNum = String.valueOf(GemstoneBuffs.speedPerLevel * level);
                        buffName = " Walk Speed";
                    }
                }
            }
            case 5 -> {
                String buff1 = "";
                String buff2 = "";
                String buff3 = "";
                String name1 = "";
                String name2 = "";
                String name3 = "";
                switch (row) {
                    case 0 -> {
                        buff1 = String.valueOf(GemstoneBuffs.healthPerLevel * GemstoneBuffs.singularityMult);
                        buff2 = String.valueOf(GemstoneBuffs.regenPerLevel * GemstoneBuffs.singularityMult);
                        buff3 = String.valueOf(GemstoneBuffs.defencePerLevel * GemstoneBuffs.singularityMult);

                        name1 = "Health";
                        name2 = "Health Regen";
                        name3 = "Defence";
                    }
                    case 1 -> {
                        buff1 = String.valueOf(GemstoneBuffs.strengthPerLevel * GemstoneBuffs.singularityMult);
                        buff2 = String.valueOf(GemstoneBuffs.critDamagePerLevel * GemstoneBuffs.singularityMult);
                        buff3 = String.valueOf(GemstoneBuffs.lifeStealPerLevel * GemstoneBuffs.singularityMult);

                        name1 = "Strength";
                        name2 = "Crit Damage";
                        name3 = "Lifesteal";
                    }
                    case 2 -> {
                        buff1 = String.valueOf(GemstoneBuffs.manaPerLevel * GemstoneBuffs.singularityMult);
                        buff2 = String.valueOf(GemstoneBuffs.manaRegenPerLevel * GemstoneBuffs.singularityMult);
                        buff3 = String.valueOf(GemstoneBuffs.elementalDamagePerLevel * GemstoneBuffs.singularityMult);

                        name1 = "Mana";
                        name2 = "Mana Regen";
                        name3 = "Elemental Damage";
                    }
                    case 3 -> {
                        buff1 = String.valueOf(GemstoneBuffs.critChancePerLevel * GemstoneBuffs.singularityMult);
                        buff2 = String.valueOf(GemstoneBuffs.attackSpeedPerLevel * GemstoneBuffs.singularityMult);
                        buff3 = String.valueOf(GemstoneBuffs.speedPerLevel * GemstoneBuffs.singularityMult);

                        name1 = "Crit Chance";
                        name2 = "Attack Speed";
                        name3 = "Walk Speed";
                    }
                }
                return "+" + buff1 + "/" + buff2 + "/" + buff3 + " " + name1 + "/" + name2 + "/" + name3;
            }
        }

        return start + buffNum + buffName;
    }

    private void renderBuffSelection(GuiGraphics guiGraphics, int slot, int x, int y) {
        if (menu.isGemstoneInSlot(slot)) {
            int type = menu.getTypeInSlot(slot);
            int selectedIndex = menu.getSelectedBuff(slot);
            if (type == 5) {
                for (int i = 0; i < 4; i++) {
                    if (i + 1 == selectedIndex) {
                        guiGraphics.blit(TEXTURE, x + 129 + (slot - 1) * 18, y + 8 + i * 18, 179 + i * 14, 62 + 72, 14, 14);
                    } else {
                        guiGraphics.blit(TEXTURE, x + 129 + (slot - 1) * 18, y + 8 + i * 18, 179 + i * 14, 62, 14, 14);
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    if (i + 1 == selectedIndex) {
                        guiGraphics.blit(TEXTURE, x + 129 + (slot - 1) * 18, y + 8 + i * 18, 179 + 14 * (type - 1), 8 + 72 + 18 * i, 14, 14);
                    } else {
                        guiGraphics.blit(TEXTURE, x + 129 + (slot - 1) * 18, y + 8 + i * 18, 179 + 14 * (type - 1), 8 + 18 * i, 14, 14);
                    }

                }
            }
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int slot = 0;
        int selected = 0;


        double x = mouseX - leftPos;
        double y = mouseY - topPos;

        if (129 <= x && x <= 142) {
            slot = 1;
        } else if (147 <= x && x <= 160) {
            slot = 2;
        }

        if (8 <= y && y <= 21) {
            selected = 1;
        } else if (26 <= y && y <= 39) {
            selected = 2;
        } else if (44 <= y && y <= 57) {
            selected = 3;
        } else if (62 <= y && y <= 77) {
            selected = 4;
        }

        if (slot > 0 && selected > 0) {
            menu.setSelectedBuff(slot, selected);
        }

        ModNetworking.INSTANCE.sendToServer(
                new SyncGemstoneBuffPacket(menu.blockEntity.getBlockPos().getX(), menu.blockEntity.getBlockPos().getY(), menu.blockEntity.getBlockPos().getZ(), slot, selected)
        );

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseOverBuffButton(double column, double row, double x, double y) {
        double dx = 135 + column * 18 - x;
        double dy = 15 + row * 18 - y;
        return dx * dx + dy * dy <= 36;
    }
}
