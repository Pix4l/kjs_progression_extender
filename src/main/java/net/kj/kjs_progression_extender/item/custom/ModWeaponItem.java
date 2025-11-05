package net.kj.kjs_progression_extender.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ModWeaponItem extends SwordItem {
    int attackDamage;
    int attackSpeed;
    int strength;
    int critDamage;
    int critChance;
    int elementalDamage;
    int lifeSteal;

    UUID WEAPON_HEALTH_BONUS_ID = UUID.randomUUID();
    UUID WEAPON_SPEED_BONUS_ID = UUID.randomUUID();

    public ModWeaponItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int strength, int critDamage, int critChance, int elementalDamage, int lifeSteal, int attackSpeed) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.attackDamage = pAttackDamageModifier;
        this.attackSpeed = attackSpeed;
        this.strength = strength;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.elementalDamage = elementalDamage;
        this.lifeSteal = lifeSteal;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return ImmutableMultimap.of();
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
        putNbt(stack);

        applyHealthBonus(WEAPON_HEALTH_BONUS_ID, player, stack.getTag().getIntArray("gemstones"), slotIndex, selectedIndex);
        applySpeedBonus(WEAPON_HEALTH_BONUS_ID, player, stack.getTag().getIntArray("gemstones"), slotIndex, selectedIndex);

        int[] cooldowns = stack.getTag().getIntArray("cooldowns");
        for (int i = 0; i < 2; i++) {
            if (cooldowns[i] > 0) {
                cooldowns[i] --;
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        putNbt(pStack);

        int[] gemstones = pStack.getTag().getIntArray("gemstones");

        pTooltipComponents.add(Component.literal("§7Attack Damage: §c+" + String.valueOf(this.attackDamage)));

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
        if (getGemstoneHealthModifier(gemstones) > 0) {
            String healthTooltip = "§7Health Bonus: §e(+" + String.valueOf(getGemstoneHealthModifier(gemstones)) + ")";
            pTooltipComponents.add(Component.literal(healthTooltip));
        }
        if (getGemstoneSpeedModifier(gemstones) > 0) {
            String speedTooltip = "§7Speed Bonus: §e(+" + String.valueOf(getGemstoneSpeedModifier(gemstones)) + ")";
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

        if (!pStack.getTag().contains("cooldowns")) {
            int[] cooldowns = {0, 0};
            pStack.getTag().putIntArray("cooldowns", cooldowns);
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
            if (health.getModifier(WEAPON_HEALTH_BONUS_ID) == null) {
                health.addTransientModifier(new AttributeModifier(WEAPON_HEALTH_BONUS_ID, "weapon_health_bonus", getGemstoneHealthModifier(gemstones), AttributeModifier.Operation.ADDITION));
            }
        } else {
            AttributeModifier modifier = health.getModifier(WEAPON_HEALTH_BONUS_ID);
            if (modifier != null) {
                health.removeModifier(WEAPON_HEALTH_BONUS_ID);
            }
        }
    }

    private void applySpeedBonus(UUID uuid, Player player, int[] gemstones, int slotIndex, int selectedIndex) {
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (slotIndex == selectedIndex) {
            if (speed.getModifier(WEAPON_SPEED_BONUS_ID) == null) {
                speed.addTransientModifier(new AttributeModifier(WEAPON_SPEED_BONUS_ID, "weapon_speed_bonus", (double) getGemstoneSpeedModifier(gemstones) / 1000, AttributeModifier.Operation.ADDITION));
            }
        } else {
            AttributeModifier modifier = speed.getModifier(WEAPON_SPEED_BONUS_ID);
            if (modifier != null) {
                speed.removeModifier(WEAPON_SPEED_BONUS_ID);
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
