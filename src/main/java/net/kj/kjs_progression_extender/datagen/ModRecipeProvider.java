package net.kj.kjs_progression_extender.datagen;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.ModBlocks;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.JADE_BLOCK.get())
                .pattern("JJJ")
                .pattern("JJJ")
                .pattern("JJJ")
                .define('J', ModItems.JADE.get())
                .unlockedBy(getHasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TOPAZ_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.TOPAZ.get())
                .unlockedBy(getHasName(ModItems.TOPAZ.get()), has(ModItems.TOPAZ.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMPOWERED_JADE_BLOCK.get())
                .pattern("JJJ")
                .pattern("JJJ")
                .pattern("JJJ")
                .define('J', ModItems.EMPOWERED_JADE.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMPOWERED_RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.EMPOWERED_RUBY.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.EMPOWERED_SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMPOWERED_TOPAZ_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.EMPOWERED_TOPAZ.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HYPER_JADE_BLOCK.get())
                .pattern("JJJ")
                .pattern("JJJ")
                .pattern("JJJ")
                .define('J', ModItems.HYPER_JADE.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HYPER_RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.HYPER_RUBY.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HYPER_SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.HYPER_SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HYPER_TOPAZ_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.HYPER_TOPAZ.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OMEGA_JADE_BLOCK.get())
                .pattern("JJJ")
                .pattern("JJJ")
                .pattern("JJJ")
                .define('J', ModItems.OMEGA_JADE.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OMEGA_RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.OMEGA_RUBY.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OMEGA_SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.OMEGA_SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OMEGA_TOPAZ_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.OMEGA_TOPAZ.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPOWERED_JADE.get())
                .pattern(" J ")
                .pattern("J#J")
                .pattern(" J ")
                .define('J', ModBlocks.JADE_BLOCK.get())
                .define('#', ModItems.EMPOWERED_CORE.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPOWERED_RUBY.get())
                .pattern(" R ")
                .pattern("R#R")
                .pattern(" R ")
                .define('R', ModBlocks.RUBY_BLOCK.get())
                .define('#', ModItems.EMPOWERED_CORE.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPOWERED_SAPPHIRE.get())
                .pattern(" S ")
                .pattern("S#S")
                .pattern(" S ")
                .define('S', ModBlocks.SAPPHIRE_BLOCK.get())
                .define('#', ModItems.EMPOWERED_CORE.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMPOWERED_TOPAZ.get())
                .pattern(" T ")
                .pattern("T#T")
                .pattern(" T ")
                .define('T', ModBlocks.TOPAZ_BLOCK.get())
                .define('#', ModItems.EMPOWERED_CORE.get())
                .unlockedBy(getHasName(ModItems.EMPOWERED_CORE.get()), has(ModItems.EMPOWERED_CORE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HYPER_JADE.get())
                .pattern(" J ")
                .pattern("J#J")
                .pattern(" J ")
                .define('J', ModBlocks.EMPOWERED_JADE_BLOCK.get())
                .define('#', ModItems.HYPER_CORE.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HYPER_RUBY.get())
                .pattern(" R ")
                .pattern("R#R")
                .pattern(" R ")
                .define('R', ModBlocks.EMPOWERED_RUBY_BLOCK.get())
                .define('#', ModItems.HYPER_CORE.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HYPER_SAPPHIRE.get())
                .pattern(" S ")
                .pattern("S#S")
                .pattern(" S ")
                .define('S', ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get())
                .define('#', ModItems.HYPER_CORE.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HYPER_TOPAZ.get())
                .pattern(" T ")
                .pattern("T#T")
                .pattern(" T ")
                .define('T', ModBlocks.EMPOWERED_TOPAZ_BLOCK.get())
                .define('#', ModItems.HYPER_CORE.get())
                .unlockedBy(getHasName(ModItems.HYPER_CORE.get()), has(ModItems.HYPER_CORE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OMEGA_JADE.get())
                .pattern(" J ")
                .pattern("J#J")
                .pattern(" J ")
                .define('J', ModBlocks.HYPER_JADE_BLOCK.get())
                .define('#', ModItems.OMEGA_CORE.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OMEGA_RUBY.get())
                .pattern(" R ")
                .pattern("R#R")
                .pattern(" R ")
                .define('R', ModBlocks.HYPER_RUBY_BLOCK.get())
                .define('#', ModItems.OMEGA_CORE.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OMEGA_SAPPHIRE.get())
                .pattern(" S ")
                .pattern("S#S")
                .pattern(" S ")
                .define('S', ModBlocks.HYPER_SAPPHIRE_BLOCK.get())
                .define('#', ModItems.OMEGA_CORE.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OMEGA_TOPAZ.get())
                .pattern(" T ")
                .pattern("T#T")
                .pattern(" T ")
                .define('T', ModBlocks.HYPER_TOPAZ_BLOCK.get())
                .define('#', ModItems.OMEGA_CORE.get())
                .unlockedBy(getHasName(ModItems.OMEGA_CORE.get()), has(ModItems.OMEGA_CORE.get()))
                .save(pWriter);

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.JADE.get(), 9)
                .requires(ModBlocks.JADE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.JADE_BLOCK.get()), has(ModBlocks.JADE_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RUBY_BLOCK.get()), has(ModBlocks.RUBY_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOPAZ.get(), 9)
                .requires(ModBlocks.TOPAZ_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TOPAZ_BLOCK.get()), has(ModBlocks.TOPAZ_BLOCK.get()))
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
