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


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(KJsProgressionExtender.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GEMSTONES = tag("gemstones");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(KJsProgressionExtender.MOD_ID, name));
        }
    }
}
