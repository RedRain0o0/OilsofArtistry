package io.github.redrain0o0.artistry.misc;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ArtistryDamageTypes {
    public static final ResourceKey<DamageType> SCULK_JAW_BITE = ResourceKey.create(Registries.DAMAGE_TYPE, Artistry.createID("sculk_jaw"));
    public static final ResourceKey<DamageType> SPLINTER = ResourceKey.create(Registries.DAMAGE_TYPE, Artistry.createID("splinter"));

    public static void initialize() {
        Artistry.LOGGER.info("Registering Damage Types...");
    }
}
