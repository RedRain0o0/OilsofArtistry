package io.github.redrain0o0.artistry.item.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import io.github.redrain0o0.artistry.Artistry;
import io.github.redrain0o0.artistry.entity.client.ArtistryModelLayers;
import io.github.redrain0o0.artistry.item.client.model.SplinterShieldModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class SplinterShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final SplinterShieldModel model;
    private static final ResourceLocation SPLINTER_SHIELD_TEXTURE = Artistry.createID("textures/entity/splinter_shield.png");

    public SplinterShieldSpecialRenderer(SplinterShieldModel splinterShieldModel) {
        this.model = splinterShieldModel;
    }

    @Nullable
    public DataComponentMap extractArgument(ItemStack itemStack) {
        return itemStack.immutableComponents();
    }

    public void render(@Nullable DataComponentMap dataComponentMap, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, boolean bl) {
        /*BannerPatternLayers bannerPatternLayers = dataComponentMap != null ? (BannerPatternLayers)dataComponentMap.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY) : BannerPatternLayers.EMPTY;
        DyeColor dyeColor = dataComponentMap != null ? (DyeColor)dataComponentMap.get(DataComponents.BASE_COLOR) : null;
        boolean bl2 = !bannerPatternLayers.layers().isEmpty() || dyeColor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = bl2 ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;
        VertexConsumer vertexConsumer = material.sprite().wrap(ItemRenderer.getFoilBuffer(multiBufferSource, this.model.renderType(material.atlasLocation()), itemDisplayContext == ItemDisplayContext.GUI, bl));
        this.model.handle().render(poseStack, vertexConsumer, i, j);
        if (bl2) {
            BannerRenderer.renderPatterns(poseStack, multiBufferSource, i, j, this.model.plate(), material, false, (DyeColor) Objects.requireNonNullElse(dyeColor, DyeColor.WHITE), bannerPatternLayers, bl, false);
        } else {
            this.model.plate().render(poseStack, vertexConsumer, i, j);
        }

        poseStack.popPose();*/

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);

        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(multiBufferSource, RenderType.armorCutoutNoCull(SPLINTER_SHIELD_TEXTURE), false, bl);
        this.model.renderToBuffer(poseStack, vertexConsumer, i, j);

        poseStack.popPose();
    }

    @Environment(EnvType.CLIENT)
    public static record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final SplinterShieldSpecialRenderer.Unbaked INSTANCE = new SplinterShieldSpecialRenderer.Unbaked();
        public static final MapCodec<SplinterShieldSpecialRenderer.Unbaked> MAP_CODEC;

        public Unbaked() {
        }

        public MapCodec<SplinterShieldSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            return new SplinterShieldSpecialRenderer(new SplinterShieldModel(entityModelSet.bakeLayer(ArtistryModelLayers.SPLINTER_SHIELD)));
        }

        static {
            MAP_CODEC = MapCodec.unit(INSTANCE);
        }
    }
}
