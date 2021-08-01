package projectsol.worldsofsol.common.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import projectsol.worldsofsol.common.entity.GreylinEntity;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class GreylinTrackTargetGoal extends TrackTargetGoal {

    private final GreylinEntity greylin;
    private LivingEntity target;
    private final TargetPredicate targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(64.0D);

    public GreylinTrackTargetGoal(GreylinEntity greylin) {
        super(greylin, false, true);
        this.greylin = greylin;
        this.setControls(EnumSet.of(Goal.Control.TARGET));
    }

    public boolean canStart() {
        Box box = this.greylin.getBoundingBox().expand(10.0D, 8.0D, 10.0D);
        List<? extends LivingEntity> list = this.greylin.world.getTargets(VillagerEntity.class, this.targetPredicate, this.greylin, box);
        List<PlayerEntity> list2 = this.greylin.world.getPlayers(this.targetPredicate, this.greylin, box);
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            LivingEntity livingEntity = (LivingEntity)var4.next();
            VillagerEntity villagerEntity = (VillagerEntity)livingEntity;
            Iterator var7 = list2.iterator();

            while(var7.hasNext()) {
                PlayerEntity playerEntity = (PlayerEntity)var7.next();
                int i = villagerEntity.getReputation(playerEntity);
                if (i <= -100) {
                    this.target = playerEntity;
                }
            }
        }

        if (this.target == null) {
            return false;
        } else if (!(this.target instanceof PlayerEntity) || !this.target.isSpectator() && !((PlayerEntity)this.target).isCreative()) {
            return true;
        } else {
            return false;
        }
    }

    public void start() {
        this.greylin.setTarget(this.target);
        super.start();
    }
}
