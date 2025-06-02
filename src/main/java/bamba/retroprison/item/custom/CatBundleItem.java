package bamba.retroprison.item.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class CatBundleItem extends Item {
    private static final String CAT_NBT_TAG = "CatData";

    public CatBundleItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient() && hasCat(stack)) {
            HitResult hitResult = player.raycast(5.0D, 0.0F, false);

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                System.out.println("[CatBundleItem] Attempting to place cat...");
                boolean placed = tryPlaceCat((ServerWorld) world, player, stack, (BlockHitResult) hitResult);
                if (placed) {
                    System.out.println("[CatBundleItem] Cat placed successfully!");
                    return TypedActionResult.success(stack, world.isClient());
                } else {
                    System.out.println("[CatBundleItem] Failed to place cat.");
                }
            }
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (player.getWorld().isClient()) {
            return ActionResult.PASS;
        }

        if (entity instanceof CatEntity cat) {
            System.out.println("[CatBundleItem] useOnEntity called on CatEntity");
            UUID ownerUUID = cat.getOwnerUuid();
            if (cat.isTamed() && ownerUUID != null && ownerUUID.equals(player.getUuid())) {
                if (!hasCat(stack)) {
                    System.out.println("[CatBundleItem] Storing cat in item");
                    NbtCompound catData = new NbtCompound();
                    cat.writeNbt(catData);
                    System.out.println("[CatBundleItem] Saved Cat NBT:\n" + catData.asString());

                    // Manually save variant as string ID
                    Identifier variantId = player.getWorld().getRegistryManager()
                            .get(RegistryKeys.CAT_VARIANT)
                            .getId(cat.getVariant());
                    if (variantId != null) {
                        catData.putString("variant", variantId.toString());
                    }

                    stack.getOrCreateNbt().put(CAT_NBT_TAG, catData);

                    // Remove the cat entity from the world
                    cat.remove(Entity.RemovalReason.DISCARDED);

                    // Mark inventory dirty to sync changes
                    player.getInventory().markDirty();
                    player.getInventory().setStack(player.getInventory().selectedSlot, stack);

                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_CAT_HISS, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    return ActionResult.SUCCESS;
                } else {
                    System.out.println("[CatBundleItem] Item already contains a cat!");
                }
            } else {
                System.out.println("[CatBundleItem] Cat is not tamed by player or owner mismatch");
            }
        } else {
            System.out.println("[CatBundleItem] Entity is not a CatEntity");
        }
        return ActionResult.PASS;
    }

    private boolean hasCat(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        boolean result = nbt != null && nbt.contains(CAT_NBT_TAG);
        System.out.println("[CatBundleItem] hasCat: " + result);
        return result;
    }

    private boolean tryPlaceCat(ServerWorld serverWorld, PlayerEntity player, ItemStack stack, BlockHitResult hitResult) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) {
            System.out.println("[CatBundleItem] No NBT found on stack");
            return false;
        }
        NbtCompound catData = nbt.getCompound(CAT_NBT_TAG);
        if (catData == null || catData.isEmpty()) {
            System.out.println("[CatBundleItem] Cat data NBT is empty");
            return false;
        }

        CatEntity cat = EntityType.CAT.create(serverWorld);
        if (cat == null) {
            System.out.println("[CatBundleItem] Failed to create CatEntity");
            return false;
        }

        // Load variant from NBT string and apply explicitly
        if (catData.contains("variant", 8)) { // 8 = STRING
            String variantIdStr = catData.getString("variant");
            Identifier variantId = new Identifier(variantIdStr);
            CatVariant variant = serverWorld.getRegistryManager()
                    .get(RegistryKeys.CAT_VARIANT)
                    .get(variantId);
            if (variant != null) {
                cat.setVariant(variant);
            }
        }

        // Apply remaining cat NBT data (tamed, name, etc)
        cat.readCustomDataFromNbt(catData);

        Vec3d spawnPos = hitResult.getPos().add(0, 0.5, 0);
        cat.refreshPositionAndAngles(spawnPos.x, spawnPos.y, spawnPos.z, player.getYaw(), player.getPitch());

        cat.initialize(serverWorld, serverWorld.getLocalDifficulty(cat.getBlockPos()), SpawnReason.MOB_SUMMONED, null, null);

        boolean spawned = serverWorld.spawnEntity(cat);
        if (!spawned) {
            System.out.println("[CatBundleItem] Failed to spawn CatEntity");
            return false;
        }

        // Remove cat data from the item stack
        nbt.remove(CAT_NBT_TAG);

        // Sync inventory to update changes
        player.getInventory().markDirty();
        player.getInventory().setStack(player.getInventory().selectedSlot, stack);

        serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_CAT_AMBIENT, SoundCategory.PLAYERS, 1.0f, 1.0f);

        return true;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (hasCat(stack)) {
            tooltip.add(Text.literal("Contains your tamed cat"));
        } else {
            tooltip.add(Text.literal("Right-click your tamed cat to pick it up"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
