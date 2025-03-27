package io.github.redrain0o0.artistry.block.entities;

import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.block.ArtistryBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ArtistryBlockEntities {
    public static final BlockEntityType<SculkJawEntity> SCULK_JAW_ENTITY = register("sculk_jaw", SculkJawEntity::new, ArtistryBlocks.SCULK_JAW);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void initialize() {
        Artistry.LOGGER.info("Registering Mod Block Entities...");
    }
}
