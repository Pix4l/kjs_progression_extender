package net.kj.kjs_progression_extender.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Gui.class)
public class ModHotbarDisplayMixin {
    @ModifyVariable(
            method = "renderHearts",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true)
    private float fixMaxHealth (float maxHealth) {
        return Math.min(Minecraft.getInstance().player.getMaxHealth(), 40);
    }

    @ModifyVariable(
            method = "renderHearts",
            at = @At("HEAD"),
            ordinal = 4,
            argsOnly = true)
    private int fixNumberOfHearts (int value) {
        Player player = Minecraft.getInstance().player;
        float percent = player.getHealth() / player.getMaxHealth();
        return (int) (Math.min(Minecraft.getInstance().player.getMaxHealth(), 40) * percent);
    }

    @ModifyVariable(
            method = "renderHearts",
            at = @At("HEAD"),
            ordinal = 2,
            argsOnly = true
    )
    private int fixRowSpacing (int value) {
        return 10;
    }
}
