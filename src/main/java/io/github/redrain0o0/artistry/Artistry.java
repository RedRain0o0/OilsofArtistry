package io.github.redrain0o0.artistry;

import io.github.redrain0o0.artistry.block.ArtistryBlocks;
import io.github.redrain0o0.artistry.block.entities.ArtistryBlockEntities;
import io.github.redrain0o0.artistry.entity.ArtistryEntities;
import io.github.redrain0o0.artistry.entity.client.ArtistryModelLayers;
import io.github.redrain0o0.artistry.item.ArtistryComponents;
import io.github.redrain0o0.artistry.item.ArtistryItemTags;
import io.github.redrain0o0.artistry.item.ArtistryItems;
import io.github.redrain0o0.artistry.item.ArtistryShieldItem;
import io.github.redrain0o0.artistry.misc.ArtistryDamageTypes;
import io.github.redrain0o0.artistry.networking.packet.AttackKeybindC2SPayload;
import io.github.redrain0o0.artistry.sounds.ArtistrySoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Artistry implements ModInitializer {

    public static final String MOD_ID = "artistry";
    public static final Logger LOGGER = LoggerFactory.getLogger("Artistry");
    //public static final ArtistryConfig CONFIG = new ArtistryConfig();

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing common...");
        ArtistryBlocks.initialize();
        ArtistryBlockEntities.initialize();
        ArtistryComponents.initialize();
        ArtistryDamageTypes.initialize();
        ArtistryEntities.initialize();
        ArtistryItems.initialize();
        ArtistryItemTags.initialize();
        ArtistryModelLayers.initialize();
        ArtistrySoundEvents.initialize();

        PayloadTypeRegistry.playC2S().register(AttackKeybindC2SPayload.ID, AttackKeybindC2SPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(AttackKeybindC2SPayload.ID, (payload, context) -> {
            Player player = context.player();
            if (player.isBlocking()) {
                Item item = player.getItemBlockingWith().getItem();
                if (item instanceof ArtistryShieldItem) {
                    ItemStack itemStack = player.getItemBlockingWith();
                    ((ArtistryShieldItem) item).shieldAbility(player, itemStack);
                }
            }
        });
    }

    public static ResourceLocation createID(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
