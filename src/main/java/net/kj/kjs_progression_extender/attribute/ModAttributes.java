package net.kj.kjs_progression_extender.attribute;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, KJsProgressionExtender.MOD_ID);

    public static final RegistryObject<Attribute> MAX_MANA =
            ATTRIBUTES.register("max_mana",
                    () -> new RangedAttribute("attribute." + KJsProgressionExtender.MOD_ID + ".max_mana",
                            20.0F,
                            20.0F,
                            10000.0F
                    ).setSyncable(true)
            );

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}