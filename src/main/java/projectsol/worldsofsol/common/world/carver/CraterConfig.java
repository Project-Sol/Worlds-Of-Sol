package projectsol.worldsofsol.common.world.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.CarverDebugConfig;
import net.minecraft.world.gen.heightprovider.HeightProvider;

public class CraterConfig extends CarverConfig {

    public static final Codec<CraterConfig> CRATER_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("probability").forGetter(i -> i.probability),
            HeightProvider.CODEC.fieldOf("y").forGetter(i -> i.y),
            FloatProvider.VALUE_CODEC.fieldOf("y_scale").forGetter(i -> i.yScale),
            CarverDebugConfig.CODEC.fieldOf("debug_settings").forGetter(i -> i.debugConfig),
            Codec.INT.fieldOf("max_radius").forGetter(i -> i.maxRadius),
            Codec.INT.fieldOf("min_radius").forGetter(i -> i.minRadius),
            Codec.INT.fieldOf("range_offset").forGetter(i -> i.rangeOffset)
    ).apply(instance, CraterConfig::new));

    public final int maxRadius;
    public final int minRadius;
    public final int rangeOffset;

    public CraterConfig(float probability, HeightProvider y, FloatProvider yScale, CarverDebugConfig debugConfig, int maxRadius, int minRadius, int idealRangeOffset) {
        super(probability, y, yScale, YOffset.fixed(0), false, debugConfig);
        this.maxRadius = maxRadius;
        this.minRadius = minRadius;
        this.rangeOffset = idealRangeOffset;
    }
}