package io.github.redrain0o0.artistry.client.screen;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TermiteNestMenu extends AbstractContainerMenu {
    private final Container container;

    public TermiteNestMenu(MenuType<?> menuType, int i, Inventory inventory, Container container) {
        super(menuType, i);
        checkContainerSize(container, 9);
        this.container = container;
        container.startOpen(inventory.player);
        this.addChestGrid(container, 8, 18);
        int l = 18 + 3 * 18 + 13;
        this.addStandardInventorySlots(inventory, 8, l);
    }

    private void addChestGrid(Container container, int i, int j) {
        for(int k = 0; k < 3; ++k) {
            for(int l = 0; l < 3; ++l) {
                this.addSlot(new Slot(container, l + k * 9, i + l * 18, j + k * 18));
            }
        }
    }

    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public @NotNull ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(i);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            if (i < 9) {
                if (!this.moveItemStackTo(itemStack2, 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }
}
