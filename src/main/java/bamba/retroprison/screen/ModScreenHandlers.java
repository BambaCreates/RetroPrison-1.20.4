package bamba.retroprison.screen;

import bamba.retroprison.Retroprison;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CoreRefinerScreenHandler> CORE_REFINER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Retroprison.MOD_ID, "core_refiner"),
                    new ExtendedScreenHandlerType<>(CoreRefinerScreenHandler::new));

    public static void registerScreenHandlers() {
        Retroprison.LOGGER.info("Registering Screen Handlers for " + Retroprison.MOD_ID);
    }
}
