package projectsol.worldsofsol;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.client.entity.model.MeteorHeadModel;
import projectsol.worldsofsol.client.entity.render.MeteorHeadRenderer;
import projectsol.worldsofsol.common.registry.SolEntities;

public class WorldsOfSolClient implements ClientModInitializer {
    /*
    public static final SkyboxType<TestSkybox> TYPE;
    public static final DefaultProperties PROPS;
    public static final Conditions CONDITIONS;
    public static final Decorations DECORATIONS;

     */
    public static final EntityModelLayer METEOR_HEAD_LAYER = new EntityModelLayer(new Identifier("worldsofsol:meteor_head_render_layer"), "meteor_head_render_layer");

    @Override
    public void onInitializeClient() {


        EntityRendererRegistry.INSTANCE.register(SolEntities.METEOR_HEAD_ENTITY, MeteorHeadRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(METEOR_HEAD_LAYER, MeteorHeadModel::getTexturedModelData);
        /*
        Registry.register(SkyboxType.REGISTRY, TYPE.createId("test"), TYPE);
        SkyboxManager.getInstance().addPermanentSkybox(TestSkybox.INSTANCE);

         */
    }
    /*

    static {
        TYPE = SkyboxType.Builder.create(
                TestSkybox.class,
                "an-entirely-hardcoded-skybox"
        ).add(2, TestSkybox.CODEC).build();
        DECORATIONS = new Decorations(
                PlayerScreenHandler.BLOCK_ATLAS_TEXTURE,
                SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE,
                true,
                true,
                false,
                Rotation.DEFAULT
        );
        CONDITIONS = new Conditions.Builder()
                .biomes(new Identifier("minecraft:plains"))
                .worlds(new Identifier("minecraft:overworld"))
                .weather(Weather.CLEAR)
                .heights(new HeightEntry(40, 120))
                .build();
        PROPS = new DefaultProperties.Builder()
                .changesFog()
                .rotates()
                .rotation(
                        new Rotation(
                                new Vec3f(0.1F, 0.0F, 0.1F),
                                new Vec3f(0.0F, 0.0F, 0.0F),
                                1
                        )
                )
                .maxAlpha(0.99F)
                .transitionSpeed(0.7F)
                .fade(new Fade(1000, 2000, 11000, 12000, false))
                .build();
    }

     */
}
