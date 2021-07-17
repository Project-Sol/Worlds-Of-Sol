package projectsol.worldsofsol.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {

    @Shadow
    @Final
    public String name;


    @Inject(method = "getDeathMessage", at = @At("HEAD"), cancellable = true)
    public void tickHead(LivingEntity entity, CallbackInfoReturnable<Text> cir) {
        if(entity.world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY && entity.getRecentDamageSource() == DamageSource.DROWN){
            LivingEntity livingEntity = entity.getPrimeAdversary();
            String string = "worldsofsol.death.attack.suffocated";
            cir.setReturnValue(new TranslatableText(string, entity.getDisplayName()));
        }
    }
}