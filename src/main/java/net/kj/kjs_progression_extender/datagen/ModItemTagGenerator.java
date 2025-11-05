package net.kj.kjs_progression_extender.datagen;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.ModBlocks;
import net.kj.kjs_progression_extender.item.ModItems;
import net.kj.kjs_progression_extender.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, KJsProgressionExtender.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.GEMSTONES)
                .add(ModItems.JADE.get(),
                        ModItems.EMPOWERED_JADE.get(),
                        ModItems.HYPER_JADE.get(),
                        ModItems.OMEGA_JADE.get(),

                        ModItems.RUBY.get(),
                        ModItems.EMPOWERED_RUBY.get(),
                        ModItems.HYPER_RUBY.get(),
                        ModItems.OMEGA_RUBY.get(),

                        ModItems.SAPPHIRE.get(),
                        ModItems.EMPOWERED_SAPPHIRE.get(),
                        ModItems.HYPER_SAPPHIRE.get(),
                        ModItems.OMEGA_SAPPHIRE.get(),

                        ModItems.TOPAZ.get(),
                        ModItems.EMPOWERED_TOPAZ.get(),
                        ModItems.HYPER_TOPAZ.get(),
                        ModItems.OMEGA_TOPAZ.get(),

                        ModItems.ELEMENTAL_SINGULARITY.get(),

                        ModBlocks.JADE_BLOCK.get().asItem(),
                        ModBlocks.RUBY_BLOCK.get().asItem(),
                        ModBlocks.SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.TOPAZ_BLOCK.get().asItem(),

                        ModBlocks.EMPOWERED_JADE_BLOCK.get().asItem(),
                        ModBlocks.EMPOWERED_RUBY_BLOCK.get().asItem(),
                        ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.EMPOWERED_TOPAZ_BLOCK.get().asItem(),

                        ModBlocks.HYPER_JADE_BLOCK.get().asItem(),
                        ModBlocks.HYPER_RUBY_BLOCK.get().asItem(),
                        ModBlocks.HYPER_SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.HYPER_TOPAZ_BLOCK.get().asItem(),

                        ModBlocks.OMEGA_JADE_BLOCK.get().asItem(),
                        ModBlocks.OMEGA_RUBY_BLOCK.get().asItem(),
                        ModBlocks.OMEGA_SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.OMEGA_TOPAZ_BLOCK.get().asItem());

        this.tag(ModTags.Items.JADE)
                .add(ModItems.JADE.get(),
                        ModBlocks.JADE_BLOCK.get().asItem(),
                        ModItems.EMPOWERED_JADE.get(),
                        ModBlocks.EMPOWERED_JADE_BLOCK.get().asItem(),
                        ModItems.HYPER_JADE.get(),
                        ModBlocks.HYPER_JADE_BLOCK.get().asItem(),
                        ModItems.OMEGA_JADE.get(),
                        ModBlocks.OMEGA_JADE_BLOCK.get().asItem());

        this.tag(ModTags.Items.RUBY)
                .add(ModItems.RUBY.get(),
                        ModBlocks.RUBY_BLOCK.get().asItem(),
                        ModItems.EMPOWERED_RUBY.get(),
                        ModBlocks.EMPOWERED_RUBY_BLOCK.get().asItem(),
                        ModItems.HYPER_RUBY.get(),
                        ModBlocks.HYPER_RUBY_BLOCK.get().asItem(),
                        ModItems.OMEGA_RUBY.get(),
                        ModBlocks.OMEGA_RUBY_BLOCK.get().asItem());

        this.tag(ModTags.Items.SAPPHIRE)
                .add(ModItems.SAPPHIRE.get(),
                        ModBlocks.SAPPHIRE_BLOCK.get().asItem(),
                        ModItems.EMPOWERED_SAPPHIRE.get(),
                        ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get().asItem(),
                        ModItems.HYPER_SAPPHIRE.get(),
                        ModBlocks.HYPER_SAPPHIRE_BLOCK.get().asItem(),
                        ModItems.OMEGA_SAPPHIRE.get(),
                        ModBlocks.OMEGA_SAPPHIRE_BLOCK.get().asItem());

        this.tag(ModTags.Items.TOPAZ)
                .add(ModItems.TOPAZ.get(),
                        ModBlocks.TOPAZ_BLOCK.get().asItem(),
                        ModItems.EMPOWERED_TOPAZ.get(),
                        ModBlocks.EMPOWERED_TOPAZ_BLOCK.get().asItem(),
                        ModItems.HYPER_TOPAZ.get(),
                        ModBlocks.HYPER_TOPAZ_BLOCK.get().asItem(),
                        ModItems.OMEGA_TOPAZ.get(),
                        ModBlocks.OMEGA_TOPAZ_BLOCK.get().asItem());

        this.tag(ModTags.Items.POWER_1)
                .add(ModItems.JADE.get(),
                        ModItems.RUBY.get(),
                        ModItems.SAPPHIRE.get(),
                        ModItems.TOPAZ.get());

        this.tag(ModTags.Items.POWER_2)
                .add(ModBlocks.JADE_BLOCK.get().asItem(),
                        ModBlocks.RUBY_BLOCK.get().asItem(),
                        ModBlocks.SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.TOPAZ_BLOCK.get().asItem());

        this.tag(ModTags.Items.POWER_3)
                .add(ModItems.EMPOWERED_JADE.get(),
                        ModItems.EMPOWERED_RUBY.get(),
                        ModItems.EMPOWERED_SAPPHIRE.get(),
                        ModItems.EMPOWERED_TOPAZ.get());

        this.tag(ModTags.Items.POWER_4)
                .add(ModBlocks.EMPOWERED_JADE_BLOCK.get().asItem(),
                        ModBlocks.EMPOWERED_RUBY_BLOCK.get().asItem(),
                        ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.EMPOWERED_TOPAZ_BLOCK.get().asItem());

        this.tag(ModTags.Items.POWER_5)
                .add(ModItems.HYPER_JADE.get(),
                        ModItems.HYPER_RUBY.get(),
                        ModItems.HYPER_SAPPHIRE.get(),
                        ModItems.HYPER_TOPAZ.get());

        this.tag(ModTags.Items.POWER_6)
                .add(ModBlocks.HYPER_JADE_BLOCK.get().asItem(),
                        ModBlocks.HYPER_RUBY_BLOCK.get().asItem(),
                        ModBlocks.HYPER_SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.HYPER_TOPAZ_BLOCK.get().asItem());

        this.tag(ModTags.Items.POWER_7)
                .add(ModItems.OMEGA_JADE.get(),
                        ModItems.OMEGA_RUBY.get(),
                        ModItems.OMEGA_SAPPHIRE.get(),
                        ModItems.OMEGA_TOPAZ.get());

        this.tag(ModTags.Items.POWER_8)
                .add(ModBlocks.OMEGA_JADE_BLOCK.get().asItem(),
                        ModBlocks.OMEGA_RUBY_BLOCK.get().asItem(),
                        ModBlocks.OMEGA_SAPPHIRE_BLOCK.get().asItem(),
                        ModBlocks.OMEGA_TOPAZ_BLOCK.get().asItem());

        this.tag(ModTags.Items.POWER_9)
                .add(ModItems.ELEMENTAL_SINGULARITY.get());

        this.tag(Tags.Items.INGOTS)
                .add(ModItems.DRAKESTEEL_INGOT.get(),
                        ModItems.PYROLITE_INGOT.get(),
                        ModItems.HYDROLITE_INGOT.get(),
                        ModItems.TERRALITE_INGOT.get(),
                        ModItems.FULGURITE_INGOT.get(),
                        ModItems.VOIDBLIGHT_INGOT.get(),
                        ModItems.GLEAMSTONE_INGOT.get(),
                        ModItems.STARCAST_INGOT.get());

        this.tag(ModTags.Items.IS_GEMMABLE)
                .add(ModItems.TEST_SWORD.get(),
                        ModItems.AMALGAMITE_SWORD.get());
    }


}
