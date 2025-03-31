package io.github.redrain0o0.artistry.entity.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class SplinterModel extends Model {
    public SplinterModel(ModelPart modelPart) {
        super(modelPart, RenderType::entityCutout);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F);
        partDefinition.addOrReplaceChild("cross_1", cubeListBuilder, PartPose.rotation(0.7853982F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("cross_2", cubeListBuilder, PartPose.rotation(2.3561945F, 0.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 16, 16);
    }
}
