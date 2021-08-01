package projectsol.worldsofsol.client.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ZombieVillagerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.math.MathHelper;
import projectsol.worldsofsol.common.entity.MeteorHeadEntity;
import projectsol.worldsofsol.common.entity.ZondavastikEntity;

public class ZondavastikModel<T extends ZondavastikEntity> extends CompositeEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart backLegs;
    private final ModelPart frontLegs;
    private final ModelPart frontRight;
    private final ModelPart frontLeft;
    private final ModelPart backRight;
    private final ModelPart backLeft;
    public ZondavastikModel(ModelPart root) {
        body = root.getChild("body");
        tail = body.getChild("tail");
        backLegs = body.getChild("backLegs");
        frontLegs = body.getChild("frontLegs");
        backRight = backLegs.getChild("backRight");
        backLeft = backLegs.getChild("backLeft");
        frontLeft = frontLegs.getChild("frontLeft");
        frontRight = frontLegs.getChild("frontRight");
        head = body.getChild("head");
    }
        public static TexturedModelData getTexturedModelData() {
            ModelData data = new ModelData();
            ModelPartData root = data.getRoot();
            ModelPartData body = root.addChild("body",
                    ModelPartBuilder.create().uv(40, 27).cuboid(-7.0F, -12.0F, -10.0F, 14.0F, 12.0F, 18.0F).uv(0, 0).cuboid(0.0F, 0.0F, -10.0F, 0.0F, 3.0F, 18.0F),
                    ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));ModelPartData head = body.addChild("head", ModelPartBuilder.create(), ModelTransform.of(0.0F, -5.0F, -9.0F, 0.0F, 0.0F, 0.0F));head.addChild("cube_r1", ModelPartBuilder.create().uv(16, 19).cuboid(0.0F, -5.0F, -16.0F, 0.0F, 4.0F, 6.0F).uv(0, 0).cuboid(-4.0F, -13.0F, -16.0F, 8.0F, 8.0F, 9.0F),
                    ModelTransform.of(0.0F, 5.0F, 9.0F, 0.3491F, 0.0F, 0.0F));body.addChild("tail",
                    ModelPartBuilder.create().cuboid(0.0F, -9.0F, 0.0F, 0.0F, 16.0F, 29.0F),
                    ModelTransform.of(0.0F, -5.0F, 8.0F, 0.0F, 0.0F, 0.0F));ModelPartData frontLegs = body.addChild("frontLegs",
                    ModelPartBuilder.create(),
                    ModelTransform.of(0.0F, -1.0F, -4.0F, 0.0F, 0.0F, 0.0F));ModelPartData frontRight = frontLegs.addChild("frontRight",
                    ModelPartBuilder.create(),
                    ModelTransform.of(-5.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.0F));frontRight.addChild("cube_r2",
                    ModelPartBuilder.create().uv(16, 49).cuboid(-6.5F, -5.0F, 3.0F, 4.0F, 4.0F, 8.0F),
                    ModelTransform.of(5.0F, 0.0F, -3.0F, -0.4456F, -0.1974F, 0.0934F));ModelPartData frontLeft = frontLegs.addChild("frontLeft",
                    ModelPartBuilder.create(),
                    ModelTransform.of(5.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.0F));frontLeft.addChild("cube_r3",
                    ModelPartBuilder.create().uv(0, 45).cuboid(2.5F, -5.0F, 3.0F, 4.0F, 4.0F, 8.0F),
                    ModelTransform.of(-5.0F, 0.0F, -3.0F, -0.4456F, 0.1974F, -0.0934F));ModelPartData backLegs = body.addChild("backLegs",
                    ModelPartBuilder.create(),
                    ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));ModelPartData backRight = backLegs.addChild("backRight",
                    ModelPartBuilder.create(),
                    ModelTransform.of(-5.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F));backRight.addChild("cube_r4",
                    ModelPartBuilder.create().uv(0, 13).cuboid(-5.5F, -6.0F, 6.0F, 0.0F, 6.0F, 8.0F).uv(27, 12).cuboid(-6.5F, -5.0F, 2.0F, 4.0F, 4.0F, 9.0F),
                    ModelTransform.of(5.0F, 0.0F, -3.0F, -0.4456F, -0.1974F, 0.0934F));ModelPartData backLeft = backLegs.addChild("backLeft",
                    ModelPartBuilder.create(),
                    ModelTransform.of(5.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F));backLeft.addChild("cube_r5",
                    ModelPartBuilder.create().uv(70, 6).cuboid(5.5F, -6.0F, 8.0F, 0.0F, 6.0F, 8.0F).uv(44, 0).cuboid(2.5F, -5.0F, 2.0F, 4.0F, 4.0F, 9.0F),
                    ModelTransform.of(-5.0F, 0.0F, -3.0F, -0.4456F, 0.1974F, -0.0934F));
                    return TexturedModelData.of(data, 128, 128);
        }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.body);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.tail.yaw = MathHelper.abs(MathHelper.cos(animationProgress * 0.6662F) * 1.4F * limbDistance * 0.2F);
    }

}