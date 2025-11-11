package net.kj.kjs_progression_extender.util;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.custom.ModArmorItem;
import net.kj.kjs_progression_extender.item.custom.ModWeaponItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Display.TextDisplay;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
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
            Entity target = event.getEntity();
            CompoundTag targetTag = target.getPersistentData();
            CompoundTag playerTag = player.getPersistentData();
            CompoundTag playerItemTag = player.getMainHandItem().getTag();

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

                    critDamage += getACritDamage(armorItem, gemstones);
                    critChance += getACritChance(armorItem, gemstones);
                    strengthModifier += getAStrengthModifier(armorItem, gemstones);
                    elementalDamageModifier += getAElementalDamageModifier(armorItem, gemstones);
                    lifeSteal += getALifeSteal(armorItem, gemstones);
                    cooldownReduction += getAAttackSpeed(armorItem, gemstones);
                    elementalDamage = calcAGemElementalDamage(armorItem, elementalDamage, resistances, gemstones);
                }
            }

            //Apply Weapon Bonuses
            if (!(playerItemTag == null) && player.getMainHandItem().getItem() instanceof ModWeaponItem weaponItem) {
                int[] cooldowns = player.getMainHandItem().getTag().getIntArray("cooldowns");

                if (cooldowns[0] > 0) {
                    event.setCanceled(true);
                    return;
                }

                base = weaponItem.getAttackDamage();

                int[] gemstones = playerItemTag.getIntArray("gemstones");

                critDamage += getCritDamage(weaponItem, gemstones);
                critChance += getCritChance(weaponItem, gemstones);
                strengthModifier += getStrengthModifier(weaponItem, gemstones);
                elementalDamageModifier += getElementalDamageModifier(weaponItem, gemstones);
                lifeSteal += getLifeSteal(weaponItem, gemstones);
                cooldownReduction += getAttackSpeed(weaponItem, gemstones);
                elementalDamage = calcGemElementalDamage(weaponItem, elementalDamage, resistances, gemstones);

                cooldown = Math.floor(weaponItem.getAttackSpeed() * Math.pow(Math.E, cooldownReduction / -84));
                cooldowns[0] = ((int) cooldown);
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

            event.setAmount(0);
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

    private static double[] calcGemElementalDamage (ModWeaponItem weaponItem, double[] elementalDamage, int[] resistances, int[] gemstones) {
        for (int i = 0; i < 4; i++) {
            if (gemstones[1] == i && gemstones[0] != 9) {
                elementalDamage[i] += gemstones[0];
            }
            if (gemstones[4] == i && gemstones[0] != 9) {
                elementalDamage[i] += gemstones[3];
            }
        }

        if (gemstones[0] == 9) {
            for (int i = 0; i < 4; i++) {
                elementalDamage[i] += 3;
            }
        }
        if (gemstones[3] == 9) {
            for (int i = 0; i < 4; i++) {
                elementalDamage[i] += 3;
            }
        }

        for (int i = 0; i < 4; i++) {
            elementalDamage[i] *= (1 - (double) resistances[i] / 100);
        }
        return elementalDamage;
    }

    private static double getStrengthModifier(ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getStrength() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneStrengthModifier(gemstones)) / 100;

        return weaponBuff + gemstoneBuff;
    }

    private static double getCritChance (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getCritChance() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneCritChanceModifier(gemstones)) / 100;

        return gemstoneBuff + weaponBuff;
    }

    private static double getCritDamage (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getCritDamage() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneCritDamageModifier(gemstones)) / 100;

        return gemstoneBuff + weaponBuff;
    }

    private static double getElementalDamageModifier (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getElementalDamage() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneElementalDamageModifier(gemstones)) / 100;

        return gemstoneBuff + weaponBuff;
    }

    private static double getLifeSteal (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getLifeSteal() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneLifeStealModifier(gemstones)) / 100;

        return weaponBuff + gemstoneBuff;
    }

    private static double getAttackSpeed (ModWeaponItem weaponItem, int[] gemstones) {
        double gemstoneBuff = ((double) weaponItem.getGemstoneAttackSpeedModifier(gemstones));

        return gemstoneBuff;
    }

    private static double[] calcAGemElementalDamage (ModArmorItem armorItem, double[] elementalDamage, int[] resistances, int[] gemstones) {
        for (int i = 0; i < 4; i++) {
            if (gemstones[1] == i && gemstones[0] != 9) {
                elementalDamage[i] += gemstones[0];
            }
            if (gemstones[4] == i && gemstones[0] != 9) {
                elementalDamage[i] += gemstones[3];
            }
        }

        if (gemstones[0] == 9) {
            for (int i = 0; i < 4; i++) {
                elementalDamage[i] += 3;
            }
        }
        if (gemstones[3] == 9) {
            for (int i = 0; i < 4; i++) {
                elementalDamage[i] += 3;
            }
        }

        for (int i = 0; i < 4; i++) {
            elementalDamage[i] *= (1 - (double) resistances[i] / 100);
        }

        return elementalDamage;
    }

    private static double getAStrengthModifier(ModArmorItem armorItem, int[] gemstones) {
        double armorBuff = (double) armorItem.getStrength() / 100;

        double gemstoneBuff = ((double) armorItem.getGemstoneStrengthModifier(gemstones)) / 100;

        return armorBuff + gemstoneBuff;
    }

    private static double getACritChance (ModArmorItem armorItem, int[] gemstones) {
        double armorBuff = (double) armorItem.getCritChance() / 100;

        double gemstoneBuff = ((double) armorItem.getGemstoneCritChanceModifier(gemstones)) / 100;

        return gemstoneBuff + armorBuff;
    }

    private static double getACritDamage (ModArmorItem armorItem, int[] gemstones) {
        double armorBuff = (double) armorItem.getCritDamage() / 100;

        double gemstoneBuff = ((double) armorItem.getGemstoneCritDamageModifier(gemstones)) / 100;

        return gemstoneBuff + armorBuff;
    }

    private static double getAElementalDamageModifier (ModArmorItem armorItem, int[] gemstones) {
        double armorBuff = (double) armorItem.getElementalDamage() / 100;

        double gemstoneBuff = ((double) armorItem.getGemstoneElementalDamageModifier(gemstones)) / 100;

        return gemstoneBuff + armorBuff;
    }

    private static double getALifeSteal (ModArmorItem armorItem, int[] gemstones) {
        double armorBuff = (double) armorItem.getLifeSteal() / 100;

        double gemstoneBuff = ((double) armorItem.getGemstoneLifeStealModifier(gemstones)) / 100;

        return armorBuff + gemstoneBuff;
    }

    private static double getAAttackSpeed (ModArmorItem armorItem, int[] gemstones) {
        double armorBuff = ((double) armorItem.getAttackSpeed());

        double gemstoneBuff = ((double) armorItem.getGemstoneAttackSpeedModifier(gemstones));

        return armorBuff + gemstoneBuff;
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