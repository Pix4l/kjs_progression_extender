package net.kj.kjs_progression_extender.util;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> GEMSTONE_BLOCKS = tag("gemstone_blocks");
        public static final TagKey<Block> NEEDS_DRAKESTEEL_TOOL = tag("needs_drakesteel_tool");
        public static final TagKey<Block> NEEDS_ELEMENTAL_TOOL = tag("needs_elemental_tool");
        public static final TagKey<Block> NEEDS_LIGHT_TOOL = tag("needs_light_tool");
        public static final TagKey<Block> NEEDS_STARCAST_TOOL = tag("needs_starcast_tool");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(KJsProgressionExtender.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GEMSTONES = tag("gemstones");
        public static final TagKey<Item> JADE = tag("jade");
        public static final TagKey<Item> RUBY = tag("ruby");
        public static final TagKey<Item> SAPPHIRE = tag("sapphire");
        public static final TagKey<Item> TOPAZ = tag("topaz");

        public static final TagKey<Item> POWER_1 = tag("power_1");
        public static final TagKey<Item> POWER_2 = tag("power_2");
        public static final TagKey<Item> POWER_3 = tag("power_3");
        public static final TagKey<Item> POWER_4 = tag("power_4");
        public static final TagKey<Item> POWER_5 = tag("power_5");
        public static final TagKey<Item> POWER_6 = tag("power_6");
        public static final TagKey<Item> POWER_7 = tag("power_7");
        public static final TagKey<Item> POWER_8 = tag("power_8");
        public static final TagKey<Item> POWER_9 = tag("power_9");

        public static final TagKey<Item> IS_GEMMABLE = tag("is_gemmable");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(KJsProgressionExtender.MOD_ID, name));
        }
    }
}
