package net.johnseagull.pickup.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.johnseagull.figManager.FigManager;
import net.johnseagull.figManagerClient.FigScreen;
import net.minecraft.network.chat.Component;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return p -> new FigScreen<>(Component.literal("Pickup"), 0.48f, FigManager.FIGS, p);
    }
}

