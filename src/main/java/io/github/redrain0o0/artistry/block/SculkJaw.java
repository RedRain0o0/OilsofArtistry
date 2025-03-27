package io.github.redrain0o0.artistry.block;

import com.mojang.serialization.MapCodec;
import io.github.redrain0o0.artistry.block.entities.SculkJawEntity;
import io.github.redrain0o0.artistry.misc.ArtistryDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SculkJaw extends BaseEntityBlock {
    public SculkJaw(Properties properties) {
        super(properties);
    }

    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().source(ArtistryDamageTypes.SCULK_JAW_BITE), 1.0F);
        }

        super.stepOn(level, blockPos, blockState, entity);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        return Shapes.create(0f, 0f, 0f, 1f, 0.25f, 1.0f);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(SculkJaw::new);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SculkJawEntity(pos, state);
    }

    /*@Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!(level.getBlockEntity(pos) instanceof SculkJawEntity sculkJawEntity)) {
            return super.useWithoutItem(state, level, pos, player, hit);
        }

        sculkJawEntity.incrementClicks();
        player.displayClientMessage(Component.literal("You've clicked the block for the " + sculkJawEntity.getClicks() + "th time."), true);

        return InteractionResult.SUCCESS;
    }*/
}
