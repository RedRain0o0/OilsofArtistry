package io.github.redrain0o0.artistry.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class SplinterShieldItem extends ShieldItem {
    public SplinterShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        if (itemStack.has(ArtistryComponents.SPLINTER_COUNT)) {
            int splinterCount = itemStack.getOrDefault(ArtistryComponents.SPLINTER_COUNT, 0); //item.getComponents().get(ArtistryComponents.SPLINTER_COUNT);
            consumer.accept(Component.translatable((splinterCount == 1) ? "item.artistry.splinter_shield.splinter" : "item.artistry.splinter_shield.splinters", splinterCount));
        }
    }
}
