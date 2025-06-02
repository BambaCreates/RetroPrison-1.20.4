package bamba.retroprison.item;

import bamba.retroprison.Retroprison;
import bamba.retroprison.item.custom.CatBundleItem;
import bamba.retroprison.item.custom.FarmingScytheItem;
import bamba.retroprison.item.custom.IronKeyItem;
import bamba.retroprison.item.custom.SlimeGauntletItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PEARL_INGOT = registerItem("pearl_ingot", new Item(new FabricItemSettings()));
    public static final Item AMETHYST_DUST = registerItem("amethyst_dust", new Item(new FabricItemSettings()));
    public static final Item AMETHYST_INGOT = registerItem("amethyst_ingot", new Item(new FabricItemSettings()));
    public static final Item AMETHYST_NUGGET = registerItem("amethyst_nugget", new Item(new FabricItemSettings()));

    public static final Item BLAZING_HEART = registerItem("blazing_heart", new Item(new FabricItemSettings()));
    public static final Item BLAZED_CORE = registerItem("blazed_core", new Item(new FabricItemSettings()));

    public static final Item GOLDEN_POTATO = registerItem("golden_potato", new Item(new FabricItemSettings().food(ModFoodComponents.GOLDEN_POTATO)));


    public static final Item IRON_KEY = registerItem("iron_key", new IronKeyItem(new FabricItemSettings().maxDamage(250)));

    public static final Item AMETHYST_PICKAXE = registerItem("amethyst_pickaxe",
            new PickaxeItem(ModToolMaterial.AMETHYST, 1, -2.8f, new FabricItemSettings()));
    public static final Item AMETHYST_AXE = registerItem("amethyst_axe",
            new AxeItem(ModToolMaterial.AMETHYST, 6, -3.1f, new FabricItemSettings()));
    public static final Item AMETHYST_SHOVEL = registerItem("amethyst_shovel",
            new ShovelItem(ModToolMaterial.AMETHYST, 1.5f, -3.0f, new FabricItemSettings()));
    public static final Item AMETHYST_SWORD = registerItem("amethyst_sword",
            new SwordItem(ModToolMaterial.AMETHYST, 3, -2.4f, new FabricItemSettings()));
    public static final Item AMETHYST_HOE = registerItem("amethyst_hoe",
            new HoeItem(ModToolMaterial.AMETHYST, -2, -1.0f, new FabricItemSettings()));

    public static final Item GALAXY_SWORD = registerItem("galaxy_sword",
            new SwordItem(ModToolMaterial.GALAXY, 3, -2.4f, new FabricItemSettings()));

    public static final Item FARMING_SCYTHE = registerItem("farming_scythe",
            new FarmingScytheItem(new FabricItemSettings().maxDamage(250)));

    public static final Item SLIME_GAUNTLET = registerItem("slime_gauntlet",
            new SlimeGauntletItem(new FabricItemSettings().maxDamage(300)));

    public static final Item CAT_BUNDLE = registerItem("cat_bundle",
            new CatBundleItem(new FabricItemSettings().maxCount(1)));

    public static final Item SLIME_BOOTS = registerItem("slime_boots",
            new ArmorItem(ModArmorMaterials.SLIME, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item AMETHYST_HELMET = registerItem("amethyst_helmet",
            new ArmorItem(ModArmorMaterials.AMETHYST_INGOT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item AMETHYST_CHESTPLATE = registerItem("amethyst_chestplate",
            new ArmorItem(ModArmorMaterials.AMETHYST_INGOT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item AMETHYST_LEGGINGS = registerItem("amethyst_leggings",
            new ArmorItem(ModArmorMaterials.AMETHYST_INGOT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item AMETHYST_BOOTS = registerItem("amethyst_boots",
            new ArmorItem(ModArmorMaterials.AMETHYST_INGOT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(PEARL_INGOT);
        entries.add(AMETHYST_DUST);
        entries.add(AMETHYST_NUGGET);
        entries.add(AMETHYST_INGOT);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Retroprison.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Retroprison.LOGGER.info("Registering Mod Items for " + Retroprison.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }

}