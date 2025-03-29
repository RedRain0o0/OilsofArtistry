package io.github.redrain0o0.artistry.entity.client;

import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.entity.TermiteEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class TermiteRenderer extends AgeableMobRenderer<TermiteEntity, TermiteRenderState, TermiteModel> {
    private static final ResourceLocation TERMITE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, "textures/entity/termite.png");

    public TermiteRenderer(EntityRendererProvider.Context context) {
        super(context, new TermiteModel(context.bakeLayer(ArtistryModelLayers.TERMITE)),
                       new TermiteModel(context.bakeLayer(ArtistryModelLayers.TERMITE_BABY)), 0.5f);
    }

    @Override
    public TermiteRenderState createRenderState() {
        return new TermiteRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(TermiteRenderState state) {
        return TERMITE_TEXTURE;
    }

    @Override
    public void extractRenderState(TermiteEntity termite, TermiteRenderState state, float f) {
        super.extractRenderState(termite, state, f);
        //state.idleAnimState = termite.idleAnimState; //.copyFrom(termite.idleAnimState);

        //state.texture = termite.getTexture();
    }
}
