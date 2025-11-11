package net.kj.kjs_progression_extender.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ModPickaxeItem extends PickaxeItem {
    private int miningSpread;

    public ModPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int miningSpread) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.miningSpread = miningSpread;
    }

    @Override
    public void appendHoverText (ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("§7Mines " + String.valueOf(this.miningSpread) + " additional blocks."));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide && pEntityLiving instanceof Player player) {
            breakSpreadBlocks(player, pLevel, pPos, this.miningSpread);
        }

        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    private void breakSpreadBlocks (Player player, Level level, BlockPos pos, int miningSpread) {
        if (!player.isShiftKeyDown()) {
            BlockState center = level.getBlockState(pos);
            if (center.is(Tags.Blocks.STONE) || center.is(Tags.Blocks.NETHERRACK) || center.is(Tags.Blocks.END_STONES)) {
                List<BlockPos> positions = new ArrayList<>();
                positions.add(pos);
                int j = 0;

                for (int i = 0; i < miningSpread; i++) {
                    BlockPos targetPos = positions.get(i).offset(offsetDir(pos, positions.get(i)));
                    if (positions.contains(targetPos) || !level.getBlockState(targetPos).is(Tags.Blocks.STONE) || center.is(Tags.Blocks.NETHERRACK) || center.is(Tags.Blocks.END_STONES)) {
                        i--;
                        j++;
                        if (j > miningSpread * 10) {
                            break;
                        }
                    } else {
                        positions.add(targetPos);
                        level.destroyBlock(targetPos, true, player);
                    }
                }
            }
        }
    }

    private Vec3i offsetDir(Vec3i center, Vec3i current) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int x = random.nextInt(2) * biasedRandom(current.getX() - center.getX(), random);
        int y = random.nextInt(2);
        int z = random.nextInt(2) * biasedRandom(current.getZ() - center.getZ(), random);
        return new Vec3i(x, y, z);
    }

    private int biasedRandom(int distance, ThreadLocalRandom random) {
        double probability = 1.0 / (1.0 + Math.exp(distance));

        return random.nextDouble() < probability ? 1 : -1;
    }
}
