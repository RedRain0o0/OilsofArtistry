package io.github.redrain0o0.artistry.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SculkJawEntity extends BlockEntity {
    public SculkJawEntity(BlockPos pos, BlockState state) {
        super(ArtistryBlockEntities.SCULK_JAW_ENTITY, pos, state);
    }
}
