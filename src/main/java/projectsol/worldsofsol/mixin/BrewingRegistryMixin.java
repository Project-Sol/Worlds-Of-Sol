package projectsol.worldsofsol.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectsol.worldsofsol.common.registry.ItemRegistry;
import projectsol.worldsofsol.common.registry.StatusEffectRegistry;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingRegistryMixin {
    @Shadow
    public static void registerPotionRecipe(Potion in, Item item, Potion out) {
    }

    @SuppressWarnings("unused")
    @Inject(method = "registerDefaults", at = @At("TAIL"))
    private static void invokeRegisterPotionRecipe(CallbackInfo callbackInfo) {
        registerPotionRecipe(Potions.WATER_BREATHING, ItemRegistry.OXIFARIBACTE, StatusEffectRegistry.COSMIC_BREATHING_POTION);
        registerPotionRecipe(StatusEffectRegistry.COSMIC_BREATHING_POTION, Items.REDSTONE, StatusEffectRegistry.COSMIC_BREATHING_POTION_LONG);
    }
}