package net.kj.kjs_progression_extender.recipe;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KJsProgressionExtender.MOD_ID);

    public static final RegistryObject<RecipeSerializer<UpgradeRecipe>> UPGRADE_SERIALIZER =
            SERIALIZERS.register("upgrade", () -> UpgradeRecipe.Serializer.INSTANCE);


    public static void register (IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
