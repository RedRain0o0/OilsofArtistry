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

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ArtistryItems {
    public static final Item AMETHYST_CHISEL = register("amethyst_chisel", Item::new, new Item.Properties().durability(432).repairable(ArtistryItemTags.AMETHYST));
    //public static final Item AMETHYST_SHIELD = register("amethyst_shield", ShieldItem::new, new Item.Properties().durability(336).repairable(ArtistryItemTags.AMETHYST).equippableUnswappable(EquipmentSlot.OFFHAND).component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(0.25F, 1.0F,List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)), new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F), Optional.of(DamageTypeTags.BYPASSES_SHIELD), Optional.of(SoundEvents.SHIELD_BLOCK), Optional.of(SoundEvents.SHIELD_BREAK))).component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));
    public static final Item KNAWED_SWORD = register("knawed_sword", Item::new, new Item.Properties().sword(ToolMaterial.IRON, 3.0F, -2.4F));
    public static final Item SPLINTER_SHIELD = register("splinter_shield", SplinterShieldItem::new, new Item.Properties().durability(336).repairable(ItemTags.WOODEN_TOOL_MATERIALS).equippableUnswappable(EquipmentSlot.OFFHAND).component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(0.25F, 1.0F,List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)), new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F), Optional.of(DamageTypeTags.BYPASSES_SHIELD), Optional.of(SoundEvents.SHIELD_BLOCK), Optional.of(SoundEvents.SHIELD_BREAK))).component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));
    public static final Item WOOD_SPLINTER = register("wood_splinter", Item::new, new Item.Properties());

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Artistry.createID(name));
        Item item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {
        Artistry.LOGGER.info("Registering Mod Items...");
        addItemToGroup(ArtistryItems.AMETHYST_CHISEL, Items.BRUSH, CreativeModeTabs.TOOLS_AND_UTILITIES);
        //addItemToGroup(ArtistryItems.AMETHYST_SHIELD, Items.SHIELD, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.KNAWED_SWORD, Items.NETHERITE_SWORD, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.SPLINTER_SHIELD, Items.SHIELD, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.WOOD_SPLINTER, Items.ARROW, CreativeModeTabs.COMBAT);
    }

    private static void addItemToGroup(Item item, Item itemAfter, ResourceKey<CreativeModeTab> group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(fabricItemGroupEntries -> fabricItemGroupEntries.addAfter(itemAfter, item));
        Artistry.LOGGER.info("Registered item {} to group {}", item.toString(), group.location());
    }
}
