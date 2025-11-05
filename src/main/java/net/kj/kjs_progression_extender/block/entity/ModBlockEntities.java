package net.kj.kjs_progression_extender.block.entity;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, KJsProgressionExtender.MOD_ID);

    public static final RegistryObject<BlockEntityType<JewelingStationBlockEntity>> JEWELING_STATION_BE =
            BLOCK_ENTIES.register("jeweling_station_be", () ->
                    BlockEntityType.Builder.of(JewelingStationBlockEntity::new,
                            ModBlocks.JEWELING_STATION.get()).build(null));

    public static void register (IEventBus eventBus) {
        BLOCK_ENTIES.register(eventBus);
    }
}
