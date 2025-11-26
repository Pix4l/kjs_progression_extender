package net.kj.kjs_progression_extender.util;

import net.kj.kjs_progression_extender.item.types.ModArmorItem;
import net.kj.kjs_progression_extender.item.types.ModBowItem;
import net.kj.kjs_progression_extender.item.types.ModWeaponItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GemstoneBuffs {
    public static int singularityMult = 6;
    //Jade
    public static int healthPerLevel = 5;
    public static int regenPerLevel = 5;
    public static int defencePerLevel = 5;
    //Ruby
    public static int strengthPerLevel =  10;
    public static int critDamagePerLevel = 10;
    public static int lifeStealPerLevel = 1;
    //Sapphire
    public static int manaPerLevel = 5;
    public static int manaRegenPerLevel = 5;
    public static int elementalDamagePerLevel = 20;
    //Topaz
    public static int critChancePerLevel = 4;
    public static int attackSpeedPerLevel = 3;
    public static int speedPerLevel = 3;



    //Jade
    public static int getHealthModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 0 && gemstones[i - 1] != 9 && gemstones[i + 1] == 1) {
                gemstoneBuff += gemstones[i - 1] * healthPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 1) {
            gemstoneBuff += healthPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 1) {
            gemstoneBuff += healthPerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getRegenModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 0 && gemstones[i - 1] != 9 && gemstones[i + 1] == 2) {
                gemstoneBuff += gemstones[i - 1] * regenPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones [2] == 1) {
            gemstoneBuff += regenPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones [5] == 1) {
            gemstoneBuff += regenPerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getDefenceModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 0 && gemstones[i - 1] != 9 && gemstones[i + 1] == 3) {
                gemstoneBuff += gemstones[i - 1] * defencePerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 1) {
            gemstoneBuff += defencePerLevel * 6;
        }
        if (gemstones[3] == 9 && gemstones[5] == 1) {
            gemstoneBuff += defencePerLevel * 6;
        }

        return gemstoneBuff;
    }
    //Ruby
    public static int getStrengthModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 1 && gemstones[i - 1] != 9 && gemstones[i + 1] == 1) {
                gemstoneBuff += gemstones[i - 1] * strengthPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 2) {
            gemstoneBuff += strengthPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 2) {
            gemstoneBuff += strengthPerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getCritDamageModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 1 && gemstones[i - 1] != 9 && gemstones[i + 1] == 2) {
                gemstoneBuff += gemstones[i - 1] * critDamagePerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 2) {
            gemstoneBuff += critDamagePerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 2) {
            gemstoneBuff += critDamagePerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getLifeStealModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 1 && gemstones[i - 1] != 9  && gemstones[i + 1] == 3) {
                gemstoneBuff += gemstones[i - 1] * lifeStealPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 2) {
            gemstoneBuff += lifeStealPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 2) {
            gemstoneBuff += lifeStealPerLevel * singularityMult;
        }

        return gemstoneBuff;
    }
    //Sapphire
    public static int getManaModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 2 && gemstones[i - 1] != 9 && gemstones[i + 1] == 1) {
                gemstoneBuff += gemstones[i - 1] * manaPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 3) {
            gemstoneBuff += manaPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 3) {
            gemstoneBuff += manaPerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getManaRegenModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 2 && gemstones[i - 1] != 9 && gemstones[i + 1] == 2) {
                gemstoneBuff += gemstones[i - 1] * manaRegenPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 3) {
            gemstoneBuff += manaRegenPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 3) {
            gemstoneBuff += manaRegenPerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getElementalDamageModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 2 && gemstones[i - 1] != 9 && gemstones[i + 1] == 3) {
                gemstoneBuff += gemstones[i - 1] * elementalDamagePerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 3) {
            gemstoneBuff += elementalDamagePerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 3) {
            gemstoneBuff += elementalDamagePerLevel * singularityMult;
        }

        return gemstoneBuff;
    }
    //Topaz
    public static int getCritChanceModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 3 && gemstones[i - 1] != 9 && gemstones[i + 1] == 1) {
                gemstoneBuff += gemstones[i - 1] * critChancePerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 4) {
            gemstoneBuff += critChancePerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 4) {
            gemstoneBuff += critChancePerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getAttackSpeedModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 3 && gemstones[i - 1] != 9 && gemstones[i + 1] == 2) {
                gemstoneBuff += gemstones[i - 1] * attackSpeedPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 4) {
            gemstoneBuff += attackSpeedPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 4) {
            gemstoneBuff += attackSpeedPerLevel * singularityMult;
        }

        return gemstoneBuff;
    }

    public static int getSpeedModifier(int[] gemstones) {
        int gemstoneBuff = 0;
        for (int i = 1; i < 6; i += 3) {
            if (gemstones[i] == 3 && gemstones[i - 1] != 9 && gemstones[i + 1] == 3) {
                gemstoneBuff += gemstones[i - 1] * speedPerLevel;
            }
        }

        if (gemstones[0] == 9 && gemstones[2] == 4) {
            gemstoneBuff += speedPerLevel * singularityMult;
        }
        if (gemstones[3] == 9 && gemstones[5] == 4) {
            gemstoneBuff += speedPerLevel * singularityMult;
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
