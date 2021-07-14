package projectsol.worldsofsol.mixin;

import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.world.gen.ChunkRandom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectsol.worldsofsol.client.OctaveSimplexNoise;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

import java.util.Random;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {


    @Shadow protected abstract void renderStars();

    @Shadow private ClientWorld world;

    @Shadow
    public void renderClouds(MatrixStack matrices, Matrix4f matrix4f, float f, double d, double e, double g) {

    }


    @Inject(method = "renderStars(Lnet/minecraft/client/render/BufferBuilder;)V", at = @At(value = "HEAD"), cancellable = true)
    private void disableLightSky(BufferBuilder builder, CallbackInfo callbackInfo) {
        this.renderStarsWithNoise(
                builder,
                5000,
                0.15f,
                0.1f,
                10842L,
                50,
                0.5F
            );
        callbackInfo.cancel();
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FDDD)V"))
    private void injected(WorldRenderer worldRenderer, MatrixStack matrices, Matrix4f matrix4f, float f, double d, double e, double g) {
        if(world.getRegistryKey() != MoonDimension.MOON_WORLD_KEY){
            this.renderClouds(matrices, matrix4f, f, d, e, g);
        }
    }


    @Unique
    private void renderStarsWithNoise(
            BufferBuilder buffer,
            int starCount,
            float baseSize,
            float sizeModifier,
            long seed,
            int noisePercentage,
            double noiseThreshold
    ) {
        Random random = new Random(10842L);
        OctaveSimplexNoise fieldSampler = new OctaveSimplexNoise(new ChunkRandom(seed), 3);

        // Cap noise threshold
        if (noiseThreshold > 1.0)
            noiseThreshold = 1.0;

        // Make a portion of the stars noise-based,
        // the rest, vanilla randomized.
        int noiseStarCount = (int)Math.floor(starCount * noisePercentage / 100D);

        double[] ipts = new double[starCount];
        double[] jpts = new double[starCount];
        double[] kpts = new double[starCount];

        int stars = 0;
        while (stars < noiseStarCount) {
            double i = random.nextFloat() * 2.0f - 1.0f;
            double j = random.nextFloat() * 2.0f - 1.0f;
            double k = random.nextFloat() * 2.0f - 1.0f;

            double weight = fieldSampler.sample(i, j, k);

            if (weight + random.nextDouble() * 0.2 > noiseThreshold) {
                ipts[stars] = i;
                jpts[stars] = j;
                kpts[stars] = k;

                stars++;
            }
        }

        while (stars < starCount) {
            ipts[stars] = random.nextFloat() * 2.0f - 1.0f;
            jpts[stars] = random.nextFloat() * 2.0f - 1.0f;
            kpts[stars] = random.nextFloat() * 2.0f - 1.0f;

            stars++;
        }

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);

        for (int i = 0; i < starCount; ++i) {
            double double5 = ipts[i];
            double double7 = jpts[i];
            double double9 = kpts[i];

            double double11 = baseSize + random.nextFloat() * sizeModifier;
            double double13 = double5 * double5 + double7 * double7 + double9 * double9;
            if (double13 < 1.0 && double13 > 0.01) {
                double13 = 1.0 / Math.sqrt(double13);
                double5 *= double13;
                double7 *= double13;
                double9 *= double13;
                double double15 = double5 * 100.0;
                double double17 = double7 * 100.0;
                double double19 = double9 * 100.0;
                double double21 = Math.atan2(double5, double9);
                double double23 = Math.sin(double21);
                double double25 = Math.cos(double21);
                double double27 = Math.atan2(Math.sqrt(double5 * double5 + double9 * double9), double7);
                double double29 = Math.sin(double27);
                double double31 = Math.cos(double27);
                double double33 = random.nextDouble() * 3.141592653589793 * 2.0;
                double double35 = Math.sin(double33);
                double double37 = Math.cos(double33);
                for (int v = 0; v < 4; ++v) {
                    double double42 = ((v & 0x2) - 1) * double11;
                    double double44 = ((v + 1 & 0x2) - 1) * double11;
                    double double48 = double42 * double37 - double44 * double35;
                    double double52;
                    double52 = double44 * double37 + double42 * double35;
                    double double54 = double48 * double29 + 0.0 * double31;
                    double double56 = 0.0 * double29 - double48 * double31;
                    double double58 = double56 * double23 - double52 * double25;
                    double double60 = double54;
                    double double62 = double52 * double23 + double56 * double25;
                    buffer.vertex(double15 + double58, double17 + double60, double19 + double62).next();
                }
            }
        }
    }
}