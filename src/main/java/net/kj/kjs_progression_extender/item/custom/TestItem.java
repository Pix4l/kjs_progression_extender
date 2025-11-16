package net.kj.kjs_progression_extender.item.custom;

import net.kj.kjs_progression_extender.mana.ManaCapability;
import net.kj.kjs_progression_extender.mana.PlayerMana;
import net.kj.kjs_progression_extender.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TestItem extends Item {
    public TestItem(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide) {
            ServerPlayer player = ((ServerPlayer) pContext.getPlayer());
            PlayerMana.addMana(player, 1);

        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity) {
        if (pMiningEntity instanceof Player player) {
            if (!player.level().isClientSide) {
                //Add max mana
            }
        }

        return false;
    }

}
