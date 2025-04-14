package io.github.redrain0o0.artistry.item;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ArtistryItemTags {
    public static final TagKey<Item> AMETHYST = bind("amethyst");
    public static final TagKey<Item> KNAWED_TOOL_MATERIALS = bind("knawed_tool_materials");
    public static final TagKey<Item> PINCER_TOOL_MATERIALS = bind("pincer_tool_materials");
    public static final TagKey<Item> SHIELDS = bind("shields");

    public static void initialize() {
        Artistry.LOGGER.info("Registering Item Tags...");
    }

    private static TagKey<Item> bind(String string) {
        return TagKey.create(Registries.ITEM, Artistry.createID(string));
    }

}
