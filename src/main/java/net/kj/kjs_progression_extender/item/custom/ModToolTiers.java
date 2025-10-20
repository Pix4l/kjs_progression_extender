package net.kj.kjs_progression_extender.item.custom;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.ModItems;
import net.kj.kjs_progression_extender.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier DRAKESTEEL = TierSortingRegistry.registerTier(
            new ForgeTier(5, 3000, 10f, 5f, 20,
                    ModTags.Blocks.NEEDS_DRAKESTEEL_TOOL, () -> Ingredient.of(ModItems.DRAKESTEEL_INGOT.get())),
            new ResourceLocation(KJsProgressionExtender.MOD_ID, "drakesteel"), List.of(Tiers.NETHERITE), List.of());
}
