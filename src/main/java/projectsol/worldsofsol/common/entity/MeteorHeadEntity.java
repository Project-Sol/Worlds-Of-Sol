package projectsol.worldsofsol.common.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class MeteorHeadEntity extends SlimeEntity {
    public MeteorHeadEntity(EntityType<? extends MeteorHeadEntity> entityType, World world) {
        super(entityType, world);
    }
    public static DefaultAttributeContainer.Builder cre() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 9.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.24D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 52.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0D);
    }
    @Override
    public void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(3, new FollowTargetGoal<>(this, PlayerEntity.class, true));
    }
    public void onPlayerCollision(PlayerEntity player) {
        if (this.canAttack()) {
            this.damage(player);
        }

    }
    @Override
    protected boolean canAttack() {
        return this.canMoveVoluntarily();
    }

    @Override
    protected float getDamageAmount() {
        return super.getDamageAmount() + 2.0F;
    }
    @Override
    protected void damage(LivingEntity target) {
        super.damage(target);
    }

    @Override
    public boolean isSmall() {
        return true;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess serverWorldAccess, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag) {
        return super.initialize(serverWorldAccess, difficulty, spawnReason, entityData, entityTag);
    }
    protected void setSize(int size, boolean heal) {
        super.setSize(size, heal);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue((double)(size * 6));
    }

    public static boolean canSpawn(EntityType<MeteorHeadEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        Optional<RegistryKey<Biome>> optional = world.getBiomeKey(pos);
        boolean bl = (world.getDifficulty() != Difficulty.PEACEFUL && world.getLightLevel(LightType.BLOCK, pos) < 10 && canMobSpawn(type, world, spawnReason, pos, random))
                || spawnReason == SpawnReason.SPAWNER;
        if (Objects.equals(optional, Optional.of(MoonDimension.COMET_TUNDRA))) {
            return bl;
        } else
            return false;
    }
}
