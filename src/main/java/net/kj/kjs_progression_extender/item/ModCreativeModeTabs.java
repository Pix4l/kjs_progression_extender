package net.kj.kjs_progression_extender.item;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.ModBlocks;
import net.kj.kjs_progression_extender.block.entity.ModBlockEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KJsProgressionExtender.MOD_ID);

    public static final RegistryObject<CreativeModeTab> KJS_PROGRESSION_EXTENDER_TAB = CREATIVE_MODE_TABS.register("kjs_progression_extender_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.kjs_progression_extender_materials_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Gemstone Ores
                        output.accept(ModBlocks.JADE_ORE.get());
                        output.accept(ModBlocks.RUBY_ORE.get());
                        output.accept(ModBlocks.SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.TOPAZ_ORE.get());

                        output.accept(ModBlocks.DEEPSLATE_JADE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_RUBY_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_TOPAZ_ORE.get());

                        //Gemstones
                        output.accept(ModItems.JADE.get());
                        output.accept(ModItems.RUBY.get());
                        output.accept(ModItems.SAPPHIRE.get());
                        output.accept(ModItems.TOPAZ.get());

                        output.accept(ModBlocks.JADE_BLOCK.get());
                        output.accept(ModBlocks.RUBY_BLOCK.get());
                        output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.TOPAZ_BLOCK.get());

                        output.accept(ModItems.EMPOWERED_JADE.get());
                        output.accept(ModItems.EMPOWERED_RUBY.get());
                        output.accept(ModItems.EMPOWERED_SAPPHIRE.get());
                        output.accept(ModItems.EMPOWERED_TOPAZ.get());

                        output.accept(ModBlocks.EMPOWERED_JADE_BLOCK.get());
                        output.accept(ModBlocks.EMPOWERED_RUBY_BLOCK.get());
                        output.accept(ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.EMPOWERED_TOPAZ_BLOCK.get());

                        output.accept(ModItems.HYPER_JADE.get());
                        output.accept(ModItems.HYPER_RUBY.get());
                        output.accept(ModItems.HYPER_SAPPHIRE.get());
                        output.accept(ModItems.HYPER_TOPAZ.get());

                        output.accept(ModBlocks.HYPER_JADE_BLOCK.get());
                        output.accept(ModBlocks.HYPER_RUBY_BLOCK.get());
                        output.accept(ModBlocks.HYPER_SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.HYPER_TOPAZ_BLOCK.get());

                        output.accept(ModItems.OMEGA_JADE.get());
                        output.accept(ModItems.OMEGA_RUBY.get());
                        output.accept(ModItems.OMEGA_SAPPHIRE.get());
                        output.accept(ModItems.OMEGA_TOPAZ.get());

                        output.accept(ModBlocks.OMEGA_JADE_BLOCK.get());
                        output.accept(ModBlocks.OMEGA_RUBY_BLOCK.get());
                        output.accept(ModBlocks.OMEGA_SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.OMEGA_TOPAZ_BLOCK.get());

                        output.accept(ModItems.ELEMENTAL_SINGULARITY.get());

                        //Ores
                        output.accept(ModBlocks.DEEPSLATE_AMALGAMITE_ORE.get());
                        output.accept(ModItems.AMALGAMITE.get());
                        output.accept(ModItems.CONCENTRATED_AMALGAMITE.get());
                        output.accept(ModBlocks.AMALGAMITE_BLOCK.get());

                        output.accept(ModBlocks.ELEMENTITE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ELEMENTITE_ORE.get());
                        output.accept(ModItems.ELEMENTITE.get());

                        output.accept(ModBlocks.METEORITE_SHARD_ORE.get());
                        output.accept(ModItems.METEORITE_SHARD.get());

                        output.accept(ModBlocks.COSMITE_ORE.get());
                        output.accept(ModItems.COSMITE_DUST.get());

                        //Ingot Crafting
                        output.accept(ModItems.DRAKE_SCALE.get());
                        output.accept(ModItems.DRAKE_HIDE.get());

                        //Ingots
                        output.accept(ModItems.DRAKESTEEL_INGOT.get());
                        output.accept(ModItems.PYROLITE_INGOT.get());
                        output.accept(ModItems.HYDROLITE_INGOT.get());
                        output.accept(ModItems.TERRALITE_INGOT.get());
                        output.accept(ModItems.FULGURITE_INGOT.get());
                        output.accept(ModItems.VOIDBLIGHT_INGOT.get());
                        output.accept(ModItems.GLEAMSTONE_INGOT.get());
                        output.accept(ModItems.STARCAST_INGOT.get());

                        //Tool Components
                        output.accept(ModItems.SIMPLE_HANDLE.get());
                        output.accept(ModItems.HEAVY_HANDLE.get());
                        output.accept(ModItems.REFINED_HANDLE.get());
                        output.accept(ModItems.DIVINE_HANDLE.get());

                        output.accept(ModItems.SIMPLE_BLADE.get());
                        output.accept(ModItems.HEAVY_BLADE.get());
                        output.accept(ModItems.REFINED_BLADE.get());
                        output.accept(ModItems.DIVINE_BLADE.get());

                        output.accept(ModItems.SIMPLE_PLATING.get());
                        output.accept(ModItems.HEAVY_PLATING.get());
                        output.accept(ModItems.REFINED_PLATING.get());
                        output.accept(ModItems.DIVINE_PLATING.get());

                        //Tools
                        output.accept(ModItems.DRAKESTEEL_PICKAXE.get());
                        output.accept(ModItems.TEST_SWORD.get());
                        output.accept(ModItems.AMALGAMITE_SWORD.get());

                        //ARMOR
                        output.accept(ModItems.AMALGAMITE_HELMET.get());
                        output.accept(ModItems.AMALGAMITE_CHESTPLATE.get());
                        output.accept(ModItems.AMALGAMITE_LEGGINGS.get());
                        output.accept(ModItems.AMALGAMITE_BOOTS.get());

                        //Misc
                        output.accept(ModItems.EMPOWERED_CORE.get());
                        output.accept(ModItems.HYPER_CORE.get());
                        output.accept(ModItems.OMEGA_CORE.get());
                        output.accept(ModItems.STARFORGED_CORE.get());

                        output.accept(ModBlocks.JEWELING_STATION.get());

                        output.accept(ModItems.TEST_ITEM.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
