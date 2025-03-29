package io.github.redrain0o0.artistry.entity;

import io.github.redrain0o0.artistry.item.ArtistryItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TermiteEntity extends Animal {
    public final AnimationState idleAnimState = new AnimationState();
    private int idleAnimTimeout = 0;

    public TermiteEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.getNavigation().setCanFloat(true);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ArtistryItems.WOOD_SPLINTER);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(ArtistryItems.WOOD_SPLINTER), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.10D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }


    public void aiStep() {

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ARMOR, 2)
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.30000001192092896);
    }

    private void setupAnimStates() {
        if (this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = 40;
            this.idleAnimState.start(this.age);
        } else {
            --this.idleAnimTimeout;
        }
    }

    //public ResourceLocation getTexture() {
    //    var variant = this.getVariant().value();
    //    if (this.hasClosedEyes()) {
    //        return variant.closedEyesTexture();
    //    }
    //    return this.hasLargeEyes() ? variant.largeEyesTexture() : variant.smallEyesTexture();
    //}

    @Override
    public void tick() {
        super.tick();

        if (this.getCommandSenderWorld().isClientSide()) {
            this.setupAnimStates();
        }
    }

    //@Nullable
    //@Override
    //public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
    //    return null;
    //}
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        //AgeableMob termite = TermiteEntity.create(serverLevel, EntitySpawnReason.BREEDING);

        return null;//termite;
    }

}
