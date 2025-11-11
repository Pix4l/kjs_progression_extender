package net.kj.kjs_progression_extender.item.custom;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.ModItems;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    AMALGAMITE("amalgamite", 26, new int[]{5, 7, 5, 4}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.CONCENTRATED_AMALGAMITE.get())),
    DRAKESTEEL("drakesteel", 42, new int[]{5, 7, 5, 4}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.DRAKE_HIDE.get())),
    TERRALITE("terralite", 56, new int[]{5, 7, 5, 4}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.EARTH_ESSENCE.get())),
    PYROLITE("pyrolite", 56, new int[]{5, 7, 5, 4}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.FIRE_ESSENCE.get())),
    HYDROLITE("hydrolite", 56, new int[]{5, 7, 5, 4}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.WATER_ESSENCE.get())),
    FULGURITE("fulgurite", 56, new int[]{5, 7, 5, 4}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.LIGHTNING_ESSENCE.get())),
    VOIDBLIGHT("voidblight", 61, new int[]{5, 7, 5, 4}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.DARK_ESSENCE.get())),
    GLEAMSTONE("gleamstone", 61, new int[]{5, 7, 5, 4}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.LIGHT_ESSENCE.get())),
    STARCAST("starcast", 75, new int[]{5, 7, 5, 4}, 30, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f, () -> Ingredient.of(ModItems.COSMITE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return KJsProgressionExtender.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
