package net.kj.kjs_progression_extender.datagen;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KJsProgressionExtender.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.JADE);
        gemstoneVariant(ModItems.EMPOWERED_JADE, "jade");
        gemstoneVariant(ModItems.HYPER_JADE, "jade");
        gemstoneVariant(ModItems.OMEGA_JADE, "jade");

        simpleItem(ModItems.RUBY);
        gemstoneVariant(ModItems.EMPOWERED_RUBY, "ruby");
        gemstoneVariant(ModItems.HYPER_RUBY, "ruby");
        gemstoneVariant(ModItems.OMEGA_RUBY, "ruby");

        simpleItem(ModItems.SAPPHIRE);
        gemstoneVariant(ModItems.EMPOWERED_SAPPHIRE, "sapphire");
        gemstoneVariant(ModItems.HYPER_SAPPHIRE, "sapphire");
        gemstoneVariant(ModItems.OMEGA_SAPPHIRE, "sapphire");

        simpleItem(ModItems.TOPAZ);
        gemstoneVariant(ModItems.EMPOWERED_TOPAZ, "topaz");
        gemstoneVariant(ModItems.HYPER_TOPAZ, "topaz");
        gemstoneVariant(ModItems.OMEGA_TOPAZ, "topaz");

        simpleItem(ModItems.ELEMENTAL_SINGULARITY);

        simpleItem(ModItems.EMPOWERED_CORE);
        simpleItem(ModItems.HYPER_CORE);
        simpleItem(ModItems.OMEGA_CORE);
        simpleItem(ModItems.STARFORGED_CORE);

        simpleItem(ModItems.AMALGAMITE);
        simpleItem(ModItems.CONCENTRATED_AMALGAMITE);

        simpleItem(ModItems.DRAKE_SCALE);
        simpleItem(ModItems.DRAKE_HIDE);
        simpleItem(ModItems.DRAKESTEEL_INGOT);

        handheldItem(ModItems.DRAKESTEEL_PICKAXE);

        simpleItem(ModItems.TEST_ITEM);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KJsProgressionExtender.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder gemstoneVariant(RegistryObject<Item> item, String gemstoneType) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KJsProgressionExtender.MOD_ID, "item/" + gemstoneType));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(KJsProgressionExtender.MOD_ID, "item/" + item.getId().getPath()));
    }
}
