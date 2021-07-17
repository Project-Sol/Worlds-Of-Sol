package projectsol.worldsofsol.common.statuseffect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class RadiationStatusEffect extends StatusEffect {
    public RadiationStatusEffect() {
        super(StatusEffectType.HARMFUL, 0xA9A01C);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(DamageSource.MAGIC, 1.0F * 1 + amplifier);
    }
}
