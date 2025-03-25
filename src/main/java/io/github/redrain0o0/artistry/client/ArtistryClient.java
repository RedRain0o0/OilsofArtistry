package io.github.redrain0o0.artistry.client;

import com.llamalad7.mixinextras.utils.MixinExtrasLogger;
import io.github.redrain0o0.artistry.Artistry;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.network.chat.Component;

public class ArtistryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing client...");
    }
}
