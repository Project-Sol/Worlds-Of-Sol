package projectsol.worldsofsol.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import projectsol.worldsofsol.common.entity.ai.goal.GreylinTrackTargetGoal;

import java.util.EnumSet;
import java.util.UUID;

public class GreylinEntity extends HostileEntity implements Angerable {

    private int lastAngrySoundAge = -2147483648;
    private static final TrackedData<Boolean> ANGRY;
    private static final TrackedData<Boolean> PROVOKED;
    private int ageWhenTargetSet;
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    private UUID targetUuid;
    private int lastAttackedTime;

    public GreylinEntity(EntityType<? extends GreylinEntity> entityType, World world) {
        super(entityType, world);
        this.stepHeight = 1.0F;
    }

    public static DefaultAttributeContainer.Builder createGreylinAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.21D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
    }

    public void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(2, new GreylinEntity.ChasePlayerGoal(this));
        this.goalSelector.add(5, new GreylinEntity.FleeGoal<>(this, PlayerEntity.class, 8.0F, 0.21D, 0.25D));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D, 0.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));

        this.targetSelector.add(1, new GreylinTrackTargetGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(3, new FollowTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(4, new UniversalAngerGoal(this, false));
    }


    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (target == null) {
            this.ageWhenTargetSet = 0;
            this.dataTracker.set(ANGRY, false);
            this.dataTracker.set(PROVOKED, false);
        } else {
            this.ageWhenTargetSet = this.age;
            this.dataTracker.set(ANGRY, true);
        }
    }

    public void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGRY, false);
        this.dataTracker.startTracking(PROVOKED, false);
    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    public void setAngerTime(int ticks) {
        this.angerTime = ticks;
    }

    public int getAngerTime() {
        return this.angerTime;
    }

    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    public UUID getAngryAt() {
        return this.targetUuid;
    }

    public void playAngrySound() {
        if (this.age >= this.lastAngrySoundAge + 400) {
            this.lastAngrySoundAge = this.age;
            if (!this.isSilent()) {
                this.world.playSound(this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, this.getSoundCategory(), 2.5F, 1.0F, false);
            }
        }
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (player.getStackInHand(hand).isEmpty()) {
            return ActionResult.PASS;
        } else if (player.getRandom().nextFloat() >= 0.5) {
            player.giveItemStack(stack.copy());
        } else
            stack.decrement(1);
        return ActionResult.SUCCESS;
    }

    public SoundEvent getAmbientSound() {
        return this.isAngry() ? SoundEvents.ENTITY_ENDERMAN_SCREAM : SoundEvents.ENTITY_ENDERMAN_AMBIENT;
    }

    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ENDERMAN_HURT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDERMAN_DEATH;
    }

    public boolean isAngry() {
        return (Boolean)this.dataTracker.get(ANGRY);
    }

    public boolean isProvoked() {
        return (Boolean)this.dataTracker.get(PROVOKED);
    }

    public void setProvoked() {
        this.dataTracker.set(PROVOKED, true);
    }

    static {
        ANGRY = DataTracker.registerData(GreylinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        PROVOKED = DataTracker.registerData(GreylinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    }

    static class ChasePlayerGoal extends Goal {
        private final GreylinEntity greylin;
        private LivingEntity target;

        public ChasePlayerGoal(GreylinEntity greylin) {
            this.greylin = greylin;
            this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
        }

        public boolean canStart() {
            this.target = this.greylin.getTarget();
            if (!(this.target instanceof PlayerEntity)) {
                return false;
            } else {
                double d = this.target.squaredDistanceTo(this.greylin);
                return d > 256.0D ? false : greylin.getLastAttackedTime() > this.greylin.lastAttackedTime;
            }
        }

        public void start() {
            this.greylin.getNavigation().stop();
        }

        public void tick() {
            this.greylin.getLookControl().lookAt(this.target.getX(), this.target.getEyeY(), this.target.getZ());
        }
    }

    private static class FleeGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
        private final GreylinEntity greylin;

        public FleeGoal(GreylinEntity greylin, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(greylin, fleeFromType, distance, slowSpeed, fastSpeed);
            this.greylin = greylin;
        }

        public boolean canStart() {
            return super.canStart();
        }
    }
}
