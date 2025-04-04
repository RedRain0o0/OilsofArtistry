package io.github.redrain0o0.artistry.client;

import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.entity.client.ArtistryModelLayers;
import io.github.redrain0o0.artistry.item.client.model.SplinterShieldModel;
import io.github.redrain0o0.artistry.item.client.renderer.SplinterShieldSpecialRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.special.SpecialModelRenderers;

public class ArtistryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Artistry.LOGGER.info("Initializing client...");
        EntityModelLayerRegistry.registerModelLayer(SplinterShieldModel.SPLINTER_SHIELD, SplinterShieldModel::createLayer);
        ArtistryModelLayers.initialize();
        SpecialModelRenderers.ID_MAPPER.put(Artistry.createID("splinter_shield"), SplinterShieldSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
