package projectsol.worldsofsol.client.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.WorldsOfSolClient;
import projectsol.worldsofsol.client.entity.model.MeteorHeadModel;
import projectsol.worldsofsol.common.entity.MeteorHeadEntity;

@Environment(EnvType.CLIENT)
public class MeteorHeadRenderer extends MobEntityRenderer<MeteorHeadEntity, MeteorHeadModel<MeteorHeadEntity>> {
    private static final Identifier TEXTURE = new Identifier("worldsofsol:textures/entity/meteor_head.png");

    public MeteorHeadRenderer(EntityRendererFactory.Context context) {
        super(context, new MeteorHeadModel<>(context.getPart(WorldsOfSolClient.METEOR_HEAD_LAYER)), 0.7F);
    }

    @Override
    public void scale(MeteorHeadEntity meteorHeadEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1F, 1F, 1F);
    }

    @Override
    public Identifier getTexture(MeteorHeadEntity meteorHeadEntity) {
        return TEXTURE;
    }
}