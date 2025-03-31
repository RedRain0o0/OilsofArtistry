package io.github.redrain0o0.artistry.entity.client;

import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.entity.client.model.SplinterModel;
import net.minecraft.client.model.BeeStingerModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.StuckInBodyLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;

public class SplinterLayer<M extends PlayerModel> extends StuckInBodyLayer<M> {
    private static final ResourceLocation SPLINTER_LOCATION = Artistry.createID("textures/entity/splinter.png");

    public SplinterLayer(LivingEntityRenderer<?, PlayerRenderState, M> livingEntityRenderer, EntityRendererProvider.Context context) {
        super(livingEntityRenderer, new SplinterModel(context.bakeLayer(ArtistryModelLayers.SPLINTER)), SPLINTER_LOCATION, PlacementStyle.ON_SURFACE);
    }

    protected int numStuck(PlayerRenderState playerRenderState) {
        return playerRenderState.stingerCount;
    }
}
