package io.github.redrain0o0.artistry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class SplinterShieldItem extends ArtistryShieldItem {
    boolean isUsingAbility = false;
    int splinterTimer = 0;
    //public static final int MAX_BAR_WIDTH = 13;
    public SplinterShieldItem(Properties properties) {
        super(properties);
    }

    //@Override
    //public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
    //    if (itemStack.has(ArtistryComponents.SPLINTER_COUNT)) {
    //        int splinterCount = itemStack.getOrDefault(ArtistryComponents.SPLINTER_COUNT, 0); //item.getComponents().get(ArtistryComponents.SPLINTER_COUNT);
    //        consumer.accept(Component.translatable((splinterCount == 1) ? "item.artistry.splinter_shield.splinter" : "item.artistry.splinter_shield.splinters", splinterCount));
    //    }
    //}

    @Override
    public void shieldAbility(Player player, ItemStack itemStack) {
        if (itemStack.getOrDefault(ArtistryComponents.SPLINTER_COUNT, 0) > 0 && !isUsingAbility) {
            if (player.level() instanceof ServerLevel) {
                int splinterCount = itemStack.getOrDefault(ArtistryComponents.SPLINTER_COUNT, 0);
                itemStack.set(ArtistryComponents.SPLINTER_COUNT, splinterCount - 1);
                isUsingAbility = true;
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        if (isUsingAbility && equipmentSlot != null) {
            if (entity instanceof Player player && (splinterTimer == 5 || splinterTimer == 10 || splinterTimer == 15)) {
                Projectile.spawnProjectileFromRotation(Snowball::new, serverLevel, itemStack, player, 0.0F, 1.5F, 1.0F);
            }
            ++splinterTimer;
            if (splinterTimer == 16) {
                splinterTimer = 0;
                isUsingAbility = false;
            }
        } else if (isUsingAbility) {
            isUsingAbility = false;
        }
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Mth.clamp(Math.round(13.0F - (((float)itemStack.getOrDefault(ArtistryComponents.SPLINTER_COUNT, 0) * (1 - 2)) + 16) * 13.0F / 16.0F), 0, 13);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        int i = 16;
        float f = Math.max(0.0F, ((float)i + (((float)itemStack.getOrDefault(ArtistryComponents.SPLINTER_COUNT, 0) * (1 - 2)) + 16)) / (float)i);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }
}
