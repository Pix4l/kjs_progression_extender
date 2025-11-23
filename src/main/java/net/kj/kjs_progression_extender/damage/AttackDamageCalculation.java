package net.kj.kjs_progression_extender.damage;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.types.ModArmorItem;
import net.kj.kjs_progression_extender.item.types.ModBowItem;
import net.kj.kjs_progression_extender.item.types.ModWeaponItem;
import net.kj.kjs_progression_extender.util.GemstoneBuffs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.Display.TextDisplay;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KJsProgressionExtender.MOD_ID)
public class AttackDamageCalculation {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!event.getEntity().getCommandSenderWorld().isClientSide && event.getSource().getEntity() instanceof Player player) {
            LivingEntity target = event.getEntity();
            CompoundTag targetTag = target.getPersistentData();
            CompoundTag playerTag = player.getPersistentData();

            placeNbt(targetTag, playerTag);
            int[] resistances = targetTag.getIntArray("resistances");
            int[] attunement = playerTag.getIntArray("attunement");

            double base = event.getAmount();

            double critDamage = 1;
            double critChance = 0.1;
            double strengthModifier = 1;
            double elementalDamageModifier = 1;
            double[] elementalDamage = {0,0,0,0};
            double lifeSteal = 0;
            double cooldown = 0;
            double cooldownReduction = 0;

            double[] attunementMultiplier = calcAttunementMultiplier(attunement);

            //Apply Armor Bonuses
            for (int i = 0; i < 4; i++) {
                if (player.getInventory().getArmor(i).getItem() instanceof ModArmorItem armorItem) {
                    int[] gemstones = player.getInventory().getArmor(i).getTag().getIntArray("gemstones");

                    critDamage += (double) (armorItem.getCritDamage() + GemstoneBuffs.getCritDamageModifier(gemstones)) / 100;
                    critChance += (double) (armorItem.getCritChance() + GemstoneBuffs.getCritChanceModifier(gemstones)) / 100;
                    strengthModifier += (double) (armorItem.getStrength() + GemstoneBuffs.getStrengthModifier(gemstones)) / 100;
                    elementalDamageModifier += (double) (armorItem.getElementalDamage() + GemstoneBuffs.getElementalDamageModifier(gemstones)) / 100;
                    lifeSteal += (double) (armorItem.getLifeSteal() + GemstoneBuffs.getLifeStealModifier(gemstones)) / 100;
                    cooldownReduction += armorItem.getAttackSpeed() + GemstoneBuffs.getAttackSpeedModifier(gemstones);
                    double[] itemElementalDamage = GemstoneBuffs.calcElementalDamage(player.getInventory().getArmor(i));
                    for (int j = 0; j < 4; j++) {
                        elementalDamage[j] += itemElementalDamage[j];
                    }
                }
            }

            //Apply Weapon Bonuses
            if (player.getMainHandItem().getItem() instanceof ModWeaponItem weaponItem) {
                int[] cooldowns = player.getMainHandItem().getTag().getIntArray("cooldowns");

                if (cooldowns[0] > 0) {
                    event.setCanceled(true);
                    return;
                }

                base = weaponItem.getAttackDamage();

                int[] gemstones = player.getMainHandItem().getTag().getIntArray("gemstones");

                critDamage += (double) (weaponItem.getCritDamage() + GemstoneBuffs.getCritDamageModifier(gemstones)) / 100;
                critChance += (double) (weaponItem.getCritChance() + GemstoneBuffs.getCritChanceModifier(gemstones)) / 100;
                strengthModifier += (double) (weaponItem.getStrength() + GemstoneBuffs.getStrengthModifier(gemstones)) / 100;
                elementalDamageModifier += (double) (weaponItem.getElementalDamage() + GemstoneBuffs.getElementalDamageModifier(gemstones)) / 100;
                lifeSteal += (double) (weaponItem.getLifeSteal() + GemstoneBuffs.getLifeStealModifier(gemstones)) / 100;
                cooldownReduction += weaponItem.getAttackSpeed() + GemstoneBuffs.getAttackSpeedModifier(gemstones);
                double[] itemElementalDamage = GemstoneBuffs.calcElementalDamage(player.getMainHandItem());
                for (int i = 0; i < 4; i++) {
                    elementalDamage[i] += itemElementalDamage[i];
                }

                double smiteLevel = 0;
                double baneLevel = 0;
                double sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, player);

                if (target.getMobType() == MobType.UNDEAD) {
                    smiteLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, player);
                }

                if (target.getMobType() == MobType.ARTHROPOD) {
                    baneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, player);
                }

                base *= 1 + ((sharpnessLevel + baneLevel + smiteLevel) / 10);

                cooldown = Math.floor(weaponItem.getAttackSpeed() * Math.pow(Math.E, cooldownReduction / -84));
                cooldowns[0] = ((int) cooldown);
            }

            //Apply Bow Bonuses
            if (player.getMainHandItem().getItem() instanceof ModBowItem bowItem) {
                base = bowItem.getAttackDamage();

                int[] gemstones = player.getMainHandItem().getTag().getIntArray("gemstones");

                critDamage += (double) (bowItem.getCritDamage() + GemstoneBuffs.getCritDamageModifier(gemstones)) / 100;
                critChance += (double) (bowItem.getCritChance() + GemstoneBuffs.getCritChanceModifier(gemstones)) / 100;
                strengthModifier += (double) (bowItem.getStrength() + GemstoneBuffs.getStrengthModifier(gemstones)) / 100;
                elementalDamageModifier += (double) (bowItem.getElementalDamage() + GemstoneBuffs.getElementalDamageModifier(gemstones)) / 100;
                lifeSteal += (double) (bowItem.getLifeSteal() + GemstoneBuffs.getLifeStealModifier(gemstones)) / 100;
                double[] itemElementalDamage = GemstoneBuffs.calcElementalDamage(player.getMainHandItem());
                for (int i = 0; i < 4; i++) {
                    elementalDamage[i] += itemElementalDamage[i];
                }
            }



            //Calculations

            base *= strengthModifier;

            if (Math.random() < critChance) {
                base *= critDamage;
            }

            for (int i = 0; i < 4; i++) {
                elementalDamage[i] = elementalDamage[i] * elementalDamageModifier * attunementMultiplier[i];
            }

            spawnDamageText(target, base, elementalDamage);

            for (int i = 0; i < 4; i++) {
                base += elementalDamage[i];
            }

            double healAmount = lifeSteal * base;
            player.heal(((float) healAmount));

            event.setAmount((float) base);
        }
    }

    private static void placeNbt (CompoundTag targetTag, CompoundTag playerTag) {
        if (!targetTag.contains("resistances")) {
            int[] resistances = {0,0,0,0};
            targetTag.putIntArray("resistances", resistances);
        }

        if (!playerTag.contains("attunement")) {
            int[] attunement = {0,0,0,0,0,0};
            playerTag.putIntArray("attunement", attunement);
        }
    }

    private static double[] calcAttunementMultiplier (int[] attunement) {
        double[] multiplier = {1,1,1,1};
        for (int i = 0; i < 4; i++) {
            multiplier[i] = 1 + Math.pow((double) attunement[i] / 100, 0.33);
            multiplier[i] *= 1 + Math.log(1.0 + Math.sqrt((double) (attunement[4] * attunement[5]) / (1 + attunement[4] + attunement[5])));
        }
        return multiplier;
    }

    private static void spawnDamageText(Entity target, double damage, double[] elementalDamage) {
        Level level = target.level();
        DamageTextDisplay display = new DamageTextDisplay(EntityType.TEXT_DISPLAY, level, 30);
        display.moveTo(new Vec3(target.getX() + Math.random() / 3, target.getY() + 1 + Math.random() / 3, target.getZ() + Math.random() / 3));
        display.setBillboardConstraints(Display.BillboardConstraints.CENTER);

        String damageText = String.format("%.0f", damage);

        if (elementalDamage[0] > 0) {
            damageText = damageText + " §2" + String.format("%.0f", elementalDamage[0]) + "§r";
        }
        if (elementalDamage[1] > 0) {
            damageText = damageText + " §4" + String.format("%.0f", elementalDamage[1]) + "§r";
        }
        if (elementalDamage[2] > 0) {
            damageText = damageText + " §9" + String.format("%.0f", elementalDamage[2]) + "§r";
        }
        if (elementalDamage[3] > 0) {
            damageText = damageText + " §e" + String.format("%.0f", elementalDamage[3]) + "§r";
        }

        display.getEntityData().set(TextDisplay.DATA_TEXT_ID, Component.literal(damageText));

        level.addFreshEntity(display);
    }

}