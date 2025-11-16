package net.kj.kjs_progression_extender.mana;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final String KEY = "mana";
    private final Mana mana = new Mana();
    private final LazyOptional<IMana> optional = LazyOptional.of(() -> mana);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return ManaCapability.MANA.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble(KEY, mana.getMana());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        mana.setMana(nbt.getDouble(KEY));
    }
}
