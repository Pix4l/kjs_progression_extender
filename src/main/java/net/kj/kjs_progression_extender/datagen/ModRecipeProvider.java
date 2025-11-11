package net.kj.kjs_progression_extender.datagen;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.ModBlocks;
import net.kj.kjs_progression_extender.datagen.recipe.UpgradeRecipeBuilder;
import net.kj.kjs_progression_extender.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> JADE_SMELTABLES = List.of(ModBlocks.JADE_ORE.get(), ModBlocks.DEEPSLATE_JADE_ORE.get());
    private static final List<ItemLike> RUBY_SMELTABLES = List.of(ModBlocks.RUBY_ORE.get(), ModBlocks.DEEPSLATE_RUBY_ORE.get());
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
    private static final List<ItemLike> TOPAZ_SMELTABLES = List.of(ModBlocks.TOPAZ_ORE.get(), ModBlocks.DEEPSLATE_TOPAZ_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, JADE_SMELTABLES, RecipeCategory.MISC, ModItems.JADE.get(), 0.25f, 100, "jade");
        oreBlasting(pWriter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.25f, 100, "ruby");
        oreBlasting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 100, "sapphire");
        oreBlasting(pWriter, TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.TOPAZ.get(), 0.25f, 100, "topaz");

        oreSmelting(pWriter, JADE_SMELTABLES, RecipeCategory.MISC, ModItems.JADE.get(), 0.25f, 200, "sapphire");
        oreSmelting(pWriter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.25f, 200, "ruby");
        oreSmelting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 200, "sapphire");
        oreSmelting(pWriter, TOPAZ_SMELTABLES, RecipeCategory.MISC, ModItems.TOPAZ.get(), 0.25f, 200, "topaz");

        blockCraft(pWriter, ModItems.JADE.get(), ModBlocks.JADE_BLOCK.get(), ModItems.JADE.get());
        blockCraft(pWriter, ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.get(), ModItems.RUBY.get());
        blockCraft(pWriter, ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get(), ModItems.SAPPHIRE.get());
        blockCraft(pWriter, ModItems.TOPAZ.get(), ModBlocks.TOPAZ_BLOCK.get(), ModItems.TOPAZ.get());

        blockCraft(pWriter, ModItems.EMPOWERED_JADE.get(), ModBlocks.EMPOWERED_JADE_BLOCK.get(), ModItems.EMPOWERED_CORE.get());
        blockCraft(pWriter, ModItems.EMPOWERED_RUBY.get(), ModBlocks.EMPOWERED_RUBY_BLOCK.get(), ModItems.EMPOWERED_CORE.get());
        blockCraft(pWriter, ModItems.EMPOWERED_SAPPHIRE.get(), ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get(), ModItems.EMPOWERED_CORE.get());
        blockCraft(pWriter, ModItems.EMPOWERED_TOPAZ.get(), ModBlocks.EMPOWERED_TOPAZ_BLOCK.get(), ModItems.EMPOWERED_CORE.get());

        blockCraft(pWriter, ModItems.HYPER_JADE.get(), ModBlocks.HYPER_JADE_BLOCK.get(), ModItems.HYPER_CORE.get());
        blockCraft(pWriter, ModItems.HYPER_RUBY.get(), ModBlocks.HYPER_RUBY_BLOCK.get(), ModItems.HYPER_CORE.get());
        blockCraft(pWriter, ModItems.HYPER_SAPPHIRE.get(), ModBlocks.HYPER_SAPPHIRE_BLOCK.get(), ModItems.HYPER_CORE.get());
        blockCraft(pWriter, ModItems.HYPER_TOPAZ.get(), ModBlocks.HYPER_TOPAZ_BLOCK.get(), ModItems.HYPER_CORE.get());

        blockCraft(pWriter, ModItems.OMEGA_JADE.get(), ModBlocks.OMEGA_JADE_BLOCK.get(), ModItems.OMEGA_CORE.get());
        blockCraft(pWriter, ModItems.OMEGA_RUBY.get(), ModBlocks.OMEGA_RUBY_BLOCK.get(), ModItems.OMEGA_CORE.get());
        blockCraft(pWriter, ModItems.OMEGA_SAPPHIRE.get(), ModBlocks.OMEGA_SAPPHIRE_BLOCK.get(), ModItems.OMEGA_CORE.get());
        blockCraft(pWriter, ModItems.OMEGA_TOPAZ.get(), ModBlocks.OMEGA_TOPAZ_BLOCK.get(), ModItems.OMEGA_CORE.get());

        gemUpgradeCraft(pWriter, ModBlocks.JADE_BLOCK.get(), ModItems.EMPOWERED_CORE.get(), ModItems.EMPOWERED_JADE.get());
        gemUpgradeCraft(pWriter, ModBlocks.RUBY_BLOCK.get(), ModItems.EMPOWERED_CORE.get(), ModItems.EMPOWERED_RUBY.get());
        gemUpgradeCraft(pWriter, ModBlocks.SAPPHIRE_BLOCK.get(), ModItems.EMPOWERED_CORE.get(), ModItems.EMPOWERED_SAPPHIRE.get());
        gemUpgradeCraft(pWriter, ModBlocks.TOPAZ_BLOCK.get(), ModItems.EMPOWERED_CORE.get(), ModItems.EMPOWERED_TOPAZ.get());

        gemUpgradeCraft(pWriter, ModBlocks.EMPOWERED_JADE_BLOCK.get(), ModItems.HYPER_CORE.get(), ModItems.HYPER_JADE.get());
        gemUpgradeCraft(pWriter, ModBlocks.EMPOWERED_RUBY_BLOCK.get(), ModItems.HYPER_CORE.get(), ModItems.HYPER_RUBY.get());
        gemUpgradeCraft(pWriter, ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get(), ModItems.HYPER_CORE.get(), ModItems.HYPER_SAPPHIRE.get());
        gemUpgradeCraft(pWriter, ModBlocks.EMPOWERED_TOPAZ_BLOCK.get(), ModItems.HYPER_CORE.get(), ModItems.HYPER_TOPAZ.get());

        gemUpgradeCraft(pWriter, ModBlocks.HYPER_JADE_BLOCK.get(), ModItems.OMEGA_CORE.get(), ModItems.OMEGA_JADE.get());
        gemUpgradeCraft(pWriter, ModBlocks.HYPER_RUBY_BLOCK.get(), ModItems.OMEGA_CORE.get(), ModItems.OMEGA_RUBY.get());
        gemUpgradeCraft(pWriter, ModBlocks.HYPER_SAPPHIRE_BLOCK.get(), ModItems.OMEGA_CORE.get(), ModItems.OMEGA_SAPPHIRE.get());
        gemUpgradeCraft(pWriter, ModBlocks.HYPER_TOPAZ_BLOCK.get(), ModItems.OMEGA_CORE.get(), ModItems.OMEGA_TOPAZ.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ELEMENTAL_SINGULARITY.get())
                .pattern(" R ")
                .pattern("J#S")
                .pattern(" T ")
                .define('J', ModBlocks.OMEGA_JADE_BLOCK.get())
                .define('R', ModBlocks.OMEGA_RUBY_BLOCK.get())
                .define('S', ModBlocks.OMEGA_SAPPHIRE_BLOCK.get())
                .define('T', ModBlocks.OMEGA_TOPAZ_BLOCK.get())
                .define('#', ModItems.STARFORGED_CORE.get())
                .unlockedBy(getHasName(ModItems.STARFORGED_CORE.get()), has(ModItems.STARFORGED_CORE.get()))
                .save(pWriter);

        blockCraft(pWriter, ModItems.DRAKE_SCALE.get(), ModItems.DRAKE_HIDE.get(), ModItems.AMALGAMITE.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DRAKESTEEL_INGOT.get())
                .requires(ModItems.DRAKE_HIDE.get(), 4)
                .requires(Items.GOLD_BLOCK, 1)
                .requires(ModBlocks.AMALGAMITE_BLOCK.get(), 4)
                .unlockedBy(getHasName(ModItems.AMALGAMITE.get()), has(ModItems.AMALGAMITE.get()))
                .save(pWriter);

        blockCraft(pWriter, ModItems.AMALGAMITE.get(), ModItems.CONCENTRATED_AMALGAMITE.get(), ModItems.AMALGAMITE.get());
        blockCraft(pWriter, ModItems.CONCENTRATED_AMALGAMITE.get(), ModBlocks.AMALGAMITE_BLOCK.get(), ModItems.AMALGAMITE.get());

        blockCraft(pWriter, ModItems.COSMITE_DUST.get(), ModItems.COSMITE.get(), ModItems.COSMITE_DUST.get());
        blockCraft(pWriter, ModItems.COSMITE.get(), ModBlocks.COSMITE_BLOCK.get(), ModItems.COSMITE.get());

        simpleToolComponentCraft(pWriter, Items.STICK, ModItems.SIMPLE_HANDLE.get());
        simpleToolComponentCraft(pWriter, Items.WOODEN_SWORD, ModItems.SIMPLE_BLADE.get());
        simpleToolComponentCraft(pWriter, Items.COPPER_BLOCK, ModItems.SIMPLE_PLATING.get());

        heavyToolComponentCraft(pWriter, ModItems.SIMPLE_HANDLE.get(), ModItems.HEAVY_HANDLE.get());
        heavyToolComponentCraft(pWriter, ModItems.SIMPLE_BLADE.get(), ModItems.HEAVY_BLADE.get());
        heavyToolComponentCraft(pWriter, ModItems.SIMPLE_PLATING.get(), ModItems.HEAVY_PLATING.get());

        refinedToolComponentCraft(pWriter, ModItems.HEAVY_HANDLE.get(), ModItems.REFINED_HANDLE.get());
        refinedToolComponentCraft(pWriter, ModItems.HEAVY_BLADE.get(), ModItems.REFINED_BLADE.get());
        refinedToolComponentCraft(pWriter, ModItems.HEAVY_PLATING.get(), ModItems.REFINED_PLATING.get());

        divineToolComponentCraft(pWriter, ModItems.REFINED_HANDLE.get(), ModItems.DIVINE_HANDLE.get());
        divineToolComponentCraft(pWriter, ModItems.REFINED_BLADE.get(), ModItems.DIVINE_BLADE.get());
        divineToolComponentCraft(pWriter, ModItems.REFINED_PLATING.get(), ModItems.DIVINE_PLATING.get());


        UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAKESTEEL_PICKAXE.get())
                .pattern("DDD")
                .pattern(" P ")
                .pattern(" S ")
                .define('D', ModItems.DRAKESTEEL_INGOT.get())
                .define('P', Items.NETHERITE_PICKAXE)
                .define('S', ModItems.SIMPLE_HANDLE.get())
                .unlockedBy(getHasName(ModItems.DRAKE_SCALE.get()), has(ModItems.DRAKE_SCALE.get()))
                .save(pWriter);

        UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAKESTEEL_AXE.get())
                .pattern("DD ")
                .pattern("DA ")
                .pattern(" S ")
                .define('D', ModItems.DRAKESTEEL_INGOT.get())
                .define('A', Items.NETHERITE_AXE)
                .define('S', ModItems.SIMPLE_HANDLE.get())
                .unlockedBy(getHasName(ModItems.DRAKE_SCALE.get()), has(ModItems.DRAKE_SCALE.get()))
                .save(pWriter);

        UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAKESTEEL_HOE.get())
                .pattern("DD ")
                .pattern(" H ")
                .pattern(" S ")
                .define('D', ModItems.DRAKESTEEL_INGOT.get())
                .define('H', Items.NETHERITE_HOE)
                .define('S', ModItems.SIMPLE_HANDLE.get())
                .unlockedBy(getHasName(ModItems.DRAKE_SCALE.get()), has(ModItems.DRAKE_SCALE.get()))
                .save(pWriter);

        UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAKESTEEL_SHOVEL.get())
                .pattern(" D ")
                .pattern(" A ")
                .pattern(" S ")
                .define('D', ModItems.DRAKESTEEL_INGOT.get())
                .define('A', Items.NETHERITE_SHOVEL)
                .define('S', ModItems.SIMPLE_HANDLE.get())
                .unlockedBy(getHasName(ModItems.DRAKE_SCALE.get()), has(ModItems.DRAKE_SCALE.get()))
                .save(pWriter);

        UpgradeRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMALGAMITE_SWORD.get())
                .pattern("ABA")
                .pattern("ASA")
                .pattern("AHA")
                .define('A', ModItems.CONCENTRATED_AMALGAMITE.get())
                .define('B', ModItems.SIMPLE_BLADE.get())
                .define('H', ModItems.SIMPLE_HANDLE.get())
                .define('S', Items.NETHERITE_SWORD)
                .unlockedBy(getHasName(ModItems.AMALGAMITE.get()), has(ModItems.AMALGAMITE.get()))
                .save(pWriter);
    }

    protected static void blockCraft(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, ItemLike result, ItemLike unlockedBy) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy(getHasName(unlockedBy), has(unlockedBy))
                .save(pWriter);
    }

    protected static void gemUpgradeCraft(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, ItemLike core, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern(" # ")
                .pattern("#C#")
                .pattern(" # ")
                .define('#', ingredient)
                .define('C', core)
                .unlockedBy(getHasName(core), has(core))
                .save(pWriter);
    }

    protected static void simpleToolComponentCraft(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern(" G ")
                .pattern("I#I")
                .pattern(" D ")
                .define('G', Items.GOLD_INGOT)
                .define('I', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .define('#', ingredient)
                .unlockedBy(getHasName(ModItems.AMALGAMITE.get()), has(ModItems.AMALGAMITE.get()))
                .save(pWriter);
    }

    protected static void heavyToolComponentCraft(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern(" E ")
                .pattern("S#S")
                .pattern(" S ")
                .define('S', Items.NETHERITE_SCRAP)
                .define('E', ModItems.ELEMENTITE.get())
                .define('#', ingredient)
                .unlockedBy(getHasName(ModItems.ELEMENTITE.get()), has(ModItems.ELEMENTITE.get()))
                .save(pWriter);
    }

    protected static void refinedToolComponentCraft(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern(" A ")
                .pattern("S#S")
                .pattern(" S ")
                .define('S', ModItems.METEORITE_SHARD.get())
                .define('A', ModBlocks.AMALGAMITE_BLOCK.get())
                .define('#', ingredient)
                .unlockedBy(getHasName(ModItems.METEORITE_SHARD.get()), has(ModItems.METEORITE_SHARD.get()))
                .save(pWriter);
    }

    protected static void divineToolComponentCraft(Consumer<FinishedRecipe> pWriter, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern(" C ")
                .pattern("L#D")
                .pattern(" C ")
                .define('L', ModItems.LIGHT_ESSENCE.get())
                .define('D', ModItems.DARK_ESSENCE.get())
                .define('C', ModBlocks.COSMITE_BLOCK.get())
                .define('#', ingredient)
                .unlockedBy(getHasName(ModItems.METEORITE_SHARD.get()), has(ModItems.METEORITE_SHARD.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, KJsProgressionExtender.MOD_ID + ":" + (pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }


}
