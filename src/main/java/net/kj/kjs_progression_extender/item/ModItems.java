package net.kj.kjs_progression_extender.item;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.custom.ModToolTiers;
import net.kj.kjs_progression_extender.item.custom.TestItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KJsProgressionExtender.MOD_ID);

    //GEMSTONES
    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> TOPAZ = ITEMS.register("topaz",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> EMPOWERED_JADE = ITEMS.register("empowered_jade",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> EMPOWERED_RUBY = ITEMS.register("empowered_ruby",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> EMPOWERED_SAPPHIRE = ITEMS.register("empowered_sapphire",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> EMPOWERED_TOPAZ = ITEMS.register("empowered_topaz",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> HYPER_JADE = ITEMS.register("hyper_jade",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HYPER_RUBY = ITEMS.register("hyper_ruby",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HYPER_SAPPHIRE = ITEMS.register("hyper_sapphire",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HYPER_TOPAZ = ITEMS.register("hyper_topaz",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> OMEGA_JADE = ITEMS.register("omega_jade",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> OMEGA_RUBY = ITEMS.register("omega_ruby",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> OMEGA_SAPPHIRE = ITEMS.register("omega_sapphire",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> OMEGA_TOPAZ = ITEMS.register("omega_topaz",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ELEMENTAL_SINGULARITY = ITEMS.register("elemental_singularity",
            () -> new Item(new Item.Properties().rarity(ModRarities.LEGENDARY)));


    //GEMSTONE CORES
    public static final RegistryObject<Item> EMPOWERED_CORE = ITEMS.register("empowered_core",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HYPER_CORE = ITEMS.register("hyper_core",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> OMEGA_CORE = ITEMS.register("omega_core",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> STARFORGED_CORE = ITEMS.register("starforged_core",
            () -> new Item(new Item.Properties().rarity(ModRarities.LEGENDARY)));


    //ORE ITEMS
    public static final RegistryObject<Item> AMALGAMITE = ITEMS.register("amalgamite",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CONCENTRATED_AMALGAMITE = ITEMS.register("concentrated_amalgamite",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));


    //INGOT CRAFTING MATS
    public static final RegistryObject<Item> DRAKE_SCALE = ITEMS.register("drake_scale",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> DRAKE_HIDE = ITEMS.register("drake_hide",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));


    //INGOTS
    public static final RegistryObject<Item> DRAKESTEEL_INGOT = ITEMS.register("drakesteel_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    //TOOLS
    public static final RegistryObject<Item> DRAKESTEEL_PICKAXE = ITEMS.register("drakesteel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.DRAKESTEEL, 1, -2.8f, new Item.Properties().rarity(Rarity.RARE)));


    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new TestItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
