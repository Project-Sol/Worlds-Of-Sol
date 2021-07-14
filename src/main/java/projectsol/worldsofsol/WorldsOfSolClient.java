package projectsol.worldsofsol;

import net.fabricmc.api.ClientModInitializer;

public class WorldsOfSolClient implements ClientModInitializer {
    /*
    public static final SkyboxType<TestSkybox> TYPE;
    public static final DefaultProperties PROPS;
    public static final Conditions CONDITIONS;
    public static final Decorations DECORATIONS;

     */

    @Override
    public void onInitializeClient() {
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
