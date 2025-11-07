package net.kj.kjs_progression_extender.datagen;

import net.kj.kjs_progression_extender.KJsProgressionExtender;
import net.kj.kjs_progression_extender.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

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
        simpleItem(ModItems.ELEMENTITE);
        simpleItem(ModItems.METEORITE_SHARD);
        simpleItem(ModItems.COSMITE_DUST);

        simpleItem(ModItems.DRAKE_SCALE);
        simpleItem(ModItems.DRAKE_HIDE);

        simpleItem(ModItems.DRAKESTEEL_INGOT);
        simpleItem(ModItems.PYROLITE_INGOT);
        simpleItem(ModItems.HYDROLITE_INGOT);
        simpleItem(ModItems.TERRALITE_INGOT);
        simpleItem(ModItems.FULGURITE_INGOT);
        simpleItem(ModItems.VOIDBLIGHT_INGOT);
        simpleItem(ModItems.GLEAMSTONE_INGOT);
        simpleItem(ModItems.STARCAST_INGOT);

        simpleItem(ModItems.SIMPLE_HANDLE);
        simpleItem(ModItems.HEAVY_HANDLE);
        simpleItem(ModItems.REFINED_HANDLE);
        simpleItem(ModItems.DIVINE_HANDLE);

        simpleItem(ModItems.SIMPLE_BLADE);
        simpleItem(ModItems.HEAVY_BLADE);
        simpleItem(ModItems.REFINED_BLADE);
        simpleItem(ModItems.DIVINE_BLADE);

        simpleItem(ModItems.SIMPLE_PLATING);
        simpleItem(ModItems.HEAVY_PLATING);
        simpleItem(ModItems.REFINED_PLATING);
        simpleItem(ModItems.DIVINE_PLATING);

        handheldItem(ModItems.DRAKESTEEL_PICKAXE);
        handheldItem(ModItems.TEST_SWORD);
        handheldItem(ModItems.AMALGAMITE_SWORD);

        trimmedArmorItem(ModItems.AMALGAMITE_HELMET);
        trimmedArmorItem(ModItems.AMALGAMITE_CHESTPLATE);
        trimmedArmorItem(ModItems.AMALGAMITE_LEGGINGS);
        trimmedArmorItem(ModItems.AMALGAMITE_BOOTS);

        simpleItem(ModItems.TEST_ITEM);

    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = KJsProgressionExtender.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
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
