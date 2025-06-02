package bamba.retroprison;

import bamba.retroprison.block.ModBlocks;
import bamba.retroprison.block.entity.ModBlockEntities;
import bamba.retroprison.item.ModItemGroups;
import bamba.retroprison.item.ModItems;
import bamba.retroprison.recipe.ModRecipes;
import bamba.retroprison.screen.ModScreenHandlers;
import bamba.retroprison.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Retroprison implements ModInitializer {
	public static final String MOD_ID = "retroprison";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		ModRecipes.registerRecipes();
	}

}