package net.kj.kjs_progression_extender.util;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.mana.Mana;
import net.kj.kjs_progression_extender.mana.ManaCapability;
import net.kj.kjs_progression_extender.mana.ManaProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEvents {
    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(KJsProgressionExtender.MOD_ID, "mana"), new ManaProvider());
        }
    }

    @SubscribeEvent
    public void clone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ManaCapability.MANA).ifPresent(oldMana -> {
                event.getEntity().getCapability(ManaCapability.MANA).ifPresent(newMana -> {
                    if (oldMana instanceof Mana o && newMana instanceof Mana n) {
                        n.copyFrom(o);
                    }
                });
            });
        }
    }
}
