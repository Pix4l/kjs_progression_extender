package net.kj.kjs_progression_extender.block;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.block.custom.JewelingStationBlock;
import net.kj.kjs_progression_extender.item.ModItems;
import net.kj.kjs_progression_extender.item.ModRarities;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KJsProgressionExtender.MOD_ID);

    //GEMSTONE BLOCKS
    public static final RegistryObject<Block> JADE_BLOCK = registerBlock("jade_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJCOMMON);

    public static final RegistryObject<Block> EMPOWERED_JADE_BLOCK = registerBlock("empowered_jade_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJUNCOMMON);
    public static final RegistryObject<Block> EMPOWERED_RUBY_BLOCK = registerBlock("empowered_ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJUNCOMMON);
    public static final RegistryObject<Block> EMPOWERED_SAPPHIRE_BLOCK = registerBlock("empowered_sapphire_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJUNCOMMON);
    public static final RegistryObject<Block> EMPOWERED_TOPAZ_BLOCK = registerBlock("empowered_topaz_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJUNCOMMON);

    public static final RegistryObject<Block> HYPER_JADE_BLOCK = registerBlock("hyper_jade_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJRARE);
    public static final RegistryObject<Block> HYPER_RUBY_BLOCK = registerBlock("hyper_ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJRARE);
    public static final RegistryObject<Block> HYPER_SAPPHIRE_BLOCK = registerBlock("hyper_sapphire_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJRARE);
    public static final RegistryObject<Block> HYPER_TOPAZ_BLOCK = registerBlock("hyper_topaz_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJRARE);

    public static final RegistryObject<Block> OMEGA_JADE_BLOCK = registerBlock("omega_jade_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJEPIC);
    public static final RegistryObject<Block> OMEGA_RUBY_BLOCK = registerBlock("omega_ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJEPIC);
    public static final RegistryObject<Block> OMEGA_SAPPHIRE_BLOCK = registerBlock("omega_sapphire_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJEPIC);
    public static final RegistryObject<Block> OMEGA_TOPAZ_BLOCK = registerBlock("omega_topaz_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)), ModRarities.KJEPIC);

    //GEMSTONE ORES
    public static final RegistryObject<Block> JADE_ORE = registerBlock("jade_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);


    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = registerBlock("deepslate_jade_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);

    //OTHER ORES
    public static final RegistryObject<Block> DEEPSLATE_AMALGAMITE_ORE = registerBlock("deepslate_amalgamite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(5f).requiresCorrectToolForDrops()), ModRarities.KJCOMMON);
    public static final RegistryObject<Block> ELEMENTITE_ORE = registerBlock("elementite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(5f).requiresCorrectToolForDrops()), ModRarities.KJUNCOMMON);
    public static final RegistryObject<Block> DEEPSLATE_ELEMENTITE_ORE = registerBlock("deepslate_elementite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(5f).requiresCorrectToolForDrops()), ModRarities.KJUNCOMMON);
    public static final RegistryObject<Block> METEORITE_SHARD_ORE = registerBlock("meteorite_shard_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).strength(5f).requiresCorrectToolForDrops()), ModRarities.KJRARE);
    public static final RegistryObject<Block> COSMITE_ORE = registerBlock("cosmite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(5f).requiresCorrectToolForDrops()), ModRarities.KJEPIC);


    //OTHER BLOCKS
    public static final RegistryObject<Block> AMALGAMITE_BLOCK = registerBlock("amalgamite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), ModRarities.KJUNCOMMON);

    //GEMSTONE STATIONS
    public static final RegistryObject<Block> JEWELING_STATION = registerBlock("jeweling_station",
            () -> new JewelingStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()), ModRarities.KJCOMMON);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Rarity rarity) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, rarity);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, Rarity rarity) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().rarity(rarity)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
