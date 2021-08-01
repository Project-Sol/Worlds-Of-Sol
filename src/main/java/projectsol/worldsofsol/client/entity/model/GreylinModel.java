package projectsol.worldsofsol.client.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import projectsol.worldsofsol.common.entity.GreylinEntity;

public class GreylinModel<T extends GreylinEntity> extends EntityModel<GreylinEntity> {
		private final ModelPart Head;
		private final ModelPart Left_arm;
		private final ModelPart cube_r1;
		private final ModelPart right_arm;
		private final ModelPart cube_r2;
		private final ModelPart Torso;
		private final ModelPart Right_leg;
		private final ModelPart left_leg;

		public GreylinModel(ModelPart root) {
			this.Head = root.getChild("head");
			this.Left_arm = root.getChild("left_arm");
			this.cube_r1 = this.Left_arm.getChild("cube_r1");
			this.right_arm = root.getChild("right_arm");
			this.cube_r2 = this.right_arm.getChild("cube_r2");
			this.Torso = root.getChild("body");
			this.Right_leg = root.getChild("right_leg");
			this.left_leg = root.getChild("left_leg");
		}

		public static TexturedModelData getTexturedModelData() {
			ModelData modelData = new ModelData();
			ModelPartData modelPartData = modelData.getRoot();
			modelPartData.addChild("head", ModelPartBuilder.create().uv(24,42).cuboid(-2.0F, -26.0F, -1.0F, 2.0F, 2.0F, 2.0F).uv(20,0).cuboid(-4.0F, -29.0F, -2.5F, 6.0F, 3.0F, 5.0F).uv(0,0).cuboid(-4.0F, -36.0F, -4.0F, 6.0F, 7.0F, 8.0F).uv(0,15).cuboid(2.0F, -34.0F, 0.0F, 2.0F, 6.0F, 0.0F).uv(18,17).cuboid(-1.0F, -29.0F, -4.0F, 0.0F, 5.0F, 8.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
			ModelPartData modelPartData1 = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(0,0).cuboid(-3.0F, -9.0F, 5.0F, 4.0F, 5.0F, 0.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
			modelPartData1.addChild("cube_r1", ModelPartBuilder.create().uv(0,32).cuboid(-2.0F, -24.0F, 4.0F, 2.0F, 16.0F, 2.0F).uv(38,36).cuboid(-1.0F, -24.0F, 6.0F, 0.0F, 16.0F, 2.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
			ModelPartData modelPartData2 = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(16,42).cuboid(-3.0F, -9.0F, -5.0F, 4.0F, 5.0F, 0.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
			modelPartData2.addChild("cube_r2", ModelPartBuilder.create().uv(34,36).cuboid(-1.0F, -24.0F, -8.0F, 0.0F, 16.0F, 2.0F).uv(8,32).cuboid(-2.0F, -24.0F, -6.0F, 2.0F, 16.0F, 2.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
			modelPartData.addChild("body", ModelPartBuilder.create().uv(0,15).cuboid(-2.5F, -24.0F, -3.0F, 3.0F, 11.0F, 6.0F).uv(18,30).cuboid(-3.5F, -25.0F, 0.0F, 8.0F, 12.0F, 0.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
			modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(34,8).cuboid(-2.0F, -13.0F, -3.0F, 2.0F, 13.0F, 2.0F).uv(42,0).cuboid(0.0F, -13.0F, -2.0F, 2.0F, 13.0F, 0.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
			modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(42,13).cuboid(0.0F, -13.0F, 2.0F, 2.0F, 13.0F, 0.0F).uv(34,23).cuboid(-2.0F, -13.0F, 1.0F, 2.0F, 13.0F, 2.0F), ModelTransform.pivot(0.0F,24.0F,0.0F));
			return TexturedModelData.of(modelData,64,64);
		}

		public void setAngles(GreylinEntity entity, float f, float g, float h, float i, float j) {
			setRotationAngle(this.Head, 0F, -1.5708F, 0F);
			setRotationAngle(this.Torso, 0F, -1.5708F, 0F);
			setRotationAngle(this.cube_r1, 0F, 0F, 0F);
			setRotationAngle(this.cube_r2, 0F, 0F, 0F);
			setRotationAngle(this.left_leg, 0F, -1.5708F, 0F);
			setRotationAngle(this.Left_arm, 0F, -1.5708F, 0F);
			setRotationAngle(this.right_arm, 0F, -1.5708F, 0F);
			setRotationAngle(this.Right_leg, 0F, -1.5708F, 0F);
		}

		@Override
		public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
			Head.render(matrixStack, buffer, packedLight, packedOverlay);
			Left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			Torso.render(matrixStack, buffer, packedLight, packedOverlay);
			Right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public static void setRotationAngle(ModelPart bone, float x, float y, float z) {
			bone.pitch = x;
			bone.yaw = y;
			bone.roll = z;
		}
	//	-1.5708F
}