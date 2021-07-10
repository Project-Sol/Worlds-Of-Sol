package projectsol.worlds.of.sol.mixin;


import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRegistryAccessor {
    @Invoker
    @SuppressWarnings("unused")
    static void invokeRegisterPotionRecipe(Potion in, Item ingredient, Potion result) {}
}
