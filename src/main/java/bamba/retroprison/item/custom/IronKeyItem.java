package bamba.retroprison.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IronKeyItem extends Item {
    public IronKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (!world.isClient) {
            boolean toggled = false;

            // --- Handle Iron Door ---
            if (block == Blocks.IRON_DOOR && state.contains(Properties.OPEN) && state.contains(Properties.DOUBLE_BLOCK_HALF)) {
                boolean isOpen = state.get(Properties.OPEN);
                DoubleBlockHalf half = state.get(Properties.DOUBLE_BLOCK_HALF);

                // Get position of the other half
                BlockPos otherHalfPos = (half == DoubleBlockHalf.LOWER) ? pos.up() : pos.down();
                BlockState otherHalfState = world.getBlockState(otherHalfPos);

                // Set both halves
                world.setBlockState(pos, state.with(Properties.OPEN, !isOpen), 10);
                world.setBlockState(otherHalfPos, otherHalfState.with(Properties.OPEN, !isOpen), 10);

                world.playSound(null, pos,
                        isOpen ? SoundEvents.BLOCK_IRON_DOOR_CLOSE : SoundEvents.BLOCK_IRON_DOOR_OPEN,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.0F
                );

                toggled = true;
            }

            // --- Handle Iron Trapdoor ---
            if (block == Blocks.IRON_TRAPDOOR && state.contains(Properties.OPEN)) {
                boolean isOpen = state.get(Properties.OPEN);
                world.setBlockState(pos, state.with(Properties.OPEN, !isOpen), 10);

                world.playSound(null, pos,
                        isOpen ? SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE : SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.0F
                );

                toggled = true;
            }

            // Damage the item if something was toggled
            if (toggled && context.getPlayer() != null) {
                context.getStack().damage(1, context.getPlayer(),
                        player -> player.sendToolBreakStatus(context.getHand()));
            }

            if (toggled) {
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS; // Let other interactions still happen if nothing was toggled
    }
}
