package io.github.redrain0o0.artistry.misc;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ArtistryDamageTypes {
    public static final ResourceKey<DamageType> SCULK_JAW_BITE = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, "sculk_jaw"));

    public static void initialize() {
        Artistry.LOGGER.info("Registering Mod Damage Types...");
    }
}
