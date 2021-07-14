package projectsol.worldsofsol.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectsol.worldsofsol.common.registry.SolObjects;
import projectsol.worldsofsol.common.registry.SolStatusEffects;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private ClientWorld world;

    @SuppressWarnings("unused")
    @Inject(method = "renderLightSky", at = @At("HEAD"), cancellable = true)
    private void disableLightSky(CallbackInfo callbackInfo) {
        if(client.world != null){
            if(client.world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
                callbackInfo.cancel();
            }
        }

    }
}