package projectsol.worldsofsol.common.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

public class CometFeatureConfig implements FeatureConfig {

    private static final Codec<CometFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("spawnX").forGetter((config) -> {
            return config.spawnX;
        }), Codec.INT.fieldOf("spawnX").forGetter((config) -> {
            return config.spawnY;
        }), Codec.INT.fieldOf("spawnX").forGetter((config) -> {
            return config.spawnZ;
        }), Codec.INT.fieldOf("spawnX").forGetter((config) -> {
            return config.tailX;
        }), Codec.INT.fieldOf("spawnX").forGetter((config) -> {
            return config.tailY;
        }), Codec.INT.fieldOf("spawnX").forGetter((config) -> {
            return config.tailZ;
        }), Codec.INT.fieldOf("spawnX").forGetter((config) -> {
            return config.size;
        })).apply(instance, CometFeatureConfig::new);
    });

    public final int spawnX;
    public final int spawnY;
    public final int spawnZ;
    public final int tailX;
    public final int tailY;
    public final int tailZ;
    public final int size;

    public CometFeatureConfig(int spawnX, int spawnY, int spawnZ, int tailX, int tailY, int tailZ, int size) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.spawnZ = spawnZ;
        this.tailX = tailX;
        this.tailY = tailY;
        this.tailZ = tailZ;
        this.size = size;
    }
}

