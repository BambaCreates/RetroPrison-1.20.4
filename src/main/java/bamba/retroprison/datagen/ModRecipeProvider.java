package bamba.retroprison.datagen;

import bamba.retroprison.block.ModBlocks;
import bamba.retroprison.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;


public class ModRecipeProvider extends FabricRecipeProvider {

    private static final List<ItemConvertible> AMETHYST_SMELTABLES = List.of(ModItems.AMETHYST_DUST);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_INGOT, 1)
                .pattern(" # ")
                .pattern("#C#")
                .pattern(" # ")
                .input('#', ModItems.AMETHYST_NUGGET)
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(ModItems.AMETHYST_NUGGET), conditionsFromItem(ModItems.AMETHYST_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_INGOT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_PICKAXE, 1)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .input('#', ModItems.AMETHYST_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_AXE, 1)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .input('#', ModItems.AMETHYST_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_SHOVEL, 1)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .input('#', ModItems.AMETHYST_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_SWORD, 1)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" S ")
                .input('#', ModItems.AMETHYST_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_HOE, 1)
                .pattern("## ")
                .pattern(" S ")
                .pattern(" S ")
                .input('#', ModItems.AMETHYST_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_HELMET, 1)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.AMETHYST_INGOT)
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_CHESTPLATE, 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.AMETHYST_INGOT)
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_LEGGINGS, 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.AMETHYST_INGOT)
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_BOOTS, 1)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.AMETHYST_INGOT)
                .criterion(hasItem(ModItems.AMETHYST_INGOT), conditionsFromItem(ModItems.AMETHYST_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IRON_KEY, 1)
                .pattern("#")
                .pattern("C")
                .input('#', Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE)
                .input('C', Items.COPPER_INGOT)
                .criterion(hasItem(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE), conditionsFromItem(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.IRON_KEY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.FARMING_SCYTHE, 1)
                .pattern("###")
                .pattern("  S")
                .pattern(" S ")
                .input('#', Items.IRON_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FARMING_SCYTHE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SLIME_BOOTS, 1)
                .pattern("s s")
                .pattern("S S")
                .input('s', Items.SLIME_BALL)
                .input('S', Items.SLIME_BLOCK)
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .criterion(hasItem(Items.SLIME_BLOCK), conditionsFromItem(Items.SLIME_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SLIME_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SLIME_GAUNTLET, 1)
                .pattern("S")
                .pattern("L")
                .input('S', Items.STICKY_PISTON)
                .input('L', Items.LEATHER)
                .criterion(hasItem(Items.STICKY_PISTON), conditionsFromItem(Items.STICKY_PISTON))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SLIME_GAUNTLET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.GALAXY_SWORD, 1)
                .pattern(" E ")
                .pattern(" E ")
                .pattern(" S ")
                .input('E', Items.ENDER_PEARL)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GALAXY_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SADDLE, 1)
                .pattern(" # ")
                .pattern("#S#")
                .input('#', Items.LEATHER)
                .input('S', Items.IRON_INGOT)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(Items.SADDLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLDEN_POTATO, 1)
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GGG")
                .input('G', Items.GOLD_INGOT)
                .input('P', Items.POTATO)
                .criterion(hasItem(Items.POTATO), conditionsFromItem(Items.POTATO))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GOLDEN_POTATO)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETHYST_DUST, 1)
                .input(Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AMETHYST_DUST)));

        offerSmelting(exporter, AMETHYST_SMELTABLES, RecipeCategory.MISC, ModItems.AMETHYST_NUGGET,
                0.7f, 200, "dust_to_nugget");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, Items.POTATO, RecipeCategory.DECORATIONS,
                ModBlocks.POTATO_BLOCK);

    }
}
