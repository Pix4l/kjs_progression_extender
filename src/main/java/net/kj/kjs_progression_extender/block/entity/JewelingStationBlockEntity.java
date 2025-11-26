package net.kj.kjs_progression_extender.block.entity;

import net.kj.kjs_progression_extender.block.ModBlocks;
import net.kj.kjs_progression_extender.item.ModItems;
import net.kj.kjs_progression_extender.screen.JewelingStationMenu;
import net.kj.kjs_progression_extender.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JewelingStationBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    private static final int INPUT_SLOT = 0;
    private static final int GEMSTONE_SLOT_1 = 1;
    private static final int GEMSTONE_SLOT_2 = 2;

    private ItemStack itemLastTick = ItemStack.EMPTY;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int type_1 = 0;
    private int buff_1 = 0;
    private int level_1 = 0;
    private int type_2 = 0;
    private int buff_2 = 0;
    private int level_2 = 0;

    public JewelingStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.JEWELING_STATION_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> JewelingStationBlockEntity.this.type_1;
                    case 1 -> JewelingStationBlockEntity.this.buff_1;
                    case 2 -> JewelingStationBlockEntity.this.level_1;
                    case 3 -> JewelingStationBlockEntity.this.type_2;
                    case 4 -> JewelingStationBlockEntity.this.buff_2;
                    case 5 -> JewelingStationBlockEntity.this.level_2;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> JewelingStationBlockEntity.this.type_1 = pValue;
                    case 1 -> JewelingStationBlockEntity.this.buff_1 = pValue;
                    case 2 -> JewelingStationBlockEntity.this.level_1 = pValue;
                    case 3 -> JewelingStationBlockEntity.this.type_2 = pValue;
                    case 4 -> JewelingStationBlockEntity.this.buff_2 = pValue;
                    case 5 -> JewelingStationBlockEntity.this.level_2 = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.kjs_progression_extender.jeweling_station");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new JewelingStationMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }


    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        ItemStack input = this.itemHandler.getStackInSlot(INPUT_SLOT);
        if (!input.isEmpty()) {
            if (input.is(ModTags.Items.IS_GEMMABLE)) {
                if (!input.getTag().contains("gemstones")) {
                    int[] gemstones = {0,0,0,0,0,0};
                    input.getTag().putIntArray("gemstones", gemstones);
                }

                int[] gemstones = input.getTag().getIntArray("gemstones");

                ItemStack slot1 = this.itemHandler.getStackInSlot(GEMSTONE_SLOT_1);
                ItemStack slot2 = this.itemHandler.getStackInSlot(GEMSTONE_SLOT_2);
                Item gem1 = getGemstoneType(gemstones[0], gemstones[1]).getItem();
                Item gem2 = getGemstoneType(gemstones[3], gemstones[4]).getItem();

                if (gemstones[0] != 0 && (slot1 == ItemStack.EMPTY || slot1.getItem() == gem1)) {
                    this.itemHandler.insertItem(GEMSTONE_SLOT_1, new ItemStack(gem1, 1), false);

                    gemstones[0] = 0;
                    gemstones[1] = 0;
                }

                if (gemstones[3] != 0 && (slot2 == ItemStack.EMPTY || slot2.getItem() == gem2)) {
                    this.itemHandler.insertItem(GEMSTONE_SLOT_2, new ItemStack(gem2, 1), false);

                    gemstones[3] = 0;
                    gemstones[4] = 0;
                }

                if (slot1.isEmpty()) {
                    this.type_1 = 0;
                    this.level_1 = 0;
                } else {
                    this.type_1 = getTypeIndex(slot1);
                    this.level_1 = getPowerIndex(slot1);
                }

                if (slot2.isEmpty()) {
                    this.type_2 = 0;
                    this.level_2 = 0;
                } else {
                    this.type_2 = getTypeIndex(slot2);
                    this.level_2 = getPowerIndex(slot2);
                }

                setChanged(pLevel, pPos, pState);

                itemLastTick = input;
            }

        } else {
            if (!itemLastTick.isEmpty() && itemLastTick.is(ModTags.Items.IS_GEMMABLE)) {
                if (!itemLastTick.getTag().contains("gemstones")) {
                    int[] gemstones = {0,0,0,0,0,0};
                    itemLastTick.getTag().putIntArray("gemstones", gemstones);
                }

                int[] gemstones = itemLastTick.getTag().getIntArray("gemstones");

                if (gemstones[0] == 0 && itemHandler.getStackInSlot(GEMSTONE_SLOT_1).is(ModTags.Items.GEMSTONES)) {
                    writeGemstoneToList(1, itemLastTick, buff_1);
                    this.itemHandler.extractItem(1, 1, false);
                }

                if (gemstones[3] == 0 && itemHandler.getStackInSlot(GEMSTONE_SLOT_2).is(ModTags.Items.GEMSTONES)) {
                    writeGemstoneToList(2, itemLastTick, buff_2);
                    this.itemHandler.extractItem(2, 1, false);
                }

                itemLastTick = ItemStack.EMPTY;
                this.type_1 = 0;
                this.buff_1 = 0;
                this.type_2 = 0;
                this.buff_2 = 0;
            }
        }
    }

    private ItemStack getGemstoneType (int index, int type) {
        if (index == 9) {
            return new ItemStack(ModItems.ELEMENTAL_SINGULARITY.get());
        }

        switch (type) {
            case 0 -> {
                switch (index) {
                    case 1 -> {
                        return new ItemStack(ModItems.JADE.get());
                    }
                    case 2 -> {
                        return new ItemStack(ModBlocks.JADE_BLOCK.get());
                    }
                    case 3 -> {
                        return new ItemStack(ModItems.EMPOWERED_JADE.get());
                    }
                    case 4 -> {
                        return new ItemStack(ModBlocks.EMPOWERED_JADE_BLOCK.get());
                    }
                    case 5 -> {
                        return new ItemStack(ModItems.HYPER_JADE.get());
                    }
                    case 6 -> {
                        return new ItemStack(ModBlocks.HYPER_JADE_BLOCK.get());
                    }
                    case 7 -> {
                        return new ItemStack(ModItems.OMEGA_JADE.get());
                    }
                    case 8 -> {
                        return new ItemStack(ModBlocks.OMEGA_JADE_BLOCK.get());
                    }
                }
            }
            case 1 -> {
                switch (index) {
                    case 1 -> {
                        return new ItemStack(ModItems.RUBY.get());
                    }
                    case 2 -> {
                        return new ItemStack(ModBlocks.RUBY_BLOCK.get());
                    }
                    case 3 -> {
                        return new ItemStack(ModItems.EMPOWERED_RUBY.get());
                    }
                    case 4 -> {
                        return new ItemStack(ModBlocks.EMPOWERED_RUBY_BLOCK.get());
                    }
                    case 5 -> {
                        return new ItemStack(ModItems.HYPER_RUBY.get());
                    }
                    case 6 -> {
                        return new ItemStack(ModBlocks.HYPER_RUBY_BLOCK.get());
                    }
                    case 7 -> {
                        return new ItemStack(ModItems.OMEGA_RUBY.get());
                    }
                    case 8 -> {
                        return new ItemStack(ModBlocks.OMEGA_RUBY_BLOCK.get());
                    }
                }
            }
            case 2 -> {
                switch (index) {
                    case 1 -> {
                        return new ItemStack(ModItems.SAPPHIRE.get());
                    }
                    case 2 -> {
                        return new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get());
                    }
                    case 3 -> {
                        return new ItemStack(ModItems.EMPOWERED_SAPPHIRE.get());
                    }
                    case 4 -> {
                        return new ItemStack(ModBlocks.EMPOWERED_SAPPHIRE_BLOCK.get());
                    }
                    case 5 -> {
                        return new ItemStack(ModItems.HYPER_SAPPHIRE.get());
                    }
                    case 6 -> {
                        return new ItemStack(ModBlocks.HYPER_SAPPHIRE_BLOCK.get());
                    }
                    case 7 -> {
                        return new ItemStack(ModItems.OMEGA_SAPPHIRE.get());
                    }
                    case 8 -> {
                        return new ItemStack(ModBlocks.OMEGA_SAPPHIRE_BLOCK.get());
                    }
                }
            }
            case 3 -> {
                switch (index) {
                    case 1 -> {
                        return new ItemStack(ModItems.TOPAZ.get());
                    }
                    case 2 -> {
                        return new ItemStack(ModBlocks.TOPAZ_BLOCK.get());
                    }
                    case 3 -> {
                        return new ItemStack(ModItems.EMPOWERED_TOPAZ.get());
                    }
                    case 4 -> {
                        return new ItemStack(ModBlocks.EMPOWERED_TOPAZ_BLOCK.get());
                    }
                    case 5 -> {
                        return new ItemStack(ModItems.HYPER_TOPAZ.get());
                    }
                    case 6 -> {
                        return new ItemStack(ModBlocks.HYPER_TOPAZ_BLOCK.get());
                    }
                    case 7 -> {
                        return new ItemStack(ModItems.OMEGA_TOPAZ.get());
                    }
                    case 8 -> {
                        return new ItemStack(ModBlocks.OMEGA_TOPAZ_BLOCK.get());
                    }
                }
            }
        }
        return new ItemStack(ModBlocks.AMALGAMITE_BLOCK.get());
    }

    private void writeGemstoneToList (int slot, ItemStack item, int buff) {
        if (!item.getTag().contains("gemstones")) {
            int[] gemstones = {0,0,0,0,0,0};
            item.getTag().putIntArray("gemstones", gemstones);
        }

        int[] gemstones = item.getTag().getIntArray("gemstones");
        ItemStack gemstone = this.itemHandler.getStackInSlot(slot);

        gemstones[(slot - 1) * 3] = getPowerIndex(gemstone);
        gemstones[(slot - 1) * 3 + 1] = getTypeIndex(gemstone) - 1;
        gemstones[(slot - 1) * 3 + 2] = buff;

        item.getTag().putIntArray("gemstones", gemstones);
    }

    public void setSelectedBuff(int slot, int buff) {
        switch (slot) {
            case 1 -> this.buff_1 = buff;
            case 2 -> this.buff_2 = buff;
        }
    }

    private int getPowerIndex(ItemStack item) {
        if(item.is(ModTags.Items.POWER_1)) {
            return 1;
        } else if (item.is(ModTags.Items.POWER_2)) {
            return 2;
        } else if (item.is(ModTags.Items.POWER_3)) {
            return 3;
        } else if (item.is(ModTags.Items.POWER_4)) {
            return 4;
        } else if (item.is(ModTags.Items.POWER_5)) {
            return 5;
        } else if (item.is(ModTags.Items.POWER_6)) {
            return 6;
        } else if (item.is(ModTags.Items.POWER_7)) {
            return 7;
        } else if (item.is(ModTags.Items.POWER_8)) {
            return 8;
        } else if (item.is(ModTags.Items.POWER_9)) {
            return 9;
        } else {
            return 0;
        }
    }

    private int getTypeIndex(ItemStack item) {
        if(item.is(ModTags.Items.JADE)) {
            return 1;
        } else if (item.is(ModTags.Items.RUBY)) {
            return 2;
        } else if (item.is(ModTags.Items.SAPPHIRE)) {
            return 3;
        } else if (item.is(ModTags.Items.TOPAZ)) {
            return 4;
        } else if (item.is(ModTags.Items.POWER_9)){
            return 5;
        } else {
            return 0;
        }
    }
}
