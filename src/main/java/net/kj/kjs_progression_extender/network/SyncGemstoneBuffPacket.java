package net.kj.kjs_progression_extender.network;

import net.kj.kjs_progression_extender.block.entity.JewelingStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncGemstoneBuffPacket {

    private final int blockX;
    private final int blockY;
    private final int blockZ;
    private final int slot;
    private final int selected;

    public SyncGemstoneBuffPacket(int blockX, int blockY, int blockZ, int slot, int selected) {
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockZ = blockZ;
        this.slot = slot;
        this.selected = selected;
    }

    public SyncGemstoneBuffPacket(FriendlyByteBuf buf) {
        this.blockX = buf.readInt();
        this.blockY = buf.readInt();
        this.blockZ = buf.readInt();
        this.slot = buf.readInt();
        this.selected = buf.readInt();
    }

    public static void toBytes(SyncGemstoneBuffPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.blockX);
        buf.writeInt(msg.blockY);
        buf.writeInt(msg.blockZ);
        buf.writeInt(msg.slot);
        buf.writeInt(msg.selected);
    }

    public static void handle(SyncGemstoneBuffPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        ctx.get().enqueueWork(() -> {
            ServerLevel level = ctx.get().getSender().serverLevel();
            BlockPos pos = new BlockPos(msg.blockX, msg.blockY, msg.blockZ);
            if (level.getBlockEntity(pos) instanceof JewelingStationBlockEntity be) {
                be.setSelectedBuff(msg.slot, msg.selected);
            }
        });

        context.setPacketHandled(true);
    }
}
