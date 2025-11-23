package net.kj.kjs_progression_extender.item.custom;

import net.kj.kjs_progression_extender.item.types.ModWeaponItem;
import net.kj.kjs_progression_extender.mana.ManaCapability;
import net.kj.kjs_progression_extender.mana.PlayerMana;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RogueDirk extends ModWeaponItem {
    int abilityCooldown;

    public RogueDirk(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int strength, int critDamage, int critChance, int elementalDamage, int lifeSteal, int attackSpeed, int health, int healthRegen, int mana, int manaRegen, int defence, int speed, int abilityCooldown) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, strength, critDamage, critChance, elementalDamage, lifeSteal, attackSpeed, health, healthRegen, mana, manaRegen, defence, speed);
        this.abilityCooldown = abilityCooldown;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            pPlayer.getCapability(ManaCapability.MANA).ifPresent(iMana -> {
                if (iMana.getMana() < 10) {
                    pPlayer.sendSystemMessage(Component.literal("§cYou are out of mana!"));
                } else {
                    PlayerMana.consumeMana(((ServerPlayer) pPlayer), 10);
                    pPlayer.getCooldowns().addCooldown(this, this.abilityCooldown);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0, false, true));
                }
            });
        }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.literal("§6§lAbility: Rogue Dash"));
        pTooltipComponents.add(Component.literal("§7Grants Speed I for 5 seconds."));
        pTooltipComponents.add(Component.literal("§7Cost: §910 mana§7 Cooldown: §910 seconds."));
        pTooltipComponents.add(Component.literal(""));
    }
}
