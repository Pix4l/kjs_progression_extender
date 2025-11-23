package net.kj.kjs_progression_extender.item.types;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.kj.kjs_progression_extender.attribute.ModAttributes;
import net.kj.kjs_progression_extender.util.GemstoneBuffs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ModBowItem extends BowItem {
    boolean shortBow;
    int arrowCount;
    int attackDamage;
    int attackSpeed;
    int strength;
    int critDamage;
    int critChance;
    int elementalDamage;
    int lifeSteal;
    int health;
    int healthRegen;
    int mana;
    int manaRegen;
    int defence;
    int speed;

    UUID WEAPON_HEALTH_BONUS_ID = UUID.randomUUID();
    UUID WEAPON_SPEED_BONUS_ID = UUID.randomUUID();
    UUID WEAPON_MANA_BONUS_ID = UUID.randomUUID();

    public ModBowItem(Properties pProperties, boolean shortBow, int arrowCount, int attackDamage, int attackSpeed, int strength, int critDamage, int critChance, int elementalDamage, int lifeSteal, int health, int healthRegen, int mana, int manaRegen, int defence, int speed) {
        super(pProperties);
        this.shortBow = shortBow;
        this.arrowCount = arrowCount;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.strength = strength;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.elementalDamage = elementalDamage;
        this.lifeSteal = lifeSteal;
        this.health = health;
        this.healthRegen = healthRegen;
        this.mana = mana;
        this.manaRegen = manaRegen;
        this.defence = defence;
        this.speed = speed;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return ImmutableMultimap.of();
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
        putNbt(stack);

        int[] gemstones = stack.getTag().getIntArray("gemstones");

        applyHealthBonus(WEAPON_HEALTH_BONUS_ID, player, gemstones, slotIndex, selectedIndex);
        applySpeedBonus(WEAPON_SPEED_BONUS_ID, player, gemstones, slotIndex, selectedIndex);
        applyManaBonus(WEAPON_MANA_BONUS_ID, player, gemstones, slotIndex, selectedIndex);

        int[] cooldowns = stack.getTag().getIntArray("cooldowns");
        for (int i = 0; i < 2; i++) {
            if (cooldowns[i] > 0) {
                cooldowns[i] --;
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player && !this.shortBow) {
            fireArrows(player, pStack, pLevel, pTimeLeft);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (this.shortBow) {
            pPlayer.releaseUsingItem();
            CompoundTag tag = pPlayer.getItemInHand(pHand).getTag();
            int[] cooldowns = tag.getIntArray("cooldowns");
            int[] gemstones = tag.getIntArray("gemstones");
            if (cooldowns[0] == 0) {
                int newCooldown = 0;
                fireArrows(pPlayer, pPlayer.getItemInHand(pHand), pLevel, 0);
                for (int i = 0; i < 4; i++) {
                    if (pPlayer.getInventory().getArmor(i).getItem() instanceof ModArmorItem armorItem) {
                        newCooldown += armorItem.getAttackSpeed() + GemstoneBuffs.getAttackSpeedModifier(GemstoneBuffs.getGemstones(pPlayer.getInventory().getArmor(i)));
                    }
                }
                cooldowns[0] = (int) Math.floor(this.getAttackSpeed() * Math.pow(Math.E, (double) (GemstoneBuffs.getAttackSpeedModifier(gemstones) + newCooldown) / -84));
            }
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));
        } else {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            boolean flag = !pPlayer.getProjectile(itemstack).isEmpty();

            InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
            if (ret != null) return ret;

            if (!pPlayer.getAbilities().instabuild && !flag) {
                return InteractionResultHolder.fail(itemstack);
            } else {
                pPlayer.startUsingItem(pHand);
                return InteractionResultHolder.consume(itemstack);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        putNbt(pStack);

        int[] gemstones = pStack.getTag().getIntArray("gemstones");

        pTooltipComponents.add(Component.literal("§7Attack Damage: §c+" + this.attackDamage));

        if (this.strength > 0 || GemstoneBuffs.getStrengthModifier(gemstones) > 0) {
            String strengthTooltip = "§7Strength: §c+" + this.strength;
            int gemstoneBuff = GemstoneBuffs.getStrengthModifier(gemstones);
            if (gemstoneBuff > 0) {
                strengthTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(strengthTooltip));
        }
        if (this.critDamage > 0 || GemstoneBuffs.getCritDamageModifier(gemstones) > 0) {
            String critDamageTooltip = "§7Crit Damage: §3+" + this.critDamage;
            int gemstoneBuff = GemstoneBuffs.getCritDamageModifier(gemstones);
            if (gemstoneBuff > 0) {
                critDamageTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(critDamageTooltip));
        }
        if (this.critChance > 0 || GemstoneBuffs.getCritChanceModifier(gemstones) > 0) {
            String critChanceTooltip = "§7Crit Chance: §3+" + this.critChance;
            int gemstoneBuff = GemstoneBuffs.getCritChanceModifier(gemstones);
            if (gemstoneBuff > 0) {
                critChanceTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(critChanceTooltip));
        }
        if (this.elementalDamage > 0 || GemstoneBuffs.getElementalDamageModifier(gemstones) > 0) {
            String elementalDamageTooltip = "§7Elemental Damage: §2+" + this.elementalDamage;
            int gemstoneBuff = GemstoneBuffs.getElementalDamageModifier(gemstones);
            if (gemstoneBuff > 0) {
                elementalDamageTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(elementalDamageTooltip));
        }
        if (this.lifeSteal > 0 || GemstoneBuffs.getLifeStealModifier(gemstones) > 0) {
            String lifeStealTooltip = "§7Life Steal: §4+" + this.lifeSteal;
            int gemstoneBuff = GemstoneBuffs.getLifeStealModifier(gemstones);
            if (gemstoneBuff > 0) {
                lifeStealTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(lifeStealTooltip));
        }
        if (this.attackSpeed > 0 || GemstoneBuffs.getAttackSpeedModifier(gemstones) > 0) {
            String attackSpeedTooltip = "§7Attack Speed: §5+" + String.format("%.1f", 20 / ((double) this.attackSpeed));
            int gemstoneBuff = GemstoneBuffs.getAttackSpeedModifier(gemstones);
            if (gemstoneBuff > 0) {
                attackSpeedTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(attackSpeedTooltip));
        }
        if (this.health > 0 || GemstoneBuffs.getHealthModifier(gemstones) > 0) {
            String healthTooltip = "§7Health Bonus: §c" + this.health;
            int gemstoneBuff = GemstoneBuffs.getHealthModifier(gemstones);
            if (gemstoneBuff > 0) {
                healthTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(healthTooltip));
        }
        if (this.healthRegen > 0 || GemstoneBuffs.getRegenModifier(gemstones) > 0) {
            String regenTooltip = "§7Health Regen: §c" + this.healthRegen + "/5s";
            int gemstoneBuff = GemstoneBuffs.getRegenModifier(gemstones);
            if (gemstoneBuff > 0) {
                regenTooltip += " §e(+" + gemstoneBuff + "/5s)";
            }
            pTooltipComponents.add(Component.literal(regenTooltip));
        }
        if (this.mana > 0 || GemstoneBuffs.getManaModifier(gemstones) > 0) {
            String manaTooltip = "§7Mana Bonus: §1" + this.mana;
            int gemstoneBuff = GemstoneBuffs.getManaModifier(gemstones);
            if (gemstoneBuff > 0) {
                manaTooltip += " §e(+" + gemstoneBuff + ")";
            }
            pTooltipComponents.add(Component.literal(manaTooltip));
        }
        if (this.manaRegen > 0 || GemstoneBuffs.getManaRegenModifier(gemstones) > 0) {
            String regenTooltip = "§7Mana Regen: §1" + this.manaRegen + "/5s";
            int gemstoneBuff = GemstoneBuffs.getManaRegenModifier(gemstones);
            if (gemstoneBuff > 0) {
                regenTooltip += " §e(+" + gemstoneBuff + "/5s)";
            }
            pTooltipComponents.add(Component.literal(regenTooltip));
        }
        if (this.speed > 0 || GemstoneBuffs.getSpeedModifier(gemstones) > 0) {
            String speedTooltip = "§7Speed Bonus: §f" + this.speed;
            int gemstoneBuff = GemstoneBuffs.getSpeedModifier(gemstones);
            if (gemstoneBuff > 0) {
                speedTooltip +=  " §e(+" + GemstoneBuffs.getSpeedModifier(gemstones) + ")";
            }
            pTooltipComponents.add(Component.literal(speedTooltip));
        }
        if (this.defence > 0 || GemstoneBuffs.getDefenceModifier(gemstones) > 0) {
            String defenceTooltip = "§7Defence: §2" + this.defence;
            int gemstoneBuff = GemstoneBuffs.getDefenceModifier(gemstones);
            if (gemstoneBuff > 0) {
                defenceTooltip += " §e(+" + GemstoneBuffs.getDefenceModifier(gemstones) + ")";
            }
            pTooltipComponents.add(Component.literal(defenceTooltip));
        }

        pTooltipComponents.add(Component.literal("§7Gemstones:"));
        pTooltipComponents.add(Component.literal(" " + getGemstoneTooltip(gemstones[0], gemstones[1]) + getGemstoneTooltip(gemstones[3], gemstones[4])));

        if (this.shortBow) {
            pTooltipComponents.add(Component.literal(""));
            pTooltipComponents.add(Component.literal("§9Shortbow! Right click to instantly shoot!"));
            pTooltipComponents.add(Component.literal(""));
        }
    }

    private void fireArrows(Player player, ItemStack pStack, Level pLevel, int pTimeLeft) {
        boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, pStack) > 0;
        ItemStack itemstack = player.getProjectile(pStack);

        int i = this.getUseDuration(pStack) - pTimeLeft;
        i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, !itemstack.isEmpty() || flag);
        if (i < 0) return;

        if (!itemstack.isEmpty() || flag) {
            if (itemstack.isEmpty()) {
                itemstack = new ItemStack(Items.ARROW);
            }

            float f = getPowerForTime(i);

            if (!((double)f < 0.1D)) {
                boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, pStack, player));
                if (!pLevel.isClientSide) {
                    float arrowIndex = (float) (this.arrowCount - 1) / 2;
                    float arrowSpread = 10;


                    for (float a = -1 * arrowIndex; a < arrowIndex + 1; a++) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrow abstractarrow = arrowitem.createArrow(pLevel, itemstack, player);
                        abstractarrow = customArrow(abstractarrow);
                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot() + a * arrowSpread, 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrow.setCritArrow(true);
                        }

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, pStack);
                        if (j > 0) {
                            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, pStack);
                        if (k > 0) {
                            abstractarrow.setKnockback(k);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, pStack) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }

                        pStack.hurtAndBreak(1, player, (p_289501_) -> {
                            p_289501_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }
                        pLevel.addFreshEntity(abstractarrow);
                    }
                }

                pLevel.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                if (!flag1 && !player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.getInventory().removeItem(itemstack);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    private void putNbt (ItemStack pStack) {
        if (pStack.getTag() == null) {
            pStack.getOrCreateTag();
        }
        if (!pStack.getTag().contains("gemstones")) {
            int[] ints = {0,0,0,0,0,0};
            pStack.getTag().putIntArray("gemstones", ints);
        }

        if (!pStack.getTag().contains("cooldowns")) {
            int[] cooldowns = {0, 0};
            pStack.getTag().putIntArray("cooldowns", cooldowns);
        }

        if (!pStack.getTag().contains("manaBonusApplied")) {
            pStack.getTag().putBoolean("manaBonusApplied", false);
        }
    }

    private String getGemstoneTooltip(int index, int type) {
        String symbol = "";
        String col = "§8";
        String rarity = "§8";

        switch (type) {
            case 0 -> {
                col = "§a";
            }
            case 1 -> {
                col = "§c";
            }
            case 2 -> {
                col = "§9";
            }
            case 3 -> {
                col = "§e";
            }
        }

        if (index % 2 == 0 && index > 0) {
            symbol = "■";
        } else if (index == 9) {
            symbol = "◯";
            col = "§6";
        } else if (index > 0){
            symbol = "◆";
        }

        if (index == 9) {
            rarity = "§6";
        } else if (index > 6) {
            rarity = "§5";
        } else if (index > 4) {
            rarity = "§1";
        } else if (index > 2) {
            rarity = "§2";
        } else if (index > 0) {
            rarity = "§f";
        }


        return rarity + "[§r" + col + symbol + "§r" + rarity + "]§r ";

    }

    private void applyHealthBonus(UUID uuid, Player player, int[] gemstones, int slotIndex, int selectedIndex) {
        AttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);

        if (slotIndex == selectedIndex) {
            if (health.getModifier(uuid) == null) {
                int buffAmount = this.health + GemstoneBuffs.getHealthModifier(gemstones);
                health.addTransientModifier(new AttributeModifier(uuid, "weapon_health_bonus", buffAmount, AttributeModifier.Operation.ADDITION));
            }
        } else {
            AttributeModifier modifier = health.getModifier(uuid);
            if (modifier != null) {
                health.removeModifier(uuid);
            }
        }
    }

    private void applySpeedBonus(UUID uuid, Player player, int[] gemstones, int slotIndex, int selectedIndex) {
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (slotIndex == selectedIndex) {
            if (speed.getModifier(uuid) == null) {
                int buffAmount = this.speed + GemstoneBuffs.getSpeedModifier(gemstones);
                speed.addTransientModifier(new AttributeModifier(uuid, "weapon_speed_bonus", (double) buffAmount / 1000, AttributeModifier.Operation.ADDITION));
            }
        } else {
            AttributeModifier modifier = speed.getModifier(uuid);
            if (modifier != null) {
                speed.removeModifier(uuid);
            }
        }
    }

    private void applyManaBonus(UUID uuid, Player player, int[] gemstones, int slotIndex, int selectedIndex) {
        AttributeInstance mana = player.getAttribute(ModAttributes.MAX_MANA.get());

        if (slotIndex == selectedIndex) {
            if (mana.getModifier(uuid) == null) {
                int buffAmount = this.mana + GemstoneBuffs.getManaModifier(gemstones);
                mana.addTransientModifier(new AttributeModifier(uuid, "weapon_mana_bonus", buffAmount, AttributeModifier.Operation.ADDITION));
            }
        } else {
            AttributeModifier modifier = mana.getModifier(uuid);
            if (modifier != null) {
                mana.removeModifier(uuid);
            }
        }
    }

    public float getAttackDamage () {
        return this.attackDamage;
    }

    public int getStrength () {
        return this.strength;
    }

    public int getCritDamage () {
        return this.critDamage;
    }

    public int getCritChance () {
        return this.critChance;
    }

    public int getElementalDamage () {
        return this.elementalDamage;
    }

    public int getLifeSteal () {
        return this.lifeSteal;
    }

    public int getAttackSpeed () {
        return this.attackSpeed;
    }
}
