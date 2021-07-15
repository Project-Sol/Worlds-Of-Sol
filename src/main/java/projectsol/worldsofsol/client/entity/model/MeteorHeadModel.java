package projectsol.worldsofsol.client.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.MagmaCubeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import projectsol.worldsofsol.common.entity.MeteorHeadEntity;


public class MeteorHeadModel<T extends MeteorHeadEntity> extends CompositeEntityModel<T> {
    private final ModelPart Body;
    private final ModelPart upper_jaw;
    private final ModelPart Lower_jaw;
    public MeteorHeadModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Lower_jaw = this.Body.getChild("Lower_jaw");
        this.upper_jaw = this.Body.getChild("upper_jaw");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData modelPartData1 = modelPartData.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,24.0F,0.0F));
        modelPartData1.addChild("upper_jaw", ModelPartBuilder.create().uv(0,48).cuboid(-8.0F, -8.0F, -16.0F, 16.0F, 4.0F, 16.0F).uv(0,24).cuboid(-8.0F, -4.0F, -16.0F, 16.0F, 8.0F, 16.0F), ModelTransform.pivot(0.0F,-8.0F,8.0F));
        modelPartData1.addChild("Lower_jaw", ModelPartBuilder.create().uv(48,8).cuboid(-8.0F, 4.0F, -16.0F, 16.0F, 4.0F, 16.0F).uv(0,0).cuboid(-8.0F, -4.0F, -16.0F, 16.0F, 8.0F, 16.0F), ModelTransform.pivot(0.0F,-8.0F,8.0F));
        return TexturedModelData.of(modelData,128,128);
    }
    @Override
    public void setAngles(MeteorHeadEntity entity, float limbAngle, float limbDistance, float ageInTicks, float netHeadYaw, float headPitch){
        this.Lower_jaw.pitch = MathHelper.abs(MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance * 0.5F);
        this.upper_jaw.pitch = -MathHelper.abs(MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance * 0.5F);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.Body);
    }
    public void animateModel(T slimeEntity, float f, float g, float h) {
        float i = MathHelper.lerp(h, slimeEntity.lastStretch, slimeEntity.stretch);
        if (i < 0.0F) {
            i = 0.0F;
        }
        this.Lower_jaw.pitch = (float)(-(4)) * i * 1.7F;


    }
}