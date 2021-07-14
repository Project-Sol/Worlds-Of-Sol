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
import projectsol.worldsofsol.common.registry.SolObjects;
import projectsol.worldsofsol.common.registry.SolStatusEffects;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingRegistryMixin {
    @Shadow
    public static void registerPotionRecipe(Potion in, Item item, Potion out) {
    }

    @SuppressWarnings("unused")
    @Inject(method = "registerDefaults", at = @At("TAIL"))
    private static void registerPotion(CallbackInfo callbackInfo) {
        registerPotionRecipe(Potions.WATER_BREATHING, SolObjects.OXIFARIBACTE, SolStatusEffects.COSMIC_BREATHING_POTION);
        registerPotionRecipe(SolStatusEffects.COSMIC_BREATHING_POTION, Items.REDSTONE, SolStatusEffects.COSMIC_BREATHING_POTION_LONG);
    }
}