package io.github.redrain0o0.artistry.block;

import com.mojang.serialization.MapCodec;
import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.block.entities.ArtistryBlockEntities;
import io.github.redrain0o0.artistry.block.entities.TermiteNestEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class TermiteNest extends BaseEntityBlock implements EntityBlock {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final MapCodec<TermiteNest> CODEC = TermiteNest.simpleCodec(TermiteNest::new);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
    }

    public TermiteNest(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(OPEN, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TermiteNestEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    /*@Override
    protected void affectNeighborsAfterRemoval(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {}
    }*/



    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        //if (!level.isClientSide) {
        //    //this.openContainer(level, blockPos, player);
        //    Artistry.LOGGER.info("{} clicked Termite Nest at {}, {}, {}!", player.getName().getString(), blockPos.getX(), blockPos.getY(), blockPos.getZ());
        //}

        if (level instanceof ServerLevel serverLevel) {
            BlockEntity var8 = level.getBlockEntity(blockPos);
            if (var8 instanceof TermiteNestEntity termiteNestEntity) {
                player.openMenu(termiteNestEntity);
                //player.awardStat(Stats.OPEN_BARREL);
            }
        }

        return InteractionResult.SUCCESS;
    }

    //@Override
    //public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
    //    // Make sure to check world.isClient if you only want to tick only on serverside.
    //    return validateTicker(type, ArtistryBlockEntities.TERMITE_NEST_ENTITY, TermiteNestEntity::serverTick);
    //}

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, ArtistryBlockEntities.TERMITE_NEST_ENTITY, TermiteNestEntity::serverTick);
    }
}
