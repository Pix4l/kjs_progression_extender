package net.kj.kjs_progression_extender.worldgen;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_JADE_ORE_KEY = registerKey("jade_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TOPAZ_ORE_KEY = registerKey("topaz_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_AMALGAMITE_ORE_KEY = registerKey("amalgamite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ELEMENTITE_ORE_KEY = registerKey("elementite_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldJadeOres = List.of(OreConfiguration.target(stoneReplaceable, ModBlocks.JADE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldRubyOres = List.of(OreConfiguration.target(stoneReplaceable, ModBlocks.RUBY_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldSapphireOres = List.of(OreConfiguration.target(stoneReplaceable, ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldTopazOres = List.of(OreConfiguration.target(stoneReplaceable, ModBlocks.TOPAZ_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldAmalgamiteOre = List.of(OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_AMALGAMITE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldElementiteOres = List.of(OreConfiguration.target(stoneReplaceable, ModBlocks.ELEMENTITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_ELEMENTITE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_JADE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldJadeOres, 3));
        register(context, OVERWORLD_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRubyOres, 3));
        register(context, OVERWORLD_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 3));
        register(context, OVERWORLD_TOPAZ_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTopazOres, 3));
        register(context, OVERWORLD_AMALGAMITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAmalgamiteOre, 5));
        register(context, OVERWORLD_ELEMENTITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldElementiteOres, 2));


    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(KJsProgressionExtender.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
