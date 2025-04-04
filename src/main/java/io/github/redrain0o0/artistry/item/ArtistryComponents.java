package io.github.redrain0o0.artistry.item;

import com.mojang.serialization.Codec;
import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public class ArtistryComponents {
    public static final DataComponentType<Integer> SPLINTER_COUNT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Artistry.createID("splinter_count"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static final DataComponentType<Boolean> IS_SHARPENED = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Artistry.createID("is_sharpened"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );



    // Glove Codecs
    //public static final Codec<TreasureGlove> CODEC = RecordCodecBuilder.create(builder -> {
    //    return builder.group(
    //            Codec.STRING.fieldOf("gem").forGetter(TreasureGlove::gem)
    //    ).apply(builder, TreasureGlove::new);
    //});

    // Glove Records
    //public record TreasureGlove(String gem) {
    //}

    // Glove Components
    //public static final DataComponentType<TreasureGlove> TREASURE_GLOVE = Registry.register(
    //        BuiltInRegistries.DATA_COMPONENT_TYPE,
    //        Artistry.createID("treasure_glove"),
    //        DataComponentType.<TreasureGlove>builder().persistent(TreasureGlove.).build()
    //);

    public static void initialize() {
        Artistry.LOGGER.info("Registering Components...");
    }
}
