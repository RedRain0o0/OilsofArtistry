package io.github.redrain0o0.artistry.client;

import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.entity.ArtistryEntities;
import io.github.redrain0o0.artistry.entity.client.ArtistryModelLayers;
import io.github.redrain0o0.artistry.entity.client.SplinterProjectileRenderer;
import io.github.redrain0o0.artistry.item.ArtistryItemTags;
import io.github.redrain0o0.artistry.item.client.model.SplinterShieldModel;
import io.github.redrain0o0.artistry.item.client.renderer.SplinterShieldSpecialRenderer;
import io.github.redrain0o0.artistry.networking.packet.AttackKeybindC2SPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.special.SpecialModelRenderers;

import java.util.concurrent.atomic.AtomicReference;

public class ArtistryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Artistry.LOGGER.info("Initializing client...");
        ArtistryModelLayers.initialize();
        EntityModelLayerRegistry.registerModelLayer(SplinterShieldModel.SPLINTER_SHIELD, SplinterShieldModel::createLayer);
        SpecialModelRenderers.ID_MAPPER.put(Artistry.createID("splinter_shield"), SplinterShieldSpecialRenderer.Unbaked.MAP_CODEC);

        EntityRendererRegistry.register(ArtistryEntities.SPLINTER, SplinterProjectileRenderer::new);

        AtomicReference<Boolean> isAttackDown = new AtomicReference<>(false);
        AtomicReference<Boolean> wasAttackDown = new AtomicReference<>(false);
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            isAttackDown.set(Minecraft.getInstance().options.keyAttack.isDown());
            if (isAttackDown.get() && !wasAttackDown.get()) {
                assert client.player != null;
                if (client.player.isHolding(itemStack -> itemStack.is(ArtistryItemTags.SHIELDS))) {
                    AttackKeybindC2SPayload payload = new AttackKeybindC2SPayload(client.player.getId());
                    ClientPlayNetworking.send(payload);
                }
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> wasAttackDown.set(isAttackDown.get()));
    }
}
