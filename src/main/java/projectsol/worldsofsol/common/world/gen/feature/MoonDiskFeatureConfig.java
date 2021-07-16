package projectsol.worldsofsol.common.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;

public class MoonDiskFeatureConfig implements FeatureConfig {

    public static final Codec<DiskFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter((diskFeatureConfig) -> {
            return diskFeatureConfig.state;
        }), IntProvider.createValidatingCodec(0, 8).fieldOf("radius").forGetter((diskFeatureConfig) -> {
            return diskFeatureConfig.radius;
        }), Codec.intRange(0, 4).fieldOf("half_height").forGetter((diskFeatureConfig) -> {
            return diskFeatureConfig.halfHeight;
        }), BlockState.CODEC.listOf().fieldOf("targets").forGetter((diskFeatureConfig) -> {
            return diskFeatureConfig.targets;
        })).apply(instance, DiskFeatureConfig::new);
    });
    public final BlockState state;
    public final IntProvider radius;
    public final int halfHeight;
    public final List<BlockState> targets;

    public MoonDiskFeatureConfig(BlockState state, IntProvider radius, int halfHeight, List<BlockState> targets) {
        this.state = state;
        this.radius = radius;
        this.halfHeight = halfHeight;
        this.targets = targets;
    }
}
