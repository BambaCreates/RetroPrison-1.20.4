package bamba.retroprison.item;

import bamba.retroprison.Retroprison;
import bamba.retroprison.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup RETROPRISON_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Retroprison.MOD_ID, "retroprison"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.retroprison"))
                    .icon(() -> new ItemStack(ModItems.AMETHYST_DUST)).entries((displayContext, entries) -> {
                        entries.add(ModItems.AMETHYST_DUST);
                        entries.add(ModItems.AMETHYST_INGOT);
                        entries.add(ModItems.AMETHYST_NUGGET);

                        entries.add(ModItems.AMETHYST_PICKAXE);
                        entries.add(ModItems.AMETHYST_AXE);
                        entries.add(ModItems.AMETHYST_SHOVEL);
                        entries.add(ModItems.AMETHYST_SWORD);
                        entries.add(ModItems.AMETHYST_HOE);

                        entries.add(ModItems.AMETHYST_HELMET);
                        entries.add(ModItems.AMETHYST_CHESTPLATE);
                        entries.add(ModItems.AMETHYST_LEGGINGS);
                        entries.add(ModItems.AMETHYST_BOOTS);

                        entries.add(ModItems.GALAXY_SWORD);
                        entries.add(ModItems.FARMING_SCYTHE);
                        entries.add(ModItems.SLIME_GAUNTLET);
                        entries.add(ModItems.SLIME_BOOTS);
                        entries.add(ModItems.CAT_BUNDLE);

                        entries.add(ModItems.IRON_KEY);

                        entries.add(ModItems.PEARL_INGOT);

                        entries.add(ModBlocks.POTATO_BLOCK);
                        entries.add(ModItems.GOLDEN_POTATO);

                        entries.add(ModItems.BLAZING_HEART);
                        entries.add(ModItems.BLAZED_CORE);

                        entries.add(ModBlocks.CORE_REFINER);

                    }).build());

    public static void registerItemGroups() {
        Retroprison.LOGGER.info("Registering Item Groups for" +Retroprison.MOD_ID);
    }
}
