package net.kj.kjs_progression_extender.datagen;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, KJsProgressionExtender.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.JADE_BLOCK);
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.TOPAZ_BLOCK);

        blockWithItem(ModBlocks.EMPOWERED_JADE_BLOCK);
        blockWithItem(ModBlocks.EMPOWERED_RUBY_BLOCK);
        blockWithItem(ModBlocks.EMPOWERED_SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.EMPOWERED_TOPAZ_BLOCK);

        blockWithItem(ModBlocks.HYPER_JADE_BLOCK);
        blockWithItem(ModBlocks.HYPER_RUBY_BLOCK);
        blockWithItem(ModBlocks.HYPER_SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.HYPER_TOPAZ_BLOCK);

        blockWithItem(ModBlocks.OMEGA_JADE_BLOCK);
        blockWithItem(ModBlocks.OMEGA_RUBY_BLOCK);
        blockWithItem(ModBlocks.OMEGA_SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.OMEGA_TOPAZ_BLOCK);

        blockWithItem(ModBlocks.JADE_ORE);
        blockWithItem(ModBlocks.RUBY_ORE);
        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.TOPAZ_ORE);

        blockWithItem(ModBlocks.DEEPSLATE_JADE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TOPAZ_ORE);

        blockWithItem(ModBlocks.AMALGAMITE_BLOCK);

        blockWithItem(ModBlocks.DEEPSLATE_AMALGAMITE_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
