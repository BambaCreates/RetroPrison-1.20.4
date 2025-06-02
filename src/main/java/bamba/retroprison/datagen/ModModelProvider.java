package bamba.retroprison.datagen;

import bamba.retroprison.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import bamba.retroprison.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POTATO_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.CORE_REFINER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PEARL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZING_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZED_CORE, Models.GENERATED);

        itemModelGenerator.register(ModItems.AMETHYST_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_NUGGET, Models.GENERATED);

        itemModelGenerator.register(ModItems.AMETHYST_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.AMETHYST_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.AMETHYST_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.AMETHYST_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.AMETHYST_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.GALAXY_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FARMING_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CAT_BUNDLE, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AMETHYST_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AMETHYST_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AMETHYST_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AMETHYST_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SLIME_BOOTS));
    }
}