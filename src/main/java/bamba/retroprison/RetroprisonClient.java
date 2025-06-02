package bamba.retroprison;

import bamba.retroprison.block.entity.ModBlockEntities;
import bamba.retroprison.block.entity.renderer.CoreRefinerBlockEntityRenderer;
import bamba.retroprison.screen.CoreRefinerScreen;
import bamba.retroprison.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class RetroprisonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.CORE_REFINER_SCREEN_HANDLER, CoreRefinerScreen::new);
        BlockEntityRendererFactories.register(ModBlockEntities.CORE_REFINER_BLOCK_ENTITY, CoreRefinerBlockEntityRenderer::new);
    }
}
