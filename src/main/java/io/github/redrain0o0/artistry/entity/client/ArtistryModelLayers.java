package io.github.redrain0o0.artistry.entity.client;

import com.google.common.collect.Sets;
import io.github.redrain0o0.artistry.Artistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;

import java.util.Set;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class ArtistryModelLayers {
    private static final String DEFAULT_LAYER = "main";
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation SPLINTER_SHIELD = register("splinter_shield");
    public static final ModelLayerLocation TERMITE = register("termite");
    public static final ModelLayerLocation TERMITE_BABY = register("termite_baby");

    public static void initialize() {
        Artistry.LOGGER.info("Registering Model Layers...");
    }

    private static ModelLayerLocation register(String string) {
        return register(string, "main");
    }

    private static ModelLayerLocation register(String string, String string2) {
        ModelLayerLocation modelLayerLocation = createLocation(string, string2);
        if (!ALL_MODELS.add(modelLayerLocation)) {
            throw new IllegalStateException("Duplicate registration for " + String.valueOf(modelLayerLocation));
        } else {
            return modelLayerLocation;
        }
    }

    private static ModelLayerLocation createLocation(String string, String string2) {
        return new ModelLayerLocation(Artistry.createID(string), string2);
    }

    public static Stream<ModelLayerLocation> getKnownLocations() {
        return ALL_MODELS.stream();
    }
}
