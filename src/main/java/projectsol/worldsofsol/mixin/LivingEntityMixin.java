package projectsol.worldsofsol.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectsol.worldsofsol.common.registry.StatusEffectRegistry;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {


    @Unique
    private int airLastTick;
    int rad = 0;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(method = "tick", at = @At("HEAD"))
    public void tickHead(CallbackInfo info) {
        airLastTick = getAir();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tickTail(CallbackInfo info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if(world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
            if(!livingEntity.hasStatusEffect(StatusEffectRegistry.COSMIC_BREATHING)){
                this.setAir(airLastTick - 1);
                if (this.getAir() == -20) {
                    this.setAir(0);
                    this.damage(DamageSource.DROWN, 2.0F);
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void moonDamage(CallbackInfo ci) {
        if (!world.isClient) {
            LivingEntity livingEntity = (LivingEntity) (Object) this;
            if(world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
                if(livingEntity.hasStatusEffect(StatusEffectRegistry.RADIATION)){
                    rad++;
                    if(rad == 20){
                        livingEntity.damage(DamageSource.ON_FIRE, 1);
                        rad = 0;
                    }
                }
            }
        }
    }
}