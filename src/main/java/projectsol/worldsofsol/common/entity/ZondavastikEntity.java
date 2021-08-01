package projectsol.worldsofsol.common.entity;

import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import projectsol.worldsofsol.common.registry.SolEntities;

import java.util.EnumSet;
import java.util.Random;

public class ZondavastikEntity extends AnimalEntity {
    private static final Ingredient BREEDING_INGREDIENT;
    public ZondavastikEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new ZondavastikEntity.ZondavastikMoveControl(this);
    }

    public static DefaultAttributeContainer.Builder cre() {
        return MobEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 9.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.24D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 52.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0D);
    }

    @Override
    public void initGoals() {
        this.goalSelector.add(1, new LookAroundGoal(this));
        this.goalSelector.add(5, new ZondavastikEntity.FlyRandomlyGoal(this));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(4, new TemptGoal(this, 1.2D, BREEDING_INGREDIENT, false));
       // goalSelector.add(0, new SwimGoal(this));
       // goalSelector.add(1, new WanderAroundFarGoal(this, 16.0D));
       // goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 8));
       // goalSelector.add(3, new LookAroundGoal(this));
       // targetSelector.add(1, new RevengeGoal(this));
    }

    @Override
    public boolean canSpawn(WorldAccess worldIn, SpawnReason spawnReasonIn) {
        BlockPos pos = new BlockPos(MathHelper.floor(this.getX()), MathHelper.floor(this.getBoundingBox().minY), MathHelper.floor(this.getZ()));

        return this.random.nextInt(65) == 0 && !worldIn.getBlockCollisions(this, this.getBoundingBox()).findAny().isPresent()
                && !worldIn.containsFluid(this.getBoundingBox()) && worldIn.getLightLevel(pos) > 8
                && super.canSpawn(worldIn, spawnReasonIn);
    }


    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess serverWorldAccess, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag) {
        return super.initialize(serverWorldAccess, difficulty, spawnReason, entityData, entityTag);
    }

    @Override
    public ZondavastikEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        passiveEntity.setBreedingAge(-24000);
        return SolEntities.ZONDAVASTIK_ENTITY.create(serverWorld);
    }
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
    public void tick() {
        super.tick();
        setNoGravity(true);
    }
    static {
        BREEDING_INGREDIENT = Ingredient.ofItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
    }
    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return this.isBaby() ? dimensions.height * 0.85F : dimensions.height * 0.92F;
    }


    static class ZondavastikMoveControl extends MoveControl {
        private final ZondavastikEntity zondavastikEntity;
        private int collisionCheckCooldown;

        public ZondavastikMoveControl(ZondavastikEntity zondavastikEntity) {
            super(zondavastikEntity);
            this.zondavastikEntity = zondavastikEntity;
        }

        public void tick() {
            if (this.state == MoveControl.State.MOVE_TO) {
                if (this.collisionCheckCooldown-- <= 0) {
                    this.collisionCheckCooldown += this.zondavastikEntity.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.zondavastikEntity.getX(), this.targetY - this.zondavastikEntity.getY(), this.targetZ - this.zondavastikEntity.getZ());
                    double d = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.willCollide(vec3d, MathHelper.ceil(d))) {
                        this.zondavastikEntity.setVelocity(this.zondavastikEntity.getVelocity().add(vec3d.multiply(0.1D)));
                    } else {
                        this.state = MoveControl.State.WAIT;
                    }
                }

            }
        }

        private boolean willCollide(Vec3d direction, int steps) {
            Box box = this.zondavastikEntity.getBoundingBox();

            for(int i = 1; i < steps; ++i) {
                box = box.offset(direction);
                if (!this.zondavastikEntity.world.isSpaceEmpty(this.zondavastikEntity, box)) {
                    return false;
                }
            }

            return true;
        }
    }

    private static class FlyRandomlyGoal extends Goal {
        private final ZondavastikEntity zondavastikEntity;

        public FlyRandomlyGoal(ZondavastikEntity zondavastikEntity) {
            this.zondavastikEntity = zondavastikEntity;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        public boolean canStart() {
            MoveControl moveControl = this.zondavastikEntity.getMoveControl();
            if (!moveControl.isMoving()) {
                return true;
            } else {
                double d = moveControl.getTargetX() - this.zondavastikEntity.getX();
                double e = moveControl.getTargetY() - this.zondavastikEntity.getY();
                double f = moveControl.getTargetZ() - this.zondavastikEntity.getZ();
                double g = d * d + e * e + f * f;
                return g < 1.0D || g > 3600.0D;
            }
        }

        public boolean shouldContinue() {
            return false;
        }

        public void start() {
            Random random = this.zondavastikEntity.getRandom();
            float dx = (random.nextFloat() * 2.0F - 1.0F) * 8.0F;
            float dy = (random.nextFloat() * 2.0F - 1.0F) * 8.0F;
            float dz = (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double x = this.zondavastikEntity.getX() + dx;
            double y = this.zondavastikEntity.getY() + dy;
            double z = this.zondavastikEntity.getZ() + dz;
            this.zondavastikEntity.getMoveControl().moveTo(x, y, z, 0.1);
        }
    }
}