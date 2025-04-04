package io.github.redrain0o0.artistry.block;

import io.github.redrain0o0.artistry.Artistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ArtistryBlocks {
    public static final Block SCULK_JAW = register(
            "sculk_jaw",
            SculkJaw::new,
            BlockBehaviour.Properties.of().sound(SoundType.SCULK).noOcclusion(),
            true
    );
    public static final Block DISPLAY_CASE = register(
            "display_case",
            DisplayCase::new,
            BlockBehaviour.Properties.of().sound(SoundType.GLASS).noOcclusion(),
            true
    );


    /*public static final Block SUSPICIOUS_DIRT = register(
            "suspicious_dirt",
            BrushableBlock::new
            register("suspicious_gravel", (properties) -> {
        return new BrushableBlock(GRAVEL, SoundEvents.BRUSH_GRAVEL, SoundEvents.BRUSH_GRAVEL, properties);
    }, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.SUSPICIOUS_GRAVEL).pushReaction(PushReaction.DESTROY));*/

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void initialize() {
        Artistry.LOGGER.info("Registering Blocks...");
        addBlockItemToGroup(ArtistryBlocks.SCULK_JAW, Blocks.SCULK_CATALYST, CreativeModeTabs.NATURAL_BLOCKS);
        addBlockItemToGroup(ArtistryBlocks.DISPLAY_CASE, Blocks.BELL, CreativeModeTabs.FUNCTIONAL_BLOCKS);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Artistry.createID(name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Artistry.createID(name));
    }

    private static void addBlockItemToGroup(Block block, Block blockAfter, ResourceKey<CreativeModeTab> group) {
        ItemGroupEvents.modifyEntriesEvent(group).register((itemGroup) -> itemGroup.addAfter(blockAfter.asItem(), block.asItem()));
        Artistry.LOGGER.info("Registered block item {} to group {}", block.asItem(), group.location());
    }
}
