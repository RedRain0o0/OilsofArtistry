package io.github.redrain0o0.artistry;

import io.github.redrain0o0.artistry.item.ArtistryItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Artistry implements ModInitializer {

    public static final String MOD_ID = "artistry";
    public static final Logger LOGGER = LoggerFactory.getLogger("Artistry");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing common...");
        ArtistryItems.registerModItems();
    }
}
