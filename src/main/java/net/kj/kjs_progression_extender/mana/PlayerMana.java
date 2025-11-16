package net.kj.kjs_progression_extender.mana;

import net.kj.kjs_progression_extender.network.ModNetworking;
import net.kj.kjs_progression_extender.network.SyncManaPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class PlayerMana {

    public static void addMana(ServerPlayer player, float amount) {
        player.getCapability(ManaCapability.MANA).ifPresent(iMana -> {
            iMana.addMana(amount);
            ModNetworking.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> player),
                    new SyncManaPacket(iMana.getMana())
            );
        });
    }

    public static void consumeMana(ServerPlayer player, float amount) {
        player.getCapability(ManaCapability.MANA).ifPresent(iMana -> {
            iMana.removeMana(amount);
            ModNetworking.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> player),
                    new SyncManaPacket(iMana.getMana())
            );
        });
    }
}