package projectsol.worldsofsol.client;

import com.mojang.serialization.Codec;
import io.github.amerebagatelle.fabricskyboxes.skyboxes.AbstractSkybox;
import io.github.amerebagatelle.fabricskyboxes.skyboxes.MonoColorSkybox;
import io.github.amerebagatelle.fabricskyboxes.skyboxes.SkyboxType;
import io.github.amerebagatelle.fabricskyboxes.util.object.Conditions;
import io.github.amerebagatelle.fabricskyboxes.util.object.Decorations;
import io.github.amerebagatelle.fabricskyboxes.util.object.DefaultProperties;
import io.github.amerebagatelle.fabricskyboxes.util.object.RGBA;
import projectsol.worldsofsol.WorldsOfSolClient;

public class TestSkybox extends MonoColorSkybox {
    public static final TestSkybox INSTANCE = new TestSkybox(WorldsOfSolClient.PROPS, WorldsOfSolClient.CONDITIONS, WorldsOfSolClient.DECORATIONS, new RGBA(1, 0, 1, 1));
    public static final Codec<TestSkybox> CODEC = Codec.unit(() -> INSTANCE);

    public TestSkybox(DefaultProperties properties, Conditions conditions, Decorations decorations, RGBA color) {
        super(properties, conditions, decorations, color);
    }

    @Override
    public SkyboxType<? extends AbstractSkybox> getType() {
        return WorldsOfSolClient.TYPE;
    }
}