package projectsol.worldsofsol.client.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.WorldsOfSolClient;
import projectsol.worldsofsol.client.entity.model.GreylinModel;
import projectsol.worldsofsol.common.entity.GreylinEntity;

@Environment(EnvType.CLIENT)
public class GreylinEntityRenderer extends MobEntityRenderer<GreylinEntity, GreylinModel<GreylinEntity>> {
    private static final Identifier TEXTURE = new Identifier("worldsofsol:textures/entity/greylin.png");

    public GreylinEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GreylinModel<>(context.getPart(WorldsOfSolClient.GREYLIN_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTexture(GreylinEntity greylinEntity) {
        return TEXTURE;
    }
}
