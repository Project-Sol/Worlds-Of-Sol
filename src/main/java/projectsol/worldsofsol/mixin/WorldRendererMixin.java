package projectsol.worldsofsol.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.client.SpaceSkybox;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;


@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    private static final Identifier EARTH_PHASES = new Identifier(WorldsOfSol.MODID,"textures/enviroment/earth_phases.png");

    @Shadow private ClientWorld world;
    @Mutable
    @Shadow @Final private static Identifier MOON_PHASES;
    private TextureManager textureManager;

    @Shadow
    public void renderClouds(MatrixStack matrices, Matrix4f matrix4f, float f, double d, double e, double g) {}

    protected void renderCosmicBackground(MatrixStack matrices, float opacity) {
        try {
            //RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();

            for (int i = 0; i < 6; ++i) {
                matrices.push();
                if (i == 0) {
                    RenderSystem.setShaderTexture(0, SpaceSkybox.WEST);
                    matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
                }

                if (i == 1) {
                    RenderSystem.setShaderTexture(0, SpaceSkybox.EAST);
                    matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90.0F));
                }

                if (i == 2) {
                    RenderSystem.setShaderTexture(0, SpaceSkybox.UP);
                    matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180.0F));
                }

                if (i == 3) {
                    RenderSystem.setShaderTexture(0, SpaceSkybox.NORTH);
                    matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(90.0F));
                }

                if (i == 4) {
                    RenderSystem.setShaderTexture(0, SpaceSkybox.SOUTH);
                    matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-90.0F));
                }
                if (i == 0) {
                    RenderSystem.setShaderTexture(0, SpaceSkybox.DOWN);
                }
                Matrix4f matrix4f = matrices.peek().getModel();
                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
                bufferBuilder.vertex(matrix4f, -150.0F, -150.0F, -150.0F).texture(0.0F, 0.0F).color(255, 255, 255, (int) (128 * opacity)).next();
                bufferBuilder.vertex(matrix4f, -150.0F, -150.0F, 150.0F).texture(0.0F, 1.0F).color(255, 255, 255, (int) (128 * opacity)).next();
                bufferBuilder.vertex(matrix4f, 150.0F, -150.0F, 150.0F).texture(1.0F, 1.0F).color(255, 255, 255, (int) (128 * opacity)).next();
                bufferBuilder.vertex(matrix4f, 150.0F, -150.0F, -150.0F).texture(1.0F, 0.0F).color(255, 255, 255, (int) (128 * opacity)).next();
                tessellator.draw();
                matrices.pop();
            }
            RenderSystem.enableTexture();

        } catch (NullPointerException e) {
        e.printStackTrace();
    }
    }
//
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "renderSky", at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V", ordinal = 1, shift = At.Shift.BEFORE, remap = false),
    @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/class_2960;)V", ordinal = 1, shift = At.Shift.BEFORE, remap = false)}
    )
    private void renderOrbitalMoon(MatrixStack matrices, Matrix4f matrix4f, float f, Runnable runnable, CallbackInfo ci) {
        if (world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY) {
            MOON_PHASES = EARTH_PHASES;
            RenderSystem.disableBlend();
        }
    }

    @Inject(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack$Entry;getModel()Lnet/minecraft/util/math/Matrix4f;", ordinal = 2, shift = At.Shift.AFTER))
    private void renderStarfield(MatrixStack matrices, Matrix4f matrix4f, float f, Runnable runnable, CallbackInfo ci) {
        if(world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
            this.renderCosmicBackground(matrices, 1);
        }
    }

    @ModifyArgs(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;color(FFFF)Lnet/minecraft/client/render/VertexConsumer;"))
    private void renderAtmos(Args args) {
        if(world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
            args.set(0, 0.0F);
            args.set(1, 0.0F);
            args.set(2, 0.0F);
            args.set(3, 0.0F);
        }
    }


    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FDDD)V"))
    private void injected(WorldRenderer worldRenderer, MatrixStack matrices, Matrix4f matrix4f, float f, double d, double e, double g) {
        if(world.getRegistryKey() != MoonDimension.MOON_WORLD_KEY){
            this.renderClouds(matrices, matrix4f, f, d, e, g);
        }
    }

}
