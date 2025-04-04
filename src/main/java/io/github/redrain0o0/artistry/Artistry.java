package io.github.redrain0o0.artistry;

import io.github.redrain0o0.artistry.block.ArtistryBlocks;
import io.github.redrain0o0.artistry.block.entities.ArtistryBlockEntities;
import io.github.redrain0o0.artistry.entity.ArtistryEntities;
import io.github.redrain0o0.artistry.entity.client.ArtistryModelLayers;
import io.github.redrain0o0.artistry.item.ArtistryComponents;
import io.github.redrain0o0.artistry.item.ArtistryItemTags;
import io.github.redrain0o0.artistry.item.ArtistryItems;
import io.github.redrain0o0.artistry.misc.ArtistryConfig;
import io.github.redrain0o0.artistry.misc.ArtistryDamageTypes;
import io.github.redrain0o0.artistry.sounds.ArtistrySoundEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Artistry implements ModInitializer {

    public static final String MOD_ID = "artistry";
    public static final Logger LOGGER = LoggerFactory.getLogger("Artistry");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing common...");
        ArtistryConfig.HANDLER.load();
        ArtistryBlocks.initialize();
        ArtistryBlockEntities.initialize();
        ArtistryComponents.initialize();
        ArtistryDamageTypes.initialize();
        ArtistryEntities.initialize();
        ArtistryItems.initialize();
        ArtistryItemTags.initialize();
        ArtistryModelLayers.initialize();
        ArtistrySoundEvents.initialize();
    }

    public static ResourceLocation createID(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
