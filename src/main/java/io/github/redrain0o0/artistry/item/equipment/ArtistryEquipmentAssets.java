package io.github.redrain0o0.artistry.item.equipment;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

public interface ArtistryEquipmentAssets {
    ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Artistry.createID("equipment_asset"));
    ResourceKey<EquipmentAsset> TERMITE = createId("termite");
    ResourceKey<EquipmentAsset> STICK_BUG = createId("stick_bug");
    ResourceKey<EquipmentAsset> ARMADILLO_SCUTE_AMETHYST = createId("armadillo_scute_amethyst");
    static ResourceKey<EquipmentAsset> createId(String string) {
        return ResourceKey.create(ROOT_ID, Artistry.createID(string));
    }
}
