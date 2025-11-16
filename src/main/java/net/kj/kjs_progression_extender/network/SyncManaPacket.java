package net.kj.kjs_progression_extender.network;

import net.kj.kjs_progression_extender.mana.ManaCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncManaPacket {

    private final double mana;

    public SyncManaPacket(double mana) {
        this.mana = mana;
    }

    public SyncManaPacket(FriendlyByteBuf buf) {
        this.mana = buf.readDouble();
    }

    public static void toBytes(SyncManaPacket msg, FriendlyByteBuf buf) {
        buf.writeDouble(msg.mana);
    }

    public static void handle(SyncManaPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        context.enqueueWork(() -> {
            // client-side update
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.getCapability(ManaCapability.MANA)
                        .ifPresent(iMana -> iMana.setMana(msg.mana));
            }
        });

        context.setPacketHandled(true);
    }
}
