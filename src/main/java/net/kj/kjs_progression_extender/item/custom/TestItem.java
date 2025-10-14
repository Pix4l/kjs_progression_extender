package net.kj.kjs_progression_extender.item.custom;

import net.kj.kjs_progression_extender.util.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class TestItem extends Item {
    public TestItem(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide) {
            Player player = pContext.getPlayer();

            ItemStack offhandItemStack = player.getOffhandItem();

            if(offhandItemStack.is(ModTags.Items.GEMSTONES)) {
                player.sendSystemMessage(Component.literal("You are holding a gemstone"));
            }
            else {
                player.sendSystemMessage(Component.literal("hee hee hee haw"));
            }
        }

        return InteractionResult.SUCCESS;
    }
}
