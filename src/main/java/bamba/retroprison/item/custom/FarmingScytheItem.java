package bamba.retroprison.item.custom;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class FarmingScytheItem extends Item {
    private static final Map<Block, Item> CROP_SEEDS = new HashMap<>();

    static {
        CROP_SEEDS.put(Blocks.WHEAT, Items.WHEAT_SEEDS);
        CROP_SEEDS.put(Blocks.CARROTS, Items.CARROT);
        CROP_SEEDS.put(Blocks.POTATOES, Items.POTATO);
        CROP_SEEDS.put(Blocks.BEETROOTS, Items.BEETROOT_SEEDS);
        CROP_SEEDS.put(Blocks.NETHER_WART, Items.NETHER_WART);
    }

    public FarmingScytheItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) return ActionResult.SUCCESS;

        BlockPos center = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        Hand hand = context.getHand();

        if (player == null) return ActionResult.PASS;

        int fortuneLevel = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack);
        boolean shouldReplant = !player.isSneaking();  // Check for sneak (shift) key

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos pos = center.add(dx, 0, dz);
                BlockState state = world.getBlockState(pos);

                if (isMatureCrop(state)) {
                    if (world instanceof ServerWorld serverWorld) {
                        Block.dropStacks(state, world, pos, null, player, stack);

                        // Apply fortune for extra drops
                        if (fortuneLevel > 0) {
                            for (int i = 0; i < serverWorld.getRandom().nextInt(fortuneLevel + 1); i++) {
                                Block.dropStacks(state, world, pos, null, player, stack);
                            }
                        }

                        world.breakBlock(pos, false);

                        if (shouldReplant) {
                            tryReplant(player, world, pos, state);
                        }

                        // Damage scythe after use (unless in creative mode)
                        if (!player.isCreative()) {
                            stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                        }
                    }
                } else if (canTill(state)) {
                    BlockPos above = pos.up();
                    if (world.getBlockState(above).isAir()) {
                        world.setBlockState(pos, Blocks.FARMLAND.getDefaultState(), 3);

                        // Play sound when hoed
                        world.playSound(null, pos, SoundEvents.BLOCK_GRASS_PLACE, player.getSoundCategory(), 1.0F, 1.0F);

                        if (!player.isCreative()) {
                            stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                        }
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    private boolean isMatureCrop(BlockState state) {
        Block block = state.getBlock();

        if (block instanceof CropBlock crop) {
            return crop.isMature(state);
        } else if (block instanceof NetherWartBlock) {
            return state.get(Properties.AGE_3) == 3;
        }

        return false;
    }

    private boolean canTill(BlockState state) {
        return state.isOf(Blocks.DIRT) || state.isOf(Blocks.GRASS_BLOCK) || state.isOf(Blocks.COARSE_DIRT);
    }

    private void tryReplant(PlayerEntity player, World world, BlockPos pos, BlockState harvestedState) {
        Block block = harvestedState.getBlock();
        Item seedItem = CROP_SEEDS.get(block);
        if (seedItem == null) return;

        ItemStack seedStack = findSeedInInventory(player, seedItem);
        if (!seedStack.isEmpty()) {
            BlockState replantedState = block.getDefaultState();
            world.setBlockState(pos, replantedState, 3);
            seedStack.decrement(1);
        }
    }

    private ItemStack findSeedInInventory(PlayerEntity player, Item seedItem) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.getItem() == seedItem && stack.getCount() > 0) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;  // Allow enchantment at the enchantment table
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Enchantment enchantment, EnchantingContext context) {
        return enchantment == Enchantments.FORTUNE
                || enchantment == Enchantments.UNBREAKING
                || enchantment == Enchantments.MENDING
                || enchantment.isAcceptableItem(stack);  // Default check for valid enchantments
    }
}
