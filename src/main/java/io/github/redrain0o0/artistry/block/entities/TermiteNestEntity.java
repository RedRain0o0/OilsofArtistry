package io.github.redrain0o0.artistry.block.entities;

import io.github.redrain0o0.artistry.block.TermiteNest;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class TermiteNestEntity extends BlockEntity implements Container, MenuProvider {
    private LockCode lockKey;
    @Nullable
    private Component name;
    public static final int MAX_OCCUPANTS = 3;
    private NonNullList<ItemStack> inventory;
    private final ContainerOpenersCounter openersCounter;

    private static final int OUTPUT_SLOT = 4;

    protected final ContainerData containerData;
    private int progress = 0;
    private int maxProgress = 72000;

    public TermiteNestEntity(BlockPos pos, BlockState state) {
        super(ArtistryBlockEntities.TERMITE_NEST_ENTITY, pos, state);
        this.lockKey = LockCode.NO_LOCK;
        inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
                TermiteNestEntity.this.playSound(SoundEvents.BARREL_OPEN);
                TermiteNestEntity.this.updateBlockState(blockState, true);
            }

            protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
                TermiteNestEntity.this.playSound(SoundEvents.BARREL_CLOSE);
                TermiteNestEntity.this.updateBlockState(blockState, false);
            }

            protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int j) {
            }

            protected boolean isOwnContainer(Player player) {
            //    if (player.containerMenu instanceof TermiteNestMenu) {
            //        Container container = ((TermiteNestMenu)player.containerMenu).getContainer();
            //        return container == TermiteNestEntity.this;
            //    } else {
                    return false;
            //    }
            }
        };
        this.containerData = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> TermiteNestEntity.this.progress;
                    case 1 -> TermiteNestEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int j) {
                switch (i) {
                    case 0: TermiteNestEntity.this.progress = j;
                    case 1: TermiteNestEntity.this.maxProgress = j;
                }
            }

            @Override
            public int getCount() {
                return 9;
            }
        };
    }

    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);this.lockKey = LockCode.fromTag(compoundTag, provider);
        this.name = parseCustomNameSafe(compoundTag.get("CustomName"), provider);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compoundTag, this.inventory, provider);
        this.progress = compoundTag.getIntOr("progress", 0);
        this.maxProgress = compoundTag.getIntOr("max_progress", 0);
        //this.recipesUsed.clear();
        //this.recipesUsed.putAll((Map)compoundTag.read("RecipesUsed", RECIPES_USED_CODEC).orElse(Map.of()));
    }

    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        this.lockKey.addToTag(compoundTag, provider);
        if (this.name != null) {
            compoundTag.put("CustomName", (Tag) ComponentSerialization.CODEC.encodeStart(provider.createSerializationContext(NbtOps.INSTANCE), this.name).getOrThrow());
        }
        compoundTag.putInt("progress", this.progress);
        compoundTag.putInt("max_progress", this.maxProgress);
        ContainerHelper.saveAllItems(compoundTag, this.inventory, provider);
        //compoundTag.store("RecipesUsed", RECIPES_USED_CODEC, this.recipesUsed);
    }

    // DONE

    public Component getName() {
        return Component.translatable("container.artistry.termite_nest");
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    public boolean canOpen(Player player) {
        return canUnlock(player, this.lockKey, this.getDisplayName());
    }

    public static boolean canUnlock(Player player, LockCode lockCode, Component component) {
        if (!player.isSpectator() && !lockCode.unlocksWith(player.getMainHandItem())) {
            player.displayClientMessage(Component.translatable("container.isLocked", new Object[]{component}), true);
            player.playNotifySound(SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 1.0F, 1.0F);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getContainerSize() {
        return 9;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public boolean isEmpty() {
        Iterator var1 = this.getItems().iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemStack = (ItemStack)var1.next();
        } while(itemStack.isEmpty());

        return false;
    }

    // DONE

    @Override
    public ItemStack getItem(int i) {
        return this.getItems().get(i);
    }

    @Override
    public ItemStack removeItem(int i, int j) {
        ItemStack itemStack = ContainerHelper.removeItem(this.getItems(), i, j);
        if (!itemStack.isEmpty()) {
            this.setChanged();
        }

        return itemStack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return ContainerHelper.takeItem(this.getItems(), i);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        this.getItems().set(i, itemStack);
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.getItems().clear();
    }

    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return this.canOpen(player) ? this.createMenu(i, inventory) : null;
    }

    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        //return new TermiteNestMenu(i, inventory, this, this.containerData);
        //return ChestMenu.oneRow(i, inventory, this);
        return null;
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, TermiteNestEntity termiteNestEntity) {
        //Artistry.LOGGER.info("Ticking Termite Nest at {}, {}, {} with block state {}", blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState.toString());
    }

    protected void applyImplicitComponents(DataComponentGetter dataComponentGetter) {
        super.applyImplicitComponents(dataComponentGetter);
        this.name = (Component)dataComponentGetter.get(DataComponents.CUSTOM_NAME);
        this.lockKey = (LockCode)dataComponentGetter.getOrDefault(DataComponents.LOCK, LockCode.NO_LOCK);
        ((ItemContainerContents)dataComponentGetter.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY)).copyInto(this.getItems());
    }

    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(DataComponents.CUSTOM_NAME, this.name);
        if (!this.lockKey.equals(LockCode.NO_LOCK)) {
            builder.set(DataComponents.LOCK, this.lockKey);
        }

        builder.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }

    public void removeComponentsFromTag(CompoundTag compoundTag) {
        compoundTag.remove("CustomName");
        compoundTag.remove("lock");
        compoundTag.remove("Items");
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    void updateBlockState(BlockState blockState, boolean bl) {
        this.level.setBlock(this.getBlockPos(), (BlockState)blockState.setValue(TermiteNest.OPEN, bl), 3);
    }

    void playSound(SoundEvent soundEvent) {
        double d = (double)this.worldPosition.getX() + 0.5;
        double e = (double)this.worldPosition.getY() + 1;
        double f = (double)this.worldPosition.getZ() + 0.5;
        this.level.playSound((Entity)null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
