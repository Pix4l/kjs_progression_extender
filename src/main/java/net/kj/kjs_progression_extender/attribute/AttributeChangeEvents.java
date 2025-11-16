package net.kj.kjs_progression_extender.attribute;

import net.kj.kjs_progression_extender.mana.ManaAttributeHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AttributeChangeEvents {

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            ManaAttributeHelper.clamp(player);
        }
    }

    @SubscribeEvent
    public static void onAttributeModified(EntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            ManaAttributeHelper.clamp(player);
        }
    }
}
