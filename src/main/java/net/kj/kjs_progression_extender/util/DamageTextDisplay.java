package net.kj.kjs_progression_extender.util;

import net.minecraft.world.entity.Display.TextDisplay;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class DamageTextDisplay extends TextDisplay {
    private int lifetime;

    public DamageTextDisplay(EntityType<?> pEntityType, Level pLevel, int lifetime) {
        super(pEntityType, pLevel);
        this.lifetime = lifetime;
    }



    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.lifetime --;
            if (this.lifetime < 0) {
                this.discard();
            }
        }
    }
}
