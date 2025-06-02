package bamba.retroprison.item.custom;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SlimeGauntletItem extends Item {
    public SlimeGauntletItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Slime you later").formatted(Formatting.GREEN, Formatting.ITALIC));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient) {
            Vec3d knockback = target.getPos().subtract(attacker.getPos()).normalize().multiply(1.5);
            target.addVelocity(knockback.getX(), 0.5, knockback.getZ());
            target.velocityModified = true;

            // Play slime jump sound at the target's position
            target.getWorld().playSound(
                    null, // player - null means all nearby players hear it
                    target.getX(),
                    target.getY(),
                    target.getZ(),
                    SoundEvents.ENTITY_SLIME_JUMP,
                    SoundCategory.PLAYERS,
                    1.0f, // volume
                    1.0f  // pitch
            );
        }

        // Optional: Damage the item
        stack.damage(1, attacker.getRandom(), null);

        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;  // Allow enchantment at the enchantment table
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Enchantment enchantment, EnchantingContext context) {
        return enchantment == Enchantments.UNBREAKING
                || enchantment == Enchantments.MENDING
                || enchantment.isAcceptableItem(stack);  // Default check for valid enchantments
    }
}
