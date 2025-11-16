package net.kj.kjs_progression_extender.mana;

import net.kj.kjs_progression_extender.item.types.ModArmorItem;
import net.kj.kjs_progression_extender.item.types.ModWeaponItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ManaRegenEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            int maxMana = ManaAttributeHelper.getMaxMana(player);

            if (maxMana > 0) {
                player.getCapability(ManaCapability.MANA).ifPresent(iMana -> {
                    if (iMana.getMana() < maxMana) {
                        iMana.addMana(calcRegenPerTick());
                    }
                });
            }
        }
    }

    private static double calcRegenPerTick() {
        Player player = Minecraft.getInstance().player;
        double RegenPerTick = 0.01D;

        for (int i = 0; i < 4; i++) {
            if (player.getInventory().getArmor(i).getItem() instanceof ModArmorItem armorItem) {
                int[] gemstones = player.getInventory().getArmor(i).getTag().getIntArray("gemstones");

                RegenPerTick += (double) armorItem.getGemstoneManaRegenModifier(gemstones) / 100;
            }
        }

        if (player.getMainHandItem().getItem() instanceof ModWeaponItem weaponItem) {
            int[] gemstones = player.getMainHandItem().getTag().getIntArray("gemstones");

            RegenPerTick += (double) weaponItem.getGemstoneManaRegenModifier(gemstones) / 100;
        }

        return RegenPerTick;
    }
}
