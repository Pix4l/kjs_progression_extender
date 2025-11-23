package net.kj.kjs_progression_extender.util;

import net.kj.kjs_progression_extender.item.types.ModArmorItem;
import net.kj.kjs_progression_extender.item.types.ModBowItem;
import net.kj.kjs_progression_extender.item.types.ModWeaponItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GemstoneBuffs {
    public static int getStrengthModifier(int[] gemstones) {
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

    public static int getCritDamageModifier(int[] gemstones) {
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

    public static int getCritChanceModifier(int[] gemstones) {
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

    public static int getElementalDamageModifier(int[] gemstones) {
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

    public static int getLifeStealModifier(int[] gemstones) {
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

    public static int getAttackSpeedModifier(int[] gemstones) {
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

    public static int getHealthModifier(int[] gemstones) {
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

    public static int getManaModifier(int[] gemstones) {
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

    public static int getSpeedModifier(int[] gemstones) {
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

    public static int getDefenceModifier(int[] gemstones) {
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

    public static int getRegenModifier(int[] gemstones) {
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

    public static int getManaRegenModifier(int[] gemstones) {
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

    public static int[] getGemstones (ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof ModArmorItem || item instanceof ModWeaponItem || item instanceof ModBowItem) {
            return stack.getTag().getIntArray("gemstones");
        }
        return null;
    }

    public static double[] calcElementalDamage (ItemStack stack) {
        int[] gemstones = getGemstones(stack);
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

        return elementalDamage;
    }
}
