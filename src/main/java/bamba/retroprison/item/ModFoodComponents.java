package bamba.retroprison.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent GOLDEN_POTATO = new FoodComponent.Builder().hunger(4).saturationModifier(0.25f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1),1f).build();
}