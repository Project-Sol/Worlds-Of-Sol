package projectsol.worldsofsol.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

@Mixin({BackgroundRenderer.class})
public class BackgroundRendererMixin {
    public BackgroundRendererMixin() {
    }

    @Inject(method = {"Lnet/minecraft/client/render/BackgroundRenderer;applyFog(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/BackgroundRenderer$FogType;FZ)V"}, at = {@At("HEAD")}, cancellable = true)
    private static void disableFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        Entity entity = camera.getFocusedEntity();
        if (entity instanceof ClientPlayerEntity) {
            if (entity.world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY) {
                ci.cancel();
            }
        }
    }
    @ModifyConstant(method = "render", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/util/CubicSampler;sampleColor(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/CubicSampler$RgbFetcher;)Lnet/minecraft/util/math/Vec3d;")), constant = @Constant(intValue = 4, ordinal = 0))
    private static int renderSkyColor(int original) {
        return Integer.MAX_VALUE;
    }
}