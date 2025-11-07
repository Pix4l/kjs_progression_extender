package net.kj.kjs_progression_extender.item;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.custom.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KJsProgressionExtender.MOD_ID);

    //GEMSTONES
    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJCOMMON)));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJCOMMON)));
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJCOMMON)));
    public static final RegistryObject<Item> TOPAZ = ITEMS.register("topaz",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJCOMMON)));

    public static final RegistryObject<Item> EMPOWERED_JADE = ITEMS.register("empowered_jade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> EMPOWERED_RUBY = ITEMS.register("empowered_ruby",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> EMPOWERED_SAPPHIRE = ITEMS.register("empowered_sapphire",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> EMPOWERED_TOPAZ = ITEMS.register("empowered_topaz",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));

    public static final RegistryObject<Item> HYPER_JADE = ITEMS.register("hyper_jade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> HYPER_RUBY = ITEMS.register("hyper_ruby",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> HYPER_SAPPHIRE = ITEMS.register("hyper_sapphire",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> HYPER_TOPAZ = ITEMS.register("hyper_topaz",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));

    public static final RegistryObject<Item> OMEGA_JADE = ITEMS.register("omega_jade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> OMEGA_RUBY = ITEMS.register("omega_ruby",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> OMEGA_SAPPHIRE = ITEMS.register("omega_sapphire",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> OMEGA_TOPAZ = ITEMS.register("omega_topaz",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));

    public static final RegistryObject<Item> ELEMENTAL_SINGULARITY = ITEMS.register("elemental_singularity",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJLEGENDARY)));


    //GEMSTONE CORES
    public static final RegistryObject<Item> EMPOWERED_CORE = ITEMS.register("empowered_core",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> HYPER_CORE = ITEMS.register("hyper_core",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> OMEGA_CORE = ITEMS.register("omega_core",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> STARFORGED_CORE = ITEMS.register("starforged_core",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJLEGENDARY)));


    //ORE ITEMS
    public static final RegistryObject<Item> AMALGAMITE = ITEMS.register("amalgamite",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJCOMMON)));
    public static final RegistryObject<Item> CONCENTRATED_AMALGAMITE = ITEMS.register("concentrated_amalgamite",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJCOMMON)));
    public static final RegistryObject<Item> ELEMENTITE = ITEMS.register("elementite",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> METEORITE_SHARD = ITEMS.register("meteorite_shard",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> COSMITE_DUST = ITEMS.register("cosmite_dust",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));


    //INGOT CRAFTING MATS
    public static final RegistryObject<Item> DRAKE_SCALE = ITEMS.register("drake_scale",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> DRAKE_HIDE = ITEMS.register("drake_hide",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));


    //INGOTS
    public static final RegistryObject<Item> DRAKESTEEL_INGOT = ITEMS.register("drakesteel_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> PYROLITE_INGOT = ITEMS.register("pyrolite_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> HYDROLITE_INGOT = ITEMS.register("hydrolite_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> TERRALITE_INGOT = ITEMS.register("terralite_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> FULGURITE_INGOT = ITEMS.register("fulgurite_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> VOIDBLIGHT_INGOT = ITEMS.register("voidblight_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJLEGENDARY)));
    public static final RegistryObject<Item> GLEAMSTONE_INGOT = ITEMS.register("gleamstone_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJLEGENDARY)));
    public static final RegistryObject<Item> STARCAST_INGOT = ITEMS.register("starcast_ingot",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJMYTHIC)));

    //TOOL COMPONENTS
    public static final RegistryObject<Item> SIMPLE_HANDLE = ITEMS.register("simple_handle",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> HEAVY_HANDLE = ITEMS.register("heavy_handle",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> REFINED_HANDLE = ITEMS.register("refined_handle",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> DIVINE_HANDLE = ITEMS.register("divine_handle",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJLEGENDARY)));

    public static final RegistryObject<Item> SIMPLE_BLADE = ITEMS.register("simple_blade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> HEAVY_BLADE = ITEMS.register("heavy_blade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> REFINED_BLADE = ITEMS.register("refined_blade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> DIVINE_BLADE = ITEMS.register("divine_blade",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJLEGENDARY)));

    public static final RegistryObject<Item> SIMPLE_PLATING = ITEMS.register("simple_plating",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJUNCOMMON)));
    public static final RegistryObject<Item> HEAVY_PLATING = ITEMS.register("heavy_plating",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> REFINED_PLATING = ITEMS.register("refined_plating",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJEPIC)));
    public static final RegistryObject<Item> DIVINE_PLATING = ITEMS.register("divine_plating",
            () -> new Item(new Item.Properties().rarity(ModRarities.KJLEGENDARY)));



    //TOOLS
    public static final RegistryObject<Item> AMALGAMITE_SWORD = ITEMS.register("amalgamite_sword",
            () -> new ModWeaponItem(ModToolTiers.DRAKESTEEL, 10, -2.4f, new Item.Properties().rarity(ModRarities.KJUNCOMMON), 50, 50, 10, 0, 0, 13, 0, 0, 0, 0, 0, 0));
    public static final RegistryObject<Item> DRAKESTEEL_PICKAXE = ITEMS.register("drakesteel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.DRAKESTEEL, 1, -2.8f, new Item.Properties().rarity(ModRarities.KJRARE)));
    public static final RegistryObject<Item> TEST_SWORD = ITEMS.register("test_sword",
            () -> new ModWeaponItem(ModToolTiers.DRAKESTEEL, 500, -2.4f, new Item.Properties().rarity(ModRarities.KJRARE), 500, 350, 30, 300, 5, 30, 0, 0, 0, 0, 0, 0));

    //ARMOR
    public static final RegistryObject<Item> AMALGAMITE_HELMET = ITEMS.register("amalgamite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.AMALGAMITE, ArmorItem.Type.HELMET, new Item.Properties().rarity(ModRarities.KJUNCOMMON), 0, 0, 0, 0, 0, 0, 5, 1, 5, 0, 20, 0));
    public static final RegistryObject<Item> AMALGAMITE_CHESTPLATE = ITEMS.register("amalgamite_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.AMALGAMITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(ModRarities.KJUNCOMMON), 0, 0, 0, 0, 0, 0, 20, 1, 5, 0, 40, 0));
    public static final RegistryObject<Item> AMALGAMITE_LEGGINGS = ITEMS.register("amalgamite_leggings",
            () -> new ModArmorItem(ModArmorMaterials.AMALGAMITE, ArmorItem.Type.LEGGINGS, new Item.Properties().rarity(ModRarities.KJUNCOMMON), 0, 0, 0, 0, 0, 0, 15, 1, 5, 0, 30, 0));
    public static final RegistryObject<Item> AMALGAMITE_BOOTS = ITEMS.register("amalgamite_boots",
            () -> new ModArmorItem(ModArmorMaterials.AMALGAMITE, ArmorItem.Type.BOOTS, new Item.Properties().rarity(ModRarities.KJUNCOMMON), 0, 0, 0, 0, 0, 0, 10, 1, 5, 0, 20, 0));

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new TestItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
