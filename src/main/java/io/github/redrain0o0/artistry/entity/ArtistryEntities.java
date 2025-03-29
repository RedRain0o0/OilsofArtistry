package io.github.redrain0o0.artistry.entity;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ArtistryEntities {
    public static final EntityType<TermiteEntity> TERMITE = /*register("termite", TermiteEntity::new, 0.5f, 0.5f);*/Registry.register(BuiltInRegistries.ENTITY_TYPE,
            ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, "termite")),
            EntityType.Builder.of(TermiteEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f).build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID,"termite")))); //ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, "termite"))));

    //public static EntityType register(String name, Entity entityClass, Float width, Float height) {
    //    ResourceKey entityKey = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, "termite"));
    //    EntityType entityType = EntityType.Builder.of(entityClass::new, MobCategory.CREATURE)
    //            .sized(width, height).build(entityKey);
    //    Registry.register(BuiltInRegistries.ENTITY_TYPE, entityKey, entityType);
    //    return entityType;
    //}

    public static void initialize() {
        Artistry.LOGGER.info("Registering Mod Entities...");
    }
}
