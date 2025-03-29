package io.github.redrain0o0.artistry;

import io.github.redrain0o0.artistry.block.ArtistryBlocks;
import io.github.redrain0o0.artistry.block.entities.ArtistryBlockEntities;
import io.github.redrain0o0.artistry.entity.ArtistryEntities;
import io.github.redrain0o0.artistry.item.ArtistryItems;
import io.github.redrain0o0.artistry.misc.ArtistryDamageTypes;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Artistry implements ModInitializer {

    public static final String MOD_ID = "artistry";
    public static final Logger LOGGER = LoggerFactory.getLogger("Artistry");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing common...");
        ArtistryBlocks.initialize();
        ArtistryBlockEntities.initialize();
        ArtistryDamageTypes.initialize();
        ArtistryEntities.initialize();
        ArtistryItems.initialize();
    }
}
