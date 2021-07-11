package projectsol.worldsofsol.common.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

public class MoonSurfaceBuilderConfig implements SurfaceConfig {
    public static final Codec<MoonSurfaceBuilderConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(BlockState.CODEC.fieldOf("top_material")
            .forGetter(config -> config.topMaterial), BlockState.CODEC.fieldOf("under_material")
            .forGetter(config -> config.underMaterial), BlockState.CODEC.fieldOf("underwater_material")
            .forGetter(config -> config.underwaterMaterial)).apply(instance, MoonSurfaceBuilderConfig::new));

    private final BlockState topMaterial;
    private final BlockState underMaterial;
    private final BlockState underwaterMaterial;

    public MoonSurfaceBuilderConfig(BlockState topMaterial, BlockState underMaterial, BlockState underwaterMaterial) {
        this.topMaterial = topMaterial;
        this.underMaterial = underMaterial;
        this.underwaterMaterial = underwaterMaterial;
    }

    @Override
    public BlockState getTopMaterial() {
        return this.topMaterial;
    }

    @Override
    public BlockState getUnderMaterial() {
        return this.underMaterial;
    }

    public BlockState getUnderwaterMaterial() {
        return this.underwaterMaterial;
    }
}
