package io.github.redrain0o0.artistry.item;

import io.github.redrain0o0.artistry.Artistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.List;
import java.util.Optional;

public class ArtistryItems {
    public static final Item CHISEL = register("chisel", new Item.Properties().stacksTo(1));
    public static final Item AMETHYST_SHIELD = register("amethyst_shield", (new Item.Properties()).durability(336).repairable(Items.AMETHYST_SHARD).equippableUnswappable(EquipmentSlot.OFFHAND).component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(0.25F, 1.0F,List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)), new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F), Optional.of(DamageTypeTags.BYPASSES_SHIELD), Optional.of(SoundEvents.SHIELD_BLOCK), Optional.of(SoundEvents.SHIELD_BREAK))).component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));

    public static Item register(String name, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, name));
        Item.Properties props = properties.setId(itemKey);

        return Registry.register(BuiltInRegistries.ITEM, itemKey, new Item(props));
    }

    public static void registerModItems() {
        Artistry.LOGGER.info("Registering Mod Items...");
        addItemToTools(CHISEL, Items.BRUSH, "chisel");
        addItemToCombat(AMETHYST_SHIELD, Items.SHIELD, "amethyst_shield");
    }

    private static void addItemToTools(Item item, Item itemAfter, String name) {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(fabricItemGroupEntries -> fabricItemGroupEntries.addAfter(itemAfter, item));
        Artistry.LOGGER.info("Registered item {}:{}", Artistry.MOD_ID, name);
    }
    private static void addItemToCombat(Item item, Item itemAfter, String name) {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(fabricItemGroupEntries -> fabricItemGroupEntries.addAfter(itemAfter, item));
        Artistry.LOGGER.info("Registered item {}:{}", Artistry.MOD_ID, name);
    }
}
