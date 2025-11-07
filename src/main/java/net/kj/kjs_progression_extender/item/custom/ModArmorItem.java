package net.kj.kjs_progression_extender.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.kj.kjs_progression_extender.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ModArmorItem extends ArmorItem {
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

    UUID ARMOR_HEALTH_BONUS_ID = UUID.randomUUID();
    UUID ARMOR_SPEED_BONUS_ID = UUID.randomUUID();

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, int attackSpeed, int strength, int critDamage, int critChance, int elementalDamage, int lifeSteal, int health, int healthRegen, int mana, int manaRegen, int defence, int speed) {
        super(pMaterial, pType, pProperties);
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

        applyHealthBonus(ARMOR_HEALTH_BONUS_ID, player, gemstones, slotIndex);

        applySpeedBonus(ARMOR_SPEED_BONUS_ID, player, gemstones, slotIndex);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        putNbt(pStack);

        int[] gemstones = pStack.getTag().getIntArray("gemstones");

        if (this.defence > 0 || getGemstoneDefenceModifier(gemstones) > 0) {
            String defenceTooltip = "§7Defence: §2" + String.valueOf(this.defence);
            int gemstoneBuff = getGemstoneDefenceModifier(gemstones);
            if (gemstoneBuff > 0) {
                defenceTooltip += " §e(+" + String.valueOf(getGemstoneDefenceModifier(gemstones)) + ")";
            }
            pTooltipComponents.add(Component.literal(defenceTooltip));
        }
        if (this.health > 0 || getGemstoneHealthModifier(gemstones) > 0) {
            String healthTooltip = "§7Health Bonus: §c" + String.valueOf(this.health);
            int gemstoneBuff = getGemstoneHealthModifier(gemstones);
            if (gemstoneBuff > 0) {
                healthTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(healthTooltip));
        }
        if (this.healthRegen > 0 || getGemstoneRegenModifier(gemstones) > 0) {
            String regenTooltip = "§7Health Regen: §c" + String.valueOf(this.healthRegen) + "/5s";
            int gemstoneBuff = getGemstoneRegenModifier(gemstones);
            if (gemstoneBuff > 0) {
                regenTooltip += " §e(+" + String.valueOf(gemstoneBuff) + "/5s)";
            }
            pTooltipComponents.add(Component.literal(regenTooltip));
        }
        if (this.mana > 0 || getGemstoneManaModifier(gemstones) > 0) {
            String manaTooltip = "§7Mana Bonus: §1" + String.valueOf(this.mana);
            int gemstoneBuff = getGemstoneManaModifier(gemstones);
            if (gemstoneBuff > 0) {
                manaTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(manaTooltip));
        }
        if (this.manaRegen > 0 || getGemstoneManaRegenModifier(gemstones) > 0) {
            String regenTooltip = "§7Mana Regen: §1" + String.valueOf(this.manaRegen) + "/5s";
            int gemstoneBuff = getGemstoneManaRegenModifier(gemstones);
            if (gemstoneBuff > 0) {
                regenTooltip += " §e(+" + String.valueOf(gemstoneBuff) + "/5s)";
            }
            pTooltipComponents.add(Component.literal(regenTooltip));
        }
        if (this.strength > 0 || getGemstoneStrengthModifier(gemstones) > 0) {
            String strengthTooltip = "§7Strength: §c+" + String.valueOf(this.strength);
            int gemstoneBuff = getGemstoneStrengthModifier(gemstones);
            if (gemstoneBuff > 0) {
                strengthTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(strengthTooltip));
        }
        if (this.critDamage > 0 || getGemstoneCritDamageModifier(gemstones) > 0) {
            String critDamageTooltip = "§7Crit Damage: §3+" + String.valueOf(this.critDamage);
            int gemstoneBuff = getGemstoneCritDamageModifier(gemstones);
            if (gemstoneBuff > 0) {
                critDamageTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(critDamageTooltip));
        }
        if (this.critChance > 0 || getGemstoneCritChanceModifier(gemstones) > 0) {
            String critChanceTooltip = "§7Crit Chance: §3+" + String.valueOf(this.critChance);
            int gemstoneBuff = getGemstoneCritChanceModifier(gemstones);
            if (gemstoneBuff > 0) {
                critChanceTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(critChanceTooltip));
        }
        if (this.elementalDamage > 0 || getGemstoneElementalDamageModifier(gemstones) > 0) {
            String elementalDamageTooltip = "§7Elemental Damage: §2+" + String.valueOf(this.elementalDamage);
            int gemstoneBuff = getGemstoneElementalDamageModifier(gemstones);
            if (gemstoneBuff > 0) {
                elementalDamageTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(elementalDamageTooltip));
        }
        if (this.lifeSteal > 0 || getGemstoneLifeStealModifier(gemstones) > 0) {
            String lifeStealTooltip = "§7Life Steal: §4+" + String.valueOf(this.lifeSteal);
            int gemstoneBuff = getGemstoneLifeStealModifier(gemstones);
            if (gemstoneBuff > 0) {
                lifeStealTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(lifeStealTooltip));
        }
        if (this.attackSpeed > 0 || getGemstoneAttackSpeedModifier(gemstones) > 0) {
            String attackSpeedTooltip = "§7Attack Speed: §5+" + String.format("%.1f", 20 / ((double) this.attackSpeed));
            int gemstoneBuff = getGemstoneAttackSpeedModifier(gemstones);
            if (gemstoneBuff > 0) {
                attackSpeedTooltip += " §e(+" + String.valueOf(gemstoneBuff) + ")";
            }
            pTooltipComponents.add(Component.literal(attackSpeedTooltip));
        }
        if (this.speed > 0 || getGemstoneSpeedModifier(gemstones) > 0) {
            String speedTooltip = "§7Speed Bonus: §f" + String.valueOf(this.speed);
            int gemstoneBuff = getGemstoneSpeedModifier(gemstones);
            if (gemstoneBuff > 0) {
                speedTooltip +=  " §e(+" + String.valueOf(getGemstoneSpeedModifier(gemstones)) + ")";
            }
            pTooltipComponents.add(Component.literal(speedTooltip));
        }




        pTooltipComponents.add(Component.literal("§7Gemstones:"));
        pTooltipComponents.add(Component.literal(" " + getGemstoneTooltip(gemstones[0], gemstones[1]) + getGemstoneTooltip(gemstones[3], gemstones[4])));
    }


    private void putNbt (ItemStack pStack) {
        if (!pStack.getTag().contains("gemstones")) {
            int[] ints = {0,0,0,0,0,0};
            pStack.getTag().putIntArray("gemstones", ints);
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

    private void applyHealthBonus(UUID uuid, Player player, int[] gemstones, int slotIndex) {
        AttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);

        if (slotIndex == 39 - this.type.ordinal()) {
            if (health.getModifier(ARMOR_HEALTH_BONUS_ID) == null) {
                int buffAmount = this.health + getGemstoneHealthModifier(gemstones);
                health.addTransientModifier(new AttributeModifier(ARMOR_HEALTH_BONUS_ID, "weapon_health_bonus", buffAmount, AttributeModifier.Operation.ADDITION));
            }
        } else {
            AttributeModifier modifier = health.getModifier(ARMOR_HEALTH_BONUS_ID);
            if (modifier != null) {
                health.removeModifier(ARMOR_HEALTH_BONUS_ID);
            }
        }
    }

    private void applySpeedBonus(UUID uuid, Player player, int[] gemstones, int slotIndex) {
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (slotIndex == 39 - this.type.ordinal()) {
            if (speed.getModifier(ARMOR_SPEED_BONUS_ID) == null) {
                int buffAmount = this.speed + getGemstoneSpeedModifier(gemstones);
                speed.addTransientModifier(new AttributeModifier(ARMOR_SPEED_BONUS_ID, "weapon_speed_bonus", (double) buffAmount / 1000, AttributeModifier.Operation.ADDITION));
            }
        } else {
            AttributeModifier modifier = speed.getModifier(ARMOR_SPEED_BONUS_ID);
            if (modifier != null) {
                speed.removeModifier(ARMOR_SPEED_BONUS_ID);
            }
        }
    }

    public int getGemstoneStrengthModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 1 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 10;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 65;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 65;
        }

        return gemstoneBuff;
    }

    public int getGemstoneCritDamageModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 1 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 10;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 65;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 65;
        }

        return gemstoneBuff;
    }

    public int getGemstoneCritChanceModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 3 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 4;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 25;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 25;
        }

        return gemstoneBuff;
    }

    public int getGemstoneElementalDamageModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 2 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 20;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 125;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 125;
        }

        return gemstoneBuff;
    }

    public int getGemstoneLifeStealModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 1 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1];
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 5;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 5;
        }

        return gemstoneBuff;
    }

    public int getGemstoneAttackSpeedModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 3 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 3;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 20;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 20;
        }

        return gemstoneBuff;
    }

    public int getGemstoneHealthModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 0 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 5;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 30;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 30;
        }

        return gemstoneBuff;
    }

    public int getGemstoneManaModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 2 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 5;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 30;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 30;
        }

        return gemstoneBuff;
    }

    public int getGemstoneSpeedModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 3 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 3;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 20;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 20;
        }

        return gemstoneBuff;
    }

    public int getGemstoneDefenceModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 0 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 10;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 55;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 55;
        }

        return gemstoneBuff;
    }

    public int getGemstoneRegenModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 0 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 5;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 30;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 30;
        }

        return gemstoneBuff;
    }

    public int getGemstoneManaRegenModifier (int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 2 && gemstones[i - 1] != 9) {
                gemstoneBuff += gemstones[i - 1] * 5;
            }
        }

        if (gemstones[0] == 9) {
            gemstoneBuff += 30;
        }
        if (gemstones[3] == 9) {
            gemstoneBuff += 30;
        }

        return gemstoneBuff;
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
