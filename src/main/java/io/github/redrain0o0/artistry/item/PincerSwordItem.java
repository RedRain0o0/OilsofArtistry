package io.github.redrain0o0.artistry.item;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class PincerSwordItem extends Item {
    public Integer ticksLeftToAttack = 0;
    public Integer ticksUntilBiteBack = 0;
    public Integer hitsUntilSharpAttack = 0;
    public Boolean isSharpAttackStarted = false;

    public PincerSwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        if (ticksLeftToAttack > 0) {
            --ticksLeftToAttack;
            Artistry.LOGGER.info(ticksLeftToAttack.toString());
            if (ticksLeftToAttack == 0) {
                Artistry.LOGGER.info("Combo failed: Ran out of time");
            }
        }
    }

     @Override
     public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) {
         Artistry.LOGGER.info("{} attacked {} using {}", livingEntity2.getName().getString(), livingEntity.getName().getString(), itemStack.getItemName().getString());
         if (ticksLeftToAttack > 0) {
             ticksLeftToAttack = 20;
             ++hitsUntilSharpAttack;
             Artistry.LOGGER.info((hitsUntilSharpAttack == 1) ? "Sharp attack activated {} time" : "Sharp attack activated {} times", hitsUntilSharpAttack);
         }

         if (itemStack.getOrDefault(ArtistryComponents.IS_SHARPENED, false) && !isSharpAttackStarted) {
             Artistry.LOGGER.info("{} is sharpened, starting attack combo.", itemStack.getItemName().getString());
             itemStack.set(ArtistryComponents.IS_SHARPENED, false);
             ticksLeftToAttack = 20;
         }

         if (!itemStack.getOrDefault(ArtistryComponents.IS_SHARPENED, false)) {
             Artistry.LOGGER.info("{} was attacked, preparing Bite Back", livingEntity.getName().getString());
             //livingEntity2.
         }
     }
}
