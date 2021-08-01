package projectsol.worldsofsol.client.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.WorldsOfSolClient;
import projectsol.worldsofsol.client.entity.model.MeteorHeadModel;
import projectsol.worldsofsol.client.entity.model.ZondavastikModel;
import projectsol.worldsofsol.common.entity.MeteorHeadEntity;
import projectsol.worldsofsol.common.entity.ZondavastikEntity;

@Environment(EnvType.CLIENT)
public class ZondavastikRenderer extends MobEntityRenderer<ZondavastikEntity, ZondavastikModel<ZondavastikEntity>> {
    private static final Identifier TEXTURE = new Identifier("worldsofsol:textures/entity/zondavastik.png");

    public ZondavastikRenderer(EntityRendererFactory.Context context) {
        super(context, new ZondavastikModel<>(context.getPart(WorldsOfSolClient.ZONDAVASTIK_LAYER)), 0.7F);
    }

    @Override
    public void scale(ZondavastikEntity zondavastikEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1F, 1F, 1F);
    }

    @Override
    public Identifier getTexture(ZondavastikEntity zondavastikEntity) {
        return TEXTURE;
    }
}