package io.github.redrain0o0.artistry.misc;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import io.github.redrain0o0.artistry.Artistry;
import net.fabricmc.loader.api.FabricLoader;

public class ArtistryConfig {
    public static ConfigClassHandler<ArtistryConfig> HANDLER = ConfigClassHandler.createBuilder(ArtistryConfig.class)
            .id(Artistry.createID("artistry_config"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FabricLoader.getInstance().getConfigDir().resolve("artistry_config.json5"))
                            .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry
    public boolean myCoolBoolean = true;

    @SerialEntry
    public int myCoolInteger = 5;

    @SerialEntry


    public String myCoolString = "How amazing!";
}
