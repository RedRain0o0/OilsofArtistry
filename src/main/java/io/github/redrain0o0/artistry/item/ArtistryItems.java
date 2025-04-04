package io.github.redrain0o0.artistry.item;

import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.item.equipment.ArtistryArmorMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ArtistryItems {
    public static final Item AMETHYST_CHISEL;
    //public static final Item AMETHYST_SHIELD;
    public static final Item KNAWED_SWORD;
    public static final Item OXIWIND_BOLT;
    public static final Item SCULK_LATCH;
    public static final Item SMALL_PINCER;
    public static final Item SPLINTER_SHIELD;
    public static final Item SPLINTERS;
    public static final Item TERMITE_HELMET;
    public static final Item WOVEN_PINCER;

    static {
        AMETHYST_CHISEL = register("amethyst_chisel", Item::new, new Item.Properties().durability(432).repairable(ArtistryItemTags.AMETHYST));
        //AMETHYST_SHIELD = register("amethyst_shield", ShieldItem::new, new Item.Properties().durability(336).repairable(ArtistryItemTags.AMETHYST).equippableUnswappable(EquipmentSlot.OFFHAND).component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(0.25F, 1.0F,List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)), new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F), Optional.of(DamageTypeTags.BYPASSES_SHIELD), Optional.of(SoundEvents.SHIELD_BLOCK), Optional.of(SoundEvents.SHIELD_BREAK))).component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));
        KNAWED_SWORD = register("knawed_sword", Item::new, new Item.Properties().sword(ToolMaterial.IRON, 3.0F, -2.4F));
        OXIWIND_BOLT = register("oxiwind_bolt", Item::new, new Item.Properties());
        SCULK_LATCH = register("sculk_latch", Item::new, new Item.Properties());
        SMALL_PINCER = register("small_pincer", Item::new, new Item.Properties());
        SPLINTER_SHIELD = register("splinter_shield", SplinterShieldItem::new, new Item.Properties().component(ArtistryComponents.SPLINTER_COUNT, 0).durability(336).repairable(ItemTags.WOODEN_TOOL_MATERIALS).equippableUnswappable(EquipmentSlot.OFFHAND).component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(0.25F, 1.0F,List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)), new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F), Optional.of(DamageTypeTags.BYPASSES_SHIELD), Optional.of(SoundEvents.SHIELD_BLOCK), Optional.of(SoundEvents.SHIELD_BREAK))).component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));
        SPLINTERS = register("splinters", SplintersItem::new, new Item.Properties());
        TERMITE_HELMET = register("termite_helmet", Item::new, new Item.Properties().humanoidArmor(ArtistryArmorMaterials.TERMITE, ArmorType.HELMET));
        WOVEN_PINCER = register("woven_pincer", Item::new, new Item.Properties());
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Artistry.createID(name));
        Item item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {
        Artistry.LOGGER.info("Registering Items...");
        addItemToGroup(ArtistryItems.AMETHYST_CHISEL, Items.BRUSH, CreativeModeTabs.TOOLS_AND_UTILITIES);
        //addItemToGroup(ArtistryItems.AMETHYST_SHIELD, Items.SHIELD, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.KNAWED_SWORD, Items.NETHERITE_SWORD, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.OXIWIND_BOLT, Items.HEAVY_CORE, CreativeModeTabs.INGREDIENTS);
        addItemToGroup(ArtistryItems.SCULK_LATCH, Items.ECHO_SHARD, CreativeModeTabs.INGREDIENTS);
        addItemToGroup(ArtistryItems.SMALL_PINCER, Items.RABBIT_HIDE, CreativeModeTabs.INGREDIENTS);
        addItemToGroup(ArtistryItems.SPLINTER_SHIELD, Items.SHIELD, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.SPLINTERS, Items.ARROW, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.TERMITE_HELMET, Items.TURTLE_HELMET, CreativeModeTabs.COMBAT);
        addItemToGroup(ArtistryItems.WOVEN_PINCER, ArtistryItems.SMALL_PINCER, CreativeModeTabs.INGREDIENTS);
    }

    private static void addItemToGroup(Item item, Item itemAfter, ResourceKey<CreativeModeTab> group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(fabricItemGroupEntries -> fabricItemGroupEntries.addAfter(itemAfter, item));
        Artistry.LOGGER.info("Registered item {} to group {}", item.toString(), group.location());
    }
}
