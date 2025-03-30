package io.github.redrain0o0.artistry.sounds;

import com.google.common.collect.ImmutableList;
import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.item.ArtistryItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.animal.wolf.WolfSoundVariant;
import net.minecraft.world.entity.animal.wolf.WolfSoundVariants;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArtistrySoundEvents {
    public static final Holder<SoundEvent> ARMOR_EQUIP_WOODEN = registerForHolder("item.armor.equip_wooden");

    public static void initialize() {
        Artistry.LOGGER.info("Registering Sound Events...");
    }

    private static Holder<SoundEvent> register(ResourceLocation resourceLocation, ResourceLocation resourceLocation2, float f) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createFixedRangeEvent(resourceLocation2, f));
    }

    private static SoundEvent register(String string) {
        return register(Artistry.createID(string));
    }

    private static SoundEvent register(ResourceLocation resourceLocation) {
        return register(resourceLocation, resourceLocation);
    }

    private static Holder.Reference<SoundEvent> registerForHolder(String string) {
        return registerForHolder(Artistry.createID(string));
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation) {
        return registerForHolder(resourceLocation, resourceLocation);
    }

    private static SoundEvent register(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
        return (SoundEvent)Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation2));
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation2));
    }
}
