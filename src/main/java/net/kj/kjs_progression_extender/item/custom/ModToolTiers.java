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
            new ForgeTier(5, 3000, 10f, 0f, 20,
                    ModTags.Blocks.NEEDS_DRAKESTEEL_TOOL, () -> Ingredient.of(ModItems.AMALGAMITE.get())),
            new ResourceLocation(KJsProgressionExtender.MOD_ID, "drakesteel"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier ELEMENTAL = TierSortingRegistry.registerTier(
            new ForgeTier(6, 5000, 10f, 0f, 20,
                    ModTags.Blocks.NEEDS_ELEMENTAL_TOOL, () -> Ingredient.of(ModItems.ELEMENTITE.get())),
            new ResourceLocation(KJsProgressionExtender.MOD_ID, "elemental"), List.of(ModToolTiers.DRAKESTEEL), List.of());

    public static final Tier LIGHT = TierSortingRegistry.registerTier(
            new ForgeTier(7, 7500, 10f, 0f, 25,
                    ModTags.Blocks.NEEDS_LIGHT_TOOL, () -> Ingredient.of(ModItems.METEORITE_SHARD.get())),
            new ResourceLocation(KJsProgressionExtender.MOD_ID, "light"), List.of(ModToolTiers.ELEMENTAL), List.of());

    public static final Tier STARCAST = TierSortingRegistry.registerTier(
            new ForgeTier(8, 9000, 10f, 0f, 30,
                    ModTags.Blocks.NEEDS_STARCAST_TOOL, () -> Ingredient.of(ModItems.COSMITE_DUST.get())),
            new ResourceLocation(KJsProgressionExtender.MOD_ID, "starcast"), List.of(ModToolTiers.LIGHT), List.of());


}
