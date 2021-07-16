package projectsol.worldsofsol.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {


    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo info) {
        if (world.getRegistryKey() == MoonDimension.MOON_WORLD_KEY) {
            Vec3d vec3d = this.getVelocity();
            double newY = vec3d.y;
            double gravity = 0.03D;
            if (!this.isOnGround() && !this.isTouchingWater()) {
                newY += gravity;
            }
            this.setVelocity(vec3d.x, newY, vec3d.z);
        }

    }
}