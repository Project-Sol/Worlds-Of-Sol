package projectsol.worldsofsol.mixin;

import net.minecraft.client.render.BackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(BackgroundRenderer.class)
public class SunSkyColorMixin {
    @ModifyConstant(
            method = "render",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/util/CubicSampler;sampleColor(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/CubicSampler$RgbFetcher;)Lnet/minecraft/util/math/Vec3d;")),
            constant = @Constant(intValue = 4, ordinal = 0)
    )
    private static int renderSkyColor(int original) {
            return Integer.MAX_VALUE;

    }
}