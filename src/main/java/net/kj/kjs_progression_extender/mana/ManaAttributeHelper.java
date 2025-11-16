package net.kj.kjs_progression_extender.mana;

import net.kj.kjs_progression_extender.attribute.ModAttributes;
import net.minecraft.world.entity.player.Player;

public class ManaAttributeHelper {

    public static int getMaxMana(Player player) {
        if (player.getAttributes() != null) {
            if (player.getAttributes().hasAttribute(ModAttributes.MAX_MANA.get())) {
                return (int) player.getAttribute(ModAttributes.MAX_MANA.get()).getValue();
            }
        }

        return 0;
    }

    public static void clamp(Player player) {
        int max = getMaxMana(player);

        player.getCapability(ManaCapability.MANA).ifPresent(iMana -> {
            if (iMana.getMana() > max)
                iMana.setMana(max);
        });
    }
}
