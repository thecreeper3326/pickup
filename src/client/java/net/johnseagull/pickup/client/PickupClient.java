package net.johnseagull.pickup.client;

import net.johnseagull.figManagerClient.FigManagerClient;
import net.johnseagull.pickup.Figs;
import net.fabricmc.api.ClientModInitializer;

public class PickupClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FigManagerClient figManager = new FigManagerClient();
        figManager.init(Figs.instance,0.5f);
    }
}
