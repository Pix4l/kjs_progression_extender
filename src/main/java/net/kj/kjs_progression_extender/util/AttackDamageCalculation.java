package net.kj.kjs_progression_extender.util;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
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
            CompoundTag playerItemTag = player.getMainHandItem().getTag();

            double base = event.getAmount();

            if (!(playerItemTag == null) && player.getMainHandItem().getItem() instanceof ModWeaponItem weaponItem) {
                placeNbt(targetTag, playerItemTag);

                int[] cooldowns = player.getMainHandItem().getTag().getIntArray("cooldowns");

                if (cooldowns[0] > 0) {
                    event.setCanceled(true);
                    return;
                }

                base = weaponItem.getAttackDamage();

                int[] resistances = targetTag.getIntArray("resistances");
                int[] gemstones = playerItemTag.getIntArray("gemstones");
                double[] elementalDamage = calcGemElementalDamage(weaponItem, resistances, gemstones);

                double critDamage = getCritDamage(weaponItem, gemstones);
                double critChance = getCritChance(weaponItem, gemstones);
                double strengthModifier = getStrengthModifier(weaponItem, gemstones);
                double elementalDamageModifier = getElementalDamageModifier(weaponItem, gemstones);
                double lifeSteal = getLifeSteal(weaponItem, gemstones);
                double finalDamage = 0;

                finalDamage = base;

                if (Math.random() < critChance) {
                    finalDamage *= critDamage;
                }

                finalDamage *= strengthModifier;

                spawnDamageText(target, finalDamage, elementalDamage, elementalDamageModifier);

                for (int i = 0; i < 4; i++) {
                    finalDamage += elementalDamage[i] * elementalDamageModifier;
                }

                player.heal((float) (finalDamage * lifeSteal));

                cooldowns[0] = ((int) getAttackSpeed(weaponItem, gemstones));

                event.setAmount(0);
                return;
            }

            spawnDamageText(target, base, new double[] {0,0,0,0}, 0);
        }
    }

    private static void placeNbt (CompoundTag targetTag, CompoundTag playerTag) {
        if (!targetTag.contains("resistances")) {
            int[] resistances = {0,0,0,0};
            targetTag.putIntArray("resistances", resistances);
        }

        if (!playerTag.contains("gemstones")) {
            int[] gems = {0,0,0,0,0,0};
            playerTag.putIntArray("gemstones", gems);
        }

        if (!playerTag.contains("cooldowns")) {
            int[] gems = {0,0};
            playerTag.putIntArray("cooldowns", gems);
        }
    }

    private static double[] calcGemElementalDamage (ModWeaponItem weaponItem, int[] resistances, int[] gemstones) {
        double[] elementalDamage = {0,0,0,0};
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

        return 1 + weaponBuff + gemstoneBuff;
    }

    private static double getCritChance (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getCritChance() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneCritChanceModifier(gemstones)) / 100;

        return gemstoneBuff + weaponBuff + 0.1;
    }

    private static double getCritDamage (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getCritDamage() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneCritDamageModifier(gemstones)) / 100;

        return 1 + gemstoneBuff + weaponBuff;
    }

    private static double getElementalDamageModifier (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getElementalDamage() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneElementalDamageModifier(gemstones)) / 100;

        return 1 + gemstoneBuff + weaponBuff;
    }

    private static double getLifeSteal (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = (double) weaponItem.getLifeSteal() / 100;

        double gemstoneBuff = ((double) weaponItem.getGemstoneLifeStealModifier(gemstones)) / 100;

        return weaponBuff + gemstoneBuff;
    }

    private static double getAttackSpeed (ModWeaponItem weaponItem, int[] gemstones) {
        double weaponBuff = ((double) weaponItem.getAttackSpeed());

        double gemstoneBuff = ((double) weaponItem.getGemstoneAttackSpeedModifier(gemstones));

        return Math.floor(weaponBuff * Math.pow(Math.E, gemstoneBuff / -84));
    }

    private static void spawnDamageText(Entity target, double damage, double[] elementalDamage, double elementalDamageModifier) {
        Level level = target.level();
        DamageTextDisplay display = new DamageTextDisplay(EntityType.TEXT_DISPLAY, level, 30);
        display.moveTo(new Vec3(target.getX() + Math.random() / 3, target.getY() + 1 + Math.random() / 3, target.getZ() + Math.random() / 3));
        display.setBillboardConstraints(Display.BillboardConstraints.CENTER);

        String damageText = String.format("%.0f", damage);

        if (elementalDamage[0] > 0) {
            damageText = damageText + " §2" + String.format("%.0f", elementalDamage[0] * elementalDamageModifier) + "§r";
        }
        if (elementalDamage[1] > 0) {
            damageText = damageText + " §4" + String.format("%.0f", elementalDamage[1] * elementalDamageModifier) + "§r";
        }
        if (elementalDamage[2] > 0) {
            damageText = damageText + " §9" + String.format("%.0f", elementalDamage[2] * elementalDamageModifier) + "§r";
        }
        if (elementalDamage[3] > 0) {
            damageText = damageText + " §e" + String.format("%.0f", elementalDamage[3] * elementalDamageModifier) + "§r";
        }

        display.getEntityData().set(TextDisplay.DATA_TEXT_ID, Component.literal(damageText));

        level.addFreshEntity(display);
    }

}