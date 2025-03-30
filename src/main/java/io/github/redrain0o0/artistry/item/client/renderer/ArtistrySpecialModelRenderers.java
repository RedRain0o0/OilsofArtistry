package io.github.redrain0o0.artistry.item.client.renderer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.redrain0o0.artistry.Artistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.special.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

@Environment(EnvType.CLIENT)
public class ArtistrySpecialModelRenderers {
    public static final ExtraCodecs.LateBoundIdMapper<ResourceLocation, MapCodec<? extends SpecialModelRenderer.Unbaked>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper();
    public static final Codec<SpecialModelRenderer.Unbaked> CODEC;

    public static void initialize() {
        Artistry.LOGGER.info("Registering Special Renderers...");
    }

    public static void bootstrap() {
        ID_MAPPER.put(Artistry.createID("splinter_shield"), io.github.redrain0o0.artistry.item.client.renderer.SplinterShieldSpecialRenderer.Unbaked.MAP_CODEC);
    }

    static {
        CODEC = ID_MAPPER.codec(ResourceLocation.CODEC).dispatch(SpecialModelRenderer.Unbaked::type, (mapCodec) -> {
            return mapCodec;
        });
    }
}
