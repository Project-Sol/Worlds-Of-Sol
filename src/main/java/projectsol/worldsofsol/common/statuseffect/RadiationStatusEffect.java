package projectsol.worldsofsol.common.statuseffect;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import projectsol.worldsofsol.common.registry.SolObjects;
import projectsol.worldsofsol.common.registry.SolStatusEffects;

public class RadiationStatusEffect extends StatusEffect {
    public RadiationStatusEffect() {
        super(
                StatusEffectType.HARMFUL,
                0xA9A01C);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getEquippedStack(EquipmentSlot.CHEST).isOf(SolObjects.EXOBONE_CHESTPLATE)
                && entity.getEquippedStack(EquipmentSlot.HEAD).isOf(SolObjects.EXOBONE_HELMET)
                && entity.getEquippedStack(EquipmentSlot.LEGS).isOf(SolObjects.EXOBONE_LEGGINGS)
                && entity.getEquippedStack(EquipmentSlot.FEET).isOf(SolObjects.EXOBONE_BOOTS))
        {
            entity.removeStatusEffect(SolStatusEffects.RADIATION);
        } else {
            entity.damage(DamageSource.MAGIC, 1.0F * 1 + amplifier);
        }
    }
}