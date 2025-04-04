package io.github.redrain0o0.artistry.item.client.model;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class SplinterShieldModel extends Model {
    public static final ModelLayerLocation SPLINTER_SHIELD = new ModelLayerLocation(Artistry.createID("splinter_shield"), "main");
    private static final String PLATE = "plate";
    private static final String HANDLE = "handle";
    private static final String SPLINTER_LAUNCHER = "splinter_launcher";
    private static final String KNOT_LEFT = "knot_left";
    private static final String KNOT_RIGHT = "knot_right";
    private static final String PINCERS = "pincers";
    private static final int SHIELD_WIDTH = 10;
    private static final int SHIELD_HEIGHT = 20;
    private final ModelPart plate;
    private final ModelPart handle;
    //private final ModelPart splinter_launcher;
    //private final ModelPart knot_left;
    //private final ModelPart knot_right;
    //private final ModelPart pincers;

    public SplinterShieldModel(ModelPart modelPart) {
        super(modelPart, RenderType::entitySolid);
        this.plate = modelPart.getChild("plate");
        this.handle = modelPart.getChild("handle");
        //this.splinter_launcher = modelPart.getChild("splinter_launcher");
        //this.knot_left = modelPart.getChild("knot_left");
        //this.knot_right = modelPart.getChild("knot_right");
        //this.pincers = modelPart.getChild("pincers");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.0F, -2.01F, 12.0F, 22.0F, 1.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("splinter_launcher", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -4.0F, -5.0F, 6.0F, 6.0F, 3.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("knot_left", CubeListBuilder.create().texOffs(18, 23).mirror().addBox(-8.0F, -4.0F, -1.51F, 2.0F, 4.0F, 0.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("knot_right", CubeListBuilder.create().texOffs(18, 23).addBox(6.0F, -4.0F, -1.51F, 2.0F, 4.0F, 0.0F), PartPose.ZERO);
        partDefinition.addOrReplaceChild("pincers", CubeListBuilder.create().texOffs(-7, 32).addBox(-5.0F, -1.0F, -9F, 10.0F, 0.0F, 7.0F), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public ModelPart plate() {
        return this.plate;
    }

    public ModelPart handle() {
        return this.handle;
    }

    /*public ModelPart splinter_launcher() {
        return this.splinter_launcher;
    }

    public ModelPart knot_left() {
        return this.knot_left;
    }

    public ModelPart knot_right() {
        return this.knot_right;
    }

    public ModelPart pincers() {
        return this.pincers;
    }*/
}
