package io.github.redrain0o0.artistry.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SplintersItem extends Item {
    public SplintersItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        var oppositeHand = (InteractionHand)switch (interactionHand) {
            case OFF_HAND -> InteractionHand.MAIN_HAND;
            case MAIN_HAND -> InteractionHand.OFF_HAND;
        };
        ItemStack itemStack2 = player.getItemInHand(oppositeHand);
        if (level.isClientSide) {
            return InteractionResult.PASS;
        } else {
            if (itemStack2.is(ArtistryItems.SPLINTER_SHIELD)) {
                itemStack.shrink(1);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResult.PASS;
        }
    }
}
