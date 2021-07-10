package projectsol.worldsofsol.common.effect;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import projectsol.worldsofsol.common.registry.ArmorRegistry;
import projectsol.worldsofsol.common.registry.StatusEffectRegistry;

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
        if (entity.getEquippedStack(EquipmentSlot.CHEST).isOf(ArmorRegistry.EXOBONE_CHESTPLATE)
                && entity.getEquippedStack(EquipmentSlot.HEAD).isOf(ArmorRegistry.EXOBONE_HELMET)
                && entity.getEquippedStack(EquipmentSlot.LEGS).isOf(ArmorRegistry.EXOBONE_LEGGINGS)
                && entity.getEquippedStack(EquipmentSlot.FEET).isOf(ArmorRegistry.EXOBONE_BOOTS))
        {
            entity.removeStatusEffect(StatusEffectRegistry.RADIATION);
        } else {
            entity.damage(DamageSource.MAGIC, 1.0F * 1 + amplifier);
        }
    }
}
