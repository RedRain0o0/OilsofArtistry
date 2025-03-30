package io.github.redrain0o0.artistry.item;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ArtistryItemTags {
    public static final TagKey<Item> AMETHYST = bind("amethyst");

    public static void initialize() {
        Artistry.LOGGER.info("Registering Mod Item Tags...");
    }

    private static TagKey<Item> bind(String string) {
        return TagKey.create(Registries.ITEM, Artistry.createID(string));
    }

}
