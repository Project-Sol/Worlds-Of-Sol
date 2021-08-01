package projectsol.worldsofsol;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.client.entity.model.MeteorHeadModel;
import projectsol.worldsofsol.client.entity.model.ZondavastikModel;
import projectsol.worldsofsol.client.entity.render.MeteorHeadRenderer;
import projectsol.worldsofsol.client.entity.render.ZondavastikRenderer;
import projectsol.worldsofsol.common.registry.SolEntities;

public class WorldsOfSolClient implements ClientModInitializer {
    public static MinecraftClient client;
    public static final EntityModelLayer METEOR_HEAD_LAYER = new EntityModelLayer(new Identifier("worldsofsol:meteor_head_render_layer"), "meteor_head_render_layer");
    public static final EntityModelLayer ZONDAVASTIK_LAYER = new EntityModelLayer(new Identifier("worldsofsol:zondavastik_render_layer"), "zondavastik_render_layer");


    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();

        EntityRendererRegistry.INSTANCE.register(SolEntities.METEOR_HEAD_ENTITY, MeteorHeadRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(METEOR_HEAD_LAYER, MeteorHeadModel::getTexturedModelData);


        EntityRendererRegistry.INSTANCE.register(SolEntities.ZONDAVASTIK_ENTITY, ZondavastikRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ZONDAVASTIK_LAYER, ZondavastikModel::getTexturedModelData);
    }
}
