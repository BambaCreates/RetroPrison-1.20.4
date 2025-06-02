package bamba.retroprison.recipe;

import bamba.retroprison.Retroprison;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Retroprison.MOD_ID, CoreRefinerRecipe.Serializer.ID),
                CoreRefinerRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Retroprison.MOD_ID, CoreRefinerRecipe.Type.ID),
                CoreRefinerRecipe.Type.INSTANCE);
    }
}