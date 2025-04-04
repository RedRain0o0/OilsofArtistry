package io.github.redrain0o0.artistry.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class DisplayCase extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    public DisplayCase(Properties properties) {
        super(properties);


        // Set the default state of the block to be deactivated.
        registerDefaultState(defaultBlockState().setValue(ACTIVATED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!player.getAbilities().mayBuild) {
            // Skip if the player isn't allowed to modify the world.
            return InteractionResult.PASS;
        } else {
            // Get the current value of the "activated" property
            boolean activated = state.getValue(ACTIVATED);

            // Flip the value of activated and save the new blockstate.
            level.setBlockAndUpdate(pos, state.setValue(ACTIVATED, !activated));

            // Play a click sound to emphasise the interaction.
            level.playSound(player, pos, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        }
    }
}
