package projectsol.worldsofsol.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import projectsol.worldsofsol.common.registry.SolObjects;
import projectsol.worldsofsol.common.registry.SolStatusEffects;
import projectsol.worldsofsol.common.registry.SolTags;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {


    @Unique
    private int airLastTick;
    int rad = 0;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(method = "tick", at = @At("HEAD"))
    public void getAir(CallbackInfo info) {
        airLastTick = getAir();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void gravity(CallbackInfo info) {
        if (!this.hasNoGravity()) {
            if (world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
                LivingEntity livingEntity = (LivingEntity) (Object) this;
                if(livingEntity instanceof PlayerEntity player){
                    if (player.isSpectator() || player.isCreative()) {
                        return;
                    }
                }
                Vec3d vec3d = this.getVelocity();
                double newY = vec3d.y;
                double gravity = 0.06D;
                if (!this.isOnGround() && !this.isTouchingWater()) {
                    newY += gravity;
                }
                this.setVelocity(vec3d.x, newY, vec3d.z);
            }

        }
    }
    @Inject(method = {"computeFallDamage(FF)I"}, at = {@At("HEAD")}, cancellable = true)
    private void noFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> info) {
        if (!this.world.isClient()) {
            if (world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY) {
                double damage = 0.0D;
                info.setReturnValue((int) damage);
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void spaceTick(CallbackInfo info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if(world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
            if(!livingEntity.hasStatusEffect(SolStatusEffects.COSMIC_BREATHING)){
                if(!SolTags.SPACE_ENTITY.contains(livingEntity.getType())){
                    this.setAir(airLastTick - 1);
                    if (this.getAir() == -20) {
                        this.setAir(0);
                        this.damage(DamageSource.DROWN, 2.0F);
                    }
                }
            }
            if(livingEntity instanceof PlayerEntity player){
                List<Item> equipmentList = new ArrayList<>();
                player.getItemsEquipped().forEach((x) -> equipmentList.add(x.getItem()));
                List<Item> armorList = equipmentList.subList(2, 6);
                if(SolTags.RAD_SUITE.contains(armorList.get(0)) &&
                   SolTags.RAD_SUITE.contains(armorList.get(1)) &&
                   SolTags.RAD_SUITE.contains(armorList.get(2)) &&
                   SolTags.RAD_SUITE.contains(armorList.get(3))){
                }else{
                    player.addStatusEffect(new StatusEffectInstance(SolStatusEffects.RADIATION));
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void moonDamage(CallbackInfo ci) {
        if (!world.isClient) {
            LivingEntity livingEntity = (LivingEntity) (Object) this;
            if(world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY){
                if(livingEntity.hasStatusEffect(SolStatusEffects.RADIATION)){
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