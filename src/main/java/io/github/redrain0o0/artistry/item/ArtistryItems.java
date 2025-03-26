package io.github.redrain0o0.artistry.item;

import io.github.redrain0o0.artistry.Artistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ArtistryItems {
    public static final Item CHISEL = register("chisel", new Item.Properties().stacksTo(1));

    public static Item register(String name, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Artistry.MOD_ID, name));
        Item.Properties props = properties.setId(itemKey);

        return Registry.register(BuiltInRegistries.ITEM, itemKey, new Item(props));
    }

    public static void registerModItems() {
        Artistry.LOGGER.info("Registering Mod Items...");
        addItemToGroup(CHISEL, Items.BRUSH, "chisel");
    }

    private static void addItemToGroup(Item item, Item itemAfter, String name) {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(fabricItemGroupEntries -> fabricItemGroupEntries.addAfter(itemAfter, item));
        Artistry.LOGGER.info("Registered item {}:{}", Artistry.MOD_ID, name);
    }
}
