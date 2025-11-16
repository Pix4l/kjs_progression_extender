package net.kj.kjs_progression_extender.damage;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KJsProgressionExtender.MOD_ID)
public class TakeDamageCalculation {
    @SubscribeEvent
    public static void onLivingHurt (LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player && event.getSource().getEntity() != null) {
            player.sendSystemMessage(Component.literal("ouch"));
        }
    }
}
