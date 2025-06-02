package bamba.retroprison.block.entity;

import bamba.retroprison.Retroprison;
import bamba.retroprison.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CoreRefinerBlockEntity> CORE_REFINER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Retroprison.MOD_ID, "core_refiner_be"),
                    FabricBlockEntityTypeBuilder.create(CoreRefinerBlockEntity::new,
                            ModBlocks.CORE_REFINER).build());

    public static void registerBlockEntities() {
        Retroprison.LOGGER.info("Registering Block Entities for " + Retroprison.MOD_ID);
    }
}
