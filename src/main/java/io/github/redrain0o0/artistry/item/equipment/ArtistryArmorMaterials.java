package io.github.redrain0o0.artistry.item.equipment;

import com.google.common.collect.Maps;
import io.github.redrain0o0.artistry.sounds.ArtistrySoundEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public interface ArtistryArmorMaterials {
    ArmorMaterial TERMITE = new ArmorMaterial(25, makeDefense(0, 0, 0, 3, 0), 9, ArtistrySoundEvents.ARMOR_EQUIP_WOODEN, 0.0F, 0.0F, ItemTags.WOODEN_TOOL_MATERIALS, ArtistryEquipmentAssets.TERMITE);
    ArmorMaterial STICK_BUG = new ArmorMaterial(25, makeDefense(0, 0, 0, 3, 0), 9, ArtistrySoundEvents.ARMOR_EQUIP_WOODEN, 0.0F, 0.0F, ItemTags.WOODEN_TOOL_MATERIALS, ArtistryEquipmentAssets.STICK_BUG);
    ArmorMaterial ARMADILLO_SCUTE_AMETHYST = new ArmorMaterial(4, makeDefense(0, 0, 0, 0, 11), 10, SoundEvents.ARMOR_EQUIP_WOLF, 0.0F, 0.0F, ItemTags.REPAIRS_WOLF_ARMOR, ArtistryEquipmentAssets.ARMADILLO_SCUTE_AMETHYST);

    private static Map<ArmorType, Integer> makeDefense(int i, int j, int k, int l, int m) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, i, ArmorType.LEGGINGS, j, ArmorType.CHESTPLATE, k, ArmorType.HELMET, l, ArmorType.BODY, m));
    }
}
