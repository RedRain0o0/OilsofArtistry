package io.github.redrain0o0.artistry.entity.client.model;

import io.github.redrain0o0.artistry.entity.client.TermiteRenderState;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import java.util.Set;

public class TermiteModel extends EntityModel<TermiteRenderState> {
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true, 8.0F, 3.35F, Set.of("head"));
    public final ModelPart root;
    public final ModelPart head_bone;
    private final ModelPart pincher_left_bone;
    private final ModelPart pincher_right_bone;
    private final ModelPart leg_front_right_bone;
    private final ModelPart leg_middle_right_bone;
    private final ModelPart leg_back_right_bone;
    private final ModelPart leg_front_left_bone;
    private final ModelPart leg_middle_left_bone;
    private final ModelPart leg_back_left_bone;

    public TermiteModel(ModelPart modelPart) {
        super(modelPart.getChild("root"));
        this.root = modelPart.getChild("root");
        this.head_bone = this.root.getChild("head_bone");
        this.pincher_left_bone = this.head_bone.getChild("pincher_left_bone");
        this.pincher_right_bone = this.head_bone.getChild("pincher_right_bone");
        this.leg_front_right_bone = this.root.getChild("leg_front_right_bone");
        this.leg_middle_right_bone = this.root.getChild("leg_middle_right_bone");
        this.leg_back_right_bone = this.root.getChild("leg_back_right_bone");
        this.leg_front_left_bone = this.root.getChild("leg_front_left_bone");
        this.leg_middle_left_bone = this.root.getChild("leg_middle_left_bone");
        this.leg_back_left_bone = this.root.getChild("leg_back_left_bone");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-2.5F, -5.0F, -4.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(-1.5F, -4.0F, 5.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 21.25F, 0.0F));

        PartDefinition head_bone = root.addOrReplaceChild("head_bone", CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, -1.5F, -4.0F));

        PartDefinition pincher_left_bone = head_bone.addOrReplaceChild("pincher_left_bone", CubeListBuilder.create().texOffs(-1, 4).addBox(-1.0F, 0.0F, -4.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.rotation(1.5F, 1.5F, -2.5F));

        PartDefinition pincher_right_bone = head_bone.addOrReplaceChild("pincher_right_bone", CubeListBuilder.create().texOffs(-5, 4).addBox(-1.0F, 0.0F, -4.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.rotation(-1.5F, 1.5F, -2.5F));

        PartDefinition leg_front_right_bone = root.addOrReplaceChild("leg_front_right_bone", CubeListBuilder.create().texOffs(-2, 21).addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(-1.5F, 0.0F, -3.0F));

        PartDefinition leg_middle_right_bone = root.addOrReplaceChild("leg_middle_right_bone", CubeListBuilder.create().texOffs(9, 14).addBox(-4.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.rotation(-1.5F, 0.0F, 0.5F));

        PartDefinition leg_back_right_bone = root.addOrReplaceChild("leg_back_right_bone", CubeListBuilder.create().texOffs(6, 21).addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(-1.5F, 0.0F, 4.0F));

        PartDefinition leg_front_left_bone = root.addOrReplaceChild("leg_front_left_bone", CubeListBuilder.create().texOffs(-2, 23).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(1.5F, 0.0F, -3.0F));

        PartDefinition leg_middle_left_bone = root.addOrReplaceChild("leg_middle_left_bone", CubeListBuilder.create().texOffs(9, 15).addBox(0.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.rotation(1.5F, 0.0F, 0.5F));

        PartDefinition leg_back_left_bone = root.addOrReplaceChild("leg_back_left_bone", CubeListBuilder.create().texOffs(6, 23).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(1.5F, 0.0F, 4.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void setupAnim(TermiteRenderState state) {
        super.setupAnim(state);
    }
}
